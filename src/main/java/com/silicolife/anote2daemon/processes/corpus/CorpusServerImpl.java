package com.silicolife.anote2daemon.processes.corpus;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.silicolife.textmining.core.datastructures.corpora.CorpusImpl;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.service.corpora.ICorpusService;
import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;
import com.silicolife.textmining.core.interfaces.core.document.IDocumentSet;
import com.silicolife.textmining.core.interfaces.core.document.corpus.ICorpus;
import com.silicolife.textmining.core.interfaces.process.IE.IIEProcess;

public class CorpusServerImpl extends CorpusImpl {
	
	private ICorpusService corpusService;
	
	public CorpusServerImpl(ICorpusService corpusService,ICorpus corpus) {
		super(corpus.getId(),corpus.getDescription(),corpus.getNotes(),corpus.getProperties());
		this.corpusService=corpusService;
	}
	
	@JsonIgnore
	@Override
	public void registerProcess(IIEProcess ieProcess) throws ANoteException {
		corpusService.registerCorpusProcess(getId(), ieProcess.getID());
	}

	@JsonIgnore
	@Override
	public synchronized IDocumentSet getArticlesCorpus() throws ANoteException {
		if(documentSet==null)
		{
			documentSet = corpusService.getCorpusPublications(this.getId());
		}
		return documentSet;
	}

}
