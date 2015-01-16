package com.silicolife.anote2daemon.model.pojo;

// Generated 16/Jan/2015 15:20:27 by Hibernate Tools 4.0.0

/**
 * AnnotationsPropertiesId generated by hbm2java
 */
public class AnnotationsPropertiesId implements java.io.Serializable {

	private String propertyName;
	private long idannotations;

	public AnnotationsPropertiesId() {
	}

	public AnnotationsPropertiesId(String propertyName, long idannotations) {
		this.propertyName = propertyName;
		this.idannotations = idannotations;
	}

	public String getPropertyName() {
		return this.propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	public long getIdannotations() {
		return this.idannotations;
	}

	public void setIdannotations(long idannotations) {
		this.idannotations = idannotations;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof AnnotationsPropertiesId))
			return false;
		AnnotationsPropertiesId castOther = (AnnotationsPropertiesId) other;

		return ((this.getPropertyName() == castOther.getPropertyName()) || (this.getPropertyName() != null && castOther.getPropertyName() != null && this.getPropertyName().equals(castOther.getPropertyName()))) && (this.getIdannotations() == castOther.getIdannotations());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getPropertyName() == null ? 0 : this.getPropertyName().hashCode());
		result = 37 * result + (int) this.getIdannotations();
		return result;
	}

}
