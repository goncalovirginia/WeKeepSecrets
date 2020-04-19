package iterators;

import users.User;

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
