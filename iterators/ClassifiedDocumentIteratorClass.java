package iterators;

import documents.Document;
import documents.ClassifiedDocument;

/**
 * @author Goncalo Virginia - 56773
 *
 * Classified document iterator.
 */

public class ClassifiedDocumentIteratorClass extends AbstractDocumentIterator implements ClassifiedDocumentIterator {
	
	/**
	 * Constructor.
	 * @param documents Document array to iterate.
	 * @param numDocuments Number of documents in the array.
	 */
	public ClassifiedDocumentIteratorClass(Document[] documents, int numDocuments) {
		super(documents, numDocuments);
	}
	
	/**
	 * @return True if the is another document to be iterated.
	 */
	@Override
	public boolean hasNext() {
		while (nextDocument < numDocuments) {
			if (!(documents[nextDocument] instanceof ClassifiedDocument)) {
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
	public ClassifiedDocument next() {
		return (ClassifiedDocument) documents[nextDocument++];
	}
	
	
}
