package com.silicolife.anote2daemon.exceptions;

public class ExceptionsCodes {
	public static final String keyConstraintCode = "keyDataBase";
	public static final String generalDbCode = "generalDataBase";
	public static final String generalCode = "general";
	public static final String parseJsonCode = "parseJson";
	public static final String nullPointerCode = "nullPointer";
	public static final String hibernateCode = "hibernate";
	public static final String badCredentialsCode = "badCredentials";
	public static final String accessDeniedCode = "accessDenied";

	/**
	 * Error codes
	 */

	public static final String codeNoQuery = "noQuery";
	public static final String codeNoResourceType = "noResourceType";
	public static final String codeQueryAccessDenied = "queryDenied";

	/**
	 * Error Messages
	 */

	public static final String msgNoQuery = "Query does not exist in daemon";

	public static final String msgNoResourceType = "Daemon resource type Queries does not exist";

	public static final String msgQueryAccessDenied = "Query Access denied";

}
