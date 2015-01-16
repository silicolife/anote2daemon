package com.silicolife.anote2daemon.model.pojo;

// Generated 16/Jan/2015 15:20:27 by Hibernate Tools 4.0.0

/**
 * ProcessProperties generated by hbm2java
 */
public class ProcessProperties implements java.io.Serializable {

	private ProcessPropertiesId id;
	private Processes processes;
	private String propertyValue;

	public ProcessProperties() {
	}

	public ProcessProperties(ProcessPropertiesId id, Processes processes, String propertyValue) {
		this.id = id;
		this.processes = processes;
		this.propertyValue = propertyValue;
	}

	public ProcessPropertiesId getId() {
		return this.id;
	}

	public void setId(ProcessPropertiesId id) {
		this.id = id;
	}

	public Processes getProcesses() {
		return this.processes;
	}

	public void setProcesses(Processes processes) {
		this.processes = processes;
	}

	public String getPropertyValue() {
		return this.propertyValue;
	}

	public void setPropertyValue(String propertyValue) {
		this.propertyValue = propertyValue;
	}

}
