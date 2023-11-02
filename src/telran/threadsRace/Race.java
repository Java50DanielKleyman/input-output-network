package telran.threadsRace;

import java.util.Random;

public class Race {
	private int winner;
	private boolean isWinner;

	public Race() {
		this.isWinner = false;
	};

	public int getSleepingTime() {
		Random random = new Random();

		int randomMilliseconds = random.nextInt(4) + 2;
		return randomMilliseconds * 1000;
	}

	public void detectWinner(int racer) {
		if (!isWinner) {
			winner = racer;
			isWinner = true;
		}
	}

	public int getWinner() {
		return winner;
	}
}
