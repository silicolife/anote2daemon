package com.silicolife.anote2daemon.webservice;

import java.util.Map;

public class DaemonResponse<T> {

	private T content;
	private Map<String, String> errors;

	public DaemonResponse(T content, Map<String, String> errors) {
		this.content = content;
		this.errors = errors;
	}

	public DaemonResponse(T content) {
		this.content = content;
	}

	public DaemonResponse() {
	}

	public T getContent() {
		return content;
	}

	public Map<String, String> getErrors() {
		return errors;
	}

	public void setContent(T content) {
		this.content = content;
	}

	public void setErrors(Map<String, String> errors) {
		this.errors = errors;
	}
}
