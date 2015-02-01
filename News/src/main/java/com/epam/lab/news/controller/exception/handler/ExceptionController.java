package com.epam.lab.news.controller.exception.handler;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.epam.lab.news.constants.AppMessages;
import com.epam.lab.news.controller.exception.ControllerException;
import com.epam.lab.news.controller.exception.RESTErrorCode;
import com.epam.lab.news.model.ErrorReport;

/**
 * Application exceptions handler
 */
@ControllerAdvice
public class ExceptionController {

	private static final Logger LOGGER = Logger.getLogger(ExceptionController.class);
	
	/**
	 * Creates error report from ControllerException and responses it to client with
	 * appropriate response status
	 * @param e ControllerException
	 * @param response HttpServletResponse
	 * @return created ErrorReport
	 */
	@ExceptionHandler(ControllerException.class)
	public @ResponseBody 
	ErrorReport handleControllerException(ControllerException e, HttpServletResponse response) {
		LOGGER.error(e.getMessage());
		response.setStatus(e.getErrorCode().getStatusCode());
		return new ErrorReport(e.getMessage(), e.getErrorCode().getStatusCode());
	}
	
	/**
	 * Creates error report from MethodArgumentNotValidException and responses it to client
	 * @param e MethodArgumentNotValidException
	 * @return created ErrorReport
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public @ResponseBody 
	ErrorReport handleValidationException(MethodArgumentNotValidException e) {
		
		List<ObjectError> errors = e.getBindingResult().getAllErrors();
		int errorCount = errors.size();
		
		String errorMessage = "";
		
		for (int i = 0; i < errorCount; i++ ) {
			errorMessage += errors.get(i).getDefaultMessage() 
					+ ((i + 1  == errorCount)? "" : ", ");
		}
		
		LOGGER.error(errorMessage);
		return new ErrorReport(errorMessage, RESTErrorCode.INVALID_REQUEST.getStatusCode());
	}
	
	/**
	 * Creates error report from RuntimeException and responses it to client with
	 * appropriate response status
	 * @param e RuntimeException
	 * @param response HttpServletResponse
	 * @return created ErrorReport
	 */
	@ExceptionHandler(RuntimeException.class)
	public @ResponseBody 
	ErrorReport handleRuntimeException(RuntimeException e, HttpServletResponse response) {
		
		response.setStatus(RESTErrorCode.INTERNAL.getStatusCode());

		if (e instanceof DataAccessException) {
			LOGGER.error(AppMessages.DATABASE_ACCESS_ERROR, e);
			return new ErrorReport(AppMessages.DATABASE_ACCESS_ERROR,
					RESTErrorCode.INTERNAL.getStatusCode());
		}
		
		if (e instanceof HttpMessageNotReadableException) {
			response.setStatus(RESTErrorCode.INVALID_REQUEST.getStatusCode());
			LOGGER.error(AppMessages.SYNTAX_ERROR);
			return new ErrorReport(AppMessages.SYNTAX_ERROR,
					RESTErrorCode.INVALID_REQUEST.getStatusCode());
		}

		LOGGER.error(AppMessages.INTERNAL_SERVER_ERROR, e);
		return new ErrorReport(AppMessages.INTERNAL_SERVER_ERROR,
				RESTErrorCode.INTERNAL.getStatusCode());
	}
}
