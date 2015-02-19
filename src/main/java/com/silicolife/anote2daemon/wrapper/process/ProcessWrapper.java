package com.silicolife.anote2daemon.wrapper.process;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import pt.uminho.anote2.core.document.corpus.ICorpus;
import pt.uminho.anote2.datastructures.process.IEProcess;
import pt.uminho.anote2.process.IProcessOrigin;
import pt.uminho.anote2.process.IProcessType;
import pt.uminho.anote2.process.IE.IIEProcess;

import com.silicolife.anote2daemon.model.core.entities.CorpusHasProcesses;
import com.silicolife.anote2daemon.model.core.entities.Processes;
import com.silicolife.anote2daemon.model.core.entities.ProcessesOrigin;
import com.silicolife.anote2daemon.model.core.entities.ProcessesProperties;
import com.silicolife.anote2daemon.model.core.entities.ProcessesType;
import com.silicolife.anote2daemon.wrapper.corpora.CorpusWrapper;

/**
 * Class to transform anote2 Processes structures to daemon Processes structures
 * and vice-verse
 * 
 * @author Joel Azevedo Costa
 * @year 2015
 *
 */
public class ProcessWrapper {

	public static IIEProcess convertToAnoteStructure(Processes processes) {
		Long id = processes.getId();
		String description = processes.getProcessName();
		String notes = processes.getNotes();
		ProcessesOrigin processOrigin = processes.getProcessesOrigin();
		ProcessesType processType = processes.getProcessesType();
		Set<ProcessesProperties> processProperties = processes.getProcessesPropertieses();
		Set<CorpusHasProcesses> corpusHasProcesses = processes.getCorpusHasProcesseses();
		/*
		 * anote structures
		 */
		IProcessOrigin processOrigin_ = ProcessOriginWrapper.convertToAnoteStructure(processOrigin);
		IProcessType processType_ = ProcessTypeWrapper.convertToAnoteStructure(processType);
		Properties properties = ProcessPropertiesWrapper.convertToAnoteStructure(processProperties);
		List<ICorpus> corpusList_ = new ArrayList<ICorpus>();
		for (CorpusHasProcesses corpusRecord : corpusHasProcesses) {
			ICorpus corpus_ = CorpusWrapper.convertToAnoteStructure(corpusRecord.getCorpus());
			corpusList_.add(corpus_);
		}

		if (corpusList_.size() == 0)
			corpusList_ = null;

		IIEProcess process_ = new IEProcess(id, corpusList_, description, notes, processType_, processOrigin_, properties);

		return process_;
	}

	public static Processes convertToDaemonStructure(IIEProcess processes_) {
		Long id = processes_.getID();
		String name = processes_.getName();
		String notes = processes_.getNotes();
		IProcessOrigin processOrigin_ = processes_.getProcessOrigin();
		IProcessType processType_ = processes_.getType();
		Properties properties = processes_.getProperties();
		/*
		 * daemon structures
		 */
		Processes processes = new Processes();
		processes.setId(id);
		processes.setProcessName(name);
		processes.setNotes(notes);
		ProcessesType processType = ProcessTypeWrapper.convertToDaemonStructure(processType_);
		ProcessesOrigin processOrigin = ProcessOriginWrapper.convertToDaemonStructure(processOrigin_);
		Set<ProcessesProperties> processesProperties = ProcessPropertiesWrapper.convertToDaemonStructure(properties, processes);
		processes.setProcessesType(processType);
		processes.setProcessesOrigin(processOrigin);
		processes.setProcessesPropertieses(processesProperties);
		
		return processes;
	}
}
