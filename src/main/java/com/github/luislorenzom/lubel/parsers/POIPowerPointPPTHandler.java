package com.github.luislorenzom.lubel.parsers;

import java.io.IOException;
import java.io.InputStream;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.poi.hslf.HSLFSlideShow;
import org.apache.poi.hslf.model.Slide;
import org.apache.poi.hslf.model.TextRun;
import org.apache.poi.hslf.usermodel.SlideShow;

import com.github.luislorenzom.lubel.exceptions.DocumentHandlerException;

public class POIPowerPointPPTHandler implements DocumentHandler {

	public Document getDocument(InputStream is) throws DocumentHandlerException {
		try {
			HSLFSlideShow ppt = new HSLFSlideShow(is);
			SlideShow document = new SlideShow(ppt);
			String fileData = "";
			Slide[] slides = document.getSlides();
			
			for (Slide slide : slides) {
				fileData += slide.getTitle();
				fileData += "\n";
				TextRun[] textRuns = slide.getTextRuns();
				for (TextRun textRun : textRuns) {
					fileData += textRun.getText();
				}
			}
			
			Document doc = new Document();
			doc.add(new TextField("content", fileData, Field.Store.YES));
			return doc;
		} catch (IOException e) {
			throw new DocumentHandlerException("Cannot parse ppt document", e);
		}
	}

}
