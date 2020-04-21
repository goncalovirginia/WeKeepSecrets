
/**
 * @author Goncalo Virginia - 56773
 *
 * Contains all the existing user input commands and their corresponding descriptions,
 * which enables them to be saved, added and listed in a more organized manner.
 */

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
	
	private final String description; //The commands description.
	
	/**
	 * Constructor.
	 * @param description The commands' description.
	 */
	Command(String description) {
		this.description = description;
	}
	
	/**
	 * @return The commands' description.
	 */
	public String getDescription() {
		return description;
	}
	
}
