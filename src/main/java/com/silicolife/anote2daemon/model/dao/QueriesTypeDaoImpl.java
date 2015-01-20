package com.silicolife.anote2daemon.model.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.silicolife.anote2daemon.model.dao.core.QueriesTypeDao;
import com.silicolife.anote2daemon.model.pojo.QueryType;

@Repository
public class QueriesTypeDaoImpl extends GenericDaoImpl<QueryType> implements QueriesTypeDao {

	@Autowired
	public QueriesTypeDaoImpl(SessionFactory sessionFactory) {
		super(sessionFactory);
	}
}
