package com.silicolife.anote2daemon.exceptions.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.silicolife.anote2daemon.exceptions.DaemonQueriesException;
import com.silicolife.anote2daemon.exceptions.pojo.ExceptionInfo;

/**
 * 
 * Generic class to handler with all custom exceptions. For example,
 * DameonQueriesException
 * 
 * 
 * @author Joel Azevedo Costa
 * @year 2015
 */
@ControllerAdvice
public class DaemonDataException {

	/**
	 * 
	 * DaemonQueries Exceptions
	 * 
	 * @param e
	 * @return
	 */
	@ExceptionHandler(DaemonQueriesException.class)
	public ResponseEntity<ExceptionInfo> handleException(DaemonQueriesException e) {

		String code = e.getCode();
		String rootCause = null;
		String message = e.getMessage();
		Throwable cause = e.getCause();
		if (cause != null)
			rootCause = cause.getMessage();

		ExceptionInfo exception = new ExceptionInfo(code, message, rootCause);
		return new ResponseEntity<ExceptionInfo>(exception, HttpStatus.FORBIDDEN);
	}

}
