package com.silicolife.anote2daemon.model.pojo;

// Generated 16/Jan/2015 16:00:05 by Hibernate Tools 4.0.0

/**
 * ClustersProperties generated by hbm2java
 */
public class ClustersProperties implements java.io.Serializable {

	private ClustersPropertiesId id;
	private ClustersProcess clustersProcess;
	private String value;

	public ClustersProperties() {
	}

	public ClustersProperties(ClustersPropertiesId id, ClustersProcess clustersProcess, String value) {
		this.id = id;
		this.clustersProcess = clustersProcess;
		this.value = value;
	}

	public ClustersPropertiesId getId() {
		return this.id;
	}

	public void setId(ClustersPropertiesId id) {
		this.id = id;
	}

	public ClustersProcess getClustersProcess() {
		return this.clustersProcess;
	}

	public void setClustersProcess(ClustersProcess clustersProcess) {
		this.clustersProcess = clustersProcess;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
