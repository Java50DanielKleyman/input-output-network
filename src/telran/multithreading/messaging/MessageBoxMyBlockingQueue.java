package telran.multithreading.messaging;

/**
 * Message box contains only one string
 */
public class MessageBoxMyBlockingQueue implements MessageBox{
	private MyBlockingQueue<String> messages = new MyLinkedBlockingQueue<>(10);
	
	@Override
	 public void put(String message)  {
		try {
			messages.put(message);
		} catch (InterruptedException e) {
			
		}
		
	}

	@Override
	 public String take() throws InterruptedException {
		return messages.take();
	}

	@Override
	synchronized public String pull() {
		
		
		return messages.poll();
	}


}