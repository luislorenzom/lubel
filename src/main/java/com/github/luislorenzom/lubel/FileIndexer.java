package com.github.luislorenzom.lubel;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexWriter;

import com.github.luislorenzom.lubel.exceptions.FileHandlerException;


/**
 * A File Indexer capable of recursively indexing a directory tree.
 */
public class FileIndexer {
	protected FileHandler fileHandler;
	
	public FileIndexer(Properties props) throws IOException {
		fileHandler = new ExtensionFileHandler(props);
	}
	
	/** Index method */
	public void index(IndexWriter writer, File file) throws FileHandlerException {
		
		// Traverse readable directories recursively
		if (file.canRead()) {
			if (file.isDirectory()) {
				String[] files = file.list();
				for (int i = 0; i < file.length(); i++) {
					index(writer, new File(file, files[i]));
				}
			} else {
				System.out.println("Indexing " + file);
				try {
					Document doc = fileHandler.getDocument(file);
					// Hands of files to ExtensionFileHandler
					if (doc != null) {
						// Add returned Lucene Document to index
						writer.addDocument(doc);
					} else {
						System.err.println("Cannot handle " + file.getAbsolutePath()
							+ "; skipping");
					}
				} catch (IOException e) {
					System.err.println("Cannot handle " + file.getAbsolutePath()
					+ "; skipping");
				}
			}
		}
	}
}