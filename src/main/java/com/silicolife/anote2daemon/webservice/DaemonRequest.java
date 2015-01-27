package com.silicolife.anote2daemon.webservice;

import com.silicolife.anote2daemon.model.pojo.DaemonUsers;

public class DaemonRequest<T> {
	private DaemonUsers user;
	private T content;

	public DaemonRequest(DaemonUsers user, T content) {
		this.user = user;
		this.content = content;
	}

	public DaemonRequest(DaemonUsers user) {
		this.user = user;
	}

	public DaemonRequest() {
	}

	public T getContent() {
		return content;
	}

	public void setContent(T content) {
		this.content = content;
	}

	public DaemonUsers getUser() {
		return user;
	}

	public void setUser(DaemonUsers user) {
		this.user = user;
	}
}
