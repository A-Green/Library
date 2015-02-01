package com.epam.lab.news.model;

/**
 * Contains information, that will be sent
 * to client in case of error
 */
public class ErrorReport {
	
	/**
	 * Should be one of HTTP response status codes
	 */
	private int errorCode;
	private String errorMessage;
	
	public ErrorReport(String errorMessage, int errorCode) {
		this.errorMessage = errorMessage;
		this.errorCode = errorCode;
	}
	
	public int getErrorCode() {
		return errorCode;
	}
	
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}
	
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	public String toString() {
		return "code: " + errorCode + "\n message:" + errorMessage;
	}
}
