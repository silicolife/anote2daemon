package com.silicolife.anote2daemon.security;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;

import pt.uminho.anote2.datastructures.dataaccess.database.dataaccess.implementation.exceptions.ExceptionsCodes;
import pt.uminho.anote2.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.UsersLogged;
import pt.uminho.anote2.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.AuthUserDataObjects;
import pt.uminho.anote2.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.AuthUserDataObjectsId;
import pt.uminho.anote2.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.AuthUsers;

import com.silicolife.anote2daemon.exceptions.DaemonException;
import com.silicolife.anote2daemon.service.users.UsersService;

/**
 * 
 * Class to handler with the permissions from an user to the anote2daemon
 * resources (query, corpus, processes, resources)
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
	public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
		@SuppressWarnings("unchecked")
		List<String> permissionList = List.class.cast(permission);
		AuthUsers user = userLogged.getCurrentUserLogged();
		AuthUserDataObjectsId idDataObject = new AuthUserDataObjectsId(user.getAuId(), (Long) targetId, targetType);
		AuthUserDataObjects dataObject = usersService.getUsersHasDataObjectById(idDataObject);
		if (dataObject == null || !permissionList.contains(dataObject.getAudoPermission()))
			throw new DaemonException(ExceptionsCodes.codeResourceAccessDenied, ExceptionsCodes.msgResourceAccessDenied);

		return true;
	}
}
