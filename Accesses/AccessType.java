package Accesses;

/**
 * @author Goncalo Virginia
 *
 * Contains all the different types of accesses that can be made to a document.
 */

public enum AccessType {
	
	/* Access Types */
	READ("read"),
	WRITE("write"),
	GRANT("grant"),
	REVOKED("revoked");
	
	private final String type;
	
	/**
	 * Constructor.
	 * @param type Type of access in string and lower case form.
	 */
	AccessType(String type) {
		this.type = type;
	}
	
	/**
	 * @return The access type.
	 */
	public String getType() {
		return type;
	}
	
}
