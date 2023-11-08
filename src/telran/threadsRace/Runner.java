package telran.threadsRace;

import java.util.AbstractMap;
import java.util.Map;

public class Runner extends Thread {
	private Race race;
	private int runnerId;
	private long startTime;

	public Runner(Race race, int runnerId, long startTime) {
		this.race = race;
		this.runnerId = runnerId;
		this.startTime = startTime;
	}

	@Override
	public void run() {
		int sleepRange = race.getMaxSleep() - race.getMinSleep() + 1;
		int minSleep = race.getMinSleep();
		int distance = race.getDistance();
		for (int i = 0; i < distance; i++) {
			try {
				sleep((long) (minSleep + Math.random() * sleepRange));
			} catch (InterruptedException e) {

			}
			System.out.printf("%d - step %d\n", runnerId, i);
		}
		Map.Entry<Integer, Integer> winnerEntry = new AbstractMap.SimpleEntry<>(runnerId,
				(int) (System.currentTimeMillis() - startTime));
		race.setWinnersList(winnerEntry);
	}
}