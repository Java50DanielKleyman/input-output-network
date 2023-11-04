package telran.numbersPrinting;

public class ScheduledNumbersPrinitingAppl {
	private static long N_NUMBERS = 100;
	private static long N_PORTIONS = 10;
	private static int numberOfPrinters = 4;

	public static void main(String[] args) {
		Printer[] printers = createPrinters();
		setNextPrinters(printers);
		printers[0].start();
	//	printers[0].interrupt();
	//	printers[0].finish();

	}

	private static void setNextPrinters(Printer[] printers) {
		for (int i = 0; i < printers.length; i++) {
			if (i == printers.length - 1) {
				printers[i].setNextPrinter(printers[0]);
			} else {
				printers[i].setNextPrinter(printers[i + 1]);
			}

		}

	}

	private static Printer[] createPrinters() {
		Printer[] printers = new Printer[numberOfThreads];
		for (int i = 0; i < numberOfThreads; i++) {
			printers[i] = new Printer(N_NUMBERS, N_PORTIONS, i);
		}
		return printers;
	}

}
