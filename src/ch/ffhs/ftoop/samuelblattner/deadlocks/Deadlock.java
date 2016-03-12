package ch.ffhs.ftoop.samuelblattner.deadlocks;

/**
 * Aufgabe: Dieses Programm demonstriert einen Deadlock. Lassen Sie dieses
 * Programm mehrfach laufen und schauen Sie, was passiert.
 * 
 * a) Erklären Sie das Verhalten des Programms. b) Korrigieren Sie das Programm,
 * so dass es sich korrekt verhält. Verändern Sie dabei nicht die Klasse Friend.
 * 
 * a: Beide Friend's sperren sich jeweils für einen Exklusivzugriff 
 * (synchronized, das das ganze Objekt sperrt und wollen aus diesem 
 * Sperrzustand auf das andere gesperrte Friend-Objekt zugreifen (bowback). 
 * Leider implementiert die Friend-Klasse keine Methode "derKluegereGibtNach"
 * und verharren so in einer auswegslosen Situation. Die Armen ...
 * b: Wir sperren jeweils einen Friend direkt in der run-Methode und 
 * benachrichtigen den anderen Thread, sobald der Friend wieder freigegeben 
 * ist. Damit der andere Thread nicht ewig wartet, darf er nach 10ms bereits 
 * versuchen.
 * @author bele
 */
public class Deadlock {

	/** Wait before second thread tries to access friend. */
	private static final short WAIT_BEFORE_TRY = 10;

	/**
	 * Does what it says: Stuff.
	 * @throws InterruptedException When thread interrupted
	 * 
	 */
	final void doStuff() throws InterruptedException {
		final Friend alphonse = new Friend("Alphonse");
		final Friend gaston = new Friend("Gaston");
		
		Thread gastonThread = new Thread(new Runnable() {
			public void run() {
				synchronized (gaston) {
					alphonse.bow(gaston);
					gaston.notifyAll();
				}
			}
		}, "Gaston");
		gastonThread.start();

		Thread alphonseThread = new Thread(new Runnable() {
			public void run() {
				synchronized (gaston) {
					try {
						gaston.wait(WAIT_BEFORE_TRY);
						gaston.bow(alphonse);
						gaston.notifyAll();
					} catch (InterruptedException e) {
						Thread.currentThread().interrupt();
					}
				}
			}
		}, "Alphonse");
		alphonseThread.start();

		alphonseThread.join();
		gastonThread.join();
		
	}

	/**
	 * main entry point.
	 * @param args Args given for execution.
	 * @throws InterruptedException Throws this.
	 */
	public static void main(final String[] args) throws InterruptedException {
		Deadlock d = new Deadlock();
		d.doStuff();
	}
}

/**
 * The Friend class. Friends can bow. And they can bow back.
 * @author samuel
 *
 */
class Friend {
	/** This guy's name. */
	private final String name;

	/**
	 * Constructor setting the name.
	 * @param newName The name
	 */
	Friend(final String newName) {
		this.name = newName;
	}

	/**
	 * Return the name.
	 * @return The name maybe?
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Bow!
	 * @param bower The guy bowing to me.
	 */
	public synchronized void bow(final Friend bower) {
		System.out.format("%s: %s" + "  has bowed to me!%n", this.name,
				bower.getName());
		bower.bowBack(this);
	}

	/**
	 * Bow back!
	 * @param bower The guy bowing to me
	 */
	public synchronized void bowBack(final Friend bower) {
		System.out.format("%s: %s" + " has bowed back to me!%n", this.name,
				bower.getName());
	}
}

