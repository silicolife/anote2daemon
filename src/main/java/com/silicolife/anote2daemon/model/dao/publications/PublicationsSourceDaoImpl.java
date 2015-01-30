package com.silicolife.anote2daemon.model.dao.publications;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.silicolife.anote2daemon.model.dao.GenericDaoImpl;
import com.silicolife.anote2daemon.model.dao.core.publications.PublicationsSourceDao;
import com.silicolife.anote2daemon.model.pojo.PublicationsSource;
@Repository
public class PublicationsSourceDaoImpl extends GenericDaoImpl<PublicationsSource> implements PublicationsSourceDao {
	
	@Autowired
	public PublicationsSourceDaoImpl(SessionFactory sessionFactory) {
		super(sessionFactory);
	}
}
