package com.silicolife.anote2daemon.utils;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.UsersLogged;

public class UserLoggedThreadLocal {
	
	public static final ThreadLocal<UsersLogged> userThreadLocal = new ThreadLocal<>();
	
	public static void set(UsersLogged userLogged){
		userThreadLocal.set(userLogged);
	}
	
	public static void unset() {
		userThreadLocal.remove();
	}

	public static UsersLogged get(){
		return userThreadLocal.get();
	}
}
