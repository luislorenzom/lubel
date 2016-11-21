package com.github.luislorenzom.lubel.parsers;


import java.io.IOException;
import java.io.InputStream;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;

import com.github.luislorenzom.lubel.exceptions.DocumentHandlerException;

public class POIWordDocHandler implements DocumentHandler {
	
	public Document getDocument(InputStream is) throws DocumentHandlerException {
		try {
			HWPFDocument document = new HWPFDocument(is);
			WordExtractor extractor = new WordExtractor(document);
			String fileData = extractor.getText();
			
			Document doc = new Document();
			doc.add(new TextField("content", fileData, Field.Store.YES));
			return doc;
		} catch (IOException e) {
			throw new DocumentHandlerException("Cannot parse DOC document", e);
		}
	}
}
