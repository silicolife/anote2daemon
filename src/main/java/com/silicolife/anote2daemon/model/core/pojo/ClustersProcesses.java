package com.silicolife.anote2daemon.model.core.pojo;

// Generated 5/Fev/2015 14:40:20 by Hibernate Tools 4.0.0

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * ClustersProcesses generated by hbm2java
 */
@Entity
@Table(name = "clusters_processes")
public class ClustersProcesses implements java.io.Serializable {

	private long id;
	private String description;
	private boolean active;
	private Set<ClustersProperties> clustersPropertieses = new HashSet<ClustersProperties>(0);
	private Set<QueriesHasClustersProcess> queriesHasClustersProcesses = new HashSet<QueriesHasClustersProcess>(0);
	private Set<ClustersProcessHasClustersLabels> clustersProcessHasClustersLabelses = new HashSet<ClustersProcessHasClustersLabels>(0);

	public ClustersProcesses() {
	}

	public ClustersProcesses(long id, boolean active) {
		this.id = id;
		this.active = active;
	}

	public ClustersProcesses(long id, String description, boolean active, Set<ClustersProperties> clustersPropertieses, Set<QueriesHasClustersProcess> queriesHasClustersProcesses, Set<ClustersProcessHasClustersLabels> clustersProcessHasClustersLabelses) {
		this.id = id;
		this.description = description;
		this.active = active;
		this.clustersPropertieses = clustersPropertieses;
		this.queriesHasClustersProcesses = queriesHasClustersProcesses;
		this.clustersProcessHasClustersLabelses = clustersProcessHasClustersLabelses;
	}

	@Id
	@Column(name = "id", unique = true, nullable = false)
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(name = "description", length = 200)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "active", nullable = false)
	public boolean isActive() {
		return this.active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "clustersProcesses")
	public Set<ClustersProperties> getClustersPropertieses() {
		return this.clustersPropertieses;
	}

	public void setClustersPropertieses(Set<ClustersProperties> clustersPropertieses) {
		this.clustersPropertieses = clustersPropertieses;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "clustersProcesses")
	public Set<QueriesHasClustersProcess> getQueriesHasClustersProcesses() {
		return this.queriesHasClustersProcesses;
	}

	public void setQueriesHasClustersProcesses(Set<QueriesHasClustersProcess> queriesHasClustersProcesses) {
		this.queriesHasClustersProcesses = queriesHasClustersProcesses;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "clustersProcesses")
	public Set<ClustersProcessHasClustersLabels> getClustersProcessHasClustersLabelses() {
		return this.clustersProcessHasClustersLabelses;
	}

	public void setClustersProcessHasClustersLabelses(Set<ClustersProcessHasClustersLabels> clustersProcessHasClustersLabelses) {
		this.clustersProcessHasClustersLabelses = clustersProcessHasClustersLabelses;
	}

}
