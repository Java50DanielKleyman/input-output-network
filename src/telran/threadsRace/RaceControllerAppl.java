package telran.threadsRace;

import java.util.Arrays;
import java.util.Scanner;

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
		System.out.printf("Congratulations to thread #%d (winner)", getWinnerNumber(racers, race, distance));
	}

	private static int getWinnerNumber(int racers, Race race, int distance) {

		Arrays.stream(getRacers(racers, race, distance)).forEach(racer -> {
			try {
				racer.start();
				racer.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});

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
