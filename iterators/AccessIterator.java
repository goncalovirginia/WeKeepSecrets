package iterators;

import Accesses.Access;

public interface AccessIterator {
	
	/**
	 * @return True if the is another access to be iterated.
	 */
	boolean hasNext();
	
	/**
	 * @return The next access to be iterated.
	 */
	Access next();
}
