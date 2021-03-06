package com.silicolife.anote2daemon.exceptions.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.silicolife.anote2daemon.webservice.DaemonResponse;
import com.silicolife.textmining.core.datastructures.dataaccess.daemon.webserviceclient.ExceptionInfo;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.exceptions.general.DataException;
import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.DaemonException;

/**
 * 
 * Generic class to handler with all custom exceptions generated by
 * anote2daemon. For example, DameonQueriesException
 * 
 * 
 * @author Joel Azevedo Costa
 * @year 2015
 */
@ControllerAdvice
public class DaemonDataException {

	/**
	 * 
	 * Daemon Exceptions
	 * 
	 * @param e
	 * @return
	 */
	@ExceptionHandler(DaemonException.class)
	public ResponseEntity<DaemonResponse<?>> handleException(DaemonException e) {

		String code = e.getCode();
		String rootCause = null;
		String message = e.getMessage();
		Throwable cause = e.getCause();
		if (cause != null)
			rootCause = cause.getMessage();

		ExceptionInfo exception = new ExceptionInfo(code, message, rootCause);
		DaemonResponse<?> response = new DaemonResponse<>();
		response.setException(exception);
		return new ResponseEntity<DaemonResponse<?>>(response, HttpStatus.NOT_ACCEPTABLE);
	}

	/**
	 * Handler with data exception
	 * 
	 * @param e
	 * @return
	 */
	@ExceptionHandler(DataException.class)
	public ResponseEntity<DaemonResponse<?>> handleException(DataException e) {

		String code = e.getCode();
		String rootCause = null;
		String message = e.getMessage();
		Throwable cause = e.getCause();
		if (cause != null)
			rootCause = cause.getMessage();

		ExceptionInfo exception = new ExceptionInfo(code, message, rootCause);
		DaemonResponse<?> response = new DaemonResponse<>();
		response.setException(exception);
		return new ResponseEntity<DaemonResponse<?>>(response, HttpStatus.NOT_ACCEPTABLE);
	}
}
