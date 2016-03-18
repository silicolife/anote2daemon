package com.silicolife.anote2daemon.exceptions.handler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.silicolife.anote2daemon.webservice.DaemonResponse;
import com.silicolife.textmining.core.datastructures.dataaccess.daemon.webserviceclient.ExceptionInfo;

/**
 * Generic class to handler with server codes exceptions, like 404, 400, ...
 * 
 * @author Joel Azevedo Costa
 * @year 2015
 *
 */
@ControllerAdvice
public class ServerCodesExceptions extends ResponseEntityExceptionHandler {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public ResponseEntity handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {

		String statusCode = status.name();
		String reason = status.getReasonPhrase();
		String message = ex.getMessage();
		ExceptionInfo exception = new ExceptionInfo(statusCode, reason, message);
		DaemonResponse<?> response = new DaemonResponse<>();
		response.setException(exception);
		return new ResponseEntity<DaemonResponse<?>>(response, status);
	}
}
