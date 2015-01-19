package com.silicolife.anote2daemon.model.pojo;

// Generated 19/Jan/2015 15:19:18 by Hibernate Tools 4.0.0

/**
 * CorpusHasPublicationsId generated by hbm2java
 */
public class CorpusHasPublicationsId implements java.io.Serializable {

	private long corpusIdcorpus;
	private long publicationsId;

	public CorpusHasPublicationsId() {
	}

	public CorpusHasPublicationsId(long corpusIdcorpus, long publicationsId) {
		this.corpusIdcorpus = corpusIdcorpus;
		this.publicationsId = publicationsId;
	}

	public long getCorpusIdcorpus() {
		return this.corpusIdcorpus;
	}

	public void setCorpusIdcorpus(long corpusIdcorpus) {
		this.corpusIdcorpus = corpusIdcorpus;
	}

	public long getPublicationsId() {
		return this.publicationsId;
	}

	public void setPublicationsId(long publicationsId) {
		this.publicationsId = publicationsId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof CorpusHasPublicationsId))
			return false;
		CorpusHasPublicationsId castOther = (CorpusHasPublicationsId) other;

		return (this.getCorpusIdcorpus() == castOther.getCorpusIdcorpus()) && (this.getPublicationsId() == castOther.getPublicationsId());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (int) this.getCorpusIdcorpus();
		result = 37 * result + (int) this.getPublicationsId();
		return result;
	}

}