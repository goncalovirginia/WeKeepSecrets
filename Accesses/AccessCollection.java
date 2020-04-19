package Accesses;

import iterators.AccessIterator;
import users.User;

public interface AccessCollection {
	
	/**
	 * Adds a new access to the collection.
	 * @param user The user that did the action.
	 * @param accessType The type of action.
	 */
	void newAccess(User user, AccessType accessType);
	
	/**
	 * @return The number of accesses in the collection.
	 */
	int getNumAccesses();
	
	/**
	 * @return The number of grants issued.
	 */
	int getNumGrants();
	
	/**
	 * @return The number of revokes issued.
	 */
	int getNumRevokes();
	
	/**
	 * Checks if a certain user has an active grant.
	 * @param userId The users' ID.
	 * @return True if the user has an active grant.
	 */
	boolean userHasGrant(String userId);
	
	/**
	 * Checks if the specified user had their last grant to the document revoked.
	 * @param userId The users' ID.
	 * @return True if the user had his last grant to the document revoked.
	 */
	boolean userHasGrantRevoked(String userId);
	
	/**
	 * @return New access iterator.
	 */
	AccessIterator newAccessIterator();
	
	/**
	 * @return New reverse access iterator.
	 */
	AccessIterator newReverseAccessIterator();
}
