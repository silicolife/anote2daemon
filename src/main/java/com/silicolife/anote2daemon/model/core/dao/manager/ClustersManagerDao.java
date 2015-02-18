package com.silicolife.anote2daemon.model.core.dao.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.silicolife.anote2daemon.model.core.dao.GenericDao;
import com.silicolife.anote2daemon.model.core.dao.specialdao.ClustersProcessAuxDaoImpl;
import com.silicolife.anote2daemon.model.core.entities.ClustersLabels;
import com.silicolife.anote2daemon.model.core.entities.ClustersProcessHasClustersLabels;
import com.silicolife.anote2daemon.model.core.entities.ClustersProcesses;
import com.silicolife.anote2daemon.model.core.entities.ClustersProperties;

/**
 * Class to handler with clusters object DAO
 * 
 * @author Joel Azevedo Costa
 * @year 2015
 *
 */
@Repository
public class ClustersManagerDao{

	private GenericDao<ClustersProcesses> clustersProcessDao;
	private GenericDao<ClustersLabels> clustersLabelsDao;
	private GenericDao<ClustersProperties> clustersPropertiesDao;
	private GenericDao<ClustersProcessHasClustersLabels> clustersProcessHasClustersLabelsDao;
	private ClustersProcessAuxDaoImpl clustersProcessAuxDao;

	@Autowired
	public ClustersManagerDao(GenericDao<ClustersProcesses> clustersProcessDao, GenericDao<ClustersLabels> clustersLabelsDao, GenericDao<ClustersProperties> clustersPropertiesDao,
			GenericDao<ClustersProcessHasClustersLabels> clustersProcessHasClustersLabelsDao, ClustersProcessAuxDaoImpl clustersProcessAuxDao) {
		this.clustersProcessDao = clustersProcessDao;
		this.clustersLabelsDao = clustersLabelsDao;
		this.clustersPropertiesDao = clustersPropertiesDao;
		this.clustersProcessHasClustersLabelsDao = clustersProcessHasClustersLabelsDao;
		this.clustersProcessAuxDao = clustersProcessAuxDao;
	}

	public GenericDao<ClustersProcesses> getClustersProcessDao() {
		return clustersProcessDao;
	}

	public GenericDao<ClustersLabels> getClustersLabelsDao() {
		return clustersLabelsDao;
	}

	public GenericDao<ClustersProperties> getClustersPropertiesDao() {
		return clustersPropertiesDao;
	}

	public GenericDao<ClustersProcessHasClustersLabels> getClustersProcessHasClustersLabelsDao() {
		return clustersProcessHasClustersLabelsDao;
	}

	public ClustersProcessAuxDaoImpl getClustersProcessAuxDao() {
		return clustersProcessAuxDao;
	}
}
