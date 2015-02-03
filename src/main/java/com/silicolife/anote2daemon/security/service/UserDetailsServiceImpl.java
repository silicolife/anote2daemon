package com.silicolife.anote2daemon.security.service;

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

import com.silicolife.anote2daemon.model.dao.core.daemon.DaemonUsersDao;
import com.silicolife.anote2daemon.model.pojo.DaemonGroupsHasAccessLevels;
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

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		DaemonUsers userDomain = daemonUsersDao.findUniqueByAttribute(DaemonUsersDao.className, "username", username);
		if (userDomain == null)
			return null;

		boolean enabled = true;
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;

		Set<DaemonGroupsHasAccessLevels> accessLevelsFromUser = userDomain.getDaemonGroups().getDaemonGroupsHasAccessLevelses();
		List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();
		for (DaemonGroupsHasAccessLevels accessRole : accessLevelsFromUser) {
			String code = accessRole.getDaemonAccessLevels().getCodesAccessLevels();
			authList.add(new SimpleGrantedAuthority(code));
		}

		return new CustomSpringUser(userDomain.getUsername(), userDomain.getPassword(), enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authList, userDomain);
	}
}
