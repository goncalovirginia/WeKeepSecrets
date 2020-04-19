package iterators;

import documents.ClassifiedDocument;
import documents.Document;
import documents.OfficialDocument;

public class OfficialDocumentIteratorClass extends AbstractDocumentIterator implements OfficialDocumentIterator {
	
	/* Constructor */
	public OfficialDocumentIteratorClass(Document[] documents, int numDocuments) {
		super(documents, numDocuments);
	}
	
	/**
	 * @return True if the is another document to be iterated.
	 */
	@Override
	public boolean hasNext() {
		while (nextDocument < documents.length) {
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
