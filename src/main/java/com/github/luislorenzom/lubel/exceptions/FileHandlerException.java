package com.github.luislorenzom.lubel.exceptions;

public class FileHandlerException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public FileHandlerException() {
		super();
	}
	
	public FileHandlerException(String msg, Exception e) {
		super(msg);
	}
}
