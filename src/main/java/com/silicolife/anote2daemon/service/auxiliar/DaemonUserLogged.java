package com.silicolife.anote2daemon.service.auxiliar;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.silicolife.anote2daemon.model.pojo.DaemonUsers;
import com.silicolife.anote2daemon.security.pojo.CustomSpringUser;

public class DaemonUserLogged {

	public static DaemonUsers getUserLogged() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		CustomSpringUser customUser = (CustomSpringUser) auth.getPrincipal();
		DaemonUsers user = customUser.getDaemonUser();
		return user;
	}
}
