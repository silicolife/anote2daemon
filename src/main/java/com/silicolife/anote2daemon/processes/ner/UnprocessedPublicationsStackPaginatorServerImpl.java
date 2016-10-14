package com.silicolife.anote2daemon.processes.ner;

import com.silicolife.anote2daemon.processes.corpus.PublicationServerImpl;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.service.corpora.ICorpusService;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.service.publications.IPublicationsService;
import com.silicolife.textmining.core.datastructures.documents.DocumentSetImpl;
import com.silicolife.textmining.core.datastructures.documents.UnprocessedPublicationsStackPaginatorImpl;
import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;
import com.silicolife.textmining.core.interfaces.core.document.IDocumentSet;
import com.silicolife.textmining.core.interfaces.core.document.IPublication;
import com.silicolife.textmining.core.interfaces.process.IE.IIEProcess;

public class UnprocessedPublicationsStackPaginatorServerImpl extends UnprocessedPublicationsStackPaginatorImpl{

	private IPublicationsService publicationService;
	private ICorpusService corpusService;

	public UnprocessedPublicationsStackPaginatorServerImpl(IPublicationsService publicationService,ICorpusService corpusService, IIEProcess process, Integer paginationSize) throws ANoteException {
		super(process, paginationSize);
		this.corpusService = corpusService;
		this.publicationService=publicationService;
	}
	
	public UnprocessedPublicationsStackPaginatorServerImpl(IPublicationsService publicationService,ICorpusService corpusService, IIEProcess process) throws ANoteException {
		super(process);
		this.corpusService = corpusService;
		this.publicationService=publicationService;
	}
	
	protected Long getUnprocessdCorpusPublicationCountOnDatabase(IIEProcess process) throws ANoteException {
		return corpusService.countCorpusPublicationsNotProcessed(process.getCorpus().getId(), process.getId());
	}
	
	protected IDocumentSet getCorpusPublicationsNotProcessedPaginatedOnDatabase(IIEProcess process, Integer paginationIndex, Integer paginationSize) throws ANoteException {
		
		IDocumentSet result = new DocumentSetImpl();
		IDocumentSet documentSetAux = corpusService.getCorpusPublicationsNotProcessedPaginated(process.getCorpus().getId(), process.getId(), paginationIndex, paginationSize);
		for(IPublication pub:documentSetAux.getAllDocuments().values())
		{
			result.addDocument(pub.getId(), new PublicationServerImpl(publicationService, pub));
		}
		return result;
	}

}
