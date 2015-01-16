package com.silicolife.anote2daemon.model.pojo;

// Generated 16/Jan/2015 16:00:05 by Hibernate Tools 4.0.0

import java.util.HashSet;
import java.util.Set;

/**
 * RelationType generated by hbm2java
 */
public class RelationType implements java.io.Serializable {

	private Long idrelationType;
	private String type;
	private Set<ResourceElementsRelation> resourceElementsRelations = new HashSet<ResourceElementsRelation>(0);

	public RelationType() {
	}

	public RelationType(String type) {
		this.type = type;
	}

	public RelationType(String type, Set<ResourceElementsRelation> resourceElementsRelations) {
		this.type = type;
		this.resourceElementsRelations = resourceElementsRelations;
	}

	public Long getIdrelationType() {
		return this.idrelationType;
	}

	public void setIdrelationType(Long idrelationType) {
		this.idrelationType = idrelationType;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Set<ResourceElementsRelation> getResourceElementsRelations() {
		return this.resourceElementsRelations;
	}

	public void setResourceElementsRelations(Set<ResourceElementsRelation> resourceElementsRelations) {
		this.resourceElementsRelations = resourceElementsRelations;
	}

}
