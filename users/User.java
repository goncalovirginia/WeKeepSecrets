package users;

import iterators.DocumentIterator;
import iterators.OfficialDocumentIterator;

public interface User {
	
	/**
	 * @return The users' type.
	 */
	String getType();
	
	/**
	 * @return The users' ID.
	 */
	String getId();
	
	/**
	 * @return The users' clearance level.
	 */
	String getLevel();
	
	/**
	 * Uploads a new document with the following characteristics:
	 * @param documentName The document's name.
	 * @param documentLevel The document's clearance level.
	 * @param documentDescription The documents' description.
	 */
	void uploadDocument(String documentName, String userIdOwner, String documentLevel, String documentDescription);
	
	/**
	 * Checks if the user has a specified document.
	 * @param documentName The documents' name.
	 * @return True if the user has the document.
	 */
	boolean hasDocument(String documentName);
	
	/**
	 * @return True if the document has a classified clearance level.
	 */
	boolean documentIsClassified(String documentName);
	
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
	 * Checks if the specified user has clearance to access the document.
	 * @param writer The user.
	 * @param documentName The documents' name.
	 * @return True if the user has clearance.
	 */
	boolean userHasClearance(User writer, String documentName);
	
	/**
	 * @return Total number of documents the user is managing.
	 */
	int getNumDocuments();
	
	/**
	 * @param documentType Type of documents to iterate.
	 * @return New document iterator.
	 */
	OfficialDocumentIterator newOfficialDocumentIterator();
	
}
