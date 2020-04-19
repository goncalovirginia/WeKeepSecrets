package users;

import iterators.*;

public interface UserCollection {
	
	/**
	 * Registers a new user with the following characteristics:
	 * @param type The users' type.
	 * @param id The users' ID.
	 * @param level The users' clearance level.
	 */
	void registerUser(String type, String id, String level);
	
	/**
	 * Checks if a user with the specified ID exists.
	 * @param id The users' ID.
	 * @return True if a user with the specified ID exists.
	 */
	boolean userExists(String id);
	
	/**
	 * Creates a new document which is managed by a user with sufficient clearance.
	 * @param documentName The documents' name.
	 * @param userId The users' ID.
	 * @param documentLevel The documents' clearance level.
	 */
	void uploadDocument(String documentName, String userId, String documentLevel, String documentDescription);
	
	/**
	 * Checks if the specified user is already managing a document with the same name.
	 * @param userId The users' ID.
	 * @param documentName The documents' name.
	 * @return True if the user is already managing a document with the same name.
	 */
	boolean userHasDocument(String userId, String documentName);
	
	/**
	 * Checks if a user has sufficient clearance to upload a document of that level.
	 * @param userId The users' ID.
	 * @param documentLevel The documents' clearance level.
	 * @return True if the user has sufficient clearance to upload the document.
	 */
	boolean userHasLevel(String userId, String documentLevel);
	
	/**
	 * Checks if the specified document has a classified clearance level.
	 * @param userIdOwner The document owners' ID.
	 * @param documentName The documents' name.
	 * @return True if the document has a classified clearance level.
	 */
	boolean documentIsClassified(String userIdOwner, String documentName);
	
	/**
	 * Checks if the specified user has sufficient clearance to access a certain document.
	 * @param userIdWriter The writers' ID.
	 * @param userIdOwner The document owners' ID.
	 * @param documentName The documents' name.
	 * @return True if the user has sufficient clearance.
	 */
	boolean userHasClearance(String userIdWriter, String userIdOwner, String documentName);
	
	/**
	 * Enables a user to edit a specific documents' description.
	 * @param userIdWriter ID of the user that will edit the document.
	 * @param userIdOwner The document owners' ID.
	 * @param documentName The documents' name.
	 * @param description The new document description.
	 */
	void writeDocument(String userIdWriter, String userIdOwner, String documentName, String description);
	
	/**
	 * Obtains the description from a specified document.
	 * @param userIdReader ID of the user reading the documents' contents.
	 * @param userIdOwner The document owners' ID.
	 * @param documentName The documents' name.
	 * @return The documents' description.
	 */
	String readDocument(String userIdReader, String userIdOwner, String documentName);
	
	/**
	 * Checks if the specified user is an officer.
	 * @param userId The users' ID.
	 * @return True if the user is an officer.
	 */
	boolean userIsOfficer(String userId);
	
	/**
	 * Grants a specified user clearance to a certain document.
	 * @param userIdGrant The users' ID.
	 * @param userIdOwner The document owners' ID.
	 * @param documentName The documents' name.
	 */
	void grantClearance(String userIdGrant, String userIdOwner, String documentName);
	
	/**
	 * Checks if the specified user had a grant to a certain document already revoked.
	 * @param userIdRevoke The users' ID.
	 * @param userIdOwner
	 * @param documentName The documents' name.
	 * @return True if the user had the grant to the document already revoked.
	 */
	boolean userHasGrantRevoked(String userIdRevoke, String userIdOwner, String documentName);
	
	/**
	 * Revokes a specified users' clearance to a certain document.
	 * @param userIdRevoke The users' ID.
	 * @param userIdOwner The document owners' ID.
	 * @param documentName The documents' name.
	 */
	void revokeClearance(String userIdRevoke, String userIdOwner, String documentName);
	
	/* Iterators */
	
	/**
	 * @return New user iterator.
	 */
	UserIterator newUserIterator();
	
	/**
	 * @param userId The users' ID.
	 * @param documentLevel The documents' clearance level.
	 * @return New user document iterator.
	 */
	DocumentIterator newUserDocumentsIterator(String userId, String documentLevel);
	
	/**
	 * @return New top leaked documents iterator.
	 */
	ClassifiedDocumentIterator newTopLeakedDocumentsIterator();
	
	/**
	 * @return New top granters iterator.
	 */
	UserIterator newTopGrantersIterator();
	
	
	
	
	
}
