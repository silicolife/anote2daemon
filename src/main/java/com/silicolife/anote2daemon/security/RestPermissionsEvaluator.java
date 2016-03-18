package com.silicolife.anote2daemon.security;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;

import com.silicolife.anote2daemon.exceptions.PrivilegesDaemonException;
import com.silicolife.anote2daemon.service.users.UsersService;
import com.silicolife.anote2daemon.utils.GenericPairSpringSpel;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.exceptions.ResourcesExceptions;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.exceptions.general.ExceptionsCodes;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.UsersLogged;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.AuthUserDataObjects;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.AuthUserDataObjectsId;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.AuthUsers;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.service.resources.IResourcesElementService;
import com.silicolife.textmining.core.interfaces.resource.IResource;
import com.silicolife.textmining.core.interfaces.resource.IResourceElement;

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
	private IResourcesElementService resourcesElementService;

	@Autowired
	public RestPermissionsEvaluator(UsersService usersService, IResourcesElementService resourcesElementService) {
		this.usersService = usersService;
		this.resourcesElementService = resourcesElementService;
	}

	@Override
	public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
		return false;
	}

	@Override
	public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
		@SuppressWarnings("unchecked")
		GenericPairSpringSpel<RestPermissionsEvaluatorEnum, List<String>> genericInformation = GenericPairSpringSpel.class.cast(permission);
		RestPermissionsEvaluatorEnum eval = genericInformation.getX();
		List<String> permissionList = genericInformation.getY();
		AuthUsers user = userLogged.getCurrentUserLogged();
		Long id = null;
		
		switch(eval){
		case resourceByResourceElement:
			try {
				IResource<IResourceElement> obj = resourcesElementService.getResourceFromResourceElement((Long) targetId);
				id = obj.getId();
			} catch (ResourcesExceptions e) {
				throw new PrivilegesDaemonException(ExceptionsCodes.codeResourceAccessDenied, ExceptionsCodes.msgResourceAccessDenied);
			}
			break;
		case default_:
		default:
			id = (Long) targetId;
			break;
		}
		
		
		AuthUserDataObjectsId idDataObject = new AuthUserDataObjectsId(user.getAuId(), id, targetType);
		AuthUserDataObjects dataObject = usersService.getUsersHasDataObjectById(idDataObject);
		if (dataObject == null || !permissionList.contains(dataObject.getAudoPermission()))
			throw new PrivilegesDaemonException(ExceptionsCodes.codeResourceAccessDenied, ExceptionsCodes.msgResourceAccessDenied);

		return true;
	}
}
