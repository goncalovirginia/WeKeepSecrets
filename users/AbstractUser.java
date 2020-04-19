package users;

import documents.DocumentCollection;
import documents.DocumentCollectionClass;
import iterators.DocumentIterator;
import iterators.OfficialDocumentIterator;

public abstract class AbstractUser implements User {
	
	/* Constants */
	private static final int INITIAL_SIZE = 10;
	
	/* Variables */
	private String type, id, level;
	protected DocumentCollection documents;
	
	/* Constructor */
	protected AbstractUser(String type, String id, String level) {
		this.type = type;
		this.id = id;
		this.level = level;
		documents = new DocumentCollectionClass();
	}
	
	/**
	 * @return The users' type.
	 */
	@Override
	public String getType() {
		return type;
	}
	
	/**
	 * @return The users' ID.
	 */
	@Override
	public String getId() {
		return id;
	}
	
	/**
	 * @return The users' clearance level.
	 */
	@Override
	public String getLevel() {
		return level;
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
		documents.uploadDocument(documentName, userIdOwner, documentLevel, documentDescription);
	}
	
	/**
	 * Checks if the user has a specified document.
	 * @param documentName The documents' name.
	 * @return True if the user has the document.
	 */
	@Override
	public boolean hasDocument(String documentName) {
		return documents.documentExists(documentName);
	}
	
	/**
	 * Checks if the specified document has a classified clearance level.
	 * @param documentName The documents' name.
	 * @return True if the document has a classified clearance level.
	 */
	@Override
	public boolean documentIsClassified(String documentName) {
		return documents.documentIsClassified(documentName);
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
	 * Obtains the description from a specified document.
	 * @param user The user reading the document.
	 * @param documentName The documents' name.
	 * @return The documents' description.
	 */
	@Override
	public String readDocument(User user, String documentName) {
		return documents.readDocument(user, documentName);
	}
	
	/**
	 * Checks if the specified user has clearance to access the document.
	 * @param writer The user who wants to access the document.
	 * @param documentName The documents' name.
	 * @return True if the user has clearance.
	 */
	public boolean userHasClearance(User writer, String documentName) {
		return documents.userHasClearance(writer, documentName);
	}
	
	/**
	 * @return Total number of documents the user is managing.
	 */
	@Override
	public int getNumDocuments() {
		return documents.getNumDocuments();
	}
	
	/**
	 * @return New official document iterator.
	 */
	@Override
	public OfficialDocumentIterator newOfficialDocumentIterator() {
		return documents.newOfficialDocumentIterator();
	}
	
}
