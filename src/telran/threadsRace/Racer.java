package telran.threadsRace;

public class Racer extends Thread {
	private Race race;
	private int distance;
	private int sleepingTime;
	private int number;
	

	public Racer(Race race, int distance, int sleepingTime, String name) {
		this.race = race;
		this.distance = distance;
		this.sleepingTime = sleepingTime;
		String[] nameParts = name.split("_");
		this.number = Integer.parseInt(nameParts[1]);
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

	}
}
