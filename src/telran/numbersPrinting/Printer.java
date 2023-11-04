package telran.numbersPrinting;

public class Printer extends Thread {
	private long N_NUMBERS;
	private long N_PORTIONS;
	boolean running = true;
	private int printerId;
	private long countDown;
	int numberOfPrinters;
	Printer nextPrinter;

	public Printer(long N_NUMBERS, long N_PORTIONS, int printerId, int numberOfPrinters) {
		this.N_NUMBERS = N_NUMBERS;
		this.N_PORTIONS = N_PORTIONS;
		this.printerId = printerId;
		this.numberOfPrinters = numberOfPrinters;
		countDown = this.N_NUMBERS / this.N_PORTIONS;
	}

	public void setNextPrinter(Printer nextPrinter) {
		this.nextPrinter = nextPrinter;
	}

	public void finish() {
		running = false;
	}

	@Override
	public void run() {
		while (running) {
			try {
				sleep(2000);
			} catch (InterruptedException e) {
				while (running || countDown != 0) {
					for (int i = 0; i < N_PORTIONS; i++) {
						System.out.println(printerId);
					}
					if (countDown > N_PORTIONS - numberOfPrinters) {
						nextPrinter.start();
					}
					countDown--;
					nextPrinter.interrupt();
				}
			}
		}
	}
}
