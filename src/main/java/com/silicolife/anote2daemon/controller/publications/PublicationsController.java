package com.silicolife.anote2daemon.controller.publications;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
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

import com.silicolife.anote2daemon.webservice.DaemonResponse;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.exceptions.PublicationManagerException;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.lucene.service.publications.IPublicationsLuceneService;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.security.Permissions;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.service.annotation.IAnnotationService;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.service.publications.IPublicationsService;
import com.silicolife.textmining.core.datastructures.documents.PublicationImpl;
import com.silicolife.textmining.core.datastructures.documents.SearchPropertiesImpl;
import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;
import com.silicolife.textmining.core.interfaces.core.document.IPublication;
import com.silicolife.textmining.core.interfaces.core.document.labels.IPublicationLabel;

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
	private IPublicationsService publicationService;
	@Autowired
	private IPublicationsLuceneService publicationsLuceneService;
	@Autowired
	private IAnnotationService annotationService;

	/**
	 * Get all Publication labels from system
	 * 
	 * @return
	 */
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/getAllPublicationsLabels", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<List<IPublicationLabel>>> getAllPublicationsLabels() {
		DaemonResponse<List<IPublicationLabel>> response = new DaemonResponse<List<IPublicationLabel>>(publicationService.getAllPublicationLabels());
		return new ResponseEntity<DaemonResponse<List<IPublicationLabel>>>(response, HttpStatus.OK);
	}

	/**
	 * Get fulltext from publication
	 * 
	 * @param id
	 * @return
	 * @throws ANoteException 
	 */
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/getFullText/{id}", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<String>> getFullText(@PathVariable Long id) throws ANoteException {
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
	 * Get PublicationId by sourceType and sourceInternalId
	 * 
	 * @param source
	 * @param sourceId
	 * @return
	 */
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/getPublicationIdBySourceId", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<Long>> getPublicationIdFromSourceId(@RequestParam String source, @RequestParam String sourceId) {
		DaemonResponse<Long> response = new DaemonResponse<Long>(publicationService.getPublicationIdFromSourceId(source, sourceId));
		return new ResponseEntity<DaemonResponse<Long>>(response, HttpStatus.OK);
	}
	
	/**
	 * 
	 * Get PublicationId by sourceType and sourceInternalId
	 * 
	 * @param source
	 * @param sourceId
	 * @return
	 */
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/getPublicationFromSourceId", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<IPublication>> getPublicationFromSourceId(@RequestParam String source, @RequestParam String sourceId) {
		DaemonResponse<IPublication> response = new DaemonResponse<IPublication>(publicationService.getPublicationFromSourceId(source, sourceId));
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
	@Caching(
			evict = {
			@CacheEvict("countAllPublications"),
			@CacheEvict(value = "countAllDistinctColumnValuesFromPublications", allEntries=true)
			}
	)
	@RequestMapping(value = "/createPublications", method = RequestMethod.POST, consumes = { "application/json" })
	public ResponseEntity<DaemonResponse<Boolean>> createMultiplePublications(@RequestBody Set<PublicationImpl> publications) {
		Set<IPublication> publicationsList = new HashSet<>();
		for (PublicationImpl pub : publications)
			publicationsList.add(pub);

		Boolean a = publicationService.create(publicationsList);
		
		DaemonResponse<Boolean> response = new DaemonResponse<Boolean>(a);
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
	@CacheEvict(value = "countAllDistinctColumnValuesFromPublications", allEntries=true)
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
	@RequestMapping(value = "/getAllPublicationsExternalIdFromSource", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<Map<String, Long>>> getAllPublicationsFromSource(@RequestParam String source) {
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
	@RequestMapping(value = "/updatePublicationAvailableFreeFullText", method = RequestMethod.POST)
	public ResponseEntity<DaemonResponse<Boolean>> updatePublicationAvailableFreeFullText(@RequestParam Long publicationId, @RequestParam Boolean isAvailable) throws PublicationManagerException {
		DaemonResponse<Boolean> response = new DaemonResponse<Boolean>(publicationService.updatePublicationAvailableFreeFullText(publicationId, isAvailable));
		return new ResponseEntity<DaemonResponse<Boolean>>(response, HttpStatus.OK);
	}
	
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/getAllPublicationsPaginated/{index}/{paginationSize}/{asc}/{sortBy}", method = RequestMethod.GET )
	public ResponseEntity<DaemonResponse<List<IPublication>>> getAllPublicationsPaginated(@PathVariable int index,@PathVariable int paginationSize, @PathVariable boolean asc,@PathVariable String sortBy)  {
		DaemonResponse<List<IPublication>> response = new DaemonResponse<List<IPublication>>(publicationService.getAllPublicationsPaginated( index, paginationSize, asc, sortBy));
		return new ResponseEntity<DaemonResponse<List<IPublication>>>(response, HttpStatus.OK);
	}
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/findAllDistinctColumnValuesFromPublicationsPaginated/{column}/{index}/{paginationSize}/{asc}/{sortBy}", method = RequestMethod.GET )
	public ResponseEntity<DaemonResponse<List<String>>> findAllDistinctColumnValuesFromPublicationsPaginated(@PathVariable String column,@PathVariable int index,@PathVariable int paginationSize, @PathVariable boolean asc,@PathVariable String sortBy)  {
		DaemonResponse<List<String>> response = new DaemonResponse<List<String>>(publicationService.findAllDistinctColumnValuesFromPublicationsPaginated(column, index, paginationSize, asc, sortBy));
		return new ResponseEntity<DaemonResponse<List<String>>>(response, HttpStatus.OK);
	}
	
	@PreAuthorize("isAuthenticated()")
	@Cacheable("countAllDistinctColumnValuesFromPublications")
	@RequestMapping(value = "/countAllDistinctColumnValuesFromPublications/{column}", method = RequestMethod.GET )
	public ResponseEntity<DaemonResponse<Integer>> countAllDistinctColumnValuesFromPublications(@PathVariable String column) {
		DaemonResponse<Integer> response = new DaemonResponse<Integer>(publicationService.countAllDistinctColumnValuesFromPublications(column));
		return new ResponseEntity<DaemonResponse<Integer>>(response, HttpStatus.OK);
	}
	
	
	@PreAuthorize("isAuthenticated()")
	@Cacheable("countAllPublications")
	@RequestMapping(value = "/countAllPublications", method = RequestMethod.GET )
	public ResponseEntity<DaemonResponse<Integer>> countAllPublications() {
		DaemonResponse<Integer> response = new DaemonResponse<Integer>(publicationService.countAllPublications());
		return new ResponseEntity<DaemonResponse<Integer>>(response, HttpStatus.OK);
	}
	
	@PreAuthorize("isAuthenticated()")
	@Caching(
			evict = {
			@CacheEvict("countAllPublications"),
			@CacheEvict(value = "countAllDistinctColumnValuesFromPublications", allEntries=true)
			}
	)
	@RequestMapping(value = "/testCache", method = RequestMethod.GET )
	public ResponseEntity<DaemonResponse<Integer>> testCache() {
		ResponseEntity<DaemonResponse<Integer>> res = this.updateCountCache();
		//DaemonResponse<Integer> response = new DaemonResponse<Integer>(100);
		return res;
	}
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/getPublicationTypesPaginated/{index}/{paginationSize}", method = RequestMethod.GET )
	public ResponseEntity<DaemonResponse<List<String>>> getPublicationsTypes(@PathVariable int index,@PathVariable int paginationSize) {
		DaemonResponse<List<String>> response = new DaemonResponse<List<String>>(publicationService.getPublicationTypesPaginated(index, paginationSize));
		return new ResponseEntity<DaemonResponse<List<String>>>(response, HttpStatus.OK);
	}
	

	@RequestMapping(value = "/countPublicationTypes", method = RequestMethod.GET )
	public ResponseEntity<DaemonResponse<Integer>> countPublicationTypes() {
		DaemonResponse<Integer> response = new DaemonResponse<Integer>(publicationService.countPublicationTypes());
		return new ResponseEntity<DaemonResponse<Integer>>(response, HttpStatus.OK);
	}
	
	public ResponseEntity<DaemonResponse<Integer>> updateCountCache(){
		DaemonResponse<Integer> response = new DaemonResponse<Integer>(100);
		return new ResponseEntity<DaemonResponse<Integer>>(response, HttpStatus.OK);
	}
	
	/**
	 * Update publication full text content
	 * 
	 * @param publicationId
	 * @param fullText
	 * @return
	 * @throws PublicationManagerException
	 */
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/updatePublicationFullTextContent", method = RequestMethod.POST)
	public ResponseEntity<DaemonResponse<Boolean>> updatePublicationFullTextContent(@RequestParam Long publicationId, @RequestParam String fullText) throws PublicationManagerException {
		DaemonResponse<Boolean> response = new DaemonResponse<Boolean>(publicationService.updatePublicationAFullTextContent(publicationId, fullText));
		return new ResponseEntity<DaemonResponse<Boolean>>(response, HttpStatus.OK);
	}
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/getPublicationsFromResourcesQuery", method = RequestMethod.POST) 
	public ResponseEntity<DaemonResponse<List<IPublication>>> getPublicationsFromResourcesQuery(@RequestParam String query)  {
		DaemonResponse<List<IPublication>> response = new DaemonResponse<List<IPublication>>(publicationService.getPublicationsFromResourcesQuery(query, annotationService));
		return new ResponseEntity<DaemonResponse<List<IPublication>>>(response, HttpStatus.OK);
	}
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/getPublicationsFromSearchPaginated/{index}/{paginationSize}", method = RequestMethod.POST , consumes = { "application/json" })
	public ResponseEntity<DaemonResponse<List<IPublication>>> getPublicationsFromSearchPaginated(@RequestBody SearchPropertiesImpl searchProperties, @PathVariable int index,@PathVariable int paginationSize)  {
		DaemonResponse<List<IPublication>> response = new DaemonResponse<List<IPublication>>(publicationsLuceneService.getPublicationsFromSearchPaginated(searchProperties, index, paginationSize));
		return new ResponseEntity<DaemonResponse<List<IPublication>>>(response, HttpStatus.OK);
	}
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/getPublicationsFromSearchPaginatedWSort/{index}/{paginationSize}/{asc}/{sortBy}", method = RequestMethod.POST , consumes = { "application/json" })
	public ResponseEntity<DaemonResponse<List<IPublication>>> getPublicationsFromSearchPaginatedWSort(@RequestBody SearchPropertiesImpl searchProperties, @PathVariable int index,@PathVariable int paginationSize, @PathVariable boolean asc,@PathVariable String sortBy)  {
		DaemonResponse<List<IPublication>> response = new DaemonResponse<List<IPublication>>(publicationsLuceneService.getPublicationsFromSearchPaginatedWSort(searchProperties, index, paginationSize, asc, sortBy));
		return new ResponseEntity<DaemonResponse<List<IPublication>>>(response, HttpStatus.OK);
	}

	
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/countGetPublicationsFromSearch", method = RequestMethod.POST , consumes = { "application/json" })
	public ResponseEntity<DaemonResponse<Integer>> countGetPublicationsFromSearch(@RequestBody SearchPropertiesImpl searchProperties) {
		DaemonResponse<Integer> response = new DaemonResponse<Integer>(publicationsLuceneService.countGetPublicationsFromSearch(searchProperties));
		return new ResponseEntity<DaemonResponse<Integer>>(response, HttpStatus.OK);
	}
	
	
	
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/getPublicationsFromSearch", method = RequestMethod.POST , consumes = { "application/json" })
	public ResponseEntity<DaemonResponse<List<IPublication>>> getPublicationsFromSearch(@RequestBody SearchPropertiesImpl searchProperties)  {
		DaemonResponse<List<IPublication>> response = new DaemonResponse<List<IPublication>>(publicationsLuceneService.getPublicationsFromSearch(searchProperties));
		return new ResponseEntity<DaemonResponse<List<IPublication>>>(response, HttpStatus.OK);
	}
	
	
}
