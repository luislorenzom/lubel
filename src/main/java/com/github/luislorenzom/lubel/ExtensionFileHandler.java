package com.github.luislorenzom.lubel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.lucene.document.Document;

import com.github.luislorenzom.lubel.exceptions.DocumentHandlerException;
import com.github.luislorenzom.lubel.exceptions.FileHandlerException;
import com.github.luislorenzom.lubel.parsers.DocumentHandler;


/**
 * A FileHandler implementation that delegates responsibility to
 * appropriate DocumentHandler implementations, based on a file
 * extension.
 */
public class ExtensionFileHandler implements FileHandler {
	private Properties handlerProps;
	
	
	public ExtensionFileHandler(Properties props) throws IOException {
		// Map file extension
		handlerProps = props;
	}
	
	public Document getDocument(File file) throws FileHandlerException {
		String name = file.getName();
		int dotIndex = name.indexOf(".");
		
		if ((dotIndex > 0) && (dotIndex < name.length())) {
			// Extract filename extension
			String ext = name.substring(dotIndex + 1, name.length());
			String handlerClassName = handlerProps.getProperty(ext);
			
			try {
				Class<?> handlerClass = Class.forName(handlerClassName);
				DocumentHandler handler = (DocumentHandler) handlerClass.newInstance();
				return handler.getDocument(new FileInputStream(file));
			} catch (ClassNotFoundException e) {
				throw new FileHandlerException("Cannot create instance of: " + handlerClassName,e);
			} catch (InstantiationException e) {
				throw new FileHandlerException("Cannot create instance of: " + handlerClassName,e);
			} catch (IllegalAccessException e) {
				throw new FileHandlerException("Cannot create instance of: " + handlerClassName,e);
			} catch (FileNotFoundException e) {
				throw new FileHandlerException("File not found: " + file.getAbsolutePath(),e);
			} catch (DocumentHandlerException e) {
				throw new FileHandlerException("Document cannot be handler: " + file.getAbsolutePath(), e);
			}
		}
		return null;
	}

}