package com.silicolife.anote2daemon.processes.ner;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.service.corpora.ICorpusService;
import com.silicolife.textmining.core.datastructures.documents.UnprocessedPublicationsStackPaginatorImpl;
import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;
import com.silicolife.textmining.core.interfaces.core.document.IDocumentSet;
import com.silicolife.textmining.core.interfaces.process.IE.IIEProcess;

public class UnprocessedPublicationsStackPaginatorServerImpl extends UnprocessedPublicationsStackPaginatorImpl{

	private ICorpusService corpusService;

	public UnprocessedPublicationsStackPaginatorServerImpl(ICorpusService corpusService, IIEProcess process, Integer paginationSize) throws ANoteException {
		super(process, paginationSize);
		this.corpusService = corpusService;
	}
	
	public UnprocessedPublicationsStackPaginatorServerImpl(ICorpusService corpusService, IIEProcess process) throws ANoteException {
		super(process);
		this.corpusService = corpusService;
	}
	
	protected Long getUnprocessdCorpusPublicationCountOnDatabase(IIEProcess process) throws ANoteException {
		return corpusService.countCorpusPublicationsNotProcessed(process.getCorpus().getId(), process.getID());
	}
	
	protected IDocumentSet getCorpusPublicationsNotProcessedPaginatedOnDatabase(IIEProcess process, Integer paginationIndex, Integer paginationSize) throws ANoteException {
		return corpusService.getCorpusPublicationsNotProcessedPaginated(process.getCorpus().getId(), process.getID(), paginationIndex, paginationSize);
	}

}
