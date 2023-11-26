package telran.net;

import java.io.*;
import java.net.*;

public class TcpClientHandler implements Closeable, NetworkHandler {
    private static final int RECONNECT_INTERVAL_MS = 1000;

    private Socket socket;
    private ObjectOutputStream writer;
    private ObjectInputStream reader;
    private String host;
    private int port;

    public TcpClientHandler(String host, int port) {
        this.host = host;
        this.port = port;
        connect(); // Initial connection attempt
    }

    private void connect() {
        while (true) {
            try {
                socket = new Socket(host, port);
                writer = new ObjectOutputStream(socket.getOutputStream());
                reader = new ObjectInputStream(socket.getInputStream());
                return; // Connection successful, exit the loop
            } catch (IOException e) {
                System.err.println("Connection attempt failed. Retrying...");
                try {
                    Thread.sleep(RECONNECT_INTERVAL_MS);
                } catch (InterruptedException interruptedException) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    @Override
    public void close() throws IOException {
        writer.close();
        reader.close();
        socket.close();
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T send(String requestType, Serializable requestData) throws IOException {
        Request request = new Request(requestType, requestData);

        while (true) {
            try {
                writer.writeObject(request);
                Response response = (Response) reader.readObject();

                if (response.code() != ResponseCode.OK) {
                    throw new IOException(response.code() + ": " + response.responseData().toString());
                }

                return (T) response.responseData();
            } catch (SocketException e) {
                // Connection lost, attempt to reconnect
                System.err.println("Connection lost. Attempting to reconnect...");
                connect();
            } catch (IOException | ClassNotFoundException e) {
                throw new IOException("Error during network communication", e);
            }
        }
    }
}