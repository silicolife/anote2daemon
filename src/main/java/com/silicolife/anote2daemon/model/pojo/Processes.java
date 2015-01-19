package com.silicolife.anote2daemon.model.pojo;

// Generated 19/Jan/2015 15:19:18 by Hibernate Tools 4.0.0

import java.util.HashSet;
import java.util.Set;

/**
 * Processes generated by hbm2java
 */
public class Processes implements java.io.Serializable {

	private Long idprocesses;
	private ProcessOrigin processOrigin;
	private ProcessesType processesType;
	private String name;
	private boolean active;
	private Set<ProcessProperties> processPropertieses = new HashSet<ProcessProperties>(0);
	private Set<CorpusHasProcesses> corpusHasProcesseses = new HashSet<CorpusHasProcesses>(0);
	private Set<Annotations> annotationses = new HashSet<Annotations>(0);
	private Set<AnnotationsLog> annotationsLogs = new HashSet<AnnotationsLog>(0);

	public Processes() {
	}

	public Processes(ProcessOrigin processOrigin, ProcessesType processesType, boolean active) {
		this.processOrigin = processOrigin;
		this.processesType = processesType;
		this.active = active;
	}

	public Processes(ProcessOrigin processOrigin, ProcessesType processesType, String name, boolean active, Set<ProcessProperties> processPropertieses, Set<CorpusHasProcesses> corpusHasProcesseses, Set<Annotations> annotationses, Set<AnnotationsLog> annotationsLogs) {
		this.processOrigin = processOrigin;
		this.processesType = processesType;
		this.name = name;
		this.active = active;
		this.processPropertieses = processPropertieses;
		this.corpusHasProcesseses = corpusHasProcesseses;
		this.annotationses = annotationses;
		this.annotationsLogs = annotationsLogs;
	}

	public Long getIdprocesses() {
		return this.idprocesses;
	}

	public void setIdprocesses(Long idprocesses) {
		this.idprocesses = idprocesses;
	}

	public ProcessOrigin getProcessOrigin() {
		return this.processOrigin;
	}

	public void setProcessOrigin(ProcessOrigin processOrigin) {
		this.processOrigin = processOrigin;
	}

	public ProcessesType getProcessesType() {
		return this.processesType;
	}

	public void setProcessesType(ProcessesType processesType) {
		this.processesType = processesType;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isActive() {
		return this.active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Set<ProcessProperties> getProcessPropertieses() {
		return this.processPropertieses;
	}

	public void setProcessPropertieses(Set<ProcessProperties> processPropertieses) {
		this.processPropertieses = processPropertieses;
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
