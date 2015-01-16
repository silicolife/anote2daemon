package com.silicolife.anote2daemon.model.pojo;

// Generated 16/Jan/2015 16:00:05 by Hibernate Tools 4.0.0

import java.util.HashSet;
import java.util.Set;

/**
 * Resources generated by hbm2java
 */
public class Resources implements java.io.Serializable {

	private Long idresources;
	private ResourcesType resourcesType;
	private String name;
	private String note;
	private boolean active;
	private Set<ResourceElements> resourceElementses = new HashSet<ResourceElements>(0);
	private Set<ResourcesContent> resourcesContents = new HashSet<ResourcesContent>(0);

	public Resources() {
	}

	public Resources(ResourcesType resourcesType, String name, boolean active) {
		this.resourcesType = resourcesType;
		this.name = name;
		this.active = active;
	}

	public Resources(ResourcesType resourcesType, String name, String note, boolean active, Set<ResourceElements> resourceElementses, Set<ResourcesContent> resourcesContents) {
		this.resourcesType = resourcesType;
		this.name = name;
		this.note = note;
		this.active = active;
		this.resourceElementses = resourceElementses;
		this.resourcesContents = resourcesContents;
	}

	public Long getIdresources() {
		return this.idresources;
	}

	public void setIdresources(Long idresources) {
		this.idresources = idresources;
	}

	public ResourcesType getResourcesType() {
		return this.resourcesType;
	}

	public void setResourcesType(ResourcesType resourcesType) {
		this.resourcesType = resourcesType;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public boolean isActive() {
		return this.active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Set<ResourceElements> getResourceElementses() {
		return this.resourceElementses;
	}

	public void setResourceElementses(Set<ResourceElements> resourceElementses) {
		this.resourceElementses = resourceElementses;
	}

	public Set<ResourcesContent> getResourcesContents() {
		return this.resourcesContents;
	}

	public void setResourcesContents(Set<ResourcesContent> resourcesContents) {
		this.resourcesContents = resourcesContents;
	}

}
