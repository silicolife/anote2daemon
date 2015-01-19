package com.silicolife.anote2daemon.model.pojo;

// Generated 19/Jan/2015 15:19:18 by Hibernate Tools 4.0.0

import java.util.Date;

/**
 * Log generated by hbm2java
 */
public class Log implements java.io.Serializable {

	private Long id;
	private Users users;
	private Date createDate;
	private String operation;
	private String tableChanged;
	private String sqlCommand;
	private String notes;

	public Log() {
	}

	public Log(Users users, Date createDate, String operation, String tableChanged) {
		this.users = users;
		this.createDate = createDate;
		this.operation = operation;
		this.tableChanged = tableChanged;
	}

	public Log(Users users, Date createDate, String operation, String tableChanged, String sqlCommand, String notes) {
		this.users = users;
		this.createDate = createDate;
		this.operation = operation;
		this.tableChanged = tableChanged;
		this.sqlCommand = sqlCommand;
		this.notes = notes;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Users getUsers() {
		return this.users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getOperation() {
		return this.operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getTableChanged() {
		return this.tableChanged;
	}

	public void setTableChanged(String tableChanged) {
		this.tableChanged = tableChanged;
	}

	public String getSqlCommand() {
		return this.sqlCommand;
	}

	public void setSqlCommand(String sqlCommand) {
		this.sqlCommand = sqlCommand;
	}

	public String getNotes() {
		return this.notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

}
