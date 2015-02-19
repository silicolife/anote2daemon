package com.silicolife.anote2daemon.wrapper.process;

import pt.uminho.anote2.datastructures.process.ProcessOrigin;
import pt.uminho.anote2.process.IProcessOrigin;

import com.silicolife.anote2daemon.model.core.entities.ProcessesOrigin;

/**
 * Class to transform anote2 process origin structures to daemon
 * process origin structures and vice-verse
 * 
 * @author Joel Azevedo Costa
 * @year 2015
 *
 */
public class ProcessOriginWrapper {
	
	public static IProcessOrigin convertToAnoteStructure(ProcessesOrigin processesOrigin) {
		Long id = processesOrigin.getId();
		String name = processesOrigin.getDescription();
		IProcessOrigin processOrigin_ = new ProcessOrigin(id, name);
		return processOrigin_;
	}

	public static ProcessesOrigin convertToDaemonStructure(IProcessOrigin processOrigin_) {
		Long id = processOrigin_.getId();
		String name = processOrigin_.getOrigin();
		ProcessesOrigin processesOrigin = new ProcessesOrigin(id, name);
		return processesOrigin;
	}
}
