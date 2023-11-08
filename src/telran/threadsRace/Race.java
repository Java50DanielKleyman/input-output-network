package telran.threadsRace;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Race {
	private int distance;
	private int minSleep;
	private int maxSleep;
	private List<Map.Entry<Integer, Integer>> winnersList = new ArrayList<>();
	public Race(int distance, int minSleep, int maxSleep) {
		this.distance = distance;
		this.minSleep = minSleep;
		this.maxSleep = maxSleep;
	}
	public List<Map.Entry<Integer, Integer>> getWinnersList() {
		 Collections.sort(winnersList, (entry1, entry2) -> entry1.getValue().compareTo(entry2.getValue()));
		return winnersList;
	}
	public void setWinnersList(Map.Entry<Integer, Integer> entry) {
		winnersList.add(entry);
	}
	public int getDistance() {
		return distance;
	}
	public int getMinSleep() {
		return minSleep;
	}
	public int getMaxSleep() {
		return maxSleep;
	}
	
}