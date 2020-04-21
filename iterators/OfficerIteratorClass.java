package iterators;

import users.Officer;

/**
 * @author Goncalo Virginia - 56773
 *
 * Officer iterator.
 */

public class OfficerIteratorClass extends UserIteratorClass implements OfficerIterator {
	
	/**
	 * Constructor.
	 * @param users User array to iterate.
	 * @param numUsers Number of users in the array.
	 */
	public OfficerIteratorClass(Officer[] users, int numUsers) {
		super(users, numUsers);
	}
	
	/**
	 * @return True if the is another user to be iterated.
	 */
	@Override
	public boolean hasNext() {
		while (nextUser < numUsers) {
			if (!(users[nextUser] instanceof Officer)) {
				nextUser++;
			}
			else {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * @return The next user to be iterated.
	 */
	@Override
	public Officer next() {
		return (Officer) users[nextUser++];
	}
	
}
