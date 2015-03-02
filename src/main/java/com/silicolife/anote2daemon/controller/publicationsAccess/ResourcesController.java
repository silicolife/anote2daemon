package com.silicolife.anote2daemon.controller.publicationsAccess;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.silicolife.anote2daemon.model.core.entities.Classes;
import com.silicolife.anote2daemon.security.Permissions;
import com.silicolife.anote2daemon.service.resources.ClassesService;
import com.silicolife.anote2daemon.webservice.DaemonResponse;

/**
 * The goal of this class is to expose for the web all Resources
 * functionalities of anote2daemon. It is necessary a user logged to access
 * these methods
 * 
 * 
 * @author Joel Azevedo Costa
 * @year 2015
 *
 */
@RequestMapping(value = "/resources")
@ResponseBody
@Controller
public class ResourcesController {

	@Autowired
	private Permissions permissions;
	@Autowired
	private ClassesService classesService;

	/**
	 * create new class
	 * 
	 * @param classes
	 * @return
	 */
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/insertNewClass", method = RequestMethod.PUT)
	public ResponseEntity<DaemonResponse<Boolean>> insertNewClass(@RequestBody Classes classes) {
		DaemonResponse<Boolean> response = new DaemonResponse<Boolean>(classesService.insertNewClass(classes));
		return new ResponseEntity<DaemonResponse<Boolean>>(response, HttpStatus.OK);
	}

	/**
	 * get classes
	 * 
	 * @return
	 */
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/getClasses/}", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<Map<Long, String>>> getClasses() {
		DaemonResponse<Map<Long, String>> response = new DaemonResponse<Map<Long, String>>(classesService.getClasses());
		return new ResponseEntity<DaemonResponse<Map<Long, String>>>(response, HttpStatus.OK);
	}

	/**
	 * get Classes by id
	 * 
	 * @param id
	 * @return
	 */
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/getClassById/{id}", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<Classes>> getClassById(@PathVariable Long id) {
		DaemonResponse<Classes> response = new DaemonResponse<Classes>(classesService.getClassById(id));
		return new ResponseEntity<DaemonResponse<Classes>>(response, HttpStatus.OK);
	}

}
