package com.silicolife.anote2daemon.model.pojo;

// Generated 19/Jan/2015 15:19:18 by Hibernate Tools 4.0.0

import java.util.HashSet;
import java.util.Set;

/**
 * PublicationsSource generated by hbm2java
 */
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

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<PublicationsHasPublicationsSource> getPublicationsHasPublicationsSources() {
		return this.publicationsHasPublicationsSources;
	}

	public void setPublicationsHasPublicationsSources(Set<PublicationsHasPublicationsSource> publicationsHasPublicationsSources) {
		this.publicationsHasPublicationsSources = publicationsHasPublicationsSources;
	}

}