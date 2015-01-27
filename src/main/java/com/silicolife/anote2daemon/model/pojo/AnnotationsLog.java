package com.silicolife.anote2daemon.model.pojo;

// Generated 27/Jan/2015 13:51:08 by Hibernate Tools 4.0.0

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * AnnotationsLog generated by hbm2java
 */
@Entity
@Table(name = "annotations_log")
public class AnnotationsLog implements java.io.Serializable {

	private long id;
	private Annotations annotations;
	private Publications publications;
	private Processes processes;
	private Corpus corpus;
	private String type;
	private String old;
	private String new_;
	private String notes;
	private Date date;

	public AnnotationsLog() {
	}

	public AnnotationsLog(long id, Publications publications, Processes processes, Corpus corpus, String type, String old, String new_, Date date) {
		this.id = id;
		this.publications = publications;
		this.processes = processes;
		this.corpus = corpus;
		this.type = type;
		this.old = old;
		this.new_ = new_;
		this.date = date;
	}

	public AnnotationsLog(long id, Annotations annotations, Publications publications, Processes processes, Corpus corpus, String type, String old, String new_, String notes, Date date) {
		this.id = id;
		this.annotations = annotations;
		this.publications = publications;
		this.processes = processes;
		this.corpus = corpus;
		this.type = type;
		this.old = old;
		this.new_ = new_;
		this.notes = notes;
		this.date = date;
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
	@JoinColumn(name = "annotations_idannotations")
	public Annotations getAnnotations() {
		return this.annotations;
	}

	public void setAnnotations(Annotations annotations) {
		this.annotations = annotations;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "publications_id", nullable = false)
	public Publications getPublications() {
		return this.publications;
	}

	public void setPublications(Publications publications) {
		this.publications = publications;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "processes_idprocesses", nullable = false)
	public Processes getProcesses() {
		return this.processes;
	}

	public void setProcesses(Processes processes) {
		this.processes = processes;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "corpus_idcorpus", nullable = false)
	public Corpus getCorpus() {
		return this.corpus;
	}

	public void setCorpus(Corpus corpus) {
		this.corpus = corpus;
	}

	@Column(name = "type", nullable = false, length = 14)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "old", nullable = false, length = 300)
	public String getOld() {
		return this.old;
	}

	public void setOld(String old) {
		this.old = old;
	}

	@Column(name = "new", nullable = false, length = 300)
	public String getNew_() {
		return this.new_;
	}

	public void setNew_(String new_) {
		this.new_ = new_;
	}

	@Column(name = "notes", length = 65535)
	public String getNotes() {
		return this.notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date", nullable = false, length = 19)
	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
