package com.silicolife.anote2daemon.model.pojo;

// Generated 3/Fev/2015 12:37:09 by Hibernate Tools 4.0.0

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * ClassesColors generated by hbm2java
 */
@Entity
@Table(name = "classes_colors")
public class ClassesColors implements java.io.Serializable {

	private long classesId;
	private Classes classes;
	private String color;

	public ClassesColors() {
	}

	public ClassesColors(Classes classes) {
		this.classes = classes;
	}

	public ClassesColors(Classes classes, String color) {
		this.classes = classes;
		this.color = color;
	}

	@GenericGenerator(name = "generator", strategy = "foreign", parameters = @Parameter(name = "property", value = "classes"))
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "classes_id", unique = true, nullable = false)
	public long getClassesId() {
		return this.classesId;
	}

	public void setClassesId(long classesId) {
		this.classesId = classesId;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	public Classes getClasses() {
		return this.classes;
	}

	public void setClasses(Classes classes) {
		this.classes = classes;
	}

	@Column(name = "color", length = 10)
	public String getColor() {
		return this.color;
	}

	public void setColor(String color) {
		this.color = color;
	}

}
