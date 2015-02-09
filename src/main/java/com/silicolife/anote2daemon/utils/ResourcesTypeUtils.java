package com.silicolife.anote2daemon.utils;

public enum ResourcesTypeUtils {

	queries("queries"),corpus("corpus");

	private final String name;

	private ResourcesTypeUtils(String s) {
		name = s;
	}

	public boolean equalsName(String otherName) {
		return (otherName == null) ? false : name.equals(otherName);
	}

	public String toString() {
		return name;
	}

}
