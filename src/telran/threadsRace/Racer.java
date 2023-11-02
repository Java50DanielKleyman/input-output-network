package telran.threadsRace;

public class Racer extends Thread {
	private Race race;
	private int distance;
	private int sleepingTime;
	private String name;

	public Racer(Race race, int distance, int sleepingTime, String name) {
		this.race = race;
		this.distance = distance;
		this.sleepingTime = sleepingTime;
		this.name = name;
	}

	@Override
	public void run() {
		for (int i = 0; i <= distance; i++) {
			try {
				sleep(sleepingTime);
			} catch (InterruptedException e) {				
				e.printStackTrace();
			}
			System.out.println(name);
		}
	}
}
