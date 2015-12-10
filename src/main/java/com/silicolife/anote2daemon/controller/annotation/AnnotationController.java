package com.silicolife.anote2daemon.controller.annotation;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;

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

import pt.uminho.anote2.datastructures.annotation.AnnotationImpl;
import pt.uminho.anote2.datastructures.annotation.log.AnnotationLogImpl;
import pt.uminho.anote2.datastructures.annotation.ner.EntityAnnotationImpl;
import pt.uminho.anote2.datastructures.annotation.re.EventAnnotationImpl;
import pt.uminho.anote2.datastructures.dataaccess.database.dataaccess.implementation.exceptions.AnnotationException;
import pt.uminho.anote2.datastructures.dataaccess.database.dataaccess.implementation.security.Permissions;
import pt.uminho.anote2.datastructures.dataaccess.database.dataaccess.implementation.service.annotation.IAnnotationService;
import pt.uminho.anote2.interfaces.core.annotation.IAnnotation;
import pt.uminho.anote2.interfaces.core.annotation.IAnnotationLog;
import pt.uminho.anote2.interfaces.core.annotation.IEntityAnnotation;
import pt.uminho.anote2.interfaces.core.annotation.IEventAnnotation;
import pt.uminho.anote2.interfaces.core.document.IAnnotatedDocumentStatistics;

import com.silicolife.anote2daemon.security.RestPermissionsEvaluatorEnum;
import com.silicolife.anote2daemon.utils.GenericPairSpringSpel;
import com.silicolife.anote2daemon.webservice.DaemonResponse;

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
			+ "T(pt.uminho.anote2.datastructures.dataaccess.database.dataaccess.implementation.utils.ResourcesTypeUtils).processes.getName(),"
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
			+ "T(pt.uminho.anote2.datastructures.dataaccess.database.dataaccess.implementation.utils.ResourcesTypeUtils).processes.getName(),"
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
			+ "T(pt.uminho.anote2.datastructures.dataaccess.database.dataaccess.implementation.utils.ResourcesTypeUtils).processes.getName(),"
			+ "@genericPairSpringSpel.getGenericPairSpringSpel(T(com.silicolife.anote2daemon.security.RestPermissionsEvaluatorEnum).default_,@permissions.getFullgrant()))")
	@RequestMapping(value = "/getProcessDoumentAnnotationEntities/{publicationId}/{processId}", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<List<IEntityAnnotation>>> getProcessDoumentAnnotationEntities(@PathVariable Long publicationId, @PathVariable Long processId) throws AnnotationException{
		DaemonResponse<List<IEntityAnnotation>> response = new DaemonResponse<List<IEntityAnnotation>>(annotationService.getProcessDoumentAnnotationEntities(publicationId, processId));
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
			+ "T(pt.uminho.anote2.datastructures.dataaccess.database.dataaccess.implementation.utils.ResourcesTypeUtils).processes.getName(),"
			+ "@genericPairSpringSpel.getGenericPairSpringSpel(T(com.silicolife.anote2daemon.security.RestPermissionsEvaluatorEnum).default_,@permissions.getFullgrant()))")
	@RequestMapping(value = "/getProcessDocumentLogs/{processId}/{publicationId}", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<SortedSet<IAnnotationLog>>> getProcessDocumentLogs(@PathVariable Long processId, @PathVariable Long publicationId) throws AnnotationException{
		DaemonResponse<SortedSet<IAnnotationLog>> response = new DaemonResponse<SortedSet<IAnnotationLog>>(annotationService.getProcessDocumentLogs(processId, publicationId));
		return new ResponseEntity<DaemonResponse<SortedSet<IAnnotationLog>>>(response, HttpStatus.OK);
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
			+ "T(pt.uminho.anote2.datastructures.dataaccess.database.dataaccess.implementation.utils.ResourcesTypeUtils).processes.getName(),"
			+ "@genericPairSpringSpel.getGenericPairSpringSpel(T(com.silicolife.anote2daemon.security.RestPermissionsEvaluatorEnum).default_,@permissions.getWritegrant()))")
	@RequestMapping(value = "/addCorpusProcessDocumentEventsAnootations/{corpusId}/{processId}/{documentId}", method = RequestMethod.POST)
	public ResponseEntity<DaemonResponse<Boolean>> addCorpusProcessDocumentEventsAnootations(@PathVariable Long corpusId,  @PathVariable Long processId, @PathVariable Long documentID, @RequestBody List<EventAnnotationImpl> events) throws AnnotationException{
		List<IEventAnnotation> eventAnnotationList  = new ArrayList<IEventAnnotation>();
		for (EventAnnotationImpl entity : events) {
			eventAnnotationList.add(entity);
		}
		DaemonResponse<Boolean> response = new DaemonResponse<Boolean>(annotationService.addCorpusProcessDocumentEventsAnootations(corpusId, processId, documentID, eventAnnotationList));
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
			+ "T(pt.uminho.anote2.datastructures.dataaccess.database.dataaccess.implementation.utils.ResourcesTypeUtils).processes.getName(),"
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
			+ "T(pt.uminho.anote2.datastructures.dataaccess.database.dataaccess.implementation.utils.ResourcesTypeUtils).processes.getName(),"
			+ "@genericPairSpringSpel.getGenericPairSpringSpel(T(com.silicolife.anote2daemon.security.RestPermissionsEvaluatorEnum).default_,@permissions.getFullgrant()))")
	@RequestMapping(value = "/getProcessDoumentAnnotationEvents/{processId}/{publicationId}", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<List<IEventAnnotation>>> getProcessDoumentAnnotationEvents(@PathVariable Long processId, @PathVariable Long publicationId) throws AnnotationException{
		DaemonResponse<List<IEventAnnotation>> response = new DaemonResponse<List<IEventAnnotation>>(annotationService.getProcessDoumentAnnotationEvents(processId, publicationId));
		return new ResponseEntity<DaemonResponse<List<IEventAnnotation>>>(response, HttpStatus.OK);
	}
	
	/**
	 * Get process annotation association to logs
	 * 
	 * @param processId
	 * @return
	 * @throws AnnotationException
	 */
	@PreAuthorize("isAuthenticated() and hasPermission(#processId, "
			+ "T(pt.uminho.anote2.datastructures.dataaccess.database.dataaccess.implementation.utils.ResourcesTypeUtils).processes.getName(),"
			+ "@genericPairSpringSpel.getGenericPairSpringSpel(T(com.silicolife.anote2daemon.security.RestPermissionsEvaluatorEnum).default_,@permissions.getFullgrant()))")
	@RequestMapping(value = "/getProcessDocumentAnnotationsAssociatedToLogs/{processId}", method = RequestMethod.GET)
	public  ResponseEntity<DaemonResponse<List<IAnnotation>>> getProcessDocumentAnnotationsAssociatedToLogs(@PathVariable Long processId) throws AnnotationException{
		DaemonResponse<List<IAnnotation>> response = new DaemonResponse<List<IAnnotation>>(annotationService.getProcessDocumentAnnotationsAssociatedToLogs(processId));
		return new ResponseEntity<DaemonResponse<List<IAnnotation>>>(response, HttpStatus.OK);
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
	public ResponseEntity<DaemonResponse<Boolean>> inactiveAnnotations(@RequestBody List<AnnotationImpl> annotation) throws AnnotationException{
		List<IAnnotation> annotationList  = new ArrayList<IAnnotation>();
		for (AnnotationImpl annot : annotation) {
			annotationList.add(annot);
		}
		DaemonResponse<Boolean> response = new DaemonResponse<Boolean>(annotationService.inactiveAnnotations(annotationList));
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
}
