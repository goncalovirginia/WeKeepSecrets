package iterators;

import Accesses.Access;

/**
 * @author Goncalo Virginia - 56773
 *
 * Reverse access iterator (starts from the end of the array).
 */

public class ReverseAccessIteratorClass implements AccessIterator {
	
	/* Constants */
	private static final int MAX_ITERATIONS = 10;
	
	/* Variables */
	private Access[] accesses;
	private int numAccesses, nextAccess, iterations;
	
	/**
	 * Constructor.
	 * @param accesses Access array to iterate.
	 * @param numAccesses Number of accesses in the array.
	 */
	public ReverseAccessIteratorClass(Access[] accesses, int numAccesses) {
		this.accesses = accesses;
		this.numAccesses = numAccesses;
		nextAccess = numAccesses-1;
		iterations = 0;
	}
	
	/**
	 * @return True if the is another user to be iterated.
	 */
	@Override
	public boolean hasNext() {
		return nextAccess >= 0 && iterations < MAX_ITERATIONS;
	}
	
	/**
	 * @return The next user to be iterated.
	 */
	@Override
	public Access next() {
		iterations++;
		return accesses[nextAccess--];
	}
	
}
