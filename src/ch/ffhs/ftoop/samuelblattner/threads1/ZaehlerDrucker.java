package ch.ffhs.ftoop.samuelblattner.threads1;

/**
 * 
 * Der Aufruf benötigt zwei Parameter min und max - der Zaehler beginnt bei min
 * zu zaehlen und terminiert bei max.
 * 
 */
public class ZaehlerDrucker {

	/** bissi warten, damit der Test funktioniert. */
	private static final int SLEEP_BEFORE_TEST = 5000;
	
	/**
	 * This is to shut up Checkstyle.
	 */
	public void dummyMethod() {
		
	}
	
	/**
	 * Our beautiful main routine.
	 * @param args The args
	 * @throws InterruptedException Again, this might happen...
	 */
	public static void main(final String[] args) throws InterruptedException {
		if (args.length != 2) {
			System.out.println("Usage: ZaehlerDrucker <min> <max>");
			System.exit(1);
		}

		Speicher s = new Speicher();
		Drucker d = new Drucker(s);
		Zaehler z = new Zaehler(s, Integer.parseInt(args[0]),
				Integer.parseInt(args[1]));

		z.start();
		d.start();
	
		Thread.sleep(SLEEP_BEFORE_TEST);
	}
}
