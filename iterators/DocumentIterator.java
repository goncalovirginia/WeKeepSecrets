package iterators;

import documents.Document;

/**
 * @author Goncalo Virginia - 56773
 *
 * Generic document iterator.
 */

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
