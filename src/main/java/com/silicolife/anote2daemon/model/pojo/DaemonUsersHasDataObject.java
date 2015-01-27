package com.silicolife.anote2daemon.model.pojo;

// Generated 27/Jan/2015 18:22:29 by Hibernate Tools 4.0.0

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * DaemonUsersHasDataObject generated by hbm2java
 */
@Entity
@Table(name = "daemon_users_has_data_object")
public class DaemonUsersHasDataObject implements java.io.Serializable {

	private DaemonUsersHasDataObjectId id;
	private DaemonDataObject daemonDataObject;
	private DaemonUsers daemonUsers;
	private String accesLevel;

	public DaemonUsersHasDataObject() {
	}

	public DaemonUsersHasDataObject(DaemonUsersHasDataObjectId id, DaemonDataObject daemonDataObject, DaemonUsers daemonUsers, String accesLevel) {
		this.id = id;
		this.daemonDataObject = daemonDataObject;
		this.daemonUsers = daemonUsers;
		this.accesLevel = accesLevel;
	}

	@EmbeddedId
	@AttributeOverrides({ @AttributeOverride(name = "usersId", column = @Column(name = "users_id", nullable = false)), @AttributeOverride(name = "uidResource", column = @Column(name = "uid_resource", nullable = false)),
			@AttributeOverride(name = "typeResourcesId", column = @Column(name = "type_resources_id", nullable = false)) })
	public DaemonUsersHasDataObjectId getId() {
		return this.id;
	}

	public void setId(DaemonUsersHasDataObjectId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "uid_resource", referencedColumnName = "id_resource", nullable = false, insertable = false, updatable = false),
			@JoinColumn(name = "type_resources_id", referencedColumnName = "type_resources_id", nullable = false, insertable = false, updatable = false) })
	public DaemonDataObject getDaemonDataObject() {
		return this.daemonDataObject;
	}

	public void setDaemonDataObject(DaemonDataObject daemonDataObject) {
		this.daemonDataObject = daemonDataObject;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "users_id", nullable = false, insertable = false, updatable = false)
	public DaemonUsers getDaemonUsers() {
		return this.daemonUsers;
	}

	public void setDaemonUsers(DaemonUsers daemonUsers) {
		this.daemonUsers = daemonUsers;
	}

	@Column(name = "acces_level", nullable = false, length = 10)
	public String getAccesLevel() {
		return this.accesLevel;
	}

	public void setAccesLevel(String accesLevel) {
		this.accesLevel = accesLevel;
	}

}
