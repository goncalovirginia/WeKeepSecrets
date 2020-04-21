package Accesses;

import iterators.AccessIterator;
import iterators.AccessIteratorClass;
import iterators.ReverseAccessIteratorClass;
import users.User;

/**
 * @author Goncalo Virginia - 56773
 *
 * Manages a collection of accesses.
 */

public class AccessCollectionClass implements AccessCollection {
	
	/* Constants */
	private static final int INITIAL_SIZE = 10;
	
	/* Variables */
	private Access[] accesses;
	private int numAccesses, numGrants;
	
	/* Constructor */
	public AccessCollectionClass() {
		accesses = new Access[INITIAL_SIZE];
		numAccesses = 0;
		numGrants = 0;
	}
	
	/**
	 * Adds a new access to the collection.
	 * @param user The user that did the action.
	 * @param accessType The type of action.
	 */
	@Override
	public void newAccess(User user, AccessType accessType) {
		if (isFull()) {
			resize();
		}
		
		accesses[numAccesses++] = new AccessClass(user, accessType);
		
		if (accessType == AccessType.GRANT) {
			numGrants++;
		}
	}
	
	/**
	 * @return The number of accesses in the collection.
	 */
	@Override
	public int getNumAccesses() {
		return numAccesses;
	}
	
	/**
	 * @return The number of grants issued.
	 */
	@Override
	public int getNumGrants() {
		return numGrants;
	}
	
	/**
	 * @return The number of revokes issued.
	 */
	@Override
	public int getNumRevokes() {
		return numAccesses - numGrants;
	}
	
	/**
	 * Checks if a certain user has an active grant.
	 * @param userId The users' ID.
	 * @return True if the user has an active grant.
	 */
	@Override
	public boolean userHasGrant(String userId) {
		return lastAccessType(userId, AccessType.GRANT);
	}
	
	/**
	 * Checks if the specified user had their last grant to the document revoked.
	 * @param userId The users' ID.
	 * @return True if the user had his last grant to the document revoked.
	 */
	@Override
	public boolean userHasGrantRevoked(String userId) {
		return lastAccessType(userId, AccessType.REVOKED);
	}
	
	/**
	 * @return New access iterator.
	 */
	@Override
	public AccessIterator newAccessIterator() {
		return new AccessIteratorClass(accesses, numAccesses);
	}
	
	@Override
	public AccessIterator newReverseAccessIterator() {
		return new ReverseAccessIteratorClass(accesses, numAccesses);
	}
	
	/* Private methods */
	
	/**
	 * Checks if the users' last access was of a certain type.
	 * @param userId The users' ID.
	 * @param accessType The type of access.
	 * @return True if the users' last access was of a certain type.
	 */
	private boolean lastAccessType(String userId, AccessType accessType) {
		for (int i = numAccesses-1; i >= 0; i--) {
			if (accesses[i].getUser().getId().equals(userId)) {
				return accesses[i].getType().equals(accessType.getType());
			}
		}
		return false;
	}
	
	/**
	 * @return True if the array is full.
	 */
	private boolean isFull() {
		return numAccesses == accesses.length;
	}
	
	/**
	 * Resizes the array to twice the current length.
	 */
	private void resize() {
		Access[] temp = new Access[accesses.length * 2];
		
		for (int i = 0; i < accesses.length; i++) {
			temp[i] = accesses[i];
		}
		
		accesses = temp;
	}
}
