package com.silicolife.anote2daemon.model.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.silicolife.anote2daemon.model.dao.core.PublicationsLabelsDao;
import com.silicolife.anote2daemon.model.pojo.PublicationsLabels;
@Repository
public class PublicationsLabelsDaoImpl extends GenericDaoImpl<PublicationsLabels> implements PublicationsLabelsDao {
	@Autowired
	public PublicationsLabelsDaoImpl(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

}
