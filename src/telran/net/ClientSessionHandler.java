package telran.net;

import java.net.*;
import java.io.*;

public class ClientSessionHandler implements Runnable {
	Socket socket;
	ObjectInputStream reader;
	ObjectOutputStream writer;
	ApplProtocol protocol;
	TcpServer tcpServer;
	private long lastActivityTime;

	public ClientSessionHandler(Socket socket, ApplProtocol protocol, TcpServer tcpServer) throws Exception {
		this.socket = socket;
		this.protocol = protocol;
		this.tcpServer = tcpServer;
		reader = new ObjectInputStream(socket.getInputStream());
		writer = new ObjectOutputStream(socket.getOutputStream());
		lastActivityTime = System.currentTimeMillis();
	}

	@Override
	public void run() {
		while (!tcpServer.executor.isShutdown()) {
			try {
				Request request = (Request) reader.readObject();
				Response response = protocol.getResponse(request);
				writer.writeObject(response);
				writer.reset();
				lastActivityTime = System.currentTimeMillis();
			} catch (SocketTimeoutException e) {
				if (isIdle(TcpServer.TOTAL_IDLE_TIME)
						&& tcpServer.getNThreads() > TcpServer.connectedClientsCount.get()) {
					System.out.println("Closing connection due to total idle time exceeding the limit");
					TcpServer.connectedClientsCount.decrementAndGet();
					break;
				}
			} catch (EOFException e) {
				System.out.println("Client closed connection");
			} catch (Exception e) {
				System.out.println("Abnormal closing connection");
			}
		}
	}

	private boolean isIdle(int totalIdleTime) {
		return System.currentTimeMillis() - lastActivityTime > TcpServer.TOTAL_IDLE_TIME;
	}
}
