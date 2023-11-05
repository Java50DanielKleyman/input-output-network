package telran.numbersPrinting;

public class Printer extends Thread {
	private long N_NUMBERS;
	private long N_PORTIONS;
//	boolean running = true;
	private int printerId;		
	Printer nextPrinter;

	public Printer(long N_NUMBERS, long N_PORTIONS, int printerId) {
		this.N_NUMBERS = N_NUMBERS;
		this.N_PORTIONS = N_PORTIONS;
		this.printerId = printerId;			
	}

	public void setNextPrinter(Printer nextPrinter) {
		this.nextPrinter = nextPrinter;
	}

//	public void finish() {
//		running = false;
//	}

	@Override
	public void run() {
		while (N_NUMBERS != 0) {
			try {
				sleep(2000);
			} catch (InterruptedException e) {
					for(int i = 0; i < N_PORTIONS; i++) {
						System.out.print(printerId);	
					}															
					N_NUMBERS -= N_PORTIONS;
					System.out.println(" Printer " + printerId + ". Numbers left: " + N_NUMBERS);
					nextPrinter.interrupt();
				}
			}
		}
	}

