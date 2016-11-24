package com.silicolife.anote2daemon.processes.corpus;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.silicolife.textmining.core.interfaces.core.corpora.ICorpusUpdateConfiguration;
import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;
import com.silicolife.textmining.core.interfaces.core.document.corpus.ICorpus;
import com.silicolife.textmining.processes.corpora.loaders.CorpusCreationInBatch;

public class CorpusUpdaterExecutorServer extends CorpusCreationExecutorServer{
	
	final static Logger updaterLogger = LoggerFactory.getLogger(CorpusUpdaterExecutorServer.class);

	public CorpusUpdaterExecutorServer(){

	}

	public void executeCorpusUpdate(ICorpusUpdateConfiguration corpusupdateConfiguration) throws IOException, ANoteException {
		CorpusCreationInBatch corpusCreator = new CorpusCreationInBatch();
		ICorpus corpus = corpusupdateConfiguration.getCorpusToUpdate();
		String publicationsDirectory = corpusupdateConfiguration.getPublicationsDirectory();
		Set<File> files = new HashSet<>();
		updaterLogger.info("Starting to read directory");
		if(!publicationsDirectory.isEmpty()){
			File dir = new File(publicationsDirectory);
			getXMLFiles(dir, files);
		}
		updaterLogger.info("Found " + files.size() + " in the given corpus directory!");	
		addPublicationsFromXMLFiles(corpusCreator, corpus, new ArrayList<>(files), corpusupdateConfiguration.getProperties());
	}
	
	

}
