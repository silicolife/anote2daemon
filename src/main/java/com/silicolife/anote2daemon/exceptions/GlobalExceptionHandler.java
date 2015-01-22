package com.silicolife.anote2daemon.exceptions;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.silicolife.anote2daemon.exceptions.pojo.ExceptionInfo;
import com.silicolife.anote2daemon.webservice.DaemonResponse;

/**
 * Generic class to manage exceptions generate by anote2daemon. All exceptions
 * are send to user in JSON Format
 * 
 * @author Joel Azevedo Costa
 *
 */
@ControllerAdvice
public class GlobalExceptionHandler {

	/**
	 * Send data integration exceptions
	 * 
	 * @param e
	 * @return
	 */
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<DaemonResponse<?>> handlingException(DataIntegrityViolationException e) {
		DaemonResponse<?> response = new DaemonResponse<>();
		ExceptionInfo exception = new ExceptionInfo(ExceptionsCodes.keyConstraintCode, e.getMessage(), e.getRootCause().getMessage());
		response.setException(exception);
		return new ResponseEntity<DaemonResponse<?>>(response, HttpStatus.CONFLICT);
	}

	/**
	 * Send JSON format exceptions
	 * 
	 * @param e
	 * @return
	 */
	@ExceptionHandler(InvalidFormatException.class)
	public ResponseEntity<DaemonResponse<?>> handlingException(InvalidFormatException e) {
		DaemonResponse<?> response = new DaemonResponse<>();
		ExceptionInfo exception = new ExceptionInfo(ExceptionsCodes.parseJsonCode, e.getMessage(), e.getCause().getMessage());
		response.setException(exception);
		return new ResponseEntity<DaemonResponse<?>>(response, HttpStatus.BAD_REQUEST);
	}

	/**
	 * Send generic exceptions
	 * 
	 * @param e
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<DaemonResponse<?>> handlingException(Exception e) {
		DaemonResponse<?> response = new DaemonResponse<>();
		ExceptionInfo exception = new ExceptionInfo(ExceptionsCodes.generalCode, e.getMessage(), e.getCause().getMessage());
		response.setException(exception);
		return new ResponseEntity<DaemonResponse<?>>(response, HttpStatus.NOT_IMPLEMENTED);
	}
}