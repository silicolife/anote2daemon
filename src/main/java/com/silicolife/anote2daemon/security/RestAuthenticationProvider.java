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
		String inputUsername = authentication.getName();
		String inputPassword = (String) authentication.getCredentials();
		UserDetails user = userService.loadUserByUsername(inputUsername);
		if (user == null) {
			throw new BadCredentialsException(ExceptionsCodes.msgWrongCredentials);
		}
<<<<<<< HEAD

//		/**
//		 * Create user salt to add security password
//		 */
//		Long userId = ((CustomSpringUser) user).getRepositoryUser().getAuId();
//		String strUserId = String.valueOf(userId);
//		Md5PasswordEncoder encoder = new Md5PasswordEncoder();
//		encoder.setIterations(13);
//		String salt = encoder.encodePassword(strUserId, null);

		if (!passwordEncoder.matches(inputPassword, user.getPassword())) {
=======
		if (!passwordEncoder.matches(password, user.getPassword())) {
>>>>>>> branch 'rrodrigues' of https://github.com/silicolife/anote2daemon
			throw new BadCredentialsException(ExceptionsCodes.msgWrongCredentials);
		}

		Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
		return new UsernamePasswordAuthenticationToken(user, inputPassword, authorities);
	}

	@Override
	public boolean supports(Class<?> arg0) {
		return true;
	}
}
