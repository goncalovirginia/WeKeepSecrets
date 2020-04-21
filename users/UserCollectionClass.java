package users;

import documents.ClassifiedDocument;
import iterators.*;
import secrets.ClearanceLevel;

/**
 * @author Goncalo Virginia - 56773
 *
 * Manages a collection of users with their corresponding documents.
 */

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
	 * @param userId The document owners' ID.
	 * @param documentLevel The documents' clearance level.
	 * @param documentDescription The documents' description.
	 */
	@Override
	public void uploadDocument(String documentName, String userId, String documentLevel, String documentDescription) {
		int userIndex = index(userId);
		users[userIndex].uploadDocument(documentName, users[userIndex] ,documentLevel, documentDescription);
	}
	
	/**
	 * Checks if the specified user is already managing a document with the following name.
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
		return ClearanceLevel.valueOf(users[index(userId)].getLevel().toUpperCase()).hasClearance(
				ClearanceLevel.valueOf(documentLevel.toUpperCase()));
	}
	
	/**
	 * Checks if the specified document has a classified clearance level.
	 * @param userId The document owners' ID.
	 * @param documentName The documents' name.
	 * @return True if the document has a classified clearance level.
	 */
	@Override
	public boolean documentIsClassified(String userId, String documentName) {
		return userIsOfficer(userId) && users[index(userId)].documentIsClassified(documentName);
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
		int indexAccess = index(userIdAccess);
		int indexOwner = index(userIdOwner);
		
		if (ClearanceLevel.valueOf(users[indexAccess].getLevel().toUpperCase()).hasClearance(
				ClearanceLevel.valueOf(users[indexOwner].getLevel().toUpperCase()))) {
			return true;
		}
		else if (userIsOfficer(userIdAccess) && userIsOfficer(userIdOwner)) {
			return ((Officer) users[indexOwner]).userHasClearance((Officer) users[indexAccess], documentName);
		}
		else {
			return false;
		}
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
		((Officer) users[index(userIdOwner)]).writeDocument(
				users[index(userIdWriter)], documentName, description);
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
		return ClearanceLevel.valueOf(users[index(userId)].getLevel().toUpperCase()).hasClearance(
				ClearanceLevel.CONFIDENTIAL);
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
	
	/* Iterators */
	
	/**
	 * @return New user iterator.
	 */
	@Override
	public UserIterator newUserIterator() {
		return new UserIteratorClass(users, numUsers);
	}
	
	/**
	 * @param userId The users' ID.
	 * @return New user document iterator.
	 */
	@Override
	public OfficialDocumentIterator newUserOfficialDocumentsIterator(String userId) {
		return users[index(userId)].newOfficialDocumentIterator();
	}
	
	/**
	 * @param userId The users' ID.
	 * @return New user document iterator.
	 */
	@Override
	public ClassifiedDocumentIterator newUserClassifiedDocumentsIterator(String userId) {
		return ((Officer)users[index(userId)]).newClassifiedDocumentIterator();
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
	public OfficerIterator newTopGrantersIterator() {
		return new OfficerIteratorClass(topGranters, numTopGranters);
	}
	
	/* Private Methods */
	
	/**
	 * Searches for a certain users' index in the array.
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
	 * @return True if the user array is full.
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
	
	/**
	 * Updates or adds a granter in the top granters array.
	 * @param user User to update or add.
	 */
	private void addTopGranter(Officer user) {
		if (topGranterIsInList(user)) {
			sortUser(topGranterIndex(user));
		}
		else {
			insertUser(user);
		}
	}
	
	/**
	 * Checks if a certain user is already in the top granters array.
	 * @param user The user.
	 * @return True if the user is already in the array.
	 */
	private boolean topGranterIsInList(Officer user) {
		return topGranterIndex(user) != -1;
	}
	
	/**
	 * Searches for a specific user in the top granters array.
	 * @param user The user.
	 * @return The index of the user (-1 if the user isn't found).
	 */
	private int topGranterIndex(Officer user) {
		for (int i = 0; i < numTopGranters; i++) {
			if (topGranters[i].getId().equals(user.getId())) {
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * Insert sorts a new user into the top granters array.
	 * @param user The user.
	 */
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
	
	/**
	 * Inserts the user into a certain position in the top granters array.
	 * @param user The user.
	 * @param pos The index.
	 */
	private void insertAt(Officer user, int pos) {
		for (int i = topGranters.length-1; i > pos; i--) {
			topGranters[i] = topGranters[i-1];
		}
		topGranters[pos] = user;
		
		if (numTopGranters < 10) {
			numTopGranters++;
		}
	}
	
	/**
	 * Sorts an already existing user in the top granters array.
	 * @param pos The position of the user.
	 */
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
	
	/**
	 * Insert sorts a new document into the top leaked documents array.
	 * @param document The document to add.
	 */
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
	
	/**
	 * Inserts the document into a certain position in the top leaked documents array.
	 * @param document The document.
	 * @param pos The index.
	 */
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