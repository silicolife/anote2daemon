package com.silicolife.anote2daemon.controller.publicationsAccess;

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

import pt.uminho.anote2.datastructures.dataaccess.database.dataaccess.implementation.service.queries.QueriesService;
import pt.uminho.anote2.datastructures.documents.query.QueryImpl;
import pt.uminho.anote2.interfaces.core.document.IPublication;
import pt.uminho.anote2.interfaces.core.document.relevance.RelevanceTypeEnum;
import pt.uminho.anote2.interfaces.process.IR.IQuery;

import com.silicolife.anote2daemon.security.Permissions;
import com.silicolife.anote2daemon.webservice.DaemonResponse;

/**
 * The goal of this class is to expose for the web all Clustering
 * functionalities of anote2daemon. It is necessary a user logged to access
 * these methods
 * 
 * @author Joel Azevedo Costa
 * @year 2015
 *
 */
@RequestMapping(value = "/queries", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
@ResponseBody
@Controller
public class QueriesController {

	@Autowired
	private Permissions permissions;
	@Autowired
	private QueriesService queriesService;

	/**
	 * Get All queries
	 * 
	 * @return
	 */
	@PreAuthorize("isAuthenticated()")
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
	@PreAuthorize("isAuthenticated() and hasPermission(#id, T(com.silicolife.anote2daemon.utils.ResourcesTypeUtils).queries.toString(), @permissions.getFullgrant())")
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
	@PreAuthorize("isAuthenticated() and hasPermission(#id, T(com.silicolife.anote2daemon.utils.ResourcesTypeUtils).queries.toString(), @permissions.getFullgrant())")
	@RequestMapping(value = "/getAllPublications/{id}", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<List<IPublication>>> getAllPublications(@PathVariable Long id) {
		DaemonResponse<List<IPublication>> response = new DaemonResponse<List<IPublication>>(queriesService.getQueryPublications(id));
		return new ResponseEntity<DaemonResponse<List<IPublication>>>(response, HttpStatus.OK);
	}

	/**
	 * Inactive / remove a query
	 * 
	 * @param id
	 * @return
	 */
	@PreAuthorize("isAuthenticated() and hasPermission(#id, T(com.silicolife.anote2daemon.utils.ResourcesTypeUtils).queries.toString(), @permissions.getFullgrant())")
	@RequestMapping(value = "/inactiveQuery/{id}", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<Boolean>> inactiveQuery(@PathVariable Long id) {
		DaemonResponse<Boolean> response = new DaemonResponse<Boolean>(queriesService.inactiveQuery(id));
		return new ResponseEntity<DaemonResponse<Boolean>>(response, HttpStatus.OK);
	}
	
	/**
	 * Create query
	 * 
	 * @param request
	 * @return
	 */
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/createQuery", method = RequestMethod.POST, consumes = { "application/json" })
	public ResponseEntity<DaemonResponse<Boolean>> createQuery(@RequestBody QueryImpl query) {
		DaemonResponse<Boolean> response = new DaemonResponse<Boolean>(queriesService.create(query));
		return new ResponseEntity<DaemonResponse<Boolean>>(response, HttpStatus.OK);
	}

	/**
	 * Update a query
	 * 
	 * @param query
	 * @return
	 */
	@PreAuthorize("isAuthenticated() and hasPermission(#id, T(com.silicolife.anote2daemon.utils.ResourcesTypeUtils).queries.toString(), @permissions.getWritegrant())")
	@RequestMapping(value = "/updateQuery", method = RequestMethod.PUT, consumes = { "application/json" })
	public ResponseEntity<DaemonResponse<Boolean>> updateQuery(@RequestBody QueryImpl query) {
		DaemonResponse<Boolean> response = new DaemonResponse<Boolean>(queriesService.update(query));
		return new ResponseEntity<DaemonResponse<Boolean>>(response, HttpStatus.OK);
	}

	/**
	 * Associate a list of publications to a query
	 * 
	 * @param id
	 * @param request
	 * @return
	 */
	@PreAuthorize("isAuthenticated() and hasPermission(#queryId, T(com.silicolife.anote2daemon.utils.ResourcesTypeUtils).queries.toString(), @permissions.getWritegrant())")
	@RequestMapping(value = "/addPublicationsToQuery/{queryId}", method = RequestMethod.POST, consumes = { "application/json" })
	public ResponseEntity<DaemonResponse<Boolean>> addPublicationsToQuery(@PathVariable Long queryId, @RequestBody List<Long> publicationsIds) {
		DaemonResponse<Boolean> response = new DaemonResponse<Boolean>(queriesService.addPublicationsToQuery(queryId, publicationsIds));
		return new ResponseEntity<DaemonResponse<Boolean>>(response, HttpStatus.OK);
	}

	/**
	 * Get all publications from a query
	 * 
	 * @param id
	 * @return
	 */
	@PreAuthorize("isAuthenticated() and hasPermission(#queryId, T(com.silicolife.anote2daemon.utils.ResourcesTypeUtils).queries.toString(), @permissions.getWritegrant())")
	@RequestMapping(value = "/updateRelevance", method = RequestMethod.PUT)
	public ResponseEntity<DaemonResponse<Boolean>> updateRelevance(@RequestParam Long queryId, @RequestParam Long publicationId, @RequestParam String relevance) {
		DaemonResponse<Boolean> response = new DaemonResponse<Boolean>(queriesService.updateRelevance(queryId, publicationId, relevance));
		return new ResponseEntity<DaemonResponse<Boolean>>(response, HttpStatus.OK);
	}

	/**
	 * Get Publication Id and relevance from a query
	 * 
	 * @param queryId
	 * @return
	 */
	@PreAuthorize("isAuthenticated() and hasPermission(#queryId, T(com.silicolife.anote2daemon.utils.ResourcesTypeUtils).queries.toString(), @permissions.getFullgrant())")
	@RequestMapping(value = "/getQueryPublicationsRelevance/{queryId}", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<Map<Long, RelevanceTypeEnum>>> getQueryPublicationsRelevance(@PathVariable Long queryId) {
		DaemonResponse<Map<Long, RelevanceTypeEnum>> response = new DaemonResponse<Map<Long, RelevanceTypeEnum>>(queriesService.getQueryPublicationsRelevance(queryId));
		return new ResponseEntity<DaemonResponse<Map<Long, RelevanceTypeEnum>>>(response, HttpStatus.OK);
	}
}
