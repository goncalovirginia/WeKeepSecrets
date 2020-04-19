public enum Command {
	
	/* Input Commands */
	REGISTER("registers a new user"),
	LISTUSERS("list all registered users"),
	UPLOAD("upload a document"),
	READ("read a document"),
	WRITE("write a document"),
	GRANT("grant access to a document"),
	REVOKE("revoke a grant to access a document"),
	USERDOCS("list the official or classified documents of an user"),
	TOPLEAKED("list the top 10 documents with more grants"),
	TOPGRANTERS("list the top 10 officers that have given more grants"),
	HELP("shows the available commands"),
	EXIT("terminates the execution of the program");
	
	private final String description;
	
	private Command(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}
	
}
