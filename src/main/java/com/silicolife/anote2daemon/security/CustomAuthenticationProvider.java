package com.silicolife.anote2daemon.security;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import com.silicolife.anote2daemon.model.core.entities.CustomSpringUser;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

	private UserDetailsService userService;
	private ShaPasswordEncoder passwordEncoder;

	@Autowired
	public CustomAuthenticationProvider(UserDetailsService userService, ShaPasswordEncoder passwordEncoder) {
		this.userService = userService;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = authentication.getName();
		String password = (String) authentication.getCredentials();

		UserDetails user = userService.loadUserByUsername(username);

		if (user == null) {
			throw new BadCredentialsException("Wrong credentials.");
		}

		/**
		 * Create user salt to add security password
		 */
		Long userId = ((CustomSpringUser) user).getRepositoryUser().getId();
		String strUserId = String.valueOf(userId);
		Md5PasswordEncoder encoder = new Md5PasswordEncoder();
		encoder.setIterations(13);
		String salt = encoder.encodePassword(strUserId, null);

		if (!passwordEncoder.isPasswordValid(user.getPassword(), password, salt)) {
			throw new BadCredentialsException("Wrong credentials.");
		}

		Collection<? extends GrantedAuthority> authorities = user.getAuthorities();

		return new UsernamePasswordAuthenticationToken(user, password, authorities);
	}

	@Override
	public boolean supports(Class<?> arg0) {
		return true;
	}
}
