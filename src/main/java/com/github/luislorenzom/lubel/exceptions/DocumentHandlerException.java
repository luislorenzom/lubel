package com.github.luislorenzom.lubel.exceptions;

public class DocumentHandlerException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public DocumentHandlerException() {
		super();
	}
	
	public DocumentHandlerException(String msg, Exception e) {
		super(msg);
	}
}
