package documents;

import Accesses.AccessCollection;
import Accesses.AccessCollectionClass;
import Accesses.AccessType;
import iterators.AccessIterator;
import users.Officer;
import users.User;

/**
 * @author Goncalo Virginia - 56773
 *
 * A type of document with high security clearance, which concedes it various other properties.
 */

public class ClassifiedDocumentClass extends AbstractDocument implements ClassifiedDocument {
	
	/* Variables */
	private AccessCollection grants;
	
	/* Constructor */
	public ClassifiedDocumentClass(String documentName, User userIdOwner, String documentLevel, String documentDescription) {
		super(documentName, userIdOwner, documentLevel, documentDescription);
		grants = new AccessCollectionClass();
	}
	
	/**
	 * @return Number of times the document has been granted.
	 */
	@Override
	public int getNumGrants() {
		return grants.getNumGrants();
	}
	
	/**
	 * @return Number of times the document has been revoked.
	 */
	@Override
	public int getNumRevokes() {
		return grants.getNumRevokes();
	}
	
	/**
	 * Edits the documents' description and adds a new user access.
	 * @param user The user editing the document.
	 * @param description The new documents' description.
	 */
	@Override
	public void writeDocument(User user, String description) {
		accesses.newAccess(user, AccessType.WRITE);
		this.description = description;
	}
	
	/**
	 * @return True if the specified user has been granted access to the document.
	 */
	@Override
	public boolean userHasGrant(String userId) {
		return grants.userHasGrant(userId);
	}
	
	/**
	 * Checks if the specified user had their last grant to the document revoked.
	 * @param userId The users' ID.
	 * @return True if the user had his last grant to the document revoked.
	 */
	@Override
	public boolean userHasGrantRevoked(String userId) {
		return grants.userHasGrantRevoked(userId);
	}
	
	/**
	 * Revokes a users grant to a certain document.
	 * @param user The user.
	 */
	@Override
	public void revokeClearance(Officer user) {
		grants.newAccess(user, AccessType.REVOKED);
	}
	
	/**
	 * Grants security clearance to a certain user.
	 * @param user The user that will receive clearance to the document.
	 */
	@Override
	public void grantClearance(Officer user) {
		grants.newAccess(user, AccessType.GRANT);
	}
	
	/**
	 * @return New grant and revoke iterator.
	 */
	@Override
	public AccessIterator newGrantIterator() {
		return grants.newAccessIterator();
	}
}
