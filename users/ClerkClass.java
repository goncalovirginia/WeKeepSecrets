package users;

/**
 * @author Goncalo Virginia - 56773
 *
 * Type of user with low security clearance.
 */

public class ClerkClass extends AbstractUser implements Clerk {
	
	/* Constructor */
	public ClerkClass(String type, String id, String level) {
		super(type, id, level);
	}
	
}
