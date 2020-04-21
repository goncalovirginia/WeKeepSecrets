package iterators;

import users.User;

/**
 * @author Goncalo Virginia - 56773
 *
 * Generic user iterator.
 */

public class UserIteratorClass implements UserIterator {
	
	/* Variables */
	protected User[] users;
	protected int numUsers, nextUser;
	
	/**
	 * Constructor.
	 * @param users User array to iterate.
	 * @param numUsers  Number of users to iterate.
	 */
	public UserIteratorClass(User[] users, int numUsers) {
		this.users = users;
		this.numUsers = numUsers;
		nextUser = 0;
	}
	
	/**
	 * @return True if the is another user to be iterated.
	 */
	@Override
	public boolean hasNext() {
		return nextUser < numUsers;
	}
	
	/**
	 * @return The next user to be iterated.
	 */
	@Override
	public User next() {
		return users[nextUser++];
	}
}
