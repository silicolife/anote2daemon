package com.silicolife.anote2daemon.model.pojo;

// Generated 19/Jan/2015 15:19:18 by Hibernate Tools 4.0.0

/**
 * PublicationsHasPublicationLabels generated by hbm2java
 */
public class PublicationsHasPublicationLabels implements java.io.Serializable {

	private PublicationsHasPublicationLabelsId id;
	private PublicationLabels publicationLabels;
	private Publications publications;

	public PublicationsHasPublicationLabels() {
	}

	public PublicationsHasPublicationLabels(PublicationsHasPublicationLabelsId id, PublicationLabels publicationLabels, Publications publications) {
		this.id = id;
		this.publicationLabels = publicationLabels;
		this.publications = publications;
	}

	public PublicationsHasPublicationLabelsId getId() {
		return this.id;
	}

	public void setId(PublicationsHasPublicationLabelsId id) {
		this.id = id;
	}

	public PublicationLabels getPublicationLabels() {
		return this.publicationLabels;
	}

	public void setPublicationLabels(PublicationLabels publicationLabels) {
		this.publicationLabels = publicationLabels;
	}

	public Publications getPublications() {
		return this.publications;
	}

	public void setPublications(Publications publications) {
		this.publications = publications;
	}

}