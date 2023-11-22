package telran.multithreading.messaging;
import java.util.ArrayDeque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

public class MyLinkedBlockingQueue<E> implements MyBlockingQueue<E> {
	private ArrayDeque<E> myLinkedBlockingQueue;
	ReentrantReadWriteLock lock;
	Lock writerLock;
	Lock readerLock;

    public MyLinkedBlockingQueue(int capacity) {
        this.myLinkedBlockingQueue = new ArrayDeque<>(capacity);
        this.lock = new ReentrantReadWriteLock();
        writerLock = lock.writeLock();
        readerLock = lock.readLock();
        
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