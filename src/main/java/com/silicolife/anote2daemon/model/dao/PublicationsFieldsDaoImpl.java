package com.silicolife.anote2daemon.model.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.silicolife.anote2daemon.model.dao.core.PublicationsFieldsDao;
import com.silicolife.anote2daemon.model.pojo.PublicationsFields;

@Repository
public class PublicationsFieldsDaoImpl extends GenericDaoImpl<PublicationsFields> implements PublicationsFieldsDao {
	@Autowired
	public PublicationsFieldsDaoImpl(SessionFactory sessionFactory) {
		super(sessionFactory);
	}
}
