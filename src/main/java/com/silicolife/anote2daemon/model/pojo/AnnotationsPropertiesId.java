package com.silicolife.anote2daemon.model.pojo;

// Generated 27/Jan/2015 18:22:29 by Hibernate Tools 4.0.0

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * AnnotationsPropertiesId generated by hbm2java
 */
@Embeddable
public class AnnotationsPropertiesId implements java.io.Serializable {

	private String key;
	private long id;

	public AnnotationsPropertiesId() {
	}

	public AnnotationsPropertiesId(String key, long id) {
		this.key = key;
		this.id = id;
	}

	@Column(name = "key", nullable = false)
	public String getKey() {
		return this.key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	@Column(name = "id", nullable = false)
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof AnnotationsPropertiesId))
			return false;
		AnnotationsPropertiesId castOther = (AnnotationsPropertiesId) other;

		return ((this.getKey() == castOther.getKey()) || (this.getKey() != null && castOther.getKey() != null && this.getKey().equals(castOther.getKey()))) && (this.getId() == castOther.getId());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getKey() == null ? 0 : this.getKey().hashCode());
		result = 37 * result + (int) this.getId();
		return result;
	}

}
