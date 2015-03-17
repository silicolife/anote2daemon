package com.silicolife.anote2daemon.service.users;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pt.uminho.anote2.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.manager.UsersManagerDao;
import pt.uminho.anote2.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.Users;
import pt.uminho.anote2.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.UsersGroupsHasUsersAccessLevels;
import pt.uminho.anote2.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.UsersHasDataObject;
import pt.uminho.anote2.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.UsersHasDataObjectId;

import com.silicolife.anote2daemon.model.core.entities.CustomSpringUser;

/**
 * 
 * Service layer which implement all operations about user. This implementation
 * has one spring method (loadUserByUsername) implementation to allow the login functionality.
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
	public UsersHasDataObject getUsersHasDataObjectById(UsersHasDataObjectId id) {
		UsersHasDataObject userDataobject = usersManagerDao.getUsersHasdataObjectDao().findById(id);
		return userDataobject;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Users userDomain = usersManagerDao.getUsersDao().findUniqueByAttribute("username", username);
		if (userDomain == null)
			return null;

		boolean enabled = true;
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;

		Set<UsersGroupsHasUsersAccessLevels> accessLevelsFromUser = userDomain.getUsersGroups().getUsersGroupsHasUsersAccessLevelses();
		List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();
		for (UsersGroupsHasUsersAccessLevels accessRole : accessLevelsFromUser) {
			String code = accessRole.getUsersAccessLevels().getCodesAccessLevels();
			authList.add(new SimpleGrantedAuthority(code));
		}

		return new CustomSpringUser(userDomain.getUsername(), userDomain.getUserPassword(), enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authList,
				userDomain);
	}
}
