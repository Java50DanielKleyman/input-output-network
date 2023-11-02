package telran.threadsRace;

import java.util.Arrays;
import java.util.Scanner;

public class RaceControllerAppl {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int racers = 0;
		int distance = 0;
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
		System.out.printf("Congratulations to thread #%d (winner)", getWinnerNumber(racers));
	}

	private static Object getWinnerNumber(int racers) {
		Race race = new Race();
		
		Arrays.stream(getRacers(racers)).forEach(racer -> {
			try {
				racer.start();
				racer.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});

		return null;
	}

	private static Thread[] getRacers(int racers) {

		return;
	}

}
