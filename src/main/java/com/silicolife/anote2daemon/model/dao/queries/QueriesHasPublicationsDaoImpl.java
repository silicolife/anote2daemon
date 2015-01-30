package com.silicolife.anote2daemon.model.dao.queries;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.silicolife.anote2daemon.model.dao.GenericDaoImpl;
import com.silicolife.anote2daemon.model.dao.core.queries.QueriesHasPublicationsDao;
import com.silicolife.anote2daemon.model.pojo.QueriesHasPublications;

@Repository
public class QueriesHasPublicationsDaoImpl extends GenericDaoImpl<QueriesHasPublications> implements QueriesHasPublicationsDao {

	@Autowired
	public QueriesHasPublicationsDaoImpl(SessionFactory sessionFactory) {
		super(sessionFactory);
	}
}
