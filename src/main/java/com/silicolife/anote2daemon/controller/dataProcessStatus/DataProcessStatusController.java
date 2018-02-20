package com.silicolife.anote2daemon.controller.dataProcessStatus;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.silicolife.anote2daemon.webservice.DaemonResponse;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.security.Permissions;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.service.dataProcessStatus.IDataProcessStatusService;
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
}
