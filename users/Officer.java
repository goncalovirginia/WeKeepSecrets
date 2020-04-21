package users;

import iterators.ClassifiedDocumentIterator;

/**
 * @author Goncalo Virginia - 56773
 *
 * Type of user with high security clearance, which concedes it various other properties.
 */

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
	 * Edits the specified documents' description.
	 * @param user The user editing the document.
	 * @param documentName The documents' name.
	 * @param description The documents' description.
	 */
	void writeDocument(User user, String documentName, String description);
	
	/**
	 * Checks if the specified user has clearance to access the document.
	 * @param user The user who wants to access the document.
	 * @param documentName The documents' name.
	 * @return True if the user has clearance.
	 */
	boolean userHasClearance(Officer user, String documentName);
	
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
