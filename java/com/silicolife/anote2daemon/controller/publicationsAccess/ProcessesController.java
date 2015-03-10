package com.silicolife.anote2daemon.controller.publicationsAccess;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pt.uminho.anote2.process.IE.IIEProcess;

import com.silicolife.anote2daemon.security.Permissions;
import com.silicolife.anote2daemon.service.processes.ProcessesService;
import com.silicolife.anote2daemon.webservice.DaemonResponse;

@RequestMapping(value = "/processes")
@ResponseBody
@Controller
public class ProcessesController {
	@Autowired
	private Permissions permissions;
	@Autowired
	private ProcessesService processesService;

	/**
	 * Create IEProcesses
	 * 
	 * @param processes_
	 * @return
	 */
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/createIEProcess", method = RequestMethod.PUT, consumes = { "application/json" })
	public ResponseEntity<DaemonResponse<Boolean>> createIEProcess(@RequestBody IIEProcess processes_) {
		DaemonResponse<Boolean> response = new DaemonResponse<Boolean>(processesService.createIEProcess(processes_));
		return new ResponseEntity<DaemonResponse<Boolean>>(response, HttpStatus.OK);
	}

	/**
	 * Update processes
	 * 
	 * @param processes_
	 * @return
	 */
	@PreAuthorize("hasRole('ROLE_ADMIN') and hasPermission(#processes_.getID(), T(com.silicolife.anote2daemon.utils.ResourcesTypeUtils).processes.toString(), @permissions.getWritegrant())")
	@RequestMapping(value = "/updateIEProcess", method = RequestMethod.POST, consumes = { "application/json" })
	public ResponseEntity<DaemonResponse<Boolean>> updateIEProcess(@RequestBody IIEProcess processes_) {
		DaemonResponse<Boolean> response = new DaemonResponse<Boolean>(processesService.updateIEProcess(processes_));
		return new ResponseEntity<DaemonResponse<Boolean>>(response, HttpStatus.OK);
	}

	/**
	 * get process by id
	 * 
	 * @param id
	 * @return
	 */
	@PreAuthorize("hasRole('ROLE_ADMIN') and hasPermission(#id, T(com.silicolife.anote2daemon.utils.ResourcesTypeUtils).processes.toString(), @permissions.getFullgrant())")
	@RequestMapping(value = "/getProcessByID", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<IIEProcess>> getProcessByID(@PathVariable Long id) {
		DaemonResponse<IIEProcess> response = new DaemonResponse<IIEProcess>(processesService.getProcessByID(id));
		return new ResponseEntity<DaemonResponse<IIEProcess>>(response, HttpStatus.OK);
	}
}
