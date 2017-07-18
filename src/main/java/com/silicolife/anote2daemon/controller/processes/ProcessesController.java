package com.silicolife.anote2daemon.controller.processes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.silicolife.anote2daemon.security.RestPermissionsEvaluatorEnum;
import com.silicolife.anote2daemon.utils.GenericPairSpringSpel;
import com.silicolife.anote2daemon.webservice.DaemonResponse;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.exceptions.ProcessException;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.security.Permissions;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.service.processes.IProcessesService;
import com.silicolife.textmining.core.datastructures.process.IEProcessImpl;
import com.silicolife.textmining.core.interfaces.process.IE.IIEProcess;
import com.silicolife.textmining.core.interfaces.process.IE.IIEProcessStatistics;


@RequestMapping(value = "/processes", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
@ResponseBody
@Controller
public class ProcessesController {
	@Autowired
	private Permissions permissions;
	@Autowired
	private GenericPairSpringSpel<RestPermissionsEvaluatorEnum, List<String>> genericPairSpringSpel;
	@Autowired
	private IProcessesService processesService;

	/**
	 * Create IEProcesses
	 * 
	 * @param processes_
	 * @return
	 */
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/createIEProcess", method = RequestMethod.POST, consumes = { "application/json" })
	public ResponseEntity<DaemonResponse<Boolean>> createIEProcess(@RequestBody IEProcessImpl processes_) {
		DaemonResponse<Boolean> response = new DaemonResponse<Boolean>(processesService.createIEProcess(processes_));
		return new ResponseEntity<DaemonResponse<Boolean>>(response, HttpStatus.OK);
	}

	/**
	 * Update processes
	 * 
	 * @param processes_
	 * @return
	 * @throws ProcessException 
	 */
	@PreAuthorize("isAuthenticated() and hasPermission(#processes_.getId(), "
			+ "T(com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.utils.ResourcesTypeUtils).processes.getName(),"
			+ "@genericPairSpringSpel.getGenericPairSpringSpel(T(com.silicolife.anote2daemon.security.RestPermissionsEvaluatorEnum).default_,@permissions.getWritegrant()))")
	@RequestMapping(value = "/updateIEProcess", method = RequestMethod.PUT, consumes = { "application/json" })
	public ResponseEntity<DaemonResponse<Boolean>> updateIEProcess(@RequestBody IEProcessImpl processes_) throws ProcessException {
		DaemonResponse<Boolean> response = new DaemonResponse<Boolean>(processesService.updateIEProcess(processes_));
		return new ResponseEntity<DaemonResponse<Boolean>>(response, HttpStatus.OK);
	}

	/**
	 * get process by id
	 * 
	 * @param id
	 * @return
	 * @throws ProcessException 
	 */
	@PreAuthorize("isAuthenticated() and hasPermission(#processId, "
			+ "T(com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.utils.ResourcesTypeUtils).processes.getName(),"
			+ "@genericPairSpringSpel.getGenericPairSpringSpel(T(com.silicolife.anote2daemon.security.RestPermissionsEvaluatorEnum).default_,@permissions.getFullgrant()))")
	@RequestMapping(value = "/getProcessByID/{processId}", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<IIEProcess>> getProcessByID(@PathVariable Long processId) throws ProcessException {
		DaemonResponse<IIEProcess> response = new DaemonResponse<IIEProcess>(processesService.getProcessByID(processId));
		return new ResponseEntity<DaemonResponse<IIEProcess>>(response, HttpStatus.OK);
	}
	
	

	/**
	 * Get processes statistics
	 * 
	 * @param processId
	 * @return
	 * @throws ProcessException
	 */
	@PreAuthorize("isAuthenticated() and hasPermission(#processId, "
			+ "T(com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.utils.ResourcesTypeUtils).processes.getName(),"
			+ "@genericPairSpringSpel.getGenericPairSpringSpel(T(com.silicolife.anote2daemon.security.RestPermissionsEvaluatorEnum).default_,@permissions.getFullgrant()))")
	@RequestMapping(value = "/getProcessStatistics/{processId}", method = RequestMethod.GET)
	public  ResponseEntity<DaemonResponse<IIEProcessStatistics>>  getProcessStatistics(@PathVariable Long processId) throws ProcessException{
		DaemonResponse<IIEProcessStatistics> response = new DaemonResponse<IIEProcessStatistics>(processesService.getProcessStatistics(processId));
		return new ResponseEntity<DaemonResponse<IIEProcessStatistics>>(response, HttpStatus.OK);
		
	}

	/**
	 * Get All Processes with Owner privilges or admin
	 * 
	 * @return
	 */
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/getPrivilegesAllProcessesAdminAccess", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<List<IIEProcess>>> getPrivilegesAllProcessesAdminAccess(){
		DaemonResponse<List<IIEProcess>> response = new DaemonResponse<List<IIEProcess>>(processesService.getPrivilegesAllProcessesAdminAccess());
		return new ResponseEntity<DaemonResponse<List<IIEProcess>>>(response, HttpStatus.OK);
	}
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/getProcessesByPublicationId/{publicationId}", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<List<IIEProcess>>> getProcessesByPublicationId(@PathVariable Long publicationId) throws ProcessException{
		DaemonResponse<List<IIEProcess>> response = new DaemonResponse<List<IIEProcess>>(processesService.getProcessesByPublicationId(publicationId));
		return new ResponseEntity<DaemonResponse<List<IIEProcess>>>(response, HttpStatus.OK);
	}
}
