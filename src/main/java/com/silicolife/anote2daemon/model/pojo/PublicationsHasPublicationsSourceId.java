package com.silicolife.anote2daemon.model.pojo;

// Generated 19/Jan/2015 15:19:18 by Hibernate Tools 4.0.0

/**
 * PublicationsHasPublicationsSourceId generated by hbm2java
 */
public class PublicationsHasPublicationsSourceId implements java.io.Serializable {

	private long publicationsId;
	private long publicationsSourceId;

	public PublicationsHasPublicationsSourceId() {
	}

	public PublicationsHasPublicationsSourceId(long publicationsId, long publicationsSourceId) {
		this.publicationsId = publicationsId;
		this.publicationsSourceId = publicationsSourceId;
	}

	public long getPublicationsId() {
		return this.publicationsId;
	}

	public void setPublicationsId(long publicationsId) {
		this.publicationsId = publicationsId;
	}

	public long getPublicationsSourceId() {
		return this.publicationsSourceId;
	}

	public void setPublicationsSourceId(long publicationsSourceId) {
		this.publicationsSourceId = publicationsSourceId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof PublicationsHasPublicationsSourceId))
			return false;
		PublicationsHasPublicationsSourceId castOther = (PublicationsHasPublicationsSourceId) other;

		return (this.getPublicationsId() == castOther.getPublicationsId()) && (this.getPublicationsSourceId() == castOther.getPublicationsSourceId());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (int) this.getPublicationsId();
		result = 37 * result + (int) this.getPublicationsSourceId();
		return result;
	}

}