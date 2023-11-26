package telran.net;

import java.net.*;
import java.util.concurrent.atomic.AtomicLong;
import java.io.*;

public class ClientSessionHandler implements Runnable {
	Socket socket;
	ObjectInputStream reader;
	ObjectOutputStream writer;
	ApplProtocol protocol;
	TcpServer tcpServer;
	AtomicLong lastActivityTime;

	public ClientSessionHandler(Socket socket, ApplProtocol protocol, TcpServer tcpServer) throws Exception {
		this.socket = socket;
		this.protocol = protocol;
		this.tcpServer = tcpServer;
		this.lastActivityTime = new AtomicLong(System.currentTimeMillis());
		reader = new ObjectInputStream(socket.getInputStream());
		writer = new ObjectOutputStream(socket.getOutputStream());
	}

	@Override
	public void run() {
		while (!tcpServer.executor.isShutdown() || tcpServer.connectedClientsCounter.get() <= tcpServer.getNThreads()
				|| !isIdle(TcpServer.TOTAL_IDLE_TIME)) {
			try {
				Request request = (Request) reader.readObject();
				lastActivityTime.set(System.currentTimeMillis());
				Response response = protocol.getResponse(request);
				writer.writeObject(response);
				writer.reset();
			} catch (SocketTimeoutException e) {
				// for exit from readObject to another iteration of cycle
			} catch (EOFException e) {
				System.out.println("Client closed connection");
				tcpServer.connectedClientsCounter.decrementAndGet();
			} catch (Exception e) {
				System.out.println("Abnormal closing connection");
				tcpServer.connectedClientsCounter.decrementAndGet();
			}
		}

	}
	
	public boolean isIdle(long totalIdleTime) {
		return System.currentTimeMillis() - lastActivityTime.get() > totalIdleTime;
	}	
}