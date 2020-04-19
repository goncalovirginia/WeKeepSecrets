package documents;

import Accesses.AccessCollection;
import Accesses.AccessCollectionClass;
import Accesses.AccessType;
import iterators.AccessIterator;
import iterators.AccessIteratorClass;
import users.User;

public abstract class AbstractDocument implements Document {
	
	
	/* Variables */
	private String name, owner, level;
	protected String description;
	protected AccessCollection accesses;
	
	/* Constructor */
	protected AbstractDocument(String name, String owner, String level, String description) {
		this.name = name;
		this.owner = owner;
		this.level = level;
		this.description = description;
		accesses = new AccessCollectionClass();
	}
	
	/**
	 * @return The documents' name.
	 */
	@Override
	public String getName() {
		return name;
	}
	
	/**
	 * @return The user that manages the document.
	 */
	@Override
	public String getOwner() {
		return owner;
	}
	
	/**
	 * @return The documents' clearance level.
	 */
	@Override
	public String getLevel() {
		return level;
	}
	
	/**
	 * @return The documents' description.
	 */
	@Override
	public String getDescription() {
		return description;
	}
	
	/**
	 * Edits the documents' description and adds a new user access.
	 * @param user The user editing the document.
	 * @param description The new documents' description.
	 */
	@Override
	public void writeDocument(User user, String description) {
		accesses.newAccess(user, AccessType.WRITE);
		this.description = description;
	}
	
	/**
	 * Obtains the description from a specified document and adds a new user access.
	 * @param user The user reading the document.
	 * @return The documents' description.
	 */
	@Override
	public String readDocument(User user) {
		accesses.newAccess(user, AccessType.READ);
		return description;
	}
	
	/**
	 * @return Number of accesses made to the document.
	 */
	@Override
	public int getNumAccesses() {
		return accesses.getNumAccesses();
	}
	
	/**
	 * @return New access iterator.
	 */
	@Override
	public AccessIterator newAccessIterator() {
		return accesses.newAccessIterator();
	}
	
	@Override
	public AccessIterator newReverseAccessIterator() {
		return accesses.newReverseAccessIterator();
	}
	
}
