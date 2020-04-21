package iterators;

import users.Officer;

/**
 * @author Goncalo Virginia - 56773
 *
 * Officer iterator.
 */

public interface OfficerIterator extends UserIterator {
	
	/**
	 * @return The next user to be iterated.
	 */
	Officer next();
	
}
