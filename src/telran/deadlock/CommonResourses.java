package telran.deadlock;

public class CommonResourses {
	static final Object mutex1 = new Object();
	static final Object mutex2 = new Object();
	static int counter1 = 0;
	static int counter2 = 0;

	public CommonResourses() {

	}

	public void f1() {

		synchronized (mutex1) {
			synchronized (mutex2) {
				counter1++;
				counter2++;
			}
		}
	}

	public void f2() {

		synchronized (mutex2) {
			synchronized (mutex1) {
				counter1++;
				counter2++;
			}
		}
	}

}