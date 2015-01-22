package com.silicolife.anote2daemon.model.pojo;

// Generated 21/Jan/2015 14:28:04 by Hibernate Tools 4.0.0

import java.util.HashSet;
import java.util.Set;

/**
 * HypherLinksMenu generated by hbm2java
 */
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

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMenuName() {
		return this.menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getHypherLinkMenu() {
		return this.hypherLinkMenu;
	}

	public void setHypherLinkMenu(String hypherLinkMenu) {
		this.hypherLinkMenu = hypherLinkMenu;
	}

	public byte[] getIcon() {
		return this.icon;
	}

	public void setIcon(byte[] icon) {
		this.icon = icon;
	}

	public String getMenuLevel() {
		return this.menuLevel;
	}

	public void setMenuLevel(String menuLevel) {
		this.menuLevel = menuLevel;
	}

	public Set<HypherLinkMenuSourceAssociation> getHypherLinkMenuSourceAssociations() {
		return this.hypherLinkMenuSourceAssociations;
	}

	public void setHypherLinkMenuSourceAssociations(Set<HypherLinkMenuSourceAssociation> hypherLinkMenuSourceAssociations) {
		this.hypherLinkMenuSourceAssociations = hypherLinkMenuSourceAssociations;
	}

	public Set<HypherLinksSubmenus> getHypherLinksSubmenusesForHypherLinksMenuId() {
		return this.hypherLinksSubmenusesForHypherLinksMenuId;
	}

	public void setHypherLinksSubmenusesForHypherLinksMenuId(Set<HypherLinksSubmenus> hypherLinksSubmenusesForHypherLinksMenuId) {
		this.hypherLinksSubmenusesForHypherLinksMenuId = hypherLinksSubmenusesForHypherLinksMenuId;
	}

	public Set<HypherLinksSubmenus> getHypherLinksSubmenusesForHypherLinksSubmenuId() {
		return this.hypherLinksSubmenusesForHypherLinksSubmenuId;
	}

	public void setHypherLinksSubmenusesForHypherLinksSubmenuId(Set<HypherLinksSubmenus> hypherLinksSubmenusesForHypherLinksSubmenuId) {
		this.hypherLinksSubmenusesForHypherLinksSubmenuId = hypherLinksSubmenusesForHypherLinksSubmenuId;
	}

}
