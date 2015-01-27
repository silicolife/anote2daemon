package com.silicolife.anote2daemon.model.pojo;

// Generated 27/Jan/2015 13:51:08 by Hibernate Tools 4.0.0

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * ResourcesClassStatsId generated by hbm2java
 */
@Embeddable
public class ResourcesClassStatsId implements java.io.Serializable {

	private long resourcesId;
	private Long classesId;
	private long numberClasses;

	public ResourcesClassStatsId() {
	}

	public ResourcesClassStatsId(long resourcesId, long numberClasses) {
		this.resourcesId = resourcesId;
		this.numberClasses = numberClasses;
	}

	public ResourcesClassStatsId(long resourcesId, Long classesId, long numberClasses) {
		this.resourcesId = resourcesId;
		this.classesId = classesId;
		this.numberClasses = numberClasses;
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

	@Column(name = "numberClasses", nullable = false)
	public long getNumberClasses() {
		return this.numberClasses;
	}

	public void setNumberClasses(long numberClasses) {
		this.numberClasses = numberClasses;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ResourcesClassStatsId))
			return false;
		ResourcesClassStatsId castOther = (ResourcesClassStatsId) other;

		return (this.getResourcesId() == castOther.getResourcesId()) && ((this.getClassesId() == castOther.getClassesId()) || (this.getClassesId() != null && castOther.getClassesId() != null && this.getClassesId().equals(castOther.getClassesId())))
				&& (this.getNumberClasses() == castOther.getNumberClasses());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (int) this.getResourcesId();
		result = 37 * result + (getClassesId() == null ? 0 : this.getClassesId().hashCode());
		result = 37 * result + (int) this.getNumberClasses();
		return result;
	}

}
