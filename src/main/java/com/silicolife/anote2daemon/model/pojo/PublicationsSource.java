package com.silicolife.anote2daemon.model.pojo;

// Generated 3/Fev/2015 12:37:09 by Hibernate Tools 4.0.0

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * PublicationsSource generated by hbm2java
 */
@Entity
@Table(name = "publications_source", uniqueConstraints = @UniqueConstraint(columnNames = "description"))
public class PublicationsSource implements java.io.Serializable {

	private long id;
	private String description;
	private Set<PublicationsHasPublicationsSource> publicationsHasPublicationsSources = new HashSet<PublicationsHasPublicationsSource>(0);

	public PublicationsSource() {
	}

	public PublicationsSource(long id, String description) {
		this.id = id;
		this.description = description;
	}

	public PublicationsSource(long id, String description, Set<PublicationsHasPublicationsSource> publicationsHasPublicationsSources) {
		this.id = id;
		this.description = description;
		this.publicationsHasPublicationsSources = publicationsHasPublicationsSources;
	}

	@Id
	@Column(name = "id", unique = true, nullable = false)
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(name = "description", unique = true, nullable = false)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "publicationsSource")
	public Set<PublicationsHasPublicationsSource> getPublicationsHasPublicationsSources() {
		return this.publicationsHasPublicationsSources;
	}

	public void setPublicationsHasPublicationsSources(Set<PublicationsHasPublicationsSource> publicationsHasPublicationsSources) {
		this.publicationsHasPublicationsSources = publicationsHasPublicationsSources;
	}

}
