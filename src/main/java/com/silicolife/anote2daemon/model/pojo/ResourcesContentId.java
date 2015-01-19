package com.silicolife.anote2daemon.model.pojo;

// Generated 19/Jan/2015 15:19:18 by Hibernate Tools 4.0.0

/**
 * ResourcesContentId generated by hbm2java
 */
public class ResourcesContentId implements java.io.Serializable {

	private long resourcesIdresources;
	private long classesIdclasses;

	public ResourcesContentId() {
	}

	public ResourcesContentId(long resourcesIdresources, long classesIdclasses) {
		this.resourcesIdresources = resourcesIdresources;
		this.classesIdclasses = classesIdclasses;
	}

	public long getResourcesIdresources() {
		return this.resourcesIdresources;
	}

	public void setResourcesIdresources(long resourcesIdresources) {
		this.resourcesIdresources = resourcesIdresources;
	}

	public long getClassesIdclasses() {
		return this.classesIdclasses;
	}

	public void setClassesIdclasses(long classesIdclasses) {
		this.classesIdclasses = classesIdclasses;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ResourcesContentId))
			return false;
		ResourcesContentId castOther = (ResourcesContentId) other;

		return (this.getResourcesIdresources() == castOther.getResourcesIdresources()) && (this.getClassesIdclasses() == castOther.getClassesIdclasses());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (int) this.getResourcesIdresources();
		result = 37 * result + (int) this.getClassesIdclasses();
		return result;
	}

}