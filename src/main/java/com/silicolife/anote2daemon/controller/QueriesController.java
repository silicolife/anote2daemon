package com.silicolife.anote2daemon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.silicolife.anote2daemon.service.core.QueriesService;
import com.silicolife.anote2daemon.service.core.QueriesTypeService;
import com.silicolife.anote2daemon.utils.DaemonResponse;

@RequestMapping(value = "/queries")
@ResponseBody
@Controller
public class QueriesController {

	@Autowired
	private QueriesService queriesService;

	@Autowired
	private QueriesTypeService queriesTypeService;

	@RequestMapping(value = "/getById/{id}", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse> getQuery(@PathVariable Long id) {
		DaemonResponse response = queriesService.getById(id);
		return new ResponseEntity<DaemonResponse>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/createQueryType/{description}", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse> createQueryType(@PathVariable String description) {
		DaemonResponse response = queriesTypeService.create(description);
		return new ResponseEntity<DaemonResponse>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/updateQueryType", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse> updateQueryType(@RequestParam(value = "id") Long id, @RequestParam(value = "desc") String description) {
		DaemonResponse response = queriesTypeService.update(id, description);
		return new ResponseEntity<DaemonResponse>(response, HttpStatus.OK);
	}
}
