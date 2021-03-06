package com.silicolife.anote2daemon.dataaccess;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.SortedSet;

import org.apache.commons.lang3.tuple.ImmutablePair;

import com.silicolife.textmining.core.datastructures.annotation.AnnotationType;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.dao.UsersLogged;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.model.core.entities.AuthUsers;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.utils.PermissionsUtilsEnum;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.utils.ResourcesTypeUtils;
import com.silicolife.textmining.core.interfaces.core.analysis.IAnnotatedDocumentStatistics;
import com.silicolife.textmining.core.interfaces.core.analysis.IIEProcessStatistics;
import com.silicolife.textmining.core.interfaces.core.annotation.IAnnotation;
import com.silicolife.textmining.core.interfaces.core.annotation.IAnnotationLog;
import com.silicolife.textmining.core.interfaces.core.annotation.IAnnotationsFilter;
import com.silicolife.textmining.core.interfaces.core.annotation.IEntityAnnotation;
import com.silicolife.textmining.core.interfaces.core.annotation.IEventAnnotation;
import com.silicolife.textmining.core.interfaces.core.annotation.IManualCurationAnnotations;
import com.silicolife.textmining.core.interfaces.core.cluster.IClusterProcess;
import com.silicolife.textmining.core.interfaces.core.dataaccess.IDataAccess;
import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;
import com.silicolife.textmining.core.interfaces.core.dataaccess.layer.resources.IResourceManagerReport;
import com.silicolife.textmining.core.interfaces.core.document.IAnnotatedDocument;
import com.silicolife.textmining.core.interfaces.core.document.IDocumentSet;
import com.silicolife.textmining.core.interfaces.core.document.IPublication;
import com.silicolife.textmining.core.interfaces.core.document.IPublicationFilter;
import com.silicolife.textmining.core.interfaces.core.document.ISearchProperties;
import com.silicolife.textmining.core.interfaces.core.document.corpus.ICorpus;
import com.silicolife.textmining.core.interfaces.core.document.corpus.ICorpusStatistics;
import com.silicolife.textmining.core.interfaces.core.document.labels.IPublicationLabel;
import com.silicolife.textmining.core.interfaces.core.document.relevance.IQueryPublicationRelevance;
import com.silicolife.textmining.core.interfaces.core.document.structure.ISentence;
import com.silicolife.textmining.core.interfaces.core.general.IDataProcessStatus;
import com.silicolife.textmining.core.interfaces.core.general.IExternalID;
import com.silicolife.textmining.core.interfaces.core.general.classe.IAnoteClass;
import com.silicolife.textmining.core.interfaces.core.general.source.ISource;
import com.silicolife.textmining.core.interfaces.core.hyperlink.IHyperLinkMenuItem;
import com.silicolife.textmining.core.interfaces.core.user.IGroup;
import com.silicolife.textmining.core.interfaces.core.user.IUser;
import com.silicolife.textmining.core.interfaces.core.user.IUserDataObject;
import com.silicolife.textmining.core.interfaces.core.utils.IGenericPair;
import com.silicolife.textmining.core.interfaces.process.IE.IIEProcess;
import com.silicolife.textmining.core.interfaces.process.IE.re.IRelationsType;
import com.silicolife.textmining.core.interfaces.process.IR.IQuery;
import com.silicolife.textmining.core.interfaces.resource.IResource;
import com.silicolife.textmining.core.interfaces.resource.IResourceElement;
import com.silicolife.textmining.core.interfaces.resource.IResourceElementSet;
import com.silicolife.textmining.core.interfaces.resource.IResourceElementsFilter;
import com.silicolife.textmining.core.interfaces.resource.content.IResourceContent;
import com.silicolife.textmining.core.interfaces.resource.ontologies.IResourceElementsRelation;

public class ServerAccess implements IDataAccess{
	
	public ServerAccess() {
	}
	


	@Override
	public List<IPublication> getQueryPublications(IQuery query) throws ANoteException {
		return SpringInjectedServicesAccess.getQueriesService().getQueryPublications(query.getId());
	}

	@Override
	public void updateQueryDocumentRelevance(IPublication publication, IQuery query,
			IQueryPublicationRelevance iQueryPublicationRelevance) throws ANoteException {
		SpringInjectedServicesAccess.getQueriesService().updateRelevance(query.getId(), publication.getId(), iQueryPublicationRelevance.getRelevance().toString());
	}

	@Override
	public void addQueryPublications(IQuery query, Set<IPublication> publications) throws ANoteException {
		Set<Long> pubIds = new HashSet<Long>();
		for (IPublication pub : publications)
			pubIds.add(pub.getId());
		SpringInjectedServicesAccess.getQueriesService().addPublicationsToQuery(query.getId(), pubIds);
	}

	@Override
	public void createQuery(IQuery query) throws ANoteException {
		SpringInjectedServicesAccess.getQueriesService().create(query);
	}

	@Override
	public void updateQuery(IQuery query) throws ANoteException {
		SpringInjectedServicesAccess.getQueriesService().update(query);
	}

	@Override
	public void inactiveQuery(IQuery query) throws ANoteException {
		SpringInjectedServicesAccess.getQueriesService().inactiveQuery(query.getId());
	}

	@Override
	public void removeQuery(IQuery query) throws ANoteException {
		SpringInjectedServicesAccess.getPrivilegesService().removePrivilegeLoggedUser(query.getId(), ResourcesTypeUtils.queries.getName());
	}

	@Override
	public List<IQuery> getAllQueries() throws ANoteException {
		return SpringInjectedServicesAccess.getQueriesService().getAllQueries();
	}

	@Override
	public IQuery getQueryByID(long queryID) throws ANoteException {
		return SpringInjectedServicesAccess.getQueriesService().getById(queryID);
	}

	@Override
	public Set<String> getQueryPublicationsExternalIDFromSource(IQuery query, String source) throws ANoteException {
		return SpringInjectedServicesAccess.getQueriesService().getQueryPublicationsExternalIDFromSource(query.getId(), source);
	}

	@Override
	public List<IQuery> getPrivilegesAllQueriesAdminAccess() throws ANoteException {
		return SpringInjectedServicesAccess.getQueriesService().getAllPrivilegesQueriesAdminAccess();
	}

	@Override
	public List<IResource<IResourceElement>> getPrivilegesAllResourcesAdminAccess() throws ANoteException {
		return SpringInjectedServicesAccess.getResourcesService().getAllPrivilegesResourcesAdminAccess();
	}

	@Override
	public void createClustering(IClusterProcess clustering) throws ANoteException {
		SpringInjectedServicesAccess.getClusteringService().createClustering(clustering);
	}

	@Override
	public void addClusteringLabels(IClusterProcess cluster) throws ANoteException {
		SpringInjectedServicesAccess.getClusteringService().addClusteringLabels(cluster.getId(), cluster.getClusterLabels());
	}

	@Override
	public List<IClusterProcess> getQueryClusters(IQuery query) throws ANoteException {
		return SpringInjectedServicesAccess.getClusteringService().getClustersFromQuery(query.getId());
	}

	@Override
	public IClusterProcess getClusterByID(long clusterId) throws ANoteException {
		return SpringInjectedServicesAccess.getClusteringService().getClusteringById(clusterId);
	}

	@Override
	public void inactivateCluster(IClusterProcess clustering) throws ANoteException {
		SpringInjectedServicesAccess.getClusteringService().inactivateClustering(clustering.getId());
		
	}

	@Override
	public void registerQueryClustering(IQuery query, IClusterProcess clustering) throws ANoteException {
		SpringInjectedServicesAccess.getClusteringService().registerQueryClustering(query.getId(), clustering.getId());
		
	}

	@Override
	public void updateQueryClusteringProcess(IClusterProcess clustering) throws ANoteException {
		SpringInjectedServicesAccess.getClusteringService().updateQueryClusteringProcess(clustering);
	}

	@Override
	public void addPublications(Set<IPublication> publications) throws ANoteException {
		SpringInjectedServicesAccess.getPublicationsService().create(publications);
	}

	@Override
	public IPublication getPublication(long publicationId) throws ANoteException {
		return SpringInjectedServicesAccess.getPublicationsService().getById(publicationId);
	}

	@Override
	public void updatePublication(IPublication publication) throws ANoteException {
		SpringInjectedServicesAccess.getPublicationsService().update(publication);
	}

	@Override
	public void updatePublicationAvailableFreeFullText(IPublication pub) throws ANoteException {
		SpringInjectedServicesAccess.getPublicationsService().updatePublicationAvailableFreeFullText(pub.getId(), pub.isFreeFullText());
	}

	@Override
	public Map<String, Long> getAllPublicationsExternalIDFromSource(String source) throws ANoteException {
		return SpringInjectedServicesAccess.getPublicationsService().getAllPublicationsIdFromSource(source);
	}

	@Override
	public String getPublicationFullText(IPublication publication) throws ANoteException {
		return SpringInjectedServicesAccess.getPublicationsService().getFullText(publication.getId());
	}

	@Override
	public void insertNewClass(IAnoteClass klass) throws ANoteException {
		SpringInjectedServicesAccess.getClassesService().addClass(klass);
	}

	@Override
	public Set<IAnoteClass> getClasses() throws ANoteException {
		return SpringInjectedServicesAccess.getClassesService().getClasses();
	}

	@Override
	public boolean updateClassName(IAnoteClass oldclassName, String newCLass) throws ANoteException {
		throw new ANoteException("Method not available in Server Access");
	}

	@Override
	public void updateAnoteKlass(IAnoteClass klass) throws ANoteException {
		SpringInjectedServicesAccess.getClassesService().updateClass(klass);
	}

	@Override
	public List<IPublicationLabel> getAllLabels() throws ANoteException {
		return SpringInjectedServicesAccess.getPublicationsService().getAllPublicationLabels();
	}

	@Override
	public void updatePublicationFullTextContent(IPublication publication) throws ANoteException {
		SpringInjectedServicesAccess.getPublicationsService().updatePublicationAFullTextContent(publication.getId(), publication.getFullTextContent());
		
	}

	@Override
	public void createCorpus(ICorpus corpus) throws ANoteException {
		SpringInjectedServicesAccess.getCorpusService().createCorpus(corpus);
	}

	@Override
	public void updateCorpus(ICorpus corpus) throws ANoteException {
		SpringInjectedServicesAccess.getCorpusService().updateCorpus(corpus);
		
	}
	
	@Override
	public void updateCorpusStatus(ICorpus corpus, boolean status) throws ANoteException {
		SpringInjectedServicesAccess.getCorpusService().updateCorpusStatus(corpus, status);
		
	}

	@Override
	public void inactivateCorpus(ICorpus corpus) throws ANoteException {
		SpringInjectedServicesAccess.getPrivilegesService().removePrivilegeLoggedUser(corpus.getId(), ResourcesTypeUtils.corpus.getName());
	}

	@Override
	public List<ICorpus> getAllCorpus() throws ANoteException {
		return SpringInjectedServicesAccess.getCorpusService().getAllCorpus();
	}

	@Override
	public ICorpus getCorpusByID(long corpusID) throws ANoteException {
		return SpringInjectedServicesAccess.getCorpusService().getCorpusByID(corpusID);
	}

	@Override
	public void registerCorpusProcess(ICorpus corpus, IIEProcess process) throws ANoteException {
		SpringInjectedServicesAccess.getCorpusService().registerCorpusProcess(corpus.getId(), process.getId());
	}

	@Override
	public List<IIEProcess> getCorpusProcesses(ICorpus corpus) throws ANoteException {
		return SpringInjectedServicesAccess.getCorpusService().getCorpusProcesses(corpus.getId());
	}

	@Override
	public IDocumentSet getCorpusPublications(ICorpus corpus) throws ANoteException {
		return SpringInjectedServicesAccess.getCorpusService().getCorpusPublications(corpus.getId());
	}

	@Override
	public Long getCorpusPublicationsCount(ICorpus corpus) throws ANoteException {
		return SpringInjectedServicesAccess.getCorpusService().getCorpusPublicationsCount(corpus.getId());
	}

	@Override
	public IDocumentSet getCorpusPublicationsPaginated(ICorpus corpus, Integer paginationIndex, Integer paginationSize)
			throws ANoteException {
		return SpringInjectedServicesAccess.getCorpusService().getCorpusPublicationsPaginated(corpus.getId(), paginationIndex, paginationSize);
	}

	@Override
	public void addCorpusPublication(ICorpus corpus, IPublication publication) throws ANoteException {
		SpringInjectedServicesAccess.getCorpusService().addCorpusPublication(corpus.getId(), publication.getId());
	}

	@Override
	public ICorpusStatistics getCorpusStatistics(ICorpus corpus) throws ANoteException {
		return SpringInjectedServicesAccess.getCorpusService().getCorpusStatistics(corpus.getId());
	}

	@Override
	public Long countCorpusPublicationsNotProcessed(IIEProcess process) throws ANoteException {
		return SpringInjectedServicesAccess.getCorpusService().countCorpusPublicationsNotProcessed(process.getCorpus().getId(), process.getId());
	}

	@Override
	public IDocumentSet getCorpusPublicationsNotProcessedPaginated(IIEProcess process, Integer paginationIndex,
			Integer paginationSize) throws ANoteException {
		return SpringInjectedServicesAccess.getCorpusService().getCorpusPublicationsNotProcessedPaginated(process.getCorpus().getId(), process.getId(), paginationIndex, paginationSize);
	}

	@Override
	public Set<String> getCorpusPublicationsExternalIDFromSource(ICorpus corpus, String source) throws ANoteException {
		return SpringInjectedServicesAccess.getCorpusService().getCorpusPublicationsExternalIDFromSource(corpus.getId(), source);
	}

	@Override
	public void createIEProcess(IIEProcess process) throws ANoteException {
		SpringInjectedServicesAccess.getProcessesService().createIEProcess(process);
		
	}

	@Override
	public void updateIEProcess(IIEProcess process) throws ANoteException {
		SpringInjectedServicesAccess.getProcessesService().updateIEProcess(process);
	}

	@Override
	public void inactivateProcess(IIEProcess process) throws ANoteException {
		SpringInjectedServicesAccess.getPrivilegesService().removePrivilegeLoggedUser(process.getId(), ResourcesTypeUtils.processes.getName());
	}

	@Override
	public IIEProcess getProcessByID(long processID) throws ANoteException {
		return SpringInjectedServicesAccess.getProcessesService().getProcessByID(processID);
	}

	@Override
	public IIEProcessStatistics getIEProcessStatistics(IIEProcess ieProcessImpl) throws ANoteException {
		return SpringInjectedServicesAccess.getProcessesService().getProcessStatistics(ieProcessImpl.getId());
	}

	@Override
	public Set<String> getIEStatiticsAllLemmas() throws ANoteException {
		throw new ANoteException("Method not available in Server Access");
	}

	@Override
	public SortedSet<IRelationsType> getIEStatiticsAllDefaultRelationsType() throws ANoteException {
		throw new ANoteException("Method not available in Server Access");
	}

	@Override
	public List<IIEProcess> getPrivilegesAllProcessesAdminAccess() throws ANoteException {
		return SpringInjectedServicesAccess.getProcessesService().getPrivilegesAllProcessesAdminAccess();
	}

	@Override
	public List<IEventAnnotation> getAnnotatedDocumentEvents(IAnnotatedDocument annotedDocument) throws ANoteException {
		return SpringInjectedServicesAccess.getAnnotationService().getProcessDoumentAnnotationEvents(annotedDocument.getProcess().getId(), annotedDocument.getId());
	}

	@Override
	public List<IEntityAnnotation> getAnnotatedDocumentEntities(IAnnotatedDocument annotedDocument)
			throws ANoteException {
		return SpringInjectedServicesAccess.getAnnotationService().getProcessDoumentAnnotationEntities(annotedDocument.getId(), annotedDocument.getProcess().getId());
	}

	@Override
	public void addProcessDocumentEntitiesAnnotations(IIEProcess process, IPublication document,
			List<IEntityAnnotation> entityAnnotations) throws ANoteException {
		SpringInjectedServicesAccess.getAnnotationService().addCorpusProcessDocumentEntityAnootations(process.getCorpus().getId(), 
				process.getId(), document.getId(), entityAnnotations);
	}

	@Override
	public void addProcessDocumentEventAnnoations(IIEProcess process, IPublication document,
			List<IEventAnnotation> events) throws ANoteException {
		SpringInjectedServicesAccess.getAnnotationService().addCorpusProcessDocumentEventsAnootations(process.getCorpus().getId(), 
				process.getId(), document.getId(), events);
	}

	@Override
	public void inactiveAnnotations(List<Long> annotation) throws ANoteException {
		SpringInjectedServicesAccess.getAnnotationService().inactiveAnnotations(annotation);
	}

	@Override
	public void updateEntityAnnotations(List<IEntityAnnotation> list) throws ANoteException {
		SpringInjectedServicesAccess.getAnnotationService().updateEntityAnnotations(list);
	}

	@Override
	public void updateEventsAnnotations(List<IEventAnnotation> list) throws ANoteException {
		throw new ANoteException("Method not available in Server Access");
	}

	@Override
	public IAnnotatedDocumentStatistics getProcessDocumentStatistics(IAnnotatedDocument annotatedDocument)
			throws ANoteException {
		return SpringInjectedServicesAccess.getAnnotationService().getProcessDocumentStatistics(annotatedDocument.getId(), annotatedDocument.getProcess().getId());
	}

	@Override
	public SortedSet<IAnnotationLog> getProcessDocumentLogs(IIEProcess process, IPublication document)
			throws ANoteException {
		return SpringInjectedServicesAccess.getAnnotationService().getProcessDocumentLogs(process.getId(), document.getId());
	}

	@Override
	public SortedSet<IAnnotationLog> getSchemaLogs(IIEProcess process) throws ANoteException {
		return SpringInjectedServicesAccess.getAnnotationService().getProcessLogs(process.getId());
	}

	@Override
	public void addProcessDocumentLogs(List<IAnnotationLog> annotationLogs) throws ANoteException {
		SpringInjectedServicesAccess.getAnnotationService().addAnnotationLogs(annotationLogs);
	}

	@Override
	public List<IAnnotation> getAnnotationsRelatedToAnnotationLogs(IIEProcess ieProcess) throws ANoteException {
		IManualCurationAnnotations manualCuration = SpringInjectedServicesAccess.getAnnotationService().getProcessDocumentAnnotationsAssociatedToLogs(ieProcess.getId());
		return manualCuration.getAnnotations();
	}

	@Override
	public List<ICorpus> getPrivilegesAllCorpusAdminAccess() throws ANoteException {
		return SpringInjectedServicesAccess.getCorpusService().getAllPrivilegesCorpusAdminAccess();
	}

	@Override
	public void createResource(IResource<IResourceElement> resource) throws ANoteException {
		SpringInjectedServicesAccess.getResourcesService().create(resource);
	}

	@Override
	public void updateResource(IResource<IResourceElement> resource) throws ANoteException {
		SpringInjectedServicesAccess.getResourcesService().update(resource);
	}

	@Override
	public void inativeResource(IResource<IResourceElement> resource) throws ANoteException {
		throw new ANoteException("Method not available in Server Access");
	}

	@Override
	public void removeResource(IResource<IResourceElement> resource) throws ANoteException {
		SpringInjectedServicesAccess.getPrivilegesService().removePrivilegeLoggedUser(resource.getId(), ResourcesTypeUtils.resources.getName());
	}

	@Override
	public List<IResource<IResourceElement>> getResourcesByType(String type) throws ANoteException {
		return SpringInjectedServicesAccess.getResourcesService().getResourcesByType(type);
	}

	@Override
	public IResource<IResourceElement> getResourceByID(long resourceID) throws ANoteException {
		return SpringInjectedServicesAccess.getResourcesService().getResourcesById(resourceID);
	}

	@Override
	public Set<IAnoteClass> getResourceClassContent(IResource<IResourceElement> resource) throws ANoteException {
		return SpringInjectedServicesAccess.getResourcesElementService().getResourceClassContent(resource.getId());
	}

	@Override
	public boolean checkResourceHasElements(IResource<IResourceElement> resource) throws ANoteException {
		throw new ANoteException("Method not available in Server Access");
	}

	@Override
	public IResourceContent getResourceContent(IResource<IResourceElement> resource) throws ANoteException {
		return SpringInjectedServicesAccess.getResourcesElementService().getResourceContent(resource.getId());
	}

	@Override
	public IResourceManagerReport addResourceElements(IResource<IResourceElement> resource,
			List<IResourceElement> element) throws ANoteException {
		return SpringInjectedServicesAccess.getResourcesElementService().addResourceElements(resource.getId(), element);
	}

	@Override
	public IResourceManagerReport addResourceElementsWithouValidation(IResource<IResourceElement> resource,
			List<IResourceElement> element) throws ANoteException {
		return SpringInjectedServicesAccess.getResourcesElementService().addResourceElementsWithoutValidation(resource.getId(), element);
	}

	@Override
	public IResourceManagerReport addResourceElementSynomyns(IResource<IResourceElement> resource,
			IResourceElement elem, List<String> synonyms) throws ANoteException {
		return SpringInjectedServicesAccess.getResourcesElementService().addResourceElementSynonyms(resource.getId(), elem.getId(), synonyms);
	}

	@Override
	public IResourceManagerReport addResourceElementExternalIds(IResource<IResourceElement> resource,
			IResourceElement elem, List<IExternalID> externalIDs) throws ANoteException {
		return SpringInjectedServicesAccess.getResourcesElementService().addResourceElementExternalIDs(resource.getId(), elem.getId(), externalIDs);
	}

	@Override
	public IResourceManagerReport updateResourceElement(IResourceElement elem) throws ANoteException {
		return SpringInjectedServicesAccess.getResourcesElementService().updateResourceElement(elem);
	}

	@Override
	public void inactivateResourceElement(IResourceElement elem) throws ANoteException {
		SpringInjectedServicesAccess.getResourcesElementService().inactivateResourceElement(elem.getId());
	}

	@Override
	public IResourceElementSet<IResourceElement> getResourceElements(IResource<IResourceElement> resource)
			throws ANoteException {
		return SpringInjectedServicesAccess.getResourcesElementService().getResourceElements(resource.getId());
	}

	@Override
	public IResourceElementSet<IResourceElement> getResourceElementsInBatchWithLimit(
			IResource<IResourceElement> resource, Integer index, Integer pagination) throws ANoteException {
		return SpringInjectedServicesAccess.getResourcesElementService().getResourceElementsInBatchWithLimit(resource.getId(), index, pagination);
	}

	@Override
	public List<IResourceElementsRelation> getResourceElementsRelations(IResource<IResourceElement> resource)
			throws ANoteException {
		return SpringInjectedServicesAccess.getResourcesElementService().getResourceElementsRelations(resource.getId());
	}

	@Override
	public void removeResourceClass(IResource<IResourceElement> resource, long classID) throws ANoteException {
		SpringInjectedServicesAccess.getResourcesElementService().removeResourceClass(resource.getId(), classID);
	}

	@Override
	public long getResourceElementIDMathingByText(IResource<IResourceElement> resource, IResourceElement elemennt,
			String text, boolean casesensitive) throws ANoteException {
		throw new ANoteException("Method not available in Server Access");
	}

	@Override
	public IResourceElementSet<IResourceElement> getResourceElementsByName(IResource<IResourceElement> resource,
			String name) throws ANoteException {
		return SpringInjectedServicesAccess.getResourcesElementService().getResourceElementsByName(resource.getId(), name);
	}

	@Override
	public IResourceElement getResourceElementByID(long resourceElementID) throws ANoteException {
		return SpringInjectedServicesAccess.getResourcesElementService().getResourceElemenByID(resourceElementID);
	}

	@Override
	public void removeResourceElementSynonyms(IResourceElement elem) throws ANoteException {
		SpringInjectedServicesAccess.getResourcesElementService().removeResourceElementSynonyms(elem.getId());
	}

	@Override
	public void removeResourceElementSynonym(IResourceElement elem, String synonym) throws ANoteException {
		SpringInjectedServicesAccess.getResourcesElementService().removeResourceElementSynonym(elem.getId(), synonym);
	}

	@Override
	public int getResourceMaxPriority(IResource<IResourceElement> resourceElment) throws ANoteException {
		return SpringInjectedServicesAccess.getResourcesElementService().getResourceMaxPriorety(resourceElment.getId());
	}

	@Override
	public void addResourceElementsRelation(IResourceElement resourceElmentIDa, IResourceElement resourceElmentIDb, String relationType)
			throws ANoteException {
		SpringInjectedServicesAccess.getResourcesElementService().addResourceElementsRelation(resourceElmentIDa.getId(), resourceElmentIDb.getId(), relationType);
	}

	@Override
	public IResourceElementSet<IResourceElement> getResourceElementsByClass(IResource<IResourceElement> resourceElement,
			IAnoteClass termClass) throws ANoteException {
		return SpringInjectedServicesAccess.getResourcesElementService().getResourceElementsByClass(resourceElement.getId(), termClass.getName());
	}

	@Override
	public List<IExternalID> getResourceElementExternalIds(IResourceElement resourceElement) throws ANoteException {
		return SpringInjectedServicesAccess.getResourcesElementService().getResourceElementExternalIDs(resourceElement.getId());
	}

	@Override
	public boolean checkResourceElementExistsInResource(IResource<IResourceElement> resourceElement, String term)
			throws ANoteException {
		return SpringInjectedServicesAccess.getResourcesElementService().checkResourceElementExistsInResource(resourceElement.getId(), term);
	}

	@Override
	public IResourceManagerReport updateResourceElementSynonym(IResource<IResourceElement> resource,
			IResourceElement elem, String oldSynonym, String newSynonym) throws ANoteException {
		return SpringInjectedServicesAccess.getResourcesElementService().updateResourceElementSynonym(resource.getId(), elem.getId(), oldSynonym, newSynonym);
	}

	@Override
	public void removeResourceElementExternalID(IResourceElement element, IExternalID extID) throws ANoteException {
		SpringInjectedServicesAccess.getResourcesElementService().removeResourceElementExternalID(element.getId(), extID);
	}

	@Override
	public void removeResourceElementAllExternalID(IResourceElement element) throws ANoteException {
		SpringInjectedServicesAccess.getResourcesElementService().removeResourceElementAllExternalID(element.getId());
	}

	@Override
	public IResource<IResourceElement> getResourceFromResourceElementByID(Long resourceElementID)
			throws ANoteException {
		return SpringInjectedServicesAccess.getResourcesElementService().getResourceFromResourceElement(resourceElementID);
	}

	@Override
	public int getDatabaseVersion() throws ANoteException {
		throw new ANoteException("Method not available in Server Access");
	}

	@Override
	public boolean addDatabaseVersion(int version, String commments) throws ANoteException {
		throw new ANoteException("Method not available in Server Access");
	}

	@Override
	public void createUser(IUser user) throws ANoteException {
		SpringInjectedServicesAccess.getUserService().createUser(user);
	}

	@Override
	public IUser getUserByEmail(String email) throws ANoteException {
		return SpringInjectedServicesAccess.getUserService().getByEmail(email);
	}

	@Override
	public IUser getUserByUsername(String username) throws ANoteException {
		return SpringInjectedServicesAccess.getUserService().getByUsername(username);
	}

	@Override
	public void updateUser(IUser user) throws ANoteException {
		SpringInjectedServicesAccess.getUserService().updateUser(user);
		
	}

	@Override
	public void removeUser(IUser user) throws ANoteException {
		SpringInjectedServicesAccess.getUserService().removeUser(user.getAuId());
	}

	@Override
	public List<IGroup> getAllGroups() throws ANoteException {
		return SpringInjectedServicesAccess.getUserService().getAllGroups();
	}

	@Override
	public List<IUser> getAllUsers() throws ANoteException {
		List<IUser> returned = new ArrayList<IUser>();
		List<AuthUsers> users =  SpringInjectedServicesAccess.getUserService().getAllUsers();
		for (AuthUsers iuser : users) {
			returned.add(iuser);
		}
		return returned;
	}

	@Override
	public IUserDataObject getUserDataObjectPrivilege(IUser user, Long resourceId, ResourcesTypeUtils resource)
			throws ANoteException {
		return SpringInjectedServicesAccess.getPrivilegesService().getPrivilege(user.getAuId(), resourceId, resource.getName());
	}

	@Override
	public Boolean hasPermissionRole(List<String> roles) throws ANoteException {
		return SpringInjectedServicesAccess.getUserService().hasPermissionRole(roles);
	}

	@Override
	public Boolean hasPermission(IQuery query, List<String> permission) throws ANoteException {
		return SpringInjectedServicesAccess.getPrivilegesService().hasPermission(query.getId(), ResourcesTypeUtils.queries.getName(), permission);
	}

	@Override
	public Boolean hasPermission(IResource<IResourceElement> resource, List<String> permission) throws ANoteException {
		return SpringInjectedServicesAccess.getPrivilegesService().hasPermission(resource.getId(), ResourcesTypeUtils.resources.name(), permission);
	}

	@Override
	public Boolean hasPermission(ICorpus corpus, List<String> premission) throws ANoteException {
		return SpringInjectedServicesAccess.getPrivilegesService().hasPermission(corpus.getId(), ResourcesTypeUtils.corpus.name(), premission);
	}

	@Override
	public Boolean hasPermission(IIEProcess process, List<String> permission) throws ANoteException {
		return SpringInjectedServicesAccess.getPrivilegesService().hasPermission(process.getId(), ResourcesTypeUtils.processes.name(), permission);
	}

	@Override
	public List<IGenericPair<IUser, String>> getUsersAndPermissions(Long resourceId, ResourcesTypeUtils resource)
			throws ANoteException {
		return SpringInjectedServicesAccess.getPrivilegesService().getUsersAndPermissions(resourceId, resource.name());
	}

	@Override
	public void addUserDataObjectPrivilege(IUser user, Long resourceId, ResourcesTypeUtils resource,
			PermissionsUtilsEnum privilege) throws ANoteException {
		SpringInjectedServicesAccess.getPrivilegesService().addPrivilege(user.getAuId(), resourceId, resource.getName(), privilege.getName());
	}

	@Override
	public void updateUserDataObjectPrivilege(IUser user, Long resourceId, ResourcesTypeUtils resource,
			PermissionsUtilsEnum privilege) throws ANoteException {
		SpringInjectedServicesAccess.getPrivilegesService().updatePrivilege(user.getAuId(), resourceId, resource.getName(), privilege.getName());
	}

	@Override
	public void removeUserDataObjectPrivilege(IUser user, Long resourceId, ResourcesTypeUtils resource)
			throws ANoteException {
		SpringInjectedServicesAccess.getPrivilegesService().removePrivilege(user.getAuId(), resourceId, resource.getName());
	}

	@Override
	public IHyperLinkMenuItem getHyperLinkMenuItemById(Long id) throws ANoteException {
		return SpringInjectedServicesAccess.getHyperLinkService().getHyperLinkMenuItemByID(id);
	}

	@Override
	public List<IHyperLinkMenuItem> getAllHyperLinkMenuItems() throws ANoteException {
		return SpringInjectedServicesAccess.getHyperLinkService().getAllHyperLinkMenuItems();
	}

	@Override
	public void updateSourcesHyperLinkMenuItem(IHyperLinkMenuItem hyperLinkMenuItem) throws ANoteException {
		SpringInjectedServicesAccess.getHyperLinkService().updateSourcesHyperLinkMenuItem(hyperLinkMenuItem);
	}

	@Override
	public List<ISource> getAllSources() throws ANoteException {
		return SpringInjectedServicesAccess.getHyperLinkService().getAllSources();
	}

	@Override
	public List<IHyperLinkMenuItem> getHyperLinkMenuItemsForSource(ISource source) throws ANoteException {
		return SpringInjectedServicesAccess.getHyperLinkService().getHyperLinkMenuItemsForSource(source.getSourceID());
	}

	@Override
	public Boolean runServerProcesses(String klass, String configuration) throws ANoteException {
		throw new ANoteException("Method not available in Server Access");
	}

	@Override
	public IUser getUser() {
		return SpringInjectedServicesAccess.getUsersLogged().getCurrentUserLogged();
	}

	@Override
	public void setUser(UsersLogged userLogged) throws ANoteException {
		throw new ANoteException("Method not available in Server Access");
	}

	@Override
	public void login(String username, String password) throws ANoteException {
		SpringInjectedServicesAccess.getUserService().login(username, password);
	}

	@Override
	public Boolean checkLogin(String username, String password) throws ANoteException {
		return SpringInjectedServicesAccess.getUserService().checkLogin(username, password);
	}

	@Override
	public void logout() throws ANoteException {
		throw new ANoteException("Method not available in Server Access");
	}

	@Override
	public void saveProperties(Properties properties) throws ANoteException {
		SpringInjectedServicesAccess.getUserService().saveProperties(properties);
	}

	@Override
	public Properties loadProperties(Set<String> propertiesIdentifiers) throws ANoteException {
		return SpringInjectedServicesAccess.getUserService().loadProperties(propertiesIdentifiers);
	}
	
	@Override
	public IResourceElementSet<IResourceElement> getResourceElementsByExternalID(IExternalID externalId)
			throws ANoteException {
		return SpringInjectedServicesAccess.getResourcesElementService().getResourceElementsByExternalID(externalId);
	}
	
	public void setUserLoggedOnServices(UsersLogged userLogged){
		SpringInjectedServicesAccess.setUserLoggedOnServices(userLogged);
	}

	@Override
	public boolean rebuildLuceneIndex() throws ANoteException {
		return SpringInjectedServicesAccess.getLuceneService().rebuildLuceneIndex();
	}

	@Override
	public List<Long> getPublicationsIdsByResourceElements(Set<IResourceElement> resourceElements) throws ANoteException {
		Set<Long> resourceElementsIds = new HashSet<>();
		for(IResourceElement resourceElement : resourceElements){
			resourceElementsIds.add(resourceElement.getId());
		}
		return SpringInjectedServicesAccess.getAnnotationService().getPublicationsIdsByResourceElements(resourceElementsIds);
	}



	@Override
	public IResourceManagerReport addResourceElementSynomynsWithoutValidation(IResource<IResourceElement> destiny,
			IResourceElement originalElem, List<String> synonymsToAdd) throws ANoteException {
		return SpringInjectedServicesAccess.getResourcesElementService().addResourceElementSynonymsWithoutValidation(destiny.getId(), originalElem.getId(), synonymsToAdd);
	}



	@Override
	public IResourceElementSet<IResourceElement> getResourceElementsByExactTerm(String term) throws ANoteException {
		return SpringInjectedServicesAccess.getResourcesElementLuceneService().getResourceElementsByExactTerm(term);
	}


	@Override
	public IResourceElementSet<IResourceElement> getResourceElementsByPartialTerm(String term) throws ANoteException {
		return SpringInjectedServicesAccess.getResourcesElementLuceneService().getResourceElementsByPartialTerm(term);
	}



	@Override
	public IResourceElementSet<IResourceElement> getResourceElementsByPartialTermPaginated(String term, int index,
			int paginationSize) throws ANoteException {
		return SpringInjectedServicesAccess.getResourcesElementLuceneService().getResourceElementsByPartialTermPaginated(term, index, paginationSize);
	}

	@Override
	public IResourceElementSet<IResourceElement> getResourceElementsByExactSynonym(String synonym)
			throws ANoteException {
		return SpringInjectedServicesAccess.getResourcesElementLuceneService().getResourceElementsByExactSynonym(synonym);
	}


	@Override
	public IResourceElementSet<IResourceElement> getResourceElementsByPartialSynonym(String synonym)
			throws ANoteException {
		return SpringInjectedServicesAccess.getResourcesElementLuceneService().getResourceElementsByPartialSynonym(synonym);
	}



	@Override
	public IResourceElementSet<IResourceElement> getResourceElementsByPartialSynonymPaginated(String synonym, int index,
			int paginationSize) throws ANoteException {
		return SpringInjectedServicesAccess.getResourcesElementLuceneService().getResourceElementsByPartialSynonymPaginated(synonym, index, paginationSize);
	}


	@Override
	public IResourceElementSet<IResourceElement> getResourceElementsByExactExternalId(String externalId)
			throws ANoteException {
		return SpringInjectedServicesAccess.getResourcesElementLuceneService().getResourceElementsByExactExternalID(externalId);
	}


	@Override
	public IResourceElementSet<IResourceElement> getResourceElementsByPartialExternalId(String partialExternalId)
			throws ANoteException {
		return SpringInjectedServicesAccess.getResourcesElementLuceneService().getResourceElementsByPartialExternalID(partialExternalId);
	}


	@Override
	public IResourceElementSet<IResourceElement> getResourceElementsByPartialExternalIdPaginated(
			String partialExternalId, int index, int paginationSize) throws ANoteException {
		return SpringInjectedServicesAccess.getResourcesElementLuceneService().getResourceElementsByPartialExternalIDPaginated(partialExternalId, index, paginationSize);
	}


	@Override
	public Integer getResourceElementsCountByPartialTerm(String partialTerm) throws ANoteException {
		return SpringInjectedServicesAccess.getResourcesElementLuceneService().getResourceElementsCountByPartialTerm(partialTerm);
	}

	@Override
	public Integer getResourceElementsCountByPartialSynonym(String partialSynonym) throws ANoteException {
		return SpringInjectedServicesAccess.getResourcesElementLuceneService().getResourceElementsCountByPartialSynonym(partialSynonym);
	}

	@Override
	public IResourceElementSet<IResourceElement> getResourceElementsByPartialTermOrPartialSynonymPaginated(
			String partialString, int index, int paginationSize) throws ANoteException {
		return SpringInjectedServicesAccess.getResourcesElementLuceneService().getResourceElementsByPartialTermOrPartialSynonymPaginated(partialString, index, paginationSize);
	}

	@Override
	public Integer getResourceElementsCountByPartialTermOrPartialSynonym(String partialString) throws ANoteException {
		return SpringInjectedServicesAccess.getResourcesElementLuceneService().getResourceElementsCountByPartialTermOrPartialSynonym(partialString);
	}



	@Override
	public IResourceElementSet<IResourceElement> getResourceElementsFilteredByExactTerm(IResourceElementsFilter filter,
			String term) throws ANoteException {
		return SpringInjectedServicesAccess.getResourcesElementLuceneService().getResourceElementsFilteredByExactTerm(filter, term);
	}



	@Override
	public IResourceElementSet<IResourceElement> getResourceElementsFilteredByPartialTerm(
			IResourceElementsFilter filter, String partialTerm) throws ANoteException {
		return SpringInjectedServicesAccess.getResourcesElementLuceneService().getResourceElementsFilteredByPartialTerm(filter, partialTerm);
	}



	@Override
	public IResourceElementSet<IResourceElement> getResourceElementsFilteredByPartialTermPaginated(
			IResourceElementsFilter filter, String partialTerm, int index, int paginationSize) throws ANoteException {
		return SpringInjectedServicesAccess.getResourcesElementLuceneService().getResourceElementsFilteredByPartialTermPaginated(filter, partialTerm, index, paginationSize);
	}



	@Override
	public IResourceElementSet<IResourceElement> getResourceElementsFilteredByExactSynonym(
			IResourceElementsFilter filter, String synonym) throws ANoteException {
		return SpringInjectedServicesAccess.getResourcesElementLuceneService().getResourceElementsFilteredByExactSynonym(filter, synonym);
	}



	@Override
	public IResourceElementSet<IResourceElement> getResourceElementsFilteredByPartialSynonym(
			IResourceElementsFilter filter, String partialSynonym) throws ANoteException {
		return SpringInjectedServicesAccess.getResourcesElementLuceneService().getResourceElementsFilteredByPartialSynonym(filter, partialSynonym);
	}



	@Override
	public IResourceElementSet<IResourceElement> getResourceElementsFilteredByPartialSynonymPaginated(
			IResourceElementsFilter filter, String partialSynonym, int index, int paginationSize)
			throws ANoteException {
		return SpringInjectedServicesAccess.getResourcesElementLuceneService().getResourceElementsFilteredByPartialSynonymPaginated(filter, partialSynonym, index, paginationSize);
	}



	@Override
	public IResourceElementSet<IResourceElement> getResourceElementsFilteredByExactExternalId(
			IResourceElementsFilter filter, String externalId) throws ANoteException {
		return SpringInjectedServicesAccess.getResourcesElementLuceneService().getResourceElementsFilteredByExactExternalID(filter, externalId);
	}



	@Override
	public IResourceElementSet<IResourceElement> getResourceElementsFilteredByPartialExternalId(
			IResourceElementsFilter filter, String partialExternalId) throws ANoteException {
		return SpringInjectedServicesAccess.getResourcesElementLuceneService().getResourceElementsFilteredByPartialExternalID(filter, partialExternalId);
	}



	@Override
	public IResourceElementSet<IResourceElement> getResourceElementsFilteredByPartialExternalIdPaginated(
			IResourceElementsFilter filter, String partialExternalId, int index, int paginationSize)
			throws ANoteException {
		return SpringInjectedServicesAccess.getResourcesElementLuceneService().getResourceElementsFilteredByPartialExternalIDPaginated(filter, partialExternalId, index, paginationSize);
	}



	@Override
	public Integer getResourceElementsFilteredCountByPartialTerm(IResourceElementsFilter filter, String partialTerm)
			throws ANoteException {
		return SpringInjectedServicesAccess.getResourcesElementLuceneService().getResourceElementsFilteredCountByPartialTerm(filter, partialTerm);
	}



	@Override
	public Integer getResourceElementsFilteredCountByPartialSynonym(IResourceElementsFilter filter,
			String partialSynonym) throws ANoteException {
		return SpringInjectedServicesAccess.getResourcesElementLuceneService().getResourceElementsFilteredCountByPartialSynonym(filter, partialSynonym);
	}



	@Override
	public Integer getResourceElementsCountByPartialExternalID(String partialExternalId) throws ANoteException {
		return SpringInjectedServicesAccess.getResourcesElementLuceneService().getResourceElementsCountByPartialExternalID(partialExternalId);
	}



	@Override
	public Integer getResourceElementsFilteredCountByPartialExternalID(IResourceElementsFilter filter,
			String partialExternalId) throws ANoteException {
		return SpringInjectedServicesAccess.getResourcesElementLuceneService().getResourceElementsFilteredCountByPartialExternalID(filter, partialExternalId);
	}



	@Override
	public List<Long> getProcessesIdsByResourceElements(Set<IResourceElement> resourceElements) throws ANoteException {
		Set<Long> resourceElementsIds = new HashSet<>();
		for(IResourceElement resourceElement : resourceElements){
			resourceElementsIds.add(resourceElement.getId());
		}
		return SpringInjectedServicesAccess.getAnnotationService().getProcessesIdsByResourceElements(resourceElementsIds);
	}



	@Override
	public List<IIEProcess> getProcessesByPublication(IPublication publication) throws ANoteException {
		return SpringInjectedServicesAccess.getProcessesService().getProcessesByPublicationId(publication.getId());
	}



	@Override
	public Set<ICorpus> getCorpusByPublication(IPublication publication) throws ANoteException {
		return SpringInjectedServicesAccess.getCorpusService().getCorpusByPublicationId(publication.getId());
	}



	@Override
	public List<Long> getPublicationsIdsByAnnotationsFilter(IAnnotationsFilter filter) throws ANoteException {
		return SpringInjectedServicesAccess.getAnnotationService().getPublicationsIdsByAnnotationsFilter(filter);
	}



	@Override
	public Boolean removeAllProcessDocumentAnnotations(IIEProcess process, IPublication document)
			throws ANoteException {
		return SpringInjectedServicesAccess.getAnnotationService().removeAllProcessDocumentAnnotations(process.getId(),document.getId());
	}



	@Override
	public Long countCorpusPublicationsOutdated(IIEProcess process) throws ANoteException {
		return SpringInjectedServicesAccess.getCorpusService().countCorpusPublicationsOutdated(process.getCorpus().getId(), process.getId());
	}



	@Override
	public IDocumentSet getCorpusPublicationsOutdatedPaginated(IIEProcess process, Integer paginationIndex, Integer paginationSize) throws ANoteException {
		return SpringInjectedServicesAccess.getCorpusService().getCorpusPublicationsOutdatedPaginated(process.getCorpus().getId(), process.getId(), paginationIndex, paginationSize);
	}
	
	@Override
	public List<IQuery> getQueriesByName(String name) throws ANoteException {
		return SpringInjectedServicesAccess.getQueriesLuceneService().getQueriesByName(name);
//		return null;
	}



	public Boolean autoupdate() throws ANoteException {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public Long getNextHyperLinkMenuItemID() throws ANoteException {
		return SpringInjectedServicesAccess.getHyperLinkService().getNextHyperLinkMenusItemID();
	}



	@Override
	public void addHyperLinkMenuItem(IHyperLinkMenuItem hyperLinkMenuItem) throws ANoteException {
		SpringInjectedServicesAccess.getHyperLinkService().addHyperLinkMenuItem(hyperLinkMenuItem);
	}



	@Override
	public void removeHyperLinkMenuItem(IHyperLinkMenuItem hyperLinkMenuItem) throws ANoteException {
		SpringInjectedServicesAccess.getHyperLinkService().removeHyperLinkMenuItem(hyperLinkMenuItem.getId());
	}



	@Override
	public void updateHyperLinkMenuItem(IHyperLinkMenuItem hyperLinkMenuItem) throws ANoteException {
		SpringInjectedServicesAccess.getHyperLinkService().updateHyperLinkMenuItem(hyperLinkMenuItem);
	}

	public List<IQuery> getQueriesByOrganism(String organism) throws ANoteException {
		return SpringInjectedServicesAccess.getQueriesLuceneService().getQueriesByOrganism(organism);
	//	return null;
	}



	@Override
	public List<IQuery> getQueriesBykeywords(String keywords) throws ANoteException {
		return SpringInjectedServicesAccess.getQueriesLuceneService().getQueriesBykeywords(keywords);
	//	return null;
	}



	@Override
	public List<IQuery> getQueriesKeywordsByWildCard(String subKeyword) throws ANoteException {
		return SpringInjectedServicesAccess.getQueriesLuceneService().getQueriesKeywordsByWildCard(subKeyword);
		//	return null;
	}



	@Override
	public List<String> getKeywordsOfQueriesByWildCard(String subKeyword) throws ANoteException {
		return SpringInjectedServicesAccess.getQueriesLuceneService().getKeywordsOfQueriesByWildCard(subKeyword);
		//	return null;
	}
	
	
	@Override
	public  List<IPublication> getPublicationsByTitle(String title) throws ANoteException{
		return SpringInjectedServicesAccess.getPublicationsLuceneService().getPublicationsByTitle(title);
	}



	@Override
	public List<IPublication> getPublicationsFromSearch(ISearchProperties searchProperties) throws ANoteException {
		return SpringInjectedServicesAccess.getPublicationsLuceneService().getPublicationsFromSearch(searchProperties);
	}



	@Override
	public List<IPublication> getPublicationsFromSearchPaginated(ISearchProperties searchProperties, int index,
			int paginationSize) throws ANoteException {
		return SpringInjectedServicesAccess.getPublicationsLuceneService().getPublicationsFromSearchPaginated(searchProperties, index, paginationSize);
		
	}



	@Override
	public Integer countGetPublicationsFromSearch(ISearchProperties searchProperties) throws ANoteException {
		return SpringInjectedServicesAccess.getPublicationsLuceneService().countGetPublicationsFromSearch(searchProperties);
		
	}



	@Override
	public Integer countGetCorpusFrom(ISearchProperties searchProperties) throws ANoteException {
		// TODO Auto-generated method stub
		return SpringInjectedServicesAccess.getCorpusLuceneService().countCorpusFromSearch(searchProperties);
		
	}



	@Override
	public List<ICorpus> getCorpusFromSearchPaginated(ISearchProperties searchProperties, int index, int paginationSize)
			throws ANoteException {
		// TODO Auto-generated method stub
		 return SpringInjectedServicesAccess.getCorpusLuceneService().getCorpusFromSearchPaginated(searchProperties, index, paginationSize);
	}



	@Override
	public Integer countResourcesFromSearchWAuth(ISearchProperties searchProperties) throws ANoteException {
		return SpringInjectedServicesAccess.getResourcesLuceneService().countResourcesFromSearchWAuth(searchProperties);
	}
	
	@Override
	public Integer countAllPublications() throws ANoteException {
		return SpringInjectedServicesAccess.getPublicationsService().countAllPublications();
	}



	@Override
	public void addDataProcessStatus(IDataProcessStatus dataprocessStatus) throws ANoteException {
		SpringInjectedServicesAccess.getDataProcessStatusService().addDataProcessStatus(dataprocessStatus);
	}



	@Override
	public void updateDataProcessStatus(IDataProcessStatus dataProcessStatus) throws ANoteException {
		SpringInjectedServicesAccess.getDataProcessStatusService().updateDataProcessStatus(dataProcessStatus);
		
	}



	@Override
	public List<IDataProcessStatus> getAllDataProcessStatus() throws ANoteException {
		return SpringInjectedServicesAccess.getDataProcessStatusService().getAllDataProcessStatus();
	}


	@Override
	public List<IDataProcessStatus> getDataProcessStatusByUserAndDateRange(IUser user, Date startDateRange,
			Date endDateRange) throws ANoteException {
		return null;
	}

	@Override
	public List<IEntityAnnotation> getAnnotatedDocumentEntitiesFilteredByResourceElement(IIEProcess process,
			IPublication publication, IResourceElement resourceElement) throws ANoteException {
		return SpringInjectedServicesAccess.getAnnotationService().getProcessDoumentAnnotationEntitiesFilteredByResourceElement(publication.getId(), process.getId(), resourceElement.getId());
	}

	@Override
	public List<Long> getPublicationsIdsByResourceElementsFilteredByPublicationFilter(
			Set<IResourceElement> resourceElements, IPublicationFilter pubFilter) throws ANoteException {
		Set<Long> resourceElementsIds = new HashSet<>();
		for(IResourceElement resourceElement : resourceElements)
			resourceElementsIds.add(resourceElement.getId());
		return SpringInjectedServicesAccess.getAnnotationService().getPublicationsIdsByResourceElementsFilteredByPublicationFilter(resourceElementsIds, pubFilter);
	}

	@Override
	public ISentence getSentence(IEntityAnnotation entityAnnotation) throws ANoteException, IOException {
		return SpringInjectedServicesAccess.getAnnotationService().getSentence(entityAnnotation.getId());
	}



	@Override
	public List<IEntityAnnotation> getProcessDoumentAnnotationEntitiesOfSentence(IIEProcess process,
			IPublication publication, ISentence sentence) throws ANoteException {
		return SpringInjectedServicesAccess.getAnnotationService().getProcessDoumentAnnotationEntitiesOfSentence(publication.getId(), process.getId(), sentence);
	}


	@Override
	public List<IResource<IResourceElement>> getAllPrivilegesResources() throws ANoteException {
		return SpringInjectedServicesAccess.getResourcesService().getAllPrivilegesResources();
	}

	@Override
	public Long countAnnotationsByResourceElement(IIEProcess process, IResourceElement resourceElement) throws ANoteException {
		return SpringInjectedServicesAccess.getAnnotationService().countAnnotationsByResourceElement(process.getId(), resourceElement.getId()); 
	}



	@Override
	public List<IPublication> getPublicationsFromSearchPaginatedWSort(ISearchProperties searchProperties, int index,
			int paginationSize, boolean asc, String sortBy) throws ANoteException {
		return  SpringInjectedServicesAccess.getPublicationsLuceneService().getPublicationsFromSearchPaginatedWSort(searchProperties, index, paginationSize, asc, sortBy);
	}



	@Override
	public IResourceElementSet<IResourceElement> getResourceElementsByExactExternalID(String externalId)
			throws ANoteException {
		return SpringInjectedServicesAccess.getResourcesElementLuceneService().getResourceElementsByExactExternalID(externalId);
	}



	@Override
	public IResourceElementSet<IResourceElement> getResourceElementsFilteredByExactExternalID(
			IResourceElementsFilter filter, String externalId) throws ANoteException {
		return SpringInjectedServicesAccess.getResourcesElementLuceneService().getResourceElementsFilteredByExactExternalID(filter, externalId);
	}



	@Override
	public IResourceElementSet<IResourceElement> getResourceElementsByPartialExternalID(String partialString)
			throws ANoteException {
		return SpringInjectedServicesAccess.getResourcesElementLuceneService().getResourceElementsByPartialExternalID(partialString);
	}



	@Override
	public IResourceElementSet<IResourceElement> getResourceElementsFilteredByPartialExternalID(
			IResourceElementsFilter filter, String partialString) throws ANoteException {
		return SpringInjectedServicesAccess.getResourcesElementLuceneService().getResourceElementsFilteredByPartialExternalID(filter, partialString);
	}



	@Override
	public IResourceElementSet<IResourceElement> getResourceElementsByPartialExternalIDPaginated(String partialString,
			int index, int paginationSize) throws ANoteException {
		return SpringInjectedServicesAccess.getResourcesElementLuceneService().getResourceElementsByPartialExternalIDPaginated(partialString, index, paginationSize);
	}



	@Override
	public IResourceElementSet<IResourceElement> getResourceElementsFilteredByPartialExternalIDPaginated(
			IResourceElementsFilter filter, String partialString, int index, int paginationSize) throws ANoteException {
		return SpringInjectedServicesAccess.getResourcesElementLuceneService().getResourceElementsFilteredByPartialExternalIDPaginated(filter, partialString, index, paginationSize);
	}



	@Override
	public IResourceElementSet<IResourceElement> getResourceElementsByExactTermOrExactSynonymPaginated(
			String exactString, int index, int paginationSize) throws ANoteException {
		return SpringInjectedServicesAccess.getResourcesElementLuceneService().getResourceElementsByExactTermOrExactSynonymPaginated(exactString,index, paginationSize);
	}



	@Override
	public Integer getCountResourceElementsByExactTermOrExactSynonymPaginated(String exactString)
			throws ANoteException {
		return SpringInjectedServicesAccess.getResourcesElementLuceneService().getCountResourceElementsByExactTermOrExactSynonymPaginated(exactString);
	}



	@Override
	public IResourceElementSet<IResourceElement> getResourceElementsPaginated(ISearchProperties searchProperties,
			int index, int paginationSize, boolean asc, String sortBy) throws ANoteException {
		return SpringInjectedServicesAccess.getResourcesElementLuceneService().getResourceElementsPaginated(searchProperties, index, paginationSize, asc, sortBy);
	}



	@Override
	public Integer countResourceElements(ISearchProperties searchProperties) throws ANoteException {
		return SpringInjectedServicesAccess.getResourcesElementLuceneService().countResourceElements(searchProperties);
	}



	@Override
	public List<IResource<IResourceElement>> getResourcesFromSearchPaginatedWAuth(ISearchProperties searchProperties,
			int index, int paginationSize) throws ANoteException {
		return SpringInjectedServicesAccess.getResourcesLuceneService().getResourcesFromSearchPaginatedWAuth(searchProperties, index, paginationSize);
	}



	@Override
	public Integer countActiveResourcesFromSearch(ISearchProperties searchProperties) throws ANoteException {
		return SpringInjectedServicesAccess.getResourcesLuceneService().countActiveResourcesFromSearch(searchProperties);
	}



	@Override
	public Integer countResourcesFromSearchWAuth(ISearchProperties searchProperties, String permission)
			throws ANoteException {
		return SpringInjectedServicesAccess.getResourcesLuceneService().countResourcesFromSearchWAuth(searchProperties, permission);
	}



	@Override
	public List<IResource<IResourceElement>> getActiveResourcesFromSearchPaginatedWSort(
			ISearchProperties searchProperties, int index, int paginationSize, boolean asc, String sortBy)
			throws ANoteException {
		return SpringInjectedServicesAccess.getResourcesLuceneService().getActiveResourcesFromSearchPaginatedWSort(searchProperties, index, paginationSize, asc, sortBy);
	}



	@Override
	public List<IResource<IResourceElement>> getResourcesFromSearchPaginatedWAuthAndSort(
			ISearchProperties searchProperties, int index, int paginationSize, boolean asc, String sortBy)
			throws ANoteException {
		return SpringInjectedServicesAccess.getResourcesLuceneService().getResourcesFromSearchPaginatedWAuthAndSort(searchProperties, index, paginationSize, asc, sortBy);
	}



	@Override
	public List<IResource<IResourceElement>> getResourcesFromSearchPaginatedWAuthAndSort(
			ISearchProperties searchProperties, int index, int paginationSize, boolean asc, String sortBy,
			String permission) throws ANoteException {
		return SpringInjectedServicesAccess.getResourcesLuceneService().getResourcesFromSearchPaginatedWAuthAndSort(searchProperties, index, paginationSize, asc, sortBy, permission);
	}



	@Override
	public List<IResource<IResourceElement>> getPrivilegesResourcesAdminAccessFromSearchPaginated(
			ISearchProperties searchProperties, Integer paginationIndex, Integer paginationSize, boolean asc,
			String sortBy) throws ANoteException {
		return SpringInjectedServicesAccess.getResourcesLuceneService().getPrivilegesResourcesAdminAccessFromSearchPaginated(searchProperties, paginationIndex, paginationSize, asc, sortBy);
	}



	@Override
	public Integer countPrivilegesResourcesAdminAccessFromSearch(ISearchProperties searchProperties)
			throws ANoteException {
		return SpringInjectedServicesAccess.getResourcesLuceneService().countPrivilegesResourcesAdminAccessFromSearch(searchProperties);
	}



	@Override
	public List<IPublication> getQueryPublicationsPaginated(IQuery query, Integer paginationIndex,
			Integer paginationSize, Boolean asc, String sortBy) throws ANoteException {
		return SpringInjectedServicesAccess.getQueriesService().getQueryPublicationsPaginated(query.getId(), paginationIndex, paginationSize, asc, sortBy);
	}



	@Override
	public Long countQueryPublications(IQuery query) throws ANoteException {
		return SpringInjectedServicesAccess.getQueriesService().getQueryPublicationsCount(query.getId());
	}



	@Override
	public List<Long> getCorpusPublicationsFromExternalID(ICorpus corpus, String source, String internalSourceId)
			throws ANoteException {
		return SpringInjectedServicesAccess.getCorpusService().getCorpusPublicationsFromExternalID(corpus.getId(), source, internalSourceId);
	}



	@Override
	public Long countAnnotationsByAnnotationType(IIEProcess process, AnnotationType annotType) throws ANoteException {
		return SpringInjectedServicesAccess.getAnnotationService().countAnnotationsByAnnotionType(process.getId(), annotType.name());
	}



	@Override
	public Map<IAnoteClass, Long> countEntityAnnotationsByClassInProcess(IIEProcess process) throws ANoteException {
		return SpringInjectedServicesAccess.getAnnotationService().countEntityAnnotationsByClassInProcess(process.getId());
	}



	@Override
	public Map<IResourceElement, Long> countEntityAnnotationsByResourceElementInProcess(IIEProcess process)
			throws ANoteException {
		return SpringInjectedServicesAccess.getAnnotationService().countEntityAnnotationsByResourceElementInProcess(process.getId());
	}



	@Override
	public Map<IResourceElement, Long> countAnnotationsByResourceElementInDocument(IAnnotatedDocument document)
			throws ANoteException {
		return SpringInjectedServicesAccess.getAnnotationService().countEntityAnnotationsByResourceElementInDocument(document.getId(), document.getProcess().getId());
	}



	@Override
	public Long countDocumentsWithResourceElementByAnnotationTypeInProcess(IResourceElement resourceElement, IIEProcess process, AnnotationType annotType)
			throws ANoteException {
		return SpringInjectedServicesAccess.getAnnotationService().countDocumentsWithResourceElementByAnnotationTypeInProcess(resourceElement.getId(), process.getId(), annotType.name());
	}



	@Override
	public Map<IResourceElement, Long> countDocumentsWithEntityAnnotationsByResourceElementInProcess(IIEProcess process)
			throws ANoteException {
		return SpringInjectedServicesAccess.getAnnotationService().countDocumentsWithEntityAnnotationsByResourceElementInProcess(process.getId());
	}



	@Override
	public Long countPublicationsWithEventsByResourceElements(List<IResourceElement> resourceElements)
			throws ANoteException {
		List<Long> resourceElementIds = new ArrayList<>();
		for(IResourceElement elem : resourceElements)
			resourceElementIds.add(elem.getId());
		return SpringInjectedServicesAccess.getAnnotationService().countPublicationsWithEventsByResourceElements(resourceElementIds);
	}



	@Override
	public List<Long> getPublicationsIdsWithEventsByResourceElements(List<IResourceElement> resourceElements)
			throws ANoteException {
		List<Long> resourceElementIds = new ArrayList<>();
		for(IResourceElement elem : resourceElements)
			resourceElementIds.add(elem.getId());
		return SpringInjectedServicesAccess.getAnnotationService().getPublicationsIdsWithEventsByResourceElements(resourceElementIds);

	}



	@Override
	public Map<ImmutablePair<IAnoteClass, IAnoteClass>, Long> countPublicationsWithEventsByIAnoteClasses(
			IIEProcess process) throws ANoteException {
		return SpringInjectedServicesAccess.getAnnotationService().countPublicationsWithEventsByIAnoteClasses(process.getId());
	}



	@Override
	public Map<ImmutablePair<IAnoteClass, IAnoteClass>, Long> countEventAnnotationsByClassInProcess(IIEProcess process)
			throws ANoteException {
		return SpringInjectedServicesAccess.getAnnotationService().countEventAnnotationsByClassInProcess(process.getId());
	}



	@Override
	public List<Long> getPublicationsIdsByEventResourceElements(IIEProcess process,
			Set<ImmutablePair<IResourceElement, IResourceElement>> resElemIds) throws ANoteException {
		Set<String> resElemConcated = new HashSet<>();
		for(ImmutablePair<IResourceElement, IResourceElement> resId : resElemIds)
			resElemConcated.add(String.valueOf(resId.getLeft().getId())+"-"+String.valueOf(resId.getRight().getId()));
		return SpringInjectedServicesAccess.getAnnotationService().getPublicationsIdsByEventResourceElements(process.getId(), resElemConcated);
	}



	@Override
	public Map<ImmutablePair<IResourceElement, IResourceElement>, Long> countDocumentsWithEventsByResourceElemnts(
			IIEProcess process) throws ANoteException {
		return SpringInjectedServicesAccess.getAnnotationService().countDocumentsWithEventsByResourceElemnts(process.getId());
	}



	@Override
	public Map<ImmutablePair<IResourceElement, IResourceElement>, Long> countEventsByResourceElemnts(IIEProcess process)
			throws ANoteException {
		return SpringInjectedServicesAccess.getAnnotationService().countEventsByResourceElemnts(process.getId());
	}

}
