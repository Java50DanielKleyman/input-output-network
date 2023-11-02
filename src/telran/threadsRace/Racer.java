package telran.threadsRace;

public class Racer extends Thread {
	private Race race;
	private int distance;
	private int sleepingTime;
	private int number;
	private static int racerCounter = 0;
	private String name;

	public Racer(Race race, int distance, int sleepingTime, String name) {
		this.race = race;
		this.distance = distance;
		this.sleepingTime = sleepingTime;
		this.name = name;
		this.number = ++racerCounter;
	}

	@Override
	public void run() {
		for (int i = 0; i <= distance; i++) {
			try {
				sleep(sleepingTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(number);
		}
race.detectWinner(getName());
	}

	public int getNumber() {
		return number;
	}
}
