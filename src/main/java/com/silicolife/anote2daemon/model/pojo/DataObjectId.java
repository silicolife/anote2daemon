package com.silicolife.anote2daemon.model.pojo;

// Generated 16/Jan/2015 16:00:05 by Hibernate Tools 4.0.0

/**
 * DataObjectId generated by hbm2java
 */
public class DataObjectId implements java.io.Serializable {

	private long idResource;
	private long typeResourcesId;

	public DataObjectId() {
	}

	public DataObjectId(long idResource, long typeResourcesId) {
		this.idResource = idResource;
		this.typeResourcesId = typeResourcesId;
	}

	public long getIdResource() {
		return this.idResource;
	}

	public void setIdResource(long idResource) {
		this.idResource = idResource;
	}

	public long getTypeResourcesId() {
		return this.typeResourcesId;
	}

	public void setTypeResourcesId(long typeResourcesId) {
		this.typeResourcesId = typeResourcesId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof DataObjectId))
			return false;
		DataObjectId castOther = (DataObjectId) other;

		return (this.getIdResource() == castOther.getIdResource()) && (this.getTypeResourcesId() == castOther.getTypeResourcesId());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (int) this.getIdResource();
		result = 37 * result + (int) this.getTypeResourcesId();
		return result;
	}

}
