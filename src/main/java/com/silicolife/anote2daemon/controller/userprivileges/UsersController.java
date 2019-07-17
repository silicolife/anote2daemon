package com.silicolife.anote2daemon.controller.userprivileges;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.silicolife.anote2daemon.webservice.DaemonResponse;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.exceptions.UserExceptions;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.lucene.service.users.IUsersLuceneService;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.AuthUsers;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.service.users.IUserService;
import com.silicolife.textmining.core.datastructures.documents.SearchPropertiesImpl;
import com.silicolife.textmining.core.datastructures.utils.GenericPairImpl;
import com.silicolife.textmining.core.interfaces.core.user.IGroup;
import com.silicolife.textmining.core.interfaces.core.user.IUser;

/**
 * 
 * 
 * @author Utilizador
 *
 */

@RequestMapping(value = "/users", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
@ResponseBody
@Controller
public class UsersController {

	@Autowired
	private IUserService userService;
	
	@Autowired
	private IUsersLuceneService usersLuceneService;

	/**
	 * Check if login exist
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	@RequestMapping(value = "/checkLogin", method = RequestMethod.POST)
	public ResponseEntity<DaemonResponse<Boolean>> checkLogin(@RequestParam String username, @RequestParam String password) {
		DaemonResponse<Boolean> response = new DaemonResponse<Boolean>(userService.checkLogin(username, password));
		return new ResponseEntity<DaemonResponse<Boolean>>(response, HttpStatus.OK);
	}

	/**
	 * Create a new user
	 * 
	 * @param user
	 * @return
	 */
	@PreAuthorize("hasAuthority('role_admin')")
	@RequestMapping(value = "/createUser", method = RequestMethod.POST, consumes = { "application/json" })
	public ResponseEntity<DaemonResponse<Boolean>> createUser(@RequestBody AuthUsers user) {
		DaemonResponse<Boolean> response = new DaemonResponse<Boolean>(userService.createUser(user));
		return new ResponseEntity<DaemonResponse<Boolean>>(response, HttpStatus.OK);
	}
	
	
	/**
	 * Create a new user encoding password given
	 * 
	 * @param user
	 * @return
	 */
	@PreAuthorize("hasAuthority('role_admin')")
	@RequestMapping(value = "/createUserFromWeb", method = RequestMethod.POST, consumes = { "application/json" })
	public ResponseEntity<DaemonResponse<Boolean>> createUserFromWeb(@RequestBody AuthUsers user) {
		DaemonResponse<Boolean> response = new DaemonResponse<Boolean>(userService.createUserFromWeb(user));
		return new ResponseEntity<DaemonResponse<Boolean>>(response, HttpStatus.OK);
	}
	
	/**
	 * Update an user
	 * 
	 * @param user
	 * @return
	 */
	@PreAuthorize("hasAuthority('role_admin')")
	@RequestMapping(value = "/updateUser", method = RequestMethod.PUT, consumes = { "application/json" })
	public ResponseEntity<DaemonResponse<Boolean>> updateUser(@RequestBody AuthUsers user) {
		DaemonResponse<Boolean> response = new DaemonResponse<Boolean>(userService.updateUser(user));
		return new ResponseEntity<DaemonResponse<Boolean>>(response, HttpStatus.OK);
	}
	
	/**
	 * Update an user
	 * 
	 * @param user
	 * @return
	 */
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/updateCurrentUserFromWeb", method = RequestMethod.PUT , consumes = { "application/json" })
	public ResponseEntity<DaemonResponse<Boolean>> updateCurrentUserFromWeb(@RequestBody GenericPairImpl<AuthUsers,String> pair) {
		DaemonResponse<Boolean> response = new DaemonResponse<Boolean>(userService.updateCurrentUserFromWeb(pair.getX(), pair.getY()));
		return new ResponseEntity<DaemonResponse<Boolean>>(response, HttpStatus.OK);
	}
	
	/**
	 * Update an user
	 * 
	 * @param user
	 * @return
	 */
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/postAvatar", method = RequestMethod.POST )
	public ResponseEntity<DaemonResponse<byte[]>> postAvatar(@RequestBody String image) {
		DaemonResponse<byte[]> response = new DaemonResponse<byte[]>(userService.postAvatar(image));
		return new ResponseEntity<DaemonResponse<byte[]>>(response, HttpStatus.OK);
	}

	/**
	 * Delete an user
	 * 
	 * @param userId
	 * @return
	 * @throws UserExceptions
	 */
	@PreAuthorize("hasAuthority('role_admin')")
	@RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
	public ResponseEntity<DaemonResponse<Boolean>> deleteUser(@RequestParam Long userId) throws UserExceptions {
		DaemonResponse<Boolean> response = new DaemonResponse<Boolean>(userService.removeUser(userId));
		return new ResponseEntity<DaemonResponse<Boolean>>(response, HttpStatus.OK);
	}

	/**
	 * Get all Groups
	 * 
	 * @param userId
	 * @return
	 */
	@PreAuthorize("hasAuthority('role_admin')")
	@RequestMapping(value = "/getAllGroups", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<List<IGroup>>> getAllGroups() {
		DaemonResponse<List<IGroup>> response = new DaemonResponse<List<IGroup>>(userService.getAllGroups());
		return new ResponseEntity<DaemonResponse<List<IGroup>>>(response, HttpStatus.OK);
	}

	/**
	 * Get user by email
	 * 
	 * @param email
	 * @return
	 */
	@PreAuthorize("hasAuthority('role_admin')")
	@RequestMapping(value = "/getUserByEmail", method = RequestMethod.POST)
	public ResponseEntity<DaemonResponse<IUser>> getUserByEmail(@RequestParam String email) {
		IUser user = userService.getByEmail(email);
		if(user != null)
			user.setAuPassword(null);
		DaemonResponse<IUser> response = new DaemonResponse<IUser>(user);
		return new ResponseEntity<DaemonResponse<IUser>>(response, HttpStatus.OK);
	}
	
	/**
	 * Get user by email
	 * 
	 * @param email
	 * @return
	 */
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/getLoggedUser", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<IUser>> getLoggedUser() {
		IUser user = userService.getLoggedUser();
		if(user != null)
			user.setAuPassword(null);
		DaemonResponse<IUser> response = new DaemonResponse<IUser>(user);
		return new ResponseEntity<DaemonResponse<IUser>>(response, HttpStatus.OK);
	}

	/**
	 * Get user by username
	 * 
	 * @param username
	 * @return
	 */
	@PreAuthorize("hasAuthority('role_admin')")
	@RequestMapping(value = "/getUserByUsername", method = RequestMethod.POST)
	public ResponseEntity<DaemonResponse<IUser>> getUserByUsername(@RequestParam String username) {
		IUser user = userService.getByUsername(username);
		if(user != null)
			user.setAuPassword(null);
		DaemonResponse<IUser> response = new DaemonResponse<IUser>(user);
		return new ResponseEntity<DaemonResponse<IUser>>(response, HttpStatus.OK);
	}

	/**
	 * Get all Users
	 * 
	 * @return
	 */
	@PreAuthorize("hasAuthority('role_admin')")
	@RequestMapping(value = "/getAllUsers", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<List<IUser>>> getAllUsers() {
		List<AuthUsers> authUsers = userService.getAllUsers();
		List<IUser> users = new ArrayList<IUser>();
		for(AuthUsers user :authUsers){
			user.setAuPassword(null);
			users.add(user);
		}
		DaemonResponse<List<IUser>> response = new DaemonResponse<List<IUser>>(users);
		return new ResponseEntity<DaemonResponse<List<IUser>>>(response, HttpStatus.OK);
	}

	/**
	 * Verify if user has some roles
	 * 
	 * @return
	 */
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/hasPermissionRole", method = RequestMethod.POST, consumes = { "application/json" })
	public ResponseEntity<DaemonResponse<Boolean>> hasPermissionRole(@RequestBody List<String> rolesCodes) {
		DaemonResponse<Boolean> response = new DaemonResponse<Boolean>(userService.hasPermissionRole(rolesCodes));
		return new ResponseEntity<DaemonResponse<Boolean>>(response, HttpStatus.OK);
	}
	
	/**
	 * 
	 * 
	 * @param propertiesIdentifiers
	 * @return
	 */
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/loadProperties", method = RequestMethod.POST, consumes = { "application/json" })
	public ResponseEntity<DaemonResponse<Properties>> loadProperties(@RequestBody HashSet<String> propertiesIdentifiers) {
		DaemonResponse<Properties> response = new DaemonResponse<Properties>(userService.loadProperties(propertiesIdentifiers));
		return new ResponseEntity<DaemonResponse<Properties>>(response, HttpStatus.OK);
	}
	
	/**
	 * 
	 * 
	 * @param properties
	 * @return
	 */
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/saveProperties", method = RequestMethod.POST, consumes = { "application/json" })
	public ResponseEntity<DaemonResponse<Boolean>> loadProperties(@RequestBody Properties properties) {
		DaemonResponse<Boolean> response = new DaemonResponse<Boolean>(userService.saveProperties(properties));
		return new ResponseEntity<DaemonResponse<Boolean>>(response, HttpStatus.OK);
	}
	
	//Lucene
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/countUsersFromSearch", method = RequestMethod.POST , consumes = { "application/json" })
	public ResponseEntity<DaemonResponse<Integer>> countGetPublicationsFromSearch(@RequestBody SearchPropertiesImpl searchProperties) {
		DaemonResponse<Integer> response = new DaemonResponse<Integer>(usersLuceneService.countUsersFromSearch(searchProperties));
		return new ResponseEntity<DaemonResponse<Integer>>(response, HttpStatus.OK);
	}
}
