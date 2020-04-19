package iterators;

import documents.Document;

public abstract class AbstractDocumentIterator implements DocumentIterator {
	
	protected Document[] documents;
	protected int numDocuments, nextDocument;
	
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
