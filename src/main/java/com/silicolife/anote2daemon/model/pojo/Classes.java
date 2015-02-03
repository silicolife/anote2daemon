package com.silicolife.anote2daemon.model.pojo;

// Generated 3/Fev/2015 12:37:09 by Hibernate Tools 4.0.0

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Classes generated by hbm2java
 */
@Entity
@Table(name = "classes", uniqueConstraints = @UniqueConstraint(columnNames = "class"))
public class Classes implements java.io.Serializable {

	private long id;
	private String class_;
	private Set<ResourcesContent> resourcesContents = new HashSet<ResourcesContent>(0);
	private ClassesColors classesColors;
	private Set<Annotations> annotationses = new HashSet<Annotations>(0);
	private Set<ClassesHierarchy> classesHierarchiesForClassesId = new HashSet<ClassesHierarchy>(0);
	private Set<ClassesHierarchy> classesHierarchiesForSuperClassId = new HashSet<ClassesHierarchy>(0);

	public Classes() {
	}

	public Classes(long id, String class_) {
		this.id = id;
		this.class_ = class_;
	}

	public Classes(long id, String class_, Set<ResourcesContent> resourcesContents, ClassesColors classesColors, Set<Annotations> annotationses, Set<ClassesHierarchy> classesHierarchiesForClassesId, Set<ClassesHierarchy> classesHierarchiesForSuperClassId) {
		this.id = id;
		this.class_ = class_;
		this.resourcesContents = resourcesContents;
		this.classesColors = classesColors;
		this.annotationses = annotationses;
		this.classesHierarchiesForClassesId = classesHierarchiesForClassesId;
		this.classesHierarchiesForSuperClassId = classesHierarchiesForSuperClassId;
	}

	@Id
	@Column(name = "id", unique = true, nullable = false)
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(name = "class", unique = true, nullable = false)
	public String getClass_() {
		return this.class_;
	}

	public void setClass_(String class_) {
		this.class_ = class_;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "classes")
	public Set<ResourcesContent> getResourcesContents() {
		return this.resourcesContents;
	}

	public void setResourcesContents(Set<ResourcesContent> resourcesContents) {
		this.resourcesContents = resourcesContents;
	}

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "classes")
	public ClassesColors getClassesColors() {
		return this.classesColors;
	}

	public void setClassesColors(ClassesColors classesColors) {
		this.classesColors = classesColors;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "classes")
	public Set<Annotations> getAnnotationses() {
		return this.annotationses;
	}

	public void setAnnotationses(Set<Annotations> annotationses) {
		this.annotationses = annotationses;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "classesByClassesId")
	public Set<ClassesHierarchy> getClassesHierarchiesForClassesId() {
		return this.classesHierarchiesForClassesId;
	}

	public void setClassesHierarchiesForClassesId(Set<ClassesHierarchy> classesHierarchiesForClassesId) {
		this.classesHierarchiesForClassesId = classesHierarchiesForClassesId;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "classesBySuperClassId")
	public Set<ClassesHierarchy> getClassesHierarchiesForSuperClassId() {
		return this.classesHierarchiesForSuperClassId;
	}

	public void setClassesHierarchiesForSuperClassId(Set<ClassesHierarchy> classesHierarchiesForSuperClassId) {
		this.classesHierarchiesForSuperClassId = classesHierarchiesForSuperClassId;
	}

}
