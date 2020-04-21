package documents;

import iterators.AccessIterator;
import users.User;

/**
 * @author Goncalo Virginia - 56773
 *
 * A document with various properties and a history of accesses from users.
 */

public interface Document {
	
	/**
	 * @return The documents' name.
	 */
	String getName();
	
	/**
	 * @return The documents' clearance level.
	 */
	String getLevel();
	
	/**
	 * @return The documents' description.
	 */
	String getDescription();
	
	/**
	 * Obtains the description from a specified document.
	 * @param user The user reading the document.
	 * @return The documents' description.
	 */
	String readDocument(User user);
	
	/**
	 * @return Number of accesses made to the document.
	 */
	int getNumAccesses();
	
	/**
	 * @return The user that manages the document.
	 */
	String getOwner();
	
	/**
	 * @return New access iterator.
	 */
	AccessIterator newAccessIterator();
	
	/**
	 * @return New reverse access iterator.
	 */
	AccessIterator newReverseAccessIterator();
}
