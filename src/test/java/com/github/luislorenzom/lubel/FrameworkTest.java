package com.github.luislorenzom.lubel;


import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

import org.apache.lucene.queryparser.classic.ParseException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.github.luislorenzom.lubel.exceptions.FileHandlerException;
import com.github.luislorenzom.lubel.utils.SimpleIndexer;
import com.github.luislorenzom.lubel.utils.SimpleSearcher;


public class FrameworkTest {
	
	private static FileOutputStream fos;

	@BeforeClass
	public static void getFilesToIndex() throws IOException {
		// Folder creation
		String folder = "tmp/";
		File dir = new File(folder);
		
		if (!dir.exists()) {
			if(!dir.mkdirs()) {
				return;
			}
		}
		
		// Download examples to index
		URL PDF = new URL("http://www.pdf995.com/samples/pdf.pdf");
		ReadableByteChannel rbc = Channels.newChannel(PDF.openStream());
		fos = new FileOutputStream("tmp/pdf.pdf");
		fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
		
		URL TXT = new URL("https://wordpress.org/plugins/about/readme.txt");
		rbc = Channels.newChannel(TXT.openStream());
		fos = new FileOutputStream("tmp/readme.txt");
		fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
		
		URL HTML = new URL("https://es.wikipedia.org/wiki/HTML");
		rbc = Channels.newChannel(HTML.openStream());
		fos = new FileOutputStream("tmp/HTML.html");
		fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
		
		URL DOC = new URL("http://www.snee.com/xml/xslt/sample.doc");
		rbc = Channels.newChannel(DOC.openStream());
		fos = new FileOutputStream("tmp/sample.doc");
		fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
	}
	
	@AfterClass
	public static void deleteIndexedFilesAndIndex() {
		// Finally, tmp folder is delete
		File tmpFolder = new File("tmp/");
		File[] files = tmpFolder.listFiles();
		
		for (File file : files) {
			file.delete();
		}
		
		tmpFolder.delete();
	}
	
	@Test
	public void testIndexAndSearchFilesTest() throws IOException, FileHandlerException, ParseException {
		SimpleIndexer indexer = new SimpleIndexer();
		SimpleSearcher searcher = new SimpleSearcher();
		File indexDir = new File("tmp/"); 
		File dataDir = new File("tmp/");
		
		int numberOfFilesIndexed = indexer.index(indexDir, dataDir);
		
		assertEquals(numberOfFilesIndexed, 4);
		
		// Some random queries
		String q1 = "TCP/IP infrastructure which allowed";
		String q2 = "without having that information incorrectly";
		String q3 = "sistema que facilitaba la lectura de información";
		String q4 = "Heading1";
		
		int scores = searcher.search(indexDir, q1);
		assertEquals(2, scores);
		
		scores = searcher.search(indexDir, q2);
		assertEquals(2, scores);
		
		scores = searcher.search(indexDir, q3);
		assertEquals(1, scores);
		
		scores = searcher.search(indexDir, q4);
		assertEquals(1, scores);
	}
}