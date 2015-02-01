package com.epam.lab.news.constants;
/**
 *	Application constants
 */
public class AppMessages {
	
	public static final String VALIDATION_TITLE_EMPTY = "News title required";
	public static final String VALIDATION_TITLE_LARGE = 
			"News title must be less or equals than 100 symbols";
	public static final String VALIDATION_DATE_EMPTY = "News date required";
	public static final String VALIDATION_BRIEF_EMPTY = "News brief required";
	public static final String VALIDATION_BRIEF_LARGE = 
			"News brief must be less or equals than 500 symbols";
	public static final String VALIDATION_CONTENT_EMPTY = "News content required";
	public static final String VALIDATION_CONTENT_LARGE = 
			"News content must be less or equals than 2048 symbols";	
	public static final String VALIDATION_INVALID_TAG = 
			"News tag length must be from 2 to 50 symbols";
	public static final String VALIDATION_INVALID_COMMENT = 
			"Comment length must be from 2 to 500 symbols";
	public static final String VALIDATION_INVALID_AUTHOR_NAME = 
			"Author name length must be from 2 to 15 symbols";
	public static final String VALIDATION_DUPLICATED_TAGS = 
			"Duplicated tags";
	public static final String VALIDATION_DUPLICATED_AUTHORS = 
			"Duplicated authors";
	public static final String SYNTAX_ERROR = 
			"Can not read json message";
	public static final String ERROR_NEWS_MESSAGE_NOT_FOUND = 
			"Required news message not found";	
	public static final String ERROR_COMMENT_NOT_FOUND = 
			"Required comment not found";	
	public static final String ERROR_NEWS_MESSAGE_NOT_FOUND_WHILE_DELETING = 
			"Can not delete news message. Required message not found";
	public static final String ERROR_FETCHING_BY_COMMENTS = 
			"Invalid amount parameter";
	public static final String ERROR_ADDING_TAG = 
			"Tag already exists";
	public static final String ERROR_ADDING_AUTHOR = 
			"Author already exists";
	
	public static final String INTERNAL_SERVER_ERROR = "Internal server error";
	public static final String DATABASE_ACCESS_ERROR = "Database access error";
	
	public static final String XML_LOAD_TIMEOUT_ERROR = "XML load timeout error";
}
