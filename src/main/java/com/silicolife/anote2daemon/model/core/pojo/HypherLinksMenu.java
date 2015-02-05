package com.silicolife.anote2daemon.model.core.pojo;

// Generated 5/Fev/2015 14:40:20 by Hibernate Tools 4.0.0

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * HypherLinksMenu generated by hbm2java
 */
@Entity
@Table(name = "hypher_links_menu")
public class HypherLinksMenu implements java.io.Serializable {

	private long id;
	private String menuName;
	private String hypherLinkMenu;
	private byte[] icon;
	private String menuLevel;
	private Set<HypherLinkMenuSourceAssociation> hypherLinkMenuSourceAssociations = new HashSet<HypherLinkMenuSourceAssociation>(0);
	private Set<HypherLinksSubmenus> hypherLinksSubmenusesForHypherLinksMenuId = new HashSet<HypherLinksSubmenus>(0);
	private Set<HypherLinksSubmenus> hypherLinksSubmenusesForHypherLinksSubmenuId = new HashSet<HypherLinksSubmenus>(0);

	public HypherLinksMenu() {
	}

	public HypherLinksMenu(long id, String menuName, String hypherLinkMenu, String menuLevel) {
		this.id = id;
		this.menuName = menuName;
		this.hypherLinkMenu = hypherLinkMenu;
		this.menuLevel = menuLevel;
	}

	public HypherLinksMenu(long id, String menuName, String hypherLinkMenu, byte[] icon, String menuLevel, Set<HypherLinkMenuSourceAssociation> hypherLinkMenuSourceAssociations, Set<HypherLinksSubmenus> hypherLinksSubmenusesForHypherLinksMenuId,
			Set<HypherLinksSubmenus> hypherLinksSubmenusesForHypherLinksSubmenuId) {
		this.id = id;
		this.menuName = menuName;
		this.hypherLinkMenu = hypherLinkMenu;
		this.icon = icon;
		this.menuLevel = menuLevel;
		this.hypherLinkMenuSourceAssociations = hypherLinkMenuSourceAssociations;
		this.hypherLinksSubmenusesForHypherLinksMenuId = hypherLinksSubmenusesForHypherLinksMenuId;
		this.hypherLinksSubmenusesForHypherLinksSubmenuId = hypherLinksSubmenusesForHypherLinksSubmenuId;
	}

	@Id
	@Column(name = "id", unique = true, nullable = false)
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(name = "menu_name", nullable = false, length = 45)
	public String getMenuName() {
		return this.menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	@Column(name = "hypher_link_menu", nullable = false, length = 250)
	public String getHypherLinkMenu() {
		return this.hypherLinkMenu;
	}

	public void setHypherLinkMenu(String hypherLinkMenu) {
		this.hypherLinkMenu = hypherLinkMenu;
	}

	@Column(name = "icon")
	public byte[] getIcon() {
		return this.icon;
	}

	public void setIcon(byte[] icon) {
		this.icon = icon;
	}

	@Column(name = "menu_level", nullable = false, length = 2)
	public String getMenuLevel() {
		return this.menuLevel;
	}

	public void setMenuLevel(String menuLevel) {
		this.menuLevel = menuLevel;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "hypherLinksMenu")
	public Set<HypherLinkMenuSourceAssociation> getHypherLinkMenuSourceAssociations() {
		return this.hypherLinkMenuSourceAssociations;
	}

	public void setHypherLinkMenuSourceAssociations(Set<HypherLinkMenuSourceAssociation> hypherLinkMenuSourceAssociations) {
		this.hypherLinkMenuSourceAssociations = hypherLinkMenuSourceAssociations;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "hypherLinksMenuByHypherLinksMenuId")
	public Set<HypherLinksSubmenus> getHypherLinksSubmenusesForHypherLinksMenuId() {
		return this.hypherLinksSubmenusesForHypherLinksMenuId;
	}

	public void setHypherLinksSubmenusesForHypherLinksMenuId(Set<HypherLinksSubmenus> hypherLinksSubmenusesForHypherLinksMenuId) {
		this.hypherLinksSubmenusesForHypherLinksMenuId = hypherLinksSubmenusesForHypherLinksMenuId;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "hypherLinksMenuByHypherLinksSubmenuId")
	public Set<HypherLinksSubmenus> getHypherLinksSubmenusesForHypherLinksSubmenuId() {
		return this.hypherLinksSubmenusesForHypherLinksSubmenuId;
	}

	public void setHypherLinksSubmenusesForHypherLinksSubmenuId(Set<HypherLinksSubmenus> hypherLinksSubmenusesForHypherLinksSubmenuId) {
		this.hypherLinksSubmenusesForHypherLinksSubmenuId = hypherLinksSubmenusesForHypherLinksSubmenuId;
	}

}
