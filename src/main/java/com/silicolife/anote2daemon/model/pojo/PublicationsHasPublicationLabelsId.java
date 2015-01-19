package com.silicolife.anote2daemon.model.pojo;

// Generated 19/Jan/2015 15:19:18 by Hibernate Tools 4.0.0

/**
 * PublicationsHasPublicationLabelsId generated by hbm2java
 */
public class PublicationsHasPublicationLabelsId implements java.io.Serializable {

	private long publicationsId;
	private long publicationLabelsId;

	public PublicationsHasPublicationLabelsId() {
	}

	public PublicationsHasPublicationLabelsId(long publicationsId, long publicationLabelsId) {
		this.publicationsId = publicationsId;
		this.publicationLabelsId = publicationLabelsId;
	}

	public long getPublicationsId() {
		return this.publicationsId;
	}

	public void setPublicationsId(long publicationsId) {
		this.publicationsId = publicationsId;
	}

	public long getPublicationLabelsId() {
		return this.publicationLabelsId;
	}

	public void setPublicationLabelsId(long publicationLabelsId) {
		this.publicationLabelsId = publicationLabelsId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof PublicationsHasPublicationLabelsId))
			return false;
		PublicationsHasPublicationLabelsId castOther = (PublicationsHasPublicationLabelsId) other;

		return (this.getPublicationsId() == castOther.getPublicationsId()) && (this.getPublicationLabelsId() == castOther.getPublicationLabelsId());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (int) this.getPublicationsId();
		result = 37 * result + (int) this.getPublicationLabelsId();
		return result;
	}

}