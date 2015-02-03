package com.silicolife.anote2daemon.exceptions.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.silicolife.anote2daemon.exceptions.ExceptionsCodes;
import com.silicolife.anote2daemon.exceptions.pojo.ExceptionInfo;

/**
 * Generic class to handler with general exceptions generate by anote2daemon.
 * 
 * @author Joel Azevedo Costa
 * @year 2015
 *
 */
@ControllerAdvice
public class GlobalExceptionHandler {

	/**
	 * Method to handler with null pointer exceptions
	 * 
	 * @param e
	 * @return
	 */
	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<ExceptionInfo> handleException(NullPointerException e) {
		String rootCause = null;
		String message = e.getMessage();
		Throwable cause = e.getCause();
		if (cause != null)
			rootCause = cause.getMessage();

		ExceptionInfo exception = new ExceptionInfo(ExceptionsCodes.nullPointerCode, message, rootCause);
		return new ResponseEntity<ExceptionInfo>(exception, HttpStatus.NOT_IMPLEMENTED);
	}

	/**
	 * JSON format exception
	 * 
	 * @param e
	 * @return
	 */
	@ExceptionHandler(InvalidFormatException.class)
	public ResponseEntity<ExceptionInfo> handleException(InvalidFormatException e) {
		String rootCause = null;
		String message = e.getMessage();
		Throwable cause = e.getCause();
		if (cause != null)
			rootCause = cause.getMessage();

		ExceptionInfo exception = new ExceptionInfo(ExceptionsCodes.parseJsonCode, message, rootCause);
		return new ResponseEntity<ExceptionInfo>(exception, HttpStatus.BAD_REQUEST);
	}

	/**
	 * General Exceptions
	 * 
	 * @param e
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ExceptionInfo> handleException(Exception e) {
		String rootCause = null;
		String message = e.getMessage();
		Throwable cause = e.getCause();
		if (cause != null)
			rootCause = cause.getMessage();

		ExceptionInfo exception = new ExceptionInfo(ExceptionsCodes.generalCode, message, rootCause);
		return new ResponseEntity<ExceptionInfo>(exception, HttpStatus.BAD_REQUEST);
	}
}