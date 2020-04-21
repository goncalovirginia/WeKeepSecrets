package iterators;

import documents.Document;
import documents.OfficialDocument;

/**
 * @author Goncalo Virginia - 56773
 *
 * Official document iterator.
 */

public class OfficialDocumentIteratorClass extends AbstractDocumentIterator implements OfficialDocumentIterator {
	
	/**
	 * Constructor.
	 * @param documents Document array to iterate.
	 * @param numDocuments Number of documents in the array.
	 */
	public OfficialDocumentIteratorClass(Document[] documents, int numDocuments) {
		super(documents, numDocuments);
	}
	
	/**
	 * @return True if the is another document to be iterated.
	 */
	@Override
	public boolean hasNext() {
		while (nextDocument < numDocuments) {
			if (!(documents[nextDocument] instanceof OfficialDocument)) {
				nextDocument++;
			}
			else {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * @return The next document to be iterated.
	 */
	@Override
	public OfficialDocument next() {
		return (OfficialDocument) documents[nextDocument++];
	}
	
}
