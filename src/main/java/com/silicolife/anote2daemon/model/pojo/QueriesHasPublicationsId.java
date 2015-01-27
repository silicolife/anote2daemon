package com.silicolife.anote2daemon.model.pojo;

// Generated 27/Jan/2015 13:51:08 by Hibernate Tools 4.0.0

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * QueriesHasPublicationsId generated by hbm2java
 */
@Embeddable
public class QueriesHasPublicationsId implements java.io.Serializable {

	private long queriesId;
	private long publicationsId;

	public QueriesHasPublicationsId() {
	}

	public QueriesHasPublicationsId(long queriesId, long publicationsId) {
		this.queriesId = queriesId;
		this.publicationsId = publicationsId;
	}

	@Column(name = "queries_id", nullable = false)
	public long getQueriesId() {
		return this.queriesId;
	}

	public void setQueriesId(long queriesId) {
		this.queriesId = queriesId;
	}

	@Column(name = "publications_id", nullable = false)
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
		if (!(other instanceof QueriesHasPublicationsId))
			return false;
		QueriesHasPublicationsId castOther = (QueriesHasPublicationsId) other;

		return (this.getQueriesId() == castOther.getQueriesId()) && (this.getPublicationsId() == castOther.getPublicationsId());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (int) this.getQueriesId();
		result = 37 * result + (int) this.getPublicationsId();
		return result;
	}

}
