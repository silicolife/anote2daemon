package com.silicolife.anote2daemon.service.users;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.silicolife.anote2daemon.model.core.dao.manager.UsersManagerDao;
import com.silicolife.anote2daemon.model.core.entities.CustomSpringUser;
import com.silicolife.anote2daemon.model.core.entities.Users;
import com.silicolife.anote2daemon.model.core.entities.UsersGroupsHasUsersAccessLevels;

@Service
@Transactional(readOnly = true)
public class UserDetailsServiceImpl implements UserDetailsService {

	private UsersManagerDao usersManagerDao;

	@Autowired
	public UserDetailsServiceImpl(UsersManagerDao usersManagerDao) {
		this.usersManagerDao = usersManagerDao;
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

		return new CustomSpringUser(userDomain.getUsername(), userDomain.getUserPassword(), enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authList, userDomain);
	}
}
