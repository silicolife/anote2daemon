package com.silicolife.anote2daemon.controller.runserverprocesses;

import java.io.IOException;

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

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.silicolife.anote2daemon.processes.ir.PubMedSearchServerRunExtension;
import com.silicolife.anote2daemon.webservice.DaemonResponse;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.exceptions.RunServerProcessesException;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.security.Permissions;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.service.publications.PublicationsService;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.service.queries.QueriesService;
import com.silicolife.textmining.core.datastructures.exceptions.process.InvalidConfigurationException;
import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;
import com.silicolife.textmining.core.interfaces.process.IR.exception.InternetConnectionProblemException;
import com.silicolife.textmining.processes.ir.pubmed.configuration.IRPubmedSearchConfigurationImpl;

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

	@Autowired
	private QueriesService queriesService;
	
	@Autowired
	private PublicationsService publicationsService;


	/**
	 * 
	 * 
	 * @param request
	 * @return
	 */
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/configuration", method = RequestMethod.POST, consumes = { "application/json" })
	public ResponseEntity<DaemonResponse<Boolean>> executeConfiguration(@RequestBody String[] parameters) throws RunServerProcessesException{
		System.out.println(parameters[0] + " " + parameters[1]);
		ObjectMapper bla = new ObjectMapper();
		try {
			switch (parameters[0]) {
				case IRPubmedSearchConfigurationImpl.pubmedsearchUID :
					IRPubmedSearchConfigurationImpl searchConfiguration = bla.readValue(parameters[1],IRPubmedSearchConfigurationImpl.class);
					new PubMedSearchServerRunExtension(queriesService,publicationsService).search(searchConfiguration);
					break;
				default :
					break;
			}
		} catch (JsonParseException e) {
			throw new RunServerProcessesException(e);
		} catch (JsonMappingException e) {
			throw new RunServerProcessesException(e);
		} catch (IOException e) {
			throw new RunServerProcessesException(e);
		} catch (ANoteException e) {
			throw new RunServerProcessesException(e);
		} catch (InternetConnectionProblemException e) {
			throw new RunServerProcessesException(e);
		} catch (InvalidConfigurationException e) {
			throw new RunServerProcessesException(e);
		}
		DaemonResponse<Boolean> response = new DaemonResponse<Boolean>(true);
		return new ResponseEntity<DaemonResponse<Boolean>>(response, HttpStatus.OK);
	}
}
