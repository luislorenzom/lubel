package com.github.luislorenzom.lubel.parsers;

import java.io.InputStream;

import org.apache.lucene.document.Document;

import com.github.luislorenzom.lubel.exceptions.DocumentHandlerException;


public interface DocumentHandler {
	
	/**
	 * Creates a Lucene Document from an InputStream
	 * This method can return <code>null</code>
	 * 
	 * @param is the InputStream to convert to a Document
	 * @return a ready-to-index instance of Document
	 */
	public Document getDocument(InputStream is) throws DocumentHandlerException;
}
