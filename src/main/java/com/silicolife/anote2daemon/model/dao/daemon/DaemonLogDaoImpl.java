package com.silicolife.anote2daemon.model.dao.daemon;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.silicolife.anote2daemon.model.dao.GenericDaoImpl;
import com.silicolife.anote2daemon.model.dao.core.daemon.DaemonLogDao;
import com.silicolife.anote2daemon.model.pojo.DaemonLog;

@Repository
public class DaemonLogDaoImpl extends GenericDaoImpl<DaemonLog> implements DaemonLogDao {

	@Autowired
	public DaemonLogDaoImpl(SessionFactory sessionFactory) {
		super(sessionFactory);
	}
}
