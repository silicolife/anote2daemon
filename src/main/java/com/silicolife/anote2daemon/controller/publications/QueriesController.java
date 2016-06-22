package com.silicolife.anote2daemon.controller.publications;

import java.util.List;
import java.util.Map;
import java.util.Set;

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

import com.silicolife.anote2daemon.security.RestPermissionsEvaluatorEnum;
import com.silicolife.anote2daemon.utils.GenericPairSpringSpel;
import com.silicolife.anote2daemon.webservice.DaemonResponse;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.exceptions.PublicationManagerException;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.security.Permissions;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.service.queries.IQueriesService;
import com.silicolife.textmining.core.datastructures.documents.query.QueryImpl;
import com.silicolife.textmining.core.interfaces.core.document.IPublication;
import com.silicolife.textmining.core.interfaces.core.document.relevance.RelevanceTypeEnum;
import com.silicolife.textmining.core.interfaces.process.IR.IQuery;

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
	private GenericPairSpringSpel<RestPermissionsEvaluatorEnum, List<String>> genericPairSpringSpel;
	@Autowired
	private IQueriesService queriesService;
	

	/**
	 * Get All queries from a user
	 * 
	 * @return
	 */
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/getAllQueries", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<List<IQuery>>> getAllQueries() {
		DaemonResponse<List<IQuery>> response = new DaemonResponse<List<IQuery>>(queriesService.getAllQueries());
		return new ResponseEntity<DaemonResponse<List<IQuery>>>(response, HttpStatus.OK);
	}

	/**
	 * get all queries from user logged. if the user has "role_admin" all
	 * queries are returned. If has another role only the "owner" queries are
	 * returned
	 * 
	 * @param permission
	 * @return
	 */
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/getAllPrivilegesQueriesAccessAdmin", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<List<IQuery>>> getAllPrivilegesQueriesAccessAdmin() {
		DaemonResponse<List<IQuery>> response = new DaemonResponse<List<IQuery>>(queriesService.getAllPrivilegesQueriesAdminAccess());
		return new ResponseEntity<DaemonResponse<List<IQuery>>>(response, HttpStatus.OK);
	}

	/**
	 * Get query by id
	 * 
	 * @param id
	 * @return
	 */
	@PreAuthorize("isAuthenticated() and hasPermission(#queryId, "
			+ "T(com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.utils.ResourcesTypeUtils).queries.getName(),"
			+ "@genericPairSpringSpel.getGenericPairSpringSpel(T(com.silicolife.anote2daemon.security.RestPermissionsEvaluatorEnum).default_,@permissions.getFullgrant()))")
	@RequestMapping(value = "/getQueryById/{queryId}", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<IQuery>> getQueryById(@PathVariable Long queryId) {
		DaemonResponse<IQuery> response = new DaemonResponse<IQuery>(queriesService.getById(queryId));
		return new ResponseEntity<DaemonResponse<IQuery>>(response, HttpStatus.OK);
	}

	/**
	 * Get all publications from a query
	 * 
	 * @param id
	 * @return
	 * @throws PublicationManagerException 
	 */
	@PreAuthorize("isAuthenticated() and hasPermission(#queryId,"
			+ "T(com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.utils.ResourcesTypeUtils).queries.getName(),"
			+ "@genericPairSpringSpel.getGenericPairSpringSpel(T(com.silicolife.anote2daemon.security.RestPermissionsEvaluatorEnum).default_,@permissions.getFullgrant()))")
	@RequestMapping(value = "/getAllPublications/{queryId}", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<List<IPublication>>> getAllPublications(@PathVariable Long queryId) throws PublicationManagerException {
		DaemonResponse<List<IPublication>> response = new DaemonResponse<List<IPublication>>(queriesService.getQueryPublications(queryId));
		return new ResponseEntity<DaemonResponse<List<IPublication>>>(response, HttpStatus.OK);
	}

	/**
	 * Inactive / remove a query
	 * 
	 * @param id
	 * @return
	 */
	@PreAuthorize("isAuthenticated() and hasPermission(#queryId, "
			+ "T(com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.utils.ResourcesTypeUtils).queries.getName(),"
			+ "@genericPairSpringSpel.getGenericPairSpringSpel(T(com.silicolife.anote2daemon.security.RestPermissionsEvaluatorEnum).default_,@permissions.getFullgrant()))")
	@RequestMapping(value = "/inactiveQuery", method = RequestMethod.POST)
	public ResponseEntity<DaemonResponse<Boolean>> inactiveQuery(@RequestParam Long queryId) {
		DaemonResponse<Boolean> response = new DaemonResponse<Boolean>(queriesService.inactiveQuery(queryId));
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
	 * @throws PublicationManagerException 
	 */
	@PreAuthorize("isAuthenticated() and hasPermission(#query.getId(),"
			+ "T(com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.utils.ResourcesTypeUtils).queries.getName(),"
			+ "@genericPairSpringSpel.getGenericPairSpringSpel(T(com.silicolife.anote2daemon.security.RestPermissionsEvaluatorEnum).default_,@permissions.getWritegrant()))")
	@RequestMapping(value = "/updateQuery", method = RequestMethod.PUT, consumes = { "application/json" })
	public ResponseEntity<DaemonResponse<Boolean>> updateQuery(@RequestBody QueryImpl query) throws PublicationManagerException {
		DaemonResponse<Boolean> response = new DaemonResponse<Boolean>(queriesService.update(query));
		return new ResponseEntity<DaemonResponse<Boolean>>(response, HttpStatus.OK);
	}

	/**
	 * Associate a list of publications to a query
	 * 
	 * @param id
	 * @param request
	 * @return
	 * @throws PublicationManagerException 
	 */
	@PreAuthorize("isAuthenticated() and hasPermission(#queryId, "
			+ "T(com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.utils.ResourcesTypeUtils).queries.getName(),"
			+ "@genericPairSpringSpel.getGenericPairSpringSpel(T(com.silicolife.anote2daemon.security.RestPermissionsEvaluatorEnum).default_,@permissions.getWritegrant()))")
	@RequestMapping(value = "/addPublicationsToQuery/{queryId}", method = RequestMethod.POST, consumes = { "application/json" })
	public ResponseEntity<DaemonResponse<Boolean>> addPublicationsToQuery(@PathVariable Long queryId, @RequestBody Set<Long> publicationsIds) throws PublicationManagerException {
		DaemonResponse<Boolean> response = new DaemonResponse<Boolean>(queriesService.addPublicationsToQuery(queryId, publicationsIds));
		return new ResponseEntity<DaemonResponse<Boolean>>(response, HttpStatus.OK);
	}

	/**
	 * Get all publications from a query
	 * 
	 * @param id
	 * @return
	 */
	@PreAuthorize("isAuthenticated() and hasPermission(#queryId, "
			+ "T(com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.utils.ResourcesTypeUtils).queries.getName(),"
			+ "@genericPairSpringSpel.getGenericPairSpringSpel(T(com.silicolife.anote2daemon.security.RestPermissionsEvaluatorEnum).default_,@permissions.getWritegrant()))")
	@RequestMapping(value = "/updateRelevance", method = RequestMethod.POST)
	public ResponseEntity<DaemonResponse<Boolean>> updateRelevance(@RequestParam Long queryId, @RequestParam Long publicationId, @RequestParam String relevance) {
		if (relevance.equals(RelevanceTypeEnum.none.toString()))
			relevance = null;
		DaemonResponse<Boolean> response = new DaemonResponse<Boolean>(queriesService.updateRelevance(queryId, publicationId, relevance));
		return new ResponseEntity<DaemonResponse<Boolean>>(response, HttpStatus.OK);
	}

	/**
	 * Get Publication Id and relevance from a query
	 * 
	 * @param queryId
	 * @return
	 * @throws PublicationManagerException 
	 */
	@PreAuthorize("isAuthenticated() and hasPermission(#queryId, "
			+ "T(com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.utils.ResourcesTypeUtils).queries.getName(),"
			+ "@genericPairSpringSpel.getGenericPairSpringSpel(T(com.silicolife.anote2daemon.security.RestPermissionsEvaluatorEnum).default_,@permissions.getFullgrant()))")
	@RequestMapping(value = "/getQueryPublicationsRelevance/{queryId}", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<Map<Long, RelevanceTypeEnum>>> getQueryPublicationsRelevance(@PathVariable Long queryId) throws PublicationManagerException {
		DaemonResponse<Map<Long, RelevanceTypeEnum>> response = new DaemonResponse<Map<Long, RelevanceTypeEnum>>(queriesService.getQueryPublicationsRelevance(queryId));
		return new ResponseEntity<DaemonResponse<Map<Long, RelevanceTypeEnum>>>(response, HttpStatus.OK);
	}

	/**
	 * Connect to daemon and get publication full text
	 * 
	 * @param queryId
	 * @param source
	 * @return
	 */
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/getAllQueryPublicationsExternalIdFromSource", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<Set<String>>> getQueryPublicationsRelevance(@RequestParam Long queryId, @RequestParam String source) {
		DaemonResponse<Set<String>> response = new DaemonResponse<Set<String>>(queriesService.getQueryPublicationsExternalIDFromSource(queryId, source));
		return new ResponseEntity<DaemonResponse<Set<String>>>(response, HttpStatus.OK);
	}
}
