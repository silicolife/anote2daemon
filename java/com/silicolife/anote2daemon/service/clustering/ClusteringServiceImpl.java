package com.silicolife.anote2daemon.service.clustering;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pt.uminho.anote2.core.cluster.IClusterLabel;
import pt.uminho.anote2.core.cluster.IClusterProcess;

import com.silicolife.anote2daemon.exceptions.DaemonException;
import com.silicolife.anote2daemon.exceptions.ExceptionsCodes;
import com.silicolife.anote2daemon.model.core.dao.UsersLogged;
import com.silicolife.anote2daemon.model.core.dao.manager.ClustersManagerDao;
import com.silicolife.anote2daemon.model.core.dao.manager.QueriesManagerDao;
import com.silicolife.anote2daemon.model.core.dao.manager.UsersManagerDao;
import com.silicolife.anote2daemon.model.core.entities.ClustersLabels;
import com.silicolife.anote2daemon.model.core.entities.ClustersProcessHasClustersLabels;
import com.silicolife.anote2daemon.model.core.entities.ClustersProcessHasClustersLabelsId;
import com.silicolife.anote2daemon.model.core.entities.ClustersProcesses;
import com.silicolife.anote2daemon.model.core.entities.ClustersProperties;
import com.silicolife.anote2daemon.model.core.entities.QueriesHasClustersProcess;
import com.silicolife.anote2daemon.model.core.entities.QueriesHasClustersProcessId;
import com.silicolife.anote2daemon.model.core.entities.Users;
import com.silicolife.anote2daemon.model.core.entities.UsersLog;
import com.silicolife.anote2daemon.wrapper.clustering.ClustersLabelsWrapper;
import com.silicolife.anote2daemon.wrapper.clustering.ClustersProcessWrapper;
import com.silicolife.anote2daemon.wrapper.clustering.ClustersPropertiesWrapper;

/**
 * Service layer which implements all operations about clustering.
 * 
 * @author Joel Azevedo Costa
 * @year 2015
 *
 */
@Service
@Transactional(readOnly = true)
public class ClusteringServiceImpl implements ClusteringService {

	private ClustersManagerDao clustersManagerDao;
	private UsersManagerDao usersManagerDao;
	private QueriesManagerDao queriesManagerDao;
	@Autowired
	private UsersLogged userLogged;
	

	@Autowired
	public ClusteringServiceImpl(ClustersManagerDao clustersManagerDao, UsersManagerDao usersManagerDao, QueriesManagerDao queriesManagerDao) {
		this.clustersManagerDao = clustersManagerDao;
		this.usersManagerDao = usersManagerDao;
		this.queriesManagerDao = queriesManagerDao;
	}

	@Transactional(readOnly = false)
	@Override
	public Boolean createClustering(IClusterProcess clustering_) {
		/*
		 * save cluster process
		 */
		ClustersProcesses clusterProcess = ClustersProcessWrapper.convertToDaemonStructure(clustering_);
		clustersManagerDao.getClustersProcessDao().save(clusterProcess);
		/*
		 * save clusters associations
		 */
		List<IClusterLabel> clustersLabels_ = clustering_.getLabels();
		if (clustersLabels_ != null) {
			for (IClusterLabel clusterLabel_ : clustersLabels_) {
				ClustersLabels clusterLabel = ClustersLabelsWrapper.convertToDaemonStructure(clusterLabel_);
				createClustersLabels(clusterProcess, clusterLabel);
			}
		}
		Properties properties = clustering_.getPropeties();
		if (properties != null) {
			Set<ClustersProperties> clustersProperties = ClustersPropertiesWrapper.convertToDaemonStructure(properties, clusterProcess);
			for (ClustersProperties clusterProperty : clustersProperties) {
				createClustersPrperties(clusterProperty);
			}
		}	
		/*
		 * Log
		 */
		Users user = userLogged.getCurrentUserLogged();
		UsersLog log = new UsersLog(user, new Date(), "create", "clusters_processes/cluster_label/cluster_properties", null, "create cluster processes");
		usersManagerDao.getUsersLog().save(log);

		return true;
	}

	@Transactional(readOnly = false)
	@Override
	public Boolean clusterProcessQueryRegistry(Long queryId, Long clusteringId) {
		
		QueriesHasClustersProcessId id = new QueriesHasClustersProcessId(queryId, clusteringId);
		QueriesHasClustersProcess queriesHasClusters = new QueriesHasClustersProcess(id, null, null);
		queriesManagerDao.getQueriesHasClustersProcessDao().save(queriesHasClusters);
		/*
		 * Log
		 */
		Users user = userLogged.getCurrentUserLogged();
		UsersLog log = new UsersLog(user, new Date(), "create", "queries_has_clusters_processes", null, "Association between queries and clusters processes");
		usersManagerDao.getUsersLog().save(log);
		
		return true;
	}

	@Override
	public List<IClusterProcess> getClustersFromQuery(Long queryId) {
		List<IClusterProcess> clustersProcess_ = new ArrayList<IClusterProcess>();
		List<ClustersProcesses> clustersProcesses = clustersManagerDao.getClustersProcessAuxDao().findClustersByQueryId(queryId);
		for (ClustersProcesses clusterProcess : clustersProcesses) {
			IClusterProcess clusterProcess_ = ClustersProcessWrapper.convertToAnoteStructure(clusterProcess);
			clustersProcess_.add(clusterProcess_);
		}
		if (clustersProcess_.size() == 0)
			return null;

		return clustersProcess_;
	}

	@Override
	public IClusterProcess getClusteringById(Long clusterId) {
		ClustersProcesses clusterProcess = clustersManagerDao.getClustersProcessDao().findById(clusterId);
		if (clusterProcess == null)
			return null;

		IClusterProcess clusterProcess_ = ClustersProcessWrapper.convertToAnoteStructure(clusterProcess);
		return clusterProcess_;
	}

	@Transactional(readOnly = false)
	@Override
	public Boolean inactivateClustering(Long clusteringId) {
		
		ClustersProcesses clustersProcess = clustersManagerDao.getClustersProcessDao().findById(clusteringId);
		if(clustersProcess == null)
			throw new DaemonException(ExceptionsCodes.codeNoCluster, ExceptionsCodes.msgNoCluster);
		clustersProcess.setActive(false);
		
		clustersManagerDao.getClustersProcessDao().update(clustersProcess);

		return true;
	}

	/*
	 * private auxiliar methods to save cluster processes
	 */
	private void createClustersPrperties(ClustersProperties properties) {
		clustersManagerDao.getClustersPropertiesDao().save(properties);
	}

	private void createClustersLabels(ClustersProcesses clusterProcess, ClustersLabels clusterLabel) {
		String name = clusterLabel.getClusterLabelName();
		ClustersLabels clusterLabelObj = clustersManagerDao.getClustersLabelsDao().findUniqueByAttribute("clusterLabelName", name);
		if (clusterLabelObj == null) {
			clustersManagerDao.getClustersLabelsDao().save(clusterLabel);
			clusterLabelObj = clusterLabel;
		}

		ClustersProcessHasClustersLabelsId id = new ClustersProcessHasClustersLabelsId(clusterProcess.getId(), clusterLabelObj.getClustersLabelId());
		ClustersProcessHasClustersLabels clusterProcessHasLabel = clustersManagerDao.getClustersProcessHasClustersLabelsDao().findById(id);
		if (clusterProcessHasLabel == null) {
			ClustersProcessHasClustersLabels clusterProcessHasLabelToDb = new ClustersProcessHasClustersLabels(id, clusterLabelObj, clusterProcess);
			clustersManagerDao.getClustersProcessHasClustersLabelsDao().save(clusterProcessHasLabelToDb);
		}
	}
}