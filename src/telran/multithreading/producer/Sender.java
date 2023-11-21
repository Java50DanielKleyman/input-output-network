package telran.multithreading.producer;

import telran.multithreading.messaging.MessageBox;

public class Sender extends Thread {
	private MessageBox messageBox;
	private int nMessages;
	private int N_RECEIVERS;

	public Sender(MessageBox messageBox, int nMessages, int N_RECEIVERS) {
		this.messageBox = messageBox;
		this.nMessages = nMessages;
		this.N_RECEIVERS = N_RECEIVERS;
	}

	@Override
	public void run() {
		for (int i = 1; i <= nMessages; i++) {
			messageBox.put("message" + i);			
			}
		for (int i = 1; i <= N_RECEIVERS; i++) {
			messageBox.put("killThread");			
			}
		}
	}
