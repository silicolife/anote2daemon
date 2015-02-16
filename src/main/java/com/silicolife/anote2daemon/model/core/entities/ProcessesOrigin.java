package com.silicolife.anote2daemon.model.core.entities;

// Generated 5/Fev/2015 14:40:20 by Hibernate Tools 4.0.0

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
 * ProcessesOrigin generated by hbm2java
 */
@Entity
@Table(name = "processes_origin", uniqueConstraints = @UniqueConstraint(columnNames = "description"))
public class ProcessesOrigin implements java.io.Serializable {

	private long id;
	private String description;
	private Set<Processes> processeses = new HashSet<Processes>(0);

	public ProcessesOrigin() {
	}

	public ProcessesOrigin(long id, String description) {
		this.id = id;
		this.description = description;
	}

	public ProcessesOrigin(long id, String description, Set<Processes> processeses) {
		this.id = id;
		this.description = description;
		this.processeses = processeses;
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "processesOrigin")
	public Set<Processes> getProcesseses() {
		return this.processeses;
	}

	public void setProcesseses(Set<Processes> processeses) {
		this.processeses = processeses;
	}

}
