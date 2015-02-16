package com.silicolife.anote2daemon.controller.publicationsAccess;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import pt.uminho.anote2.core.document.IPublication;
import pt.uminho.anote2.process.IR.IQuery;

import com.silicolife.anote2daemon.model.core.RelevanceType;
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
	public ResponseEntity<DaemonResponse<List<IQuery>>> getAllQueries() {
		DaemonResponse<List<IQuery>> response = new DaemonResponse<List<IQuery>>(queriesService.getAll());
		return new ResponseEntity<DaemonResponse<List<IQuery>>>(response, HttpStatus.OK);
	}

	/**
	 * Get query by id
	 * 
	 * @param id
	 * @return
	 */
//	@PreAuthorize("hasRole('ROLE_ADMIN') and hasPermission(#id, 'queries', 'queryOwner')")
	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/getQueryById/{id}", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<IQuery>> getQueryById(@PathVariable Long id) {
		DaemonResponse<IQuery> response = new DaemonResponse<IQuery>(queriesService.getById(id));
		return new ResponseEntity<DaemonResponse<IQuery>>(response, HttpStatus.OK);
	}

	/**
	 * Get all publications from a query
	 * 
	 * @param id
	 * @return
	 */
	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/getAllPublications/{id}", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<List<IPublication>>> getAllPublications(@PathVariable Long id) {
	
		DaemonResponse<List<IPublication>> response = new DaemonResponse<List<IPublication>>(queriesService.getQueryPublications(id));
		return new ResponseEntity<DaemonResponse<List<IPublication>>>(response, HttpStatus.OK);
	}

	/**
	 * Create query
	 * 
	 * @param request
	 * @return
	 */
	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/createQuery", method = RequestMethod.PUT, consumes = { "application/json" })
	public ResponseEntity<DaemonResponse<Boolean>> createQuery(@RequestBody IQuery query) {
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
