package com.silicolife.anote2daemon.model.core.dao.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.silicolife.anote2daemon.model.core.dao.GenericDao;
import com.silicolife.anote2daemon.model.core.entities.Processes;
import com.silicolife.anote2daemon.model.core.entities.ProcessesOrigin;
import com.silicolife.anote2daemon.model.core.entities.ProcessesProperties;
import com.silicolife.anote2daemon.model.core.entities.ProcessesType;

@Repository
public class ProcessesManagerDao {

	private GenericDao<Processes> processesDao;
	private GenericDao<ProcessesType> processesTypeDao;
	private GenericDao<ProcessesOrigin> processesOriginDao;
	private GenericDao<ProcessesProperties> processesPropertiesDao;

	@Autowired
	public ProcessesManagerDao(GenericDao<Processes> processesDao, GenericDao<ProcessesType> processesTypeDao, GenericDao<ProcessesOrigin> processesOriginDao,
			GenericDao<ProcessesProperties> processesPropertiesDao) {
		super();
		this.processesDao = processesDao;
		this.processesTypeDao = processesTypeDao;
		this.processesOriginDao = processesOriginDao;
		this.processesPropertiesDao = processesPropertiesDao;
	}

	public GenericDao<Processes> getProcessesDao() {
		return processesDao;
	}

	public GenericDao<ProcessesType> getProcessesTypeDao() {
		return processesTypeDao;
	}

	public GenericDao<ProcessesOrigin> getProcessesOriginDao() {
		return processesOriginDao;
	}

	public GenericDao<ProcessesProperties> getProcessesPropertiesDao() {
		return processesPropertiesDao;
	}
}
