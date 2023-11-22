package telran.multithreading.messaging;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class MyLinkedBlockingQueue<E> implements MyBlockingQueue<E> {
    private BlockingQueue<E> myLinkedBlockingQueue;
	public MyLinkedBlockingQueue (int capacity) {
		myLinkedBlockingQueue = new LinkedBlockingQueue<E>(capacity);
	}
	@Override
	public boolean add(E obj) {
		
		return myLinkedBlockingQueue.add(obj);
	}

	@Override
	public boolean offer(E obj) {
		
		return myLinkedBlockingQueue.offer(obj);
	}

	@Override
	public void put(E obj) throws InterruptedException {
		myLinkedBlockingQueue.put(obj);

	}

	@Override
	public boolean offer(E obj, long timeout, TimeUnit unit) throws InterruptedException {
		
		return myLinkedBlockingQueue.offer(obj, timeout, unit);
	}

	@Override
	public E remove() {
		
		return myLinkedBlockingQueue.remove();
	}

	@Override
	public E poll() {
		
		return myLinkedBlockingQueue.poll();
	}

	@Override
	public E take() throws InterruptedException {
		
		return myLinkedBlockingQueue.take();
	}

	@Override
	public E poll(long timeout, TimeUnit unit) throws InterruptedException {
		
		return myLinkedBlockingQueue.poll(timeout, unit);
	}

	@Override
	public E element() {
		
		return myLinkedBlockingQueue.element();
	}

	@Override
	public E peek() {
		
		return myLinkedBlockingQueue.peek();
	}

}