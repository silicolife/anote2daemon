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
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.lucene.service.processes.IProcessesLuceneService;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.security.Permissions;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.service.processes.IProcessesService;
import com.silicolife.textmining.core.datastructures.documents.SearchPropertiesImpl;
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
	@Autowired
	private IProcessesLuceneService processesLuceneService;

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
	
	/**
	 * Get All Processes Paginated with Owner privilges or admin
	 * 
	 * @return
	 */
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/getPrivilegesAllProcessesAdminAccessPaginated/{paginationIndex}/{paginationSize}/{asc}/{sortBy}", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<List<IIEProcess>>> getPrivilegesAllProcessesAdminAccessPaginated(@PathVariable Long paginationIndex, @PathVariable Long paginationSize, @PathVariable boolean asc, @PathVariable String sortBy){
		DaemonResponse<List<IIEProcess>> response = new DaemonResponse<List<IIEProcess>>(processesService.getPrivilegesAllProcessesAdminAccessPaginated(Integer.valueOf(paginationIndex.toString()), Integer.valueOf(paginationSize.toString()), asc, sortBy));
		return new ResponseEntity<DaemonResponse<List<IIEProcess>>>(response, HttpStatus.OK);
	}
	
	/**
	 * Get All Processes paginated with privileges
	 * 
	 * @return
	 */
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/getAllProcessesPaginated/{paginationIndex}/{paginationSize}/{asc}/{sortBy}", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<List<IIEProcess>>> getAllProcessesPaginated(@PathVariable Long paginationIndex, @PathVariable Long paginationSize, @PathVariable boolean asc, @PathVariable String sortBy){
		DaemonResponse<List<IIEProcess>> response = new DaemonResponse<List<IIEProcess>>(processesService.getAllProcessesPaginated(Integer.valueOf(paginationIndex.toString()), Integer.valueOf(paginationSize.toString()), asc, sortBy));
		return new ResponseEntity<DaemonResponse<List<IIEProcess>>>(response, HttpStatus.OK);
	}
	
	
	/**
	 * Count Processes with Owner privilges or admin
	 * 
	 * @return
	 */
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/countPrivilegesAllProcessesAdminAccess", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<Integer>> countPrivilegesAllProcessesAdminAccess() {
		DaemonResponse<Integer> response = new DaemonResponse<Integer>(processesService.countPrivilegesAllProcessesAdminAccess());
		return new ResponseEntity<DaemonResponse<Integer>>(response, HttpStatus.OK);
	}
	
	
	/**
	 * Count all Processes
	 * 
	 * @return
	 */
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/countAllProcesses", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<Integer>> countAllProcesses() {
		DaemonResponse<Integer> response = new DaemonResponse<Integer>(processesService.countAllProcesses());
		return new ResponseEntity<DaemonResponse<Integer>>(response, HttpStatus.OK);
	}
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/getProcessesByPublicationId/{publicationId}", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<List<IIEProcess>>> getProcessesByPublicationId(@PathVariable Long publicationId) throws ProcessException{
		DaemonResponse<List<IIEProcess>> response = new DaemonResponse<List<IIEProcess>>(processesService.getProcessesByPublicationId(publicationId));
		return new ResponseEntity<DaemonResponse<List<IIEProcess>>>(response, HttpStatus.OK);
	}
	
	//Lucene
	
	/**
	 * Get Processes From search paginated with privileges
	 * 
	 * @return
	 */
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/getProcessesFromSearchPaginatedWAuthAndSort/{paginationIndex}/{paginationSize}/{asc}/{sortBy}", method = RequestMethod.POST, consumes = { "application/json" })
	public ResponseEntity<DaemonResponse<List<IIEProcess>>> getProcessesFromSearchPaginatedWAuthAndSort(@RequestBody SearchPropertiesImpl searchProperties,@PathVariable Long paginationIndex, @PathVariable Long paginationSize, @PathVariable boolean asc, @PathVariable String sortBy){
		DaemonResponse<List<IIEProcess>> response = new DaemonResponse<List<IIEProcess>>(processesLuceneService.getProcessesFromSearchPaginatedWAuthAndSort(searchProperties, Integer.valueOf(paginationIndex.toString()), Integer.valueOf(paginationSize.toString()), asc, sortBy));
		return new ResponseEntity<DaemonResponse<List<IIEProcess>>>(response, HttpStatus.OK);
	}
	
	/**
	 * Count Processes of a search with privileges
	 * 
	 * @return
	 */
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/countProcessesFromSearchWAuth", method = RequestMethod.POST, consumes = { "application/json" })
	public ResponseEntity<DaemonResponse<Integer>> countProcessesFromSearchWAuth(@RequestBody SearchPropertiesImpl searchProperties) {
		DaemonResponse<Integer> response = new DaemonResponse<Integer>(processesLuceneService.countProcessesFromSearchWAuth(searchProperties));
		return new ResponseEntity<DaemonResponse<Integer>>(response, HttpStatus.OK);
	}
	
	/**
	 * Get Processes From search paginated if admin all active else all owner
	 * 
	 * @return
	 */
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/getPrivilegesProcessesAdminAccessFromSearchPaginated/{paginationIndex}/{paginationSize}/{asc}/{sortBy}", method = RequestMethod.POST, consumes = { "application/json" })
	public ResponseEntity<DaemonResponse<List<IIEProcess>>> getPrivilegesProcessesAdminAccessFromSearchPaginated(@RequestBody SearchPropertiesImpl searchProperties,@PathVariable Long paginationIndex, @PathVariable Long paginationSize, @PathVariable boolean asc, @PathVariable String sortBy){
		DaemonResponse<List<IIEProcess>> response = new DaemonResponse<List<IIEProcess>>(processesLuceneService.getPrivilegesProcessesAdminAccessFromSearchPaginated(searchProperties, Integer.valueOf(paginationIndex.toString()), Integer.valueOf(paginationSize.toString()), asc, sortBy));
		return new ResponseEntity<DaemonResponse<List<IIEProcess>>>(response, HttpStatus.OK);
	}
	
	/**
	 * Count Processes of a getPrivilegesProcessesAdminAccessFromSearchPaginated search
	 * 
	 * @return
	 */
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/countPrivilegesProcessesAdminAccessFromSearch", method = RequestMethod.POST, consumes = { "application/json" })
	public ResponseEntity<DaemonResponse<Integer>> countPrivilegesProcessesAdminAccessFromSearch(@RequestBody SearchPropertiesImpl searchProperties) {
		DaemonResponse<Integer> response = new DaemonResponse<Integer>(processesLuceneService.countPrivilegesProcessesAdminAccessFromSearch(searchProperties));
		return new ResponseEntity<DaemonResponse<Integer>>(response, HttpStatus.OK);
	}
	
	
}
