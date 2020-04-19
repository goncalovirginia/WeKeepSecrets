package users;

import iterators.ClassifiedDocumentIterator;
import iterators.DocumentIterator;

public interface Officer extends User {
	
	/**
	 * @return The number of times the user has granted access to other users.
	 */
	int getNumGrants();
	
	/**
	 * @return The number of times the user has revoked access to other users.
	 */
	int getNumRevokes();
	
	/**
	 * Checks if the specified user had their last grant to the document revoked.
	 * @param user The user.
	 * @param documentName The documents' name.
	 * @return True if the user had his last grant to the document revoked.
	 */
	boolean userHasGrantRevoked(Officer user, String documentName);
	
	/**
	 * Grants a specified user clearance to a certain document.
	 * @param user User to grant clearance to.
	 * @param documentName The documents' name.
	 */
	void grantClearance(Officer user, String documentName);
	
	/**
	 * Revokes a users grant to a certain document.
	 * @param user The user.
	 * @param documentName The documents' name.
	 */
	void revokeClearance(Officer user, String documentName);
	
	/**
	 * @return New classified document iterator.
	 */
	ClassifiedDocumentIterator newClassifiedDocumentIterator();
	
}
