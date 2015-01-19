package com.silicolife.anote2daemon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.silicolife.anote2daemon.model.pojo.Publications;
import com.silicolife.anote2daemon.model.pojo.Queries;
import com.silicolife.anote2daemon.service.core.PublicationsService;
import com.silicolife.anote2daemon.service.core.QueriesService;

@ResponseBody
@Controller
public class PublicationsController {

	@Autowired
	private PublicationsService publicationService;
	@Autowired
	private QueriesService queriesService;

	@RequestMapping(value = "/publications/getById/{id}", method = RequestMethod.GET)
	public Publications getPublications(@PathVariable Long id) {
		Publications publicationObj = publicationService.getById(id);
		return publicationObj;
	}

	@RequestMapping(value = "/queries/getById/{id}", method = RequestMethod.GET)
	public Queries getQuery(@PathVariable Long id) {
		Queries queryObj = queriesService.getById(id);
		return queryObj;
	}
}
