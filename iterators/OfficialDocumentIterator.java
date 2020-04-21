package iterators;

import documents.OfficialDocument;

/**
 * @author Goncalo Virginia - 56773
 *
 * Official document iterator.
 */

public interface OfficialDocumentIterator extends DocumentIterator {
	
	/**
	 * @return The next document to be iterated.
	 */
	OfficialDocument next();
	
}
