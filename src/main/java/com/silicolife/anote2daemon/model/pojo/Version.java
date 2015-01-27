package com.silicolife.anote2daemon.model.pojo;

// Generated 27/Jan/2015 13:51:08 by Hibernate Tools 4.0.0

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Version generated by hbm2java
 */
@Entity
@Table(name = "version")
public class Version implements java.io.Serializable {

	private long version;
	private Date date;
	private String notes;

	public Version() {
	}

	public Version(long version, Date date) {
		this.version = version;
		this.date = date;
	}

	public Version(long version, Date date, String notes) {
		this.version = version;
		this.date = date;
		this.notes = notes;
	}

	@Id
	@Column(name = "version", unique = true, nullable = false)
	public long getVersion() {
		return this.version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date", nullable = false, length = 10)
	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Column(name = "notes", length = 500)
	public String getNotes() {
		return this.notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

}
