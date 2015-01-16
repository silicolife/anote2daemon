package com.silicolife.anote2daemon.model.pojo;

// Generated 16/Jan/2015 15:20:27 by Hibernate Tools 4.0.0

/**
 * CorpusProperties generated by hbm2java
 */
public class CorpusProperties implements java.io.Serializable {

	private CorpusPropertiesId id;
	private Corpus corpus;
	private String propertyValue;

	public CorpusProperties() {
	}

	public CorpusProperties(CorpusPropertiesId id, Corpus corpus, String propertyValue) {
		this.id = id;
		this.corpus = corpus;
		this.propertyValue = propertyValue;
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

	public String getPropertyValue() {
		return this.propertyValue;
	}

	public void setPropertyValue(String propertyValue) {
		this.propertyValue = propertyValue;
	}

}
