package com.silicolife.anote2daemon.model.core.dao.specialdao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.silicolife.anote2daemon.model.core.entities.Processes;

@Repository
public class CorpusAuxDaoImpl implements CorpusAuxDao {
	private SessionFactory sessionFactory;

	@Autowired
	public CorpusAuxDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<Processes> findProcessesByCorpusId(Long corpusId) {

		Session session = sessionFactory.getCurrentSession();
		Criteria c = session.createCriteria(Processes.class, "processes");
		c.createAlias("processes.corpusHasProcesseses", "corpusHasProcesses");
		c.setFetchMode("corpusHasProcesses.processes", FetchMode.JOIN);
		c.add(Restrictions.eq("corpusHasProcesses.id.corpusId", corpusId));

		@SuppressWarnings("unchecked")
		List<Processes> processes = c.list();

		return processes;
	}
}
