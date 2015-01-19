package com.silicolife.anote2daemon.model.pojo;

// Generated 19/Jan/2015 15:19:18 by Hibernate Tools 4.0.0

import java.util.HashSet;
import java.util.Set;

/**
 * Annotations generated by hbm2java
 */
public class Annotations implements java.io.Serializable {

	private Long idannotations;
	private Corpus corpus;
	private Publications publications;
	private Processes processes;
	private ResourceElements resourceElements;
	private Classes classes;
	private int start;
	private int end;
	private String element;
	private String normalizationForm;
	private String type;
	private String clue;
	private String classificationRe;
	private boolean active;
	private Integer startSentenceOffset;
	private Integer endSentenceOffset;
	private Set<AnnotationsSide> annotationsSidesForAnnotationsIdannotations = new HashSet<AnnotationsSide>(0);
	private Set<AnnotationsProperties> annotationsPropertieses = new HashSet<AnnotationsProperties>(0);
	private Set<AnnotationsSide> annotationsSidesForIdannotationsSub = new HashSet<AnnotationsSide>(0);
	private Set<AnnotationsLog> annotationsLogs = new HashSet<AnnotationsLog>(0);
	private Set<AnnotationsSide> annotationsSidesForIdannotations = new HashSet<AnnotationsSide>(0);

	public Annotations() {
	}

	public Annotations(Corpus corpus, Publications publications, Processes processes, int start, int end, String type, boolean active) {
		this.corpus = corpus;
		this.publications = publications;
		this.processes = processes;
		this.start = start;
		this.end = end;
		this.type = type;
		this.active = active;
	}

	public Annotations(Corpus corpus, Publications publications, Processes processes, ResourceElements resourceElements, Classes classes, int start, int end, String element, String normalizationForm, String type, String clue, String classificationRe, boolean active,
			Integer startSentenceOffset, Integer endSentenceOffset, Set<AnnotationsSide> annotationsSidesForAnnotationsIdannotations, Set<AnnotationsProperties> annotationsPropertieses, Set<AnnotationsSide> annotationsSidesForIdannotationsSub,
			Set<AnnotationsLog> annotationsLogs, Set<AnnotationsSide> annotationsSidesForIdannotations) {
		this.corpus = corpus;
		this.publications = publications;
		this.processes = processes;
		this.resourceElements = resourceElements;
		this.classes = classes;
		this.start = start;
		this.end = end;
		this.element = element;
		this.normalizationForm = normalizationForm;
		this.type = type;
		this.clue = clue;
		this.classificationRe = classificationRe;
		this.active = active;
		this.startSentenceOffset = startSentenceOffset;
		this.endSentenceOffset = endSentenceOffset;
		this.annotationsSidesForAnnotationsIdannotations = annotationsSidesForAnnotationsIdannotations;
		this.annotationsPropertieses = annotationsPropertieses;
		this.annotationsSidesForIdannotationsSub = annotationsSidesForIdannotationsSub;
		this.annotationsLogs = annotationsLogs;
		this.annotationsSidesForIdannotations = annotationsSidesForIdannotations;
	}

	public Long getIdannotations() {
		return this.idannotations;
	}

	public void setIdannotations(Long idannotations) {
		this.idannotations = idannotations;
	}

	public Corpus getCorpus() {
		return this.corpus;
	}

	public void setCorpus(Corpus corpus) {
		this.corpus = corpus;
	}

	public Publications getPublications() {
		return this.publications;
	}

	public void setPublications(Publications publications) {
		this.publications = publications;
	}

	public Processes getProcesses() {
		return this.processes;
	}

	public void setProcesses(Processes processes) {
		this.processes = processes;
	}

	public ResourceElements getResourceElements() {
		return this.resourceElements;
	}

	public void setResourceElements(ResourceElements resourceElements) {
		this.resourceElements = resourceElements;
	}

	public Classes getClasses() {
		return this.classes;
	}

	public void setClasses(Classes classes) {
		this.classes = classes;
	}

	public int getStart() {
		return this.start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return this.end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public String getElement() {
		return this.element;
	}

	public void setElement(String element) {
		this.element = element;
	}

	public String getNormalizationForm() {
		return this.normalizationForm;
	}

	public void setNormalizationForm(String normalizationForm) {
		this.normalizationForm = normalizationForm;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getClue() {
		return this.clue;
	}

	public void setClue(String clue) {
		this.clue = clue;
	}

	public String getClassificationRe() {
		return this.classificationRe;
	}

	public void setClassificationRe(String classificationRe) {
		this.classificationRe = classificationRe;
	}

	public boolean isActive() {
		return this.active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Integer getStartSentenceOffset() {
		return this.startSentenceOffset;
	}

	public void setStartSentenceOffset(Integer startSentenceOffset) {
		this.startSentenceOffset = startSentenceOffset;
	}

	public Integer getEndSentenceOffset() {
		return this.endSentenceOffset;
	}

	public void setEndSentenceOffset(Integer endSentenceOffset) {
		this.endSentenceOffset = endSentenceOffset;
	}

	public Set<AnnotationsSide> getAnnotationsSidesForAnnotationsIdannotations() {
		return this.annotationsSidesForAnnotationsIdannotations;
	}

	public void setAnnotationsSidesForAnnotationsIdannotations(Set<AnnotationsSide> annotationsSidesForAnnotationsIdannotations) {
		this.annotationsSidesForAnnotationsIdannotations = annotationsSidesForAnnotationsIdannotations;
	}

	public Set<AnnotationsProperties> getAnnotationsPropertieses() {
		return this.annotationsPropertieses;
	}

	public void setAnnotationsPropertieses(Set<AnnotationsProperties> annotationsPropertieses) {
		this.annotationsPropertieses = annotationsPropertieses;
	}

	public Set<AnnotationsSide> getAnnotationsSidesForIdannotationsSub() {
		return this.annotationsSidesForIdannotationsSub;
	}

	public void setAnnotationsSidesForIdannotationsSub(Set<AnnotationsSide> annotationsSidesForIdannotationsSub) {
		this.annotationsSidesForIdannotationsSub = annotationsSidesForIdannotationsSub;
	}

	public Set<AnnotationsLog> getAnnotationsLogs() {
		return this.annotationsLogs;
	}

	public void setAnnotationsLogs(Set<AnnotationsLog> annotationsLogs) {
		this.annotationsLogs = annotationsLogs;
	}

	public Set<AnnotationsSide> getAnnotationsSidesForIdannotations() {
		return this.annotationsSidesForIdannotations;
	}

	public void setAnnotationsSidesForIdannotations(Set<AnnotationsSide> annotationsSidesForIdannotations) {
		this.annotationsSidesForIdannotations = annotationsSidesForIdannotations;
	}

}
