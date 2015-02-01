package com.epam.lab.news.services.exception;

import com.epam.lab.news.controller.exception.RESTErrorCode;

/**
 *	Service level exception
 */
public class ServiceException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	private RESTErrorCode errorCode = RESTErrorCode.INTERNAL;

	public ServiceException() {
		super();
	}
	
	public ServiceException(String errorMessage, Throwable e, RESTErrorCode errorCode) {
		super(errorMessage, e);
		this.errorCode = errorCode;

	}
	
	public ServiceException(String errorMessage, RESTErrorCode errorCode) {
		super(errorMessage);
		this.errorCode = errorCode;
	}

	public ServiceException(String errorMessage, Throwable e) {
		super(errorMessage, e);

	}

	public ServiceException(String errorMessage) {
		super(errorMessage);

	}

	public ServiceException(Throwable e) {
		super(e);

	}
	
	public RESTErrorCode getErrorCode() {
		return errorCode;
	}
}
