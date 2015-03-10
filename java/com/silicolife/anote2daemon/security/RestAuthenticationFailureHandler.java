package com.silicolife.anote2daemon.security;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.silicolife.anote2daemon.exceptions.ExceptionsCodes;
import com.silicolife.anote2daemon.exceptions.entities.ExceptionInfo;

/**
 * 
 * Custom class to handler with authentication fail to daemon. The error message
 * is sent in JSON format
 * 
 * 
 * @author Joel Azevedo Costa
 * @year 2015
 *
 */

public class RestAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
		/*
		 * send authentication fail in JSON format
		 */
		response.addHeader("Content-type", "application/json");
		response.addDateHeader("Date", new Date().getTime());
		response.setCharacterEncoding("UTF-8");
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		String message = exception.getMessage();
		String rootCause = null;
		Throwable cause = exception.getCause();
		if (cause != null)
			rootCause = cause.getMessage();
		String code = ExceptionsCodes.codeWrongCredentials;
		ExceptionInfo exceptionObj = new ExceptionInfo(code, message, rootCause);
		ObjectMapper mapper = new ObjectMapper();
		ObjectWriter writer = mapper.writer().withDefaultPrettyPrinter();
		String json = writer.writeValueAsString(exceptionObj);

		ServletOutputStream output = response.getOutputStream();
		output.print(json);
		output.flush();
		output.close();

	}

}
