package com.silicolife.anote2daemon.model.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.silicolife.anote2daemon.model.dao.core.DaemonUsersDao;
import com.silicolife.anote2daemon.model.pojo.DaemonUsers;
@Repository
public class DaemonUsersDaoImpl extends GenericDaoImpl<DaemonUsers> implements DaemonUsersDao {

	@Autowired
	public DaemonUsersDaoImpl(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

}
