package com.silicolife.anote2daemon.processes.ner;

import com.silicolife.anote2daemon.processes.corpus.PublicationServerImpl;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.service.corpora.ICorpusService;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.service.publications.IPublicationsService;
import com.silicolife.textmining.core.datastructures.documents.CorpusPublicationPaginatorImpl;
import com.silicolife.textmining.core.datastructures.documents.DocumentSetImpl;
import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;
import com.silicolife.textmining.core.interfaces.core.document.IDocumentSet;
import com.silicolife.textmining.core.interfaces.core.document.IPublication;
import com.silicolife.textmining.core.interfaces.core.document.corpus.ICorpus;

public class CorpusPublicationPaginatorServerImpl extends CorpusPublicationPaginatorImpl{

	private ICorpusService corpusService;
	private IPublicationsService publicationService;

	public CorpusPublicationPaginatorServerImpl(ICorpusService corpusService,IPublicationsService publicationService, ICorpus corpus, Integer paginationSize) throws ANoteException {
		super(corpus, paginationSize);
		this.corpusService=corpusService;
		this.publicationService=publicationService;
	}
	
	public CorpusPublicationPaginatorServerImpl(ICorpusService corpusService,IPublicationsService publicationService, ICorpus corpus) throws ANoteException {
		super(corpus);
		this.corpusService=corpusService;
		this.publicationService=publicationService;
	}
	
	protected Long getCorpusPublicationCountOnDatabase(ICorpus corpus) throws ANoteException {
		return corpusService.getCorpusPublicationsCount(corpus.getId());
	}
	
	protected IDocumentSet getCorpusPublicationsPaginatedOnDatabase(ICorpus corpus, Integer paginationIndex, Integer paginationSize) throws ANoteException {
		IDocumentSet docSet = corpusService.getCorpusPublicationsPaginated(corpus.getId(), paginationIndex, paginationSize);
		IDocumentSet result = new DocumentSetImpl();
		for(IPublication pub:docSet.getAllDocuments().values())
		{
			result.addDocument(pub.getId(), new PublicationServerImpl(publicationService, pub));
		}
		return result;
	}

}
