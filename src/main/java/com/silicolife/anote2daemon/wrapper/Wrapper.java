package com.silicolife.anote2daemon.wrapper;

/**
 * Interface to handler with the conversion of objects
 * 
 * @author Joel Azevedo Costa
 * @year 2015
 *
 * @param <D,A> <Daemon, Anote2>
 */
public interface Wrapper<D, A> {

	/**
	 * public Publications convertToDaemonStructure()
	 * 
	 * @param parameter
	 * @return
	 */
	public A convertToAnoteStructure(D parameter);

	/**
	 * Convert from anote2 structure to daemon structure
	 * 
	 * @param parameter
	 * @return
	 */
	public D convertToDaemonStructure(A parameter);
}
