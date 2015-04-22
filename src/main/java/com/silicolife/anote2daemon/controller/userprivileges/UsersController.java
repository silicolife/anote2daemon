package com.silicolife.anote2daemon.controller.userprivileges;

import java.util.List;

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

import pt.uminho.anote2.datastructures.dataaccess.database.dataaccess.implementation.exceptions.UserExceptions;
import pt.uminho.anote2.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.AuthUsers;
import pt.uminho.anote2.datastructures.dataaccess.database.dataaccess.implementation.service.users.UserService;
import pt.uminho.anote2.interfaces.core.user.IGroup;
import pt.uminho.anote2.interfaces.core.user.IUser;

import com.silicolife.anote2daemon.webservice.DaemonResponse;

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
	private UserService userService;

	/**
	 * Create a new user
	 * 
	 * @param user
	 * @return
	 */
	@PreAuthorize("hasRole('role_admin')")
	@RequestMapping(value = "/createUser", method = RequestMethod.POST, consumes = { "application/json" })
	public ResponseEntity<DaemonResponse<Boolean>> createUser(@RequestBody AuthUsers user) {
		DaemonResponse<Boolean> response = new DaemonResponse<Boolean>(userService.createUser(user));
		return new ResponseEntity<DaemonResponse<Boolean>>(response, HttpStatus.OK);
	}

	/**
	 * Update an user
	 * 
	 * @param user
	 * @return
	 */
	@PreAuthorize("hasRole('role_admin')")
	@RequestMapping(value = "/updateUser", method = RequestMethod.PUT, consumes = { "application/json" })
	public ResponseEntity<DaemonResponse<Boolean>> updateUser(@RequestBody AuthUsers user) {
		DaemonResponse<Boolean> response = new DaemonResponse<Boolean>(userService.updateUser(user));
		return new ResponseEntity<DaemonResponse<Boolean>>(response, HttpStatus.OK);
	}

	/**
	 * Delete an user
	 * 
	 * @param userId
	 * @return
	 * @throws UserExceptions
	 */
	@PreAuthorize("hasRole('role_admin')")
	@RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
	public ResponseEntity<DaemonResponse<Boolean>> deleteUser(@RequestParam Long userId){
		DaemonResponse<Boolean> response = new DaemonResponse<Boolean>(userService.removeUser(userId));
		return new ResponseEntity<DaemonResponse<Boolean>>(response, HttpStatus.OK);
	}

	/**
	 * Get all Groups
	 * 
	 * @param userId
	 * @return
	 */
	@PreAuthorize("hasRole('role_admin')")
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
	@PreAuthorize("hasRole('role_admin')")
	@RequestMapping(value = "/getUserByEmail", method = RequestMethod.POST)
	public ResponseEntity<DaemonResponse<IUser>> getUserByEmail(@RequestParam String email) {
		DaemonResponse<IUser> response = new DaemonResponse<IUser>(userService.getByEmail(email));
		return new ResponseEntity<DaemonResponse<IUser>>(response, HttpStatus.OK);
	}
	
	/**
	 * Get user by username
	 * 
	 * @param username
	 * @return
	 */
	@PreAuthorize("hasRole('role_admin')")
	@RequestMapping(value = "/getUserByUsername", method = RequestMethod.POST)
	public ResponseEntity<DaemonResponse<IUser>> getUserByUsername(@RequestParam String username) {
		DaemonResponse<IUser> response = new DaemonResponse<IUser>(userService.getByUsername(username));
		return new ResponseEntity<DaemonResponse<IUser>>(response, HttpStatus.OK);
	}

	/**
	 * Get all Users
	 * 
	 * @return
	 */
	@PreAuthorize("hasRole('role_admin')")
	@RequestMapping(value = "/getAllUsers", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<List<IUser>>> getAllUsers() {
		DaemonResponse<List<IUser>> response = new DaemonResponse<List<IUser>>(userService.getAllUsers());
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
}
