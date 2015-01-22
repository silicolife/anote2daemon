package com.silicolife.anote2daemon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.silicolife.anote2daemon.service.core.PublicationsService;
import com.silicolife.anote2daemon.service.core.QueriesService;
import com.silicolife.anote2daemon.webservice.DaemonResponse;

@RequestMapping(value = "/publications")
@ResponseBody
@Controller
public class PublicationsController {

	@Autowired
	private PublicationsService publicationService;
	@Autowired
	private QueriesService queriesService;

	@RequestMapping(value = "/getById/{id}", method = RequestMethod.GET)
	public DaemonResponse getPublications(@PathVariable Long id) {
		DaemonResponse response = publicationService.getById(id);
		return response;
	}
}
