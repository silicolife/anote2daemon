package com.silicolife.anote2daemon.model.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.silicolife.anote2daemon.model.dao.core.QueriesDao;
import com.silicolife.anote2daemon.model.pojo.Queries;

@Repository
public class QueriesDaoImpl extends GenericDaoImpl<Queries> implements QueriesDao {

	@Autowired
	public QueriesDaoImpl(SessionFactory sessionFactory) {
		super(sessionFactory);
	}
}
