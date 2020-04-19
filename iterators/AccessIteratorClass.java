package iterators;

import Accesses.Access;

public class AccessIteratorClass implements AccessIterator {
	
	private Access[] accesses;
	private int numAccesses, nextAccess;
	
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
