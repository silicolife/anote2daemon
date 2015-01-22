package com.silicolife.anote2daemon.model.pojo;

// Generated 21/Jan/2015 14:28:04 by Hibernate Tools 4.0.0

import java.util.HashSet;
import java.util.Set;

/**
 * Processes generated by hbm2java
 */
public class Processes implements java.io.Serializable {

	private long id;
	private ProcessesType processesType;
	private ProcessesOrigin processesOrigin;
	private String name;
	private String notes;
	private boolean active;
	private Set<ProcessesProperties> processesPropertieses = new HashSet<ProcessesProperties>(0);
	private Set<CorpusHasProcesses> corpusHasProcesseses = new HashSet<CorpusHasProcesses>(0);
	private Set<Annotations> annotationses = new HashSet<Annotations>(0);
	private Set<AnnotationsLog> annotationsLogs = new HashSet<AnnotationsLog>(0);

	public Processes() {
	}

	public Processes(long id, ProcessesType processesType, ProcessesOrigin processesOrigin, boolean active) {
		this.id = id;
		this.processesType = processesType;
		this.processesOrigin = processesOrigin;
		this.active = active;
	}

	public Processes(long id, ProcessesType processesType, ProcessesOrigin processesOrigin, String name, String notes, boolean active, Set<ProcessesProperties> processesPropertieses, Set<CorpusHasProcesses> corpusHasProcesseses, Set<Annotations> annotationses,
			Set<AnnotationsLog> annotationsLogs) {
		this.id = id;
		this.processesType = processesType;
		this.processesOrigin = processesOrigin;
		this.name = name;
		this.notes = notes;
		this.active = active;
		this.processesPropertieses = processesPropertieses;
		this.corpusHasProcesseses = corpusHasProcesseses;
		this.annotationses = annotationses;
		this.annotationsLogs = annotationsLogs;
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public ProcessesType getProcessesType() {
		return this.processesType;
	}

	public void setProcessesType(ProcessesType processesType) {
		this.processesType = processesType;
	}

	public ProcessesOrigin getProcessesOrigin() {
		return this.processesOrigin;
	}

	public void setProcessesOrigin(ProcessesOrigin processesOrigin) {
		this.processesOrigin = processesOrigin;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNotes() {
		return this.notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public boolean isActive() {
		return this.active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Set<ProcessesProperties> getProcessesPropertieses() {
		return this.processesPropertieses;
	}

	public void setProcessesPropertieses(Set<ProcessesProperties> processesPropertieses) {
		this.processesPropertieses = processesPropertieses;
	}

	public Set<CorpusHasProcesses> getCorpusHasProcesseses() {
		return this.corpusHasProcesseses;
	}

	public void setCorpusHasProcesseses(Set<CorpusHasProcesses> corpusHasProcesseses) {
		this.corpusHasProcesseses = corpusHasProcesseses;
	}

	public Set<Annotations> getAnnotationses() {
		return this.annotationses;
	}

	public void setAnnotationses(Set<Annotations> annotationses) {
		this.annotationses = annotationses;
	}

	public Set<AnnotationsLog> getAnnotationsLogs() {
		return this.annotationsLogs;
	}

	public void setAnnotationsLogs(Set<AnnotationsLog> annotationsLogs) {
		this.annotationsLogs = annotationsLogs;
	}

}
