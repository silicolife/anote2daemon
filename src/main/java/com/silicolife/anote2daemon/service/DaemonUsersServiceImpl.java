package com.silicolife.anote2daemon.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.silicolife.anote2daemon.model.dao.core.DaemonUsersDao;
import com.silicolife.anote2daemon.model.pojo.DaemonUsers;
import com.silicolife.anote2daemon.service.core.DaemonUsersService;
import com.silicolife.anote2daemon.webservice.DaemonResponse;

public class DaemonUsersServiceImpl implements DaemonUsersService {

	@Autowired
	private DaemonUsersDao daemonUsersDao;

	private final static Class<DaemonUsers> className = DaemonUsers.class;

	@Override
	public DaemonResponse<DaemonUsers> getById(Long id) {
		DaemonUsers user = daemonUsersDao.findById(className, id);
		DaemonResponse<DaemonUsers> response = new DaemonResponse<DaemonUsers>(user);
		return response;
	}

	@Override
	public DaemonResponse<DaemonUsers> create(DaemonUsers user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DaemonResponse<DaemonUsers> update(DaemonUsers user) {
		// TODO Auto-generated method stub
		return null;
	}

}
