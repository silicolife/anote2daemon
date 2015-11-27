package com.silicolife.anote2daemon.controller.ResourcesAccess;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pt.uminho.anote2.datastructures.dataaccess.database.dataaccess.implementation.exceptions.ClassException;
import pt.uminho.anote2.datastructures.dataaccess.database.dataaccess.implementation.security.Permissions;
import pt.uminho.anote2.datastructures.dataaccess.database.dataaccess.implementation.service.resources.ClassesService;
import pt.uminho.anote2.datastructures.general.AnoteClass;
import pt.uminho.anote2.interfaces.core.general.classe.IAnoteClass;

import com.silicolife.anote2daemon.webservice.DaemonResponse;

/**
 * The goal of this class is to expose for the web all classe functionalities
 * of anote2daemon. It is necessary a user logged to access these methods
 * 
 * 
 * @author Joel Azevedo Costa
 * @year 2015
 *
 */
@RequestMapping(value = "/classes", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
@ResponseBody
@Controller
public class ClassesController {

	@Autowired
	private Permissions permissions;
	@Autowired
	private ClassesService classesService;

	/**
	 * create new class
	 * 
	 * @param classes
	 * @return
	 * @throws ClassException 
	 */
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/insertNewClass", method = RequestMethod.PUT)
	public ResponseEntity<DaemonResponse<Boolean>> insertNewClass(@RequestBody AnoteClass classes) throws ClassException {
		DaemonResponse<Boolean> response = new DaemonResponse<Boolean>(classesService.addClass(classes));
		return new ResponseEntity<DaemonResponse<Boolean>>(response, HttpStatus.OK);
	}

	/**
	 * get classes
	 * 
	 * @return
	 */
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/getClasses/}", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<Set<IAnoteClass>>> getClasses() {
		DaemonResponse<Set<IAnoteClass>> response = new DaemonResponse<Set<IAnoteClass>>(classesService.getClasses());
		return new ResponseEntity<DaemonResponse<Set<IAnoteClass>>>(response, HttpStatus.OK);
	}

	/**
	 * get Classes by id
	 * 
	 * @param id
	 * @return
	 */
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/getClassById/{id}", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<IAnoteClass>> getClassById(@PathVariable Long id) {
		DaemonResponse<IAnoteClass> response = new DaemonResponse<IAnoteClass>(classesService.getClassById(id));
		return new ResponseEntity<DaemonResponse<IAnoteClass>>(response, HttpStatus.OK);
	}

}
