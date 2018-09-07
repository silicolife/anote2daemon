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

	public int executeCorpusUpdate(ICorpusUpdateConfiguration corpusupdateConfiguration) throws IOException, ANoteException {
		CorpusCreationInBatch corpusCreator = new CorpusCreationInBatch();
		ICorpus corpus = corpusupdateConfiguration.getCorpusToUpdate();
		String publicationsDirectory = corpusupdateConfiguration.getPublicationsDirectory();
		Set<File> files = new HashSet<>();
		updaterLogger.info("Starting to read directory");
		updaterLogger.info(publicationsDirectory);
		if(!publicationsDirectory.isEmpty()){
			File dir = new File(publicationsDirectory);
			getFiles(dir, files);
		}
		updaterLogger.info("Found " + files.size() + " in the given directory! ["+corpusupdateConfiguration.getCorpusToUpdate().getDescription() + "]");	
		// Corpus Update
		addPublicationsFromFiles(corpusCreator, corpus, new ArrayList<>(files), corpusupdateConfiguration.getCorpusSource());
		return files.size();
	}
	
	

}
