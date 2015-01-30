package com.silicolife.anote2daemon.model.dao.publications;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.silicolife.anote2daemon.model.dao.GenericDaoImpl;
import com.silicolife.anote2daemon.model.dao.core.publications.PublicationsLabelsDao;
import com.silicolife.anote2daemon.model.pojo.PublicationsLabels;
@Repository
public class PublicationsLabelsDaoImpl extends GenericDaoImpl<PublicationsLabels> implements PublicationsLabelsDao {

	@Autowired
	public PublicationsLabelsDaoImpl(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

}
