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

import com.silicolife.anote2daemon.security.RestPermissionsEvaluatorEnum;
import com.silicolife.anote2daemon.utils.GenericPairSpringSpel;
import com.silicolife.anote2daemon.webservice.DaemonResponse;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.exceptions.ResourcesExceptions;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.lucene.service.resources.IResourcesElementLuceneService;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.security.Permissions;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.service.resources.IResourcesElementService;
import com.silicolife.textmining.core.datastructures.general.ExternalIDImpl;
import com.silicolife.textmining.core.datastructures.resources.ResourceElementImpl;
import com.silicolife.textmining.core.interfaces.core.dataaccess.layer.resources.IResourceManagerReport;
import com.silicolife.textmining.core.interfaces.core.general.IExternalID;
import com.silicolife.textmining.core.interfaces.core.general.classe.IAnoteClass;
import com.silicolife.textmining.core.interfaces.resource.IResource;
import com.silicolife.textmining.core.interfaces.resource.IResourceElement;
import com.silicolife.textmining.core.interfaces.resource.IResourceElementSet;
import com.silicolife.textmining.core.interfaces.resource.content.IResourceContent;
import com.silicolife.textmining.core.interfaces.resource.ontologies.IResourceElementsRelation;

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
	@Autowired
	private IResourcesElementLuceneService resourcesElementLuceneService;

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
			+ "T(com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.utils.ResourcesTypeUtils).resources.getName(),"
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
			+ "T(com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.utils.ResourcesTypeUtils).resources.getName(),"
			+ "@genericPairSpringSpel.getGenericPairSpringSpel(T(com.silicolife.anote2daemon.security.RestPermissionsEvaluatorEnum).default_,@permissions.getFullgrant()))")
	@RequestMapping(value = "/getResourceElements/{resourceId}", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<IResourceElementSet<IResourceElement>>> getResourceElements(@PathVariable Long resourceId) throws ResourcesExceptions {
		DaemonResponse<IResourceElementSet<IResourceElement>> response = new DaemonResponse<IResourceElementSet<IResourceElement>>(
				resourcesElementService.getResourceElements(resourceId));
		return new ResponseEntity<DaemonResponse<IResourceElementSet<IResourceElement>>>(response, HttpStatus.OK);
	}
	
	/**
	 * Get resource elements by resource with limit
	 * 
	 * @param resourceId
	 * @return
	 * @throws ResourcesExceptions
	 */
	@PreAuthorize("isAuthenticated() and hasPermission(#resourceId, "
			+ "T(com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.utils.ResourcesTypeUtils).resources.getName(),"
			+ "@genericPairSpringSpel.getGenericPairSpringSpel(T(com.silicolife.anote2daemon.security.RestPermissionsEvaluatorEnum).default_,@permissions.getFullgrant()))")
	@RequestMapping(value = "/getResourceElementsInBatchWithLimit", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<IResourceElementSet<IResourceElement>>> getResourceElementsInBatchWithLimit(@RequestParam Long resourceId, @RequestParam Integer index, @RequestParam Integer pagination) throws ResourcesExceptions {
		DaemonResponse<IResourceElementSet<IResourceElement>> response = new DaemonResponse<IResourceElementSet<IResourceElement>>(
				resourcesElementService.getResourceElementsInBatchWithLimit(resourceId, index, pagination));
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
			+ "T(com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.utils.ResourcesTypeUtils).resources.getName(),"
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
			+ "T(com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.utils.ResourcesTypeUtils).resources.getName(),"
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
			+ "T(com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.utils.ResourcesTypeUtils).resources.getName(),"
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
			+ "T(com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.utils.ResourcesTypeUtils).resources.getName(),"
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
			+ "T(com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.utils.ResourcesTypeUtils).resources.getName(),"
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
			+ "T(com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.utils.ResourcesTypeUtils).resources.getName(),"
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
			+ "T(com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.utils.ResourcesTypeUtils).resources.getName(),"
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
			+ "T(com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.utils.ResourcesTypeUtils).resources.getName(),"
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
			+ "T(com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.utils.ResourcesTypeUtils).resources.getName(), "
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
			+ "T(com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.utils.ResourcesTypeUtils).resources.getName(), "
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
			+ "T(com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.utils.ResourcesTypeUtils).resources.getName(), "
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
			+ "T(com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.utils.ResourcesTypeUtils).resources.getName(), "
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
			+ "T(com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.utils.ResourcesTypeUtils).resources.getName(), "
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
			+ "T(com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.utils.ResourcesTypeUtils).resources.getName(), "
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
			+ "T(com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.utils.ResourcesTypeUtils).resources.getName(), "
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
			+ "T(com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.utils.ResourcesTypeUtils).resources.getName(), "
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
			+ "T(com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.utils.ResourcesTypeUtils).resources.getName(), "
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
			+ "T(com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.utils.ResourcesTypeUtils).resources.getName(), "
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
			+ "T(com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.utils.ResourcesTypeUtils).resources.getName(), "
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
			+ "T(com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.utils.ResourcesTypeUtils).resources.getName(), "
			+ "@genericPairSpringSpel.getGenericPairSpringSpel(T(com.silicolife.anote2daemon.security.RestPermissionsEvaluatorEnum).resourceByResourceElement, @permissions.getWritegrant()))")
	@RequestMapping(value = "/removeResourceElementSynonyms", method = RequestMethod.POST)
	public ResponseEntity<DaemonResponse<Boolean>> removeResourceElementSynonyms(@RequestParam Long resourceElmentId) throws ResourcesExceptions{
		DaemonResponse<Boolean> response = new DaemonResponse<Boolean>(resourcesElementService.removeResourceElementSynonyms(resourceElmentId));
		return new ResponseEntity<DaemonResponse<Boolean>>(response, HttpStatus.OK);	
	}
	
	//lucene
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/getResourceElementsByExactTerm", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<IResourceElementSet<IResourceElement>>> getResourceElementsByExactTerm(@RequestParam String term) throws ResourcesExceptions{
		DaemonResponse<IResourceElementSet<IResourceElement>> response = new DaemonResponse<IResourceElementSet<IResourceElement>>(resourcesElementLuceneService.getResourceElementsByExactTerm(term));
		return new ResponseEntity<DaemonResponse<IResourceElementSet<IResourceElement>>>(response, HttpStatus.OK);	
	}
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/getResourceElementsFromResourceByExactTerm", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<IResourceElementSet<IResourceElement>>> getResourceElementsFromResourceByExactTerm(@RequestParam Long resourceId, @RequestParam String term) throws ResourcesExceptions{
		DaemonResponse<IResourceElementSet<IResourceElement>> response = new DaemonResponse<IResourceElementSet<IResourceElement>>(resourcesElementLuceneService.getResourceElementsFromResourceByExactTerm(term, resourceId));
		return new ResponseEntity<DaemonResponse<IResourceElementSet<IResourceElement>>>(response, HttpStatus.OK);	
	}
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/getResourceElementsByPartialTerm", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<IResourceElementSet<IResourceElement>>> getResourceElementsByPartialTerm(@RequestParam String partialTerm) throws ResourcesExceptions{
		DaemonResponse<IResourceElementSet<IResourceElement>> response = new DaemonResponse<IResourceElementSet<IResourceElement>>(resourcesElementLuceneService.getResourceElementsByPartialTerm(partialTerm));
		return new ResponseEntity<DaemonResponse<IResourceElementSet<IResourceElement>>>(response, HttpStatus.OK);	
	}
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/getResourceElementsFromResourceByPartialTerm", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<IResourceElementSet<IResourceElement>>> getResourceElementsFromResourceByPartialTerm(@RequestParam Long resourceId, @RequestParam String partialTerm) throws ResourcesExceptions{
		DaemonResponse<IResourceElementSet<IResourceElement>> response = new DaemonResponse<IResourceElementSet<IResourceElement>>(resourcesElementLuceneService.getResourceElementsFromResourceByPartialTerm(partialTerm, resourceId));
		return new ResponseEntity<DaemonResponse<IResourceElementSet<IResourceElement>>>(response, HttpStatus.OK);	
	}
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/getResourceElementsByPartialTermPaginated", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<IResourceElementSet<IResourceElement>>> getResourceElementsByPartialTermPaginated(@RequestParam String partialTerm, @RequestParam Integer index, @RequestParam Integer paginationSize) throws ResourcesExceptions{
		DaemonResponse<IResourceElementSet<IResourceElement>> response = new DaemonResponse<IResourceElementSet<IResourceElement>>(resourcesElementLuceneService.getResourceElementsByPartialTermPaginated(partialTerm, index, paginationSize));
		return new ResponseEntity<DaemonResponse<IResourceElementSet<IResourceElement>>>(response, HttpStatus.OK);	
	}
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/getResourceElementsFromResourceByPartialTermPaginated", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<IResourceElementSet<IResourceElement>>> getResourceElementsFromResourceByPartialTermPaginated(@RequestParam Long resourceId, @RequestParam String partialTerm, @RequestParam int index, @RequestParam int paginationSize) throws ResourcesExceptions{
		DaemonResponse<IResourceElementSet<IResourceElement>> response = new DaemonResponse<IResourceElementSet<IResourceElement>>(resourcesElementLuceneService.getResourceElementsFromResourceByPartialTermPaginated(partialTerm, resourceId, index, paginationSize));
		return new ResponseEntity<DaemonResponse<IResourceElementSet<IResourceElement>>>(response, HttpStatus.OK);	
	}
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/getResourceElementsByExactSynonym", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<IResourceElementSet<IResourceElement>>> getResourceElementsByExactSynonym(@RequestParam String synonym) throws ResourcesExceptions{
		DaemonResponse<IResourceElementSet<IResourceElement>> response = new DaemonResponse<IResourceElementSet<IResourceElement>>(resourcesElementLuceneService.getResourceElementsByExactSynonym(synonym));
		return new ResponseEntity<DaemonResponse<IResourceElementSet<IResourceElement>>>(response, HttpStatus.OK);	
	}
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/getResourceElementsFromResourceByExactSynonym", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<IResourceElementSet<IResourceElement>>> getResourceElementsFromResourceByExactSynonym(@RequestParam Long resourceId, @RequestParam String synonym) throws ResourcesExceptions{
		DaemonResponse<IResourceElementSet<IResourceElement>> response = new DaemonResponse<IResourceElementSet<IResourceElement>>(resourcesElementLuceneService.getResourceElementsFromResourceByExactSynonym(synonym, resourceId));
		return new ResponseEntity<DaemonResponse<IResourceElementSet<IResourceElement>>>(response, HttpStatus.OK);	
	}
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/getResourceElementsByPartialSynonym", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<IResourceElementSet<IResourceElement>>> getResourceElementsByPartialSynonym(@RequestParam String partialSynonym) throws ResourcesExceptions{
		DaemonResponse<IResourceElementSet<IResourceElement>> response = new DaemonResponse<IResourceElementSet<IResourceElement>>(resourcesElementLuceneService.getResourceElementsByPartialSynonym(partialSynonym));
		return new ResponseEntity<DaemonResponse<IResourceElementSet<IResourceElement>>>(response, HttpStatus.OK);	
	}
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/getResourceElementsFromResourceByPartialSynonym", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<IResourceElementSet<IResourceElement>>> getResourceElementsFromResourceByPartialSynonym(@RequestParam Long resourceId, @RequestParam String partialSynonym) throws ResourcesExceptions{
		DaemonResponse<IResourceElementSet<IResourceElement>> response = new DaemonResponse<IResourceElementSet<IResourceElement>>(resourcesElementLuceneService.getResourceElementsFromResourceByPartialSynonym(partialSynonym, resourceId));
		return new ResponseEntity<DaemonResponse<IResourceElementSet<IResourceElement>>>(response, HttpStatus.OK);	
	}
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/getResourceElementsByPartialSynonymPaginated", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<IResourceElementSet<IResourceElement>>> getResourceElementsByPartialSynonymPaginated(@RequestParam String partialSynonym, @RequestParam Integer index, @RequestParam Integer paginationSize) throws ResourcesExceptions{
		DaemonResponse<IResourceElementSet<IResourceElement>> response = new DaemonResponse<IResourceElementSet<IResourceElement>>(resourcesElementLuceneService.getResourceElementsByPartialSynonymPaginated(partialSynonym, index, paginationSize));
		return new ResponseEntity<DaemonResponse<IResourceElementSet<IResourceElement>>>(response, HttpStatus.OK);	
	}
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/getResourceElementsFromResourceByPartialSynonymPaginated", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<IResourceElementSet<IResourceElement>>> getResourceElementsFromResourceByPartialSynonymPaginated(@RequestParam String partialSynonym, @RequestParam Long resourceId, @RequestParam Integer index, @RequestParam Integer paginationSize) throws ResourcesExceptions{
		DaemonResponse<IResourceElementSet<IResourceElement>> response = new DaemonResponse<IResourceElementSet<IResourceElement>>(resourcesElementLuceneService.getResourceElementsFromResourceByPartialSynonymPaginated(partialSynonym, resourceId, index, paginationSize));
		return new ResponseEntity<DaemonResponse<IResourceElementSet<IResourceElement>>>(response, HttpStatus.OK);	
	}
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/getResourceElementsByExactExternalID", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<IResourceElementSet<IResourceElement>>> getResourceElementsByExactExternalID(@RequestParam String externalId) throws ResourcesExceptions{
		DaemonResponse<IResourceElementSet<IResourceElement>> response = new DaemonResponse<IResourceElementSet<IResourceElement>>(resourcesElementLuceneService.getResourceElementsByExactExternalID(externalId));
		return new ResponseEntity<DaemonResponse<IResourceElementSet<IResourceElement>>>(response, HttpStatus.OK);	
	}
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/getResourceElementsFromSourceByExactExternalID", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<IResourceElementSet<IResourceElement>>> getResourceElementsFromSourceByExactExternalID(@RequestParam String externalId, @RequestParam Long sourceId) throws ResourcesExceptions{
		DaemonResponse<IResourceElementSet<IResourceElement>> response = new DaemonResponse<IResourceElementSet<IResourceElement>>(resourcesElementLuceneService.getResourceElementsFromSourceByExactExternalID(externalId, sourceId));
		return new ResponseEntity<DaemonResponse<IResourceElementSet<IResourceElement>>>(response, HttpStatus.OK);	
	}
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/getResourceElementsFromResourceByExactExternalID", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<IResourceElementSet<IResourceElement>>> getResourceElementsFromResourceByExactExternalID(@RequestParam String externalId, @RequestParam Long resourceId) throws ResourcesExceptions{
		DaemonResponse<IResourceElementSet<IResourceElement>> response = new DaemonResponse<IResourceElementSet<IResourceElement>>(resourcesElementLuceneService.getResourceElementsFromResourceByExactExternalID(externalId, resourceId));
		return new ResponseEntity<DaemonResponse<IResourceElementSet<IResourceElement>>>(response, HttpStatus.OK);	
	}
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/getResourceElementsFromResourceAndSourceByExactExternalID", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<IResourceElementSet<IResourceElement>>> getResourceElementsFromResourceAndSourceByExactExternalID(@RequestParam Long resourceId, @RequestParam Long sourceId, @RequestParam String externalId) throws ResourcesExceptions{
		DaemonResponse<IResourceElementSet<IResourceElement>> response = new DaemonResponse<IResourceElementSet<IResourceElement>>(resourcesElementLuceneService.getResourceElementsFromResourceAndSourceByExactExternalID(externalId, sourceId, resourceId));
		return new ResponseEntity<DaemonResponse<IResourceElementSet<IResourceElement>>>(response, HttpStatus.OK);	
	}
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/getResourceElementsByPartialExternalID", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<IResourceElementSet<IResourceElement>>> getResourceElementsByPartialExternalID(@RequestParam String externalId) throws ResourcesExceptions{
		DaemonResponse<IResourceElementSet<IResourceElement>> response = new DaemonResponse<IResourceElementSet<IResourceElement>>(resourcesElementLuceneService.getResourceElementsByPartialExternalID(externalId));
		return new ResponseEntity<DaemonResponse<IResourceElementSet<IResourceElement>>>(response, HttpStatus.OK);	
	}
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/getResourceElementsFromSourceByPartialExternalID", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<IResourceElementSet<IResourceElement>>> getResourceElementsFromSourceByPartialExternalID(@RequestParam String externalId, @RequestParam Long sourceId) throws ResourcesExceptions{
		DaemonResponse<IResourceElementSet<IResourceElement>> response = new DaemonResponse<IResourceElementSet<IResourceElement>>(resourcesElementLuceneService.getResourceElementsFromSourceByPartialExternalID(externalId, sourceId));
		return new ResponseEntity<DaemonResponse<IResourceElementSet<IResourceElement>>>(response, HttpStatus.OK);	
	}
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/getResourceElementsFromResourceByPartialExternalID", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<IResourceElementSet<IResourceElement>>> getResourceElementsFromResourceByPartialExternalID(@RequestParam String externalId, @RequestParam Long resourceId) throws ResourcesExceptions{
		DaemonResponse<IResourceElementSet<IResourceElement>> response = new DaemonResponse<IResourceElementSet<IResourceElement>>(resourcesElementLuceneService.getResourceElementsFromResourceByPartialExternalID(externalId, resourceId));
		return new ResponseEntity<DaemonResponse<IResourceElementSet<IResourceElement>>>(response, HttpStatus.OK);	
	}
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/getResourceElementsFromResourceAndSourceByPartialExternalID", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<IResourceElementSet<IResourceElement>>> getResourceElementsFromResourceAndSourceByPartialExternalID(@RequestParam Long resourceId, @RequestParam Long sourceId, @RequestParam String externalId) throws ResourcesExceptions{
		DaemonResponse<IResourceElementSet<IResourceElement>> response = new DaemonResponse<IResourceElementSet<IResourceElement>>(resourcesElementLuceneService.getResourceElementsFromResourceAndSourceByPartialExternalID(externalId, sourceId, resourceId));
		return new ResponseEntity<DaemonResponse<IResourceElementSet<IResourceElement>>>(response, HttpStatus.OK);	
	}
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/getResourceElementsByPartialExternalIDPaginated", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<IResourceElementSet<IResourceElement>>> getResourceElementsByPartialExternalIDPaginated(@RequestParam String partialExternalId, @RequestParam Integer index, @RequestParam Integer paginationSize) throws ResourcesExceptions{
		DaemonResponse<IResourceElementSet<IResourceElement>> response = new DaemonResponse<IResourceElementSet<IResourceElement>>(resourcesElementLuceneService.getResourceElementsByPartialExternalIDPaginated(partialExternalId, index, paginationSize));
		return new ResponseEntity<DaemonResponse<IResourceElementSet<IResourceElement>>>(response, HttpStatus.OK);	
	}
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/getResourceElementsFromSourceByPartialExternalIDPaginated", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<IResourceElementSet<IResourceElement>>> getResourceElementsFromSourceByPartialExternalIDPaginated(@RequestParam Long sourceId, @RequestParam String partialExternalId, @RequestParam Integer index, @RequestParam Integer paginationSize) throws ResourcesExceptions{
		DaemonResponse<IResourceElementSet<IResourceElement>> response = new DaemonResponse<IResourceElementSet<IResourceElement>>(resourcesElementLuceneService.getResourceElementsFromSourceByPartialExternalIDPaginated(partialExternalId, sourceId, index, paginationSize));
		return new ResponseEntity<DaemonResponse<IResourceElementSet<IResourceElement>>>(response, HttpStatus.OK);	
	}
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/getResourceElementsFromResourceByPartialExternalIDPaginated", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<IResourceElementSet<IResourceElement>>> getResourceElementsFromResourceByPartialExternalIDPaginated(@RequestParam Long resourceId, @RequestParam String partialExternalId, @RequestParam Integer index, @RequestParam Integer paginationSize) throws ResourcesExceptions{
		DaemonResponse<IResourceElementSet<IResourceElement>> response = new DaemonResponse<IResourceElementSet<IResourceElement>>(resourcesElementLuceneService.getResourceElementsFromResourceByPartialExternalIDPaginated(partialExternalId, resourceId, index, paginationSize));
		return new ResponseEntity<DaemonResponse<IResourceElementSet<IResourceElement>>>(response, HttpStatus.OK);	
	}
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/getResourceElementsFromResourceAndSourceByPartialExternalIDPaginated", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<IResourceElementSet<IResourceElement>>> getResourceElementsFromResourceAndSourceByPartialExternalIDPaginated(@RequestParam Long resourceId, @RequestParam Long sourceId, @RequestParam String partialExternalId, @RequestParam Integer index, @RequestParam Integer paginationSize) throws ResourcesExceptions{
		DaemonResponse<IResourceElementSet<IResourceElement>> response = new DaemonResponse<IResourceElementSet<IResourceElement>>(resourcesElementLuceneService.getResourceElementsFromResourceAndSourceByPartialExternalIDPaginated(partialExternalId, sourceId, resourceId, index, paginationSize));
		return new ResponseEntity<DaemonResponse<IResourceElementSet<IResourceElement>>>(response, HttpStatus.OK);	
	}
}
