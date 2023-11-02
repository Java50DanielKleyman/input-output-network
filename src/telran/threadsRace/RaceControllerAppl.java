package telran.threadsRace;

import java.util.Scanner;
import java.util.concurrent.CountDownLatch;


public class RaceControllerAppl {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int racers = 0;
		int distance = 0;
		Race race = new Race();
		try {
			System.out.println("Enter the number of racers: ");
			racers = scanner.nextInt();
			System.out.println("Enter the distance: ");
			distance = scanner.nextInt();
		} catch (java.util.InputMismatchException e) {
			System.err.println("Invalid input. Please enter a valid integer.");
		} finally {
			scanner.close();
		}
		int winner = getWinnerNumber(racers, race, distance);
		System.out.printf("Congratulations to thread #%d (winner)", winner);
	}

	private static int getWinnerNumber(int racers, Race race, int distance) {

		  Thread[] racersArray = getRacers(racers, race, distance);
		  for (Thread racer : racersArray) {
		        racer.start();
		       }
		  for (Thread racer : racersArray) {
		        try {
					racer.join();
				} catch (InterruptedException e) {					
					e.printStackTrace();
				}
		       }
//		    CountDownLatch latch = new CountDownLatch(racers);
//
//		    for (Thread racer : racersArray) {
//		        racer.start();
//		        new Thread(() -> {
//		            try {
//		                racer.join();
//		                latch.countDown();
//		            } catch (InterruptedException e) {
//		                e.printStackTrace();
//		            }
//		        }).start();
//		    }
//
//		    try {
//		        latch.await();
//		    } catch (InterruptedException e) {
//		        e.printStackTrace();
//		    }

		return race.getWinner();
	}

	private static Thread[] getRacers(int racers, Race race, int distance) {
		Thread[] racersArray = new Thread[racers];
		int sleepingTime = race.getSleepingTime();
		for(int i = 0; i < racersArray.length; i++) {
			racersArray[i] = new Racer(race, distance, sleepingTime);
		}
		return racersArray;
	}

}
