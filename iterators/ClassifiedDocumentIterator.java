package iterators;

import documents.ClassifiedDocument;

/**
 * @author Goncalo Virginia - 56773
 *
 * Classified document iterator.
 */

public interface ClassifiedDocumentIterator extends DocumentIterator {
	
	/**
	 * @return The next document to be iterated.
	 */
	ClassifiedDocument next();
	
}
