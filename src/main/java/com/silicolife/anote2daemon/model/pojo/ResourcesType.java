package com.silicolife.anote2daemon.model.pojo;

// Generated 27/Jan/2015 13:51:08 by Hibernate Tools 4.0.0

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * ResourcesType generated by hbm2java
 */
@Entity
@Table(name = "resources_type", uniqueConstraints = @UniqueConstraint(columnNames = "type"))
public class ResourcesType implements java.io.Serializable {

	private long id;
	private String type;
	private Set<Resources> resourceses = new HashSet<Resources>(0);

	public ResourcesType() {
	}

	public ResourcesType(long id, String type) {
		this.id = id;
		this.type = type;
	}

	public ResourcesType(long id, String type, Set<Resources> resourceses) {
		this.id = id;
		this.type = type;
		this.resourceses = resourceses;
	}

	@Id
	@Column(name = "id", unique = true, nullable = false)
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(name = "type", unique = true, nullable = false, length = 250)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "resourcesType")
	public Set<Resources> getResourceses() {
		return this.resourceses;
	}

	public void setResourceses(Set<Resources> resourceses) {
		this.resourceses = resourceses;
	}

}
