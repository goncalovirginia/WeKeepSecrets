package iterators;

import Accesses.Access;

public class ReverseAccessIteratorClass implements AccessIterator {
	
	/* Constants */
	private static final int MAX_ITERATIONS = 10;
	
	/* Variables */
	private Access[] accesses;
	private int numAccesses, nextAccess, iterations;
	
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
