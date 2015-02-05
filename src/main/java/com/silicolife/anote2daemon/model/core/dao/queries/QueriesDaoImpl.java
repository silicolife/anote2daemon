package com.silicolife.anote2daemon.model.core.dao.queries;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.silicolife.anote2daemon.model.core.dao.GenericDaoImpl;
import com.silicolife.anote2daemon.model.core.entities.Queries;

@Repository
public class QueriesDaoImpl extends GenericDaoImpl<Queries> implements QueriesDao {

	@Autowired
	public QueriesDaoImpl(SessionFactory sessionFactory) {
		super(sessionFactory);
	}
}
