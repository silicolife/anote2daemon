package com.silicolife.anote2daemon.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.silicolife.anote2daemon.webservice.DaemonResponse;

@RequestMapping(value = "/configuration", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
@ResponseBody
@Controller
public class ConfigurationController {

	@RequestMapping(value = "/getVersion", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<String>> getVersion() {
		String version = "Version: Development";
		DaemonResponse<String> response = new DaemonResponse<String>(version);
		return new ResponseEntity<DaemonResponse<String>>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public @ResponseBody String handleFileUpload(@RequestParam("file") MultipartFile file) {

		return "OK";

		/*
		 * String name = "ddd"; if (!file.isEmpty()) { try { File ff = new
		 * File("C:\\apache-tomcat-8.0.14\\temp\\ddd.txt"); byte[] bytes =
		 * file.getBytes(); BufferedOutputStream stream = new
		 * BufferedOutputStream(new FileOutputStream(ff)); stream.write(bytes);
		 * stream.close(); return "You successfully uploaded " + name + "!"; }
		 * catch (Exception e) { return "You failed to upload " + name + " => "
		 * + e.getMessage(); } } else { return "You failed to upload " + name +
		 * " because the file was empty."; }
		 */
	}

}