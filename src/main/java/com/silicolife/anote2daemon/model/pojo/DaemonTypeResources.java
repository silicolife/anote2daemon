package com.silicolife.anote2daemon.model.pojo;

// Generated 3/Fev/2015 12:37:09 by Hibernate Tools 4.0.0

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
 * DaemonTypeResources generated by hbm2java
 */
@Entity
@Table(name = "daemon_type_resources", uniqueConstraints = @UniqueConstraint(columnNames = "description"))
public class DaemonTypeResources implements java.io.Serializable {

	private long id;
	private String description;
	private Set<DaemonDataObject> daemonDataObjects = new HashSet<DaemonDataObject>(0);

	public DaemonTypeResources() {
	}

	public DaemonTypeResources(long id, String description) {
		this.id = id;
		this.description = description;
	}

	public DaemonTypeResources(long id, String description, Set<DaemonDataObject> daemonDataObjects) {
		this.id = id;
		this.description = description;
		this.daemonDataObjects = daemonDataObjects;
	}

	@Id
	@Column(name = "id", unique = true, nullable = false)
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(name = "description", unique = true, nullable = false)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "daemonTypeResources")
	public Set<DaemonDataObject> getDaemonDataObjects() {
		return this.daemonDataObjects;
	}

	public void setDaemonDataObjects(Set<DaemonDataObject> daemonDataObjects) {
		this.daemonDataObjects = daemonDataObjects;
	}

}
