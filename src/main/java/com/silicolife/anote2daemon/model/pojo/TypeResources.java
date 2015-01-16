package com.silicolife.anote2daemon.model.pojo;

// Generated 16/Jan/2015 15:20:27 by Hibernate Tools 4.0.0

import java.util.HashSet;
import java.util.Set;

/**
 * TypeResources generated by hbm2java
 */
public class TypeResources implements java.io.Serializable {

	private long id;
	private String description;
	private Set<DataObject> dataObjects = new HashSet<DataObject>(0);

	public TypeResources() {
	}

	public TypeResources(long id, String description) {
		this.id = id;
		this.description = description;
	}

	public TypeResources(long id, String description, Set<DataObject> dataObjects) {
		this.id = id;
		this.description = description;
		this.dataObjects = dataObjects;
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<DataObject> getDataObjects() {
		return this.dataObjects;
	}

	public void setDataObjects(Set<DataObject> dataObjects) {
		this.dataObjects = dataObjects;
	}

}
