package com.github.luislorenzom.lubel.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import com.github.luislorenzom.lubel.FileIndexer;
import com.github.luislorenzom.lubel.FileIndexerFactory;
import com.github.luislorenzom.lubel.exceptions.FileHandlerException;

public class SimpleIndexer {
	
	public int index(File indexDir, File dataDir) throws IOException, FileHandlerException {
		
		if (!dataDir.exists() || !dataDir.isDirectory()) {
			throw new IOException(dataDir + " does not exist or is not a directory");
		}
		
		// Create a Lucene index
		FSDirectory directory = FSDirectory.open(indexDir);
		Analyzer analyzer = new StandardAnalyzer();
		IndexWriterConfig config = new IndexWriterConfig(Version.LATEST, analyzer);
		config.setOpenMode(IndexWriterConfig.OpenMode.CREATE);
		
		IndexWriter indexWriter = new IndexWriter(directory, config);
		indexDirectory(indexWriter, dataDir);
		
		int numIndexed = indexWriter.numDocs();
		indexWriter.close();
		return numIndexed;
	}
	
	public void indexDirectory(IndexWriter writer, File dataDir) 
			throws FileNotFoundException, IOException, FileHandlerException {
		File[] files = dataDir.listFiles();
		FileIndexer fileIndexer = FileIndexerFactory.getFileIndexer();
		
		for (File file : files) {
			if (file.isDirectory()) {
				indexDirectory(writer, file);
			} else {
				if ((file.getName().endsWith(".txt")) 
						|| (file.getName().endsWith(".pdf")) 
						|| (file.getName().endsWith(".html"))) {
					fileIndexer.index(writer, file);
				}
			}
		}
	}
	
}
