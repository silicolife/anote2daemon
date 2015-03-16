package com.silicolife.anote2daemon.wrapper.process;

import pt.uminho.anote2.datastructures.process.ProcessType;
import pt.uminho.anote2.process.IProcessType;

import com.silicolife.anote2daemon.model.core.entities.ProcessesType;

/**
 * Class to transform anote2 Process type structures to daemon
 * Process Type structures and vice-verse
 * 
 * @author Joel Azevedo Costa
 * @year 2015
 *
 */
public class ProcessTypeWrapper {

	public static IProcessType convertToAnoteStructure(ProcessesType processesType) {
		Long id = processesType.getId();
		String name = processesType.getProcessType();
		IProcessType processType_ = new ProcessType(id, name);
		return processType_;
	}

	public static ProcessesType convertToDaemonStructure(IProcessType processType_) {
		Long id = processType_.getId();
		String name = processType_.getType();
		ProcessesType processesType = new ProcessesType(id, name);
		return processesType;
	}
}
