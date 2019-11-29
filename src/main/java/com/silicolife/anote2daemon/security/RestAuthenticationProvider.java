package com.silicolife.anote2daemon.security;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.silicolife.anote2daemon.service.users.UsersService;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.exceptions.general.ExceptionsCodes;

/**
 * Custom provider to handler with login and password encryption
 * 
 * @author Joel Azevedo Costa
 *
 */
public class RestAuthenticationProvider implements AuthenticationProvider {

	private UsersService userService;
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	public RestAuthenticationProvider(UsersService userService, BCryptPasswordEncoder passwordEncoder) {
		this.userService = userService;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = authentication.getName();
		String password = (String) authentication.getCredentials();
		UserDetails user = userService.loadUserByUsername(username);
		if (user == null) {
			throw new BadCredentialsException(ExceptionsCodes.msgWrongCredentials);
		}
		if (!passwordEncoder.matches(password, user.getPassword())) {
			throw new BadCredentialsException(ExceptionsCodes.msgWrongCredentials);
		}

		Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
		return new UsernamePasswordAuthenticationToken(user, password, authorities);
	}

	@Override
	public boolean supports(Class<?> arg0) {
		return true;
	}
}
