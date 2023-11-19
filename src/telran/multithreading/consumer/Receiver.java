package telran.multithreading.consumer;

import telran.multithreading.messaging.MessageBox;
import telran.multithreading.messaging.MessageBoxString;

public class Receiver extends Thread {
	private MessageBox messageBox;

	public Receiver(MessageBox messageBox) {
		this.messageBox = messageBox;		
	}
	@Override
	public void run() {
		String message = null;
		while(MessageBoxString.flag) {
			
			try {
				message = messageBox.take();
			} catch (InterruptedException e) {
				// TODO 
			}
			System.out.printf("thread id: %d, message: %s\n", getId(),message );
		}
		if(!MessageBoxString.flag) {
			message = messageBox.pull();
			if(message != null) {
				System.out.printf("thread id: %d, Last message: %s\n", getId(),message );
			}
			
		}
	}
}