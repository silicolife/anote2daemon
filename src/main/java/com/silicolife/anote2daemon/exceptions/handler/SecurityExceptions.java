package com.silicolife.anote2daemon.exceptions.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.silicolife.anote2daemon.exceptions.PrivilegesDaemonException;
import com.silicolife.anote2daemon.webservice.DaemonResponse;
import com.silicolife.textmining.core.datastructures.dataaccess.daemon.webserviceclient.ExceptionInfo;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.exceptions.PrivilegesException;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.exceptions.general.ExceptionsCodes;

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
		return new ResponseEntity<DaemonResponse<?>>(response, HttpStatus.UNAUTHORIZED);
	}

	/**
	 * Wrong authentication
	 * 
	 * @param e
	 * @return
	 */
	@ExceptionHandler(AuthenticationException.class)
	public ResponseEntity<DaemonResponse<?>> handleException(AuthenticationException e) {

		String rootCause = null;
		String message = e.getMessage();
		Throwable cause = e.getCause();
		if (cause != null)
			rootCause = cause.getMessage();

		ExceptionInfo exception = new ExceptionInfo(ExceptionsCodes.codeWrongCredentials, message, rootCause);
		DaemonResponse<?> response = new DaemonResponse<>();
		response.setException(exception);
		return new ResponseEntity<DaemonResponse<?>>(response, HttpStatus.UNAUTHORIZED);
	}

	/**
	 * Handler with privileges (Permission denied)
	 * 
	 * @param e
	 * @return
	 */
	@ExceptionHandler(PrivilegesException.class)
	public ResponseEntity<DaemonResponse<?>> handleException(PrivilegesException e) {

		String code = e.getCode();
		String rootCause = null;
		String message = e.getMessage();
		Throwable cause = e.getCause();
		if (cause != null)
			rootCause = cause.getMessage();

		ExceptionInfo exception = new ExceptionInfo(code, message, rootCause);
		DaemonResponse<?> response = new DaemonResponse<>();
		response.setException(exception);
		return new ResponseEntity<DaemonResponse<?>>(response, HttpStatus.UNAUTHORIZED);
	}
	
	/**
	 * Handler with privileges from RestPermissionsEvaluator (hasPermission method)
	 * 
	 * @param e
	 * @return
	 */
	@ExceptionHandler(PrivilegesDaemonException.class)
	public ResponseEntity<DaemonResponse<?>> handleException(PrivilegesDaemonException e) {

		String code = e.getCode();
		String rootCause = null;
		String message = e.getMessage();
		Throwable cause = e.getCause();
		if (cause != null)
			rootCause = cause.getMessage();

		ExceptionInfo exception = new ExceptionInfo(code, message, rootCause);
		DaemonResponse<?> response = new DaemonResponse<>();
		response.setException(exception);
		return new ResponseEntity<DaemonResponse<?>>(response, HttpStatus.UNAUTHORIZED);
	}
}