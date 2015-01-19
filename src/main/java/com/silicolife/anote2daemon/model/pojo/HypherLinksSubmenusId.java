package com.silicolife.anote2daemon.model.pojo;

// Generated 19/Jan/2015 15:19:18 by Hibernate Tools 4.0.0

/**
 * HypherLinksSubmenusId generated by hbm2java
 */
public class HypherLinksSubmenusId implements java.io.Serializable {

	private long hypherLinksMenuId;
	private long hypherLinksSubmenuId;

	public HypherLinksSubmenusId() {
	}

	public HypherLinksSubmenusId(long hypherLinksMenuId, long hypherLinksSubmenuId) {
		this.hypherLinksMenuId = hypherLinksMenuId;
		this.hypherLinksSubmenuId = hypherLinksSubmenuId;
	}

	public long getHypherLinksMenuId() {
		return this.hypherLinksMenuId;
	}

	public void setHypherLinksMenuId(long hypherLinksMenuId) {
		this.hypherLinksMenuId = hypherLinksMenuId;
	}

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
