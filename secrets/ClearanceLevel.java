package secrets;

/**
 * @author Goncalo Virginia - 56773
 *
 * Contains all the existing security clearance levels for users and documents,
 * allowing for easy comparisons.
 */

public enum ClearanceLevel {
	
	/* Clearance Levels */
	OFFICIAL(0),
	CONFIDENTIAL(1),
	SECRET(2),
	TOPSECRET(3);
	
	private final int value; //The clearance levels' numeric value.
	
	/**
	 * Constructor.
	 * @param value The clearance levels' numeric value.
	 */
	ClearanceLevel(int value) {
		this.value = value;
	}
	
	/**
	 * @return The clearance levels' numeric value.
	 */
	public int getValue() {
		return value;
	}
	
	/**
	 * Checks if a clearance level is equal to or greater than another clearance level.
	 * @param other The other clearance level.
	 * @return True if it has enough clearance compared to another clearance level.
	 */
	public boolean hasClearance(ClearanceLevel other) {
		return this.value >= other.getValue();
	}
}
