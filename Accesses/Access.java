package Accesses;

import users.User;

/**
 * @author Goncalo Virginia - 56773
 *
 * Saves a user and and an access type.
 */

public interface Access {
	
	/**
	 * @return The user who was involved in the action.
	 */
	User getUser();
	
	/**
	 * @return The type of access made to the document.
	 */
	String getType();
	
}
