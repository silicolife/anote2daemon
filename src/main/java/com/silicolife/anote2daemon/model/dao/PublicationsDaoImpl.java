package com.silicolife.anote2daemon.model.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.silicolife.anote2daemon.model.dao.core.PublicationsDao;

@Repository
public class PublicationsDaoImpl extends GenericDaoImpl implements PublicationsDao {
	private SessionFactory sessionFactory;

	@Autowired
	public PublicationsDaoImpl(SessionFactory sessionFactory) {
		super(sessionFactory);
		this.sessionFactory = sessionFactory;
	}
}
