package secrets;

public enum ClearanceLevel {
	
	OFFICIAL(0),
	CONFIDENTIAL(1),
	SECRET(2),
	TOPSECRET(3);
	
	private final int value;
	
	private ClearanceLevel(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
	
	public boolean isGreaterThan(ClearanceLevel other) {
		return this.value > other.getValue();
	}
}
