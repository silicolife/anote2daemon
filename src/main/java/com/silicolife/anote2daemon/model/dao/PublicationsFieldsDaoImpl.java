package com.silicolife.anote2daemon.model.dao;

import org.hibernate.SessionFactory;

import com.silicolife.anote2daemon.model.dao.core.PublicationsFieldsDao;
import com.silicolife.anote2daemon.model.pojo.PublicationsFields;

public class PublicationsFieldsDaoImpl extends GenericDaoImpl<PublicationsFields> implements PublicationsFieldsDao {

	public PublicationsFieldsDaoImpl(SessionFactory sessionFactory) {
		super(sessionFactory);
	}
}
