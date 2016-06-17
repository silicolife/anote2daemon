package com.silicolife.anote2daemon.processes.corpus;

import java.util.List;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.service.corpora.ICorpusService;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.service.publications.IPublicationsService;
import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;
import com.silicolife.textmining.core.interfaces.core.document.IPublication;
import com.silicolife.textmining.core.interfaces.core.document.corpus.ICorpus;
import com.silicolife.textmining.processes.corpora.loaders.CorpusCreationInBatch;

public class CorpusCreationInBatchServerRunExtension extends CorpusCreationInBatch{

	private ICorpusService corpusService;
	private IPublicationsService publictionService;
	
	public CorpusCreationInBatchServerRunExtension(ICorpusService corpusService,IPublicationsService publictionService)
	{
		super();
		this.corpusService=corpusService;
		this.publictionService=publictionService;
	}
	
	
	@Override
	protected void createCorpusOnDatabase(ICorpus newCorpus)throws ANoteException {
		corpusService.createCorpus(newCorpus);
	}
	
	@Override
	protected IPublication getPublicationOnDatabaseByID(Long publictionID)throws ANoteException {
		return publictionService.getById(publictionID);
	}
	
	@Override
	protected void addPublicationToDatabase(List<IPublication> documentToadd)throws ANoteException {
		publictionService.create(documentToadd);
	}
	
	@Override
	protected void associatePublicationToCorpusOnDatabase(ICorpus corpus, IPublication publication) throws ANoteException {
		corpusService.addCorpusPublication(corpus.getId(), publication.getId());
	}
	
	@Override
	protected void updatePublicationFullTextOnfDatabase(IPublication publication) throws ANoteException {
		publictionService.updatePublicationAFullTextContent(publication.getId(), publication.getFullTextContent());
	}
}