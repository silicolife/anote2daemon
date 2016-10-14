package com.silicolife.anote2daemon.processes.ner;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.silicolife.anote2daemon.controller.resources.ClassPropertiesManagementServer;
import com.silicolife.anote2daemon.processes.resources.LexicalWordsServerImpl;
import com.silicolife.anote2daemon.processes.resources.ResourceServerImpl;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.service.annotation.IAnnotationService;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.service.corpora.ICorpusService;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.service.processes.IProcessesService;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.service.publications.IPublicationsService;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.service.resources.IClassesService;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.service.resources.IResourcesElementService;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.service.resources.IResourcesService;
import com.silicolife.textmining.core.datastructures.process.ner.ElementToNer;
import com.silicolife.textmining.core.datastructures.utils.conf.GlobalOptions;
import com.silicolife.textmining.core.interfaces.core.annotation.IEntityAnnotation;
import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;
import com.silicolife.textmining.core.interfaces.core.document.ICorpusPublicationPaginator;
import com.silicolife.textmining.core.interfaces.core.document.IDocumentSet;
import com.silicolife.textmining.core.interfaces.core.document.IPublication;
import com.silicolife.textmining.core.interfaces.core.document.corpus.ICorpus;
import com.silicolife.textmining.core.interfaces.core.general.classe.IAnoteClass;
import com.silicolife.textmining.core.interfaces.process.IE.IIEProcess;
import com.silicolife.textmining.core.interfaces.process.IE.ner.INERConfiguration;
import com.silicolife.textmining.core.interfaces.resource.IResource;
import com.silicolife.textmining.core.interfaces.resource.IResourceElement;
import com.silicolife.textmining.core.interfaces.resource.lexicalwords.ILexicalWords;
import com.silicolife.textmining.processes.ie.ner.linnaeus.LinnaeusTagger;
import com.silicolife.textmining.processes.ie.ner.linnaeus.adapt.uk.ac.man.documentparser.input.DocumentIterator;
import com.silicolife.textmining.processes.ie.ner.linnaeus.configuration.INERLinnaeusConfiguration;

public class LinnaeusTaggerServerRunExtention extends LinnaeusTagger{
	
	private IPublicationsService publicationService;
	private ICorpusService corpusService;
	private IResourcesService resourcesService;
	private IResourcesElementService resourcesElementService;
	private IClassesService classesService;
	private IProcessesService processService;
	private IAnnotationService annotationService;
	private Integer paginationSizeInServer = 50000; //get's 50000 documents each batch to be splitted into x threads.
	
	final static Logger serverNerlogger = LoggerFactory.getLogger(LinnaeusTaggerServerRunExtention.class);

	public LinnaeusTaggerServerRunExtention(IPublicationsService publicationService,ICorpusService corpusService, IResourcesService resourcesService, IResourcesElementService resourcesElementService, IClassesService classesService, IProcessesService processService, IAnnotationService annotationService) {
		this.publicationService=publicationService;
		this.corpusService=corpusService;
		this.resourcesService = resourcesService;
		this.resourcesElementService=resourcesElementService;
		this.classesService=classesService;
		this.processService=processService;
		this.annotationService=annotationService;
	}
	
	protected ICorpusPublicationPaginator getPublicationsPaginator(ICorpus corpus) throws ANoteException {
		return new CorpusPublicationPaginatorServerImpl(corpusService,publicationService, corpus, paginationSizeInServer);
	}
	
	protected ElementToNer getElementsToNER(INERLinnaeusConfiguration linnauesConfiguration) throws ANoteException {
		ElementToNerServer elementsToNER = new ElementToNerServer(resourcesElementService, linnauesConfiguration.getResourceToNER(), linnauesConfiguration.isNormalized());
		elementsToNER.processingINfo();
		return elementsToNER;
	}

	protected void createIEProcessONDataAccess(IIEProcess processToRun) throws ANoteException {
		processService.createIEProcess(processToRun);
	}
	
	protected void associateIEProcessToCorpusONDataAccess(ICorpus corpus, IIEProcess processToRun)throws ANoteException {
		corpusService.registerCorpusProcess(corpus.getId(), processToRun.getId());
	}
	
	protected void addAnnotatedDocumentEntities(IIEProcess processToRun,List<IEntityAnnotation> entityAnnotations, IPublication document)throws ANoteException {
		annotationService.addCorpusProcessDocumentEntityAnootations(processToRun.getCorpus().getId(), processToRun.getId(), document.getId(), entityAnnotations);
	}
	
	protected IAnoteClass getIAnoteClass(Long classID) throws ANoteException {
		return ClassPropertiesManagementServer.getClassGivenClassID(classesService, classID);
	}
	
	protected void memoryAndProgress(int step, int total,long startime) {
		if(step%1000==0)
		{
			System.out.println((GlobalOptions.decimalformat.format((double)step/ (double) total * 100)) + " %...");
			serverNerlogger.info((GlobalOptions.decimalformat.format((double)step/ (double) total * 100)) + " %...");
		}
	}
	
	protected IIEProcess getProcessInDatabase(IIEProcess process) throws ANoteException{
		return processService.getProcessByID(process.getId());
	}
	
	protected ICorpusPublicationPaginator getUnprocessedPublicationsPaginator(IIEProcess process) throws ANoteException {
		return new UnprocessedPublicationsStackPaginatorServerImpl(publicationService,corpusService, process, paginationSizeInServer);
	}
	
	protected IResource<IResourceElement> getResourceFromDatabase(Long resourceId) throws ANoteException{
		return resourcesService.getResourcesById(resourceId);
	}


	protected ILexicalWords getStopWords(INERLinnaeusConfiguration linnauesConfiguration){
		return new LexicalWordsServerImpl(new ResourceServerImpl(resourcesElementService, linnauesConfiguration.getStopWords()));
	}
	
	protected DocumentIterator getDocumentIterator(INERConfiguration configuration, IIEProcess processToRun,
			IDocumentSet documentSet) throws ANoteException {
		DocumentIterator documents = new PublicationServerIt(publicationService,configuration.getCorpus(), documentSet, processToRun);
		return documents;
	}
}
