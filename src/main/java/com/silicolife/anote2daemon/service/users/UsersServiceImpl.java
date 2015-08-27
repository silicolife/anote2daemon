package com.silicolife.anote2daemon.service.users;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pt.uminho.anote2.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.manager.UsersManagerDao;
import pt.uminho.anote2.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.AuthGroupHasRoles;
import pt.uminho.anote2.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.AuthUserDataObjects;
import pt.uminho.anote2.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.AuthUserDataObjectsId;
import pt.uminho.anote2.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.AuthUsers;

import com.silicolife.anote2daemon.model.core.entities.CustomSpringUser;

/**
 * 
 * Service layer which implement all operations about user. This implementation
 * has one spring method (loadUserByUsername) implementation to allow the login
 * functionality.
 * 
 * @author Joel Azevedo Costa
 * @year 2015
 *
 */
@Service
@Transactional(readOnly = true)
public class UsersServiceImpl implements UsersService {

	private UsersManagerDao usersManagerDao;

	@Autowired
	public UsersServiceImpl(UsersManagerDao usersManagerDao) {
		this.usersManagerDao = usersManagerDao;
	}

	@Override
	public AuthUserDataObjects getUsersHasDataObjectById(AuthUserDataObjectsId id) {
		AuthUserDataObjects userDataobject = usersManagerDao.getAuthUserDataObjectsDao().findById(id);
		return userDataobject;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, HibernateException {

		AuthUsers userDomain = usersManagerDao.getAuthUsersDao().findUniqueByAttribute("auUsername", username);
		if (userDomain == null)
			return null;

		boolean enabled = true;
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;

		Set<AuthGroupHasRoles> accessLevelsFromUser = userDomain.getAuthGroups().getAuthGroupHasRoleses();
		List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();
		for (AuthGroupHasRoles accessRole : accessLevelsFromUser) {
			String code = accessRole.getAuthRoles().getArRoleCode();
			authList.add(new SimpleGrantedAuthority(code));
		}

		return new CustomSpringUser(userDomain.getAuUsername(), userDomain.getAuPassword(), enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authList,
				userDomain);
	}
}
