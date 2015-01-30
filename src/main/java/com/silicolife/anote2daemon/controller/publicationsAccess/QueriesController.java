package com.silicolife.anote2daemon.controller.publicationsAccess;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

import com.silicolife.anote2daemon.model.pojo.Publications;
import com.silicolife.anote2daemon.model.pojo.Queries;
import com.silicolife.anote2daemon.service.core.queries.QueriesService;
import com.silicolife.anote2daemon.webservice.DaemonRequest;
import com.silicolife.anote2daemon.webservice.DaemonResponse;

/**
 * API queries functionalities. All these methods are exposed to the web.
 * It is necessary a user logged to access these methods
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
	public ResponseEntity<DaemonResponse<Map<String, Set<Queries>>>> getAllQueries() {
		DaemonResponse<Map<String, Set<Queries>>> response = new DaemonResponse<Map<String, Set<Queries>>>(queriesService.getAll());
		return new ResponseEntity<DaemonResponse<Map<String, Set<Queries>>>>(response, HttpStatus.OK);
	}

	/**
	 * Get query by id
	 * 
	 * @param id
	 * @return
	 */
	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/getQueryById/{id}", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<Map<String, Queries>>> getQueryById(@PathVariable Long id) {
		DaemonResponse<Map<String, Queries>> response = new DaemonResponse<Map<String, Queries>>(queriesService.getById(id));
		return new ResponseEntity<DaemonResponse<Map<String, Queries>>>(response, HttpStatus.OK);
	}

	/**
	 * Get all publications from a query
	 * 
	 * @param id
	 * @return
	 */
	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/getAllPublications/{id}", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<Map<String, Set<Publications>>>> getAllPublications(@PathVariable Long id) {
		DaemonResponse<Map<String, Set<Publications>>> response = new DaemonResponse<Map<String, Set<Publications>>>(queriesService.getAllPublications(id));
		return new ResponseEntity<DaemonResponse<Map<String, Set<Publications>>>>(response, HttpStatus.OK);
	}

	/**
	 * Create query
	 * 
	 * @param request
	 * @return
	 */
	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/createQuery", method = RequestMethod.PUT, consumes = { "application/json" })
	public ResponseEntity<DaemonResponse<Boolean>> createQuery(@RequestBody DaemonRequest<Queries> request) {
		Queries query = request.getContent();
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
	public ResponseEntity<DaemonResponse<Boolean>> addPublicationsToQuery(@RequestParam Long id, @RequestBody DaemonRequest<ArrayList<Long>> request) {
		List<Long> publicationsIds = request.getContent();
		DaemonResponse<Boolean> response = new DaemonResponse<Boolean>(queriesService.addPublicationsToQuery(id, publicationsIds));
		return new ResponseEntity<DaemonResponse<Boolean>>(response, HttpStatus.OK);
	}
}
