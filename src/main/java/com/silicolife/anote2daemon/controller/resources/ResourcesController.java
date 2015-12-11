package com.silicolife.anote2daemon.controller.resources;

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

import pt.uminho.anote2.datastructures.dataaccess.database.dataaccess.implementation.exceptions.ResourcesExceptions;
import pt.uminho.anote2.datastructures.dataaccess.database.dataaccess.implementation.security.Permissions;
import pt.uminho.anote2.datastructures.dataaccess.database.dataaccess.implementation.service.resources.ResourcesService;
import pt.uminho.anote2.datastructures.resources.ResourceImpl;
import pt.uminho.anote2.datastructures.resources.dictionary.DictionaryImpl;
import pt.uminho.anote2.datastructures.resources.lexiacalwords.LexicalWordsImpl;
import pt.uminho.anote2.datastructures.resources.lookuptable.LookupTableImpl;
import pt.uminho.anote2.datastructures.resources.ontology.OntologyImpl;
import pt.uminho.anote2.datastructures.resources.rule.RuleSetImpl;
import pt.uminho.anote2.interfaces.resource.IResource;
import pt.uminho.anote2.interfaces.resource.IResourceElement;

import com.silicolife.anote2daemon.security.RestPermissionsEvaluatorEnum;
import com.silicolife.anote2daemon.utils.GenericPairSpringSpel;
import com.silicolife.anote2daemon.webservice.DaemonResponse;

/**
 * The goal of this class is to expose for the web all resources functionalities
 * of anote2daemon. It is necessary a user logged to access these methods
 * 
 * 
 * @author Joel Azevedo Costa
 * @version 1.0
 *
 */
@RequestMapping(value = "/resources", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
@ResponseBody
@Controller
public class ResourcesController {

	@Autowired
	private Permissions permissions;
	@Autowired
	private GenericPairSpringSpel<RestPermissionsEvaluatorEnum, List<String>> genericPairSpringSpel;
	@Autowired
	private ResourcesService resourcesService;

	/**
	 * Create a dictionary resource
	 * 
	 * @param resource
	 * @return
	 */
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/createResourceDictionary", method = RequestMethod.POST, consumes = { "application/json" })
	public ResponseEntity<DaemonResponse<Boolean>> createResourceDictionary(@RequestBody DictionaryImpl resource) {
		DaemonResponse<Boolean> response = new DaemonResponse<Boolean>(resourcesService.create(resource));
		return new ResponseEntity<DaemonResponse<Boolean>>(response, HttpStatus.OK);
	}
	
	/**
	 * Create a lookup table resource
	 * 
	 * @param resource
	 * @return
	 */
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/createResourceLookupTable", method = RequestMethod.POST, consumes = { "application/json" })
	public ResponseEntity<DaemonResponse<Boolean>> createResourceLookupTable(@RequestBody LookupTableImpl resource) {
		DaemonResponse<Boolean> response = new DaemonResponse<Boolean>(resourcesService.create(resource));
		return new ResponseEntity<DaemonResponse<Boolean>>(response, HttpStatus.OK);
	}
	
	/**
	 * Create rule set resource
	 * 
	 * @param resource
	 * @return
	 */
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/createResourceRuleSet", method = RequestMethod.POST, consumes = { "application/json" })
	public ResponseEntity<DaemonResponse<Boolean>> createResourceRuleSet(@RequestBody RuleSetImpl resource) {
		DaemonResponse<Boolean> response = new DaemonResponse<Boolean>(resourcesService.create(resource));
		return new ResponseEntity<DaemonResponse<Boolean>>(response, HttpStatus.OK);
	}
	
	/**
	 * Create an ontology resource
	 * 
	 * @param resource
	 * @return
	 */
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/createResourceOntology", method = RequestMethod.POST, consumes = { "application/json" })
	public ResponseEntity<DaemonResponse<Boolean>> createResourceOntology(@RequestBody OntologyImpl resource) {
		DaemonResponse<Boolean> response = new DaemonResponse<Boolean>(resourcesService.create(resource));
		return new ResponseEntity<DaemonResponse<Boolean>>(response, HttpStatus.OK);
	}
	
	/**
	 * Create a lexical words resource
	 * 
	 * @param resource
	 * @return
	 */
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/createResourceLexicalWords", method = RequestMethod.POST, consumes = { "application/json" })
	public ResponseEntity<DaemonResponse<Boolean>> createResourceLexicalWords(@RequestBody LexicalWordsImpl resource) {
		DaemonResponse<Boolean> response = new DaemonResponse<Boolean>(resourcesService.create(resource));
		return new ResponseEntity<DaemonResponse<Boolean>>(response, HttpStatus.OK);
	}

	/**
	 * 
	 * 
	 * @param type
	 * @return
	 * @throws ResourcesExceptions
	 */
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/getResourcesByType", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<List<IResource<IResourceElement>>>> getResourcesByType(@RequestParam String type) throws ResourcesExceptions {
		DaemonResponse<List<IResource<IResourceElement>>> response = new DaemonResponse<List<IResource<IResourceElement>>>(resourcesService.getResourcesByType(type));
		return new ResponseEntity<DaemonResponse<List<IResource<IResourceElement>>>>(response, HttpStatus.OK);
	}

	/**
	 * 
	 * 
	 * @param resourceId
	 * @return
	 */
	@PreAuthorize("isAuthenticated() and hasPermission(#resourceId,"
			+ "T(pt.uminho.anote2.datastructures.dataaccess.database.dataaccess.implementation.utils.ResourcesTypeUtils).resources.getName(),"
			+ "@genericPairSpringSpel.getGenericPairSpringSpel(T(com.silicolife.anote2daemon.security.RestPermissionsEvaluatorEnum).default_,@permissions.getFullgrant()))")
	@RequestMapping(value = "/getResourceById/{resourceId}", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<IResource<IResourceElement>>> getResourceById(@PathVariable Long resourceId) {
		DaemonResponse<IResource<IResourceElement>> response = new DaemonResponse<IResource<IResourceElement>>(resourcesService.getResourcesById(resourceId));
		return new ResponseEntity<DaemonResponse<IResource<IResourceElement>>>(response, HttpStatus.OK);
	}

	/**
	 * 
	 * 
	 * @param resource
	 * @return
	 * @throws ResourcesExceptions
	 */
	@PreAuthorize("isAuthenticated() and hasPermission(#resource.getID(), "
			+ "T(pt.uminho.anote2.datastructures.dataaccess.database.dataaccess.implementation.utils.ResourcesTypeUtils).resources.getName(),"
			+ "@genericPairSpringSpel.getGenericPairSpringSpel(T(com.silicolife.anote2daemon.security.RestPermissionsEvaluatorEnum).default_,@permissions.getWritegrant()))")
	@RequestMapping(value = "/updateResource", method = RequestMethod.PUT, consumes = { "application/json" })
	public ResponseEntity<DaemonResponse<Boolean>> updateResource(@RequestBody ResourceImpl resource) throws ResourcesExceptions {
		DaemonResponse<Boolean> response = new DaemonResponse<Boolean>(resourcesService.update(resource));
		return new ResponseEntity<DaemonResponse<Boolean>>(response, HttpStatus.OK);
	}

	/**
	 * 
	 * 
	 * @return
	 * @throws ResourcesExceptions
	 */
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/getAllPrivilegesResourcesAdminAccess", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<List<IResource<IResourceElement>>>> getAllPrivilegesResourcesAdminAccess() throws ResourcesExceptions {
		DaemonResponse<List<IResource<IResourceElement>>> response = new DaemonResponse<List<IResource<IResourceElement>>>(resourcesService.getAllPrivilegesResourcesAdminAccess());
		return new ResponseEntity<DaemonResponse<List<IResource<IResourceElement>>>>(response, HttpStatus.OK);
	}
}
