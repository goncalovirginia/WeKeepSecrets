package documents;

import iterators.AccessIterator;
import users.Officer;
import users.User;

public interface ClassifiedDocument extends Document {
	
	/**
	 * @return Number of times the document has been granted.
	 */
	int getNumGrants();
	
	/**
	 * @return Number of times the document has been revoked.
	 */
	int getNumRevokes();
	
	/**
	 * @return True if the specified user has been granted access to the document.
	 */
	boolean userHasGrant(String userId);
	
	/**
	 * Checks if the specified user had their last grant to the document revoked.
	 * @param userId The users' ID.
	 * @return True if the user had his last grant to the document revoked.
	 */
	boolean userHasGrantRevoked(String userId);
	
	/**
	 * Revokes a users grant to a certain document.
	 * @param user The user.
	 */
	void revokeClearance(Officer user);
	
	/**
	 * Grants security clearance to a certain user.
	 * @param user The user that will receive clearance to the document.
	 */
	void grantClearance(Officer user);
	
	/**
	 * @return New grant and revoke iterator.
	 */
	AccessIterator newGrantIterator();
	
}
