package ch.ffhs.ftoop.samuelblattner.threads1;

/**
 * Speicher interface describing the basic layout for Speicher instances.
 */
public interface SpeicherIf {

	/**
	 * Gibt den aktuellen Wert zurück.
	 * 
	 * @return int Der aktuelle Wert
	 * @throws InterruptedException The exception
	 */
	int getWert() throws InterruptedException;

	/**
	 * Setzt einen neuen aktuellen Wert.
	 * 
	 * @param wert Der neue Wert, der gesetzt werden soll.
	 * @throws InterruptedException The exception
	 */
	void setWert(int wert) throws InterruptedException;

	/**
	 * Gibt true zurück, wenn es einen neuen, noch nicht konsumierten Wert im
	 * Objekt hat.
	 * 
	 * @return boolean True, falls nichtkonsumierter Wert vorhanden.
	 */
	boolean isHatWert();

}
