package com.silicolife.anote2daemon.model.pojo;

// Generated 21/Jan/2015 14:28:04 by Hibernate Tools 4.0.0

/**
 * CorpusProperties generated by hbm2java
 */
public class CorpusProperties implements java.io.Serializable {

	private CorpusPropertiesId id;
	private Corpus corpus;
	private String value;

	public CorpusProperties() {
	}

	public CorpusProperties(CorpusPropertiesId id, Corpus corpus, String value) {
		this.id = id;
		this.corpus = corpus;
		this.value = value;
	}

	public CorpusPropertiesId getId() {
		return this.id;
	}

	public void setId(CorpusPropertiesId id) {
		this.id = id;
	}

	public Corpus getCorpus() {
		return this.corpus;
	}

	public void setCorpus(Corpus corpus) {
		this.corpus = corpus;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
