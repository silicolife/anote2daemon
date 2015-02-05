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
 * CorpusHasProcesses generated by hbm2java
 */
@Entity
@Table(name = "corpus_has_processes")
public class CorpusHasProcesses implements java.io.Serializable {

	private CorpusHasProcessesId id;
	private Processes processes;
	private Corpus corpus;

	public CorpusHasProcesses() {
	}

	public CorpusHasProcesses(CorpusHasProcessesId id, Processes processes, Corpus corpus) {
		this.id = id;
		this.processes = processes;
		this.corpus = corpus;
	}

	@EmbeddedId
	@AttributeOverrides({ @AttributeOverride(name = "corpusId", column = @Column(name = "corpus_id", nullable = false)), @AttributeOverride(name = "processesId", column = @Column(name = "processes_id", nullable = false)) })
	public CorpusHasProcessesId getId() {
		return this.id;
	}

	public void setId(CorpusHasProcessesId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "processes_id", nullable = false, insertable = false, updatable = false)
	public Processes getProcesses() {
		return this.processes;
	}

	public void setProcesses(Processes processes) {
		this.processes = processes;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "corpus_id", nullable = false, insertable = false, updatable = false)
	public Corpus getCorpus() {
		return this.corpus;
	}

	public void setCorpus(Corpus corpus) {
		this.corpus = corpus;
	}

}
