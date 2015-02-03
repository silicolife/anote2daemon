package com.silicolife.anote2daemon.model.pojo;

// Generated 3/Fev/2015 12:37:09 by Hibernate Tools 4.0.0

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * QueriesHasClustersProcessId generated by hbm2java
 */
@Embeddable
public class QueriesHasClustersProcessId implements java.io.Serializable {

	private long queriesId;
	private long clustersProcessId;

	public QueriesHasClustersProcessId() {
	}

	public QueriesHasClustersProcessId(long queriesId, long clustersProcessId) {
		this.queriesId = queriesId;
		this.clustersProcessId = clustersProcessId;
	}

	@Column(name = "queries_id", nullable = false)
	public long getQueriesId() {
		return this.queriesId;
	}

	public void setQueriesId(long queriesId) {
		this.queriesId = queriesId;
	}

	@Column(name = "clusters_process_id", nullable = false)
	public long getClustersProcessId() {
		return this.clustersProcessId;
	}

	public void setClustersProcessId(long clustersProcessId) {
		this.clustersProcessId = clustersProcessId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof QueriesHasClustersProcessId))
			return false;
		QueriesHasClustersProcessId castOther = (QueriesHasClustersProcessId) other;

		return (this.getQueriesId() == castOther.getQueriesId()) && (this.getClustersProcessId() == castOther.getClustersProcessId());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (int) this.getQueriesId();
		result = 37 * result + (int) this.getClustersProcessId();
		return result;
	}

}
