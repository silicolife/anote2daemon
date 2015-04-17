package com.silicolife.anote2daemon.webservice;

import pt.uminho.anote2.datastructures.dataaccess.daemon.webserviceclient.ExceptionInfo;

public class DaemonResponse<T> {

	private T content;
	private ExceptionInfo exception;

	public DaemonResponse(T content, ExceptionInfo exception) {
		this.content = content;
		this.exception = exception;
	}

	public DaemonResponse(T content) {
		this.content = content;
	}

	public DaemonResponse() {
	}

	public T getContent() {
		return content;
	}

	public ExceptionInfo getException() {
		return exception;
	}

	public void setContent(T content) {
		this.content = content;
	}

	public void setException(ExceptionInfo exception) {
		this.exception = exception;
	}
}
