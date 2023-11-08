package telran.threadsRace;

import java.util.List;
import java.util.Map;

import telran.view.InputOutput;
import telran.view.Item;
import telran.view.Menu;
import telran.view.SystemInputOutput;

public class RaceAppl {

	private static final int MAX_THREADS = 10;
	private static final int MIN_THREADS = 3;
	private static final int MIN_DISTANCE = 100;
	private static final int MAX_DISTANCE = 3500;
	private static final int MIN_SLEEP = 2;
	private static final int MAX_SLEEP = 5;

	public static void main(String[] args) {
		InputOutput io = new SystemInputOutput();
		Item[] items = getItems();
		Menu menu = new Menu("Race Game", items);
		menu.perform(io);

	}

	private static Item[] getItems() {
		Item[] res = { Item.of("Start new game", RaceAppl::startGame), Item.exit() };
		return res;
	}

	static void startGame(InputOutput io) {
		int nThreads = io.readInt("Enter number of the runners", "Wrong number of the runners", MIN_THREADS,
				MAX_THREADS);
		int distance = io.readInt("Enter distance", "Wrong Distance", MIN_DISTANCE, MAX_DISTANCE);
		Race race = new Race(distance, MIN_SLEEP, MAX_SLEEP);
		Runner[] runners = new Runner[nThreads];
		long startTime = System.currentTimeMillis();
		startRunners(runners, race, startTime);
		joinRunners(runners);
		displayWinner(race);
	}

	private static void displayWinner(Race race) {
		List<Map.Entry<Integer, Integer>> winnersList = race.getWinnersList();
		System.out.println("Place  racer number    time");
		for (int i = 0; i < winnersList.size(); i++) {
			Map.Entry<Integer, Integer> winner = winnersList.get(i);
			  System.out.printf("%d          %d            %d%n", i + 1, winner.getKey(), winner.getValue());
		}

	}

	private static void joinRunners(Runner[] runners) {
		for (int i = 0; i < runners.length; i++) {
			try {
				runners[i].join();
			} catch (InterruptedException e) {

			}
		}

	}

	private static void startRunners(Runner[] runners, Race race, long startTime) {
		for (int i = 0; i < runners.length; i++) {
			runners[i] = new Runner(race, i + 1, startTime);
			runners[i].start();
		}

	}

}