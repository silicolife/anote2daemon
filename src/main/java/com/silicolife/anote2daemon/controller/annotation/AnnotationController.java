package com.silicolife.anote2daemon.controller.annotation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
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

import com.silicolife.anote2daemon.security.RestPermissionsEvaluatorEnum;
import com.silicolife.anote2daemon.utils.GenericPairSpringSpel;
import com.silicolife.anote2daemon.webservice.DaemonResponse;
import com.silicolife.textmining.core.datastructures.annotation.AnnotationsFilterImpl;
import com.silicolife.textmining.core.datastructures.annotation.log.AnnotationLogImpl;
import com.silicolife.textmining.core.datastructures.annotation.ner.EntityAnnotationImpl;
import com.silicolife.textmining.core.datastructures.annotation.re.EventAnnotationImpl;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.exceptions.AnnotationException;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.security.Permissions;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.service.annotation.IAnnotationService;
import com.silicolife.textmining.core.datastructures.documents.PublicationFilterImpl;
import com.silicolife.textmining.core.datastructures.documents.structure.SentenceImpl;
import com.silicolife.textmining.core.datastructures.utils.GenericPairImpl;
import com.silicolife.textmining.core.interfaces.core.analysis.IAnnotatedDocumentStatistics;
import com.silicolife.textmining.core.interfaces.core.annotation.IAnnotationLog;
import com.silicolife.textmining.core.interfaces.core.annotation.IEntityAnnotation;
import com.silicolife.textmining.core.interfaces.core.annotation.IEventAnnotation;
import com.silicolife.textmining.core.interfaces.core.annotation.IManualCurationAnnotations;
import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;
import com.silicolife.textmining.core.interfaces.core.document.structure.ISentence;
import com.silicolife.textmining.core.interfaces.core.general.classe.IAnoteClass;
import com.silicolife.textmining.core.interfaces.resource.IResourceElement;

/**
 * The goal of this class is to expose for the web all annotation functionalities of
 * anote2daemon. It is necessary a user logged to access these methods
 * 
 * @author Joel Azevedo Costa
 * @year 2015
 *
 */
@RequestMapping(value = "/annotation", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
@ResponseBody
@Controller
public class AnnotationController {

	@Autowired
	private Permissions permissions;
	@Autowired
	private GenericPairSpringSpel<RestPermissionsEvaluatorEnum, List<String>> genericPairSpringSpel;
	@Autowired
	private IAnnotationService annotationService;
	
	
	/**
	 * Add corpus processes document entity
	 * 
	 * @param corpusId
	 * @param processId
	 * @param documentID
	 * @param entityAnnotations
	 * @return
	 * @throws AnnotationException
	 */
	@PreAuthorize("isAuthenticated() and hasPermission(#processId, "
			+ "T(com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.utils.ResourcesTypeUtils).processes.getName(),"
			+ "@genericPairSpringSpel.getGenericPairSpringSpel(T(com.silicolife.anote2daemon.security.RestPermissionsEvaluatorEnum).default_,@permissions.getWritegrant()))")
	@RequestMapping(value = "/addCorpusProcessDocumentEntityAnootations/{corpusId}/{processId}/{documentId}", method = RequestMethod.POST)
	public ResponseEntity<DaemonResponse<Boolean>> addCorpusProcessDocumentEntityAnootations(@PathVariable Long corpusId, @PathVariable Long processId, 
																							@PathVariable Long documentId, @RequestBody List<EntityAnnotationImpl> entityAnnotations) throws AnnotationException{
		List<IEntityAnnotation> entityAnnotationList  = new ArrayList<IEntityAnnotation>();
		for (EntityAnnotationImpl entity : entityAnnotations) {
			entityAnnotationList.add(entity);
		}
		DaemonResponse<Boolean> response = new DaemonResponse<Boolean>(annotationService.addCorpusProcessDocumentEntityAnootations(corpusId, processId, documentId, entityAnnotationList));
		return new ResponseEntity<DaemonResponse<Boolean>>(response, HttpStatus.OK);
	}
	
	/**
	 * Get process publication statistics
	 * 
	 * @param publicationId
	 * @param processId
	 * @return
	 * @throws AnnotationException
	 */
	@PreAuthorize("isAuthenticated() and hasPermission(#processId, "
			+ "T(com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.utils.ResourcesTypeUtils).processes.getName(),"
			+ "@genericPairSpringSpel.getGenericPairSpringSpel(T(com.silicolife.anote2daemon.security.RestPermissionsEvaluatorEnum).default_,@permissions.getFullgrant()))")
	@RequestMapping(value = "/getProcessDocumentStatistics/{publicationId}/{processId}", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<IAnnotatedDocumentStatistics>> getProcessDocumentStatistics(@PathVariable Long publicationId, @PathVariable Long processId) throws AnnotationException{	
		DaemonResponse<IAnnotatedDocumentStatistics> response = new DaemonResponse<IAnnotatedDocumentStatistics>(annotationService.getProcessDocumentStatistics(publicationId, processId));
		return new ResponseEntity<DaemonResponse<IAnnotatedDocumentStatistics>>(response, HttpStatus.OK);
	}
	
	/**
	 * Get processes document annotations entities
	 * 
	 * @param publicationId
	 * @param processId
	 * @return
	 * @throws AnnotationException
	 */
	@PreAuthorize("isAuthenticated() and hasPermission(#processId, "
			+ "T(com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.utils.ResourcesTypeUtils).processes.getName(),"
			+ "@genericPairSpringSpel.getGenericPairSpringSpel(T(com.silicolife.anote2daemon.security.RestPermissionsEvaluatorEnum).default_,@permissions.getFullgrant()))")
	@RequestMapping(value = "/getProcessDoumentAnnotationEntities/{publicationId}/{processId}", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<List<IEntityAnnotation>>> getProcessDoumentAnnotationEntities(@PathVariable Long publicationId, @PathVariable Long processId) throws AnnotationException{
		DaemonResponse<List<IEntityAnnotation>> response = new DaemonResponse<List<IEntityAnnotation>>(annotationService.getProcessDoumentAnnotationEntities(publicationId, processId));
		return new ResponseEntity<DaemonResponse<List<IEntityAnnotation>>>(response, HttpStatus.OK);
	}
	
	/**
	 * Get processes document annotations entities filtered by resource element
	 * 
	 * @param publicationId
	 * @param processId
	 * @param resourceElementId
	 * @return
	 * @throws AnnotationException
	 */
	@PreAuthorize("isAuthenticated() and hasPermission(#processId, "
			+ "T(com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.utils.ResourcesTypeUtils).processes.getName(),"
			+ "@genericPairSpringSpel.getGenericPairSpringSpel(T(com.silicolife.anote2daemon.security.RestPermissionsEvaluatorEnum).default_,@permissions.getFullgrant()))")
	@RequestMapping(value = "/getProcessDoumentAnnotationEntitiesFilteredByResourceElement/{publicationId}/{processId}/{resourceId}", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<List<IEntityAnnotation>>> getProcessDoumentAnnotationEntitiesFilteredByResourceElement(@PathVariable Long publicationId, @PathVariable Long processId, @PathVariable Long resourceId) throws AnnotationException{
		DaemonResponse<List<IEntityAnnotation>> response = new DaemonResponse<List<IEntityAnnotation>>(annotationService.getProcessDoumentAnnotationEntitiesFilteredByResourceElement(publicationId, processId, resourceId));
		return new ResponseEntity<DaemonResponse<List<IEntityAnnotation>>>(response, HttpStatus.OK);
	}
	
	
	/**
	 * Get document annotations entities of a sentence related to a process
	 *  
	 * @param publicationId
	 * @param processId
	 * @param sentence
	 * @return
	 * @throws AnnotationException
	 */
	@PreAuthorize("isAuthenticated() and hasPermission(#processId, "
			+ "T(com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.utils.ResourcesTypeUtils).processes.getName(),"
			+ "@genericPairSpringSpel.getGenericPairSpringSpel(T(com.silicolife.anote2daemon.security.RestPermissionsEvaluatorEnum).default_,@permissions.getFullgrant()))")
	@RequestMapping(value = "/getProcessDoumentAnnotationEntitiesOfSentence/{publicationId}/{processId}", method = RequestMethod.POST)
	public ResponseEntity<DaemonResponse<List<IEntityAnnotation>>> getProcessDoumentAnnotationEntitiesOfSentence(@PathVariable Long publicationId, @PathVariable Long processId, @RequestBody SentenceImpl sentence) throws AnnotationException{
		DaemonResponse<List<IEntityAnnotation>> response = new DaemonResponse<List<IEntityAnnotation>>(annotationService.getProcessDoumentAnnotationEntitiesOfSentence(publicationId, processId, sentence));
		return new ResponseEntity<DaemonResponse<List<IEntityAnnotation>>>(response, HttpStatus.OK);
	}
	
	/**
	 * Get process document logs
	 * 
	 * @param processId
	 * @param publicationId
	 * @return
	 * @throws AnnotationException
	 */
	@PreAuthorize("isAuthenticated() and hasPermission(#processId, "
			+ "T(com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.utils.ResourcesTypeUtils).processes.getName(),"
			+ "@genericPairSpringSpel.getGenericPairSpringSpel(T(com.silicolife.anote2daemon.security.RestPermissionsEvaluatorEnum).default_,@permissions.getFullgrant()))")
	@RequestMapping(value = "/getProcessDocumentLogs/{processId}/{publicationId}", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<SortedSet<IAnnotationLog>>> getProcessDocumentLogs(@PathVariable Long processId, @PathVariable Long publicationId) throws AnnotationException{
		DaemonResponse<SortedSet<IAnnotationLog>> response = new DaemonResponse<SortedSet<IAnnotationLog>>(annotationService.getProcessDocumentLogs(processId, publicationId));
		return new ResponseEntity<DaemonResponse<SortedSet<IAnnotationLog>>>(response, HttpStatus.OK);
	}
	
	@PreAuthorize("isAuthenticated() and hasPermission(#processId, "
			+ "T(com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.utils.ResourcesTypeUtils).processes.getName(),"
			+ "@genericPairSpringSpel.getGenericPairSpringSpel(T(com.silicolife.anote2daemon.security.RestPermissionsEvaluatorEnum).default_,@permissions.getFullgrant()))")
	@RequestMapping(value = "/countAnnotationsByResourceElement/{processId}/{resourceElementId}", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<Long>> countAnnotations(@PathVariable Long processId, @PathVariable Long resourceElementId) throws AnnotationException{
		DaemonResponse<Long> response = new DaemonResponse<Long>(annotationService.countAnnotationsByResourceElement(processId, resourceElementId));
		return new ResponseEntity<DaemonResponse<Long>>(response, HttpStatus.OK);
	}
	
	/**
	 * Add process corpus to event annotations
	 * 
	 * @param corpusId
	 * @param processId
	 * @param documentID
	 * @param events
	 * @return
	 * @throws AnnotationException
	 */
	@PreAuthorize("isAuthenticated() and hasPermission(#processId, "
			+ "T(com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.utils.ResourcesTypeUtils).processes.getName(),"
			+ "@genericPairSpringSpel.getGenericPairSpringSpel(T(com.silicolife.anote2daemon.security.RestPermissionsEvaluatorEnum).default_,@permissions.getWritegrant()))")
	@RequestMapping(value = "/addCorpusProcessDocumentEventsAnootations/{corpusId}/{processId}/{documentId}", method = RequestMethod.POST)
	public ResponseEntity<DaemonResponse<Boolean>> addCorpusProcessDocumentEventsAnootations(@PathVariable Long corpusId,  @PathVariable Long processId, @PathVariable Long documentId, @RequestBody List<EventAnnotationImpl> events) throws AnnotationException{
		List<IEventAnnotation> eventAnnotationList  = new ArrayList<IEventAnnotation>();
		for (EventAnnotationImpl entity : events) {
			eventAnnotationList.add(entity);
		}
		DaemonResponse<Boolean> response = new DaemonResponse<Boolean>(annotationService.addCorpusProcessDocumentEventsAnootations(corpusId, processId, documentId, eventAnnotationList));
		return new ResponseEntity<DaemonResponse<Boolean>>(response, HttpStatus.OK);
	}
	
	
	/**
	 * Get processes log
	 * 
	 * @param processId
	 * @return
	 * @throws AnnotationException
	 */
	@PreAuthorize("isAuthenticated() and hasPermission(#processId, "
			+ "T(com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.utils.ResourcesTypeUtils).processes.getName(),"
			+ "@genericPairSpringSpel.getGenericPairSpringSpel(T(com.silicolife.anote2daemon.security.RestPermissionsEvaluatorEnum).default_,@permissions.getFullgrant()))")
	@RequestMapping(value = "/getProcessLogs/{processId}", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<SortedSet<IAnnotationLog>>> getProcessLogs(@PathVariable Long processId) throws AnnotationException{
		DaemonResponse<SortedSet<IAnnotationLog>> response = new DaemonResponse<SortedSet<IAnnotationLog>>(annotationService.getProcessLogs(processId));
		return new ResponseEntity<DaemonResponse<SortedSet<IAnnotationLog>>>(response, HttpStatus.OK);
	}
	
	
	/**
	 * get process document annotation events
	 * 
	 * @param processId
	 * @param publicationId
	 * @return
	 * @throws AnnotationException
	 */
	@PreAuthorize("isAuthenticated() and hasPermission(#processId, "
			+ "T(com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.utils.ResourcesTypeUtils).processes.getName(),"
			+ "@genericPairSpringSpel.getGenericPairSpringSpel(T(com.silicolife.anote2daemon.security.RestPermissionsEvaluatorEnum).default_,@permissions.getFullgrant()))")
	@RequestMapping(value = "/getProcessDoumentAnnotationEvents/{processId}/{publicationId}", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<List<IEventAnnotation>>> getProcessDoumentAnnotationEvents(@PathVariable Long processId, @PathVariable Long publicationId) throws AnnotationException{
		DaemonResponse<List<IEventAnnotation>> response = new DaemonResponse<List<IEventAnnotation>>(annotationService.getProcessDoumentAnnotationEvents(processId, publicationId));
		return new ResponseEntity<DaemonResponse<List<IEventAnnotation>>>(response, HttpStatus.OK);
	}
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/getSentence/{entityAnnotationId}", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<ISentence>> getSentence(@PathVariable Long entityAnnotationId ) throws ANoteException, IOException{
		DaemonResponse<ISentence> response = new DaemonResponse<ISentence>(annotationService.getSentence(entityAnnotationId));
		return new ResponseEntity<DaemonResponse<ISentence>>(response, HttpStatus.OK);
	}
	
	/**
	 * Get process annotation association to logs
	 * 
	 * @param processId
	 * @return
	 * @throws AnnotationException
	 */
	@PreAuthorize("isAuthenticated() and hasPermission(#processId, "
			+ "T(com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.utils.ResourcesTypeUtils).processes.getName(),"
			+ "@genericPairSpringSpel.getGenericPairSpringSpel(T(com.silicolife.anote2daemon.security.RestPermissionsEvaluatorEnum).default_,@permissions.getFullgrant()))")
	@RequestMapping(value = "/getProcessDocumentAnnotationsAssociatedToLogs/{processId}", method = RequestMethod.GET)
	public  ResponseEntity<DaemonResponse<IManualCurationAnnotations>> getProcessDocumentAnnotationsAssociatedToLogs(@PathVariable Long processId) throws AnnotationException{
		DaemonResponse<IManualCurationAnnotations> response = new DaemonResponse<IManualCurationAnnotations>(annotationService.getProcessDocumentAnnotationsAssociatedToLogs(processId));
		return new ResponseEntity<DaemonResponse<IManualCurationAnnotations>>(response, HttpStatus.OK);
	}
	
	/**
	 * add annotation logs
	 * 
	 * @param annotationLogs
	 * @return
	 * @throws AnnotationException
	 */
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/addAnnotationLogs", method = RequestMethod.POST)
	public ResponseEntity<DaemonResponse<Boolean>> addAnnotationLogs(@RequestBody List<AnnotationLogImpl> annotationLogs) throws AnnotationException{
		List<IAnnotationLog> logAnnotationList  = new ArrayList<IAnnotationLog>();
		for (AnnotationLogImpl entity : annotationLogs) {
			logAnnotationList.add(entity);
		}
		DaemonResponse<Boolean> response = new DaemonResponse<Boolean>(annotationService.addAnnotationLogs(logAnnotationList));
		return new ResponseEntity<DaemonResponse<Boolean>>(response, HttpStatus.OK);
	}

	
	/**
	 * inactive annotations
	 * 
	 * @param annotation
	 * @return
	 * @throws AnnotationException
	 */
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/inactiveAnnotations", method = RequestMethod.PUT)
	public ResponseEntity<DaemonResponse<Boolean>> inactiveAnnotations(@RequestBody List<Long> annotation) throws AnnotationException{
		DaemonResponse<Boolean> response = new DaemonResponse<Boolean>(annotationService.inactiveAnnotations(annotation));
		return new ResponseEntity<DaemonResponse<Boolean>>(response, HttpStatus.OK);
	}
	
	/**
	 * remove annotations
	 * 
	 * @param annotation
	 * @return
	 * @throws AnnotationException
	 */
	@PreAuthorize("isAuthenticated() and hasPermission(#processId, "
			+ "T(com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.utils.ResourcesTypeUtils).processes.getName(),"
			+ "@genericPairSpringSpel.getGenericPairSpringSpel(T(com.silicolife.anote2daemon.security.RestPermissionsEvaluatorEnum).default_,@permissions.getWritegrant()))")
	@RequestMapping(value = "/removeAllProcessDocumentAnnotations/{processId}/{documentId}", method = RequestMethod.PUT)
	public ResponseEntity<DaemonResponse<Boolean>> removeAllProcessDocumentAnnotations(@PathVariable Long processId, @PathVariable Long documentId) throws AnnotationException{
		DaemonResponse<Boolean> response = new DaemonResponse<Boolean>(annotationService.removeAllProcessDocumentAnnotations(processId,documentId));
		return new ResponseEntity<DaemonResponse<Boolean>>(response, HttpStatus.OK);
	}
	
	/**
	 * Update entity annotations
	 * 
	 * @param entityAnnotations
	 * @return
	 * @throws AnnotationException
	 */
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/updateEntityAnnotations", method = RequestMethod.PUT)
	public ResponseEntity<DaemonResponse<Boolean>> updateEntityAnnotations(@RequestBody List<EntityAnnotationImpl> iEntityAnnotation) throws AnnotationException{
		List<IEntityAnnotation> entityAnnotationList  = new ArrayList<IEntityAnnotation>();
		for (EntityAnnotationImpl entity : iEntityAnnotation) {
			entityAnnotationList.add(entity);
		}
		DaemonResponse<Boolean> response = new DaemonResponse<Boolean>(annotationService.updateEntityAnnotations(entityAnnotationList));
		return new ResponseEntity<DaemonResponse<Boolean>>(response, HttpStatus.OK);
	}
	
	/**
	 * Update entity annotations
	 * 
	 * @param entityAnnotations
	 * @return
	 * @throws AnnotationException
	 */
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/getPublicationsIdsByResourceElements", method = RequestMethod.POST)
	public ResponseEntity<DaemonResponse<List<Long>>> getPublicationByResourceElement(@RequestBody Set<Long> resourceElementIds) throws AnnotationException{
		DaemonResponse<List<Long>> response = new DaemonResponse<List<Long>>(annotationService.getPublicationsIdsByResourceElements(resourceElementIds));
		return new ResponseEntity<DaemonResponse<List<Long>>>(response, HttpStatus.OK);
	}
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/getPublicationsIdsByResourceElementsFilteredByPublicationFilter", method = RequestMethod.POST)
	public ResponseEntity<DaemonResponse<List<Long>>> getPublicationByResourceElementFilteredByPublicationFilter(@RequestBody GenericPairImpl<Set<Long>,PublicationFilterImpl> resourceElementIdsAndFilter) throws AnnotationException{
		DaemonResponse<List<Long>> response = new DaemonResponse<List<Long>>(annotationService.getPublicationsIdsByResourceElementsFilteredByPublicationFilter(resourceElementIdsAndFilter.getX(), resourceElementIdsAndFilter.getY()));
		return new ResponseEntity<DaemonResponse<List<Long>>>(response, HttpStatus.OK);
	}
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/getProcessesIdsByResourceElements", method = RequestMethod.POST)
	public ResponseEntity<DaemonResponse<List<Long>>> getProcessesIdsByResourceElements(@RequestBody Set<Long> resourceElementIds) throws AnnotationException{
		DaemonResponse<List<Long>> response = new DaemonResponse<List<Long>>(annotationService.getProcessesIdsByResourceElements(resourceElementIds));
		return new ResponseEntity<DaemonResponse<List<Long>>>(response, HttpStatus.OK);
	}
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/getPublicationsIdsByAnnotationsFilter", method = RequestMethod.POST, consumes = { "application/json" })
	public ResponseEntity<DaemonResponse<List<Long>>> getPublicationsIdsByAnnotationsFilter(@RequestBody AnnotationsFilterImpl filter) throws AnnotationException{
		DaemonResponse<List<Long>> response = new DaemonResponse<List<Long>>(annotationService.getPublicationsIdsByAnnotationsFilter(filter));
		return new ResponseEntity<DaemonResponse<List<Long>>>(response, HttpStatus.OK);	
	}
	
	@PreAuthorize("isAuthenticated()")
	@Cacheable(value = "ctEntityByTypeCache", sync = true)
	@RequestMapping(value = "/countAnnotationsByAnnotationType/{processId}/{annotationType}", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<Long>> countAnnotationsByAnnotationType(@PathVariable Long processId, @PathVariable String annotationType) throws AnnotationException{
		DaemonResponse<Long> response = new DaemonResponse<Long>(annotationService.countAnnotationsByAnnotionType(processId, annotationType));
		return new ResponseEntity<DaemonResponse<Long>>(response, HttpStatus.OK);
	}
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/countDocumentsWithResourceElementByAnnotationTypeInProcess/{resourceElementId}/{processId}/{annotationType}", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<Long>> countDocumentsWithResourceElementByAnnotationTypeInProcess(@PathVariable Long resourceElementId, @PathVariable Long processId, @PathVariable String annotationType) throws AnnotationException{
		DaemonResponse<Long> response = new DaemonResponse<Long>(annotationService.countDocumentsWithResourceElementByAnnotationTypeInProcess(resourceElementId,processId, annotationType));
		return new ResponseEntity<DaemonResponse<Long>>(response, HttpStatus.OK);
	}
	
	
	@PreAuthorize("isAuthenticated()")
	@Cacheable(value = "ctEntityByClassCache", sync = true)
	@RequestMapping(value = "/countEntityAnnotationsByClassInProcess/{processId}", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<Map<IAnoteClass, Long>>> countEntityAnnotationsByClassInProcess(@PathVariable Long processId) throws AnnotationException{
		DaemonResponse<Map<IAnoteClass, Long>> response = new DaemonResponse<Map<IAnoteClass, Long>>(annotationService.countEntityAnnotationsByClassInProcess(processId));
		return new ResponseEntity<DaemonResponse<Map<IAnoteClass, Long>>>(response, HttpStatus.OK);
	}
	
	@PreAuthorize("isAuthenticated()")
	@Cacheable(value = "ctDocWithEntityByResourceElmCache", sync = true)
	@RequestMapping(value = "/countDocumentsWithEntityAnnotationsByResourceElementInProcess/{processId}", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<Map<IResourceElement, Long>>> countDocumentsWithEntityAnnotationsByResourceElementInProcess(@PathVariable Long processId) throws AnnotationException{
		DaemonResponse<Map<IResourceElement, Long>> response = new DaemonResponse<Map<IResourceElement, Long>>(annotationService.countDocumentsWithEntityAnnotationsByResourceElementInProcess(processId));
		return new ResponseEntity<DaemonResponse<Map<IResourceElement, Long>>>(response, HttpStatus.OK);
	}
	
	@PreAuthorize("isAuthenticated()")
	@Cacheable(value = "ctEntityByResourceElmCache", sync = true)
	@RequestMapping(value = "/countEntityAnnotationsByResourceElementInProcess/{processId}", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<Map<IResourceElement, Long>>> countEntityAnnotationsByResourceElementInProcess(@PathVariable Long processId) throws AnnotationException{
		DaemonResponse<Map<IResourceElement, Long>> response = new DaemonResponse<Map<IResourceElement, Long>>(annotationService.countEntityAnnotationsByResourceElementInProcess(processId));
		return new ResponseEntity<DaemonResponse<Map<IResourceElement, Long>>>(response, HttpStatus.OK);
	}
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/countPublicationsWithEventsByIAnoteClasses/{processId}", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<Map<ImmutablePair<IAnoteClass,IAnoteClass>, Long>>> countPublicationsWithEventsByIAnoteClasses( @PathVariable Long processId) throws AnnotationException{
		DaemonResponse<Map<ImmutablePair<IAnoteClass,IAnoteClass>, Long>> response = new DaemonResponse<Map<ImmutablePair<IAnoteClass,IAnoteClass>, Long>>(annotationService.countPublicationsWithEventsByIAnoteClasses(processId));
		return new ResponseEntity<DaemonResponse<Map<ImmutablePair<IAnoteClass,IAnoteClass>, Long>>>(response, HttpStatus.OK);
	}
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/countEventAnnotationsByClassInProcess/{processId}", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<Map<ImmutablePair<IAnoteClass,IAnoteClass>, Long>>> countEventAnnotationsByClassInProcess( @PathVariable Long processId) throws AnnotationException{
		DaemonResponse<Map<ImmutablePair<IAnoteClass,IAnoteClass>, Long>> response = new DaemonResponse<Map<ImmutablePair<IAnoteClass,IAnoteClass>, Long>>(annotationService.countEventAnnotationsByClassInProcess( processId));
		return new ResponseEntity<DaemonResponse<Map<ImmutablePair<IAnoteClass,IAnoteClass>, Long>>>(response, HttpStatus.OK);
	}
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/getPublicationsIdsByEventResourceElements/{processId}", method = RequestMethod.POST)
	public ResponseEntity<DaemonResponse<List<Long>>> getPublicationsIdsByEventResourceElements( @PathVariable Long processId, 
																							@RequestBody Set<String> resElemIds) throws AnnotationException{
		DaemonResponse<List<Long>> response = new DaemonResponse<List<Long>>(annotationService.getPublicationsIdsByEventResourceElements(processId, resElemIds));
		return new ResponseEntity<DaemonResponse<List<Long>>>(response, HttpStatus.OK);
	}
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/countDocumentsWithEventsByResourceElemnts/{processId}", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<Map<ImmutablePair<IResourceElement,IResourceElement>, Long>>> countDocumentsWithEventsByResourceElemnts( @PathVariable Long processId) throws AnnotationException{
		DaemonResponse<Map<ImmutablePair<IResourceElement,IResourceElement>, Long>> response = new DaemonResponse<Map<ImmutablePair<IResourceElement,IResourceElement>, Long>>(annotationService.countDocumentsWithEventsByResourceElemnts( processId));
		return new ResponseEntity<DaemonResponse<Map<ImmutablePair<IResourceElement,IResourceElement>, Long>>>(response, HttpStatus.OK);
	}
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/countEventsByResourceElemnts/{processId}", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<Map<ImmutablePair<IResourceElement,IResourceElement>, Long>>> countEventsByResourceElemnts( @PathVariable Long processId) throws AnnotationException{
		DaemonResponse<Map<ImmutablePair<IResourceElement,IResourceElement>, Long>> response = new DaemonResponse<Map<ImmutablePair<IResourceElement,IResourceElement>, Long>>(annotationService.countEventsByResourceElemnts( processId));
		return new ResponseEntity<DaemonResponse<Map<ImmutablePair<IResourceElement,IResourceElement>, Long>>>(response, HttpStatus.OK);
	}
	
}
