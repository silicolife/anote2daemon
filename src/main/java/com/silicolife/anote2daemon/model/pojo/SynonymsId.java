package com.silicolife.anote2daemon.model.pojo;

// Generated 27/Jan/2015 13:51:08 by Hibernate Tools 4.0.0

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * SynonymsId generated by hbm2java
 */
@Embeddable
public class SynonymsId implements java.io.Serializable {

	private long resourceElementsId;
	private String synonym;
	private boolean active;

	public SynonymsId() {
	}

	public SynonymsId(long resourceElementsId, String synonym, boolean active) {
		this.resourceElementsId = resourceElementsId;
		this.synonym = synonym;
		this.active = active;
	}

	@Column(name = "resource_elements_id", nullable = false)
	public long getResourceElementsId() {
		return this.resourceElementsId;
	}

	public void setResourceElementsId(long resourceElementsId) {
		this.resourceElementsId = resourceElementsId;
	}

	@Column(name = "synonym", nullable = false, length = 500)
	public String getSynonym() {
		return this.synonym;
	}

	public void setSynonym(String synonym) {
		this.synonym = synonym;
	}

	@Column(name = "active", nullable = false)
	public boolean isActive() {
		return this.active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof SynonymsId))
			return false;
		SynonymsId castOther = (SynonymsId) other;

		return (this.getResourceElementsId() == castOther.getResourceElementsId()) && ((this.getSynonym() == castOther.getSynonym()) || (this.getSynonym() != null && castOther.getSynonym() != null && this.getSynonym().equals(castOther.getSynonym())))
				&& (this.isActive() == castOther.isActive());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (int) this.getResourceElementsId();
		result = 37 * result + (getSynonym() == null ? 0 : this.getSynonym().hashCode());
		result = 37 * result + (this.isActive() ? 1 : 0);
		return result;
	}

}
