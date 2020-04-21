package secrets;

import iterators.*;

/**
 * @author Goncalo Virginia - 56773.
 *
 * Serves as a middleman to the Main and UserCollectionClass classes.
 * Verifies certain preconditions for the Main class in order to execute methods in the UserCollectionClass.
 */

public interface SecretManager {
	
	/**
	 * Registers a new user with the following characteristics:
	 * @param type The users' type.
	 * @param userId The users' ID.
	 * @param level The users' clearance level.
	 * @pre type == "clerk" || type == "officer" && userId != null && level == "official" || level == "confidential" ||
	 * level == "secret" || level == "topsecret".
	 */
	void registerUser(String type, String userId, String level);
	
	/**
	 * Checks if a user with the specified ID exists.
	 * @param userId The users' ID.
	 * @return True if a user with the specified ID exists.
	 * @pre userId != null.
	 */
	boolean userExists(String userId);
	
	/**
	 * Creates a new document which is managed by a user with sufficient clearance.
	 * @param documentName The documents' name.
	 * @param userIdOwner The users' ID.
	 * @param documentLevel The documents' clearance level.
	 * @param documentDescription The documents' description.
	 * @pre documentName != null && userIdOwner != null && documentLevel != null && documentDescription != null.
	 */
	void uploadDocument(String documentName, String userIdOwner, String documentLevel, String documentDescription);
	
	/**
	 * Checks if the specified user is already managing a document with the same name.
	 * @param userId The users' ID.
	 * @param documentName The documents' name.
	 * @return True if the user is already managing a document with the same name.
	 * @pre userId != null && documentName != null.
	 */
	boolean userHasDocument(String userId, String documentName);
	
	/**
	 * Checks if a user has sufficient clearance to interact with a document of that level.
	 * @param userId The users' ID.
	 * @param documentLevel The documents' clearance level.
	 * @return True if the user has sufficient clearance to upload the document.
	 * @pre userId != null && documentLevel == "official" || documentLevel == "confidential" ||
	 * documentLevel == "secret" || documentLevel == "topsecret".
	 */
	boolean userHasLevel(String userId, String documentLevel);
	
	/**
	 * Checks if the specified document has a classified clearance level.
	 * @param userIdOwner The document owners' ID.
	 * @param documentName The documents' name.
	 * @return True if the document has a classified clearance level.
	 * @pre userIdOwner != null && documentName != null.
	 */
	boolean documentIsClassified(String userIdOwner, String documentName);
	
	/**
	 * Checks if the specified user has sufficient clearance to access a certain document.
	 * @param userIdAccess ID of the user trying to access the document.
	 * @param userIdOwner The document owners' ID.
	 * @param documentName The documents' name.
	 * @return True if the user has sufficient clearance.
	 * @pre userIdAccess != null && userIdOwner != null && documentName != null.
	 */
	boolean userHasClearance(String userIdAccess, String userIdOwner, String documentName);
	
	/**
	 * Enables a user to edit a specific documents' description.
	 * @param userIdWriter ID of the user that will edit the document.
	 * @param userIdOwner The document owners' ID.
	 * @param documentName The documents' name.
	 * @param description The new document description.
	 * @pre userIdWriter != null && userIdOwner != null && documentName != null && description != null.
	 */
	void writeDocument(String userIdWriter, String userIdOwner, String documentName, String description);
	
	/**
	 * Obtains the description from a specified document.
	 * @param userIdReader ID of the user reading the documents' contents.
	 * @param userIdOwner The document owners' ID.
	 * @param documentName The documents' name.
	 * @return The documents' description.
	 * @pre userIdReader != null && userIdOwner != null && documentName != null.
	 */
	String readDocument(String userIdReader, String userIdOwner, String documentName);
	
	/**
	 * Checks if the specified user is an officer.
	 * @param userId The users' ID.
	 * @return True if the user is an officer.
	 * @pre userId != null.
	 */
	boolean userIsOfficer(String userId);
	
	/**
	 * Grants a specified user clearance to a certain document.
	 * @param userIdGrant The users' ID.
	 * @param userIdOwner The document owners' ID.
	 * @param documentName The documents' name.
	 * @pre userIdGrant != null & userIdOwner != null && documentName != null.
	 */
	void grantClearance(String userIdGrant, String userIdOwner, String documentName);
	
	/**
	 * Checks if the specified user had a grant to a certain document already revoked.
	 * @param userIdRevoke The users' ID.
	 * @param userIdOwner The document owners' ID.
	 * @param documentName The documents' name.
	 * @return True if the user had the grant to the document already revoked.
	 * @pre userIdRevoke != null && userIdOwner != null && documentName != null.
	 */
	boolean userHasGrantRevoked(String userIdRevoke, String userIdOwner, String documentName);
	
	/**
	 * Revokes a specified users' clearance to a certain document.
	 * @param userIdRevoke The users' ID.
	 * @param userIdOwner The documentOwners' ID.
	 * @param documentName The documents' name.
	 * @pre userIdRevoke != null && userIdOwner != null && documentName != null.
	 */
	void revokeClearance(String userIdRevoke, String userIdOwner, String documentName);
	
	/**
	 * Checks if a certain clearance level is confidential.
	 * @param documentType The documents' type.
	 * @return True if the clearance level is confidential.
	 * @pre level == "official"  || level == "classified".
	 */
	boolean isClassified(String documentType);
	
	/* Iterators */
	
	/**
	 * @return New user iterator.
	 */
	UserIterator newUserIterator();
	
	/**
	 * @param userId The users' ID.
	 * @return New users' official documents iterator.
	 * @pre userId != null.
	 */
	OfficialDocumentIterator newUserOfficialDocumentsIterator(String userId);
	
	/**
	 * @param userId The users' ID.
	 * @return New users' classified documents iterator.
	 * @pre userId != null.
	 */
	ClassifiedDocumentIterator newUserClassifiedDocumentsIterator(String userId);
	
	/**
	 * @return New top leaked documents iterator.
	 */
	ClassifiedDocumentIterator newTopLeakedDocumentsIterator();
	
	/**
	 * @return New top granters iterator.
	 */
	OfficerIterator newTopGrantersIterator();
	
}
