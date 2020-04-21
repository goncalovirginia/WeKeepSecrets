package iterators;

import users.User;

/**
 * @author Goncalo Virginia - 56773
 *
 * Generic user iterator.
 */

public interface UserIterator {
	
	/**
	 * @return True if the is another user to be iterated.
	 */
	boolean hasNext();
	
	/**
	 * @return The next user to be iterated.
	 */
	User next();
}
