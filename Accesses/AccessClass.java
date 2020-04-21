package Accesses;

import users.User;

/**
 * @author Goncalo Virginia - 56773
 *
 * Saves a user and and an access type.
 */

public class AccessClass implements Access {
	
	/* Variables */
	private User user;
	private AccessType accessType;
	
	/* Constructor */
	public AccessClass(User user, AccessType accessType) {
		this.user = user;
		this.accessType = accessType;
	}
	
	/**
	 * @return The user who was involved in the action.
	 */
	@Override
	public User getUser() {
		return user;
	}
	
	/**
	 * @return The type of access made to the document.
	 */
	@Override
	public String getType() {
		return accessType.getType();
	}
}
