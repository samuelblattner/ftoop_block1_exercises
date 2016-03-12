package ch.ffhs.ftoop.samuelblattner.threads1;

/**
 * Drucker class extending the Thread class. This class
 * implements a simple task printing values from the 
 * Speicher instance whenever available. 
 */
public class Drucker extends Thread {
	
	/** A speicher instance providing values to print. */
	private Speicher speicher;
	/** Max amount of time in ms the thread should wait 
	 * on a value from speicher before checking anyway.
	 */
	private static final int MAX_WAIT_TIME = 500;
	
	/**
	 * @param s The Custructor of this class expecting a 
	 * reference to a Speicher instance
	 */
	Drucker(final Speicher s) {
		this.speicher = s;
	}

	/**
	 * Holt einen Wert vom Zaehler und gibt ihn aus, gefolgt von einem einzelnen
	 * Leerzeichen.
	 */
	@Override
	public final void run() {
		while (true) {
			try {
				synchronized (speicher) {
					speicher.wait(MAX_WAIT_TIME);
					if (speicher.isHatWert()) {
						System.out.print(speicher.getWert() + " ");
					}
					speicher.notifyAll();
				}
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
	}
}
