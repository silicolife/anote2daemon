package com.silicolife.anote2daemon.model.pojo;

// Generated 27/Jan/2015 18:22:29 by Hibernate Tools 4.0.0

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Queries generated by hbm2java
 */
@Entity
@Table(name = "queries")
public class Queries implements java.io.Serializable {

	private long id;
	private QueriesType queriesType;
	private Date date;
	private String keywords;
	private String organism;
	private String completeQuery;
	private Integer matchingPublications;
	private Integer availableAbstracts;
	private String name;
	private boolean active;
	private String notes;
	private Set<QueriesHasPublications> queriesHasPublicationses = new HashSet<QueriesHasPublications>(0);
	private Set<QueriesProperties> queriesPropertieses = new HashSet<QueriesProperties>(0);
	private Set<QueriesHasClustersProcess> queriesHasClustersProcesses = new HashSet<QueriesHasClustersProcess>(0);
	private Set<PublicationsQueryRelevance> publicationsQueryRelevances = new HashSet<PublicationsQueryRelevance>(0);

	public Queries() {
	}

	public Queries(long id, QueriesType queriesType, Date date, String keywords, boolean active) {
		this.id = id;
		this.queriesType = queriesType;
		this.date = date;
		this.keywords = keywords;
		this.active = active;
	}

	public Queries(long id, QueriesType queriesType, Date date, String keywords, String organism, String completeQuery, Integer matchingPublications, Integer availableAbstracts, String name, boolean active, String notes, Set<QueriesHasPublications> queriesHasPublicationses,
			Set<QueriesProperties> queriesPropertieses, Set<QueriesHasClustersProcess> queriesHasClustersProcesses, Set<PublicationsQueryRelevance> publicationsQueryRelevances) {
		this.id = id;
		this.queriesType = queriesType;
		this.date = date;
		this.keywords = keywords;
		this.organism = organism;
		this.completeQuery = completeQuery;
		this.matchingPublications = matchingPublications;
		this.availableAbstracts = availableAbstracts;
		this.name = name;
		this.active = active;
		this.notes = notes;
		this.queriesHasPublicationses = queriesHasPublicationses;
		this.queriesPropertieses = queriesPropertieses;
		this.queriesHasClustersProcesses = queriesHasClustersProcesses;
		this.publicationsQueryRelevances = publicationsQueryRelevances;
	}

	@Id
	@Column(name = "id", unique = true, nullable = false)
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "query_type_id", nullable = false)
	public QueriesType getQueriesType() {
		return this.queriesType;
	}

	public void setQueriesType(QueriesType queriesType) {
		this.queriesType = queriesType;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date", nullable = false, length = 19)
	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Column(name = "keywords", nullable = false, length = 16777215)
	public String getKeywords() {
		return this.keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	@Column(name = "organism", length = 500)
	public String getOrganism() {
		return this.organism;
	}

	public void setOrganism(String organism) {
		this.organism = organism;
	}

	@Column(name = "complete_query", length = 500)
	public String getCompleteQuery() {
		return this.completeQuery;
	}

	public void setCompleteQuery(String completeQuery) {
		this.completeQuery = completeQuery;
	}

	@Column(name = "matching_publications")
	public Integer getMatchingPublications() {
		return this.matchingPublications;
	}

	public void setMatchingPublications(Integer matchingPublications) {
		this.matchingPublications = matchingPublications;
	}

	@Column(name = "available_abstracts")
	public Integer getAvailableAbstracts() {
		return this.availableAbstracts;
	}

	public void setAvailableAbstracts(Integer availableAbstracts) {
		this.availableAbstracts = availableAbstracts;
	}

	@Column(name = "name", length = 500)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "active", nullable = false)
	public boolean isActive() {
		return this.active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Column(name = "notes", length = 65535)
	public String getNotes() {
		return this.notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "queries")
	public Set<QueriesHasPublications> getQueriesHasPublicationses() {
		return this.queriesHasPublicationses;
	}

	public void setQueriesHasPublicationses(Set<QueriesHasPublications> queriesHasPublicationses) {
		this.queriesHasPublicationses = queriesHasPublicationses;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "queries")
	public Set<QueriesProperties> getQueriesPropertieses() {
		return this.queriesPropertieses;
	}

	public void setQueriesPropertieses(Set<QueriesProperties> queriesPropertieses) {
		this.queriesPropertieses = queriesPropertieses;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "queries")
	public Set<QueriesHasClustersProcess> getQueriesHasClustersProcesses() {
		return this.queriesHasClustersProcesses;
	}

	public void setQueriesHasClustersProcesses(Set<QueriesHasClustersProcess> queriesHasClustersProcesses) {
		this.queriesHasClustersProcesses = queriesHasClustersProcesses;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "queries")
	public Set<PublicationsQueryRelevance> getPublicationsQueryRelevances() {
		return this.publicationsQueryRelevances;
	}

	public void setPublicationsQueryRelevances(Set<PublicationsQueryRelevance> publicationsQueryRelevances) {
		this.publicationsQueryRelevances = publicationsQueryRelevances;
	}

}
