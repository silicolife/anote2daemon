package com.silicolife.anote2daemon.model.pojo;

// Generated 3/Fev/2015 12:37:09 by Hibernate Tools 4.0.0

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * ClustersLabelsPublications generated by hbm2java
 */
@Entity
@Table(name = "clusters_labels_publications")
public class ClustersLabelsPublications implements java.io.Serializable {

	private ClustersLabelsPublicationsId id;
	private ClustersLabels clustersLabels;
	private QueriesHasPublications queriesHasPublications;

	public ClustersLabelsPublications() {
	}

	public ClustersLabelsPublications(ClustersLabelsPublicationsId id, ClustersLabels clustersLabels, QueriesHasPublications queriesHasPublications) {
		this.id = id;
		this.clustersLabels = clustersLabels;
		this.queriesHasPublications = queriesHasPublications;
	}

	@EmbeddedId
	@AttributeOverrides({ @AttributeOverride(name = "clustersLabelsId", column = @Column(name = "clusters_labels_id", nullable = false)), @AttributeOverride(name = "queriesId", column = @Column(name = "queries_id", nullable = false)),
			@AttributeOverride(name = "publicationsId", column = @Column(name = "publications_id", nullable = false)) })
	public ClustersLabelsPublicationsId getId() {
		return this.id;
	}

	public void setId(ClustersLabelsPublicationsId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "clusters_labels_id", nullable = false, insertable = false, updatable = false)
	public ClustersLabels getClustersLabels() {
		return this.clustersLabels;
	}

	public void setClustersLabels(ClustersLabels clustersLabels) {
		this.clustersLabels = clustersLabels;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "queries_id", referencedColumnName = "queries_id", nullable = false, insertable = false, updatable = false),
			@JoinColumn(name = "publications_id", referencedColumnName = "publications_id", nullable = false, insertable = false, updatable = false) })
	public QueriesHasPublications getQueriesHasPublications() {
		return this.queriesHasPublications;
	}

	public void setQueriesHasPublications(QueriesHasPublications queriesHasPublications) {
		this.queriesHasPublications = queriesHasPublications;
	}

}
