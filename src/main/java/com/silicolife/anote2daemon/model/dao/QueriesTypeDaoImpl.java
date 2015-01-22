package com.silicolife.anote2daemon.model.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.silicolife.anote2daemon.model.dao.core.QueriesTypeDao;
import com.silicolife.anote2daemon.model.pojo.QueriesType;

@Repository
public class QueriesTypeDaoImpl extends GenericDaoImpl<QueriesType> implements QueriesTypeDao {

	@Autowired
	public QueriesTypeDaoImpl(SessionFactory sessionFactory) {
		super(sessionFactory);
	}
}
