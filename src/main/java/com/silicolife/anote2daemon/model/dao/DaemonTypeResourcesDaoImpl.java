package com.silicolife.anote2daemon.model.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.silicolife.anote2daemon.model.dao.core.DaemonTypeResourcesDao;
import com.silicolife.anote2daemon.model.pojo.DaemonTypeResources;
@Repository
public class DaemonTypeResourcesDaoImpl extends GenericDaoImpl<DaemonTypeResources> implements DaemonTypeResourcesDao {

	@Autowired
	public DaemonTypeResourcesDaoImpl(SessionFactory sessionFactory) {
		super(sessionFactory);
	}
}
