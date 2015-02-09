package com.silicolife.anote2daemon.model.core.dao.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.silicolife.anote2daemon.model.core.dao.GenericDao;
import com.silicolife.anote2daemon.model.core.dao.specialdao.PublicationsAuxDao;
import com.silicolife.anote2daemon.model.core.dao.specialdao.QueriesAuxDao;
import com.silicolife.anote2daemon.model.core.entities.Publications;
import com.silicolife.anote2daemon.model.core.entities.PublicationsHasPublicationsSource;
import com.silicolife.anote2daemon.model.core.entities.PublicationsSource;
import com.silicolife.anote2daemon.model.core.entities.Queries;
import com.silicolife.anote2daemon.model.core.entities.QueriesHasPublications;

@Component
public class QueriesManagerDao extends PublicationsManagerDao {

	private GenericDao<Queries> queriesDao;
	private GenericDao<QueriesHasPublications> queriesHasPublicationsDao;
	private QueriesAuxDao queriesAuxDao;

	@Autowired
	public QueriesManagerDao(GenericDao<PublicationsSource> publicationsSourceDao, GenericDao<PublicationsHasPublicationsSource> publicationsHasPublicationsSourceDao,
			GenericDao<Publications> publicationsDao, PublicationsAuxDao publicationsAuxDao, GenericDao<Queries> queriesDao,
			GenericDao<QueriesHasPublications> queriesHasPublicationsDao, QueriesAuxDao queriesAuxDao) {
		super(publicationsSourceDao, publicationsHasPublicationsSourceDao, publicationsDao, publicationsAuxDao);
		this.queriesDao = queriesDao;
		this.queriesHasPublicationsDao = queriesHasPublicationsDao;
		this.queriesAuxDao = queriesAuxDao;
	}

	public GenericDao<Queries> getQueriesDao() {
		return queriesDao;
	}

	public GenericDao<QueriesHasPublications> getQueriesHasPublicationsDao() {
		return queriesHasPublicationsDao;
	}

	public QueriesAuxDao getQueriesAuxDao() {
		return queriesAuxDao;
	}

}
