package iterators;

import documents.Document;

public interface DocumentIterator {
	
	/**
	 * @return True if the is another document to be iterated.
	 */
	boolean hasNext();
	
	/**
	 * @return The next document to be iterated.
	 */
	Document next();
	
}
