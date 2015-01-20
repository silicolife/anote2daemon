package com.silicolife.anote2daemon.model.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.silicolife.anote2daemon.model.dao.core.PublicationsDao;
import com.silicolife.anote2daemon.model.pojo.Publications;

@Repository
public class PublicationsDaoImpl extends GenericDaoImpl<Publications> implements PublicationsDao {

	@Autowired
	public PublicationsDaoImpl(SessionFactory sessionFactory) {
		super(sessionFactory);
	}
}
