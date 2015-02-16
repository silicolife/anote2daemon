package com.silicolife.anote2daemon.model.core.dao.specialdao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.silicolife.anote2daemon.model.core.entities.Publications;

@Repository
public class PublicationsAuxDaoImpl implements PublicationsAuxDao {

	private SessionFactory sessionFactory;

	@Autowired
	public PublicationsAuxDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Publications> findPublicationsByQueryId(Long queryId) {

		Session session = sessionFactory.getCurrentSession();
		Criteria c = session.createCriteria(Publications.class, "pub");
		c.setFetchMode("pubHasPub.publications", FetchMode.JOIN);
		c.createAlias("pub.queriesHasPublicationses", "queriesHasPub");
		c.add(Restrictions.eq("queriesHasPub.id.queriesId", queryId));

		List<Publications> publications = c.list();

		return publications;
	}
}
