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
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.exceptions.CorpusException;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.exceptions.PublicationManagerException;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.lucene.service.queries.IQueriesLuceneService;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.security.Permissions;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.service.queries.IQueriesService;
import com.silicolife.textmining.core.datastructures.documents.SearchPropertiesImpl;
import com.silicolife.textmining.core.datastructures.documents.query.QueryImpl;
import com.silicolife.textmining.core.interfaces.core.document.IDocumentSet;
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
	@Autowired
	private IQueriesLuceneService queriesLucineService;

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
	 * Counts All queries from a user
	 * 
	 * @return
	 */
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/countAllQueries", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<Integer>> countAllQueries() {
		DaemonResponse<Integer> response = new DaemonResponse<Integer>(queriesService.countAllQueries());
		return new ResponseEntity<DaemonResponse<Integer>>(response, HttpStatus.OK);
	}
	
	/**
	 * Counts All queries from a user
	 * 
	 * @return
	 */
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/countAllActiveQueries", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<Integer>> countAllActiveQueries() {
		DaemonResponse<Integer> response = new DaemonResponse<Integer>(queriesService.countAllActiveQueries());
		return new ResponseEntity<DaemonResponse<Integer>>(response, HttpStatus.OK);
	}
	
	
	/**
	 * 
	 * Get queries a user as permissions to paginated
	 * 
	 * @param paginationIndex
	 * @param paginationSize
	 * @param asc
	 * @param sort
	 * @return
	 */
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/getAllQueriesPaginated/{paginationIndex}/{paginationSize}/{asc}/{sortBy}", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<List<IQuery>>> getAllQueriesPaginated(@PathVariable Long paginationIndex, @PathVariable Long paginationSize, @PathVariable boolean asc, @PathVariable String sortBy) {
		DaemonResponse<List<IQuery>> response = new DaemonResponse<List<IQuery>>(queriesService.getAllQueriesPaginated(Integer.valueOf(paginationIndex.toString()), Integer.valueOf(paginationSize.toString()), asc, sortBy));
		return new ResponseEntity<DaemonResponse<List<IQuery>>>(response, HttpStatus.OK);
	}
	
	/**
	 * 
	 * get queries paginated from user logged. if the user has "role_admin" all
	 * queries are returned. If has another role only the "owner" queries are
	 * returned
	 * 
	 * @param paginationIndex
	 * @param paginationSize
	 * @param asc
	 * @param sort
	 * @return
	 */
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/getAllPrivilegesQueriesAdminAccessPaginated/{paginationIndex}/{paginationSize}/{asc}/{sortBy}", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<List<IQuery>>> getAllPrivilegesQueriesAdminAccessPaginated(@PathVariable Long paginationIndex, @PathVariable Long paginationSize, @PathVariable boolean asc, @PathVariable String sortBy) {
		DaemonResponse<List<IQuery>> response = new DaemonResponse<List<IQuery>>(queriesService.getAllPrivilegesQueriesAdminAccessPaginated(Integer.valueOf(paginationIndex.toString()), Integer.valueOf(paginationSize.toString()), asc, sortBy));
		return new ResponseEntity<DaemonResponse<List<IQuery>>>(response, HttpStatus.OK);
	}
	
	/**
	 * Counts All active queries if user has role admin otherwise 
	 * counts only the queries that he owns 
	 * 
	 * @return
	 */
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/countAllPrivilegesQueriesAdminAccess", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<Integer>> countAllPrivilegesQueriesAdminAccess() {
		DaemonResponse<Integer> response = new DaemonResponse<Integer>(queriesService.countAllPrivilegesQueriesAdminAccess());
		return new ResponseEntity<DaemonResponse<Integer>>(response, HttpStatus.OK);
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
	 * 
	 * Get query publications paginated
	 * 
	 * @param queryId
	 * @param paginationIndex
	 * @param paginationSize
	 * @return
	 * @throws PublicationManagerException
	 */
	@PreAuthorize("isAuthenticated() and hasPermission(#queryId,"
			+ "T(com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.utils.ResourcesTypeUtils).queries.getName(),"
			+ "@genericPairSpringSpel.getGenericPairSpringSpel(T(com.silicolife.anote2daemon.security.RestPermissionsEvaluatorEnum).default_,@permissions.getFullgrant()))")
	@RequestMapping(value = "/getQueryByIdPublicationsPaginated/{queryId}/{paginationIndex}/{paginationSize}/{asc}/{sortBy}", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<List<IPublication>>> getQueryPublicationsPaginated(@PathVariable Long queryId, @PathVariable Long paginationIndex, @PathVariable Long paginationSize, @PathVariable boolean asc, @PathVariable String sortBy) throws PublicationManagerException {
		DaemonResponse<List<IPublication>> response = new DaemonResponse<List<IPublication>>(queriesService.getQueryPublicationsPaginated(queryId, Integer.valueOf(paginationIndex.toString()), Integer.valueOf(paginationSize.toString()), asc, sortBy));
		return new ResponseEntity<DaemonResponse<List<IPublication>>>(response, HttpStatus.OK);
	}
	
	
	/**
	 * Count query publications
	 * 
	 * @param queryId
	 * @return
	 * @throws PublicationManagerException 
	 */
	
	@PreAuthorize("isAuthenticated() and hasPermission(#queryId,"
			+ "T(com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.utils.ResourcesTypeUtils).queries.getName(),"
			+ "@genericPairSpringSpel.getGenericPairSpringSpel(T(com.silicolife.anote2daemon.security.RestPermissionsEvaluatorEnum).default_,@permissions.getFullgrant()))")
	@RequestMapping(value = "/getQueryByIdPublicationsCount/{queryId}", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<Long>> getQueryPublicationsCount(@PathVariable Long queryId) throws PublicationManagerException {
		DaemonResponse<Long> response = new DaemonResponse<Long>(queriesService.getQueryPublicationsCount(queryId));
		return new ResponseEntity<DaemonResponse<Long>>(response, HttpStatus.OK);
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
		if (relevance.equals(RelevanceTypeEnum.none.toString())||relevance.isEmpty())
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
	
	//Lucine
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/getKeyWordsByWildCard/{subKeyword}", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<List<String>>> getKeywordsOfQueriesByWildCard( @PathVariable String subKeyword) {
		DaemonResponse<List<String>> response = new DaemonResponse<List<String>>(queriesLucineService.getKeywordsOfQueriesByWildCard(subKeyword));
		return new ResponseEntity<DaemonResponse<List<String>>>(response, HttpStatus.OK);	
	}
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/getQueriesBykeywords/{keywords}", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<List<IQuery>>> getQueriesBykeywords(@PathVariable String keywords) {
		DaemonResponse<List<IQuery>> response = new DaemonResponse<List<IQuery>>(queriesLucineService.getQueriesBykeywords(keywords));
		return new ResponseEntity<DaemonResponse<List<IQuery>>>(response, HttpStatus.OK);
	}
	
	
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/getQueriesBykeywordsPaginated/{keywords}/{paginationIndex}/{paginationSize}", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<List<IQuery>>> getQueriesBykeywordsPaginated(@PathVariable String keywords, @PathVariable int paginationIndex, @PathVariable int paginationSize) {
		DaemonResponse<List<IQuery>> response = new DaemonResponse<List<IQuery>>(queriesLucineService.getQueriesBykeywordsPaginated(keywords, paginationIndex,paginationSize ));
		return new ResponseEntity<DaemonResponse<List<IQuery>>>(response, HttpStatus.OK);
	}
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/getQueriesByOrganismPaginated/{organism}/{paginationIndex}/{paginationSize}", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<List<IQuery>>> getQueriesByOrganismPaginated(@PathVariable String organism, @PathVariable int paginationIndex, @PathVariable int paginationSize) {
		DaemonResponse<List<IQuery>> response = new DaemonResponse<List<IQuery>>(queriesLucineService.getQueriesByOrganismPaginated(organism, paginationIndex,paginationSize ));
		return new ResponseEntity<DaemonResponse<List<IQuery>>>(response, HttpStatus.OK);
	}
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/getQueriesFromSearchPaginated/{index}/{paginationSize}", method = RequestMethod.POST , consumes = { "application/json" })
	public ResponseEntity<DaemonResponse<List<IQuery>>> getQueriesFromSearchPaginated(@RequestBody SearchPropertiesImpl searchProperties, @PathVariable int index,@PathVariable int paginationSize)  {
		DaemonResponse<List<IQuery>> response = new DaemonResponse<List<IQuery>>(queriesLucineService.getQueriesFromSearchPaginated(searchProperties, index, paginationSize));
		return new ResponseEntity<DaemonResponse<List<IQuery>>>(response, HttpStatus.OK);
	}
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/getQueriesFromSearchPaginatedWAuth/{index}/{paginationSize}", method = RequestMethod.POST , consumes = { "application/json" })
	public ResponseEntity<DaemonResponse<List<IQuery>>> getQueriesFromSearchPaginatedWAuth(@RequestBody SearchPropertiesImpl searchProperties, @PathVariable int index,@PathVariable int paginationSize)  {
		DaemonResponse<List<IQuery>> response = new DaemonResponse<List<IQuery>>(queriesLucineService.getQueriesFromSearchPaginatedWAuth(searchProperties, index, paginationSize));
		return new ResponseEntity<DaemonResponse<List<IQuery>>>(response, HttpStatus.OK);
	}
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/getQueriesFromSearchPaginatedWAuthAndSort/{index}/{paginationSize}/{asc}/{sortBy}", method = RequestMethod.POST , consumes = { "application/json" })
	public ResponseEntity<DaemonResponse<List<IQuery>>> getQueriesFromSearchPaginatedWAuthAndSort(@RequestBody SearchPropertiesImpl searchProperties, @PathVariable int index,@PathVariable int paginationSize,@PathVariable boolean asc, @PathVariable String sortBy)  {
		DaemonResponse<List<IQuery>> response = new DaemonResponse<List<IQuery>>(queriesLucineService.getQueriesFromSearchPaginatedWAuthAndSort(searchProperties, index, paginationSize, asc, sortBy));
		return new ResponseEntity<DaemonResponse<List<IQuery>>>(response, HttpStatus.OK);
	}
	
	
	
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/countQueriesFromSearchWAuth", method = RequestMethod.POST , consumes = { "application/json" })
	public ResponseEntity<DaemonResponse<Integer>> countQueriesFromSearchWAuth(@RequestBody SearchPropertiesImpl searchProperties) {
		DaemonResponse<Integer> response = new DaemonResponse<Integer>(queriesLucineService.countQueriesFromSearchWAuth(searchProperties));
		return new ResponseEntity<DaemonResponse<Integer>>(response, HttpStatus.OK);
	}
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/countQueriesFromSearch", method = RequestMethod.POST , consumes = { "application/json" })
	public ResponseEntity<DaemonResponse<Integer>> countQueriesFromSearch(@RequestBody SearchPropertiesImpl searchProperties) {
		DaemonResponse<Integer> response = new DaemonResponse<Integer>(queriesLucineService.countQueriesFromSearch(searchProperties));
		return new ResponseEntity<DaemonResponse<Integer>>(response, HttpStatus.OK);
	}
	
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/countQueriesBykeywords/{keywords}", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<Integer>> countQueriesBykeywords(@PathVariable String keywords) {
		DaemonResponse<Integer> response = new DaemonResponse<Integer>(queriesLucineService.countQueriesBykeywords(keywords));
		return new ResponseEntity<DaemonResponse<Integer>>(response, HttpStatus.OK);
	}
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/countQueriesByOrganism/{organims}", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<Integer>> countQueriesByOrganism(@PathVariable String organism) {
		DaemonResponse<Integer> response = new DaemonResponse<Integer>(queriesLucineService.countQueriesByOrganism(organism));
		return new ResponseEntity<DaemonResponse<Integer>>(response, HttpStatus.OK);
	}
	
}
