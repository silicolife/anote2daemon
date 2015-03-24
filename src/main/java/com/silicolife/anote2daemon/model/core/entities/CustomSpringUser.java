package com.silicolife.anote2daemon.model.core.entities;

import java.util.Collection;

import javax.persistence.Entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import pt.uminho.anote2.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.AuthUsers;


@Entity
public class CustomSpringUser extends User {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final AuthUsers userDatabase;

	public CustomSpringUser(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities, AuthUsers userDatabase) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		this.userDatabase = userDatabase;
	}

	public AuthUsers getRepositoryUser() {
		return userDatabase;
	}
}
