package com.silicolife.anote2daemon.controller.publicationsAccess;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import pt.uminho.anote2.core.document.IPublication;
import pt.uminho.anote2.core.document.corpus.ICorpus;

import com.silicolife.anote2daemon.security.Permissions;
import com.silicolife.anote2daemon.service.corpora.CorpusService;
import com.silicolife.anote2daemon.webservice.DaemonResponse;

/**
 * The goal of this class is to expose for the web all Corpus functionalities of
 * anote2daemon. It is necessary a user logged to access these methods
 * 
 * @author Joel Azevedo Costa
 * @year 2015
 *
 */
@RequestMapping(value = "/corpus")
@ResponseBody
@Controller
public class CorpusController {

	@Autowired
	private Permissions permissions;
	@Autowired
	private CorpusService corpusService;

	/**
	 * Get all corpus
	 * 
	 * @return
	 */
	@PreAuthorize("hasRole('ROLE_ADMIN')")
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
	@PreAuthorize("hasRole('ROLE_ADMIN') and hasPermission(#id, T(com.silicolife.anote2daemon.utils.ResourcesTypeUtils).corpus.toString(), @permissions.getFullgrant())")
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
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/createCorpus", method = RequestMethod.PUT, consumes = { "application/json" })
	public ResponseEntity<DaemonResponse<Boolean>> getCorpusById(@RequestBody ICorpus corpus) {
		DaemonResponse<Boolean> response = new DaemonResponse<Boolean>(corpusService.createCorpus(corpus));
		return new ResponseEntity<DaemonResponse<Boolean>>(response, HttpStatus.OK);
	}

	/**
	 * Update corpus
	 * 
	 * @param id
	 * @return
	 */
	@PreAuthorize("hasRole('ROLE_ADMIN') and hasPermission(#id, T(com.silicolife.anote2daemon.utils.ResourcesTypeUtils).corpus.toString(), @permissions.getWritegrant())")
	@RequestMapping(value = "/updateCorpus", method = RequestMethod.POST, consumes = { "application/json" })
	public ResponseEntity<DaemonResponse<Boolean>> updateCorpus(@RequestBody ICorpus corpus) {
		DaemonResponse<Boolean> response = new DaemonResponse<Boolean>(corpusService.updateCorpus(corpus));
		return new ResponseEntity<DaemonResponse<Boolean>>(response, HttpStatus.OK);
	}
	
	/**
	 * get processes from a corpus
	 * 
	 * @param id
	 * @return
	 */
	@PreAuthorize("hasRole('ROLE_ADMIN') and hasPermission(#id, T(com.silicolife.anote2daemon.utils.ResourcesTypeUtils).corpus.toString(), @permissions.getFullgrant())")
	@RequestMapping(value = "/getCorpusPublications/{id}", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<List<IPublication>>> getCorpusPublications(@PathVariable Long id) {
		DaemonResponse<List<IPublication>> response = new DaemonResponse<List<IPublication>>(corpusService.getCorpusPublications(id));
		return new ResponseEntity<DaemonResponse<List<IPublication>>>(response, HttpStatus.OK);
	}
	
	/**
	 * Register corpus to a process
	 * 
	 * @param corpusId
	 * @param processId
	 * @return
	 */
	@PreAuthorize("hasRole('ROLE_ADMIN') and hasPermission(#id, T(com.silicolife.anote2daemon.utils.ResourcesTypeUtils).corpus.toString(), @permissions.getWritegrant())")
	@RequestMapping(value = "/registerCorpusProcess", method = RequestMethod.PUT)
	public ResponseEntity<DaemonResponse<Boolean>> registerCorpusProcess(@RequestParam Long corpusId, @RequestParam Long processId) {
		DaemonResponse<Boolean> response = new DaemonResponse<Boolean>(corpusService.registerCorpusProcess(corpusId, processId));
		return new ResponseEntity<DaemonResponse<Boolean>>(response, HttpStatus.OK);
	}
}
