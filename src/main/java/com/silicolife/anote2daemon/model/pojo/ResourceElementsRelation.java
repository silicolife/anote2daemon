package com.silicolife.anote2daemon.model.pojo;

// Generated 3/Fev/2015 12:37:09 by Hibernate Tools 4.0.0

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
 * ResourceElementsRelation generated by hbm2java
 */
@Entity
@Table(name = "resource_elements_relation")
public class ResourceElementsRelation implements java.io.Serializable {

	private ResourceElementsRelationId id;
	private ResourceElements resourceElementsByResourceElementsIdA;
	private ResourceElements resourceElementsByResourceElementsIdB;
	private ResourceElementsRelationType resourceElementsRelationType;

	public ResourceElementsRelation() {
	}

	public ResourceElementsRelation(ResourceElementsRelationId id, ResourceElements resourceElementsByResourceElementsIdA, ResourceElements resourceElementsByResourceElementsIdB, ResourceElementsRelationType resourceElementsRelationType) {
		this.id = id;
		this.resourceElementsByResourceElementsIdA = resourceElementsByResourceElementsIdA;
		this.resourceElementsByResourceElementsIdB = resourceElementsByResourceElementsIdB;
		this.resourceElementsRelationType = resourceElementsRelationType;
	}

	@EmbeddedId
	@AttributeOverrides({ @AttributeOverride(name = "resourceElementsIdB", column = @Column(name = "resource_elements_id_b", nullable = false)), @AttributeOverride(name = "resourceElementsIdA", column = @Column(name = "resource_elements_id_a", nullable = false)) })
	public ResourceElementsRelationId getId() {
		return this.id;
	}

	public void setId(ResourceElementsRelationId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "resource_elements_id_a", nullable = false, insertable = false, updatable = false)
	public ResourceElements getResourceElementsByResourceElementsIdA() {
		return this.resourceElementsByResourceElementsIdA;
	}

	public void setResourceElementsByResourceElementsIdA(ResourceElements resourceElementsByResourceElementsIdA) {
		this.resourceElementsByResourceElementsIdA = resourceElementsByResourceElementsIdA;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "resource_elements_id_b", nullable = false, insertable = false, updatable = false)
	public ResourceElements getResourceElementsByResourceElementsIdB() {
		return this.resourceElementsByResourceElementsIdB;
	}

	public void setResourceElementsByResourceElementsIdB(ResourceElements resourceElementsByResourceElementsIdB) {
		this.resourceElementsByResourceElementsIdB = resourceElementsByResourceElementsIdB;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "relation_type_id", nullable = false)
	public ResourceElementsRelationType getResourceElementsRelationType() {
		return this.resourceElementsRelationType;
	}

	public void setResourceElementsRelationType(ResourceElementsRelationType resourceElementsRelationType) {
		this.resourceElementsRelationType = resourceElementsRelationType;
	}

}
