package iterators;

import documents.Document;

/**
 * @author Goncalo Virginia - 56773
 *
 * Generic document iterator.
 */

public abstract class AbstractDocumentIterator implements DocumentIterator {
	
	/* Variables */
	protected Document[] documents;
	protected int numDocuments, nextDocument;
	
	/**
	 * Constructor.
	 * @param documents Document array to iterate.
	 * @param numDocuments Number of documents in the array.
	 */
	protected AbstractDocumentIterator(Document[] documents, int numDocuments) {
		this.documents = documents;
		this.numDocuments = numDocuments;
		nextDocument = 0;
	}
	
	/**
	 * @return True if the is another document to be iterated.
	 */
	@Override
	public abstract boolean hasNext();
	
	/**
	 * @return The next document to be iterated.
	 */
	@Override
	public abstract Document next();
	
}
