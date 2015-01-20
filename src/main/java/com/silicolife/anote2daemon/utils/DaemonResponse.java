package com.silicolife.anote2daemon.utils;

import java.util.Map;

public class DaemonResponse {

	private Object content;
	private Map<Integer, String> errors;

	public DaemonResponse(Object content, Map<Integer, String> errors) {
		this.content = content;
		this.errors = errors;
	}

	public DaemonResponse() {
	}

	public Object getContent() {
		return content;
	}

	public Map<Integer, String> getErrors() {
		return errors;
	}

	public void setContent(Object content) {
		this.content = content;
	}

	public void setErrors(Map<Integer, String> errors) {
		this.errors = errors;
	}
}
