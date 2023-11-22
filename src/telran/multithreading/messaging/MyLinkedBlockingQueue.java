package telran.multithreading.messaging;

import java.util.ArrayDeque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
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
	private int capacity;
	private final Condition notFull;

	public MyLinkedBlockingQueue(int capacity) {
		this.myLinkedBlockingQueue = new ArrayDeque<>(capacity);
		this.lock = new ReentrantReadWriteLock();
		writerLock = lock.writeLock();
		readerLock = lock.readLock();
		this.capacity = capacity;
		notFull = writerLock.newCondition();
	}

	@Override
	public boolean add(E obj) {

		try {
			writerLock.lock();
			return myLinkedBlockingQueue.add(obj);
		} finally {
			writerLock.unlock();
		}
	}

	@Override
	public boolean offer(E obj) {

		try {
			writerLock.lock();
			return myLinkedBlockingQueue.offer(obj);
		} finally {
			writerLock.unlock();
		}
	}

	@Override
	public void put(E obj) throws InterruptedException {
		try {
			writerLock.lock();
			while (myLinkedBlockingQueue.size() == capacity) {
				notFull.await();
			}
			myLinkedBlockingQueue.add(obj);
			notFull.signal();

		} finally {
			writerLock.unlock();
		}

	}

	@Override
	public boolean offer(E obj, long timeout, TimeUnit unit) throws InterruptedException {

		try {
			writerLock.lock();
			boolean added = add(obj);
			if (added) {
				return added;
			} else {
				long timeoutInMillis = getTimeoutInMillis(timeout, unit);
				long startTimeInMillis = System.currentTimeMillis();
				while (!added && ((startTimeInMillis + timeoutInMillis) > System.currentTimeMillis())) {
					added = add(obj);
				}
			}
			return added;
		} finally {
			writerLock.unlock();
		}
	}

	private long getTimeoutInMillis(long timeout, TimeUnit unit) {
		long originalTimeout = timeout;
		TimeUnit originalTimeUnit = unit;
		long timeoutInMillis = originalTimeUnit.toMillis(originalTimeout);

		return timeoutInMillis;
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