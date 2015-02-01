package com.epam.lab.news.controller.exception;
/**
 *	Controller level exception
 */
public class ControllerException extends Exception {

	private static final long serialVersionUID = 1L;
	
	private RESTErrorCode errorCode = RESTErrorCode.INTERNAL;
	
	public RESTErrorCode getErrorCode() {
		return errorCode;
	}

	public ControllerException() {
		super();
	}
	
	public ControllerException(String errorMessage, Throwable e, RESTErrorCode errorCode) {
		super(errorMessage, e);
		this.errorCode = errorCode;

	}
	
	public ControllerException(String errorMessage, RESTErrorCode errorCode) {
		super(errorMessage);
		this.errorCode = errorCode;
	}

	public ControllerException(String errorMessage, Throwable e) {
		super(errorMessage, e);

	}

	public ControllerException(String errorMessage) {
		super(errorMessage);

	}

	public ControllerException(Throwable e) {
		super(e);

	}
}
