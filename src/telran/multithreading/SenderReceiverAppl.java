package telran.multithreading;

import java.util.ArrayList;
import java.util.List;

import telran.multithreading.consumer.Receiver;
import telran.multithreading.messaging.*;
import telran.multithreading.producer.Sender;

public class SenderReceiverAppl {

	public static final int N_MESSAGES = 20;
	private static final int N_RECEIVERS = 10;

	public static void main(String[] args) throws InterruptedException {
		MessageBox messageBox = new MessageBoxString();
		Sender sender = new Sender(messageBox, N_MESSAGES, N_RECEIVERS);
		sender.start();
		List<Receiver> receivers = new ArrayList<>();
		for (int i = 0; i < N_RECEIVERS; i++) {
			Receiver receiver = new Receiver(messageBox);
			receivers.add(receiver);
			receiver.start();
		}
		sender.join();
//		for (Receiver receiver : receivers) {
//			receiver.interrupt();
//		}
	}

}