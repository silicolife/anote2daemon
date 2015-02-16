package com.silicolife.anote2daemon.model.core.entities;

// Generated 5/Fev/2015 14:40:20 by Hibernate Tools 4.0.0

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Resources generated by hbm2java
 */
@Entity
@Table(name = "resources")
public class Resources implements java.io.Serializable {

	private long id;
	private ResourcesType resourcesType;
	private String resourceName;
	private String notes;
	private boolean active;
	private Set<ResourceElements> resourceElementses = new HashSet<ResourceElements>(0);

	public Resources() {
	}

	public Resources(long id, ResourcesType resourcesType, String resourceName, boolean active) {
		this.id = id;
		this.resourcesType = resourcesType;
		this.resourceName = resourceName;
		this.active = active;
	}

	public Resources(long id, ResourcesType resourcesType, String resourceName, String notes, boolean active, Set<ResourceElements> resourceElementses) {
		this.id = id;
		this.resourcesType = resourcesType;
		this.resourceName = resourceName;
		this.notes = notes;
		this.active = active;
		this.resourceElementses = resourceElementses;
	}

	@Id
	@Column(name = "id", unique = true, nullable = false)
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "resources_type_id", nullable = false)
	public ResourcesType getResourcesType() {
		return this.resourcesType;
	}

	public void setResourcesType(ResourcesType resourcesType) {
		this.resourcesType = resourcesType;
	}

	@Column(name = "resource_name", nullable = false, length = 500)
	public String getResourceName() {
		return this.resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	@Column(name = "notes", length = 500)
	public String getNotes() {
		return this.notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	@Column(name = "active", nullable = false)
	public boolean isActive() {
		return this.active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "resources")
	public Set<ResourceElements> getResourceElementses() {
		return this.resourceElementses;
	}

	public void setResourceElementses(Set<ResourceElements> resourceElementses) {
		this.resourceElementses = resourceElementses;
	}

}
