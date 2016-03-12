package ch.ffhs.ftoop.samuelblattner.threads1;

/**
 * Zaehler class implementing a simple counter incrementing the 
 * value of a Speicher instance one by one whenever a value has been
 * consumed by the printer instance.
 */
public class Zaehler extends Thread {

	/** A reference to the Speicher instance. */
	private Speicher speicher;
	/** Min and max for the iteration. */
	private int max, min;

	/**
	 * 
	 * @param s
	 *            Das Speicherobject, das die aktuelle Zahl haelt.
	 * @param minValue
	 *            Der Startwert für den Zaehler
	 * @param maxValue
	 *            Der Endwert für den Zaehler (einschliesslich)
	 * 
	 */
	Zaehler(final Speicher s, final int minValue, final int maxValue) {
		this.speicher = s;
		this.max = maxValue;
		this.min = minValue;
	}

	/**
	 * Diese Run Methode zählt den Wert in Speicher hoch - von min bis max
	 * (einschliesslich).
	 * 
	 */
	@Override
	public final void run() {
		int counter = this.min;

		while (counter <= this.max) {
			synchronized (speicher) {
				if (!speicher.isHatWert()) {
					speicher.setWert(counter++);
				}
				speicher.notifyAll();
			}
		}
	}
}
