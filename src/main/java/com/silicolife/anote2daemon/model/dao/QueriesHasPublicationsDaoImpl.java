package com.silicolife.anote2daemon.model.dao;

import org.hibernate.SessionFactory;

import com.silicolife.anote2daemon.model.dao.core.QueriesHasPublicationsDao;
import com.silicolife.anote2daemon.model.pojo.QueriesHasPublications;

public class QueriesHasPublicationsDaoImpl extends GenericDaoImpl<QueriesHasPublications> implements QueriesHasPublicationsDao {

	public QueriesHasPublicationsDaoImpl(SessionFactory sessionFactory) {
		super(sessionFactory);
	}
}
