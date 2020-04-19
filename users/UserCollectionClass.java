package users;

import documents.ClassifiedDocument;
import documents.Document;
import iterators.*;
import secrets.ClearanceLevel;

public class UserCollectionClass implements UserCollection {
	
	/* Constants */
	private static final int DEFAULT_SIZE = 100, TOP_SIZE = 10;
	
	/* Variables */
	private User[] users;
	private Officer[] topGranters;
	private ClassifiedDocument[] topLeaked;
	private int numUsers, numTopGranters, numTopLeaked;
	
	/* Constructor */
	public UserCollectionClass() {
		users = new User[DEFAULT_SIZE];
		numUsers = 0;
		topGranters = new Officer[TOP_SIZE];
		numTopGranters = 0;
	}
	
	/* Public Methods */
	
	/**
	 * Registers a new user with the following characteristics:
	 * @param type The users' type.
	 * @param id The users' ID.
	 * @param level The users' clearance level.
	 */
	@Override
	public void registerUser(String type, String id, String level) {
		if (isFull()) {
			resize();
		}
		
		if (type.equals("clerk")) {
			users[numUsers++] = new ClerkClass(type, id, level);
		}
		else {
			users[numUsers++] = new OfficerClass(type, id, level);
		}
	}
	
	/**
	 * Checks if a user with the specified ID exists.
	 * @param userId The users' ID.
	 * @return True if a user with the specified ID exists.
	 */
	@Override
	public boolean userExists(String userId) {
		return index(userId) != -1;
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
		users[index(userIdOwner)].uploadDocument(documentName, userIdOwner ,documentLevel, documentDescription);
	}
	
	/**
	 * Checks if the specified user is already managing a document with the same name.
	 * @param userId The users' ID.
	 * @param documentName The documents' name.
	 * @return True if the user is already managing a document with the same name.
	 */
	@Override
	public boolean userHasDocument(String userId, String documentName) {
		return users[index(userId)].hasDocument(documentName);
	}
	
	/**
	 * Checks if a user has sufficient clearance to upload a document of that level.
	 * @param userId The users' ID.
	 * @param documentLevel The documents' clearance level.
	 * @return True if the user has sufficient clearance to upload the document.
	 */
	@Override
	public boolean userHasLevel(String userId, String documentLevel) {
		return users[index(userId)].getType().equals("officer") || documentLevel.equals("official");
	}
	
	/**
	 * Checks if the specified document has a classified clearance level.
	 * @param userIdOwner The document owners' ID.
	 * @param documentName The documents' name.
	 * @return True if the document has a classified clearance level.
	 */
	@Override
	public boolean documentIsClassified(String userIdOwner, String documentName) {
		return users[index(userIdOwner)].documentIsClassified(documentName);
	}
	
	/**
	 * Checks if the specified user has sufficient clearance to access a certain document.
	 * @param userIdWriter The writers' ID.
	 * @param userIdOwner The document owners' ID.
	 * @param documentName The documents' name.
	 * @return True if the user has sufficient clearance.
	 */
	@Override
	public boolean userHasClearance(String userIdWriter, String userIdOwner, String documentName) {
		return users[index(userIdOwner)].userHasClearance(users[index(userIdWriter)], documentName);
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
		users[index(userIdOwner)].writeDocument(users[index(userIdWriter)], documentName, description);
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
		return users[index(userIdOwner)].readDocument(users[index(userIdReader)], documentName);
	}
	
	/**
	 * Checks if the specified user is an officer.
	 * @param userId The users' ID.
	 * @return True if the user is an officer.
	 */
	@Override
	public boolean userIsOfficer(String userId) {
		return ClearanceLevel.valueOf(users[index(userId)].getLevel().toUpperCase()).isGreaterThan(ClearanceLevel.OFFICIAL);
	}
	
	/**
	 * Grants a specified user clearance to a certain document.
	 * @param userIdGrant The users' ID.
	 * @param userIdOwner The document owners' ID.
	 * @param documentName The documents' name.
	 */
	@Override
	public void grantClearance(String userIdGrant, String userIdOwner, String documentName) {
		int indexOwner = index(userIdOwner);
		
		((Officer)users[indexOwner]).grantClearance((Officer)users[index(userIdGrant)], documentName);
		
		addTopGranter((Officer)users[indexOwner]);
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
		return ((Officer)users[index(userIdOwner)]).userHasGrantRevoked((Officer)users[index(userIdRevoke)], documentName);
	}
	
	/**
	 * Revokes a specified users' clearance to a certain document.
	 * @param userIdRevoke The users' ID.
	 * @param userIdOwner The document owners' ID.
	 * @param documentName The documents' name.
	 */
	@Override
	public void revokeClearance(String userIdRevoke, String userIdOwner, String documentName) {
		((Officer)users[index(userIdOwner)]).revokeClearance((Officer)users[index(userIdRevoke)], documentName);
	}
	
	/**
	 * @return New user iterator.
	 */
	@Override
	public UserIterator newUserIterator() {
		return new UserIteratorClass(users, numUsers);
	}
	
	/**
	 * @param userId The users' ID.
	 * @param documentType The documents' clearance level.
	 * @return New user document iterator.
	 */
	@Override
	public DocumentIterator newUserDocumentsIterator(String userId, String documentType) {
		if (documentType.equals("official")) {
			return users[index(userId)].newOfficialDocumentIterator();
		}
		else {
			return ((Officer)users[index(userId)]).newClassifiedDocumentIterator();
		}
	}
	
	/**
	 * @return New top leaked documents iterator.
	 */
	@Override
	public ClassifiedDocumentIterator newTopLeakedDocumentsIterator() {
		topLeaked = new ClassifiedDocument[TOP_SIZE];
		numTopLeaked = 0;
		
		for (int i = 0; i < numUsers; i++) {
			if (users[i] instanceof Officer) {
				ClassifiedDocumentIterator leakedDocuments = ((Officer)users[i]).newClassifiedDocumentIterator();
				
				while (leakedDocuments.hasNext()) {
					ClassifiedDocument document = leakedDocuments.next();
					
					if (document.getNumGrants() > 0) {
						insertDocument(document);
					}
				}
			}
		}
		
		return new ClassifiedDocumentIteratorClass(topLeaked, numTopLeaked);
	}
	
	/**
	 * @return New top granters iterator.
	 */
	@Override
	public UserIterator newTopGrantersIterator() {
		return new UserIteratorClass(topGranters, numTopGranters);
	}
	
	/* Private Methods */
	
	/**
	 * Searches for a certain users' index in the collection.
	 * @param userId The users' ID.
	 * @return The users' index.
	 */
	private int index(String userId) {
		for (int i = 0; i < numUsers; i++) {
			if (users[i].getId().equals(userId)) {
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * @return True if the array is full.
	 */
	private boolean isFull() {
		return numUsers == users.length;
	}
	
	/**
	 * Resizes the array to twice the current length.
	 */
	private void resize() {
		User[] temp = new User[users.length * 2];
		
		for (int i = 0; i < users.length; i++) {
			temp[i] = users[i];
		}
		
		users = temp;
	}
	
	private void addTopGranter(Officer user) {
		if (topGranterIsInList(user)) {
			sortUser(topGranterIndex(user));
		}
		else {
			insertUser(user);
		}
	}
	
	private boolean topGranterIsInList(Officer user) {
		return topGranterIndex(user) != -1;
	}
	
	private int topGranterIndex(Officer user) {
		for (int i = 0; i < numTopGranters; i++) {
			if (topGranters[i].getId().equals(user.getId())) {
				return i;
			}
		}
		return -1;
	}
	
	private void insertUser(Officer user) {
		int pos = -1;
		
		for (int i = 0; i < numTopGranters && pos == -1; i++) {
			if (topGranters[i].getNumGrants() < user.getNumGrants() ||
					(topGranters[i].getNumGrants() == user.getNumGrants() &&
							topGranters[i].getId().compareTo(user.getId()) > 0)) {
				pos = i;
			}
		}
		if (pos == -1 && numTopGranters < topGranters.length) {
			pos = numTopGranters;
		}
		
		if (pos != -1) {
			insertAt(user, pos);
		}
	}
	
	private void insertAt(Officer user, int pos) {
		for (int i = topGranters.length-1; i > pos; i--) {
			topGranters[i] = topGranters[i-1];
		}
		topGranters[pos] = user;
		
		if (numTopGranters < 10) {
			numTopGranters++;
		}
	}
	
	private void sortUser(int pos) {
		for (int i = pos; i > 0; i--) {
			if (topGranters[i].getNumGrants() > topGranters[i-1].getNumGrants() ||
					(topGranters[i].getNumGrants() == topGranters[i-1].getNumGrants() &&
							topGranters[i].getId().compareTo(topGranters[i-1].getId()) < 0)) {
				Officer temp = topGranters[i];
				
				topGranters[i] = topGranters[i-1];
				topGranters[i-1] = temp;
			}
		}
	}
	
	private void insertDocument(ClassifiedDocument document) {
		int pos = -1;
		
		for (int i = 0; i < numTopLeaked && pos == -1; i++) {
			if (topLeaked[i].getNumGrants() < document.getNumGrants() ||
					(topLeaked[i].getNumGrants() == document.getNumGrants() &&
							topLeaked[i].getName().compareTo(document.getName()) > 0)) {
				pos = i;
			}
		}
		if (pos == -1 && numTopLeaked < topLeaked.length) {
			pos = numTopLeaked;
		}
		
		if (pos != -1) {
			insertAt(document, pos);
		}
	}
	
	private void insertAt(ClassifiedDocument document, int pos) {
		for (int i = topLeaked.length-1; i > pos; i--) {
			topLeaked[i] = topLeaked[i-1];
		}
		topLeaked[pos] = document;
		
		if (numTopLeaked < 10) {
			numTopLeaked++;
		}
	}
	
}