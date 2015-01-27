package com.silicolife.anote2daemon.model.pojo;

// Generated 27/Jan/2015 13:51:08 by Hibernate Tools 4.0.0

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
 * Synonyms generated by hbm2java
 */
@Entity
@Table(name = "synonyms")
public class Synonyms implements java.io.Serializable {

	private SynonymsId id;
	private ResourceElements resourceElements;

	public Synonyms() {
	}

	public Synonyms(SynonymsId id, ResourceElements resourceElements) {
		this.id = id;
		this.resourceElements = resourceElements;
	}

	@EmbeddedId
	@AttributeOverrides({ @AttributeOverride(name = "resourceElementsId", column = @Column(name = "resource_elements_id", nullable = false)), @AttributeOverride(name = "synonym", column = @Column(name = "synonym", nullable = false, length = 500)),
			@AttributeOverride(name = "active", column = @Column(name = "active", nullable = false)) })
	public SynonymsId getId() {
		return this.id;
	}

	public void setId(SynonymsId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "resource_elements_id", nullable = false, insertable = false, updatable = false)
	public ResourceElements getResourceElements() {
		return this.resourceElements;
	}

	public void setResourceElements(ResourceElements resourceElements) {
		this.resourceElements = resourceElements;
	}

}
