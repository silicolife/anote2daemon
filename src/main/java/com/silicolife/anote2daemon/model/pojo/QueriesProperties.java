package com.silicolife.anote2daemon.model.pojo;

// Generated 19/Jan/2015 15:19:18 by Hibernate Tools 4.0.0

/**
 * QueriesProperties generated by hbm2java
 */
public class QueriesProperties implements java.io.Serializable {

	private QueriesPropertiesId id;
	private Queries queries;
	private String propertyValue;

	public QueriesProperties() {
	}

	public QueriesProperties(QueriesPropertiesId id, Queries queries, String propertyValue) {
		this.id = id;
		this.queries = queries;
		this.propertyValue = propertyValue;
	}

	public QueriesPropertiesId getId() {
		return this.id;
	}

	public void setId(QueriesPropertiesId id) {
		this.id = id;
	}

	public Queries getQueries() {
		return this.queries;
	}

	public void setQueries(Queries queries) {
		this.queries = queries;
	}

	public String getPropertyValue() {
		return this.propertyValue;
	}

	public void setPropertyValue(String propertyValue) {
		this.propertyValue = propertyValue;
	}

}
