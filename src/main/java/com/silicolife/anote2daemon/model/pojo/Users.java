package com.silicolife.anote2daemon.model.pojo;

// Generated 16/Jan/2015 15:20:27 by Hibernate Tools 4.0.0

import java.util.HashSet;
import java.util.Set;

/**
 * Users generated by hbm2java
 */
public class Users implements java.io.Serializable {

	private long id;
	private Groups groups;
	private String username;
	private String password;
	private String name;
	private String email;
	private Integer phone;
	private String address;
	private String zipCode;
	private String location;
	private Set<Log> logs = new HashSet<Log>(0);
	private Set<UsersHasDataObject> usersHasDataObjects = new HashSet<UsersHasDataObject>(0);

	public Users() {
	}

	public Users(long id, Groups groups, String username, String password, String name, String email) {
		this.id = id;
		this.groups = groups;
		this.username = username;
		this.password = password;
		this.name = name;
		this.email = email;
	}

	public Users(long id, Groups groups, String username, String password, String name, String email, Integer phone, String address, String zipCode, String location, Set<Log> logs, Set<UsersHasDataObject> usersHasDataObjects) {
		this.id = id;
		this.groups = groups;
		this.username = username;
		this.password = password;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.zipCode = zipCode;
		this.location = location;
		this.logs = logs;
		this.usersHasDataObjects = usersHasDataObjects;
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Groups getGroups() {
		return this.groups;
	}

	public void setGroups(Groups groups) {
		this.groups = groups;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getPhone() {
		return this.phone;
	}

	public void setPhone(Integer phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getZipCode() {
		return this.zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Set<Log> getLogs() {
		return this.logs;
	}

	public void setLogs(Set<Log> logs) {
		this.logs = logs;
	}

	public Set<UsersHasDataObject> getUsersHasDataObjects() {
		return this.usersHasDataObjects;
	}

	public void setUsersHasDataObjects(Set<UsersHasDataObject> usersHasDataObjects) {
		this.usersHasDataObjects = usersHasDataObjects;
	}

}
