package com.silicolife.anote2daemon.model.pojo;

// Generated 19/Jan/2015 15:19:18 by Hibernate Tools 4.0.0

import java.util.HashSet;
import java.util.Set;

/**
 * ClustersLabels generated by hbm2java
 */
public class ClustersLabels implements java.io.Serializable {

	private long id;
	private String name;
	private Double score;
	private Set<ClustersLabelsDocuments> clustersLabelsDocumentses = new HashSet<ClustersLabelsDocuments>(0);
	private Set<ClustersProcessHasClustersLabels> clustersProcessHasClustersLabelses = new HashSet<ClustersProcessHasClustersLabels>(0);

	public ClustersLabels() {
	}

	public ClustersLabels(long id, String name) {
		this.id = id;
		this.name = name;
	}

	public ClustersLabels(long id, String name, Double score, Set<ClustersLabelsDocuments> clustersLabelsDocumentses, Set<ClustersProcessHasClustersLabels> clustersProcessHasClustersLabelses) {
		this.id = id;
		this.name = name;
		this.score = score;
		this.clustersLabelsDocumentses = clustersLabelsDocumentses;
		this.clustersProcessHasClustersLabelses = clustersProcessHasClustersLabelses;
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getScore() {
		return this.score;
	}

	public void setScore(Double score) {
		this.score = score;
	}

	public Set<ClustersLabelsDocuments> getClustersLabelsDocumentses() {
		return this.clustersLabelsDocumentses;
	}

	public void setClustersLabelsDocumentses(Set<ClustersLabelsDocuments> clustersLabelsDocumentses) {
		this.clustersLabelsDocumentses = clustersLabelsDocumentses;
	}

	public Set<ClustersProcessHasClustersLabels> getClustersProcessHasClustersLabelses() {
		return this.clustersProcessHasClustersLabelses;
	}

	public void setClustersProcessHasClustersLabelses(Set<ClustersProcessHasClustersLabels> clustersProcessHasClustersLabelses) {
		this.clustersProcessHasClustersLabelses = clustersProcessHasClustersLabelses;
	}

}