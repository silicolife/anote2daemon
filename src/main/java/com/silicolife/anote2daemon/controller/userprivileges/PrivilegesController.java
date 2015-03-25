package com.silicolife.anote2daemon.controller.userprivileges;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pt.uminho.anote2.datastructures.dataaccess.database.dataaccess.implementation.service.system.PrivilegesService;

import com.silicolife.anote2daemon.webservice.DaemonResponse;

/**
 * 
 * 
 * 
 * @author Utilizador
 *
 */
@RequestMapping(value = "/privileges", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
@ResponseBody
@Controller
public class PrivilegesController {

	@Autowired
	private PrivilegesService privilegesService;

	/**
	 * 
	 * @param userId
	 * @param resourceId
	 * @param resource
	 * @param privilege
	 * @return
	 */
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/addPrivileges", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<Boolean>> addPrivileges(@PathVariable Long userId, @PathVariable Long resourceId, @PathVariable String resource,
			@PathVariable String privilege) {
		DaemonResponse<Boolean> response = new DaemonResponse<Boolean>(privilegesService.addPrivilege(userId, resourceId, resource, privilege));
		return new ResponseEntity<DaemonResponse<Boolean>>(response, HttpStatus.OK);
	}

	/**
	 * 
	 * @param userId
	 * @param resourceId
	 * @param resource
	 * @param privilege
	 * @return
	 */
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/updatePrivileges", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<Boolean>> updatePrivileges(@PathVariable Long userId, @PathVariable Long resourceId, @PathVariable String resource,
			@PathVariable String privilege) {
		DaemonResponse<Boolean> response = new DaemonResponse<Boolean>(privilegesService.updatePrivilege(userId, resourceId, resource, privilege));
		return new ResponseEntity<DaemonResponse<Boolean>>(response, HttpStatus.OK);
	}

	/**
	 * 
	 * @param userId
	 * @param resourceId
	 * @param resource
	 * @return
	 */
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/removePrivileges", method = RequestMethod.DELETE)
	public ResponseEntity<DaemonResponse<Boolean>> removePrivileges(@PathVariable Long userId, @PathVariable Long resourceId, @PathVariable String resource) {
		DaemonResponse<Boolean> response = new DaemonResponse<Boolean>(privilegesService.removePrivilege(userId, resourceId, resource));
		return new ResponseEntity<DaemonResponse<Boolean>>(response, HttpStatus.OK);
	}
}
