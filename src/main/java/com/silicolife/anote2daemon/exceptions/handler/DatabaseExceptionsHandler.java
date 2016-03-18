package com.silicolife.anote2daemon.exceptions.handler;

import org.hibernate.HibernateException;
import org.hibernate.PropertyValueException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.silicolife.anote2daemon.webservice.DaemonResponse;
import com.silicolife.textmining.core.datastructures.dataaccess.daemon.webserviceclient.ExceptionInfo;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.exceptions.general.ExceptionsCodes;

/**
 * Generic class to handler with database exceptions generated by anote2daemon
 * 
 * @author Joel Azevedo Costa
 * @year 2015
 *
 */
@ControllerAdvice
public class DatabaseExceptionsHandler {

	
	/**
	 * Constraint violation exceptions
	 * 
	 * @param e
	 * @return
	 */
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<DaemonResponse<?>> handleException(ConstraintViolationException e) {
		String rootCause = null;
		String message = e.getMessage();
		Throwable cause = e.getCause();
		if (cause != null)
			rootCause = cause.getMessage();
		
		ExceptionInfo exception = new ExceptionInfo(ExceptionsCodes.codeConstraint, message, rootCause);
		DaemonResponse<?> response = new DaemonResponse<>();
		response.setException(exception);
		return new ResponseEntity<DaemonResponse<?>>(response, HttpStatus.CONFLICT);
	}

	/**
	 * DataIntegration violation exceptions
	 * 
	 * @param e
	 * @return
	 */
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<DaemonResponse<?>> handleException(DataIntegrityViolationException e) {
		String rootCause = null;
		String message = e.getMessage();
		Throwable cause = e.getCause();
		if (cause != null)
			rootCause = cause.getMessage();
		
		ExceptionInfo exception = new ExceptionInfo(ExceptionsCodes.codeConstraint, message, rootCause);
		DaemonResponse<?> response = new DaemonResponse<>();
		response.setException(exception);
		return new ResponseEntity<DaemonResponse<?>>(response, HttpStatus.CONFLICT);
	}

	/**
	 * Property value exceptions
	 * 
	 * @param e
	 * @return
	 */
	@ExceptionHandler(PropertyValueException.class)
	public ResponseEntity<DaemonResponse<?>> handleException(PropertyValueException e) {
		String rootCause = null;
		String message = e.getMessage();
		Throwable cause = e.getCause();
		if (cause != null)
			rootCause = cause.getMessage();

		ExceptionInfo exception = new ExceptionInfo(ExceptionsCodes.codeWrongValue, message, rootCause);
		DaemonResponse<?> response = new DaemonResponse<>();
		response.setException(exception);
		return new ResponseEntity<DaemonResponse<?>>(response, HttpStatus.CONFLICT);
	}
	
	
	/**
	 * Hibernate general exception
	 * 
	 * @param e
	 * @return
	 */
	@ExceptionHandler(HibernateException.class)
	public ResponseEntity<DaemonResponse<?>> handleException(HibernateException e) {
		String rootCause = null;
		String message = e.getMessage();
		Throwable cause = e.getCause();
		if (cause != null)
			rootCause = cause.getMessage();

		ExceptionInfo exception = new ExceptionInfo(ExceptionsCodes.codeHibernateGeneral, message, rootCause);
		DaemonResponse<?> response = new DaemonResponse<>();
		response.setException(exception);
		return new ResponseEntity<DaemonResponse<?>>(response, HttpStatus.NOT_ACCEPTABLE);
	}
	
	/**
	 * Transaction creation Exception
	 * 
	 * @param e
	 * @return
	 */
	@ExceptionHandler(CannotCreateTransactionException.class)
	public ResponseEntity<DaemonResponse<?>> handleException(CannotCreateTransactionException e) {
		String rootCause = null;
		String message = e.getMessage();
		Throwable cause = e.getCause();
		if (cause != null)
			rootCause = cause.getMessage();

		ExceptionInfo exception = new
				ExceptionInfo(ExceptionsCodes.codeHibernateGeneral, message, rootCause);
		DaemonResponse<?> response = new DaemonResponse<>();
		response.setException(exception);
		return new ResponseEntity<DaemonResponse<?>>(response, HttpStatus.NOT_ACCEPTABLE);
	}
}
