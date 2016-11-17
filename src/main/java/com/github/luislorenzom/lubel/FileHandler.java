package com.github.luislorenzom.lubel;

import java.io.File;
import org.apache.lucene.document.Document;

import com.github.luislorenzom.lubel.exceptions.FileHandlerException;



public interface FileHandler {
	
	/** 
	 * Creates a Lucene Document from a file.
	 * This method can return <code>null</code>.
	 * 
	 * @param file the File to convert to a Document
	 * @return a ready-to-index instance of Document
	 */
	Document getDocument(File file) throws FileHandlerException;
}
