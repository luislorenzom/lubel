package com.github.luislorenzom.lubel.parsers;


import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.TextField;

import com.github.luislorenzom.lubel.exceptions.DocumentHandlerException;

public class PlainTextHandler implements DocumentHandler {

	public Document getDocument(InputStream is) throws DocumentHandlerException {
		Document doc = new Document();
		// Index the content
		doc.add(new TextField("content", new InputStreamReader(is)));
		return doc;
	}
}
