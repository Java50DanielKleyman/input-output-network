package telran.multithreading.producer;

import telran.multithreading.messaging.MessageBox;
import telran.multithreading.messaging.MessageBoxString;

public class Sender extends Thread {
	private MessageBox messageBox;
	private int nMessages;
	boolean currentFlagValue = MessageBoxString.flag;

	public Sender(MessageBox messageBox, int nMessages) {
		this.messageBox = messageBox;
		this.nMessages = nMessages;
	}

	@Override
	public void run() {
		for (int i = 1; i <= nMessages; i++) {
			messageBox.put("message" + i);
			if (i > nMessages) {
				MessageBoxString.flag = false;
			}
		}
	}
}