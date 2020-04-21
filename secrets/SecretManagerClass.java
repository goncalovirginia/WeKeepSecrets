package secrets;

import iterators.*;
import users.*;

/**
 * @author Goncalo Virginia - 56773.
 *
 * Serves as a middleman to the Main and UserCollectionClass classes.
 * Verifies certain preconditions for the Main class in order to execute methods in the UserCollectionClass.
 */

public class SecretManagerClass implements SecretManager {
	
	/* Variables */
	private UserCollection users;
	
	/* Constructor */
	public SecretManagerClass() {
		users = new UserCollectionClass();
	}
	
	/**
	 * Registers a new user with the following characteristics:
	 * @param type The users' type.
	 * @param userId The users' ID.
	 * @param level The users' clearance level.
	 * @pre type == "clerk" || type == "officer" && userId != null && level == "official" || level == "confidential" ||
	 * level == "secret" || level == "topsecret".
	 */
	@Override
	public void registerUser(String type, String userId, String level) {
		users.registerUser(type, userId, level);
	}
	
	/**
	 * Checks if a user with the specified ID exists.
	 * @param userId The users' ID.
	 * @return True if a user with the specified ID exists.
	 * @pre userId != null.
	 */
	@Override
	public boolean userExists(String userId) {
		return users.userExists(userId);
	}
	
	/**
	 * Creates a new document which is managed by a user with sufficient clearance.
	 * @param documentName The documents' name.
	 * @param userIdOwner The users' ID.
	 * @param documentLevel The documents' clearance level.
	 * @param documentDescription The documents' description.
	 * @pre documentName != null && userIdOwner != null && documentLevel == "official" || documentLevel == "confidential" ||
	 * 	 * documentLevel == "secret" || documentLevel == "topsecret" && documentDescription != null.
	 */
	@Override
	public void uploadDocument(String documentName, String userIdOwner, String documentLevel, String documentDescription) {
		users.uploadDocument(documentName, userIdOwner, documentLevel, documentDescription);
	}
	
	/**
	 * Checks if the specified user is already managing a document with the following name.
	 * @param userId The users' ID.
	 * @param documentName The documents' name.
	 * @return True if the user is already managing a document with the same name.
	 * @pre userId != null && documentName != null.
	 */
	@Override
	public boolean userHasDocument(String userId, String documentName) {
		return users.userHasDocument(userId, documentName);
	}
	
	/**
	 * Checks if a user has sufficient clearance to interact with a document of that level.
	 * @param userId The users' ID.
	 * @param documentLevel The documents' clearance level.
	 * @return True if the user has sufficient clearance to upload the document.
	 * @pre userId != null && documentLevel == "official" || documentLevel == "confidential" ||
	 * documentLevel == "secret" || documentLevel == "topsecret".
	 */
	@Override
	public boolean userHasLevel(String userId, String documentLevel) {
		return users.userHasLevel(userId, documentLevel);
	}
	
	/**
	 * Checks if the specified document has a classified clearance level.
	 * @param userIdOwner The document owners' ID.
	 * @param documentName The documents' name.
	 * @return True if the document has a classified clearance level.
	 * @pre userIdOwner != null && documentName != null.
	 */
	@Override
	public boolean documentIsClassified(String userIdOwner, String documentName) {
		return users.documentIsClassified(userIdOwner, documentName);
	}
	
	/**
	 * Checks if the specified user has sufficient clearance to access a certain document.
	 * @param userIdAccess ID of the user trying to access the document.
	 * @param userIdOwner The document owners' ID.
	 * @param documentName The documents' name.
	 * @return True if the user has sufficient clearance.
	 * @pre userIdAccess != null && userIdOwner != null && documentName != null.
	 */
	@Override
	public boolean userHasClearance(String userIdAccess, String userIdOwner, String documentName) {
		return users.userHasClearance(userIdAccess, userIdOwner, documentName);
	}
	
	/**
	 * Enables a user to edit a specific documents' description.
	 * @param userIdWriter ID of the user that will edit the document.
	 * @param userIdOwner The document owners' ID.
	 * @param documentName The documents' name.
	 * @param description The new document description.
	 * @pre userIdWriter != null && userIdOwner != null && documentName != null && description != null.
	 */
	@Override
	public void writeDocument(String userIdWriter, String userIdOwner, String documentName, String description) {
		users.writeDocument(userIdWriter, userIdOwner, documentName, description);
	}
	
	/**
	 * Obtains the description from a specified document.
	 * @param userIdReader ID of the user reading the documents' contents.
	 * @param userIdOwner The document owners' ID.
	 * @param documentName The documents' name.
	 * @return The documents' description.
	 * @pre userIdReader != null && userIdOwner != null && documentName != null.
	 */
	@Override
	public String readDocument(String userIdReader, String userIdOwner, String documentName) {
		return users.readDocument(userIdReader, userIdOwner, documentName);
	}
	
	/**
	 * Checks if the specified user is an officer.
	 * @param userId The users' ID.
	 * @return True if the user is an officer.
	 * @pre userId != null.
	 */
	@Override
	public boolean userIsOfficer(String userId) {
		return users.userIsOfficer(userId);
	}
	
	/**
	 * Grants a specified user clearance to a certain document.
	 * @param userIdGrant The users' ID.
	 * @param userIdOwner The document owners' ID.
	 * @param documentName The documents' name.
	 * @pre userIdGrant != null & userIdOwner != null && documentName != null.
	 */
	@Override
	public void grantClearance(String userIdGrant, String userIdOwner, String documentName) {
		users.grantClearance(userIdGrant, userIdOwner, documentName);
	}
	
	/**
	 * Checks if the specified user had a grant to a certain document already revoked.
	 * @param userIdRevoke The users' ID.
	 * @param userIdOwner The document owners' ID.
	 * @param documentName The documents' name.
	 * @return True if the user had the grant to the document already revoked.
	 * @pre userIdRevoke != null && userIdOwner != null && documentName != null.
	 */
	@Override
	public boolean userHasGrantRevoked(String userIdRevoke, String userIdOwner, String documentName) {
		return users.userHasGrantRevoked(userIdRevoke, userIdOwner, documentName);
	}
	
	/**
	 * Revokes a specified users' clearance to a certain document.
	 * @param userIdRevoke The users' ID.
	 * @param userIdOwner The documentOwners' ID.
	 * @param documentName The documents' name.
	 * @pre userIdRevoke != null && userIdOwner != null && documentName != null.
	 */
	@Override
	public void revokeClearance(String userIdRevoke, String userIdOwner, String documentName) {
		users.revokeClearance(userIdRevoke, userIdOwner, documentName);
	}
	
	/**
	 * Checks if a certain clearance level is confidential.
	 * @param documentType The documents' type.
	 * @return True if the clearance level is confidential.
	 * @pre level == "official"  || level == "classified".
	 */
	@Override
	public boolean isClassified(String documentType) {
		return documentType.equals("classified");
	}
	
	/* Iterators */
	
	/**
	 * @return New user iterator.
	 */
	@Override
	public UserIterator newUserIterator() {
		return users.newUserIterator();
	}
	
	/**
	 * @param userId The users' ID.
	 * @return New users' official documents iterator.
	 * @pre userId != null.
	 */
	@Override
	public OfficialDocumentIterator newUserOfficialDocumentsIterator(String userId) {
		return users.newUserOfficialDocumentsIterator(userId);
	}
	
	/**
	 * @param userId The users' ID.
	 * @return New users' classified documents iterator.
	 * @pre userId != null.
	 */
	@Override
	public ClassifiedDocumentIterator newUserClassifiedDocumentsIterator(String userId) {
		return users.newUserClassifiedDocumentsIterator(userId);
	}
	
	/**
	 * @return New top leaked documents iterator.
	 */
	@Override
	public ClassifiedDocumentIterator newTopLeakedDocumentsIterator() {
		return users.newTopLeakedDocumentsIterator();
	}
	
	/**
	 * @return New top granters iterator.
	 */
	@Override
	public OfficerIterator newTopGrantersIterator() {
		return users.newTopGrantersIterator();
	}
}
