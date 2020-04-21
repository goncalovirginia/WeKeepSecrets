package documents;

import iterators.*;
import secrets.ClearanceLevel;
import users.Officer;
import users.User;

/**
 * @author Goncalo Virginia - 56773
 *
 * Manages a collection of documents with various types of security levels.
 */

public class DocumentCollectionClass implements DocumentCollection {
	
	/* Constants */
	private static final int INITIAL_SIZE = 10;
	
	/* Variables */
	private Document[] documents;
	private int numDocuments;
	
	/* Constructor */
	public DocumentCollectionClass() {
		documents = new Document[INITIAL_SIZE];
		numDocuments = 0;
	}
	
	/**
	 * Checks if a specified document exists in the collection.
	 * @param documentName The documents' name.
	 * @return True if the document exists.
	 */
	public boolean documentExists(String documentName) {
		return index(documentName) != -1;
	}
	
	/**
	 * Checks if the specified document has a classified clearance level.
	 * @param documentName The documents' name.
	 * @return True if the document has a classified clearance level.
	 */
	public boolean documentIsClassified(String documentName) {
		return ClearanceLevel.valueOf(documents[index(documentName)].getLevel().toUpperCase()).hasClearance(
				ClearanceLevel.CONFIDENTIAL);
	}
	
	/**
	 * @return The number of documents in the collection.
	 */
	@Override
	public int getNumDocuments() {
		return numDocuments;
	}
	
	/**
	 * Checks if the specified user has clearance to access the document.
	 * @param user The user who wants to access the document.
	 * @param documentName The documents' name.
	 * @return True if the user has clearance.
	 */
	@Override
	public boolean userHasClearance(Officer user, String documentName) {
		return ClearanceLevel.valueOf(user.getLevel().toUpperCase()).hasClearance(
				ClearanceLevel.valueOf(documents[index(documentName)].getLevel().toUpperCase())) ||
					((ClassifiedDocument) documents[index(documentName)]).userHasGrant(user.getId());
	}
	
	/**
	 * Uploads a new document with the following characteristics:
	 * @param name The document's name.
	 * @param level The document's clearance level.
	 * @param description The documents' description.
	 */
	@Override
	public void uploadDocument(String name, User owner, String level, String description) {
		if (isFull()) {
			resize();
		}
		
		if (!ClearanceLevel.valueOf(level.toUpperCase()).hasClearance(ClearanceLevel.CONFIDENTIAL)) {
			documents[numDocuments++] = new OfficialDocumentClass(name, owner, level, description);
		}
		else {
			documents[numDocuments++] = new ClassifiedDocumentClass(name, owner, level, description);
		}
	}
	
	/**
	 * Edits the specified documents' description.
	 * @param user The user editing the document.
	 * @param documentName The documents' name.
	 * @param description The documents' description.
	 */
	@Override
	public void writeDocument(User user, String documentName, String description) {
		((ClassifiedDocument) documents[index(documentName)]).writeDocument(user, description);
	}
	
	/**
	 * Obtains the description from a specified document.
	 * @param user The user reading the document.
	 * @param documentName The documents' name.
	 * @return The documents' description.
	 */
	@Override
	public String readDocument(User user, String documentName) {
		return documents[index(documentName)].readDocument(user);
	}
	
	/**
	 * Grants a specified user clearance to a certain document.
	 * @param user User to grant clearance to.
	 * @param documentName The documents' name.
	 */
	@Override
	public void grantClearance(Officer user, String documentName) {
		((ClassifiedDocument)documents[index(documentName)]).grantClearance(user);
	}
	
	/**
	 * Checks if the specified user had their last grant to the document revoked.
	 * @param user The user.
	 * @param documentName The documents' name.
	 * @return True if the user had his last grant to the document revoked.
	 */
	@Override
	public boolean userHasGrantRevoked(Officer user, String documentName) {
		return ((ClassifiedDocument)documents[index(documentName)]).userHasGrantRevoked(user.getId());
	}
	
	/**
	 * Revokes a users grant to a certain document.
	 * @param user The user.
	 * @param documentName The documents' name.
	 */
	@Override
	public void revokeClearance(Officer user, String documentName) {
		((ClassifiedDocument)documents[index(documentName)]).revokeClearance(user);
	}
	
	/**
	 * @return New official document iterator.
	 */
	@Override
	public OfficialDocumentIterator newOfficialDocumentIterator() {
		return new OfficialDocumentIteratorClass(documents, numDocuments);
	}
	
	/**
	 * @return New classified document iterator.
	 */
	@Override
	public ClassifiedDocumentIterator newClassifiedDocumentIterator() {
		return new ClassifiedDocumentIteratorClass(documents, numDocuments);
	}
	
	/* Private Methods */
	
	/**
	 * Searches for a certain documents' index in the collection.
	 * @param documentName The documents' name.
	 * @return The documents' index.
	 */
	private int index(String documentName) {
		for (int i = 0; i < numDocuments; i++) {
			if (documents[i].getName().equals(documentName)) {
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * @return True if the array is full.
	 */
	private boolean isFull() {
		return numDocuments == documents.length;
	}
	
	/**
	 * Resizes the array to twice the current length.
	 */
	private void resize() {
		Document[] temp = new Document[documents.length * 2];
		
		for (int i = 0; i < documents.length; i++) {
			temp[i] = documents[i];
		}
		
		documents = temp;
	}
	
}
