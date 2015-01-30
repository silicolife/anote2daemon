package com.silicolife.anote2daemon.model.dao.publications;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.silicolife.anote2daemon.model.dao.GenericDaoImpl;
import com.silicolife.anote2daemon.model.dao.core.publications.PublicationsHasPublicationLabelsDao;
import com.silicolife.anote2daemon.model.pojo.PublicationsHasPublicationLabels;

@Repository
public class PublicationsHasPublicationLabelsDaoImpl extends GenericDaoImpl<PublicationsHasPublicationLabels> implements PublicationsHasPublicationLabelsDao {
	
	@Autowired
	public PublicationsHasPublicationLabelsDaoImpl(SessionFactory sessionFactory) {
		super(sessionFactory);
	}
}
