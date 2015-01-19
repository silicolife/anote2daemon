package com.silicolife.anote2daemon.controller;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.silicolife.anote2daemon.model.pojo.Publications;
import com.silicolife.anote2daemon.service.core.PublicationsService;

@RequestMapping("/publications")
@ResponseBody
@Controller
public class PublicationsController {

	@Autowired
	PublicationsService publicationService;

	@RequestMapping(value = "/getById/{id}", method = RequestMethod.GET)
	public Publications getPublications(@PathVariable Long id) {
		Publications publicationObj = publicationService.getById(id);
		
		
	    

	      
		
		
		
		return publicationObj;
	}
}
