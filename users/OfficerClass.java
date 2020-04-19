package users;

import iterators.ClassifiedDocumentIterator;
import iterators.ClassifiedDocumentIteratorClass;
import iterators.DocumentIterator;

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
