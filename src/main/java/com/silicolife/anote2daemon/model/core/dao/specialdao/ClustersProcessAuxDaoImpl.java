package com.silicolife.anote2daemon.model.core.dao.specialdao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.silicolife.anote2daemon.model.core.entities.ClustersProcesses;

@Repository
public class ClustersProcessAuxDaoImpl implements ClustersAuxDao {

	private SessionFactory sessionFactory;

	@Autowired
	public ClustersProcessAuxDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ClustersProcesses> findClustersByQueryId(Long queryId) {
		Session session = sessionFactory.getCurrentSession();
		Criteria c = session.createCriteria(ClustersProcesses.class, "clusters");
		c.setFetchMode("clusters.queriesHasClustersProcesses", FetchMode.JOIN);
		c.createAlias("clusters.queriesHasClustersProcesses", "queriesHasClusters");
		c.add(Restrictions.eq("queriesHasClusters.id.queriesId", queryId));

		List<ClustersProcesses> clustersProcesses = c.list();
		return clustersProcesses;
	}

}
