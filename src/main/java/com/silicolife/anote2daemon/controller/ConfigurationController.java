package com.silicolife.anote2daemon.controller;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.silicolife.anote2daemon.webservice.DaemonResponse;

@RequestMapping(value = "/", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
@ResponseBody
@Controller
public class ConfigurationController {

	private final String version = "Version: 2.6.0.52";
	private final String title = "@note2daemon - The best Text Mining Tool :d (SilicoLife) "+version;
	
	@RequestMapping(value = "/getVersion", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<String>> getVersion() {
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
	
	
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
	public ResponseEntity<String> getFirstPage() {
		
		String output ="<!DOCTYPE html>"
				+ "<html>"
				+ "<head>"
				+ "<meta charset=\"UTF-8\">"
				+ "<title>" + title + "</title>"
				+ "</head><body>"
				+ "<hr/>" + title + "<hr/>"
				+ "</body></html>";
		
		return new ResponseEntity<String>(output, HttpStatus.OK);
	}
	
	  @RequestMapping(value = "/checkIfIsLoggedIn", method = RequestMethod.GET)
	  @ResponseBody
	  public boolean checkIfIsLoggedin(HttpSession session) {
	    session.getId(); // to start an annouynimous session or get the current session.
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    if(authentication instanceof AnonymousAuthenticationToken){
	    	return false;
	    }
	    return authentication.isAuthenticated();
	  }
	
}
