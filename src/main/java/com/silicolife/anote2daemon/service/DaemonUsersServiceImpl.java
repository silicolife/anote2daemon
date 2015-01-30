package com.silicolife.anote2daemon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.silicolife.anote2daemon.model.dao.core.DaemonUsersDao;
import com.silicolife.anote2daemon.model.pojo.DaemonUsers;
import com.silicolife.anote2daemon.service.core.DaemonUsersService;
@Transactional(readOnly = true)
@Service
public class DaemonUsersServiceImpl implements DaemonUsersService {

	@Autowired
	private DaemonUsersDao daemonUsersDao;

	private final static Class<DaemonUsers> className = DaemonUsers.class;

	@Override
	public DaemonUsers getById(Long id) {
		DaemonUsers user = daemonUsersDao.findById(className, id);
		return user;
	}

	@Override
	public DaemonUsers create(DaemonUsers user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DaemonUsers update(DaemonUsers user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DaemonUsers getByUserName(String username) {
		DaemonUsers user = daemonUsersDao.findUniqueByAttribute(className, "username", username);
		return user;
	}
}
