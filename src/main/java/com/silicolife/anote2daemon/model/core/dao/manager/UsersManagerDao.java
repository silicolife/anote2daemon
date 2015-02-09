package com.silicolife.anote2daemon.model.core.dao.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.silicolife.anote2daemon.model.core.dao.GenericDao;
import com.silicolife.anote2daemon.model.core.entities.Users;
import com.silicolife.anote2daemon.model.core.entities.UsersHasDataObject;
import com.silicolife.anote2daemon.model.core.entities.UsersLog;

@Component
public class UsersManagerDao {

	private GenericDao<Users> usersDao;
	private GenericDao<UsersLog> usersLogDao;
	private GenericDao<UsersHasDataObject> usersHasdataObjectDao;

	@Autowired
	public UsersManagerDao(GenericDao<Users> usersDao, GenericDao<UsersLog> usersLogDao, GenericDao<UsersHasDataObject> usersHasdataObjectDao) {
		super();
		this.usersDao = usersDao;
		this.usersLogDao = usersLogDao;
		this.usersHasdataObjectDao = usersHasdataObjectDao;
	}

	public GenericDao<Users> getUsersDao() {
		return usersDao;
	}

	public GenericDao<UsersLog> getUsersLog() {
		return usersLogDao;
	}

	public GenericDao<UsersHasDataObject> getUsersHasdataObjectDao() {
		return usersHasdataObjectDao;
	}

}