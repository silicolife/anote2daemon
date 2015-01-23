package com.silicolife.anote2daemon.exceptions;

import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.silicolife.anote2daemon.exceptions.pojo.ExceptionInfo;
import com.silicolife.anote2daemon.webservice.DaemonResponse;

/**
 * Generic class to handler with database and hibernate exceptions generate by
 * anote2daemon. All exceptions are send to user in JSON Format
 * 
 * @author Joel Azevedo Costa
 *
 */
@ControllerAdvice
public class DatabaseExceptionsHandler {

	/**
	 * Method to handler with keys constraint
	 * 
	 * @param e
	 * @return
	 */
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<DaemonResponse<?>> handlingDatabaseExceptions(DataIntegrityViolationException e) {
		DaemonResponse<?> response = new DaemonResponse<>();
		String message = null;
		String rootCause = null;

		if (e.getMessage() != null)
			message = e.getMessage();
		if (e.getRootCause() != null)
			rootCause = e.getRootCause().getMessage();

		ExceptionInfo exception = new ExceptionInfo(ExceptionsCodes.keyConstraintCode, message, rootCause);
		response.setException(exception);
		return new ResponseEntity<DaemonResponse<?>>(response, HttpStatus.CONFLICT);
	}

	/**
	 * Method to handler with general database exceptions
	 * 
	 * @param e
	 * @return
	 */
	@ExceptionHandler({ SQLException.class, DataAccessException.class })
	public ResponseEntity<DaemonResponse<?>> handlingDatabaseExceptions(Exception e) {
		DaemonResponse<?> response = new DaemonResponse<>();
		String message = null;
		String rootCause = null;

		if (e.getMessage() != null)
			message = e.getMessage();
		if (e.getCause() != null)
			rootCause = e.getCause().getMessage();

		ExceptionInfo exception = new ExceptionInfo(ExceptionsCodes.generalDbCode, message, rootCause);
		response.setException(exception);
		return new ResponseEntity<DaemonResponse<?>>(response, HttpStatus.SERVICE_UNAVAILABLE);
	}

	/**
	 * Method to handler with hibernate exceptions
	 * 
	 * @param e
	 * @return
	 */
	@ExceptionHandler(HibernateException.class)
	public ResponseEntity<DaemonResponse<?>> handlingHibernateExceptions(HibernateException e) {
		DaemonResponse<?> response = new DaemonResponse<>();
		String message = null;
		String rootCause = null;

		if (e.getMessage() != null)
			message = e.getMessage();
		if (e.getCause() != null)
			rootCause = e.getCause().getMessage();

		ExceptionInfo exception = new ExceptionInfo(ExceptionsCodes.hibernateCode, message, rootCause);
		response.setException(exception);
		return new ResponseEntity<DaemonResponse<?>>(response, HttpStatus.NOT_IMPLEMENTED);
	}
}
