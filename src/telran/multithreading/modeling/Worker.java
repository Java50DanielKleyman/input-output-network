package telran.multithreading.modeling;

import java.util.concurrent.*;

import telran.multithreading.dto.Car;

public class Worker extends Thread {
	int nCars;
	BlockingQueue<Car> cars;

	public Worker(BlockingQueue<Car> cars) {
		this.cars = cars;
	}

	@Override
	public void run() {
		Car car = null;
		try {
			while (!Thread.currentThread().isInterrupted()) {
				car = cars.take();
				processingCar(car);
			}
		} catch (InterruptedException e) {
			while ((car = cars.poll()) != null) {
				try {
					processingCar(car);
				} catch (InterruptedException e1) {

				}
			}

		}
	}

	private void processingCar(Car car) throws InterruptedException {
		sleep(car.repairTime());
		nCars++;
	}
}