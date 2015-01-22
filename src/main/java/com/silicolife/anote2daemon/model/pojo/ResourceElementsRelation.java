package com.silicolife.anote2daemon.model.pojo;

// Generated 21/Jan/2015 14:28:04 by Hibernate Tools 4.0.0

/**
 * ResourceElementsRelation generated by hbm2java
 */
public class ResourceElementsRelation implements java.io.Serializable {

	private ResourceElementsRelationId id;
	private ResourceElements resourceElementsByResourceElementsIdA;
	private ResourceElements resourceElementsByResourceElementsIdB;
	private RelationType relationType;

	public ResourceElementsRelation() {
	}

	public ResourceElementsRelation(ResourceElementsRelationId id, ResourceElements resourceElementsByResourceElementsIdA, ResourceElements resourceElementsByResourceElementsIdB, RelationType relationType) {
		this.id = id;
		this.resourceElementsByResourceElementsIdA = resourceElementsByResourceElementsIdA;
		this.resourceElementsByResourceElementsIdB = resourceElementsByResourceElementsIdB;
		this.relationType = relationType;
	}

	public ResourceElementsRelationId getId() {
		return this.id;
	}

	public void setId(ResourceElementsRelationId id) {
		this.id = id;
	}

	public ResourceElements getResourceElementsByResourceElementsIdA() {
		return this.resourceElementsByResourceElementsIdA;
	}

	public void setResourceElementsByResourceElementsIdA(ResourceElements resourceElementsByResourceElementsIdA) {
		this.resourceElementsByResourceElementsIdA = resourceElementsByResourceElementsIdA;
	}

	public ResourceElements getResourceElementsByResourceElementsIdB() {
		return this.resourceElementsByResourceElementsIdB;
	}

	public void setResourceElementsByResourceElementsIdB(ResourceElements resourceElementsByResourceElementsIdB) {
		this.resourceElementsByResourceElementsIdB = resourceElementsByResourceElementsIdB;
	}

	public RelationType getRelationType() {
		return this.relationType;
	}

	public void setRelationType(RelationType relationType) {
		this.relationType = relationType;
	}

}
