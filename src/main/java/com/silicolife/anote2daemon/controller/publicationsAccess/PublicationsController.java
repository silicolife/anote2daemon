package com.silicolife.anote2daemon.controller.publicationsAccess;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.silicolife.anote2daemon.model.pojo.Publications;
import com.silicolife.anote2daemon.service.core.PublicationsService;
import com.silicolife.anote2daemon.webservice.DaemonRequest;
import com.silicolife.anote2daemon.webservice.DaemonResponse;

/**
 * API publications functionalities. All these methods are exposed to the web.
 * It is necessary a user logged to access these methods
 * 
 * 
 * 
 * @author Joel Azevedo Costa
 * @year 2015
 *
 */

@RequestMapping(value = "/publications")
@ResponseBody
@Controller
public class PublicationsController {

	@Autowired
	private PublicationsService publicationService;

	/**
	 * 
	 * Get Publication by id (without fulltext)
	 * 
	 * @param id
	 * @return
	 */
	@Secured("ROLE_EMPLOYEES")
	@RequestMapping(value = "/getPublicationById/{id}", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<Publications>> getPublicationById(@PathVariable Long id) {
		DaemonResponse<Publications> response = new DaemonResponse<Publications>(publicationService.getById(id));
		return new ResponseEntity<DaemonResponse<Publications>>(response, HttpStatus.OK);
	}

	/**
	 * 
	 * Create multiple publications at same time
	 * 
	 * @param request
	 * @return
	 */
	@Secured("ROLE_EMPLOYEES")
	@RequestMapping(value = "/createMultiplePublications", method = RequestMethod.PUT, consumes = { "application/json" })
	public ResponseEntity<DaemonResponse<Boolean>> createMultiplePublications(@RequestBody DaemonRequest<List<Publications>> request) {
		List<Publications> publications = request.getContent();
		DaemonResponse<Boolean> response = new DaemonResponse<Boolean>(publicationService.addPublications(publications));
		return new ResponseEntity<DaemonResponse<Boolean>>(response, HttpStatus.OK);
	}

	/**
	 * 
	 * Update publication
	 * 
	 * @param request
	 * @return
	 */
	@Secured("ROLE_EMPLOYEES")
	@RequestMapping(value = "/updatePublication", method = RequestMethod.PUT, consumes = { "application/json" })
	public ResponseEntity<DaemonResponse<Boolean>> updatePublication(@RequestBody DaemonRequest<Publications> request) {
		Publications publication = request.getContent();
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
	@Secured("ROLE_EMPLOYEES")
	@RequestMapping(value = "/getAllPublicationsFromSource/{source}", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<Map<String, Long>>> getAllPublicationsFromSource(@PathVariable String source) {
		DaemonResponse<Map<String, Long>> response = new DaemonResponse<Map<String, Long>>(publicationService.getAllPublicationsFromSource(source));
		return new ResponseEntity<DaemonResponse<Map<String, Long>>>(response, HttpStatus.OK);
	}
}
