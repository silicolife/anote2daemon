package com.silicolife.anote2daemon.model.dao;

import org.hibernate.SessionFactory;

import com.silicolife.anote2daemon.model.dao.core.PublicationsHasPublicationLabelsDao;
import com.silicolife.anote2daemon.model.pojo.PublicationsHasPublicationLabels;

public class PublicationsHasPublicationLabelsDaoImpl extends GenericDaoImpl<PublicationsHasPublicationLabels> implements PublicationsHasPublicationLabelsDao {

	public PublicationsHasPublicationLabelsDaoImpl(SessionFactory sessionFactory) {
		super(sessionFactory);
	}
}
