package com.silicolife.anote2daemon.model.pojo;

// Generated 16/Jan/2015 15:20:27 by Hibernate Tools 4.0.0

/**
 * ClustersLabelsDocuments generated by hbm2java
 */
public class ClustersLabelsDocuments implements java.io.Serializable {

	private ClustersLabelsDocumentsId id;
	private QueriesHasPublications queriesHasPublications;
	private ClustersLabels clustersLabels;

	public ClustersLabelsDocuments() {
	}

	public ClustersLabelsDocuments(ClustersLabelsDocumentsId id, QueriesHasPublications queriesHasPublications, ClustersLabels clustersLabels) {
		this.id = id;
		this.queriesHasPublications = queriesHasPublications;
		this.clustersLabels = clustersLabels;
	}

	public ClustersLabelsDocumentsId getId() {
		return this.id;
	}

	public void setId(ClustersLabelsDocumentsId id) {
		this.id = id;
	}

	public QueriesHasPublications getQueriesHasPublications() {
		return this.queriesHasPublications;
	}

	public void setQueriesHasPublications(QueriesHasPublications queriesHasPublications) {
		this.queriesHasPublications = queriesHasPublications;
	}

	public ClustersLabels getClustersLabels() {
		return this.clustersLabels;
	}

	public void setClustersLabels(ClustersLabels clustersLabels) {
		this.clustersLabels = clustersLabels;
	}

}
