package com.silicolife.anote2daemon.model.pojo;

// Generated 3/Fev/2015 12:37:09 by Hibernate Tools 4.0.0

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * ResourcesClassSynonymsCountId generated by hbm2java
 */
@Embeddable
public class ResourcesClassSynonymsCountId implements java.io.Serializable {

	private long resourcesId;
	private Long classesId;
	private long numberSynonyms;

	public ResourcesClassSynonymsCountId() {
	}

	public ResourcesClassSynonymsCountId(long resourcesId, long numberSynonyms) {
		this.resourcesId = resourcesId;
		this.numberSynonyms = numberSynonyms;
	}

	public ResourcesClassSynonymsCountId(long resourcesId, Long classesId, long numberSynonyms) {
		this.resourcesId = resourcesId;
		this.classesId = classesId;
		this.numberSynonyms = numberSynonyms;
	}

	@Column(name = "resources_id", nullable = false)
	public long getResourcesId() {
		return this.resourcesId;
	}

	public void setResourcesId(long resourcesId) {
		this.resourcesId = resourcesId;
	}

	@Column(name = "classes_id")
	public Long getClassesId() {
		return this.classesId;
	}

	public void setClassesId(Long classesId) {
		this.classesId = classesId;
	}

	@Column(name = "numberSynonyms", nullable = false)
	public long getNumberSynonyms() {
		return this.numberSynonyms;
	}

	public void setNumberSynonyms(long numberSynonyms) {
		this.numberSynonyms = numberSynonyms;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ResourcesClassSynonymsCountId))
			return false;
		ResourcesClassSynonymsCountId castOther = (ResourcesClassSynonymsCountId) other;

		return (this.getResourcesId() == castOther.getResourcesId()) && ((this.getClassesId() == castOther.getClassesId()) || (this.getClassesId() != null && castOther.getClassesId() != null && this.getClassesId().equals(castOther.getClassesId())))
				&& (this.getNumberSynonyms() == castOther.getNumberSynonyms());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (int) this.getResourcesId();
		result = 37 * result + (getClassesId() == null ? 0 : this.getClassesId().hashCode());
		result = 37 * result + (int) this.getNumberSynonyms();
		return result;
	}

}
