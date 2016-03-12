package ch.ffhs.ftoop.samuelblattner.deadlocks;

import student.TestCase;

/**
 * 
 */
public class DeadlockTest extends TestCase {

	/** Defining a static int because check style told me to. :-( */
	private static final int MAX_ITERATIONS = 10;
	
	/**
	 * Tests if program runs without deadlock.
	 * @throws InterruptedException Yes it does
	 */
	public final void testDoStuff() throws InterruptedException {

		for (int i = 0; i < MAX_ITERATIONS; i++) {
			Deadlock d = new Deadlock();
			d.doStuff();
		}

	}

}
