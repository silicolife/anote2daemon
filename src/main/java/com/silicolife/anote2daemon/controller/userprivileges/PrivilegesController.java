package com.silicolife.anote2daemon.controller.userprivileges;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import pt.uminho.anote2.datastructures.dataaccess.database.dataaccess.implementation.service.system.PrivilegesService;
import pt.uminho.anote2.interfaces.core.user.IUser;
import pt.uminho.anote2.interfaces.core.user.IUserDataObject;
import pt.uminho.anote2.interfaces.core.utils.IGenericPair;

import com.silicolife.anote2daemon.webservice.DaemonResponse;

/**
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
	 * add privilege
	 * 
	 * @param userId
	 * @param resourceId
	 * @param resource
	 * @param privilege
	 * @return
	 */
	@PreAuthorize("hasRole('role_admin')")
	@RequestMapping(value = "/addPrivileges", method = RequestMethod.POST)
	public ResponseEntity<DaemonResponse<Boolean>> addPrivileges(@RequestParam Long userId, @RequestParam Long resourceId, @RequestParam String resource,
			@RequestParam String privilege) {
		DaemonResponse<Boolean> response = new DaemonResponse<Boolean>(privilegesService.addPrivilege(userId, resourceId, resource, privilege));
		return new ResponseEntity<DaemonResponse<Boolean>>(response, HttpStatus.OK);
	}

	/**
	 * update privilege
	 * 
	 * @param userId
	 * @param resourceId
	 * @param resource
	 * @param privilege
	 * @return
	 */
	@PreAuthorize("hasRole('role_admin')")
	@RequestMapping(value = "/updatePrivileges", method = RequestMethod.PUT)
	public ResponseEntity<DaemonResponse<Boolean>> updatePrivileges(@RequestParam Long userId, @RequestParam Long resourceId, @RequestParam String resource,
			@RequestParam String privilege) {
		DaemonResponse<Boolean> response = new DaemonResponse<Boolean>(privilegesService.updatePrivilege(userId, resourceId, resource, privilege));
		return new ResponseEntity<DaemonResponse<Boolean>>(response, HttpStatus.OK);
	}

	/**
	 * delete privilege
	 * 
	 * @param userId
	 * @param resourceId
	 * @param resource
	 * @return
	 */
	@PreAuthorize("hasRole('role_admin')")
	@RequestMapping(value = "/deletePrivileges", method = RequestMethod.POST)
	public ResponseEntity<DaemonResponse<Boolean>> deletePrivileges(@RequestParam Long userId, @RequestParam Long resourceId, @RequestParam String resource) {
		DaemonResponse<Boolean> response = new DaemonResponse<Boolean>(privilegesService.removePrivilege(userId, resourceId, resource));
		return new ResponseEntity<DaemonResponse<Boolean>>(response, HttpStatus.OK);
	}

	/**
	 * Get privilege from an user to resource
	 * 
	 * @param userId
	 * @param resourceId
	 * @param resource
	 * @return
	 */
	@PreAuthorize("hasRole('role_admin')")
	@RequestMapping(value = "/getUserDataObjectPrivilege/{userId}/{resourceId}/{resource}", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<IUserDataObject>> getUserDataObjectPrivilege(@PathVariable Long userId, @PathVariable Long resourceId, @PathVariable String resource) {
		DaemonResponse<IUserDataObject> response = new DaemonResponse<IUserDataObject>(privilegesService.getPrivilege(userId, resourceId, resource));
		return new ResponseEntity<DaemonResponse<IUserDataObject>>(response, HttpStatus.OK);
	}

	/**
	 * Get all users and permission that user to a resource
	 * 
	 * @param resourceId
	 * @param resource
	 * @return
	 */
	@PreAuthorize("hasRole('role_admin')")
	@RequestMapping(value = "/getUsersAndPrivilegers/{resourceId}/{resource}", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<List<IGenericPair<IUser, String>>>> getUsersAndPrivilegers(@PathVariable Long resourceId, @PathVariable String resource) {
		DaemonResponse<List<IGenericPair<IUser, String>>> response = new DaemonResponse<List<IGenericPair<IUser, String>>>(privilegesService.getUsersAndPermissions(resourceId,
				resource));
		return new ResponseEntity<DaemonResponse<List<IGenericPair<IUser, String>>>>(response, HttpStatus.OK);
	}
}
