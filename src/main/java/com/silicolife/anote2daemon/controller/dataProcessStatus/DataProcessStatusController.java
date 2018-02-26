package com.silicolife.anote2daemon.controller.dataProcessStatus;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.silicolife.anote2daemon.webservice.DaemonResponse;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.security.Permissions;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.service.dataProcessStatus.IDataProcessStatusService;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.utils.ProcessStatusResourceTypesEnum;
import com.silicolife.textmining.core.interfaces.core.general.IDataProcessStatus;

@RequestMapping(value = "/dataProcessStatus", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
@ResponseBody
@Controller
public class DataProcessStatusController {
	
	@Autowired
	private Permissions permissions;
	
	@Autowired
	private IDataProcessStatusService dataProcessStatusService;
	
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/getAllDataProcessStatus", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<List<IDataProcessStatus>>> getAllDataProcessStatus() {
		DaemonResponse<List<IDataProcessStatus>> response = new DaemonResponse<List<IDataProcessStatus>>(dataProcessStatusService.getAllDataProcessStatus());
		return new ResponseEntity<DaemonResponse<List<IDataProcessStatus>>>(response, HttpStatus.OK);
	}
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/getDataProcessStatusById/{id}", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<IDataProcessStatus>> getDataProcessStatusById(@PathVariable int id) {
		DaemonResponse<IDataProcessStatus> response = new DaemonResponse<IDataProcessStatus>(dataProcessStatusService.getDataProcessStatusById(id));
		return new ResponseEntity<DaemonResponse<IDataProcessStatus>>(response, HttpStatus.OK);
	}
	
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/getUserDataProcessStatus", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<List<IDataProcessStatus>>> getUserDataProcessStatus() {
		DaemonResponse<List<IDataProcessStatus>> response = new DaemonResponse<List<IDataProcessStatus>>(dataProcessStatusService.getUserDataProcessStatus());
		return new ResponseEntity<DaemonResponse<List<IDataProcessStatus>>>(response, HttpStatus.OK);
	}
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/getUserRecentDataProcessStatus", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<List<IDataProcessStatus>>> getUserRecentDataProcessStatus() {
		DaemonResponse<List<IDataProcessStatus>> response = new DaemonResponse<List<IDataProcessStatus>>(dataProcessStatusService.getUserRecentDataProcessStatus());
		return new ResponseEntity<DaemonResponse<List<IDataProcessStatus>>>(response, HttpStatus.OK);
	}
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/getUserRecentDataProcessStatusSorted", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<List<IDataProcessStatus>>> getUserRecentDataProcessStatusSorted() {
		DaemonResponse<List<IDataProcessStatus>> response = new DaemonResponse<List<IDataProcessStatus>>(dataProcessStatusService.getUserRecentDataProcessStatusSorted());
		return new ResponseEntity<DaemonResponse<List<IDataProcessStatus>>>(response, HttpStatus.OK);
	}
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/getUserRecentDataProcessStatusSortedOfType/{type}", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<List<IDataProcessStatus>>> getUserRecentDataProcessStatusSortedOfType(@PathVariable ProcessStatusResourceTypesEnum type) {
		DaemonResponse<List<IDataProcessStatus>> response = new DaemonResponse<List<IDataProcessStatus>>(dataProcessStatusService.getUserRecentDataProcessStatusSortedOfType(type));
		return new ResponseEntity<DaemonResponse<List<IDataProcessStatus>>>(response, HttpStatus.OK);
	}
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/getUserRecentDataProcessStatusSortedWLimit/{paginationSize}", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<List<IDataProcessStatus>>> getUserRecentDataProcessStatusSortedWLimit(@PathVariable Integer paginationSize) {
		DaemonResponse<List<IDataProcessStatus>> response = new DaemonResponse<List<IDataProcessStatus>>(dataProcessStatusService.getUserRecentDataProcessStatusSortedWLimit(paginationSize));
		return new ResponseEntity<DaemonResponse<List<IDataProcessStatus>>>(response, HttpStatus.OK);
	}
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/getUserRecentDataProcessStatusSortedOfTypeWLimit/{type}/{paginationSize}", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<List<IDataProcessStatus>>> getUserRecentDataProcessStatusSortedOfTypeWLimit(@PathVariable ProcessStatusResourceTypesEnum type,@PathVariable Integer paginationSize) {
		DaemonResponse<List<IDataProcessStatus>> response = new DaemonResponse<List<IDataProcessStatus>>(dataProcessStatusService.getUserRecentDataProcessStatusSortedOfTypeWLimit(type,paginationSize));
		return new ResponseEntity<DaemonResponse<List<IDataProcessStatus>>>(response, HttpStatus.OK);
	}
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/getAdminPrivilegesProcessStatusByTypeSortedPaginated/{type}/{paginationIndex}/{paginationSize}/{asc}/{sortBy}", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<List<IDataProcessStatus>>> getAdminPrivilegesProcessStatusByTypeSortedPaginated(@PathVariable ProcessStatusResourceTypesEnum type,@PathVariable int paginationIndex,@PathVariable int paginationSize, @PathVariable boolean asc, @PathVariable String sortBy) {
		DaemonResponse<List<IDataProcessStatus>> response = new DaemonResponse<List<IDataProcessStatus>>(dataProcessStatusService.getAdminPrivilegesProcessStatusByTypeSortedPaginated(type,paginationIndex, paginationSize, asc, sortBy));
		return new ResponseEntity<DaemonResponse<List<IDataProcessStatus>>>(response, HttpStatus.OK);
	}
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/getAdminPrivilegesProcessStatusSortedPaginated/{paginationIndex}/{paginationSize}/{asc}/{sortBy}", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<List<IDataProcessStatus>>> getAdminPrivilegesProcessStatusSortedPaginated(@PathVariable int paginationIndex,@PathVariable int paginationSize, @PathVariable boolean asc, @PathVariable String sortBy) {
		DaemonResponse<List<IDataProcessStatus>> response = new DaemonResponse<List<IDataProcessStatus>>(dataProcessStatusService.getAdminPrivilegesProcessStatusSortedPaginated(paginationIndex, paginationSize, asc, sortBy));
		return new ResponseEntity<DaemonResponse<List<IDataProcessStatus>>>(response, HttpStatus.OK);
	}
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/countAdminPrivilegesProcessStatus", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<Integer>> countAdminPrivilegesProcessStatus() {
		DaemonResponse<Integer> response = new DaemonResponse<Integer>(dataProcessStatusService.countAdminPrivilegesProcessStatus());
		return new ResponseEntity<DaemonResponse<Integer>>(response, HttpStatus.OK);
	}
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/countRunningOrStartDataProcessStatusforUser", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<Integer>> countRunningOrStartDataProcessStatusforUser() {
		DaemonResponse<Integer> response = new DaemonResponse<Integer>(dataProcessStatusService.countRunningOrStartDataProcessStatusforUser());
		return new ResponseEntity<DaemonResponse<Integer>>(response, HttpStatus.OK);
	}
	
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/countAdminPrivilegesProcessStatusByType/{type}", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<Integer>> countAdminPrivilegesProcessStatusByType(@PathVariable ProcessStatusResourceTypesEnum type) {
		DaemonResponse<Integer> response = new DaemonResponse<Integer>(dataProcessStatusService.countAdminPrivilegesProcessStatusByType(type));
		return new ResponseEntity<DaemonResponse<Integer>>(response, HttpStatus.OK);
	}
	
}
