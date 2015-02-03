package com.silicolife.anote2daemon.model.dao.publications;

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
import com.silicolife.anote2daemon.model.dao.core.publications.PublicationsHasPublicationsSourceDao;
import com.silicolife.anote2daemon.model.pojo.PublicationsHasPublicationsSource;

@Repository
public class PublicationsHasPublicationsSourceDaoImpl extends GenericDaoImpl<PublicationsHasPublicationsSource> implements PublicationsHasPublicationsSourceDao {

	private SessionFactory sessionFactory;

	@Autowired
	public PublicationsHasPublicationsSourceDaoImpl(SessionFactory sessionFactory) {
		super(sessionFactory);
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<Object[]> findPublicationsFromSource(Long sourceId) {
		
		Session session = sessionFactory.getCurrentSession();
		Criteria c = session.createCriteria(PublicationsHasPublicationsSourceDao.className, "publications_has_publications_source");
		c.createAlias("publications_has_publications_source.publications", "publications");
		ProjectionList columns = Projections.projectionList()
				.add(Projections.property("publications.id"), "id")
				.add(Projections.property("publications.title"), "title")
				.add(Projections.property("publications_has_publications_source.id.publicationsSourceInternalId"), "publicationsSourceInternalId");
		c.setProjection(columns);
		c.add(Restrictions.eq("publications_has_publications_source.id.publicationsSourceId", sourceId));
		
		@SuppressWarnings("unchecked")
		List<Object[]> list = c.list();
		if (list.size() == 0)
			return null;
		return list;
	}
}
