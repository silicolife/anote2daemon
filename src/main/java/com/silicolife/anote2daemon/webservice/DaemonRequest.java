package com.silicolife.anote2daemon.webservice;

public class DaemonRequest<T> {
	private T content;

	public DaemonRequest(T content) {
		this.content = content;
	}

	public DaemonRequest() {
	}

	public T getContent() {
		return content;
	}

	public void setContent(T content) {
		this.content = content;
	}
}
