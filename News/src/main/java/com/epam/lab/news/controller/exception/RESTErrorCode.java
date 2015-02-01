package com.epam.lab.news.controller.exception;


/**
 * REST error codes holder
 */
public enum RESTErrorCode {
	
	INTERNAL(500), RESOURCE_NOT_FOUND(404), INVALID_REQUEST(400);
	
	private RESTErrorCode(int associatedHttpStatus) {
		this.httpStatusCode = associatedHttpStatus;
	}
	
	/**
	 * Associated HTTP status
	 * @return
	 */
	private int httpStatusCode;

	/**
	 * Get associated HTTP status
	 * @return
	 */
	public int getStatusCode() {
		return  httpStatusCode;
	}
}
