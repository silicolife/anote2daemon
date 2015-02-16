package com.silicolife.anote2daemon.service.clustering;

import java.util.List;

import pt.uminho.anote2.core.cluster.IClusterProcess;

/**
 * 
 * Interface to define all methods of Service layer from clustering
 * 
 * @author Joel Azevedo Costa
 * @year 2015
 *
 */
public interface ClusteringService {
	/**
	 * Create a cluster process
	 * 
	 * @param clustering
	 * @return
	 */
	public Boolean createClustering(IClusterProcess clustering_);
	/**
	 * Associate cluster to query
	 * 
	 * @param queryId
	 * @param clustering
	 * @return
	 */
	public Boolean clusterProcessQueryRegistry(Long queryId, IClusterProcess clustering_);
	/**
	 * Get cluster from query
	 * 
	 * @param queryId
	 * @return
	 */
	public List<IClusterProcess> getClustersFromQuery(Long queryId);
	/**
	 * Get cluster by id
	 * 
	 * @param clusterId
	 * @return
	 */
	public IClusterProcess getClusteringById(Long clusterId);
	/**
	 * Inactive a cluster
	 * 
	 * @param clustering
	 * @return
	 */
	public Boolean inactivateClustering(IClusterProcess clustering_);
	

}
