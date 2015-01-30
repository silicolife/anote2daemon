package com.silicolife.anote2daemon.model.dao.daemon;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.silicolife.anote2daemon.model.dao.GenericDaoImpl;
import com.silicolife.anote2daemon.model.dao.core.daemon.DaemonDataObjectDao;
import com.silicolife.anote2daemon.model.pojo.DaemonDataObject;

@Repository
public class DaemonDataObjectDaoImpl extends GenericDaoImpl<DaemonDataObject> implements DaemonDataObjectDao {

	@Autowired
	public DaemonDataObjectDaoImpl(SessionFactory sessionFactory) {
		super(sessionFactory);
	}
}
