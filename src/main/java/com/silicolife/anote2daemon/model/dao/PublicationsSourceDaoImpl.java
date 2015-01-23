package com.silicolife.anote2daemon.model.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.silicolife.anote2daemon.model.dao.core.PublicationsSourceDao;
import com.silicolife.anote2daemon.model.pojo.PublicationsSource;
@Repository
public class PublicationsSourceDaoImpl extends GenericDaoImpl<PublicationsSource> implements PublicationsSourceDao {
	@Autowired
	public PublicationsSourceDaoImpl(SessionFactory sessionFactory) {
		super(sessionFactory);
	}
}
