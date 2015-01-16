package com.silicolife.anote2daemon.model.pojo;

// Generated 16/Jan/2015 16:00:05 by Hibernate Tools 4.0.0

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Queries generated by hbm2java
 */
public class Queries implements java.io.Serializable {

	private Long idqueries;
	private QueryType queryType;
	private Date date;
	private String keywords;
	private String organism;
	private Integer matchingPublications;
	private Integer availableAbstracts;
	private Integer downloadedPublications;
	private String name;
	private boolean active;
	private Set<QueriesHasPublications> queriesHasPublicationses = new HashSet<QueriesHasPublications>(0);
	private Set<QueriesProperties> queriesPropertieses = new HashSet<QueriesProperties>(0);
	private Set<QueriesHasClustersProcess> queriesHasClustersProcesses = new HashSet<QueriesHasClustersProcess>(0);
	private Set<DocumentRelevance> documentRelevances = new HashSet<DocumentRelevance>(0);

	public Queries() {
	}

	public Queries(QueryType queryType, Date date, String keywords, boolean active) {
		this.queryType = queryType;
		this.date = date;
		this.keywords = keywords;
		this.active = active;
	}

	public Queries(QueryType queryType, Date date, String keywords, String organism, Integer matchingPublications, Integer availableAbstracts, Integer downloadedPublications, String name, boolean active, Set<QueriesHasPublications> queriesHasPublicationses,
			Set<QueriesProperties> queriesPropertieses, Set<QueriesHasClustersProcess> queriesHasClustersProcesses, Set<DocumentRelevance> documentRelevances) {
		this.queryType = queryType;
		this.date = date;
		this.keywords = keywords;
		this.organism = organism;
		this.matchingPublications = matchingPublications;
		this.availableAbstracts = availableAbstracts;
		this.downloadedPublications = downloadedPublications;
		this.name = name;
		this.active = active;
		this.queriesHasPublicationses = queriesHasPublicationses;
		this.queriesPropertieses = queriesPropertieses;
		this.queriesHasClustersProcesses = queriesHasClustersProcesses;
		this.documentRelevances = documentRelevances;
	}

	public Long getIdqueries() {
		return this.idqueries;
	}

	public void setIdqueries(Long idqueries) {
		this.idqueries = idqueries;
	}

	public QueryType getQueryType() {
		return this.queryType;
	}

	public void setQueryType(QueryType queryType) {
		this.queryType = queryType;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getKeywords() {
		return this.keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getOrganism() {
		return this.organism;
	}

	public void setOrganism(String organism) {
		this.organism = organism;
	}

	public Integer getMatchingPublications() {
		return this.matchingPublications;
	}

	public void setMatchingPublications(Integer matchingPublications) {
		this.matchingPublications = matchingPublications;
	}

	public Integer getAvailableAbstracts() {
		return this.availableAbstracts;
	}

	public void setAvailableAbstracts(Integer availableAbstracts) {
		this.availableAbstracts = availableAbstracts;
	}

	public Integer getDownloadedPublications() {
		return this.downloadedPublications;
	}

	public void setDownloadedPublications(Integer downloadedPublications) {
		this.downloadedPublications = downloadedPublications;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isActive() {
		return this.active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Set<QueriesHasPublications> getQueriesHasPublicationses() {
		return this.queriesHasPublicationses;
	}

	public void setQueriesHasPublicationses(Set<QueriesHasPublications> queriesHasPublicationses) {
		this.queriesHasPublicationses = queriesHasPublicationses;
	}

	public Set<QueriesProperties> getQueriesPropertieses() {
		return this.queriesPropertieses;
	}

	public void setQueriesPropertieses(Set<QueriesProperties> queriesPropertieses) {
		this.queriesPropertieses = queriesPropertieses;
	}

	public Set<QueriesHasClustersProcess> getQueriesHasClustersProcesses() {
		return this.queriesHasClustersProcesses;
	}

	public void setQueriesHasClustersProcesses(Set<QueriesHasClustersProcess> queriesHasClustersProcesses) {
		this.queriesHasClustersProcesses = queriesHasClustersProcesses;
	}

	public Set<DocumentRelevance> getDocumentRelevances() {
		return this.documentRelevances;
	}

	public void setDocumentRelevances(Set<DocumentRelevance> documentRelevances) {
		this.documentRelevances = documentRelevances;
	}

}
