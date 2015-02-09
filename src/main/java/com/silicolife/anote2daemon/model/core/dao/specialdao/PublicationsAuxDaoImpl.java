package com.silicolife.anote2daemon.model.core.dao.specialdao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.silicolife.anote2daemon.model.core.entities.Publications;

@Repository
public class PublicationsAuxDaoImpl implements PublicationsAuxDao{

	private SessionFactory sessionFactory;

	@Autowired
	public PublicationsAuxDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public Publications findPublicationsById(Long id) {
		
		Session session = sessionFactory.getCurrentSession();
		Criteria c = session.createCriteria(Publications.class).setProjection(Projections.projectionList()
				.add(Projections.property("id"), "id")
				.add(Projections.property("title"), "title"))
				.setResultTransformer(Transformers.aliasToBean(Publications.class));
		
		c.add(Restrictions.eq("id", id));

		Publications publications = (Publications)c.uniqueResult();

		return publications;
	}
}
