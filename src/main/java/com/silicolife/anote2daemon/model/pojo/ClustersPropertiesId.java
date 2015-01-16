package com.silicolife.anote2daemon.model.pojo;

// Generated 16/Jan/2015 16:00:05 by Hibernate Tools 4.0.0

/**
 * ClustersPropertiesId generated by hbm2java
 */
public class ClustersPropertiesId implements java.io.Serializable {

	private long clustersProcessId;
	private String key;

	public ClustersPropertiesId() {
	}

	public ClustersPropertiesId(long clustersProcessId, String key) {
		this.clustersProcessId = clustersProcessId;
		this.key = key;
	}

	public long getClustersProcessId() {
		return this.clustersProcessId;
	}

	public void setClustersProcessId(long clustersProcessId) {
		this.clustersProcessId = clustersProcessId;
	}

	public String getKey() {
		return this.key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ClustersPropertiesId))
			return false;
		ClustersPropertiesId castOther = (ClustersPropertiesId) other;

		return (this.getClustersProcessId() == castOther.getClustersProcessId()) && ((this.getKey() == castOther.getKey()) || (this.getKey() != null && castOther.getKey() != null && this.getKey().equals(castOther.getKey())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (int) this.getClustersProcessId();
		result = 37 * result + (getKey() == null ? 0 : this.getKey().hashCode());
		return result;
	}

}
