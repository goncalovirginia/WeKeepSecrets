package iterators;

import Accesses.Access;

/**
 * @author Goncalo Virginia - 56773
 *
 * Generic access iterator.
 */

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
