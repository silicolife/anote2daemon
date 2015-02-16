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
 * ProcessesType generated by hbm2java
 */
@Entity
@Table(name = "processes_type", uniqueConstraints = @UniqueConstraint(columnNames = "process_type"))
public class ProcessesType implements java.io.Serializable {

	private long id;
	private String processType;
	private Set<Processes> processeses = new HashSet<Processes>(0);

	public ProcessesType() {
	}

	public ProcessesType(long id, String processType) {
		this.id = id;
		this.processType = processType;
	}

	public ProcessesType(long id, String processType, Set<Processes> processeses) {
		this.id = id;
		this.processType = processType;
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

	@Column(name = "process_type", unique = true, nullable = false, length = 250)
	public String getProcessType() {
		return this.processType;
	}

	public void setProcessType(String processType) {
		this.processType = processType;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "processesType")
	public Set<Processes> getProcesseses() {
		return this.processeses;
	}

	public void setProcesseses(Set<Processes> processeses) {
		this.processeses = processeses;
	}

}
