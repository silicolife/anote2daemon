package com.silicolife.anote2daemon.security;


public enum RestPermissionsEvaluatorEnum {

	default_("default_"), resourceByResourceElement("resourceByResourceElement");
	
	private final String name;

	private RestPermissionsEvaluatorEnum(String s) {
		name = s;
	}

	public boolean equalsName(String otherName) {
		return (otherName == null) ? false : name.equals(otherName);
	}

	public String getName() {
		return name;
	}

	public String toString() {
		return name;
	}
}
