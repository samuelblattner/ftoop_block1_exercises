package ch.ffhs.ftoop.samuelblattner.optimusprime;
import java.util.ArrayList;
import java.util.concurrent.Callable;

/**
 * PrimeSpotter Callable finding prime numbers in a given interval.
 * @author samuel
 *
 */
public class PrimeSpotter implements Callable<ArrayList<Integer>> {
	/** Interval [from, to]. */
	private int from, to;
	
	/**
	 * Constructor of PrimeSpotter, initializing interval bounds.
	 * @param fromNumber Start of interval (inclusive)
	 * @param toNumber End of interval (inclusive)
	 */
	public PrimeSpotter(final int fromNumber, final int toNumber) {
		this.from = fromNumber;
		this.to = toNumber;
	}
	
	/**
	 * Calculates if a given number n is a prime number.
	 * @param n Number to be examined
	 * @return boolean
	 */
	private boolean isPrime(final int n) {
		int divisor = 1;
		int divisions = 0;
		while (divisor <= n / 2 && divisions <= 1) {
			if (n % divisor == 0) {
				divisions++;
			}
			divisor++;
		}
		return divisions <= 1 && n > 1;
	}
	
	/**
	 * Runnable method run.
	 * @return List of results
	 */
	public final ArrayList<Integer> call() {
		ArrayList<Integer> foundPrimeNumbers = new ArrayList<Integer>();

		for (int n = from; n < to; n++) {
			if (isPrime(n)) {
				foundPrimeNumbers.add(n);
			}
		}
		return foundPrimeNumbers;
	}
}
