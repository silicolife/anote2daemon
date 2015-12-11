package com.silicolife.anote2daemon.controller.resources;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import pt.uminho.anote2.datastructures.dataaccess.database.dataaccess.implementation.exceptions.ResourcesExceptions;
import pt.uminho.anote2.datastructures.dataaccess.database.dataaccess.implementation.security.Permissions;
import pt.uminho.anote2.datastructures.dataaccess.database.dataaccess.implementation.service.resources.IResourcesElementService;
import pt.uminho.anote2.datastructures.general.ExternalIDImpl;
import pt.uminho.anote2.datastructures.resources.ResourceElementImpl;
import pt.uminho.anote2.interfaces.core.dataaccess.layer.resources.IResourceManagerReport;
import pt.uminho.anote2.interfaces.core.general.IExternalID;
import pt.uminho.anote2.interfaces.core.general.classe.IAnoteClass;
import pt.uminho.anote2.interfaces.resource.IResource;
import pt.uminho.anote2.interfaces.resource.IResourceElement;
import pt.uminho.anote2.interfaces.resource.IResourceElementSet;
import pt.uminho.anote2.interfaces.resource.content.IResourceContent;
import pt.uminho.anote2.interfaces.resource.ontologies.IResourceElementsRelation;

import com.silicolife.anote2daemon.security.RestPermissionsEvaluatorEnum;
import com.silicolife.anote2daemon.utils.GenericPairSpringSpel;
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
	private GenericPairSpringSpel<RestPermissionsEvaluatorEnum, List<String>> genericPairSpringSpel;
	@Autowired
	private IResourcesElementService resourcesElementService;

	/**
	 * Add resource element
	 * 
	 * 
	 * @param resourceId
	 * @param elem
	 * @return
	 * @throws ResourcesExceptions
	 */
	@PreAuthorize("isAuthenticated() and hasPermission(#resourceId, "
			+ "T(pt.uminho.anote2.datastructures.dataaccess.database.dataaccess.implementation.utils.ResourcesTypeUtils).resources.getName(),"
			+ "@genericPairSpringSpel.getGenericPairSpringSpel(T(com.silicolife.anote2daemon.security.RestPermissionsEvaluatorEnum).default_,@permissions.getWritegrant()))")
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
	@PreAuthorize("isAuthenticated() and hasPermission(#resourceId, "
			+ "T(pt.uminho.anote2.datastructures.dataaccess.database.dataaccess.implementation.utils.ResourcesTypeUtils).resources.getName(),"
			+ "@genericPairSpringSpel.getGenericPairSpringSpel(T(com.silicolife.anote2daemon.security.RestPermissionsEvaluatorEnum).default_,@permissions.getFullgrant()))")
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
	@PreAuthorize("isAuthenticated() and hasPermission(#resourceId, "
			+ "T(pt.uminho.anote2.datastructures.dataaccess.database.dataaccess.implementation.utils.ResourcesTypeUtils).resources.getName(),"
			+ "@genericPairSpringSpel.getGenericPairSpringSpel(T(com.silicolife.anote2daemon.security.RestPermissionsEvaluatorEnum).default_,@permissions.getFullgrant()))")
	@RequestMapping(value = "/getResourceElementsByClass", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<IResourceElementSet<IResourceElement>>> getResourceElementsByClass(@RequestParam Long resourceId, @RequestParam String termClass)
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
	@PreAuthorize("isAuthenticated() and hasPermission(#resourceId, "
			+ "T(pt.uminho.anote2.datastructures.dataaccess.database.dataaccess.implementation.utils.ResourcesTypeUtils).resources.getName(),"
			+ "@genericPairSpringSpel.getGenericPairSpringSpel(T(com.silicolife.anote2daemon.security.RestPermissionsEvaluatorEnum).default_,@permissions.getFullgrant()))")
	@RequestMapping(value = "/getResourceContent/{resourceId}", method = RequestMethod.GET)
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
	@PreAuthorize("isAuthenticated() and hasPermission(#resourceId, "
			+ "T(pt.uminho.anote2.datastructures.dataaccess.database.dataaccess.implementation.utils.ResourcesTypeUtils).resources.getName(),"
			+ "@genericPairSpringSpel.getGenericPairSpringSpel(T(com.silicolife.anote2daemon.security.RestPermissionsEvaluatorEnum).default_,@permissions.getFullgrant()))")
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
	 * @throws ResourcesExceptions 
	 */
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/getResourceElementExternalIds/{resourceElementId}", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<List<IExternalID>>> getResourceElementExternalIds(@PathVariable Long resourceElementId) throws ResourcesExceptions {
		DaemonResponse<List<IExternalID>> response = new DaemonResponse<List<IExternalID>>(resourcesElementService.getResourceElementExternalIDs(resourceElementId));
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
	@PreAuthorize("isAuthenticated() and hasPermission(#resourceId, "
			+ "T(pt.uminho.anote2.datastructures.dataaccess.database.dataaccess.implementation.utils.ResourcesTypeUtils).resources.getName(),"
			+ "@genericPairSpringSpel.getGenericPairSpringSpel(T(com.silicolife.anote2daemon.security.RestPermissionsEvaluatorEnum).default_,@permissions.getWritegrant()))")
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
	@PreAuthorize("isAuthenticated() and hasPermission(#resourceId, "
			+ "T(pt.uminho.anote2.datastructures.dataaccess.database.dataaccess.implementation.utils.ResourcesTypeUtils).resources.getName(),"
			+ "@genericPairSpringSpel.getGenericPairSpringSpel(T(com.silicolife.anote2daemon.security.RestPermissionsEvaluatorEnum).default_,@permissions.getFullgrant()))")
	@RequestMapping(value = "/checkResourceElementExistsInResource", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<Boolean>> checkResourceElementExistsInResource(@RequestParam long resourceId, @RequestParam String term) throws ResourcesExceptions {
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
	@PreAuthorize("isAuthenticated() and hasPermission(#resourceId, "
			+ "T(pt.uminho.anote2.datastructures.dataaccess.database.dataaccess.implementation.utils.ResourcesTypeUtils).resources.getName(),"
			+ "@genericPairSpringSpel.getGenericPairSpringSpel(T(com.silicolife.anote2daemon.security.RestPermissionsEvaluatorEnum).default_,@permissions.getWritegrant()))")
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
	@PreAuthorize("isAuthenticated() and hasPermission(#resourceId, "
			+ "T(pt.uminho.anote2.datastructures.dataaccess.database.dataaccess.implementation.utils.ResourcesTypeUtils).resources.getName(),"
			+ "@genericPairSpringSpel.getGenericPairSpringSpel(T(com.silicolife.anote2daemon.security.RestPermissionsEvaluatorEnum).default_,@permissions.getWritegrant()))")
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
	@PreAuthorize("isAuthenticated() and hasPermission(#resourceId, "
			+ "T(pt.uminho.anote2.datastructures.dataaccess.database.dataaccess.implementation.utils.ResourcesTypeUtils).resources.getName(),"
			+ "@genericPairSpringSpel.getGenericPairSpringSpel(T(com.silicolife.anote2daemon.security.RestPermissionsEvaluatorEnum).default_,@permissions.getWritegrant()))")
	@RequestMapping(value = "/addResourceElementExternalIDs/{resourceId}/{resourceElmentId}", method = RequestMethod.POST, consumes = { "application/json" })
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
	@PreAuthorize("isAuthenticated() and hasPermission(#elem.getId(), "
			+ "T(pt.uminho.anote2.datastructures.dataaccess.database.dataaccess.implementation.utils.ResourcesTypeUtils).resources.getName(), "
			+ "@genericPairSpringSpel.getGenericPairSpringSpel(T(com.silicolife.anote2daemon.security.RestPermissionsEvaluatorEnum).resourceByResourceElement, @permissions.getWritegrant()))")
	@RequestMapping(value = "/updateResourceElement", method = RequestMethod.PUT, consumes = { "application/json" })
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
	@PreAuthorize("isAuthenticated() and hasPermission(#resourceElmentId, "
			+ "T(pt.uminho.anote2.datastructures.dataaccess.database.dataaccess.implementation.utils.ResourcesTypeUtils).resources.getName(), "
			+ "@genericPairSpringSpel.getGenericPairSpringSpel(T(com.silicolife.anote2daemon.security.RestPermissionsEvaluatorEnum).resourceByResourceElement, @permissions.getWritegrant()))")
	@RequestMapping(value = "/inactivateResourceElement", method = RequestMethod.POST)
	public ResponseEntity<DaemonResponse<Boolean>> inactivateResourceElement(@RequestParam Long resourceElmentId) throws ResourcesExceptions {
		DaemonResponse<Boolean> response = new DaemonResponse<Boolean>(resourcesElementService.inactivateResourceElement(resourceElmentId));
		return new ResponseEntity<DaemonResponse<Boolean>>(response, HttpStatus.OK);
	}
	
	
	/**
	 * Update synonyms from a resource element
	 * 
	 * @param resourceId
	 * @param resourceElmentId
	 * @param oldSynonym
	 * @param newSynonym
	 * @return
	 * @throws ResourcesExceptions
	 */
	@PreAuthorize("isAuthenticated() and hasPermission(#resourceId, "
			+ "T(pt.uminho.anote2.datastructures.dataaccess.database.dataaccess.implementation.utils.ResourcesTypeUtils).resources.getName(), "
			+ "@genericPairSpringSpel.getGenericPairSpringSpel(T(com.silicolife.anote2daemon.security.RestPermissionsEvaluatorEnum).default_, @permissions.getWritegrant()))")
	@RequestMapping(value = "/updateResourceElementSynonym", method = RequestMethod.POST)
	public ResponseEntity<DaemonResponse<IResourceManagerReport>>  updateResourceElementSynonym(@RequestParam Long resourceId, @RequestParam Long resourceElmentId, @RequestParam String oldSynonym, @RequestParam String newSynonym) throws ResourcesExceptions{
		DaemonResponse<IResourceManagerReport> response = new DaemonResponse<IResourceManagerReport>(resourcesElementService.updateResourceElementSynonym(resourceId, resourceElmentId, oldSynonym, newSynonym));
		return new ResponseEntity<DaemonResponse<IResourceManagerReport>>(response, HttpStatus.OK);	
	}
	
	/**
	 * Remove Resource element synonym
	 * 
	 * @param resourceElmentID
	 * @param synonym
	 * @return
	 * @throws ResourcesExceptions
	 */
	@PreAuthorize("isAuthenticated() and hasPermission(#resourceElmentId, "
			+ "T(pt.uminho.anote2.datastructures.dataaccess.database.dataaccess.implementation.utils.ResourcesTypeUtils).resources.getName(), "
			+ "@genericPairSpringSpel.getGenericPairSpringSpel(T(com.silicolife.anote2daemon.security.RestPermissionsEvaluatorEnum).resourceByResourceElement, @permissions.getWritegrant()))")
	@RequestMapping(value = "/removeResourceElementSynonym", method = RequestMethod.POST)
	public ResponseEntity<DaemonResponse<Boolean>> removeResourceElementSynonym(@RequestParam Long resourceElmentId, @RequestParam String synonym) throws ResourcesExceptions{
		DaemonResponse<Boolean> response = new DaemonResponse<Boolean>(resourcesElementService.removeResourceElementSynonym(resourceElmentId, synonym));
		return new ResponseEntity<DaemonResponse<Boolean>>(response, HttpStatus.OK);	
	}
	
	
	/**
	 * Remove resource element external id
	 * 
	 * @param resourceElmentId
	 * @param extID
	 * @return
	 * @throws ResourcesExceptions
	 */
	@PreAuthorize("isAuthenticated() and hasPermission(#resourceElmentId, "
			+ "T(pt.uminho.anote2.datastructures.dataaccess.database.dataaccess.implementation.utils.ResourcesTypeUtils).resources.getName(), "
			+ "@genericPairSpringSpel.getGenericPairSpringSpel(T(com.silicolife.anote2daemon.security.RestPermissionsEvaluatorEnum).resourceByResourceElement, @permissions.getWritegrant()))")
	@RequestMapping(value = "/removeResourceElementExternalId/{resourceElmentId}", method = RequestMethod.POST)
	public ResponseEntity<DaemonResponse<Boolean>> removeResourceElementExternalID(@PathVariable Long resourceElmentId, @RequestBody ExternalIDImpl extID) throws ResourcesExceptions{
		DaemonResponse<Boolean> response = new DaemonResponse<Boolean>(resourcesElementService.removeResourceElementExternalID(resourceElmentId, extID));
		return new ResponseEntity<DaemonResponse<Boolean>>(response, HttpStatus.OK);	
	}
	
	/**
	 * Get resources max priority
	 * 
	 * @param resourceElmentId
	 * @return
	 * @throws ResourcesExceptions
	 */
	@PreAuthorize("isAuthenticated()and hasPermission(#resourceId, "
			+ "T(pt.uminho.anote2.datastructures.dataaccess.database.dataaccess.implementation.utils.ResourcesTypeUtils).resources.getName(), "
			+ "@genericPairSpringSpel.getGenericPairSpringSpel(T(com.silicolife.anote2daemon.security.RestPermissionsEvaluatorEnum).default_, @permissions.getFullgrant()))")
	@RequestMapping(value = "/getResourceMaxPriorety/{resourceId}", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<Integer>> getResourceMaxPriorety(@PathVariable Long resourceId)throws ResourcesExceptions{	
		DaemonResponse<Integer> response = new DaemonResponse<Integer>(resourcesElementService.getResourceMaxPriorety(resourceId));
		return new ResponseEntity<DaemonResponse<Integer>>(response, HttpStatus.OK);	
	}

	
	/**
	 * add resources elements relation
	 * 
	 * @param resourceElmentIDa
	 * @param resourceElmentIDb
	 * @param relationType
	 * @return
	 * @throws ResourcesExceptions
	 */
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/addResourceElementsRelation", method = RequestMethod.POST)
	public ResponseEntity<DaemonResponse<Boolean>> addResourceElementsRelation(@RequestParam Long resourceElmentIda, @RequestParam Long resourceElmentIdb, @RequestParam String relationType) throws ResourcesExceptions{
		DaemonResponse<Boolean> response = new DaemonResponse<Boolean>(resourcesElementService.addResourceElementsRelation(resourceElmentIda, resourceElmentIdb, relationType));
		return new ResponseEntity<DaemonResponse<Boolean>>(response, HttpStatus.OK);	
	}
	
	/**
	 * Get relations from a resource
	 * 
	 * @param resourceId
	 * @return
	 * @throws ResourcesExceptions
	 */
	@PreAuthorize("isAuthenticated() and hasPermission(#resourceId, "
			+ "T(pt.uminho.anote2.datastructures.dataaccess.database.dataaccess.implementation.utils.ResourcesTypeUtils).resources.getName(), "
			+ "@genericPairSpringSpel.getGenericPairSpringSpel(T(com.silicolife.anote2daemon.security.RestPermissionsEvaluatorEnum).default_, @permissions.getFullgrant()))")
	@RequestMapping(value = "/getResourceElementsRelations/{resourceId}", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<List<IResourceElementsRelation>>> getResourceElementsRelations(@PathVariable Long resourceId) throws ResourcesExceptions{
		DaemonResponse<List<IResourceElementsRelation>> response = new DaemonResponse<List<IResourceElementsRelation>>(resourcesElementService.getResourceElementsRelations(resourceId));
		return new ResponseEntity<DaemonResponse<List<IResourceElementsRelation>>>(response, HttpStatus.OK);	
	}


	/**
	 * Get resource from a resource element
	 * 
	 * @param resourceElementId
	 * @return
	 * @throws ResourcesExceptions
	 */
	@PreAuthorize("isAuthenticated() and hasPermission(#resourceElementId, "
			+ "T(pt.uminho.anote2.datastructures.dataaccess.database.dataaccess.implementation.utils.ResourcesTypeUtils).resources.getName(), "
			+ "@genericPairSpringSpel.getGenericPairSpringSpel(T(com.silicolife.anote2daemon.security.RestPermissionsEvaluatorEnum).resourceByResourceElement, @permissions.getFullgrant()))")
	@RequestMapping(value = "/getResourceFromResourceElement/{resourceElementId}", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<IResource<IResourceElement>>> getResourceFromResourceElement(@PathVariable Long resourceElementId) throws ResourcesExceptions{
		DaemonResponse<IResource<IResourceElement>> response = new DaemonResponse<IResource<IResourceElement>>(resourcesElementService.getResourceFromResourceElement(resourceElementId));
		return new ResponseEntity<DaemonResponse<IResource<IResourceElement>>>(response, HttpStatus.OK);	
	}

	/**
	 * Get resource element by id
	 * 
	 * @param resourceElementId
	 * @return
	 * @throws ResourcesExceptions
	 */
	@PreAuthorize("isAuthenticated() and hasPermission(#resourceElementId, "
			+ "T(pt.uminho.anote2.datastructures.dataaccess.database.dataaccess.implementation.utils.ResourcesTypeUtils).resources.getName(), "
			+ "@genericPairSpringSpel.getGenericPairSpringSpel(T(com.silicolife.anote2daemon.security.RestPermissionsEvaluatorEnum).resourceByResourceElement, @permissions.getFullgrant()))")
	@RequestMapping(value = "/getResourceElemenById/{resourceElementId}", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<IResourceElement>> getResourceElemenByID(@PathVariable Long resourceElementId) throws ResourcesExceptions{
		DaemonResponse<IResourceElement> response = new DaemonResponse<IResourceElement>(resourcesElementService.getResourceElemenByID(resourceElementId));
		return new ResponseEntity<DaemonResponse<IResourceElement>>(response, HttpStatus.OK);	
	}
	
	
	
	/**
	 * Get resources elements by name
	 * 
	 * @param id
	 * @param name
	 * @return
	 * @throws ResourcesExceptions
	 */
	@PreAuthorize("isAuthenticated() and hasPermission(#resourceId, "
			+ "T(pt.uminho.anote2.datastructures.dataaccess.database.dataaccess.implementation.utils.ResourcesTypeUtils).resources.getName(), "
			+ "@genericPairSpringSpel.getGenericPairSpringSpel(T(com.silicolife.anote2daemon.security.RestPermissionsEvaluatorEnum).default_, @permissions.getFullgrant()))")
	@RequestMapping(value = "/getResourceElementsByName", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<IResourceElementSet<IResourceElement>>> getResourceElementsByName(@RequestParam Long resourceId, @RequestParam String name) throws ResourcesExceptions{
		DaemonResponse<IResourceElementSet<IResourceElement>> response = new DaemonResponse<IResourceElementSet<IResourceElement>>(resourcesElementService.getResourceElementsByName(resourceId, name));
		return new ResponseEntity<DaemonResponse<IResourceElementSet<IResourceElement>>>(response, HttpStatus.OK);	
	}
	
	
	/**
	 * remove all external ids from a resource
	 * 
	 * @param resourceElmentId
	 * @return
	 * @throws ResourcesExceptions
	 */
	@PreAuthorize("isAuthenticated() and hasPermission(#resourceId, "
			+ "T(pt.uminho.anote2.datastructures.dataaccess.database.dataaccess.implementation.utils.ResourcesTypeUtils).resources.getName(), "
			+ "@genericPairSpringSpel.getGenericPairSpringSpel(T(com.silicolife.anote2daemon.security.RestPermissionsEvaluatorEnum).resourceByResourceElement, @permissions.getWritegrant()))")
	@RequestMapping(value = "/removeResourceElementAllExternalID", method = RequestMethod.POST)
	public ResponseEntity<DaemonResponse<Boolean>> removeResourceElementAllExternalID(@RequestParam Long resourceElmentId) throws ResourcesExceptions{
		DaemonResponse<Boolean> response = new DaemonResponse<Boolean>(resourcesElementService.removeResourceElementAllExternalID(resourceElmentId));
		return new ResponseEntity<DaemonResponse<Boolean>>(response, HttpStatus.OK);	
	}

	/**
	 * Remove a synonym
	 * 
	 * @param resourceElmentId
	 * @return
	 * @throws ResourcesExceptions
	 */
	@PreAuthorize("isAuthenticated() and hasPermission(#resourceElmentId, "
			+ "T(pt.uminho.anote2.datastructures.dataaccess.database.dataaccess.implementation.utils.ResourcesTypeUtils).resources.getName(), "
			+ "@genericPairSpringSpel.getGenericPairSpringSpel(T(com.silicolife.anote2daemon.security.RestPermissionsEvaluatorEnum).resourceByResourceElement, @permissions.getWritegrant()))")
	@RequestMapping(value = "/removeResourceElementSynonyms", method = RequestMethod.POST)
	public ResponseEntity<DaemonResponse<Boolean>> removeResourceElementSynonyms(@RequestParam Long resourceElmentId) throws ResourcesExceptions{
		DaemonResponse<Boolean> response = new DaemonResponse<Boolean>(resourcesElementService.removeResourceElementSynonyms(resourceElmentId));
		return new ResponseEntity<DaemonResponse<Boolean>>(response, HttpStatus.OK);	
	}
}
