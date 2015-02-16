package com.silicolife.anote2daemon.security;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.silicolife.anote2daemon.exceptions.DaemonException;
import com.silicolife.anote2daemon.exceptions.ExceptionsCodes;
import com.silicolife.anote2daemon.model.core.dao.UsersLogged;
import com.silicolife.anote2daemon.model.core.dao.manager.UsersManagerDao;
import com.silicolife.anote2daemon.model.core.entities.Users;
import com.silicolife.anote2daemon.model.core.entities.UsersHasDataObject;
import com.silicolife.anote2daemon.model.core.entities.UsersHasDataObjectId;

/**
 * 
 * Class to manage the permission from an user to the anote2 resources (query,
 * corpus, processes, resources)
 * 
 * @author Joel Azevedo Costa
 * @year 2015
 *
 */

@Service
public class RestPermissionsEvaluator implements PermissionEvaluator {

	@Autowired
	private UsersLogged userLogged;
	private UsersManagerDao usersManagerDao;

	@Autowired
	public RestPermissionsEvaluator(UsersManagerDao usersManagerDao) {
		this.usersManagerDao = usersManagerDao;
	}

	@Override
	public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) throws DaemonException {
		return false;
	}

	@Override
	public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) throws DaemonException {
		String action = (String) permission;
		switch (action) {
		case "queryAll":
			return true;
		case "queryOwner":
			hasPermission((Long) targetId, targetType, "owner");
		case "queryRead":
			hasPermission((Long) targetId, targetType, "read");
		case "queryReadWrite":
			hasPermission((Long) targetId, targetType, "read_write");
		default:
			return false;
		}
	}

	private Boolean hasPermission(Long targetId, String targetType, String permission) {
		Users user = userLogged.getCurrentUserLogged();
		UsersHasDataObjectId idDataObject = new UsersHasDataObjectId(user.getId(), targetId, targetType);
		UsersHasDataObject dataObject = usersManagerDao.getUsersHasdataObjectDao().findById(idDataObject);
		if (dataObject == null || dataObject.getAccesLevel() != permission)
			throw new DaemonException(ExceptionsCodes.codeQueryAccessDenied, ExceptionsCodes.msgQueryAccessDenied);

		return true;
	}

}
