package com.silicolife.anote2daemon.service.processes;

import pt.uminho.anote2.process.IE.IIEProcess;

/**
 * Interface to define all methods of Service layer about processes
 * 
 * @author Joel Azevedo Costa
 * @year 2015
 *
 */
public interface ProcessesService {
	/**
	 * Create a new process
	 * 
	 * @param processes
	 * @return
	 */
	public Boolean createIEProcess(IIEProcess processes_);

	/**
	 * Update process
	 * 
	 * @param process
	 * @return
	 */
	public Boolean updateIEProcess(IIEProcess processes_);

	/**
	 * Get process by id
	 * 
	 * @param id
	 * @return
	 */
	public IIEProcess getProcessByID(Long id);
}
