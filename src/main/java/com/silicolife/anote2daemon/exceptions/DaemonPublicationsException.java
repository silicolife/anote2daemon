package com.silicolife.anote2daemon.exceptions;

public class DaemonPublicationsException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String code;

	public DaemonPublicationsException(String code, String message) {
		super(message);
		this.code = code;
	}

	public String getCode() {
		return code;
	}
}
