package com.silicolife.anote2daemon.model.pojo;

// Generated 27/Jan/2015 13:51:08 by Hibernate Tools 4.0.0

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
 * CorpusHasPublications generated by hbm2java
 */
@Entity
@Table(name = "corpus_has_publications")
public class CorpusHasPublications implements java.io.Serializable {

	private CorpusHasPublicationsId id;
	private Publications publications;
	private Corpus corpus;

	public CorpusHasPublications() {
	}

	public CorpusHasPublications(CorpusHasPublicationsId id, Publications publications, Corpus corpus) {
		this.id = id;
		this.publications = publications;
		this.corpus = corpus;
	}

	@EmbeddedId
	@AttributeOverrides({ @AttributeOverride(name = "corpusId", column = @Column(name = "corpus_id", nullable = false)), @AttributeOverride(name = "publicationsId", column = @Column(name = "publications_id", nullable = false)) })
	public CorpusHasPublicationsId getId() {
		return this.id;
	}

	public void setId(CorpusHasPublicationsId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "publications_id", nullable = false, insertable = false, updatable = false)
	public Publications getPublications() {
		return this.publications;
	}

	public void setPublications(Publications publications) {
		this.publications = publications;
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
