package com.silicolife.anote2daemon.processes.ner;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.service.corpora.ICorpusService;
import com.silicolife.textmining.core.datastructures.documents.CorpusPublicationPaginatorImpl;
import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;
import com.silicolife.textmining.core.interfaces.core.document.IDocumentSet;
import com.silicolife.textmining.core.interfaces.core.document.corpus.ICorpus;

public class CorpusPublicationPaginatorServerImpl extends CorpusPublicationPaginatorImpl{

	private ICorpusService corpusService;

	public CorpusPublicationPaginatorServerImpl(ICorpusService corpusService, ICorpus corpus) throws ANoteException {
		super(corpus);
		this.corpusService=corpusService;
	}
	
	protected Long getCorpusPublicationCountOnDatabase(ICorpus corpus) throws ANoteException {
		return corpusService.getCorpusPublicationsCount(corpus.getId());
	}
	
	protected IDocumentSet getCorpusPublicationsPaginatedOnDatabase(ICorpus corpus, Integer paginationIndex, Integer paginationSize) throws ANoteException {
		return corpusService.getCorpusPublicationsPaginated(corpus.getId(), paginationIndex, paginationSize);
	}

}
