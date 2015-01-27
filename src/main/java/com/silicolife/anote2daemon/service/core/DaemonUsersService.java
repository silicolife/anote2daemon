package com.silicolife.anote2daemon.service.core;

import com.silicolife.anote2daemon.model.pojo.DaemonUsers;
import com.silicolife.anote2daemon.webservice.DaemonResponse;

public interface DaemonUsersService {

	public DaemonResponse<DaemonUsers> getById(Long id);

	public DaemonResponse<DaemonUsers> create(DaemonUsers daemonUser);

	public DaemonResponse<DaemonUsers> update(DaemonUsers daemonUser);
	
	
}
