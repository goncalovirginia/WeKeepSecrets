package iterators;

import users.User;

public class UserIteratorClass implements UserIterator {
	
	private User[] users;
	private int numUsers, nextUser;
	
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
