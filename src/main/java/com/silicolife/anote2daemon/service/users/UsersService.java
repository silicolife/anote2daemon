package com.silicolife.anote2daemon.service.users;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.AuthUserDataObjects;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.AuthUserDataObjectsId;

/**
 * Service layer which implements all operations about users. This interface
 * extends from spring UserDetailsService to allow to use the method
 * loadUserByUsername for login.
 * 
 * 
 * @author Joel Azevedo Costa
 * @year 2015
 *
 */
public interface UsersService extends UserDetailsService {

	/**
	 * Get object associated to user by id
	 * 
	 * @param id
	 * @return
	 */
	public AuthUserDataObjects getUsersHasDataObjectById(AuthUserDataObjectsId id);

}
