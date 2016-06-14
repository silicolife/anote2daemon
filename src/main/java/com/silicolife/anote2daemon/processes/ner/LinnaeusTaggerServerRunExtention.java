package com.silicolife.anote2daemon.processes.ner;

import java.util.List;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.service.annotation.IAnnotationService;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.service.corpora.ICorpusService;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.service.processes.IProcessesService;
import com.silicolife.textmining.core.interfaces.core.annotation.IEntityAnnotation;
import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;
import com.silicolife.textmining.core.interfaces.core.document.ICorpusPublicationPaginator;
import com.silicolife.textmining.core.interfaces.core.document.IPublication;
import com.silicolife.textmining.core.interfaces.core.document.corpus.ICorpus;
import com.silicolife.textmining.core.interfaces.process.IE.IIEProcess;
import com.silicolife.textmining.processes.ie.ner.linnaeus.LinnaeusTagger;

public class LinnaeusTaggerServerRunExtention extends LinnaeusTagger{
	
	private ICorpusService corpusService;
	private IProcessesService processService;
	private IAnnotationService annotationService;

	public LinnaeusTaggerServerRunExtention(ICorpusService corpusService, IProcessesService processService, IAnnotationService annotationService) {
		this.corpusService=corpusService;
		this.processService=processService;
		this.annotationService=annotationService;
	}
	
	protected ICorpusPublicationPaginator getPublicationsPaginator(ICorpus corpus) throws ANoteException {
		return new CorpusPublicationPaginatorServerImpl(corpusService, corpus);
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
}
