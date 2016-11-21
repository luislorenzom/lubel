package com.github.luislorenzom.lubel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class FileIndexerFactory {
	
	public static FileIndexer getFileIndexer() {
		try {
			Properties props = new Properties();
			props.load(new FileInputStream("src/main/java/com/github/luislorenzom/lubel/handler.properties"));
			return new FileIndexer(props);
		} catch (FileNotFoundException e) {
			System.err.println("Cannot return a FileIndexer instance");
		} catch (IOException e) {
			System.err.println("Cannot return a FileIndexer instance");
		}
		return null;
	}
}
