package com.silicolife.anote2daemon.model.pojo;

// Generated 21/Jan/2015 14:28:04 by Hibernate Tools 4.0.0

/**
 * PublicationsFieldsId generated by hbm2java
 */
public class PublicationsFieldsId implements java.io.Serializable {

	private String field;
	private long publicationsId;

	public PublicationsFieldsId() {
	}

	public PublicationsFieldsId(String field, long publicationsId) {
		this.field = field;
		this.publicationsId = publicationsId;
	}

	public String getField() {
		return this.field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public long getPublicationsId() {
		return this.publicationsId;
	}

	public void setPublicationsId(long publicationsId) {
		this.publicationsId = publicationsId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof PublicationsFieldsId))
			return false;
		PublicationsFieldsId castOther = (PublicationsFieldsId) other;

		return ((this.getField() == castOther.getField()) || (this.getField() != null && castOther.getField() != null && this.getField().equals(castOther.getField()))) && (this.getPublicationsId() == castOther.getPublicationsId());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getField() == null ? 0 : this.getField().hashCode());
		result = 37 * result + (int) this.getPublicationsId();
		return result;
	}

}