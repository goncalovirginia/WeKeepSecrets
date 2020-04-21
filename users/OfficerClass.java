package users;

import iterators.ClassifiedDocumentIterator;

/**
 * @author Goncalo Virginia - 56773
 *
 * Type of user with high security clearance, which concedes it various other properties.
 */

public class OfficerClass extends AbstractUser implements Officer {
	
	/* Variables */
	private int numGrants, numRevokes;
	
	/* Constructor */
	public OfficerClass(String type, String id, String level) {
		super(type, id, level);
	}
	
	/**
	 * @return The number of times the user has granted access to other users.
	 */
	@Override
	public int getNumGrants() {
		return numGrants;
	}
	
	/**
	 * @return The number of times the user has revoked access to other users.
	 */
	@Override
	public int getNumRevokes() {
		return numRevokes;
	}
	
	/**
	 * Edits the specified documents' description.
	 * @param user The user editing the document.
	 * @param documentName The documents' name.
	 * @param description The documents' description.
	 */
	@Override
	public void writeDocument(User user, String documentName, String description) {
		documents.writeDocument(user, documentName, description);
	}
	
	/**
	 * Checks if the specified user has clearance to access the document.
	 * @param user The user who wants to access the document.
	 * @param documentName The documents' name.
	 * @return True if the user has clearance.
	 */
	@Override
	public boolean userHasClearance(Officer user, String documentName) {
		return documents.userHasClearance(user, documentName);
	}
	
	/**
	 * Checks if the specified user had their last grant to the document revoked.
	 * @param user The user.
	 * @param documentName The documents' name.
	 * @return True if the user had his last grant to the document revoked.
	 */
	@Override
	public boolean userHasGrantRevoked(Officer user, String documentName) {
		return documents.userHasGrantRevoked(user, documentName);
	}
	
	/**
	 * Grants a specified user clearance to a certain document.
	 * @param user User to grant clearance to.
	 * @param documentName The documents' name.
	 */
	@Override
	public void grantClearance(Officer user, String documentName) {
		documents.grantClearance(user, documentName);
		numGrants++;
	}
	
	/**
	 * Revokes a users grant to a certain document.
	 * @param user The user.
	 * @param documentName The documents' name.
	 */
	@Override
	public void revokeClearance(Officer user, String documentName) {
		documents.revokeClearance(user, documentName);
		numRevokes++;
	}
	
	/**
	 * @return New classified document iterator.
	 */
	@Override
	public ClassifiedDocumentIterator newClassifiedDocumentIterator() {
		return documents.newClassifiedDocumentIterator();
	}
	
}
