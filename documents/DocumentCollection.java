package documents;

import iterators.ClassifiedDocumentIterator;
import iterators.DocumentIterator;
import iterators.OfficialDocumentIterator;
import users.Officer;
import users.User;

public interface DocumentCollection {
	
	/**
	 * Checks if a specified document exists in the collection.
	 * @param documentName The documents' name.
	 * @return True if the document exists.
	 */
	boolean documentExists(String documentName);
	
	/**
	 * Checks if the specified document has a classified clearance level.
	 * @param documentName The documents' name.
	 * @return True if the document has a classified clearance level.
	 */
	boolean documentIsClassified(String documentName);
	
	/**
	 * @return The number of documents in the collection.
	 */
	int getNumDocuments();
	
	/**
	 * Checks if the specified user has clearance to access the document.
	 * @param user The user who wants to access the document.
	 * @param documentName The documents' name.
	 * @return True if the user has clearance.
	 */
	boolean userHasClearance(User user, String documentName);
	
	/**
	 * Uploads a new document with the following characteristics:
	 * @param documentName The document's name.
	 * @param documentLevel The document's clearance level.
	 * @param documentDescription The documents' description.
	 */
	void uploadDocument(String documentName, String userIdOwner, String documentLevel, String documentDescription);
	
	/**
	 * Edits the specified documents' description.
	 * @param user The user editing the document.
	 * @param documentName The documents' name.
	 * @param description The documents' description.
	 */
	void writeDocument(User user, String documentName, String description);
	
	/**
	 * Obtains the description from a specified document.
	 * @param user The user reading the document.
	 * @param documentName The documents' name.
	 * @return The documents' description.
	 */
	String readDocument(User user, String documentName);
	
	/**
	 * Grants a specified user clearance to a certain document.
	 * @param user User to grant clearance to.
	 * @param documentName The documents' name.
	 */
	void grantClearance(Officer user, String documentName);
	
	/**
	 * Checks if the specified user had their last grant to the document revoked.
	 * @param user The user.
	 * @param documentName The documents' name.
	 * @return True if the user had his last grant to the document revoked.
	 */
	boolean userHasGrantRevoked(Officer user, String documentName);
	
	/**
	 * Revokes a users grant to a certain document.
	 * @param user The user.
	 * @param documentName The documents' name.
	 */
	void revokeClearance(Officer user, String documentName);
	
	/**
	 * @return New official document iterator.
	 */
	OfficialDocumentIterator newOfficialDocumentIterator();
	
	/**
	 * @return New classified document iterator.
	 */
	ClassifiedDocumentIterator newClassifiedDocumentIterator();
	
}
