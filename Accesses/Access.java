package Accesses;

import users.User;

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
