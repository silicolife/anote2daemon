package com.silicolife.anote2daemon.model.pojo;

// Generated 21/Jan/2015 14:28:04 by Hibernate Tools 4.0.0

/**
 * PublictaionQueryRelevanceId generated by hbm2java
 */
public class PublictaionQueryRelevanceId implements java.io.Serializable {

	private long publicationsId;
	private long queriesId;

	public PublictaionQueryRelevanceId() {
	}

	public PublictaionQueryRelevanceId(long publicationsId, long queriesId) {
		this.publicationsId = publicationsId;
		this.queriesId = queriesId;
	}

	public long getPublicationsId() {
		return this.publicationsId;
	}

	public void setPublicationsId(long publicationsId) {
		this.publicationsId = publicationsId;
	}

	public long getQueriesId() {
		return this.queriesId;
	}

	public void setQueriesId(long queriesId) {
		this.queriesId = queriesId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof PublictaionQueryRelevanceId))
			return false;
		PublictaionQueryRelevanceId castOther = (PublictaionQueryRelevanceId) other;

		return (this.getPublicationsId() == castOther.getPublicationsId()) && (this.getQueriesId() == castOther.getQueriesId());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (int) this.getPublicationsId();
		result = 37 * result + (int) this.getQueriesId();
		return result;
	}

}