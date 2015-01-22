package com.silicolife.anote2daemon.model.pojo;

// Generated 21/Jan/2015 14:28:04 by Hibernate Tools 4.0.0

/**
 * HypherLinksSubmenus generated by hbm2java
 */
public class HypherLinksSubmenus implements java.io.Serializable {

	private HypherLinksSubmenusId id;
	private HypherLinksMenu hypherLinksMenuByHypherLinksMenuId;
	private HypherLinksMenu hypherLinksMenuByHypherLinksSubmenuId;

	public HypherLinksSubmenus() {
	}

	public HypherLinksSubmenus(HypherLinksSubmenusId id, HypherLinksMenu hypherLinksMenuByHypherLinksMenuId, HypherLinksMenu hypherLinksMenuByHypherLinksSubmenuId) {
		this.id = id;
		this.hypherLinksMenuByHypherLinksMenuId = hypherLinksMenuByHypherLinksMenuId;
		this.hypherLinksMenuByHypherLinksSubmenuId = hypherLinksMenuByHypherLinksSubmenuId;
	}

	public HypherLinksSubmenusId getId() {
		return this.id;
	}

	public void setId(HypherLinksSubmenusId id) {
		this.id = id;
	}

	public HypherLinksMenu getHypherLinksMenuByHypherLinksMenuId() {
		return this.hypherLinksMenuByHypherLinksMenuId;
	}

	public void setHypherLinksMenuByHypherLinksMenuId(HypherLinksMenu hypherLinksMenuByHypherLinksMenuId) {
		this.hypherLinksMenuByHypherLinksMenuId = hypherLinksMenuByHypherLinksMenuId;
	}

	public HypherLinksMenu getHypherLinksMenuByHypherLinksSubmenuId() {
		return this.hypherLinksMenuByHypherLinksSubmenuId;
	}

	public void setHypherLinksMenuByHypherLinksSubmenuId(HypherLinksMenu hypherLinksMenuByHypherLinksSubmenuId) {
		this.hypherLinksMenuByHypherLinksSubmenuId = hypherLinksMenuByHypherLinksSubmenuId;
	}

}
