package com.silicolife.anote2daemon.exceptions;

/**
 * Exceptions codes and messages used in anote2dameon exceptions.
 * 
 * 
 * @author Joel Azevedo Costa
 * @year 2015
 *
 */
public class ExceptionsCodes {
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

	public static final String codeConstraint = "keyDataBase";
	public static final String codeWrongValue = "wrongTypeValue";
	public static final String codeNoQuery = "noQuery";
	public static final String codeNoPublication = "noPublication";
	public static final String codeNoResourceType = "noResourceType";
	public static final String codeQueryAccessDenied = "queryDenied";
	public static final String codeQueryPublication = "noAssociationQueryPub";
	public static final String codePublicationSource = "noPublicationSource";
	public static final String codeWrongCredentials = "wrongCredentials";
	public static final String codeResourceAccessDenied = "resourceDenied";
	public static final String codeNoCluster = "noCluster";
	public static final String codeNoCorpus = "noCorpus";
	
	

	/**
	 * Error Messages
	 */

	public static final String msgNoQuery = "Query does not exist in daemon";
	public static final String msgNoPublication = "Publication does not exist in daemon";
	public static final String msgNoResourceType = "Daemon resource type Queries does not exist";
	public static final String msgQueryAccessDenied = "Query Access denied";
	public static final String msgQueryPublication = "Association query publication does not exist";
	public static final String msgPublicationSource = "Publication source does not exist";
	public static final String msgWrongCredentials = "The username or password are wrong. :D";
	public static final String msgResourceAccessDenied = "Puff... you do not access to that anote2daemon resource. :d";
	public static final String msgNoCluster = "Cluster does not exist";
	public static final String msgNoCorpus = "Corpus does not exit";

}
