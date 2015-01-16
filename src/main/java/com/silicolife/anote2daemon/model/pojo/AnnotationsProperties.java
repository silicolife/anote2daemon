package com.silicolife.anote2daemon.model.pojo;

// Generated 16/Jan/2015 15:20:27 by Hibernate Tools 4.0.0

/**
 * AnnotationsProperties generated by hbm2java
 */
public class AnnotationsProperties implements java.io.Serializable {

	private AnnotationsPropertiesId id;
	private Annotations annotations;
	private String propertyValue;

	public AnnotationsProperties() {
	}

	public AnnotationsProperties(AnnotationsPropertiesId id, Annotations annotations, String propertyValue) {
		this.id = id;
		this.annotations = annotations;
		this.propertyValue = propertyValue;
	}

	public AnnotationsPropertiesId getId() {
		return this.id;
	}

	public void setId(AnnotationsPropertiesId id) {
		this.id = id;
	}

	public Annotations getAnnotations() {
		return this.annotations;
	}

	public void setAnnotations(Annotations annotations) {
		this.annotations = annotations;
	}

	public String getPropertyValue() {
		return this.propertyValue;
	}

	public void setPropertyValue(String propertyValue) {
		this.propertyValue = propertyValue;
	}

}
