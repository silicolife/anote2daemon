package com.silicolife.anote2daemon.security;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;

import com.silicolife.anote2daemon.exceptions.DaemonException;
import com.silicolife.anote2daemon.exceptions.ExceptionsCodes;
import com.silicolife.anote2daemon.model.core.dao.UsersLogged;
import com.silicolife.anote2daemon.model.core.entities.Users;
import com.silicolife.anote2daemon.model.core.entities.UsersHasDataObject;
import com.silicolife.anote2daemon.model.core.entities.UsersHasDataObjectId;
import com.silicolife.anote2daemon.service.users.UsersService;

/**
 * 
 * Class to manage the permission from an user to the anote2daemon resources
 * (query, corpus, processes, resources)
 * 
 * @author Joel Azevedo Costa
 * @year 2015
 *
 */
public class RestPermissionsEvaluator implements PermissionEvaluator {

	@Autowired
	private UsersLogged userLogged;
	private UsersService usersService;

	public RestPermissionsEvaluator(UsersService usersService) {
		this.usersService = usersService;
	}

	@Override
	public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
		return false;
	}

	@Override
	public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) throws DaemonException {
		@SuppressWarnings("unchecked")
		List<String> permissionList = List.class.cast(permission);
		Users user = userLogged.getCurrentUserLogged();
		UsersHasDataObjectId idDataObject = new UsersHasDataObjectId(user.getId(), (Long) targetId, targetType);
		UsersHasDataObject dataObject = usersService.getUsersHasDataObjectById(idDataObject);
		if (dataObject == null || !permissionList.contains(dataObject.getAccesLevel()))
			throw new DaemonException(ExceptionsCodes.codeResourceAccessDenied, ExceptionsCodes.msgResourceAccessDenied);

		return true;
	}
}
