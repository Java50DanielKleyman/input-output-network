package telran.multithreading.consumer;

import telran.multithreading.messaging.MessageBox;

public class Receiver extends Thread {
	private MessageBox messageBox;

	public Receiver(MessageBox messageBox) {
		this.messageBox = messageBox;
	}

	@Override
	public void run() {
		String message = null;
		while (true) {
			try {
				message = messageBox.take();
				System.out.printf("thread id: %d, message: %s\n", getId(), message);

			} catch (InterruptedException e) {
				break;
//				message = messageBox.pull();
//				if (message == null) {
//					break;
//				}
			}
		}
	}
}