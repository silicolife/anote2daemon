package com.silicolife.anote2daemon.model.pojo;

// Generated 16/Jan/2015 16:00:05 by Hibernate Tools 4.0.0

import java.util.HashSet;
import java.util.Set;

/**
 * ProcessesType generated by hbm2java
 */
public class ProcessesType implements java.io.Serializable {

	private Long idprocessesType;
	private String type;
	private Set<Processes> processeses = new HashSet<Processes>(0);

	public ProcessesType() {
	}

	public ProcessesType(String type) {
		this.type = type;
	}

	public ProcessesType(String type, Set<Processes> processeses) {
		this.type = type;
		this.processeses = processeses;
	}

	public Long getIdprocessesType() {
		return this.idprocessesType;
	}

	public void setIdprocessesType(Long idprocessesType) {
		this.idprocessesType = idprocessesType;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Set<Processes> getProcesseses() {
		return this.processeses;
	}

	public void setProcesseses(Set<Processes> processeses) {
		this.processeses = processeses;
	}

}
