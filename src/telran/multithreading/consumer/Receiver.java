package telran.multithreading.consumer;

import telran.multithreading.messaging.MessageBox;
import telran.multithreading.messaging.MessageBoxString;

public class Receiver extends Thread {
	private MessageBox messageBox;
	boolean currentFlagValue = MessageBoxString.flag;

	public Receiver(MessageBox messageBox) {
		this.messageBox = messageBox;
		// FIXME HW #46 fix setting daemon
		//setDaemon(true); // HW #46 remove it
	}

	@Override
	public void run() {
		while (MessageBoxString.flag) {
			String message = null;
			try {
				message = messageBox.take();
			} catch (InterruptedException e) {
				// TODO
			}
			System.out.printf("thread id: %d, message: %s\n", getId(), message);
		}
		if (!MessageBoxString.flag) {
			String message = null;
			message = messageBox.pull();
			System.out.printf("thread id: %d, message: %s\n", getId(), message);
		}
	}
}