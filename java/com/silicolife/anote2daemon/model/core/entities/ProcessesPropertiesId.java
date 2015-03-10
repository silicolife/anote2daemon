package com.silicolife.anote2daemon.model.core.entities;

// Generated 5/Fev/2015 14:40:20 by Hibernate Tools 4.0.0

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * ProcessesPropertiesId generated by hbm2java
 */
@Embeddable
public class ProcessesPropertiesId implements java.io.Serializable {

	private long processesId;
	private String propKey;

	public ProcessesPropertiesId() {
	}

	public ProcessesPropertiesId(long processesId, String propKey) {
		this.processesId = processesId;
		this.propKey = propKey;
	}

	@Column(name = "processes_id", nullable = false)
	public long getProcessesId() {
		return this.processesId;
	}

	public void setProcessesId(long processesId) {
		this.processesId = processesId;
	}

	@Column(name = "prop_key", nullable = false, length = 250)
	public String getPropKey() {
		return this.propKey;
	}

	public void setPropKey(String propKey) {
		this.propKey = propKey;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ProcessesPropertiesId))
			return false;
		ProcessesPropertiesId castOther = (ProcessesPropertiesId) other;

		return (this.getProcessesId() == castOther.getProcessesId()) && ((this.getPropKey() == castOther.getPropKey()) || (this.getPropKey() != null && castOther.getPropKey() != null && this.getPropKey().equals(castOther.getPropKey())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (int) this.getProcessesId();
		result = 37 * result + (getPropKey() == null ? 0 : this.getPropKey().hashCode());
		return result;
	}

}