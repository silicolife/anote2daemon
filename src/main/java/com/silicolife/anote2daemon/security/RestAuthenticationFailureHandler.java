package com.silicolife.anote2daemon.security;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.silicolife.anote2daemon.webservice.DaemonResponse;
import com.silicolife.textmining.core.datastructures.dataaccess.daemon.webserviceclient.ExceptionInfo;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.exceptions.general.ExceptionsCodes;

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
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		response.addDateHeader("Date", new Date().getTime());
		response.setCharacterEncoding("UTF-8");
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		String message = exception.getMessage();
		String rootCause = null;
		Throwable cause = exception.getCause();
		if (cause != null)
			rootCause = cause.getMessage();
		String code = ExceptionsCodes.codeWrongCredentials;
		DaemonResponse<?> responseObj = new DaemonResponse<>();
		responseObj.setException(new ExceptionInfo(code, message, rootCause));
		ObjectMapper mapper = new ObjectMapper();
		ObjectWriter writer = mapper.writer().withDefaultPrettyPrinter();
		String json = writer.writeValueAsString(responseObj);

		PrintWriter output = response.getWriter();
		output.print(json);
		output.flush();
		output.close();

	}

}
