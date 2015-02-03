package com.silicolife.anote2daemon.model.dao.queries;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.silicolife.anote2daemon.model.dao.GenericDaoImpl;
import com.silicolife.anote2daemon.model.dao.core.queries.QueriesHasPublicationsDao;
import com.silicolife.anote2daemon.model.pojo.QueriesHasPublications;

@Repository
public class QueriesHasPublicationsDaoImpl extends GenericDaoImpl<QueriesHasPublications> implements QueriesHasPublicationsDao {

	private SessionFactory sessionFactory;

	@Autowired
	public QueriesHasPublicationsDaoImpl(SessionFactory sessionFactory) {
		super(sessionFactory);
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<Object[]> findPublicationsRelevanceFromQuery(Long queryId) {
		Session session = sessionFactory.getCurrentSession();
		Criteria c = session.createCriteria(QueriesHasPublicationsDao.className, "queries_has_publications");
		c.createAlias("queries_has_publications.publications", "publications");
		ProjectionList columns = Projections.projectionList()
								.add(Projections.property("publications.id"), "id")
								.add(Projections.property("publications.title"), "title")
								.add(Projections.property("queries_has_publications.relevanceEnum"));
		c.setProjection(columns);
	
		c.add(Restrictions.eq("queries_has_publications.id.queriesId", queryId));
		//c.setResultTransformer(Criteria.);
		@SuppressWarnings("unchecked")
		List<Object[]> list = c.list();
		if (list.size() == 0)
			return null;
		return list;
	}
}
