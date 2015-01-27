package com.silicolife.anote2daemon.model.pojo;

// Generated 27/Jan/2015 18:22:29 by Hibernate Tools 4.0.0

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
 * ResourceElementsExtenalId generated by hbm2java
 */
@Entity
@Table(name = "resource_elements_extenal_id")
public class ResourceElementsExtenalId implements java.io.Serializable {

	private ResourceElementsExtenalIdId id;
	private ResourceElements resourceElements;
	private Sources sources;
	private boolean active;

	public ResourceElementsExtenalId() {
	}

	public ResourceElementsExtenalId(ResourceElementsExtenalIdId id, ResourceElements resourceElements, Sources sources, boolean active) {
		this.id = id;
		this.resourceElements = resourceElements;
		this.sources = sources;
		this.active = active;
	}

	@EmbeddedId
	@AttributeOverrides({ @AttributeOverride(name = "resourceElementsId", column = @Column(name = "resource_elements_id", nullable = false)), @AttributeOverride(name = "sourcesId", column = @Column(name = "sources_id", nullable = false)),
			@AttributeOverride(name = "externalId", column = @Column(name = "external_id", nullable = false, length = 200)) })
	public ResourceElementsExtenalIdId getId() {
		return this.id;
	}

	public void setId(ResourceElementsExtenalIdId id) {
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sources_id", nullable = false, insertable = false, updatable = false)
	public Sources getSources() {
		return this.sources;
	}

	public void setSources(Sources sources) {
		this.sources = sources;
	}

	@Column(name = "active", nullable = false)
	public boolean isActive() {
		return this.active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

}
