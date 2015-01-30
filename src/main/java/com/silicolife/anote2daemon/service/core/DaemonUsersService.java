package com.silicolife.anote2daemon.service.core;

import com.silicolife.anote2daemon.model.pojo.DaemonUsers;

public interface DaemonUsersService {

	public DaemonUsers getById(Long id);

	public DaemonUsers create(DaemonUsers daemonUser);

	public DaemonUsers update(DaemonUsers daemonUser);
	
	public DaemonUsers getByUserName(String username);
	
	
}
