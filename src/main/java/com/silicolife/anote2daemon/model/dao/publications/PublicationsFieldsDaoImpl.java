package com.silicolife.anote2daemon.model.dao.publications;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.silicolife.anote2daemon.model.dao.GenericDaoImpl;
import com.silicolife.anote2daemon.model.dao.core.publications.PublicationsFieldsDao;
import com.silicolife.anote2daemon.model.pojo.PublicationsFields;

@Repository
public class PublicationsFieldsDaoImpl extends GenericDaoImpl<PublicationsFields> implements PublicationsFieldsDao {
	
	@Autowired
	public PublicationsFieldsDaoImpl(SessionFactory sessionFactory) {
		super(sessionFactory);
	}
}
