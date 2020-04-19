package iterators;

import documents.ClassifiedDocument;

public interface ClassifiedDocumentIterator extends DocumentIterator {
	
	/**
	 * @return True if the is another document to be iterated.
	 */
	boolean hasNext();
	
	/**
	 * @return The next document to be iterated.
	 */
	ClassifiedDocument next();
	
}
