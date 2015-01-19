package com.silicolife.anote2daemon.model.pojo;

// Generated 19/Jan/2015 15:19:18 by Hibernate Tools 4.0.0

import java.util.HashSet;
import java.util.Set;

/**
 * AccessLevels generated by hbm2java
 */
public class AccessLevels implements java.io.Serializable {

	private Long id;
	private CodesAccessLevels codesAccessLevels;
	private String description;
	private Set<GroupsHasAccessLevels> groupsHasAccessLevelses = new HashSet<GroupsHasAccessLevels>(0);

	public AccessLevels() {
	}

	public AccessLevels(CodesAccessLevels codesAccessLevels, String description) {
		this.codesAccessLevels = codesAccessLevels;
		this.description = description;
	}

	public AccessLevels(CodesAccessLevels codesAccessLevels, String description, Set<GroupsHasAccessLevels> groupsHasAccessLevelses) {
		this.codesAccessLevels = codesAccessLevels;
		this.description = description;
		this.groupsHasAccessLevelses = groupsHasAccessLevelses;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public CodesAccessLevels getCodesAccessLevels() {
		return this.codesAccessLevels;
	}

	public void setCodesAccessLevels(CodesAccessLevels codesAccessLevels) {
		this.codesAccessLevels = codesAccessLevels;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<GroupsHasAccessLevels> getGroupsHasAccessLevelses() {
		return this.groupsHasAccessLevelses;
	}

	public void setGroupsHasAccessLevelses(Set<GroupsHasAccessLevels> groupsHasAccessLevelses) {
		this.groupsHasAccessLevelses = groupsHasAccessLevelses;
	}

}