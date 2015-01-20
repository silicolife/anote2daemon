package com.silicolife.anote2daemon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	public DaemonResponse getQuery(@PathVariable Long id) {
		DaemonResponse response = queriesService.getById(id);
		return response;
	}

	@RequestMapping(value = "/createQueryType/{description}", method = RequestMethod.GET)
	public DaemonResponse createQueryType(@PathVariable String description) {
		DaemonResponse response = queriesTypeService.create(description);
		return response;
	}
}
