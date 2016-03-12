package ch.ffhs.ftoop.samuelblattner.threads1;

/**
 * Speicher class holding values fed by a Zaehler instance.
 */
public class Speicher implements SpeicherIf {

	/** The value wert. */
	private int wert;
	/** Indicates if the current value has been consumed by the printer yet. */
	private boolean hatWert = false;

	/**
	 * Constructor initializing value with 0.
	 */
	public Speicher() {
		wert = 0;
	}

	/**
	 * Method to check if there is an unconsumed value.
	 * @return boolean 
	 */
    public final boolean isHatWert() {
        return this.hatWert;
    }

    /**
     * Setter for value.
     * @param newWert The value that should be stored 
     */
	public final void setWert(final int newWert) {
        wert = newWert;
        hatWert = true;
	}

	/**
	 * Getter for value.
	 * @return The current value (int) 
	 */	
    public final int getWert() {
        hatWert = false;
        return wert;
    }
}
