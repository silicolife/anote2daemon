package com.silicolife.anote2daemon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/publications")
@ResponseBody
@Controller
public class PublicationsController {

	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public String getPublications() {

		return "Olá";
	}
}
