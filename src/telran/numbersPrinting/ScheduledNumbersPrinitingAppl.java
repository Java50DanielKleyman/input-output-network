package telran.numbersPrinting;

public class ScheduledNumbersPrinitingAppl {
	private static long N_NUMBERS = 100;
	private static long N_PORTIONS = 10;
	private static int N_PRINTERS = 4;

	public static void main(String[] args) {
		Printer[] printers = createPrinters();
		for (int i = 0; i < printers.length; i++) {
			 printers[i].setNextPrinter(printers[(i + 1) % N_PRINTERS]);
			}
		for (Printer printer : printers) {
            printer.start();
        }
		printers[0].interrupt();
	//	printers[0].finish();

	}	

	private static Printer[] createPrinters() {
		Printer[] printers = new Printer[N_PRINTERS];
		for (int i = 0; i < N_PRINTERS; i++) {
			printers[i] = new Printer(N_NUMBERS, N_PORTIONS, i+1);
		}
		return printers;
	}

}
