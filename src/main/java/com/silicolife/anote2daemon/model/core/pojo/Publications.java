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
 * Publications generated by hbm2java
 */
@Entity
@Table(name = "publications")
public class Publications implements java.io.Serializable {

	private long id;
	private String title;
	private String pubauthors;
	private String category;
	private Integer pubdate;
	private String fulldate;
	private String pubstatus;
	private String journal;
	private String volume;
	private String issue;
	private String pages;
	private String abstract_;
	private String externalLinks;
	private Boolean freeFullText;
	private String fullcontent;
	private String notes;
	private String relativePath;
	private Set<AnnotationsLog> annotationsLogs = new HashSet<AnnotationsLog>(0);
	private Set<QueriesHasPublications> queriesHasPublicationses = new HashSet<QueriesHasPublications>(0);
	private Set<PublicationsHasPublicationsSource> publicationsHasPublicationsSources = new HashSet<PublicationsHasPublicationsSource>(0);
	private Set<Annotations> annotationses = new HashSet<Annotations>(0);
	private Set<PublicationsFields> publicationsFieldses = new HashSet<PublicationsFields>(0);
	private Set<PublicationsHasPublicationLabels> publicationsHasPublicationLabelses = new HashSet<PublicationsHasPublicationLabels>(0);
	private Set<PublicationsQueryRelevance> publicationsQueryRelevances = new HashSet<PublicationsQueryRelevance>(0);
	private Set<CorpusHasPublications> corpusHasPublicationses = new HashSet<CorpusHasPublications>(0);

	public Publications() {
	}

	public Publications(long id) {
		this.id = id;
	}

	public Publications(long id, String title, String pubauthors, String category, Integer pubdate, String fulldate, String pubstatus, String journal, String volume, String issue, String pages, String abstract_, String externalLinks, Boolean freeFullText, String fullcontent,
			String notes, String relativePath, Set<AnnotationsLog> annotationsLogs, Set<QueriesHasPublications> queriesHasPublicationses, Set<PublicationsHasPublicationsSource> publicationsHasPublicationsSources, Set<Annotations> annotationses,
			Set<PublicationsFields> publicationsFieldses, Set<PublicationsHasPublicationLabels> publicationsHasPublicationLabelses, Set<PublicationsQueryRelevance> publicationsQueryRelevances, Set<CorpusHasPublications> corpusHasPublicationses) {
		this.id = id;
		this.title = title;
		this.pubauthors = pubauthors;
		this.category = category;
		this.pubdate = pubdate;
		this.fulldate = fulldate;
		this.pubstatus = pubstatus;
		this.journal = journal;
		this.volume = volume;
		this.issue = issue;
		this.pages = pages;
		this.abstract_ = abstract_;
		this.externalLinks = externalLinks;
		this.freeFullText = freeFullText;
		this.fullcontent = fullcontent;
		this.notes = notes;
		this.relativePath = relativePath;
		this.annotationsLogs = annotationsLogs;
		this.queriesHasPublicationses = queriesHasPublicationses;
		this.publicationsHasPublicationsSources = publicationsHasPublicationsSources;
		this.annotationses = annotationses;
		this.publicationsFieldses = publicationsFieldses;
		this.publicationsHasPublicationLabelses = publicationsHasPublicationLabelses;
		this.publicationsQueryRelevances = publicationsQueryRelevances;
		this.corpusHasPublicationses = corpusHasPublicationses;
	}

	@Id
	@Column(name = "id", unique = true, nullable = false)
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(name = "title", length = 65535)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "pubauthors", length = 65535)
	public String getPubauthors() {
		return this.pubauthors;
	}

	public void setPubauthors(String pubauthors) {
		this.pubauthors = pubauthors;
	}

	@Column(name = "category", length = 200)
	public String getCategory() {
		return this.category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Column(name = "pubdate")
	public Integer getPubdate() {
		return this.pubdate;
	}

	public void setPubdate(Integer pubdate) {
		this.pubdate = pubdate;
	}

	@Column(name = "fulldate", length = 25)
	public String getFulldate() {
		return this.fulldate;
	}

	public void setFulldate(String fulldate) {
		this.fulldate = fulldate;
	}

	@Column(name = "pubstatus", length = 25)
	public String getPubstatus() {
		return this.pubstatus;
	}

	public void setPubstatus(String pubstatus) {
		this.pubstatus = pubstatus;
	}

	@Column(name = "journal", length = 500)
	public String getJournal() {
		return this.journal;
	}

	public void setJournal(String journal) {
		this.journal = journal;
	}

	@Column(name = "volume", length = 20)
	public String getVolume() {
		return this.volume;
	}

	public void setVolume(String volume) {
		this.volume = volume;
	}

	@Column(name = "issue", length = 20)
	public String getIssue() {
		return this.issue;
	}

	public void setIssue(String issue) {
		this.issue = issue;
	}

	@Column(name = "pages", length = 75)
	public String getPages() {
		return this.pages;
	}

	public void setPages(String pages) {
		this.pages = pages;
	}

	@Column(name = "abstract")
	public String getAbstract_() {
		return this.abstract_;
	}

	public void setAbstract_(String abstract_) {
		this.abstract_ = abstract_;
	}

	@Column(name = "external_links", length = 65535)
	public String getExternalLinks() {
		return this.externalLinks;
	}

	public void setExternalLinks(String externalLinks) {
		this.externalLinks = externalLinks;
	}

	@Column(name = "free_full_text")
	public Boolean getFreeFullText() {
		return this.freeFullText;
	}

	public void setFreeFullText(Boolean freeFullText) {
		this.freeFullText = freeFullText;
	}

	@Column(name = "fullcontent")
	public String getFullcontent() {
		return this.fullcontent;
	}

	public void setFullcontent(String fullcontent) {
		this.fullcontent = fullcontent;
	}

	@Column(name = "notes", length = 65535)
	public String getNotes() {
		return this.notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	@Column(name = "relative_path", length = 200)
	public String getRelativePath() {
		return this.relativePath;
	}

	public void setRelativePath(String relativePath) {
		this.relativePath = relativePath;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "publications")
	public Set<AnnotationsLog> getAnnotationsLogs() {
		return this.annotationsLogs;
	}

	public void setAnnotationsLogs(Set<AnnotationsLog> annotationsLogs) {
		this.annotationsLogs = annotationsLogs;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "publications")
	public Set<QueriesHasPublications> getQueriesHasPublicationses() {
		return this.queriesHasPublicationses;
	}

	public void setQueriesHasPublicationses(Set<QueriesHasPublications> queriesHasPublicationses) {
		this.queriesHasPublicationses = queriesHasPublicationses;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "publications")
	public Set<PublicationsHasPublicationsSource> getPublicationsHasPublicationsSources() {
		return this.publicationsHasPublicationsSources;
	}

	public void setPublicationsHasPublicationsSources(Set<PublicationsHasPublicationsSource> publicationsHasPublicationsSources) {
		this.publicationsHasPublicationsSources = publicationsHasPublicationsSources;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "publications")
	public Set<Annotations> getAnnotationses() {
		return this.annotationses;
	}

	public void setAnnotationses(Set<Annotations> annotationses) {
		this.annotationses = annotationses;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "publications")
	public Set<PublicationsFields> getPublicationsFieldses() {
		return this.publicationsFieldses;
	}

	public void setPublicationsFieldses(Set<PublicationsFields> publicationsFieldses) {
		this.publicationsFieldses = publicationsFieldses;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "publications")
	public Set<PublicationsHasPublicationLabels> getPublicationsHasPublicationLabelses() {
		return this.publicationsHasPublicationLabelses;
	}

	public void setPublicationsHasPublicationLabelses(Set<PublicationsHasPublicationLabels> publicationsHasPublicationLabelses) {
		this.publicationsHasPublicationLabelses = publicationsHasPublicationLabelses;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "publications")
	public Set<PublicationsQueryRelevance> getPublicationsQueryRelevances() {
		return this.publicationsQueryRelevances;
	}

	public void setPublicationsQueryRelevances(Set<PublicationsQueryRelevance> publicationsQueryRelevances) {
		this.publicationsQueryRelevances = publicationsQueryRelevances;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "publications")
	public Set<CorpusHasPublications> getCorpusHasPublicationses() {
		return this.corpusHasPublicationses;
	}

	public void setCorpusHasPublicationses(Set<CorpusHasPublications> corpusHasPublicationses) {
		this.corpusHasPublicationses = corpusHasPublicationses;
	}

}
