package com.silicolife.anote2daemon.controller.runserverprocesses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.silicolife.anote2daemon.webservice.DaemonResponse;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.security.Permissions;
import com.silicolife.textmining.core.datastructures.process.ConfigurationImpl;

/**
 * The goal of this class is to expose for the web all Publications
 * functionalities of anote2daemon. It is necessary a user logged to access
 * these methods
 * 
 * @author Joel Azevedo Costa
 * @year 2015
 *
 */
@RequestMapping(value = "/runserverprocesses", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
@ResponseBody
@Controller
public class RunServerProcessesController {

	@Autowired
	private Permissions permissions;



	/**
	 * 
	 * 
	 * @param request
	 * @return
	 */
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/configuration", method = RequestMethod.POST, consumes = { "application/json" })
	public ResponseEntity<DaemonResponse<Boolean>> executeConfiguration(@RequestBody ConfigurationImpl configuration) {

		DaemonResponse<Boolean> response = new DaemonResponse<Boolean>(true);
		return new ResponseEntity<DaemonResponse<Boolean>>(response, HttpStatus.OK);
	}
}
