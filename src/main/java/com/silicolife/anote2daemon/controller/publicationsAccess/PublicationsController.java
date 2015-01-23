package com.silicolife.anote2daemon.controller.publicationsAccess;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.silicolife.anote2daemon.model.pojo.Publications;
import com.silicolife.anote2daemon.service.core.PublicationsService;
import com.silicolife.anote2daemon.webservice.DaemonResponse;

@RequestMapping(value = "/publications")
@ResponseBody
@Controller
public class PublicationsController {

	@Autowired
	private PublicationsService publicationService;

	@RequestMapping(value = "/getPublicationById/{id}", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<Publications>> getPublicationById(@PathVariable Long id) {
		DaemonResponse<Publications> response = publicationService.getById(id);
		return new ResponseEntity<DaemonResponse<Publications>>(response, HttpStatus.OK);
	}
}
