package com.silicolife.anote2daemon.model.pojo;

// Generated 16/Jan/2015 16:00:05 by Hibernate Tools 4.0.0

import java.util.HashSet;
import java.util.Set;

/**
 * Publications generated by hbm2java
 */
public class Publications implements java.io.Serializable {

	private Long id;
	private String title;
	private String authors;
	private String type;
	private Integer date;
	private String fulldate;
	private String status;
	private String journal;
	private String volume;
	private String issue;
	private String pages;
	private String abstract_;
	private String externalLinks;
	private Boolean availablePdf;
	private String fulltext;
	private String notes;
	private Set<AnnotationsLog> annotationsLogs = new HashSet<AnnotationsLog>(0);
	private Set<QueriesHasPublications> queriesHasPublicationses = new HashSet<QueriesHasPublications>(0);
	private Set<PublicationsHasPublicationsSource> publicationsHasPublicationsSources = new HashSet<PublicationsHasPublicationsSource>(0);
	private Set<Annotations> annotationses = new HashSet<Annotations>(0);
	private Set<PublicationFields> publicationFieldses = new HashSet<PublicationFields>(0);
	private Set<PublicationsHasPublicationLabels> publicationsHasPublicationLabelses = new HashSet<PublicationsHasPublicationLabels>(0);
	private Set<DocumentRelevance> documentRelevances = new HashSet<DocumentRelevance>(0);
	private Set<CorpusHasPublications> corpusHasPublicationses = new HashSet<CorpusHasPublications>(0);

	public Publications() {
	}

	public Publications(String title, String authors, String type, Integer date, String fulldate, String status, String journal, String volume, String issue, String pages, String abstract_, String externalLinks, Boolean availablePdf, String fulltext, String notes,
			Set<AnnotationsLog> annotationsLogs, Set<QueriesHasPublications> queriesHasPublicationses, Set<PublicationsHasPublicationsSource> publicationsHasPublicationsSources, Set<Annotations> annotationses, Set<PublicationFields> publicationFieldses,
			Set<PublicationsHasPublicationLabels> publicationsHasPublicationLabelses, Set<DocumentRelevance> documentRelevances, Set<CorpusHasPublications> corpusHasPublicationses) {
		this.title = title;
		this.authors = authors;
		this.type = type;
		this.date = date;
		this.fulldate = fulldate;
		this.status = status;
		this.journal = journal;
		this.volume = volume;
		this.issue = issue;
		this.pages = pages;
		this.abstract_ = abstract_;
		this.externalLinks = externalLinks;
		this.availablePdf = availablePdf;
		this.fulltext = fulltext;
		this.notes = notes;
		this.annotationsLogs = annotationsLogs;
		this.queriesHasPublicationses = queriesHasPublicationses;
		this.publicationsHasPublicationsSources = publicationsHasPublicationsSources;
		this.annotationses = annotationses;
		this.publicationFieldses = publicationFieldses;
		this.publicationsHasPublicationLabelses = publicationsHasPublicationLabelses;
		this.documentRelevances = documentRelevances;
		this.corpusHasPublicationses = corpusHasPublicationses;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthors() {
		return this.authors;
	}

	public void setAuthors(String authors) {
		this.authors = authors;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getDate() {
		return this.date;
	}

	public void setDate(Integer date) {
		this.date = date;
	}

	public String getFulldate() {
		return this.fulldate;
	}

	public void setFulldate(String fulldate) {
		this.fulldate = fulldate;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getJournal() {
		return this.journal;
	}

	public void setJournal(String journal) {
		this.journal = journal;
	}

	public String getVolume() {
		return this.volume;
	}

	public void setVolume(String volume) {
		this.volume = volume;
	}

	public String getIssue() {
		return this.issue;
	}

	public void setIssue(String issue) {
		this.issue = issue;
	}

	public String getPages() {
		return this.pages;
	}

	public void setPages(String pages) {
		this.pages = pages;
	}

	public String getAbstract_() {
		return this.abstract_;
	}

	public void setAbstract_(String abstract_) {
		this.abstract_ = abstract_;
	}

	public String getExternalLinks() {
		return this.externalLinks;
	}

	public void setExternalLinks(String externalLinks) {
		this.externalLinks = externalLinks;
	}

	public Boolean getAvailablePdf() {
		return this.availablePdf;
	}

	public void setAvailablePdf(Boolean availablePdf) {
		this.availablePdf = availablePdf;
	}

	public String getFulltext() {
		return this.fulltext;
	}

	public void setFulltext(String fulltext) {
		this.fulltext = fulltext;
	}

	public String getNotes() {
		return this.notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Set<AnnotationsLog> getAnnotationsLogs() {
		return this.annotationsLogs;
	}

	public void setAnnotationsLogs(Set<AnnotationsLog> annotationsLogs) {
		this.annotationsLogs = annotationsLogs;
	}

	public Set<QueriesHasPublications> getQueriesHasPublicationses() {
		return this.queriesHasPublicationses;
	}

	public void setQueriesHasPublicationses(Set<QueriesHasPublications> queriesHasPublicationses) {
		this.queriesHasPublicationses = queriesHasPublicationses;
	}

	public Set<PublicationsHasPublicationsSource> getPublicationsHasPublicationsSources() {
		return this.publicationsHasPublicationsSources;
	}

	public void setPublicationsHasPublicationsSources(Set<PublicationsHasPublicationsSource> publicationsHasPublicationsSources) {
		this.publicationsHasPublicationsSources = publicationsHasPublicationsSources;
	}

	public Set<Annotations> getAnnotationses() {
		return this.annotationses;
	}

	public void setAnnotationses(Set<Annotations> annotationses) {
		this.annotationses = annotationses;
	}

	public Set<PublicationFields> getPublicationFieldses() {
		return this.publicationFieldses;
	}

	public void setPublicationFieldses(Set<PublicationFields> publicationFieldses) {
		this.publicationFieldses = publicationFieldses;
	}

	public Set<PublicationsHasPublicationLabels> getPublicationsHasPublicationLabelses() {
		return this.publicationsHasPublicationLabelses;
	}

	public void setPublicationsHasPublicationLabelses(Set<PublicationsHasPublicationLabels> publicationsHasPublicationLabelses) {
		this.publicationsHasPublicationLabelses = publicationsHasPublicationLabelses;
	}

	public Set<DocumentRelevance> getDocumentRelevances() {
		return this.documentRelevances;
	}

	public void setDocumentRelevances(Set<DocumentRelevance> documentRelevances) {
		this.documentRelevances = documentRelevances;
	}

	public Set<CorpusHasPublications> getCorpusHasPublicationses() {
		return this.corpusHasPublicationses;
	}

	public void setCorpusHasPublicationses(Set<CorpusHasPublications> corpusHasPublicationses) {
		this.corpusHasPublicationses = corpusHasPublicationses;
	}

}
