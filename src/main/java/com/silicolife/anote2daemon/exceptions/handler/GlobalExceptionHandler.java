package com.silicolife.anote2daemon.exceptions.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import pt.uminho.anote2.datastructures.dataaccess.daemon.webserviceclient.ExceptionInfo;
import pt.uminho.anote2.datastructures.dataaccess.database.dataaccess.implementation.exceptions.general.ExceptionsCodes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.silicolife.anote2daemon.webservice.DaemonResponse;

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
	//@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<DaemonResponse<?>> handleException(NullPointerException e) {
		String rootCause = null;
		String message = e.getMessage();
		Throwable cause = e.getCause();
		if (cause != null)
			rootCause = cause.getMessage();

		ExceptionInfo exception = new ExceptionInfo(ExceptionsCodes.nullPointerCode, message, rootCause);
		DaemonResponse<?> response = new DaemonResponse<>();
		response.setException(exception);
		return new ResponseEntity<DaemonResponse<?>>(response, HttpStatus.NOT_IMPLEMENTED);
	}

	/**
	 * JSON format exception
	 * 
	 * @param e
	 * @return
	 */
	@ExceptionHandler(JsonProcessingException.class)
	public ResponseEntity<DaemonResponse<?>> handleException(JsonProcessingException e) {
		String rootCause = null;
		String message = e.getMessage();
		Throwable cause = e.getCause();
		if (cause != null)
			rootCause = cause.getMessage();

		ExceptionInfo exception = new ExceptionInfo(ExceptionsCodes.parseJsonCode, message, rootCause);
		DaemonResponse<?> response = new DaemonResponse<>();
		response.setException(exception);
		return new ResponseEntity<DaemonResponse<?>>(response, HttpStatus.BAD_REQUEST);
	}

	/**
	 * General Exceptions
	 * 
	 * @param e
	 * @return
	 */
	//@ExceptionHandler(Exception.class)
	public ResponseEntity<DaemonResponse<?>> handleException(Exception e) {
		String rootCause = null;
		String message = e.getMessage();
		Throwable cause = e.getCause();
		if (cause != null)
			rootCause = cause.getMessage();

		ExceptionInfo exception = new ExceptionInfo(ExceptionsCodes.generalCode, message, rootCause);
		DaemonResponse<?> response = new DaemonResponse<>();
		response.setException(exception);
		return new ResponseEntity<DaemonResponse<?>>(response, HttpStatus.BAD_REQUEST);
	}
}