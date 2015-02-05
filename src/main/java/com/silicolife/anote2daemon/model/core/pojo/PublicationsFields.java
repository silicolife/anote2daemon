package com.silicolife.anote2daemon.model.core.pojo;

// Generated 5/Fev/2015 14:40:20 by Hibernate Tools 4.0.0

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * PublicationsFields generated by hbm2java
 */
@Entity
@Table(name = "publications_fields")
public class PublicationsFields implements java.io.Serializable {

	private PublicationsFieldsId id;
	private Publications publications;
	private long fieldStart;
	private long fieldEnd;

	public PublicationsFields() {
	}

	public PublicationsFields(PublicationsFieldsId id, Publications publications, long fieldStart, long fieldEnd) {
		this.id = id;
		this.publications = publications;
		this.fieldStart = fieldStart;
		this.fieldEnd = fieldEnd;
	}

	@EmbeddedId
	@AttributeOverrides({ @AttributeOverride(name = "field", column = @Column(name = "field", nullable = false, length = 200)), @AttributeOverride(name = "publicationsId", column = @Column(name = "publications_id", nullable = false)) })
	public PublicationsFieldsId getId() {
		return this.id;
	}

	public void setId(PublicationsFieldsId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "publications_id", nullable = false, insertable = false, updatable = false)
	public Publications getPublications() {
		return this.publications;
	}

	public void setPublications(Publications publications) {
		this.publications = publications;
	}

	@Column(name = "field_start", nullable = false)
	public long getFieldStart() {
		return this.fieldStart;
	}

	public void setFieldStart(long fieldStart) {
		this.fieldStart = fieldStart;
	}

	@Column(name = "field_end", nullable = false)
	public long getFieldEnd() {
		return this.fieldEnd;
	}

	public void setFieldEnd(long fieldEnd) {
		this.fieldEnd = fieldEnd;
	}

}
