package com.silicolife.anote2daemon.model.pojo;

// Generated 16/Jan/2015 15:20:27 by Hibernate Tools 4.0.0

/**
 * QueriesHasClustersProcess generated by hbm2java
 */
public class QueriesHasClustersProcess implements java.io.Serializable {

	private QueriesHasClustersProcessId id;
	private Queries queries;
	private ClustersProcess clustersProcess;

	public QueriesHasClustersProcess() {
	}

	public QueriesHasClustersProcess(QueriesHasClustersProcessId id, Queries queries, ClustersProcess clustersProcess) {
		this.id = id;
		this.queries = queries;
		this.clustersProcess = clustersProcess;
	}

	public QueriesHasClustersProcessId getId() {
		return this.id;
	}

	public void setId(QueriesHasClustersProcessId id) {
		this.id = id;
	}

	public Queries getQueries() {
		return this.queries;
	}

	public void setQueries(Queries queries) {
		this.queries = queries;
	}

	public ClustersProcess getClustersProcess() {
		return this.clustersProcess;
	}

	public void setClustersProcess(ClustersProcess clustersProcess) {
		this.clustersProcess = clustersProcess;
	}

}
