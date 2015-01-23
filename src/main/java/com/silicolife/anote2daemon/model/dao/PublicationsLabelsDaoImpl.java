package com.silicolife.anote2daemon.model.dao;

import org.hibernate.SessionFactory;

import com.silicolife.anote2daemon.model.dao.core.PublicationsLabelsDao;
import com.silicolife.anote2daemon.model.pojo.PublicationsLabels;

public class PublicationsLabelsDaoImpl extends GenericDaoImpl<PublicationsLabels> implements PublicationsLabelsDao {

	public PublicationsLabelsDaoImpl(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

}
