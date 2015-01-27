package com.silicolife.anote2daemon.model.pojo;

// Generated 27/Jan/2015 13:51:08 by Hibernate Tools 4.0.0

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * PublicationsHasPublicationsSource generated by hbm2java
 */
@Entity
@Table(name = "publications_has_publications_source")
public class PublicationsHasPublicationsSource implements java.io.Serializable {

	private PublicationsHasPublicationsSourceId id;
	private PublicationsSource publicationsSource;
	private Publications publications;

	public PublicationsHasPublicationsSource() {
	}

	public PublicationsHasPublicationsSource(PublicationsHasPublicationsSourceId id, PublicationsSource publicationsSource, Publications publications) {
		this.id = id;
		this.publicationsSource = publicationsSource;
		this.publications = publications;
	}

	@EmbeddedId
	@AttributeOverrides({ @AttributeOverride(name = "publicationsId", column = @Column(name = "publications_id", nullable = false)), @AttributeOverride(name = "publicationsSourceId", column = @Column(name = "publications_source_id", nullable = false)),
			@AttributeOverride(name = "publicationsSourceInternalId", column = @Column(name = "publications_source_internal_id", nullable = false, length = 250)) })
	public PublicationsHasPublicationsSourceId getId() {
		return this.id;
	}

	public void setId(PublicationsHasPublicationsSourceId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "publications_source_id", nullable = false, insertable = false, updatable = false)
	public PublicationsSource getPublicationsSource() {
		return this.publicationsSource;
	}

	public void setPublicationsSource(PublicationsSource publicationsSource) {
		this.publicationsSource = publicationsSource;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "publications_id", nullable = false, insertable = false, updatable = false)
	public Publications getPublications() {
		return this.publications;
	}

	public void setPublications(Publications publications) {
		this.publications = publications;
	}

}
