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
 * HypherLinkMenuSourceAssociation generated by hbm2java
 */
@Entity
@Table(name = "hypher_link_menu_source_association")
public class HypherLinkMenuSourceAssociation implements java.io.Serializable {

	private HypherLinkMenuSourceAssociationId id;
	private HypherLinksMenu hypherLinksMenu;
	private Sources sources;

	public HypherLinkMenuSourceAssociation() {
	}

	public HypherLinkMenuSourceAssociation(HypherLinkMenuSourceAssociationId id, HypherLinksMenu hypherLinksMenu, Sources sources) {
		this.id = id;
		this.hypherLinksMenu = hypherLinksMenu;
		this.sources = sources;
	}

	@EmbeddedId
	@AttributeOverrides({ @AttributeOverride(name = "hypherLinksMenuId", column = @Column(name = "hypher_links_menu_id", nullable = false)), @AttributeOverride(name = "sourcesId", column = @Column(name = "sources_id", nullable = false)) })
	public HypherLinkMenuSourceAssociationId getId() {
		return this.id;
	}

	public void setId(HypherLinkMenuSourceAssociationId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "hypher_links_menu_id", nullable = false, insertable = false, updatable = false)
	public HypherLinksMenu getHypherLinksMenu() {
		return this.hypherLinksMenu;
	}

	public void setHypherLinksMenu(HypherLinksMenu hypherLinksMenu) {
		this.hypherLinksMenu = hypherLinksMenu;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sources_id", nullable = false, insertable = false, updatable = false)
	public Sources getSources() {
		return this.sources;
	}

	public void setSources(Sources sources) {
		this.sources = sources;
	}

}
