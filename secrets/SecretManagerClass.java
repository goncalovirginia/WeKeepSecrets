package secrets;

import iterators.*;
import users.*;

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
	 */
	@Override
	public void registerUser(String type, String userId, String level) {
		users.registerUser(type, userId, level);
	}
	
	/**
	 * Checks if a user with the specified ID exists.
	 * @param userId The users' ID.
	 * @return True if a user with the specified ID exists.
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
	 */
	@Override
	public void uploadDocument(String documentName, String userIdOwner, String documentLevel, String documentDescription) {
		users.uploadDocument(documentName, userIdOwner, documentLevel, documentDescription);
	}
	
	/**
	 * Checks if the specified user is already managing a document with the same name.
	 * @param userId The users' ID.
	 * @param documentName The documents' name.
	 * @return True if the user is already managing a document with the same name.
	 */
	@Override
	public boolean userHasDocument(String userId, String documentName) {
		return users.userHasDocument(userId, documentName);
	}
	
	/**
	 * Checks if a user has sufficient clearance to upload a document of that level.
	 * @param userId The users' ID.
	 * @param documentLevel The documents' clearance level.
	 * @return True if the user has sufficient clearance to upload the document.
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
	 */
	@Override
	public String readDocument(String userIdReader, String userIdOwner, String documentName) {
		return users.readDocument(userIdReader, userIdOwner, documentName);
	}
	
	/**
	 * Checks if the specified user is an officer.
	 * @param userId The users' ID.
	 * @return True if the user is an officer.
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
	 */
	@Override
	public boolean userHasGrantRevoked(String userIdRevoke, String userIdOwner, String documentName) {
		return users.userHasGrantRevoked(userIdRevoke, userIdOwner, documentName);
	}
	
	/**
	 * Revokes a specified users' clearance to a certain document.
	 * @param userIdRevoke The users' ID.
	 * @param userIdOwner
	 * @param documentName The documents' name.
	 */
	@Override
	public void revokeClearance(String userIdRevoke, String userIdOwner, String documentName) {
		users.revokeClearance(userIdRevoke, userIdOwner, documentName);
	}
	
	/**
	 * Checks if a certain clearance level is confidential.
	 * @param level The clearance level to check.
	 * @return True if the clearance level is confidential.
	 */
	@Override
	public boolean isClassified(String level) {
		return level.equals("classified");
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
	 * @param documentType The documents' clearance level.
	 * @return New user document iterator.
	 */
	@Override
	public DocumentIterator newUserDocumentsIterator(String userId, String documentType) {
		return users.newUserDocumentsIterator(userId, documentType);
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
	public UserIterator newTopGrantersIterator() {
		return users.newTopGrantersIterator();
	}
}
