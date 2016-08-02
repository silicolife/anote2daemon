package com.silicolife.anote2daemon.processes.ner;

import java.util.List;

import com.silicolife.anote2daemon.controller.resources.ClassPropertiesManagementServer;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.service.annotation.IAnnotationService;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.service.corpora.ICorpusService;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.service.processes.IProcessesService;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.service.resources.IClassesService;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.service.resources.IResourcesElementService;
import com.silicolife.textmining.core.datastructures.documents.UnprocessedPublicationsPaginatorImpl;
import com.silicolife.textmining.core.datastructures.process.ner.ElementToNer;
import com.silicolife.textmining.core.datastructures.utils.conf.GlobalOptions;
import com.silicolife.textmining.core.interfaces.core.annotation.IEntityAnnotation;
import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;
import com.silicolife.textmining.core.interfaces.core.document.ICorpusPublicationPaginator;
import com.silicolife.textmining.core.interfaces.core.document.IPublication;
import com.silicolife.textmining.core.interfaces.core.document.corpus.ICorpus;
import com.silicolife.textmining.core.interfaces.core.general.classe.IAnoteClass;
import com.silicolife.textmining.core.interfaces.process.IE.IIEProcess;
import com.silicolife.textmining.processes.ie.ner.linnaeus.LinnaeusTagger;
import com.silicolife.textmining.processes.ie.ner.linnaeus.configuration.INERLinnaeusConfiguration;

public class LinnaeusTaggerServerRunExtention extends LinnaeusTagger{
	
	private ICorpusService corpusService;
	private IResourcesElementService resourceService;
	private IClassesService classesService;
	private IProcessesService processService;
	private IAnnotationService annotationService;
	private Integer paginationSizeInServer = 50000; //get's 50000 documents each batch to be splitted into x threads.
	

	public LinnaeusTaggerServerRunExtention(ICorpusService corpusService, IResourcesElementService resourceService, IClassesService classesService, IProcessesService processService, IAnnotationService annotationService) {
		this.corpusService=corpusService;
		this.resourceService=resourceService;
		this.classesService=classesService;
		this.processService=processService;
		this.annotationService=annotationService;
	}
	
	protected ICorpusPublicationPaginator getPublicationsPaginator(ICorpus corpus) throws ANoteException {
		return new CorpusPublicationPaginatorServerImpl(corpusService, corpus, paginationSizeInServer);
	}
	
	protected ElementToNer getElementsToNER(INERLinnaeusConfiguration linnauesConfiguration) throws ANoteException {
		ElementToNerServer elementsToNER = new ElementToNerServer(resourceService, linnauesConfiguration.getResourceToNER(), linnauesConfiguration.isNormalized());
		elementsToNER.processingINfo();
		return elementsToNER;
	}

	protected void createIEProcessONDataAccess(IIEProcess processToRun) throws ANoteException {
		processService.createIEProcess(processToRun);
	}
	
	protected void associateIEProcessToCorpusONDataAccess(ICorpus corpus, IIEProcess processToRun)throws ANoteException {
		corpusService.registerCorpusProcess(corpus.getId(), processToRun.getID());
	}
	
	protected void addAnnotatedDocumentEntities(IIEProcess processToRun,List<IEntityAnnotation> entityAnnotations, IPublication document)throws ANoteException {
		annotationService.addCorpusProcessDocumentEntityAnootations(processToRun.getCorpus().getId(), processToRun.getID(), document.getId(), entityAnnotations);
	}
	
	protected IAnoteClass getIAnoteClass(Long classID) throws ANoteException {
		return ClassPropertiesManagementServer.getClassGivenClassID(classesService, classID);
	}
	
	protected void memoryAndProgress(int step, int total,long startime) {
		if(step%1000==0)
		{
			System.out.println((GlobalOptions.decimalformat.format((double)step/ (double) total * 100)) + " %...");
		}
	}
	
	protected IIEProcess getProcessInDatabase(IIEProcess process) throws ANoteException{
		return processService.getProcessByID(process.getID());
	}
	
	protected ICorpusPublicationPaginator getUnprocessedPublicationsPaginator(IIEProcess process) throws ANoteException {
		return new UnprocessedPublicationsPaginatorImpl(process);
	}

}
