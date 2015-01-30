package com.silicolife.anote2daemon.security.pojo;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.silicolife.anote2daemon.model.pojo.DaemonUsers;

public class CustomSpringUser extends User {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final DaemonUsers userDatabase;

	public CustomSpringUser(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities, DaemonUsers userDatabase) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		this.userDatabase = userDatabase;
	}

	public DaemonUsers getDaemonUser() {
		return userDatabase;
	}
}
