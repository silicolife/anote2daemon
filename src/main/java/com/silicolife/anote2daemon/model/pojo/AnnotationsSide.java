package com.silicolife.anote2daemon.model.pojo;

// Generated 16/Jan/2015 15:20:27 by Hibernate Tools 4.0.0

/**
 * AnnotationsSide generated by hbm2java
 */
public class AnnotationsSide implements java.io.Serializable {

	private AnnotationsSideId id;
	private Annotations annotationsByIdannotationsSub;
	private Annotations annotationsByIdannotations;
	private Annotations annotationsByAnnotationsIdannotations;
	private String type;

	public AnnotationsSide() {
	}

	public AnnotationsSide(AnnotationsSideId id, Annotations annotationsByIdannotationsSub, Annotations annotationsByIdannotations, Annotations annotationsByAnnotationsIdannotations, String type) {
		this.id = id;
		this.annotationsByIdannotationsSub = annotationsByIdannotationsSub;
		this.annotationsByIdannotations = annotationsByIdannotations;
		this.annotationsByAnnotationsIdannotations = annotationsByAnnotationsIdannotations;
		this.type = type;
	}

	public AnnotationsSideId getId() {
		return this.id;
	}

	public void setId(AnnotationsSideId id) {
		this.id = id;
	}

	public Annotations getAnnotationsByIdannotationsSub() {
		return this.annotationsByIdannotationsSub;
	}

	public void setAnnotationsByIdannotationsSub(Annotations annotationsByIdannotationsSub) {
		this.annotationsByIdannotationsSub = annotationsByIdannotationsSub;
	}

	public Annotations getAnnotationsByIdannotations() {
		return this.annotationsByIdannotations;
	}

	public void setAnnotationsByIdannotations(Annotations annotationsByIdannotations) {
		this.annotationsByIdannotations = annotationsByIdannotations;
	}

	public Annotations getAnnotationsByAnnotationsIdannotations() {
		return this.annotationsByAnnotationsIdannotations;
	}

	public void setAnnotationsByAnnotationsIdannotations(Annotations annotationsByAnnotationsIdannotations) {
		this.annotationsByAnnotationsIdannotations = annotationsByAnnotationsIdannotations;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
