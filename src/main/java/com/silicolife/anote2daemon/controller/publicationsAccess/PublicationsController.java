package com.silicolife.anote2daemon.controller.publicationsAccess;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import pt.uminho.anote2.datastructures.dataaccess.database.dataaccess.implementation.exceptions.PublicationManagerException;
import pt.uminho.anote2.datastructures.dataaccess.database.dataaccess.implementation.service.publications.PublicationsService;
import pt.uminho.anote2.datastructures.documents.PublicationImpl;
import pt.uminho.anote2.interfaces.core.document.IPublication;

import com.silicolife.anote2daemon.security.Permissions;
import com.silicolife.anote2daemon.webservice.DaemonResponse;

/**
 * The goal of this class is to expose for the web all Publications
 * functionalities of anote2daemon. It is necessary a user logged to access
 * these methods
 * 
 * @author Joel Azevedo Costa
 * @year 2015
 *
 */
@RequestMapping(value = "/publications", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
@ResponseBody
@Controller
public class PublicationsController {

	@Autowired
	private Permissions permissions;
	@Autowired
	private PublicationsService publicationService;

	/**
	 * Get fulltext from publication
	 * 
	 * @param id
	 * @return
	 * @throws PublicationManagerException
	 */
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/getFullText/{id}", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<String>> getFullText(@PathVariable Long id) {
		DaemonResponse<String> response = new DaemonResponse<String>(publicationService.getFullText(id));
		return new ResponseEntity<DaemonResponse<String>>(response, HttpStatus.OK);
	}

	/**
	 * 
	 * Get Publication by id (without fulltext)
	 * 
	 * @param id
	 * @return
	 */
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/getPublicationById/{id}", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<IPublication>> getPublicationById(@PathVariable Long id) {
		DaemonResponse<IPublication> response = new DaemonResponse<IPublication>(publicationService.getById(id));
		return new ResponseEntity<DaemonResponse<IPublication>>(response, HttpStatus.OK);
	}

	/**
	 * 
	 * Create multiple publications at same time
	 * 
	 * @param request
	 * @return
	 */
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/createPublications", method = RequestMethod.POST, consumes = { "application/json" })
	public ResponseEntity<DaemonResponse<Boolean>> createMultiplePublications(@RequestBody List<PublicationImpl> publications) {
		List<IPublication> publicationsList = new ArrayList<IPublication>();
		for (PublicationImpl pub : publications)
			publicationsList.add(pub);

		DaemonResponse<Boolean> response = new DaemonResponse<Boolean>(publicationService.create(publicationsList));
		return new ResponseEntity<DaemonResponse<Boolean>>(response, HttpStatus.OK);
	}

	/**
	 * 
	 * Update publication
	 * 
	 * @param request
	 * @return
	 */
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/updatePublication", method = RequestMethod.PUT, consumes = { "application/json" })
	public ResponseEntity<DaemonResponse<Boolean>> updatePublication(@RequestBody PublicationImpl publication) {
		DaemonResponse<Boolean> response = new DaemonResponse<Boolean>(publicationService.update(publication));
		return new ResponseEntity<DaemonResponse<Boolean>>(response, HttpStatus.OK);
	}

	/**
	 * 
	 * Get all publications from a source
	 * 
	 * @param source
	 * @return
	 */
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/getAllPublicationsExternalIdFromSource/{source}", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<Map<String, Long>>> getAllPublicationsFromSource(@PathVariable String source) {
		DaemonResponse<Map<String, Long>> response = new DaemonResponse<Map<String, Long>>(publicationService.getAllPublicationsIdFromSource(source));
		return new ResponseEntity<DaemonResponse<Map<String, Long>>>(response, HttpStatus.OK);
	}

	/**
	 * Update only pub available free full text
	 * 
	 * @param pubId
	 * @param isAvailable
	 * @return
	 * @throws PublicationManagerException
	 */
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/updatePublicationAvailableFreeFullText", method = RequestMethod.PUT)
	public ResponseEntity<DaemonResponse<Boolean>> updatePublicationAvailableFreeFullText(@RequestParam Long publicationId, @RequestParam Boolean isAvailable) {
		DaemonResponse<Boolean> response = new DaemonResponse<Boolean>(publicationService.updatePublicationAvailableFreeFullText(publicationId, isAvailable));
		return new ResponseEntity<DaemonResponse<Boolean>>(response, HttpStatus.OK);
	}
}
