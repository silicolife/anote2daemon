package com.silicolife.anote2daemon.model.dao;

import org.hibernate.SessionFactory;

import com.silicolife.anote2daemon.model.dao.core.PublicationsSourceDao;
import com.silicolife.anote2daemon.model.pojo.PublicationsSource;

public class PublicationsSourceDaoImpl extends GenericDaoImpl<PublicationsSource> implements PublicationsSourceDao {

	public PublicationsSourceDaoImpl(SessionFactory sessionFactory) {
		super(sessionFactory);
	}
}
