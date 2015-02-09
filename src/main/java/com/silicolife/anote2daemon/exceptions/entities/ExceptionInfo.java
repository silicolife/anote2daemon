package com.silicolife.anote2daemon.exceptions.entities;

import java.io.Serializable;

/**
 * Class to represent the exception. It is sent in JSON message
 * 
 * @author Joel Azevedo Costa
 * @year 2015
 *
 */
public class ExceptionInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String code;
	private String message;
	private String rootCause;
	private String otherInfo;

	public ExceptionInfo(String code, String message, String rootCause) {
		this.code = code;
		this.message = message;
		this.rootCause = rootCause;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getRootCause() {
		return rootCause;
	}

	public void setRootCause(String rootCause) {
		this.rootCause = rootCause;
	}

	public String getOtherInfo() {
		return otherInfo;
	}

	public void setOtherInfo(String otherInfo) {
		this.otherInfo = otherInfo;
	}
}
