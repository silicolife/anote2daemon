package com.silicolife.anote2daemon.exceptions.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.silicolife.anote2daemon.exceptions.ExceptionsCodes;
import com.silicolife.anote2daemon.exceptions.entities.ExceptionInfo;

/**
 * 
 * Generic class to handler with security exceptions generate by spring
 * 
 * 
 * @author Joel Azevedo Costa
 * @year 2015
 *
 */

@ControllerAdvice
public class SecurityExceptions {

	/**
	 * Access denied (roles) exceptions
	 * 
	 * @param e
	 * @return
	 */
	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<ExceptionInfo> handleException(AccessDeniedException e) {
		String rootCause = null;
		String message = e.getMessage();
		Throwable cause = e.getCause();
		if (cause != null)
			rootCause = cause.getMessage();

		ExceptionInfo exception = new ExceptionInfo(ExceptionsCodes.accessDeniedCode, message, rootCause);
		return new ResponseEntity<ExceptionInfo>(exception, HttpStatus.UNAUTHORIZED);
	}

	/**
	 * Bad credentials exceptions
	 * 
	 * @param e
	 * @return
	 */
	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<ExceptionInfo> handleException(BadCredentialsException e) {
		String rootCause = null;
		String message = e.getMessage();
		Throwable cause = e.getCause();
		if (cause != null)
			rootCause = cause.getMessage();

		ExceptionInfo exception = new ExceptionInfo(ExceptionsCodes.badCredentialsCode, message, rootCause);
		return new ResponseEntity<ExceptionInfo>(exception, HttpStatus.UNAUTHORIZED);
	}
}
