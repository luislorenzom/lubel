package com.github.luislorenzom.lubel.parsers;


import java.io.IOException;
import java.io.InputStream;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.jsoup.Jsoup;
import com.github.luislorenzom.lubel.exceptions.DocumentHandlerException;

public class JsoupHTMLHandler implements DocumentHandler {

	public Document getDocument(InputStream is) throws DocumentHandlerException {
		try {
			org.jsoup.nodes.Document htmlDoc = Jsoup.parse(is,null,null);
			// Get full text
			String plainText = htmlDoc.select("body").text();
			Document doc = new Document();
			// Index the content
			doc.add(new TextField("content", plainText, Field.Store.YES));
			return doc;
		} catch (IOException e) {
			throw new DocumentHandlerException();
		} 
	}
}

