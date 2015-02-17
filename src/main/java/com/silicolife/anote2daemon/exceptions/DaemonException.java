package com.silicolife.anote2daemon.exceptions;

/**
 * Class to represent the anote2dameon exceptions
 * 
 * 
 * 
 * @author Joel Azevedo Costa
 * @year 2015
 */
public class DaemonException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String code;

	public DaemonException(String code, String message) {
		super(message);
		this.code = code;
	}

	public String getCode() {
		return code;
	}
}
