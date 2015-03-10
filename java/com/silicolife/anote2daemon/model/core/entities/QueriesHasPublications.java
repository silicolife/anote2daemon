package com.silicolife.anote2daemon.model.core.entities;

// Generated 5/Fev/2015 14:40:20 by Hibernate Tools 4.0.0

import java.util.HashSet;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * QueriesHasPublications generated by hbm2java
 */
@Entity
@Table(name = "queries_has_publications")
public class QueriesHasPublications implements java.io.Serializable {

	private QueriesHasPublicationsId id;
	private Queries queries;
	private Publications publications;
	private String relevance;
	private Set<ClustersLabelsPublications> clustersLabelsPublicationses = new HashSet<ClustersLabelsPublications>(0);

	public QueriesHasPublications() {
	}

	public QueriesHasPublications(QueriesHasPublicationsId id, Queries queries, Publications publications) {
		this.id = id;
		this.queries = queries;
		this.publications = publications;
	}

	public QueriesHasPublications(QueriesHasPublicationsId id, Queries queries, Publications publications, String relevance, Set<ClustersLabelsPublications> clustersLabelsPublicationses) {
		this.id = id;
		this.queries = queries;
		this.publications = publications;
		this.relevance = relevance;
		this.clustersLabelsPublicationses = clustersLabelsPublicationses;
	}

	@EmbeddedId
	@AttributeOverrides({ @AttributeOverride(name = "queriesId", column = @Column(name = "queries_id", nullable = false)), @AttributeOverride(name = "publicationsId", column = @Column(name = "publications_id", nullable = false)) })
	public QueriesHasPublicationsId getId() {
		return this.id;
	}

	public void setId(QueriesHasPublicationsId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "queries_id", nullable = false, insertable = false, updatable = false)
	public Queries getQueries() {
		return this.queries;
	}

	public void setQueries(Queries queries) {
		this.queries = queries;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "publications_id", nullable = false, insertable = false, updatable = false)
	public Publications getPublications() {
		return this.publications;
	}

	public void setPublications(Publications publications) {
		this.publications = publications;
	}

	@Column(name = "relevance", length = 10)
	public String getRelevance() {
		return this.relevance;
	}

	public void setRelevance(String relevance) {
		this.relevance = relevance;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "queriesHasPublications")
	public Set<ClustersLabelsPublications> getClustersLabelsPublicationses() {
		return this.clustersLabelsPublicationses;
	}

	public void setClustersLabelsPublicationses(Set<ClustersLabelsPublications> clustersLabelsPublicationses) {
		this.clustersLabelsPublicationses = clustersLabelsPublicationses;
	}

}