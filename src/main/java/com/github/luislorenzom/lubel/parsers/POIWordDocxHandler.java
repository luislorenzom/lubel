package com.github.luislorenzom.lubel.parsers;

import java.io.IOException;
import java.io.InputStream;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

import com.github.luislorenzom.lubel.exceptions.DocumentHandlerException;

public class POIWordDocxHandler implements DocumentHandler{

	public Document getDocument(InputStream is) throws DocumentHandlerException {
		XWPFDocument document;
		try {
			document = new XWPFDocument(is);
			XWPFParagraph[] paragraphs = document.getParagraphs();
			String fileData = "";
			
			for (XWPFParagraph paragraph : paragraphs) {
				fileData += paragraph.getText();
			}
			
			Document doc = new Document();
			doc.add(new TextField("content", fileData, Field.Store.YES));
			return doc;
		} catch (IOException e) {
			throw new DocumentHandlerException("Cannot parse Docx document", e); 
		}
	}
}
