package users;

import iterators.OfficialDocumentIterator;

/**
 * @author Goncalo Virginia - 56773
 *
 * Generic user with various base properties.
 */

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
	 * Creates a new document which is managed by a user with sufficient clearance.
	 * @param documentName The documents' name.
	 * @param owner The user.
	 * @param documentLevel The documents' clearance level.
	 * @param documentDescription The documents' description.
	 */
	void uploadDocument(String documentName, User owner, String documentLevel, String documentDescription);
	
	/**
	 * Checks if the user has a specified document.
	 * @param documentName The documents' name.
	 * @return True if the user has the document.
	 */
	boolean hasDocument(String documentName);
	
	/**
	 * Checks if the specified document has a classified clearance level.
	 * @param documentName The documents' name.
	 * @return True if the document has a classified clearance level.
	 */
	boolean documentIsClassified(String documentName);
	
	/**
	 * Obtains the description from a specified document.
	 * @param user The user reading the document.
	 * @param documentName The documents' name.
	 * @return The documents' description.
	 */
	String readDocument(User user, String documentName);
	
	/**
	 * @return Total number of documents the user is managing.
	 */
	int getNumDocuments();
	
	/**
	 * @return New document iterator.
	 */
	OfficialDocumentIterator newOfficialDocumentIterator();
	
}
