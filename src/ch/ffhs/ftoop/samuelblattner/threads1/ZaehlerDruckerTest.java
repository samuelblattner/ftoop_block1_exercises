package ch.ffhs.ftoop.samuelblattner.threads1;

import student.TestCase;

/**
 * Test if the numbers are produced and printed in the right sequence.
 * 
 */
public class ZaehlerDruckerTest extends TestCase {

	/**
	 * Tests if numbers printed are in the right order.
	 * @throws InterruptedException The exception
	 */
	public final void testZaehlerDrucker() throws InterruptedException {
		ZaehlerDrucker.main(new String[] { "1", "20" });
		assertFuzzyEquals(
				"1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 ",
				systemOut().getHistory());
	}
}
