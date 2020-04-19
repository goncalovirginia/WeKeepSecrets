package Accesses;

public enum AccessType {
	
	READ("read"),
	WRITE("write"),
	GRANT("grant"),
	REVOKED("revoked");
	
	private final String type;
	
	private AccessType(String type) {
		this.type = type;
	}
	
	public String getType() {
		return type;
	}
	
}
