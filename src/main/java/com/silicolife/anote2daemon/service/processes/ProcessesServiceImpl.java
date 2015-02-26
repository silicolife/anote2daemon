package com.silicolife.anote2daemon.service.processes;

import java.util.Date;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pt.uminho.anote2.process.IE.IIEProcess;

import com.silicolife.anote2daemon.model.core.dao.UsersLogged;
import com.silicolife.anote2daemon.model.core.dao.manager.ProcessesManagerDao;
import com.silicolife.anote2daemon.model.core.dao.manager.UsersManagerDao;
import com.silicolife.anote2daemon.model.core.entities.Processes;
import com.silicolife.anote2daemon.model.core.entities.ProcessesOrigin;
import com.silicolife.anote2daemon.model.core.entities.ProcessesProperties;
import com.silicolife.anote2daemon.model.core.entities.ProcessesType;
import com.silicolife.anote2daemon.model.core.entities.Users;
import com.silicolife.anote2daemon.model.core.entities.UsersLog;
import com.silicolife.anote2daemon.wrapper.process.ProcessWrapper;

@Service
@Transactional(readOnly = true)
public class ProcessesServiceImpl implements ProcessesService {

	private ProcessesManagerDao processesManagerDao;
	private UsersManagerDao usersManagerDao;
	@Autowired
	private UsersLogged userLogged;
	
	@Autowired
	public ProcessesServiceImpl(ProcessesManagerDao processesManagerDao, UsersManagerDao usersManagerDao) {
		this.processesManagerDao = processesManagerDao;
		this.usersManagerDao = usersManagerDao;
	}
	
	@Transactional(readOnly = false)
	@Override
	public Boolean createIEProcess(IIEProcess processes_) {
		Processes processes  = ProcessWrapper.convertToDaemonStructure(processes_);
		/*
		 * create processes dependencies
		 */
		ProcessesType processesType = processes.getProcessesType();
		processesManagerDao.getProcessesTypeDao().save(processesType);
		ProcessesOrigin processesOrigin = processes.getProcessesOrigin();
		processesManagerDao.getProcessesOriginDao().save(processesOrigin);
		Set<ProcessesProperties> processesPropertiess = processes.getProcessesPropertieses();
		for(ProcessesProperties processesProperty : processesPropertiess){
			processesManagerDao.getProcessesPropertiesDao().save(processesProperty);
		}
		
		processesManagerDao.getProcessesDao().save(processes);
		/*
		 * log
		 */
		Users user = userLogged.getCurrentUserLogged();
		UsersLog log = new UsersLog(user, new Date(), "create", "processes/processes_origin/processes_type/processes_properties", null, "create processes");
		usersManagerDao.getUsersLog().save(log);

		return true;

	}

	@Transactional(readOnly = false)
	@Override
	public Boolean updateIEProcess(IIEProcess processes_) {
		Processes processes  = ProcessWrapper.convertToDaemonStructure(processes_);
		processesManagerDao.getProcessesDao().update(processes);
		/*
		 * log
		 */
		Users user = userLogged.getCurrentUserLogged();
		UsersLog log = new UsersLog(user, new Date(), "update", "processes", null, "update processes");
		usersManagerDao.getUsersLog().save(log);

		return true;
	}

	@Override
	public IIEProcess getProcessByID(Long id) {
		Processes processes = processesManagerDao.getProcessesDao().findById(id);
		IIEProcess processes_ = ProcessWrapper.convertToAnoteStructure(processes);
		return processes_;
	}

}
