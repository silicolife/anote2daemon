package com.silicolife.anote2daemon.model.core.dao;

import org.springframework.stereotype.Repository;

import com.silicolife.anote2daemon.model.core.entities.Users;

@Repository
public class UsersLogged {

	private Users currentUserLogged;

	public UsersLogged() {
	}

	public Users getCurrentUserLogged() {
		return currentUserLogged;
	}

	public void setCurrentUserLogged(Users currentUserLogged) {
		this.currentUserLogged = currentUserLogged;
	}
}
