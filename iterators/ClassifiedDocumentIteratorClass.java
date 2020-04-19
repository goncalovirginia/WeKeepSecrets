package iterators;

import documents.Document;
import documents.ClassifiedDocument;

public class ClassifiedDocumentIteratorClass extends AbstractDocumentIterator implements ClassifiedDocumentIterator {
	
	/* Constructor */
	public ClassifiedDocumentIteratorClass(Document[] documents, int numDocuments) {
		super(documents, numDocuments);
	}
	
	/**
	 * @return True if the is another document to be iterated.
	 */
	@Override
	public boolean hasNext() {
		while (nextDocument < documents.length) {
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
