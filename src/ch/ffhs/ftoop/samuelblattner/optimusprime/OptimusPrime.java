package ch.ffhs.ftoop.samuelblattner.optimusprime;

import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Main class to calculate prime numbers in a given interval. Provides
 * a thread pool using the Executor Framework in ordert to allow for
 * parallel calculation. The inverval is split into sub-intervals where
 * each is assigned to a free thread.
 *
 * @author samuel blattner
 *
 */
public class OptimusPrime {
	
	/** Ask check style how this should make any sense. */
	private static final int NUM_EXPECTED_ARGUMENTS = 3;
	/** Thread pool providing threads for prime number calculation. */
	private ExecutorService threadPool;
	/** Number of threads used for calculation. */
	private int numberOfThreads;
	
	/**
	 * Constructor of OptimsPrime. Initializes the thread pool.
	 * @param newNumberOfThreads Number of threads that should be provided 
	 * to calculate the prime numbers.
	 */
	public OptimusPrime(final int newNumberOfThreads) {
		this.numberOfThreads = newNumberOfThreads;
		threadPool = Executors.newFixedThreadPool(newNumberOfThreads);
	}
	
	/**
	 * Main task submission routine. Breaks down a given interval [from, to] 
	 * into smaller sub-intervals and assigns them each to a 
	 * PrimeSpotter-Object. All sub-intervals are then calculated using the 
	 * threads provided by the pool.
	 * 
	 *  @param from Interval start
	 *  @param to Interval end
	 *  @return A list of Future-Objects, each holding an ArrayList, 
	 *  containing the prime numbers
	 */
	private List<Future<ArrayList<Integer>>> calculatePrimeNumbers(
			final int from, final int to) {
		// Size of the sub-intervals
		int intervalSize = (to - from) / numberOfThreads;
		// Start number in the current sub-interval
		int curFrom = from;
		// End number in the current sub-interval
		int curTo = curFrom + intervalSize;
		// Array of PrimeSpotter-Objects (Callables)
		ArrayList<PrimeSpotter> subTasks = new ArrayList<PrimeSpotter>();
		
		/** Loop over sub-intervals and create sub-tasks until end reached */
		while (curFrom < to) {
			subTasks.add(new PrimeSpotter(curFrom, curTo));
			curFrom += intervalSize; curTo += intervalSize;
			if (curTo > to) { 
				curTo = to; 
				}
		}
		try {
			List<Future<ArrayList<Integer>>> results = 
					threadPool.invokeAll(subTasks);
			threadPool.shutdown();
			return results;
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		return null;
	}
	
	/**
	 * Invokes the calculatePrimeNumbers method and unifies its results in one 
	 * single list.
	 * @param from Start of interva
	 * @param to End of interval
	 * @return Unified ArrayList of prime numbers
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	public final ArrayList<Integer> getPrimeNumbers(
			final int from, final int to) {
		// A list of future objects for the results
		List<Future<ArrayList<Integer>>> primeNumbers = 
				calculatePrimeNumbers(from, to);
		// The unified list, all results of all lists are to be collected in.
		ArrayList<Integer> result = new ArrayList<Integer>();
		
		for (Future<ArrayList<Integer>> primeSet: primeNumbers) {
			try {
				result.addAll(primeSet.get());
			} catch (final InterruptedException e) {
				// Not going to occur since .get() is blocking until finished.
			} catch (final ExecutionException e) {
				// Not going to occur
			}
		}
		return result;
	}
	
	/**
	 * main entry point.
	 * @param args The args
	 */
	public static void main(final String[] args) {
		int from = 0, to = 0, numThreads = 1;
		
		if (args.length != NUM_EXPECTED_ARGUMENTS) {
			System.err.println("Usage main (int from, int to, int n_threads)");
			System.exit(1);
		}
		try {
			from = Integer.parseInt(args[0]);
			to = Integer.parseInt(args[1]);
			numThreads = Integer.parseInt(args[2]);
		} catch (Exception e) {
			System.err.println("Usage main (int from, int to, int n_threads)");
			System.exit(1);
		}
		
		// OptimusPrime instance
		OptimusPrime optimusPrime = new OptimusPrime(numThreads);
		// Measure start time
		long startTime = System.currentTimeMillis();
		// Calculate prime numbers
		ArrayList<Integer> primeNums = optimusPrime.getPrimeNumbers(from, to);
		// Measure end time
		long endTime = System.currentTimeMillis();
		
		System.out.format(
				"Calculation of %d prime nums with %d threads took %d ms.\n\n", 
						primeNums.size(), numThreads, (endTime - startTime));
		System.out.println("Result:");
		System.out.println(primeNums);
	}
}
