package com.silicolife.anote2daemon.model.dao.publications;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.silicolife.anote2daemon.model.dao.GenericDaoImpl;
import com.silicolife.anote2daemon.model.dao.core.publications.PublicationsHasPublicationsSourceDao;
import com.silicolife.anote2daemon.model.pojo.PublicationsHasPublicationsSource;

@Repository
public class PublicationsHasPublicationsSourceDaoImpl extends GenericDaoImpl<PublicationsHasPublicationsSource> implements PublicationsHasPublicationsSourceDao {


	@Autowired
	public PublicationsHasPublicationsSourceDaoImpl(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

}
