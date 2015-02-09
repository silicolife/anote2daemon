package com.silicolife.anote2daemon.model.core.entities;

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
 * Corpus generated by hbm2java
 */
@Entity
@Table(name = "corpus")
public class Corpus implements java.io.Serializable {

	private long id;
	private String corpusName;
	private boolean active;
	private String notes;
	private Set<CorpusHasPublications> corpusHasPublicationses = new HashSet<CorpusHasPublications>(0);
	private Set<AnnotationsLog> annotationsLogs = new HashSet<AnnotationsLog>(0);
	private Set<CorpusProperties> corpusPropertieses = new HashSet<CorpusProperties>(0);
	private Set<Annotations> annotationses = new HashSet<Annotations>(0);
	private Set<CorpusHasProcesses> corpusHasProcesseses = new HashSet<CorpusHasProcesses>(0);

	public Corpus() {
	}

	public Corpus(long id, String corpusName, boolean active) {
		this.id = id;
		this.corpusName = corpusName;
		this.active = active;
	}

	public Corpus(long id, String corpusName, boolean active, String notes, Set<CorpusHasPublications> corpusHasPublicationses, Set<AnnotationsLog> annotationsLogs, Set<CorpusProperties> corpusPropertieses, Set<Annotations> annotationses,
			Set<CorpusHasProcesses> corpusHasProcesseses) {
		this.id = id;
		this.corpusName = corpusName;
		this.active = active;
		this.notes = notes;
		this.corpusHasPublicationses = corpusHasPublicationses;
		this.annotationsLogs = annotationsLogs;
		this.corpusPropertieses = corpusPropertieses;
		this.annotationses = annotationses;
		this.corpusHasProcesseses = corpusHasProcesseses;
	}

	@Id
	@Column(name = "id", unique = true, nullable = false)
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(name = "corpus_name", nullable = false, length = 500)
	public String getCorpusName() {
		return this.corpusName;
	}

	public void setCorpusName(String corpusName) {
		this.corpusName = corpusName;
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "corpus")
	public Set<CorpusHasPublications> getCorpusHasPublicationses() {
		return this.corpusHasPublicationses;
	}

	public void setCorpusHasPublicationses(Set<CorpusHasPublications> corpusHasPublicationses) {
		this.corpusHasPublicationses = corpusHasPublicationses;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "corpus")
	public Set<AnnotationsLog> getAnnotationsLogs() {
		return this.annotationsLogs;
	}

	public void setAnnotationsLogs(Set<AnnotationsLog> annotationsLogs) {
		this.annotationsLogs = annotationsLogs;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "corpus")
	public Set<CorpusProperties> getCorpusPropertieses() {
		return this.corpusPropertieses;
	}

	public void setCorpusPropertieses(Set<CorpusProperties> corpusPropertieses) {
		this.corpusPropertieses = corpusPropertieses;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "corpus")
	public Set<Annotations> getAnnotationses() {
		return this.annotationses;
	}

	public void setAnnotationses(Set<Annotations> annotationses) {
		this.annotationses = annotationses;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "corpus")
	public Set<CorpusHasProcesses> getCorpusHasProcesseses() {
		return this.corpusHasProcesseses;
	}

	public void setCorpusHasProcesseses(Set<CorpusHasProcesses> corpusHasProcesseses) {
		this.corpusHasProcesseses = corpusHasProcesseses;
	}

}