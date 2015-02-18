package com.silicolife.anote2daemon.model.core.dao.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.silicolife.anote2daemon.model.core.dao.GenericDao;
import com.silicolife.anote2daemon.model.core.dao.specialdao.PublicationsAuxDao;
import com.silicolife.anote2daemon.model.core.dao.specialdao.QueriesAuxDao;
import com.silicolife.anote2daemon.model.core.entities.Publications;
import com.silicolife.anote2daemon.model.core.entities.PublicationsFields;
import com.silicolife.anote2daemon.model.core.entities.PublicationsHasPublicationLabels;
import com.silicolife.anote2daemon.model.core.entities.PublicationsHasPublicationsSource;
import com.silicolife.anote2daemon.model.core.entities.PublicationsLabels;
import com.silicolife.anote2daemon.model.core.entities.PublicationsSource;
import com.silicolife.anote2daemon.model.core.entities.Queries;
import com.silicolife.anote2daemon.model.core.entities.QueriesHasClustersProcess;
import com.silicolife.anote2daemon.model.core.entities.QueriesHasPublications;
import com.silicolife.anote2daemon.model.core.entities.QueriesType;

/**
 * Class to handler with Queries object DAO
 * 
 * @author Joel Azevedo Costa
 * @year 2015
 *
 */
@Repository
public class QueriesManagerDao extends PublicationsManagerDao {

	private GenericDao<Queries> queriesDao;
	private GenericDao<QueriesType> queriesTypeDao;
	private GenericDao<QueriesHasPublications> queriesHasPublicationsDao;
	private GenericDao<QueriesHasClustersProcess> queriesHasClustersProcessDao;
	private QueriesAuxDao queriesAuxDao;

	@Autowired
	public QueriesManagerDao(GenericDao<PublicationsSource> publicationsSourceDao, GenericDao<PublicationsHasPublicationsSource> publicationsHasPublicationsSourceDao,
			GenericDao<Publications> publicationsDao, GenericDao<PublicationsFields> publicationsFieldsDao, GenericDao<PublicationsLabels> publicationsLabelsDao,
			GenericDao<PublicationsHasPublicationLabels> publicationsHasPublicationLabelsDao, PublicationsAuxDao publicationsAuxDao, GenericDao<Queries> queriesDao,
			GenericDao<QueriesType> queriesTypeDao, GenericDao<QueriesHasPublications> queriesHasPublicationsDao,
			GenericDao<QueriesHasClustersProcess> queriesHasClustersProcessDao, QueriesAuxDao queriesAuxDao) {
		super(publicationsSourceDao, publicationsHasPublicationsSourceDao, publicationsDao, publicationsFieldsDao, publicationsLabelsDao, publicationsHasPublicationLabelsDao,
				publicationsAuxDao);
		this.queriesDao = queriesDao;
		this.queriesTypeDao = queriesTypeDao;
		this.queriesHasPublicationsDao = queriesHasPublicationsDao;
		this.queriesHasClustersProcessDao = queriesHasClustersProcessDao;
		this.queriesAuxDao = queriesAuxDao;
	}

	public GenericDao<Queries> getQueriesDao() {
		return queriesDao;
	}

	public GenericDao<QueriesType> getQueriesType() {
		return queriesTypeDao;
	}

	public GenericDao<QueriesHasPublications> getQueriesHasPublicationsDao() {
		return queriesHasPublicationsDao;
	}

	public GenericDao<QueriesHasClustersProcess> getQueriesHasClustersProcessDao() {
		return queriesHasClustersProcessDao;
	}

	public QueriesAuxDao getQueriesAuxDao() {
		return queriesAuxDao;
	}

}
