package com.silicolife.anote2daemon.exceptions;

public class DaemonQueriesException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String code;

	public DaemonQueriesException(String code, String message) {
		super(message);
		this.code = code;
	}

	public String getCode() {
		return code;
	}
}
