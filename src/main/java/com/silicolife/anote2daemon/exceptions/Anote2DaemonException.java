package com.silicolife.anote2daemon.exceptions;

public class Anote2DaemonException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Exception e;

	public Anote2DaemonException(Exception e) {
		super(e);
		this.e = e;
	}

	public Anote2DaemonException(String errorMessage, Exception e) {
		super(errorMessage, e);
		this.e = e;
	}

	public Anote2DaemonException(String errorMessage) {
		super(errorMessage);
	}

	public Exception getExeption() {
		return e;
	}
}
