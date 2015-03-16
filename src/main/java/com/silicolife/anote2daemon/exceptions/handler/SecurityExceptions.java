package com.silicolife.anote2daemon.exceptions.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.silicolife.anote2daemon.exceptions.ExceptionsCodes;
import com.silicolife.anote2daemon.exceptions.entities.ExceptionInfo;
import com.silicolife.anote2daemon.webservice.DaemonResponse;

/**
 * 
 * Generic class to handler with security exceptions (access a methods secured
 * by roles) generate by spring
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
	public ResponseEntity<DaemonResponse<?>> handleException(AccessDeniedException e) {

		String rootCause = null;
		String message = e.getMessage();
		Throwable cause = e.getCause();
		if (cause != null)
			rootCause = cause.getMessage();

		ExceptionInfo exception = new ExceptionInfo(ExceptionsCodes.accessDeniedCode, message, rootCause);
		DaemonResponse<?> response = new DaemonResponse<>();
		response.setException(exception);
		return new ResponseEntity<DaemonResponse<?>>(response,HttpStatus.UNAUTHORIZED);
	}
}