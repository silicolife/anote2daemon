package com.silicolife.anote2daemon.model.pojo;

// Generated 27/Jan/2015 13:51:08 by Hibernate Tools 4.0.0

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * ProcessGeneralResumeStatsId generated by hbm2java
 */
@Embeddable
public class ProcessGeneralResumeStatsId implements java.io.Serializable {

	private long processesId;
	private String type;
	private long numberType;

	public ProcessGeneralResumeStatsId() {
	}

	public ProcessGeneralResumeStatsId(long processesId, String type, long numberType) {
		this.processesId = processesId;
		this.type = type;
		this.numberType = numberType;
	}

	@Column(name = "processes_id", nullable = false)
	public long getProcessesId() {
		return this.processesId;
	}

	public void setProcessesId(long processesId) {
		this.processesId = processesId;
	}

	@Column(name = "type", nullable = false, length = 4)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "numberType", nullable = false)
	public long getNumberType() {
		return this.numberType;
	}

	public void setNumberType(long numberType) {
		this.numberType = numberType;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ProcessGeneralResumeStatsId))
			return false;
		ProcessGeneralResumeStatsId castOther = (ProcessGeneralResumeStatsId) other;

		return (this.getProcessesId() == castOther.getProcessesId()) && ((this.getType() == castOther.getType()) || (this.getType() != null && castOther.getType() != null && this.getType().equals(castOther.getType()))) && (this.getNumberType() == castOther.getNumberType());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (int) this.getProcessesId();
		result = 37 * result + (getType() == null ? 0 : this.getType().hashCode());
		result = 37 * result + (int) this.getNumberType();
		return result;
	}

}
