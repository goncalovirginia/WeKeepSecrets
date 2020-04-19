package iterators;

import documents.OfficialDocument;

public interface OfficialDocumentIterator extends DocumentIterator {
	
	/**
	 * @return True if the is another document to be iterated.
	 */
	boolean hasNext();
	
	/**
	 * @return The next document to be iterated.
	 */
	OfficialDocument next();
	
}
