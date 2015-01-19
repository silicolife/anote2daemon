package com.silicolife.anote2daemon.model.pojo;

// Generated 19/Jan/2015 15:19:18 by Hibernate Tools 4.0.0

/**
 * DocumentRelevance generated by hbm2java
 */
public class DocumentRelevance implements java.io.Serializable {

	private DocumentRelevanceId id;
	private Queries queries;
	private Publications publications;
	private String relevance;
	private String relevanceEnum;

	public DocumentRelevance() {
	}

	public DocumentRelevance(DocumentRelevanceId id, Queries queries, Publications publications, String relevance) {
		this.id = id;
		this.queries = queries;
		this.publications = publications;
		this.relevance = relevance;
	}

	public DocumentRelevance(DocumentRelevanceId id, Queries queries, Publications publications, String relevance, String relevanceEnum) {
		this.id = id;
		this.queries = queries;
		this.publications = publications;
		this.relevance = relevance;
		this.relevanceEnum = relevanceEnum;
	}

	public DocumentRelevanceId getId() {
		return this.id;
	}

	public void setId(DocumentRelevanceId id) {
		this.id = id;
	}

	public Queries getQueries() {
		return this.queries;
	}

	public void setQueries(Queries queries) {
		this.queries = queries;
	}

	public Publications getPublications() {
		return this.publications;
	}

	public void setPublications(Publications publications) {
		this.publications = publications;
	}

	public String getRelevance() {
		return this.relevance;
	}

	public void setRelevance(String relevance) {
		this.relevance = relevance;
	}

	public String getRelevanceEnum() {
		return this.relevanceEnum;
	}

	public void setRelevanceEnum(String relevanceEnum) {
		this.relevanceEnum = relevanceEnum;
	}

}
