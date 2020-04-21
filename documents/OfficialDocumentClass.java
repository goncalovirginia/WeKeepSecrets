package documents;

import users.User;

/**
 * @author Goncalo Virginia - 56773
 *
 * A type of document with low security clearance.
 */

public class OfficialDocumentClass extends AbstractDocument implements OfficialDocument {
	
	/* Constructor */
	public OfficialDocumentClass(String documentName, User userIdOwner, String documentLevel, String documentDescription) {
		super(documentName, userIdOwner, documentLevel, documentDescription);
	}
	
}
