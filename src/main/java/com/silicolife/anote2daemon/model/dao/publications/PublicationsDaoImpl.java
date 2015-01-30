package com.silicolife.anote2daemon.model.dao.publications;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.silicolife.anote2daemon.model.dao.GenericDaoImpl;
import com.silicolife.anote2daemon.model.dao.core.publications.PublicationsDao;
import com.silicolife.anote2daemon.model.pojo.Publications;

@Repository
public class PublicationsDaoImpl extends GenericDaoImpl<Publications> implements PublicationsDao {

	private SessionFactory sessionFactory;

	@Autowired
	public PublicationsDaoImpl(SessionFactory sessionFactory) {
		super(sessionFactory);
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Publications findFewColumnsById(Long id) {
		
		Session session = sessionFactory.getCurrentSession();
		Criteria c = session.createCriteria(PublicationsDao.className).setProjection(Projections.projectionList()
				.add(Projections.property("id"), "id")
				.add(Projections.property("title"), "title"))
				.setResultTransformer(Transformers.aliasToBean(PublicationsDao.className));
		
		c.add(Restrictions.eq("id", id));

		Publications publications = (Publications)c.uniqueResult();

		return publications;
	}
}
