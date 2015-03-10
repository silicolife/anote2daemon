package com.silicolife.anote2daemon.model.core.dao;

import org.springframework.stereotype.Repository;

import com.silicolife.anote2daemon.model.core.entities.Users;
/**
 * Manage the user logged in the system
 * 
 * @author Joel Azevedo Costa
 *
 */
@Repository
public class UsersLogged {

	private Users currentUserLogged;

	public UsersLogged() {
	}

	/**
	 * return the user logged
	 * 
	 * @return
	 */
	public Users getCurrentUserLogged() {
		return currentUserLogged;
	}

	public void setCurrentUserLogged(Users currentUserLogged) {
		this.currentUserLogged = currentUserLogged;
	}
}
