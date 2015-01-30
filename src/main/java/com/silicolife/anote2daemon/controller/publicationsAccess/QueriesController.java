package com.silicolife.anote2daemon.controller.publicationsAccess;

import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.silicolife.anote2daemon.model.pojo.Publications;
import com.silicolife.anote2daemon.model.pojo.Queries;
import com.silicolife.anote2daemon.service.core.QueriesService;
import com.silicolife.anote2daemon.webservice.DaemonResponse;

@RequestMapping(value = "/queries")
@ResponseBody
@Controller
public class QueriesController {

	@Autowired
	private QueriesService queriesService;

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/getAllQueries", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<Map<String, Set<Queries>>>> getAllQueries() {
		DaemonResponse<Map<String, Set<Queries>>> response = new DaemonResponse<Map<String, Set<Queries>>>(queriesService.getAll());
		return new ResponseEntity<DaemonResponse<Map<String, Set<Queries>>>>(response, HttpStatus.OK);
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/getQueryById/{id}", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<Map<String, Queries>>> getQueryById(@PathVariable Long id) {
		DaemonResponse<Map<String, Queries>> response = new DaemonResponse<Map<String, Queries>>(queriesService.getById(id));
		return new ResponseEntity<DaemonResponse<Map<String, Queries>>>(response, HttpStatus.OK);
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/getAllPublications/{id}", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<Map<String, Set<Publications>>>> getAllPublications(@PathVariable Long id) {
		DaemonResponse<Map<String, Set<Publications>>> response = new DaemonResponse<Map<String, Set<Publications>>>(queriesService.getAllPublications(id));
		return new ResponseEntity<DaemonResponse<Map<String, Set<Publications>>>>(response, HttpStatus.OK);
	}

}
