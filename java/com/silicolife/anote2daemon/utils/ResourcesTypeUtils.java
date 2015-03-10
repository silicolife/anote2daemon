package com.silicolife.anote2daemon.utils;

/**
 * Enum which save the resource type of anote2daemon
 * 
 * @author Joel Azevedo costa
 * @year 2015
 *
 */
public enum ResourcesTypeUtils {

	queries("queries"), corpus("corpus"), processes("processes");

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
