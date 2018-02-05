package com.silicolife.anote2daemon.controller.corpora;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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
import com.silicolife.textmining.core.datastructures.corpora.CorpusImpl;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.exceptions.CorpusException;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.lucene.service.corpus.ICorpusLuceneService;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.security.Permissions;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.service.corpora.ICorpusService;
import com.silicolife.textmining.core.datastructures.documents.SearchPropertiesImpl;
import com.silicolife.textmining.core.interfaces.core.document.IDocumentSet;
import com.silicolife.textmining.core.interfaces.core.document.corpus.ICorpus;
import com.silicolife.textmining.core.interfaces.core.document.corpus.ICorpusStatistics;
import com.silicolife.textmining.core.interfaces.process.IE.IIEProcess;
//import com.silicolife.textmining.core.interfaces.process.IR.ICorpus;

/**
 * The goal of this class is to expose for the web all Corpus functionalities of
 * anote2daemon. It is necessary a user logged to access these methods
 * 
 * @author Joel Azevedo Costa
 * @year 2015
 *
 */
@RequestMapping(value = "/corpus", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
@ResponseBody
@Controller
public class CorpusController {

	@Autowired
	private Permissions permissions;
	@Autowired
	private GenericPairSpringSpel<RestPermissionsEvaluatorEnum, List<String>> genericPairSpringSpel;
	@Autowired
	private ICorpusService corpusService;
	@Autowired
	private ICorpusLuceneService corpusluceneService;

	/**
	 * Get all corpus
	 * 
	 * @return
	 */
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/getAllCorpus", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<List<ICorpus>>> getAllCorpus() {
		DaemonResponse<List<ICorpus>> response = new DaemonResponse<List<ICorpus>>(corpusService.getAllCorpus());
		return new ResponseEntity<DaemonResponse<List<ICorpus>>>(response, HttpStatus.OK);
	}
	
	/**
	 * 
	 * Get all corpus from a user paginated
	 * 
	 * @param paginationIndex
	 * @param paginationSize
	 * @param asc
	 * @param sort
	 * @return
	 */
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/getAllCorpusPaginated/{paginationIndex}/{paginationSize}/{asc}/{sortBy}", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<List<ICorpus>>> getAllCorpusPaginated(@PathVariable Long paginationIndex, @PathVariable Long paginationSize, @PathVariable boolean asc, @PathVariable String sortBy) {
		DaemonResponse<List<ICorpus>> response = new DaemonResponse<List<ICorpus>>(corpusService.getAllCorpusPaginated(Integer.valueOf(paginationIndex.toString()), Integer.valueOf(paginationSize.toString()), asc, sortBy));
		return new ResponseEntity<DaemonResponse<List<ICorpus>>>(response, HttpStatus.OK);
	}
	
	/**
	 * 
	 * Get all owner or admin paginated
	 * 
	 * @param paginationIndex
	 * @param paginationSize
	 * @param asc
	 * @param sort
	 * @return
	 */
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/getAllPrivilegesCorpusAdminAccessPaginated/{paginationIndex}/{paginationSize}/{asc}/{sortBy}", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<List<ICorpus>>> getAllPrivilegesCorpusAdminAccessPaginated(@PathVariable Long paginationIndex, @PathVariable Long paginationSize, @PathVariable boolean asc, @PathVariable String sortBy) {
		DaemonResponse<List<ICorpus>> response = new DaemonResponse<List<ICorpus>>(corpusService.getAllPrivilegesCorpusAdminAccessPaginated(Integer.valueOf(paginationIndex.toString()), Integer.valueOf(paginationSize.toString()), asc, sortBy));
		return new ResponseEntity<DaemonResponse<List<ICorpus>>>(response, HttpStatus.OK);
	}
	
	
	/**
	 * Count all owner or admin 
	 * 
	 * @return
	 */
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/countAllPrivilegesCorpusAdminAccess", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<Integer>> countAllPrivilegesCorpusAdminAccess() {
		DaemonResponse<Integer> response = new DaemonResponse<Integer>(corpusService.countAllPrivilegesCorpusAdminAccess());
		return new ResponseEntity<DaemonResponse<Integer>>(response, HttpStatus.OK);
	}
	

	/**
	 * Count all corpus
	 * 
	 * @return
	 */
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/countAllCorpus", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<Integer>> countAllCorpus() {
		DaemonResponse<Integer> response = new DaemonResponse<Integer>(corpusService.countAllCorpus());
		return new ResponseEntity<DaemonResponse<Integer>>(response, HttpStatus.OK);
	}

	/**
	 * Get corpus by Id
	 * 
	 * @param id
	 * @return
	 */
	@PreAuthorize("isAuthenticated() and hasPermission(#id, "
			+ "T(com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.utils.ResourcesTypeUtils).corpus.getName(),"
			+ "@genericPairSpringSpel.getGenericPairSpringSpel(T(com.silicolife.anote2daemon.security.RestPermissionsEvaluatorEnum).default_,@permissions.getFullgrant()))")
	@RequestMapping(value = "/getCorpusById/{id}", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<ICorpus>> getCorpusById(@PathVariable Long id) {
		DaemonResponse<ICorpus> response = new DaemonResponse<ICorpus>(corpusService.getCorpusByID(id));
		return new ResponseEntity<DaemonResponse<ICorpus>>(response, HttpStatus.OK);
	}

	/**
	 * Create a corpus
	 * 
	 * @param corpus
	 * @return
	 */
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/createCorpus", method = RequestMethod.POST, consumes = { "application/json" })
	public ResponseEntity<DaemonResponse<Boolean>> createCorpus(@RequestBody CorpusImpl corpus) {
		DaemonResponse<Boolean> response = new DaemonResponse<Boolean>(corpusService.createCorpus(corpus));
		return new ResponseEntity<DaemonResponse<Boolean>>(response, HttpStatus.OK);
	}

	/**
	 * Update corpus
	 * 
	 * @param id
	 * @return
	 * @throws CorpusException 
	 */
	@PreAuthorize("isAuthenticated() and hasPermission(#corpus.getId(),"
			+ "T(com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.utils.ResourcesTypeUtils).corpus.getName(),"
			+ "@genericPairSpringSpel.getGenericPairSpringSpel(T(com.silicolife.anote2daemon.security.RestPermissionsEvaluatorEnum).default_,@permissions.getWritegrant()))")
	@CacheEvict(value = "corpusStatistics", key="#corpus.id")
	@RequestMapping(value = "/updateCorpus", method = RequestMethod.PUT, consumes = { "application/json" })
	public ResponseEntity<DaemonResponse<Boolean>> updateCorpus(@RequestBody CorpusImpl corpus) throws CorpusException {
		DaemonResponse<Boolean> response = new DaemonResponse<Boolean>(corpusService.updateCorpus(corpus));
		return new ResponseEntity<DaemonResponse<Boolean>>(response, HttpStatus.OK);
	}

	/**
	 * get processes from a corpus
	 * 
	 * @param id
	 * @return
	 * @throws CorpusException 
	 */
	@PreAuthorize("isAuthenticated() and hasPermission(#id,"
			+ "T(com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.utils.ResourcesTypeUtils).corpus.getName(),"
			+ "@genericPairSpringSpel.getGenericPairSpringSpel(T(com.silicolife.anote2daemon.security.RestPermissionsEvaluatorEnum).default_,@permissions.getFullgrant()))")
	@RequestMapping(value = "/getCorpusPublications/{id}", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<IDocumentSet>> getCorpusPublications(@PathVariable Long id) throws CorpusException {
		DaemonResponse<IDocumentSet> response = new DaemonResponse<IDocumentSet>(corpusService.getCorpusPublications(id));
		return new ResponseEntity<DaemonResponse<IDocumentSet>>(response, HttpStatus.OK);
	}
	
	/**
	 * Count corpus publications
	 * 
	 * @param id
	 * @return
	 * @throws CorpusException 
	 */
	@PreAuthorize("isAuthenticated() and hasPermission(#id,"
			+ "T(com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.utils.ResourcesTypeUtils).corpus.getName(),"
			+ "@genericPairSpringSpel.getGenericPairSpringSpel(T(com.silicolife.anote2daemon.security.RestPermissionsEvaluatorEnum).default_,@permissions.getFullgrant()))")
	@RequestMapping(value = "/getCorpusPublicationsCount/{id}", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<Long>> getCorpusPublicationsCount(@PathVariable Long id) throws CorpusException {
		DaemonResponse<Long> response = new DaemonResponse<Long>(corpusService.getCorpusPublicationsCount(id));
		return new ResponseEntity<DaemonResponse<Long>>(response, HttpStatus.OK);
	}
	
	/**
	 * getCorpusPublicationsPaginated
	 * 
	 * @param id
	 * @return
	 * @throws CorpusException 
	 */
	@PreAuthorize("isAuthenticated() and hasPermission(#corpusId,"
			+ "T(com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.utils.ResourcesTypeUtils).corpus.getName(),"
			+ "@genericPairSpringSpel.getGenericPairSpringSpel(T(com.silicolife.anote2daemon.security.RestPermissionsEvaluatorEnum).default_,@permissions.getFullgrant()))")
	@RequestMapping(value = "/getCorpusPublicationsPaginated/{corpusId}/{paginationIndex}/{paginationSize}", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<IDocumentSet>> getCorpusPublicationsPaginated(@PathVariable Long corpusId, @PathVariable Long paginationIndex, @PathVariable Long paginationSize) throws CorpusException {
		DaemonResponse<IDocumentSet> response = new DaemonResponse<IDocumentSet>(corpusService.getCorpusPublicationsPaginated(corpusId, Integer.valueOf(paginationIndex.toString()), Integer.valueOf(paginationSize.toString())));
		return new ResponseEntity<DaemonResponse<IDocumentSet>>(response, HttpStatus.OK);
	}
	
	
	/**
	 * getCorpusPublicationsPaginatedWSort
	 * 
	 * @param id
	 * @return
	 * @throws CorpusException 
	 */
	@PreAuthorize("isAuthenticated() and hasPermission(#corpusId,"
			+ "T(com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.utils.ResourcesTypeUtils).corpus.getName(),"
			+ "@genericPairSpringSpel.getGenericPairSpringSpel(T(com.silicolife.anote2daemon.security.RestPermissionsEvaluatorEnum).default_,@permissions.getFullgrant()))")
	@RequestMapping(value = "/getCorpusPublicationsPaginatedWSort/{corpusId}/{paginationIndex}/{paginationSize}/{asc}/{sortBy}", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<IDocumentSet>> getCorpusPublicationsPaginatedWSort(@PathVariable Long corpusId, @PathVariable Long paginationIndex, @PathVariable Long paginationSize, @PathVariable boolean asc, @PathVariable String sortBy) throws CorpusException {
		DaemonResponse<IDocumentSet> response = new DaemonResponse<IDocumentSet>(corpusService.getCorpusPublicationsPaginatedWSort(corpusId, Integer.valueOf(paginationIndex.toString()), Integer.valueOf(paginationSize.toString()), asc, sortBy));
		return new ResponseEntity<DaemonResponse<IDocumentSet>>(response, HttpStatus.OK);
	}
	
	/**
	 * 
	 * Get corpus publications not processed
	 * 
	 * @param corpusId
	 * @param processId
	 * @param paginationIndex
	 * @param paginationSize
	 * @return
	 * @throws CorpusException
	 */
	@PreAuthorize("isAuthenticated()"
			+ " and hasPermission(#corpusId,"
			+ "T(com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.utils.ResourcesTypeUtils).corpus.getName(),"
			+ "@genericPairSpringSpel.getGenericPairSpringSpel(T(com.silicolife.anote2daemon.security.RestPermissionsEvaluatorEnum).default_,@permissions.getFullgrant()))"
			+ " and hasPermission(#processId, "
			+ "T(com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.utils.ResourcesTypeUtils).processes.getName(),"
			+ "@genericPairSpringSpel.getGenericPairSpringSpel(T(com.silicolife.anote2daemon.security.RestPermissionsEvaluatorEnum).default_,@permissions.getFullgrant()))")
	@RequestMapping(value = "/getCorpusPublicationsNotProcessedPaginated/{corpusId}/{processId}/{paginationIndex}/{paginationSize}", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<IDocumentSet>> getCorpusPublicationsNotProcessedPaginated(@PathVariable Long corpusId, @PathVariable Long processId, @PathVariable Long paginationIndex, @PathVariable Long paginationSize) throws CorpusException {
		DaemonResponse<IDocumentSet> response = new DaemonResponse<IDocumentSet>(corpusService.getCorpusPublicationsNotProcessedPaginated(corpusId, processId, Integer.valueOf(paginationIndex.toString()), Integer.valueOf(paginationSize.toString())));
		return new ResponseEntity<DaemonResponse<IDocumentSet>>(response, HttpStatus.OK);
	}
	
	/**
	 * Count corpus publications not Processed
	 * 
	 * @param id
	 * @return
	 * @throws CorpusException 
	 */
	@PreAuthorize("isAuthenticated() and hasPermission(#corpusId,"
			+ "T(com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.utils.ResourcesTypeUtils).corpus.getName(),"
			+ "@genericPairSpringSpel.getGenericPairSpringSpel(T(com.silicolife.anote2daemon.security.RestPermissionsEvaluatorEnum).default_,@permissions.getFullgrant()))")
	@RequestMapping(value = "/countCorpusPublicationsNotProcessed/{corpusId}/{processId}", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<Long>> countCorpusPublicationsNotProcessed(@PathVariable Long corpusId, @PathVariable Long processId) throws CorpusException {
		DaemonResponse<Long> response = new DaemonResponse<Long>(corpusService.countCorpusPublicationsNotProcessed(corpusId, processId));
		return new ResponseEntity<DaemonResponse<Long>>(response, HttpStatus.OK);
	}
	
	/**
	 * get processes from a corpus
	 * 
	 * @param id
	 * @return
	 * @throws CorpusException 
	 */	
	@PreAuthorize("isAuthenticated() and hasPermission(#corpusId,"
			+ "T(com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.utils.ResourcesTypeUtils).corpus.getName(),"
			+ "@genericPairSpringSpel.getGenericPairSpringSpel(T(com.silicolife.anote2daemon.security.RestPermissionsEvaluatorEnum).default_,@permissions.getFullgrant()))")
	@RequestMapping(value = "/getCorpusProcesses/{corpusId}", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<List<IIEProcess>>> getCorpusProcesses(@PathVariable Long corpusId) throws CorpusException{
		DaemonResponse<List<IIEProcess>> response = new DaemonResponse<List<IIEProcess>>(corpusService.getCorpusProcesses(corpusId));
		return new ResponseEntity<DaemonResponse<List<IIEProcess>>>(response, HttpStatus.OK);
	}
	
	/**
	 * get number of processes from a corpus
	 * 
	 * @param id
	 * @return
	 * @throws CorpusException 
	 */	
	@PreAuthorize("isAuthenticated() and hasPermission(#corpusId,"
			+ "T(com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.utils.ResourcesTypeUtils).corpus.getName(),"
			+ "@genericPairSpringSpel.getGenericPairSpringSpel(T(com.silicolife.anote2daemon.security.RestPermissionsEvaluatorEnum).default_,@permissions.getFullgrant()))")
	@RequestMapping(value = "/countCorpusProcesses/{corpusId}", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<Integer>> countCorpusProcesses(@PathVariable Long corpusId) throws CorpusException{
		DaemonResponse<Integer> response = new DaemonResponse<Integer>(corpusService.countCorpusProcesses(corpusId));
		return new ResponseEntity<DaemonResponse<Integer>>(response, HttpStatus.OK);
	}
	
	
	/**
	 * get processes from a corpus paginated
	 * 
	 * @param id
	 * @return
	 * @throws CorpusException 
	 */	
	@PreAuthorize("isAuthenticated() and hasPermission(#corpusId,"
			+ "T(com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.utils.ResourcesTypeUtils).corpus.getName(),"
			+ "@genericPairSpringSpel.getGenericPairSpringSpel(T(com.silicolife.anote2daemon.security.RestPermissionsEvaluatorEnum).default_,@permissions.getFullgrant()))")
	@RequestMapping(value = "/getCorpusProcessesPaginated/{corpusId}/{paginationIndex}/{paginationSize}/{asc}/{sortBy}", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<List<IIEProcess>>> getCorpusProcessesPaginated(@PathVariable Long corpusId , @PathVariable Long paginationIndex, @PathVariable Long paginationSize, @PathVariable boolean asc, @PathVariable String sortBy) throws CorpusException{
		DaemonResponse<List<IIEProcess>> response = new DaemonResponse<List<IIEProcess>>(corpusService.getCorpusProcessesPaginated(corpusId, Integer.valueOf(paginationIndex.toString()), Integer.valueOf(paginationSize.toString()), asc, sortBy));
		return new ResponseEntity<DaemonResponse<List<IIEProcess>>>(response, HttpStatus.OK);
	}
	
	/**
	 * 
	 * Get publications from corpus with a specific source.
	 * 
	 * @param corpusId
	 * @param source
	 * @return
	 */
	@PreAuthorize("isAuthenticated() and hasPermission(#corpusId,"
			+ "T(com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.utils.ResourcesTypeUtils).corpus.getName(),"
			+ "@genericPairSpringSpel.getGenericPairSpringSpel(T(com.silicolife.anote2daemon.security.RestPermissionsEvaluatorEnum).default_,@permissions.getFullgrant()))")
	@RequestMapping(value = "/getCorpusPublicationsExternalIDFromSource/{corpusId}/{source}", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<Set<String>>> getCorpusPublicationsExternalIDFromSource(@PathVariable Long corpusId, @PathVariable String source) {
		DaemonResponse<Set<String>> response = new DaemonResponse<Set<String>>(corpusService.getCorpusPublicationsExternalIDFromSource(corpusId, source));
		return new ResponseEntity<DaemonResponse<Set<String>>>(response, HttpStatus.OK);
	}

	/**
	 * Register corpus to a process
	 * 
	 * @param corpusId
	 * @param processId
	 * @return
	 * @throws CorpusException 
	 */
	@PreAuthorize("isAuthenticated() and hasPermission(#corpusId,"
			+ "T(com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.utils.ResourcesTypeUtils).corpus.getName(),"
			+ "@genericPairSpringSpel.getGenericPairSpringSpel(T(com.silicolife.anote2daemon.security.RestPermissionsEvaluatorEnum).default_,@permissions.getWritegrant()))")
	@CacheEvict(value = "corpusStatistics", key="#corpusId")
	@RequestMapping(value = "/registerCorpusProcess", method = RequestMethod.POST)
	public ResponseEntity<DaemonResponse<Boolean>> registerCorpusProcess(@RequestParam Long corpusId, @RequestParam Long processId) throws CorpusException {
		DaemonResponse<Boolean> response = new DaemonResponse<Boolean>(corpusService.registerCorpusProcess(corpusId, processId));
		return new ResponseEntity<DaemonResponse<Boolean>>(response, HttpStatus.OK);
	}
	
	/**
	 * Associate publication to corpus
	 * 
	 * @param corpusId
	 * @param publicationId
	 * @throws CorpusException
	 */
	@PreAuthorize("isAuthenticated() and hasPermission(#corpusId,"
			+ "T(com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.utils.ResourcesTypeUtils).corpus.getName(),"
			+ "@genericPairSpringSpel.getGenericPairSpringSpel(T(com.silicolife.anote2daemon.security.RestPermissionsEvaluatorEnum).default_,@permissions.getWritegrant()))")
	@CacheEvict(value = "corpusStatistics", key="#corpusId")
	@RequestMapping(value = "/addCorpusPublication", method = RequestMethod.POST)
	public ResponseEntity<DaemonResponse<Boolean>> addCorpusPublication(@RequestParam Long corpusId, @RequestParam Long publicationId) throws CorpusException {
		DaemonResponse<Boolean> response = new DaemonResponse<Boolean>(corpusService.addCorpusPublication(corpusId, publicationId));
		return new ResponseEntity<DaemonResponse<Boolean>>(response, HttpStatus.OK);
	}
	
	
	/**
	 * Get statistics from a corpus
	 * 
	 * @param corpusId
	 * @return
	 * @throws CorpusException
	 */
	@PreAuthorize("isAuthenticated() and hasPermission(#corpusId,"
			+ "T(com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.utils.ResourcesTypeUtils).corpus.getName(),"
			+ "@genericPairSpringSpel.getGenericPairSpringSpel(T(com.silicolife.anote2daemon.security.RestPermissionsEvaluatorEnum).default_,@permissions.getFullgrant()))")
	@Cacheable(value = "corpusStatistics", key="#corpusId")
	@RequestMapping(value = "/getCorpusStatistics/{corpusId}", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<ICorpusStatistics>> getCorpusStatistics(@PathVariable Long corpusId) throws CorpusException{
		DaemonResponse<ICorpusStatistics> response = new DaemonResponse<ICorpusStatistics>(corpusService.getCorpusStatistics(corpusId));
		return new ResponseEntity<DaemonResponse<ICorpusStatistics>>(response, HttpStatus.OK);

	}
	
	/**
	 * Inactive a corpus
	 * 
	 * @param corpusId
	 * @return
	 * @throws CorpusException
	 */
	@PreAuthorize("isAuthenticated() and hasPermission(#corpusId,"
			+ "T(com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.utils.ResourcesTypeUtils).corpus.getName(),"
			+ "@genericPairSpringSpel.getGenericPairSpringSpel(T(com.silicolife.anote2daemon.security.RestPermissionsEvaluatorEnum).default_,@permissions.getWritegrant()))")
	@CacheEvict(value = "corpusStatistics", key="#corpusId")
	@RequestMapping(value = "/inativateCorpus", method = RequestMethod.POST)
	public ResponseEntity<DaemonResponse<Boolean>> inativateCorpus(@RequestParam Long corpusId) throws CorpusException{
		DaemonResponse<Boolean> response = new DaemonResponse<Boolean>(corpusService.inativateCorpus(corpusId));
		return new ResponseEntity<DaemonResponse<Boolean>>(response, HttpStatus.OK);
	}
	
	
	/**
	 * Get all owner or admin 
	 * 
	 * @return
	 */
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/getAllPrivilegesCorpusAdminAccess", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<List<ICorpus>>>  getAllPrivilegesCorpusAdminAccess(){
		DaemonResponse<List<ICorpus>> response = new DaemonResponse<List<ICorpus>>(corpusService.getAllPrivilegesCorpusAdminAccess());
		return new ResponseEntity<DaemonResponse<List<ICorpus>>>(response, HttpStatus.OK);
	}
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/getCorpusByPublicationId/{publicationId}", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<Set<ICorpus>>> getCorpusByPublicationId(@PathVariable Long publicationId) throws CorpusException{
		DaemonResponse<Set<ICorpus>> response = new DaemonResponse<Set<ICorpus>>(corpusService.getCorpusByPublicationId(publicationId));
		return new ResponseEntity<DaemonResponse<Set<ICorpus>>>(response, HttpStatus.OK);
	}
	
	/**
	 * 
	 * Count outdated publications
	 * 
	 * @param corpusId
	 * @param processId
	 * @return
	 * @throws CorpusException
	 */
	@PreAuthorize("isAuthenticated() and hasPermission(#corpusId,"
			+ "T(com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.utils.ResourcesTypeUtils).corpus.getName(),"
			+ "@genericPairSpringSpel.getGenericPairSpringSpel(T(com.silicolife.anote2daemon.security.RestPermissionsEvaluatorEnum).default_,@permissions.getFullgrant()))")
	@RequestMapping(value = "/countCorpusPublicationsOutdated/{corpusId}/{processId}", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<Long>> countCorpusPublicationsOutdated(@PathVariable Long corpusId, @PathVariable Long processId) throws CorpusException {
		DaemonResponse<Long> response = new DaemonResponse<Long>(corpusService.countCorpusPublicationsOutdated(corpusId, processId));
		return new ResponseEntity<DaemonResponse<Long>>(response, HttpStatus.OK);
	}
	
	/**
	 * 
	 * Get outdated publications 
	 * 
	 * @param corpusId
	 * @param processId
	 * @param paginationIndex
	 * @param paginationSize
	 * @return
	 * @throws CorpusException
	 */
	@PreAuthorize("isAuthenticated()"
			+ " and hasPermission(#corpusId,"
			+ "T(com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.utils.ResourcesTypeUtils).corpus.getName(),"
			+ "@genericPairSpringSpel.getGenericPairSpringSpel(T(com.silicolife.anote2daemon.security.RestPermissionsEvaluatorEnum).default_,@permissions.getFullgrant()))"
			+ " and hasPermission(#processId, "
			+ "T(com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.utils.ResourcesTypeUtils).processes.getName(),"
			+ "@genericPairSpringSpel.getGenericPairSpringSpel(T(com.silicolife.anote2daemon.security.RestPermissionsEvaluatorEnum).default_,@permissions.getFullgrant()))")
	@RequestMapping(value = "/getCorpusPublicationsOutdatedPaginated/{corpusId}/{processId}/{paginationIndex}/{paginationSize}", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<IDocumentSet>> getCorpusPublicationsOutdatedPaginated(@PathVariable Long corpusId, @PathVariable Long processId, @PathVariable Long paginationIndex, @PathVariable Long paginationSize) throws CorpusException {
		DaemonResponse<IDocumentSet> response = new DaemonResponse<IDocumentSet>(corpusService.getCorpusPublicationsOutdatedPaginated(corpusId, processId, Integer.valueOf(paginationIndex.toString()), Integer.valueOf(paginationSize.toString())));
		return new ResponseEntity<DaemonResponse<IDocumentSet>>(response, HttpStatus.OK);
	}
	
	//Lucene
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/getCorpusFromSearchPaginated/{index}/{paginationSize}", method = RequestMethod.POST , consumes = { "application/json" })
	public ResponseEntity<DaemonResponse<List<ICorpus>>> getCorpusFromSearchPaginated(@RequestBody SearchPropertiesImpl searchProperties, @PathVariable int index,@PathVariable int paginationSize)  {
		DaemonResponse<List<ICorpus>> response = new DaemonResponse<List<ICorpus>>(corpusluceneService.getCorpusFromSearchPaginated(searchProperties, index, paginationSize));
		return new ResponseEntity<DaemonResponse<List<ICorpus>>>(response, HttpStatus.OK);
	}
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/countCorpusFromSearch", method = RequestMethod.POST , consumes = { "application/json" })
	public ResponseEntity<DaemonResponse<Integer>> countCorpusFromSearch(@RequestBody SearchPropertiesImpl searchProperties) {
		DaemonResponse<Integer> response = new DaemonResponse<Integer>(corpusluceneService.countCorpusFromSearch(searchProperties));
		return new ResponseEntity<DaemonResponse<Integer>>(response, HttpStatus.OK);
	}
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/getCorpusFromSearchWPrivileges", method = RequestMethod.POST , consumes = { "application/json" })
	public ResponseEntity<DaemonResponse<List<ICorpus>>> getCorpusFromSearchWPrivileges(@RequestBody SearchPropertiesImpl searchProperties)  {
		DaemonResponse<List<ICorpus>> response = new DaemonResponse<List<ICorpus>>(corpusluceneService.getCorpusFromSearchWPrivileges(searchProperties));
		return new ResponseEntity<DaemonResponse<List<ICorpus>>>(response, HttpStatus.OK);
	}
	
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/getPrivilegesCorpusAdminAccessFromSearchPaginated/{index}/{paginationSize}/{asc}/{sortBy}", method = RequestMethod.POST , consumes = { "application/json" })
	public ResponseEntity<DaemonResponse<List<ICorpus>>> getPrivilegesCorpusAdminAccessFromSearchPaginated(@RequestBody SearchPropertiesImpl searchProperties, @PathVariable int index,@PathVariable int paginationSize, @PathVariable boolean asc, @PathVariable String sortBy) {
		DaemonResponse<List<ICorpus>> response = new DaemonResponse<List<ICorpus>>(corpusluceneService.getPrivilegesCorpusAdminAccessFromSearchPaginated(searchProperties, index, paginationSize, asc,sortBy));
		return new ResponseEntity<DaemonResponse<List<ICorpus>>>(response, HttpStatus.OK);
	}
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/countPrivilegesCorpusAdminAccessFromSearch", method = RequestMethod.POST , consumes = { "application/json" })
	public ResponseEntity<DaemonResponse<Integer>> countPrivilegesCorpusAdminAccessFromSearch(@RequestBody SearchPropertiesImpl searchProperties) {
		DaemonResponse<Integer> response = new DaemonResponse<Integer>(corpusluceneService.countPrivilegesCorpusAdminAccessFromSearch(searchProperties));
		return new ResponseEntity<DaemonResponse<Integer>>(response, HttpStatus.OK);
	}
	
}
