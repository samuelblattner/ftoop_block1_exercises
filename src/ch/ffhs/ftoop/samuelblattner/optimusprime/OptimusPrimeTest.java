package ch.ffhs.ftoop.samuelblattner.optimusprime;

import static org.junit.Assert.assertArrayEquals;

import java.util.ArrayList;
import student.TestCase;

/**
 * The Test Class.
 * @author samuel
 *
 */
public class OptimusPrimeTest extends TestCase {

	/**
	 * Tests if the correct prime numbers are calculated.
	 */
	public final void testCorrectPrimeNumbersCalculated() {
		int[] expectedPrimes = new int[]{2, 3, 5, 7, 11, 13, 17, 19, 23};

		OptimusPrime optimusPrime = new OptimusPrime(4);
		ArrayList<Integer> results = optimusPrime.getPrimeNumbers(0, 24);
		int[] resultPrimes = new int[results.size()];
		for (int r = 0; r < results.size(); r++) {
			resultPrimes[r] = results.get(r);
		}

		assertArrayEquals(expectedPrimes, resultPrimes);		
		
	}

	/**
	 * Tests if the correct prime numbers are calculated within
	 * a certain interval where start != 0.
	 */
	public final void testCorrectPrimeNumbersCalculatedInFrame() {
		int[] expectedPrimes = new int[]{23, 29, 31, 37};

		OptimusPrime optimusPrime = new OptimusPrime(4);
		ArrayList<Integer> results = optimusPrime.getPrimeNumbers(20, 40);
		int[] resultPrimes = new int[results.size()];
		for (int r = 0; r < results.size(); r++) {
			resultPrimes[r] = results.get(r);
		}

		assertArrayEquals(expectedPrimes, resultPrimes);		
	}

}
