package com.silicolife.anote2daemon.controller.publicationsAccess;

import java.util.List;

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

import pt.uminho.anote2.datastructures.corpora.CorpusImpl;
import pt.uminho.anote2.datastructures.dataaccess.database.dataaccess.implementation.exceptions.CorpusException;
import pt.uminho.anote2.datastructures.dataaccess.database.dataaccess.implementation.security.Permissions;
import pt.uminho.anote2.datastructures.dataaccess.database.dataaccess.implementation.service.corpora.ICorpusService;
import pt.uminho.anote2.interfaces.core.document.IDocumentSet;
import pt.uminho.anote2.interfaces.core.document.corpus.ICorpus;
import pt.uminho.anote2.interfaces.core.document.corpus.ICorpusStatistics;
import pt.uminho.anote2.interfaces.process.IE.IIEProcess;

import com.silicolife.anote2daemon.security.RestPermissionsEvaluatorEnum;
import com.silicolife.anote2daemon.utils.GenericPairSpringSpel;
import com.silicolife.anote2daemon.webservice.DaemonResponse;

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
	 * Get corpus by Id
	 * 
	 * @param id
	 * @return
	 */
	@PreAuthorize("isAuthenticated() and hasPermission(#id, "
			+ "T(pt.uminho.anote2.datastructures.dataaccess.database.dataaccess.implementation.utils.ResourcesTypeUtils).corpus.getName(),"
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
			+ "T(pt.uminho.anote2.datastructures.dataaccess.database.dataaccess.implementation.utils.ResourcesTypeUtils).corpus.getName(),"
			+ "@genericPairSpringSpel.getGenericPairSpringSpel(T(com.silicolife.anote2daemon.security.RestPermissionsEvaluatorEnum).default_,@permissions.getWritegrant()))")
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
			+ "T(pt.uminho.anote2.datastructures.dataaccess.database.dataaccess.implementation.utils.ResourcesTypeUtils).corpus.getName(),"
			+ "@genericPairSpringSpel.getGenericPairSpringSpel(T(com.silicolife.anote2daemon.security.RestPermissionsEvaluatorEnum).default_,@permissions.getFullgrant()))")
	@RequestMapping(value = "/getCorpusPublications/{id}", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<IDocumentSet>> getCorpusPublications(@PathVariable Long id) throws CorpusException {
		DaemonResponse<IDocumentSet> response = new DaemonResponse<IDocumentSet>(corpusService.getCorpusPublications(id));
		return new ResponseEntity<DaemonResponse<IDocumentSet>>(response, HttpStatus.OK);
	}
	
	
	@PreAuthorize("isAuthenticated() and hasPermission(#corpusId,"
			+ "T(pt.uminho.anote2.datastructures.dataaccess.database.dataaccess.implementation.utils.ResourcesTypeUtils).corpus.getName(),"
			+ "@genericPairSpringSpel.getGenericPairSpringSpel(T(com.silicolife.anote2daemon.security.RestPermissionsEvaluatorEnum).default_,@permissions.getFullgrant()))")
	@RequestMapping(value = "/getCorpusProcesses/{corpusId}", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<List<IIEProcess>>> getCorpusProcesses(@PathVariable Long corpusId) throws CorpusException{
		DaemonResponse<List<IIEProcess>> response = new DaemonResponse<List<IIEProcess>>(corpusService.getCorpusProcesses(corpusId));
		return new ResponseEntity<DaemonResponse<List<IIEProcess>>>(response, HttpStatus.OK);
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
			+ "T(pt.uminho.anote2.datastructures.dataaccess.database.dataaccess.implementation.utils.ResourcesTypeUtils).corpus.getName(),"
			+ "@genericPairSpringSpel.getGenericPairSpringSpel(T(com.silicolife.anote2daemon.security.RestPermissionsEvaluatorEnum).default_,@permissions.getWritegrant()))")
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
			+ "T(pt.uminho.anote2.datastructures.dataaccess.database.dataaccess.implementation.utils.ResourcesTypeUtils).corpus.getName(),"
			+ "@genericPairSpringSpel.getGenericPairSpringSpel(T(com.silicolife.anote2daemon.security.RestPermissionsEvaluatorEnum).default_,@permissions.getWritegrant()))")
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
			+ "T(pt.uminho.anote2.datastructures.dataaccess.database.dataaccess.implementation.utils.ResourcesTypeUtils).corpus.getName(),"
			+ "@genericPairSpringSpel.getGenericPairSpringSpel(T(com.silicolife.anote2daemon.security.RestPermissionsEvaluatorEnum).default_,@permissions.getFullgrant()))")
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
			+ "T(pt.uminho.anote2.datastructures.dataaccess.database.dataaccess.implementation.utils.ResourcesTypeUtils).corpus.getName(),"
			+ "@genericPairSpringSpel.getGenericPairSpringSpel(T(com.silicolife.anote2daemon.security.RestPermissionsEvaluatorEnum).default_,@permissions.getWritegrant()))")
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
}
