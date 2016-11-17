package com.github.luislorenzom.lubel.parsers;

import java.io.IOException;
import java.io.InputStream;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

import com.github.luislorenzom.lubel.exceptions.DocumentHandlerException;

public class PDFBoxPDFHandler implements DocumentHandler {

	public Document getDocument(InputStream is) throws DocumentHandlerException {
		PDFTextStripper pdfTextStripper = null;
		PDDocument pdDoc = null;
		COSDocument cosDoc = null;
		try {
			// Parse the PDF file to obtain the text
			PDFParser parser = new PDFParser(is);
			parser.parse();
			cosDoc = parser.getDocument();
			pdfTextStripper = new PDFTextStripper();
			pdDoc = new PDDocument(cosDoc);
			pdfTextStripper.setStartPage(0);
			String parsedText = pdfTextStripper.getText(pdDoc);
			Document doc = new Document();
			// Index the content
			doc.add(new TextField("content", parsedText, Field.Store.YES));
			return doc;
		} catch (IOException e) {
			throw new DocumentHandlerException("Cannot parse PDF document", e);
		}
	}

}
