package com.silicolife.anote2daemon.controller.userprivileges;

import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.silicolife.anote2daemon.webservice.DaemonResponse;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.exceptions.PrivilegesException;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.lucene.service.users.IUsersLuceneService;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.service.system.IPrivilegesService;
import com.silicolife.textmining.core.datastructures.documents.SearchPropertiesImpl;
import com.silicolife.textmining.core.interfaces.core.user.IUser;
import com.silicolife.textmining.core.interfaces.core.user.IUserDataObject;
import com.silicolife.textmining.core.interfaces.core.utils.IGenericPair;

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
	private IPrivilegesService privilegesService;
	
	@Autowired
	private IUsersLuceneService usersLuceneService;

	/**
	 * add privilege
	 * 
	 * @param userId
	 * @param resourceId
	 * @param resource
	 * @param privilege
	 * @return
	 * @throws PrivilegesException
	 */
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/addPrivileges", method = RequestMethod.POST)
	public ResponseEntity<DaemonResponse<Boolean>> addPrivileges(@RequestParam Long userId, @RequestParam Long resourceId, @RequestParam String resource,
			@RequestParam String privilege) throws PrivilegesException {
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
	 * @throws PrivilegesException
	 */
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/updatePrivileges", method = RequestMethod.POST)
	public ResponseEntity<DaemonResponse<Boolean>> updatePrivileges(@RequestParam Long userId, @RequestParam Long resourceId, @RequestParam String resource,
			@RequestParam String privilege) throws PrivilegesException {
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
	 * @throws PrivilegesException
	 */
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/deletePrivilegesForUser", method = RequestMethod.POST)
	public ResponseEntity<DaemonResponse<Boolean>> deletePrivilegesForUser(@RequestParam Long userId, @RequestParam Long resourceId, @RequestParam String resource) throws PrivilegesException {
		DaemonResponse<Boolean> response = new DaemonResponse<Boolean>(privilegesService.removePrivilege(userId, resourceId, resource));
		return new ResponseEntity<DaemonResponse<Boolean>>(response, HttpStatus.OK);
	}

	/**
	 * 
	 * @param userId
	 * @param resourceId
	 * @param resource
	 * @return
	 */
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/deletePrivileges", method = RequestMethod.POST)
	public ResponseEntity<DaemonResponse<Boolean>> deletePrivileges(@RequestParam Long resourceId, @RequestParam String resource) {
		DaemonResponse<Boolean> response = new DaemonResponse<Boolean>(privilegesService.removePrivilegeLoggedUser(resourceId, resource));
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
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/getUserDataObjectPrivilege", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<IUserDataObject>> getUserDataObjectPrivilege(@RequestParam Long userId, @RequestParam Long resourceId, @RequestParam String resource) {
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
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/getUsersAndPrivilegers", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<List<IGenericPair<IUser, String>>>> getUsersAndPrivilegers(@RequestParam Long resourceId, @RequestParam String resource) {
		DaemonResponse<List<IGenericPair<IUser, String>>> response = new DaemonResponse<List<IGenericPair<IUser, String>>>(privilegesService.getUsersAndPermissions(resourceId,
				resource));
		return new ResponseEntity<DaemonResponse<List<IGenericPair<IUser, String>>>>(response, HttpStatus.OK);
	}
	
	/**
	 * Count all users and permission that user to a resource
	 * 
	 * @param resourceId
	 * @param resource
	 * @return
	 */
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/countUsersAndPrivilegers", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<Integer>> countUsersAndPrivilegers(@RequestParam Long resourceId, @RequestParam String resource) {
		DaemonResponse<Integer> response = new DaemonResponse<Integer>(privilegesService.countUsersAndPermissions(resourceId,
				resource));
		return new ResponseEntity<DaemonResponse<Integer>>(response, HttpStatus.OK);
	}
	
	
	
	/**
	 * Get all users and permission that user to a resource
	 * 
	 * @param resourceId
	 * @param resource
	 * @return
	 */
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/getUsersAndPermissionsPaginated", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<List<IGenericPair<IUser, String>>>> getUsersAndPermissionsPaginated(@RequestParam Long resourceId, @RequestParam String resource, 
			@RequestParam Integer paginationIndex,@RequestParam Integer paginationSize,@RequestParam boolean asc,@RequestParam String sortBy) {
		DaemonResponse<List<IGenericPair<IUser, String>>> response = new DaemonResponse<List<IGenericPair<IUser, String>>>(privilegesService.getUsersAndPermissionsPaginated(resourceId,
				resource, paginationIndex, paginationSize, asc, sortBy));
		return new ResponseEntity<DaemonResponse<List<IGenericPair<IUser, String>>>>(response, HttpStatus.OK);
	}
	

	/**
	 * Check if user has permission to that resource
	 * 
	 * @param resourceId
	 * @param resource
	 * @param permissions
	 * @return
	 */
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/hasPermission/{resourceId}/{resource}", method = RequestMethod.POST, consumes = { "application/json" })
	public ResponseEntity<DaemonResponse<Boolean>> hasPermission(@PathVariable Long resourceId, @PathVariable String resource, @RequestBody List<String> permissions) {
		DaemonResponse<Boolean> response = new DaemonResponse<Boolean>(privilegesService.hasPermission(resourceId, resource, permissions));
		return new ResponseEntity<DaemonResponse<Boolean>>(response, HttpStatus.OK);
	}
	
	//Lucene
	
	/**
	 * Search users and get them paginated with the permission that user has to a resource 
	 * 
	 * @param resourceId
	 * @param resource
	 * @return
	 */
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/getUsersAndPermissionsFromSearchPaginated/{resourceId}/{resource}/{paginationIndex}/{paginationSize}/{asc}/{sortBy}", method = RequestMethod.POST , consumes = { "application/json" })
	public ResponseEntity<DaemonResponse<List<IGenericPair<IUser, String>>>> getUsersAndPermissionsFromSearchPaginated(@RequestBody SearchPropertiesImpl searchProperties,@PathVariable Long resourceId, @PathVariable String resource, 
			@PathVariable Integer paginationIndex,@PathVariable Integer paginationSize,@PathVariable boolean asc,@PathVariable String sortBy) {
		DaemonResponse<List<IGenericPair<IUser, String>>> response = new DaemonResponse<List<IGenericPair<IUser, String>>>(usersLuceneService.getUsersAndPermissionsFromSearchPaginated(searchProperties, resourceId,
				resource, paginationIndex, paginationSize, asc, sortBy));
		return new ResponseEntity<DaemonResponse<List<IGenericPair<IUser, String>>>>(response, HttpStatus.OK);
	}
}
