package com.silicolife.anote2daemon.model.core.pojo;

// Generated 5/Fev/2015 14:40:20 by Hibernate Tools 4.0.0

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * HypherLinksSubmenusId generated by hbm2java
 */
@Embeddable
public class HypherLinksSubmenusId implements java.io.Serializable {

	private long hypherLinksMenuId;
	private long hypherLinksSubmenuId;

	public HypherLinksSubmenusId() {
	}

	public HypherLinksSubmenusId(long hypherLinksMenuId, long hypherLinksSubmenuId) {
		this.hypherLinksMenuId = hypherLinksMenuId;
		this.hypherLinksSubmenuId = hypherLinksSubmenuId;
	}

	@Column(name = "hypher_links_menu_id", nullable = false)
	public long getHypherLinksMenuId() {
		return this.hypherLinksMenuId;
	}

	public void setHypherLinksMenuId(long hypherLinksMenuId) {
		this.hypherLinksMenuId = hypherLinksMenuId;
	}

	@Column(name = "hypher_links_submenu_id", nullable = false)
	public long getHypherLinksSubmenuId() {
		return this.hypherLinksSubmenuId;
	}

	public void setHypherLinksSubmenuId(long hypherLinksSubmenuId) {
		this.hypherLinksSubmenuId = hypherLinksSubmenuId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof HypherLinksSubmenusId))
			return false;
		HypherLinksSubmenusId castOther = (HypherLinksSubmenusId) other;

		return (this.getHypherLinksMenuId() == castOther.getHypherLinksMenuId()) && (this.getHypherLinksSubmenuId() == castOther.getHypherLinksSubmenuId());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (int) this.getHypherLinksMenuId();
		result = 37 * result + (int) this.getHypherLinksSubmenuId();
		return result;
	}

}
