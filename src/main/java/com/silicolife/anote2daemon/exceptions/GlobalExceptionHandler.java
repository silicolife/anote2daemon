package com.silicolife.anote2daemon.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.silicolife.anote2daemon.webservice.DaemonResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<DaemonResponse<?>> handlingDataIntegrationException(DataIntegrityViolationException e) {
		DaemonResponse<?> response = new DaemonResponse<>();
		Map<String, String> mapError = new HashMap<String, String>();
		mapError.put(ExceptionsMessages.keyConstraintCode, e.getMessage());
		response.setErrors(mapError);
		return new ResponseEntity<DaemonResponse<?>>(response, HttpStatus.CONFLICT);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<DaemonResponse<?>> handlingGeneralException(Exception e) {
		DaemonResponse<?> response = new DaemonResponse<>();
		Map<String, String> mapError = new HashMap<String, String>();
		mapError.put(ExceptionsMessages.generalCode, e.getMessage());
		response.setErrors(mapError);
		return new ResponseEntity<DaemonResponse<?>>(response, HttpStatus.CONFLICT);
	}
}