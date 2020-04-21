package iterators;

import Accesses.Access;

/**
 * @author Goncalo Virginia - 56773
 *
 * Generic access iterator.
 */

public class AccessIteratorClass implements AccessIterator {
	
	/* Variables */
	private Access[] accesses;
	private int numAccesses, nextAccess;
	
	/**
	 * Constructor.
	 * @param accesses Access array to iterate.
	 * @param numAccesses Number of accesses in the array-
	 */
	public AccessIteratorClass(Access[] accesses, int numAccesses) {
		this.accesses = accesses;
		this.numAccesses = numAccesses;
		nextAccess = 0;
	}
	
	/**
	 * @return True if the is another user to be iterated.
	 */
	@Override
	public boolean hasNext() {
		return nextAccess < numAccesses;
	}
	
	/**
	 * @return The next user to be iterated.
	 */
	@Override
	public Access next() {
		return accesses[nextAccess++];
	}
}
