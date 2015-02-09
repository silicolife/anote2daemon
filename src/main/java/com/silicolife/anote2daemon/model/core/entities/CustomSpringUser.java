package com.silicolife.anote2daemon.model.core.entities;

import java.util.Collection;

import javax.persistence.Entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

@Entity
public class CustomSpringUser extends User {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final Users userDatabase;

	public CustomSpringUser(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities, Users userDatabase) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		this.userDatabase = userDatabase;
	}

	public Users getRepositoryUser() {
		return userDatabase;
	}
}
