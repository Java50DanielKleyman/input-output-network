package telran.multithreading.messaging;

import java.util.ArrayDeque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class MyLinkedBlockingQueue<E> implements MyBlockingQueue<E> {
	private ArrayDeque<E> myLinkedBlockingQueue;
	ReentrantReadWriteLock lock;
	Lock writerLock;
	Lock readerLock;
	private final Condition producer;
	private final Condition consumer;

	public MyLinkedBlockingQueue(int capacity) {
		this.myLinkedBlockingQueue = new ArrayDeque<>(capacity);
		this.lock = new ReentrantReadWriteLock();
		writerLock = lock.writeLock();
		readerLock = lock.readLock();
		producer = writerLock.newCondition();
		consumer = writerLock.newCondition();
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
			while (!myLinkedBlockingQueue.add(obj)) {
				producer.await();
			}
			consumer.signal();

		} finally {
			writerLock.unlock();
		}
	}

	@Override
	public boolean offer(E obj, long timeout, TimeUnit unit) throws InterruptedException {
		boolean isAdded = false;
		long timeoutInMillis = unit.toMillis(timeout);
		long startTimeInMillis = System.currentTimeMillis();
		while (!isAdded && ((startTimeInMillis + timeoutInMillis) > System.currentTimeMillis())) {

			try {
				boolean acquiredLock = writerLock.tryLock();
				if (acquiredLock) {
					isAdded = myLinkedBlockingQueue.offer(obj);
				}
			} finally {
				writerLock.unlock();
			}
		}
		return isAdded;

	}

	@Override
	public E remove() {

		try {
			writerLock.lock();
			return myLinkedBlockingQueue.remove();
		} finally {
			writerLock.unlock();
		}
	}

	@Override
	public E poll() {

		try {
			writerLock.lock();
			return myLinkedBlockingQueue.poll();
		} finally {
			writerLock.unlock();
		}
	}

	@Override
	public E take() throws InterruptedException {

		try {
			writerLock.lock();
			E object = myLinkedBlockingQueue.poll();
			while (object == null) {
				consumer.await();
			}
			producer.signal();
			return object;
		} finally {
			writerLock.unlock();
		}
	}

	@Override
	public E poll(long timeout, TimeUnit unit) throws InterruptedException {
		E object = null;
		long timeoutInMillis = unit.toMillis(timeout);
		long startTimeInMillis = System.currentTimeMillis();
		while (object == null && ((startTimeInMillis + timeoutInMillis) > System.currentTimeMillis())) {

			try {
				boolean acquiredLock = writerLock.tryLock();
				if (acquiredLock) {
					object = myLinkedBlockingQueue.poll();
				}
			} finally {
				writerLock.unlock();
			}
		}
		return object;
	}

	@Override
	public E element() {

		try {
			readerLock.lock();
			return myLinkedBlockingQueue.element();
		} finally {
			readerLock.unlock();
		}
	}

	@Override
	public E peek() {

		try {
			readerLock.lock();
			return myLinkedBlockingQueue.peek();
		} finally {
			readerLock.unlock();
		}
	}
}