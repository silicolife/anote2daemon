package com.silicolife.anote2daemon.security.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.silicolife.anote2daemon.model.dao.core.DaemonUsersDao;
import com.silicolife.anote2daemon.model.pojo.DaemonUsers;
import com.silicolife.anote2daemon.security.pojo.CustomSpringUser;

@Service
@Transactional(readOnly = true)
public class UserDetailsServiceImpl implements UserDetailsService {

	private DaemonUsersDao daemonUsersDao;

	@Autowired
	public UserDetailsServiceImpl(DaemonUsersDao daemonUsersDao) {
		this.daemonUsersDao = daemonUsersDao;
	}

	private static final String roleGlobal = "ROLE_ADMIN";

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		DaemonUsers userDomain = daemonUsersDao.findUniqueByAttribute(DaemonUsersDao.className, "username", username);
		if (userDomain == null)
			throw new UsernameNotFoundException("Bad Credentials");

		boolean enabled = true;
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;
		List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();
		authList.add(new SimpleGrantedAuthority(roleGlobal));

		return new CustomSpringUser(userDomain.getUsername(), userDomain.getPassword(), enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authList, userDomain);
	}
}
