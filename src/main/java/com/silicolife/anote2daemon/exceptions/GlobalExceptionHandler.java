package com.silicolife.anote2daemon.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.silicolife.anote2daemon.exceptions.pojo.ExceptionInfo;
import com.silicolife.anote2daemon.webservice.DaemonResponse;

/**
 * Generic class to handler with general exceptions generate by anote2daemon.
 * All exceptions are send to user in JSON Format
 * 
 * @author Joel Azevedo Costa
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
	public ResponseEntity<DaemonResponse<?>> handleGeneralException(NullPointerException e) {
		DaemonResponse<?> response = new DaemonResponse<>();
		String message = null;
		String rootCause = null;

		if (e.getMessage() != null)
			message = e.getMessage();
		if (e.getCause() != null)
			rootCause = e.getCause().getMessage();

		ExceptionInfo exception = new ExceptionInfo(ExceptionsCodes.nullPointerCode, message, rootCause);
		response.setException(exception);
		return new ResponseEntity<DaemonResponse<?>>(response, HttpStatus.NOT_IMPLEMENTED);
	}

	/**
	 * Method to handler with JSON formatter exceptions
	 * 
	 * @param e
	 * @return
	 */
	@ExceptionHandler(InvalidFormatException.class)
	public ResponseEntity<DaemonResponse<?>> handleGeneralException(InvalidFormatException e) {
		DaemonResponse<?> response = new DaemonResponse<>();
		String message = null;
		String rootCause = null;

		if (e.getMessage() != null)
			message = e.getMessage();
		if (e.getCause() != null)
			rootCause = e.getCause().getMessage();

		ExceptionInfo exception = new ExceptionInfo(ExceptionsCodes.parseJsonCode, message, rootCause);
		response.setException(exception);
		return new ResponseEntity<DaemonResponse<?>>(response, HttpStatus.BAD_REQUEST);
	}
}