package com.silicolife.anote2daemon.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.silicolife.anote2daemon.model.core.entities.CustomSpringUser;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.UsersLogged;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.UsersLoggedImpl;

public abstract class SpringRunnable implements Runnable{

	private Authentication authentication;
	protected UsersLogged usersLogged;

	public SpringRunnable(){
		this.authentication = SecurityContextHolder.getContext().getAuthentication();
	}

	@Override
	public void run() {
		try {
			onRun();
		} finally {
			cleanAuthenticationAndUser();
		}
	}


	private void cleanAuthenticationAndUser() {
		authentication = null;
		usersLogged = null;
	}

	protected abstract void onRun();
	
	private Authentication getAuthentication(){
		return authentication;
	}
	
	protected UsersLogged getUserLogged(){
		if(getAuthentication() != null){
			CustomSpringUser customUser = (CustomSpringUser) getAuthentication().getPrincipal();
			return new UsersLoggedImpl(customUser.getRepositoryUser());
		}
		return new UsersLoggedImpl();
	}

}
