package com.silicolife.anote2daemon.controller.ResourcesAccess;

import java.util.ArrayList;
import java.util.List;
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

import pt.uminho.anote2.datastructures.dataaccess.database.dataaccess.implementation.exceptions.ResourcesExceptions;
import pt.uminho.anote2.datastructures.dataaccess.database.dataaccess.implementation.service.resources.ResourcesElementService;
import pt.uminho.anote2.datastructures.general.ExternalIDImpl;
import pt.uminho.anote2.datastructures.resources.ResourceElementImpl;
import pt.uminho.anote2.interfaces.core.dataaccess.layer.resources.IResourceManagerReport;
import pt.uminho.anote2.interfaces.core.general.IExternalID;
import pt.uminho.anote2.interfaces.core.general.classe.IAnoteClass;
import pt.uminho.anote2.interfaces.resource.IResourceElement;
import pt.uminho.anote2.interfaces.resource.IResourceElementSet;
import pt.uminho.anote2.interfaces.resource.content.IResourceContent;

import com.silicolife.anote2daemon.security.Permissions;
import com.silicolife.anote2daemon.webservice.DaemonResponse;

/**
 * The goal of this class is to expose for the web all resources elements
 * functionalities of anote2daemon. It is necessary a user logged to access
 * these methods
 * 
 * 
 * @author Joel Azevedo Costa
 * @year 2015
 *
 */
@RequestMapping(value = "/resourceElements", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
@ResponseBody
@Controller
public class ResourceElementsController {

	@Autowired
	private Permissions permissions;
	@Autowired
	private ResourcesElementService resourcesElementService;

	/**
	 * Add resource element
	 * 
	 * 
	 * @param resourceId
	 * @param elem
	 * @return
	 * @throws ResourcesExceptions
	 */
	@PreAuthorize("isAuthenticated() and hasPermission(#resourceId, T(pt.uminho.anote2.datastructures.dataaccess.database.dataaccess.implementation.utils.ResourcesTypeUtils).resources.getName(), @permissions.getWritegrant())")
	@RequestMapping(value = "/addResourceElements/{resourceId}", method = RequestMethod.POST, consumes = { "application/json" })
	public ResponseEntity<DaemonResponse<IResourceManagerReport>> addResourceElements(@PathVariable Long resourceId, @RequestBody List<ResourceElementImpl> elem)
			throws ResourcesExceptions {
		List<IResourceElement> resourceElementsList = new ArrayList<IResourceElement>();
		for (ResourceElementImpl resourceElem : elem)
			resourceElementsList.add(resourceElem);

		DaemonResponse<IResourceManagerReport> response = new DaemonResponse<IResourceManagerReport>(resourcesElementService.addResourceElements(resourceId, resourceElementsList));
		return new ResponseEntity<DaemonResponse<IResourceManagerReport>>(response, HttpStatus.OK);
	}

	/**
	 * Get resource elements by resource
	 * 
	 * @param resourceId
	 * @return
	 * @throws ResourcesExceptions
	 */
	@PreAuthorize("isAuthenticated() and hasPermission(#resourceId, T(pt.uminho.anote2.datastructures.dataaccess.database.dataaccess.implementation.utils.ResourcesTypeUtils).resources.getName(), @permissions.fullGrant())")
	@RequestMapping(value = "/getResourceElements/{resourceId}", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<IResourceElementSet<IResourceElement>>> getResourceElements(@PathVariable Long resourceId) throws ResourcesExceptions {
		DaemonResponse<IResourceElementSet<IResourceElement>> response = new DaemonResponse<IResourceElementSet<IResourceElement>>(
				resourcesElementService.getResourceElements(resourceId));
		return new ResponseEntity<DaemonResponse<IResourceElementSet<IResourceElement>>>(response, HttpStatus.OK);
	}

	/**
	 * Get resource elements by class
	 * 
	 * @param resourceId
	 * @param termClass
	 * @return
	 * @throws ResourcesExceptions
	 */
	@PreAuthorize("isAuthenticated() and hasPermission(#resourceId, T(pt.uminho.anote2.datastructures.dataaccess.database.dataaccess.implementation.utils.ResourcesTypeUtils).resources.getName(), @permissions.fullGrant())")
	@RequestMapping(value = "/getResourceElementsByClass/{resourceId}/{termClass}", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<IResourceElementSet<IResourceElement>>> getResourceElementsByClass(@PathVariable Long resourceId, @PathVariable String termClass)
			throws ResourcesExceptions {
		DaemonResponse<IResourceElementSet<IResourceElement>> response = new DaemonResponse<IResourceElementSet<IResourceElement>>(
				resourcesElementService.getResourceElementsByClass(resourceId, termClass));
		return new ResponseEntity<DaemonResponse<IResourceElementSet<IResourceElement>>>(response, HttpStatus.OK);
	}

	/**
	 * Get resource content
	 * 
	 * 
	 * @param resourceId
	 * @return
	 * @throws ResourcesExceptions
	 */
	@PreAuthorize("isAuthenticated() and hasPermission(#resourceId, T(pt.uminho.anote2.datastructures.dataaccess.database.dataaccess.implementation.utils.ResourcesTypeUtils).resources.getName(), @permissions.fullGrant())")
	@RequestMapping(value = "/addResourceElement/{resourceId}", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<IResourceContent>> getResourceContent(@PathVariable Long resourceId) throws ResourcesExceptions {
		DaemonResponse<IResourceContent> response = new DaemonResponse<IResourceContent>(resourcesElementService.getResourceContent(resourceId));
		return new ResponseEntity<DaemonResponse<IResourceContent>>(response, HttpStatus.OK);
	}

	/**
	 * Get classes by resource
	 * 
	 * 
	 * @param resourceId
	 * @return
	 * @throws ResourcesExceptions
	 */
	@PreAuthorize("isAuthenticated() and hasPermission(#resourceId, T(pt.uminho.anote2.datastructures.dataaccess.database.dataaccess.implementation.utils.ResourcesTypeUtils).resources.getName(), @permissions.fullGrant())")
	@RequestMapping(value = "/getResourceClassContent/{resourceId}", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<Set<IAnoteClass>>> getResourceClassContent(@PathVariable Long resourceId) throws ResourcesExceptions {
		DaemonResponse<Set<IAnoteClass>> response = new DaemonResponse<Set<IAnoteClass>>(resourcesElementService.getResourceClassContent(resourceId));
		return new ResponseEntity<DaemonResponse<Set<IAnoteClass>>>(response, HttpStatus.OK);
	}

	/**
	 * Get external Ids by resource element
	 * 
	 * 
	 * @param resourceElementID
	 * @return
	 */
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/getResourceElementExternalIDs/{resourceElementID}", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<List<IExternalID>>> getResourceElementExternalIDs(@PathVariable Long resourceElementID) {
		DaemonResponse<List<IExternalID>> response = new DaemonResponse<List<IExternalID>>(resourcesElementService.getResourceElementExternalIDs(resourceElementID));
		return new ResponseEntity<DaemonResponse<List<IExternalID>>>(response, HttpStatus.OK);
	}

	/**
	 * Add resource elements without validations
	 * 
	 * 
	 * @param resourceId
	 * @param element
	 * @return
	 * @throws ResourcesExceptions
	 */
	@PreAuthorize("isAuthenticated() and hasPermission(#resourceId, T(pt.uminho.anote2.datastructures.dataaccess.database.dataaccess.implementation.utils.ResourcesTypeUtils).resources.getName(), @permissions.getWritegrant())")
	@RequestMapping(value = "/addResourceElementsWithoutValidation/{resourceId}", method = RequestMethod.POST, consumes = { "application/json" })
	public ResponseEntity<DaemonResponse<IResourceManagerReport>> addResourceElementsWithoutValidation(@PathVariable Long resourceId, @RequestBody List<ResourceElementImpl> element)
			throws ResourcesExceptions {

		List<IResourceElement> resourceElementsList = new ArrayList<IResourceElement>();
		for (ResourceElementImpl resourceElem : element)
			resourceElementsList.add(resourceElem);

		DaemonResponse<IResourceManagerReport> response = new DaemonResponse<IResourceManagerReport>(resourcesElementService.addResourceElementsWithoutValidation(resourceId,
				resourceElementsList));
		return new ResponseEntity<DaemonResponse<IResourceManagerReport>>(response, HttpStatus.OK);
	}

	/**
	 * Check if resource element exist in resource
	 * 
	 * 
	 * @param resourceId
	 * @param term
	 * @return
	 * @throws ResourcesExceptions
	 */
	@PreAuthorize("isAuthenticated() and hasPermission(#resourceId, T(pt.uminho.anote2.datastructures.dataaccess.database.dataaccess.implementation.utils.ResourcesTypeUtils).resources.getName(), @permissions.fullGrant())")
	@RequestMapping(value = "/checkResourceElementExistsInResource/{resourceId}/{term}", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<Boolean>> checkResourceElementExistsInResource(@PathVariable long resourceId, @PathVariable String term) throws ResourcesExceptions {
		DaemonResponse<Boolean> response = new DaemonResponse<Boolean>(resourcesElementService.checkResourceElementExistsInResource(resourceId, term));
		return new ResponseEntity<DaemonResponse<Boolean>>(response, HttpStatus.OK);
	}

	/**
	 * Remove resource from class
	 * 
	 * 
	 * @param id
	 * @param classID
	 * @return
	 * @throws ResourcesExceptions
	 */
	@PreAuthorize("isAuthenticated() and hasPermission(#resourceId, T(pt.uminho.anote2.datastructures.dataaccess.database.dataaccess.implementation.utils.ResourcesTypeUtils).resources.getName(), @permissions.getWritegrant())")
	@RequestMapping(value = "/removeResourceClass/{resourceId}/{classId}", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<Boolean>> removeResourceClass(@PathVariable Long resourceId, @PathVariable Long classId) throws ResourcesExceptions {
		DaemonResponse<Boolean> response = new DaemonResponse<Boolean>(resourcesElementService.removeResourceClass(resourceId, classId));
		return new ResponseEntity<DaemonResponse<Boolean>>(response, HttpStatus.OK);
	}

	/**
	 * Add synonyms to resource element
	 * 
	 * 
	 * @param resourceId
	 * @param resourceElmentId
	 * @param newSynonyms
	 * @return
	 * @throws ResourcesExceptions
	 */
	@PreAuthorize("isAuthenticated() and hasPermission(#resourceId, T(pt.uminho.anote2.datastructures.dataaccess.database.dataaccess.implementation.utils.ResourcesTypeUtils).resources.getName(), @permissions.getWritegrant())")
	@RequestMapping(value = "/addResourceElementSynonyms/{resourceId}/{resourceElmentId}", method = RequestMethod.POST, consumes = { "application/json" })
	public ResponseEntity<DaemonResponse<IResourceManagerReport>> addResourceElementSynonyms(@PathVariable Long resourceId, @PathVariable Long resourceElmentId,
			@RequestBody List<String> newSynonyms) throws ResourcesExceptions {
		DaemonResponse<IResourceManagerReport> response = new DaemonResponse<IResourceManagerReport>(resourcesElementService.addResourceElementSynonyms(resourceId,
				resourceElmentId, newSynonyms));
		return new ResponseEntity<DaemonResponse<IResourceManagerReport>>(response, HttpStatus.OK);
	}

	/**
	 * Add external ids to resource element
	 * 
	 * 
	 * @param resourceId
	 * @param resourceElmentId
	 * @param externalIDs
	 * @return
	 * @throws ResourcesExceptions
	 */
	@PreAuthorize("isAuthenticated() and hasPermission(#resourceId, T(pt.uminho.anote2.datastructures.dataaccess.database.dataaccess.implementation.utils.ResourcesTypeUtils).resources.getName(), @permissions.getWritegrant())")
	@RequestMapping(value = "/addResourceElementSynonyms/{resourceId}/{resourceElmentId}", method = RequestMethod.POST, consumes = { "application/json" })
	public ResponseEntity<DaemonResponse<IResourceManagerReport>> addResourceElementExternalIDs(@PathVariable Long resourceId, @PathVariable Long resourceElmentId,
			@RequestBody List<ExternalIDImpl> externalIDs) throws ResourcesExceptions {
		List<IExternalID> externalIdsList = new ArrayList<IExternalID>();
		for (ExternalIDImpl id : externalIDs)
			externalIdsList.add(id);

		DaemonResponse<IResourceManagerReport> response = new DaemonResponse<IResourceManagerReport>(resourcesElementService.addResourceElementExternalIDs(resourceId,
				resourceElmentId, externalIdsList));
		return new ResponseEntity<DaemonResponse<IResourceManagerReport>>(response, HttpStatus.OK);
	}

	/**
	 * Update resource element
	 * 
	 * 
	 * @param elem
	 * @return
	 * @throws ResourcesExceptions
	 */
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/addResourceElementSynonyms", method = RequestMethod.PUT, consumes = { "application/json" })
	public ResponseEntity<DaemonResponse<IResourceManagerReport>> updateResourceElement(@RequestBody ResourceElementImpl elem) throws ResourcesExceptions {
		DaemonResponse<IResourceManagerReport> response = new DaemonResponse<IResourceManagerReport>(resourcesElementService.updateResourceElement(elem));
		return new ResponseEntity<DaemonResponse<IResourceManagerReport>>(response, HttpStatus.OK);
	}

	/**
	 * Inactive resource element
	 * 
	 * @param resourceElmentId
	 * @throws ResourcesExceptions
	 */
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/inactivateResourceElement/{resourceElmentId}", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<Boolean>> inactivateResourceElement(@PathVariable Long resourceElmentId) throws ResourcesExceptions {
		DaemonResponse<Boolean> response = new DaemonResponse<Boolean>(resourcesElementService.inactivateResourceElement(resourceElmentId));
		return new ResponseEntity<DaemonResponse<Boolean>>(response, HttpStatus.OK);
	}
}
