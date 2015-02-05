package com.silicolife.anote2daemon.model.core.pojo;

// Generated 5/Fev/2015 14:40:20 by Hibernate Tools 4.0.0

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * PublicationsQueryRelevance generated by hbm2java
 */
@Entity
@Table(name = "publications_query_relevance")
public class PublicationsQueryRelevance implements java.io.Serializable {

	private PublicationsQueryRelevanceId id;
	private Queries queries;
	private Publications publications;
	private String relevance;

	public PublicationsQueryRelevance() {
	}

	public PublicationsQueryRelevance(PublicationsQueryRelevanceId id, Queries queries, Publications publications) {
		this.id = id;
		this.queries = queries;
		this.publications = publications;
	}

	public PublicationsQueryRelevance(PublicationsQueryRelevanceId id, Queries queries, Publications publications, String relevance) {
		this.id = id;
		this.queries = queries;
		this.publications = publications;
		this.relevance = relevance;
	}

	@EmbeddedId
	@AttributeOverrides({ @AttributeOverride(name = "publicationsId", column = @Column(name = "publications_id", nullable = false)), @AttributeOverride(name = "queriesId", column = @Column(name = "queries_id", nullable = false)) })
	public PublicationsQueryRelevanceId getId() {
		return this.id;
	}

	public void setId(PublicationsQueryRelevanceId id) {
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

}
