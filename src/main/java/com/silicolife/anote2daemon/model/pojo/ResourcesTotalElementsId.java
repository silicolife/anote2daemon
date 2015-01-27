package com.silicolife.anote2daemon.model.pojo;

// Generated 27/Jan/2015 13:51:08 by Hibernate Tools 4.0.0

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * ResourcesTotalElementsId generated by hbm2java
 */
@Embeddable
public class ResourcesTotalElementsId implements java.io.Serializable {

	private long resourcesId;
	private long numberElements;

	public ResourcesTotalElementsId() {
	}

	public ResourcesTotalElementsId(long resourcesId, long numberElements) {
		this.resourcesId = resourcesId;
		this.numberElements = numberElements;
	}

	@Column(name = "resources_id", nullable = false)
	public long getResourcesId() {
		return this.resourcesId;
	}

	public void setResourcesId(long resourcesId) {
		this.resourcesId = resourcesId;
	}

	@Column(name = "numberElements", nullable = false)
	public long getNumberElements() {
		return this.numberElements;
	}

	public void setNumberElements(long numberElements) {
		this.numberElements = numberElements;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ResourcesTotalElementsId))
			return false;
		ResourcesTotalElementsId castOther = (ResourcesTotalElementsId) other;

		return (this.getResourcesId() == castOther.getResourcesId()) && (this.getNumberElements() == castOther.getNumberElements());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (int) this.getResourcesId();
		result = 37 * result + (int) this.getNumberElements();
		return result;
	}

}
