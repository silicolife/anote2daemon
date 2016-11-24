package com.silicolife.anote2daemon.processes.corpus;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.silicolife.textmining.core.datastructures.corpora.CorpusImpl;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.service.corpora.ICorpusService;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.service.publications.IPublicationsService;
import com.silicolife.textmining.core.datastructures.documents.DocumentSetImpl;
import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;
import com.silicolife.textmining.core.interfaces.core.document.IDocumentSet;
import com.silicolife.textmining.core.interfaces.core.document.IPublication;
import com.silicolife.textmining.core.interfaces.core.document.corpus.ICorpus;

@Deprecated
public class CorpusServerImpl extends CorpusImpl {
	
	private IPublicationsService publictionService;
	private ICorpusService corpusService;
	
	public CorpusServerImpl(IPublicationsService publictionService,ICorpusService corpusService,ICorpus corpus) {
		super(corpus.getId(),corpus.getDescription(),corpus.getNotes(),corpus.getProperties());
		this.corpusService=corpusService;
		this.publictionService=publictionService;
	}
	
//	@JsonIgnore
//	@Override
//	public void registerProcess(IIEProcess ieProcess) throws ANoteException {
//		corpusService.registerCorpusProcess(getId(), ieProcess.getID());
//	}

	@JsonIgnore
	@Override
	public synchronized IDocumentSet getArticlesCorpus() throws ANoteException {
		if(documentSet==null)
		{
			IDocumentSet documentSetAux = corpusService.getCorpusPublications(this.getId());
			IDocumentSet result = new DocumentSetImpl();
			for(IPublication pub:documentSetAux.getAllDocuments().values())
			{
				result.addDocument(pub.getId(), new PublicationServerImpl(publictionService, pub));
			}
			this.documentSet = result;
		}
		return documentSet;
	}

}
