package com.silicolife.anote2daemon.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

/**
 * Entry point in login system
 * 
 * @author Joel Azevedo Costa
 * @year 2015
 *
 */

public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest arg0, HttpServletResponse arg1, AuthenticationException arg2) throws IOException, ServletException {
		arg1.setContentType("application/json");
		arg1.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		arg1.getOutputStream().println("{ \"error\": \"" + arg2.getMessage() + "\" }");
	}
}