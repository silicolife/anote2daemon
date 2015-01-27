package com.silicolife.anote2daemon.model.pojo;

// Generated 27/Jan/2015 18:22:29 by Hibernate Tools 4.0.0

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * ResourceElementsRelationId generated by hbm2java
 */
@Embeddable
public class ResourceElementsRelationId implements java.io.Serializable {

	private long resourceElementsIdB;
	private long resourceElementsIdA;

	public ResourceElementsRelationId() {
	}

	public ResourceElementsRelationId(long resourceElementsIdB, long resourceElementsIdA) {
		this.resourceElementsIdB = resourceElementsIdB;
		this.resourceElementsIdA = resourceElementsIdA;
	}

	@Column(name = "resource_elements_id_b", nullable = false)
	public long getResourceElementsIdB() {
		return this.resourceElementsIdB;
	}

	public void setResourceElementsIdB(long resourceElementsIdB) {
		this.resourceElementsIdB = resourceElementsIdB;
	}

	@Column(name = "resource_elements_id_a", nullable = false)
	public long getResourceElementsIdA() {
		return this.resourceElementsIdA;
	}

	public void setResourceElementsIdA(long resourceElementsIdA) {
		this.resourceElementsIdA = resourceElementsIdA;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ResourceElementsRelationId))
			return false;
		ResourceElementsRelationId castOther = (ResourceElementsRelationId) other;

		return (this.getResourceElementsIdB() == castOther.getResourceElementsIdB()) && (this.getResourceElementsIdA() == castOther.getResourceElementsIdA());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (int) this.getResourceElementsIdB();
		result = 37 * result + (int) this.getResourceElementsIdA();
		return result;
	}

}
