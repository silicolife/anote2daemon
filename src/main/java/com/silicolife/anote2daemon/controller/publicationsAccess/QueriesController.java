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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.silicolife.anote2daemon.model.core.RelevanceType;
import com.silicolife.anote2daemon.model.core.entities.Publications;
import com.silicolife.anote2daemon.model.core.entities.Queries;
import com.silicolife.anote2daemon.service.queries.QueriesService;
import com.silicolife.anote2daemon.webservice.DaemonResponse;

/**
 * API queries functionalities. All these methods are exposed to the web. It is
 * necessary a user logged to access these methods
 * 
 * 
 * 
 * 
 * @author Joel Azevedo Costa
 * @year 2015
 *
 */
@RequestMapping(value = "/queries")
@ResponseBody
@Controller
public class QueriesController {

	@Autowired
	private QueriesService queriesService;


	/**
	 * Get All queries
	 * 
	 * 
	 * @return
	 */
	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/getAllQueries", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<List<Queries>>> getAllQueries() {
		
		DaemonResponse<List<Queries>> response = new DaemonResponse<List<Queries>>(queriesService.getAll());
		return new ResponseEntity<DaemonResponse<List<Queries>>>(response, HttpStatus.OK);
	}

	/**
	 * Get query by id
	 * 
	 * @param id
	 * @return
	 */
	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/getQueryById/{id}", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<Queries>> getQueryById(@PathVariable Long id) {
	
		DaemonResponse<Queries> response = new DaemonResponse<Queries>(queriesService.getById(id));
		return new ResponseEntity<DaemonResponse<Queries>>(response, HttpStatus.OK);
	}

	/**
	 * Get all publications from a query
	 * 
	 * @param id
	 * @return
	 */
	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/getAllPublications/{id}", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<List<Publications>>> getAllPublications(@PathVariable Long id) {
	
		DaemonResponse<List<Publications>> response = new DaemonResponse<List<Publications>>(queriesService.getQueryPublications(id));
		return new ResponseEntity<DaemonResponse<List<Publications>>>(response, HttpStatus.OK);
	}

	/**
	 * Create query
	 * 
	 * @param request
	 * @return
	 */
	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/createQuery", method = RequestMethod.PUT, consumes = { "application/json" })
	public ResponseEntity<DaemonResponse<Boolean>> createQuery(@RequestBody Queries query) {
		DaemonResponse<Boolean> response = new DaemonResponse<Boolean>(queriesService.create(query));
		return new ResponseEntity<DaemonResponse<Boolean>>(response, HttpStatus.OK);
	}

	/**
	 * Associate a list of publications to a query
	 * 
	 * @param id
	 * @param request
	 * @return
	 */
	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/addPublicationsToQuery", method = RequestMethod.PUT, consumes = { "application/json" })
	public ResponseEntity<DaemonResponse<Boolean>> addPublicationsToQuery(@RequestParam Long id, @RequestBody List<Long> publicationsIds) {
		DaemonResponse<Boolean> response = new DaemonResponse<Boolean>(queriesService.addPublicationsToQuery(id, publicationsIds));
		return new ResponseEntity<DaemonResponse<Boolean>>(response, HttpStatus.OK);
	}

	/**
	 * Get all publications from a query
	 * 
	 * @param id
	 * @return
	 */
	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/updateRelevance", method = RequestMethod.POST)
	public ResponseEntity<DaemonResponse<Boolean>> updateRelevance(@RequestParam Long queryId, @RequestParam Long publicationId, @RequestParam String relevance) {
		DaemonResponse<Boolean> response = new DaemonResponse<Boolean>(queriesService.updateRelevance(queryId, publicationId, relevance));
		return new ResponseEntity<DaemonResponse<Boolean>>(response, HttpStatus.OK);
	}

	/**
	 * Get Publications from queries and relevance
	 * 
	 * @param queryId
	 * @return
	 */
	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/getQueryPublicationsRelevance/{queryId}", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<Map<Long, RelevanceType>>> getQueryPublicationsRelevance(@PathVariable Long queryId) {
		DaemonResponse<Map<Long, RelevanceType>> response = new DaemonResponse<Map<Long, RelevanceType>>(queriesService.getQueryPublicationsRelevance(queryId));
		return new ResponseEntity<DaemonResponse<Map<Long, RelevanceType>>>(response, HttpStatus.OK);
	}
}
