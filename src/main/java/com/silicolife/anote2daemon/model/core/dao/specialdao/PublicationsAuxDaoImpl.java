package com.silicolife.anote2daemon.model.core.dao.specialdao;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.silicolife.anote2daemon.model.core.entities.Publications;
import com.silicolife.anote2daemon.model.core.entities.PublicationsHasPublicationsSource;

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
		c.setFetchMode("queriesHasPub.publications", FetchMode.JOIN);
		c.createAlias("pub.queriesHasPublicationses", "queriesHasPub");
		c.add(Restrictions.eq("queriesHasPub.id.queriesId", queryId));

		List<Publications> publications = c.list();

		return publications;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Publications> findPublicationsByCorpusId(Long corpusId) {

		Session session = sessionFactory.getCurrentSession();
		Criteria c = session.createCriteria(Publications.class, "pub");
		c.createAlias("pub.corpusHasPublicationses", "corpusHasPub");
		c.setFetchMode("corpusHasPub.publications", FetchMode.JOIN);
		c.add(Restrictions.eq("corpusHasPub.id.corpusId", corpusId));

		List<Publications> publications = c.list();

		return publications;
	}

	@Override
	public Publications getPublicationFullText(Long publicationId) {

		Session session = sessionFactory.getCurrentSession();
		String sqlString = "SELECT id, fullcontent FROM publications WHERE id = ?";
		SQLQuery qry = session.createSQLQuery(sqlString);
		qry.setParameter(0, publicationId);
		qry.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		@SuppressWarnings("rawtypes")
		Map data = (Map) qry.uniqueResult();
		Publications publication = null;
		if (data != null) {
			Long id = ((BigInteger) data.get("id")).longValue();
			String content = (String) data.get("fullcontent");
			publication = new Publications(id);
			publication.setFullcontent(content);
		}

		return publication;
	}
	@Override
	public List<Object[]> getPublicationBySource(Long sourceId) {

		Session session = sessionFactory.getCurrentSession();
		Criteria c = session.createCriteria(PublicationsHasPublicationsSource.class, "pubHasSource");
		c.createAlias("pubHasSource.publications", "publications");
		c.setFetchMode("pubHasSource.publications", FetchMode.JOIN);
		c.add(Restrictions.eq("pubHasSource.id.publicationsSourceId", sourceId));
		c.setProjection(Projections.projectionList().add(Projections.property("publications.id"), "pubId")
				.add(Projections.property("pubHasSource.id.publicationsSourceInternalId"), "internalId"));

		@SuppressWarnings("unchecked")
		List<Object[]> response = c.list();

		return response;
	}
}
