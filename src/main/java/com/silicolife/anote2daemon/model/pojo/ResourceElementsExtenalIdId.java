package com.silicolife.anote2daemon.model.pojo;

// Generated 19/Jan/2015 15:19:18 by Hibernate Tools 4.0.0

/**
 * ResourceElementsExtenalIdId generated by hbm2java
 */
public class ResourceElementsExtenalIdId implements java.io.Serializable {

	private long resourceElementsIdresourceElements;
	private String externalId;
	private long sourcesIdsources;

	public ResourceElementsExtenalIdId() {
	}

	public ResourceElementsExtenalIdId(long resourceElementsIdresourceElements, String externalId, long sourcesIdsources) {
		this.resourceElementsIdresourceElements = resourceElementsIdresourceElements;
		this.externalId = externalId;
		this.sourcesIdsources = sourcesIdsources;
	}

	public long getResourceElementsIdresourceElements() {
		return this.resourceElementsIdresourceElements;
	}

	public void setResourceElementsIdresourceElements(long resourceElementsIdresourceElements) {
		this.resourceElementsIdresourceElements = resourceElementsIdresourceElements;
	}

	public String getExternalId() {
		return this.externalId;
	}

	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}

	public long getSourcesIdsources() {
		return this.sourcesIdsources;
	}

	public void setSourcesIdsources(long sourcesIdsources) {
		this.sourcesIdsources = sourcesIdsources;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ResourceElementsExtenalIdId))
			return false;
		ResourceElementsExtenalIdId castOther = (ResourceElementsExtenalIdId) other;

		return (this.getResourceElementsIdresourceElements() == castOther.getResourceElementsIdresourceElements())
				&& ((this.getExternalId() == castOther.getExternalId()) || (this.getExternalId() != null && castOther.getExternalId() != null && this.getExternalId().equals(castOther.getExternalId()))) && (this.getSourcesIdsources() == castOther.getSourcesIdsources());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (int) this.getResourceElementsIdresourceElements();
		result = 37 * result + (getExternalId() == null ? 0 : this.getExternalId().hashCode());
		result = 37 * result + (int) this.getSourcesIdsources();
		return result;
	}

}