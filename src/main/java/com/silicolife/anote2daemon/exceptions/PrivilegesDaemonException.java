package com.silicolife.anote2daemon.exceptions;

public class PrivilegesDaemonException extends Anote2DaemonException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String code;

	public PrivilegesDaemonException(Exception e) {
		super(e);
	}

	public PrivilegesDaemonException(String code, String errorMessage, Exception e) {
		super(errorMessage, e);
		this.code = code;
	}

	public PrivilegesDaemonException(String code, String errorMessage) {
		super(errorMessage);
		this.code = code;
	}

	public String getCode() {
		return code;
	}
}
