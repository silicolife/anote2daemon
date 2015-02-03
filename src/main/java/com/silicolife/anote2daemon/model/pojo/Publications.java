package com.silicolife.anote2daemon.model.pojo;

// Generated 3/Fev/2015 12:37:09 by Hibernate Tools 4.0.0

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
	private Boolean availablePdf;
	private String fullContent;
	private String notes;
	private Set<AnnotationsLog> annotationsLogs = new HashSet<AnnotationsLog>(0);
	private Set<QueriesHasPublications> queriesHasPublicationses = new HashSet<QueriesHasPublications>(0);
	private Set<PublicationsHasPublicationsSource> publicationsHasPublicationsSources = new HashSet<PublicationsHasPublicationsSource>(0);
	private Set<Annotations> annotationses = new HashSet<Annotations>(0);
	private Set<PublicationsFields> publicationsFieldses = new HashSet<PublicationsFields>(0);
	private Set<PublicationsHasPublicationLabels> publicationsHasPublicationLabelses = new HashSet<PublicationsHasPublicationLabels>(0);
	private Set<CorpusHasPublications> corpusHasPublicationses = new HashSet<CorpusHasPublications>(0);

	public Publications() {
	}

	public Publications(long id) {
		this.id = id;
	}

	public Publications(long id, String title, String pubauthors, String category, Integer pubdate, String fulldate, String pubstatus, String journal, String volume, String issue, String pages, String abstract_, String externalLinks, Boolean availablePdf, String fullContent,
			String notes, Set<AnnotationsLog> annotationsLogs, Set<QueriesHasPublications> queriesHasPublicationses, Set<PublicationsHasPublicationsSource> publicationsHasPublicationsSources, Set<Annotations> annotationses, Set<PublicationsFields> publicationsFieldses,
			Set<PublicationsHasPublicationLabels> publicationsHasPublicationLabelses, Set<CorpusHasPublications> corpusHasPublicationses) {
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
		this.availablePdf = availablePdf;
		this.fullContent = fullContent;
		this.notes = notes;
		this.annotationsLogs = annotationsLogs;
		this.queriesHasPublicationses = queriesHasPublicationses;
		this.publicationsHasPublicationsSources = publicationsHasPublicationsSources;
		this.annotationses = annotationses;
		this.publicationsFieldses = publicationsFieldses;
		this.publicationsHasPublicationLabelses = publicationsHasPublicationLabelses;
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

	@Column(name = "available_pdf")
	public Boolean getAvailablePdf() {
		return this.availablePdf;
	}

	public void setAvailablePdf(Boolean availablePdf) {
		this.availablePdf = availablePdf;
	}

	@Column(name = "fullContent")
	public String getFullContent() {
		return this.fullContent;
	}

	public void setFullContent(String fullContent) {
		this.fullContent = fullContent;
	}

	@Column(name = "notes", length = 16777215)
	public String getNotes() {
		return this.notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
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
	public Set<CorpusHasPublications> getCorpusHasPublicationses() {
		return this.corpusHasPublicationses;
	}

	public void setCorpusHasPublicationses(Set<CorpusHasPublications> corpusHasPublicationses) {
		this.corpusHasPublicationses = corpusHasPublicationses;
	}

}
