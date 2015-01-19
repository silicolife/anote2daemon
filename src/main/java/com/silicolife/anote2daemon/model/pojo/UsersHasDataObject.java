package com.silicolife.anote2daemon.model.pojo;

// Generated 19/Jan/2015 15:19:18 by Hibernate Tools 4.0.0

/**
 * UsersHasDataObject generated by hbm2java
 */
public class UsersHasDataObject implements java.io.Serializable {

	private UsersHasDataObjectId id;
	private DataObject dataObject;
	private Users users;
	private String accesLevel;

	public UsersHasDataObject() {
	}

	public UsersHasDataObject(UsersHasDataObjectId id, DataObject dataObject, Users users, String accesLevel) {
		this.id = id;
		this.dataObject = dataObject;
		this.users = users;
		this.accesLevel = accesLevel;
	}

	public UsersHasDataObjectId getId() {
		return this.id;
	}

	public void setId(UsersHasDataObjectId id) {
		this.id = id;
	}

	public DataObject getDataObject() {
		return this.dataObject;
	}

	public void setDataObject(DataObject dataObject) {
		this.dataObject = dataObject;
	}

	public Users getUsers() {
		return this.users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public String getAccesLevel() {
		return this.accesLevel;
	}

	public void setAccesLevel(String accesLevel) {
		this.accesLevel = accesLevel;
	}

}