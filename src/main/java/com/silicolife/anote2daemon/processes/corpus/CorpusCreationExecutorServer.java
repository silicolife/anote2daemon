package com.silicolife.anote2daemon.processes.corpus;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.silicolife.textmining.core.datastructures.utils.conf.GlobalNames;
import com.silicolife.textmining.core.interfaces.core.corpora.CorpusCreateSourceEnum;
import com.silicolife.textmining.core.interfaces.core.corpora.ICorpusCreateConfiguration;
import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;
import com.silicolife.textmining.core.interfaces.core.document.IPublication;
import com.silicolife.textmining.core.interfaces.core.document.corpus.ICorpus;
import com.silicolife.textmining.core.interfaces.core.general.IDataProcessStatus;
import com.silicolife.textmining.processes.corpora.loaders.CorpusCreation;
import com.silicolife.textmining.processes.corpora.loaders.CorpusCreationInBatch;
import com.silicolife.textmining.processes.corpora.readers.PatentMetaFilesReader;
import com.silicolife.textmining.processes.ir.pubmed.PubmedReader;
import com.silicolife.textmining.processes.ir.pubmed.reader.PMCReader;

public class CorpusCreationExecutorServer {

	final static Logger creationLogger = LoggerFactory.getLogger(CorpusCreationExecutorServer.class);


	public CorpusCreationExecutorServer(){
	}



	public void executeCorpusCreation(ICorpusCreateConfiguration configuration) throws ANoteException, IOException{
		Set<Long> documents = configuration.getDocumentsIDs();
		if(documents != null && !documents.isEmpty())
			new CorpusCreation().createCorpus(configuration);
		else
			corpusCreationBatchExecution(configuration);
	}
	
	public void executeCorpusCreationByIds(ICorpusCreateConfiguration configuration,
			ICorpus newCorpus, IDataProcessStatus dataprocessStatus) throws ANoteException, IOException{
			new CorpusCreation().createCorpusByIds(configuration, newCorpus, dataprocessStatus);	
	}
	
	public int executeCorpusCreationByLuceneSearch(ICorpusCreateConfiguration configuration, ICorpus newCorpus,IDataProcessStatus dataprocessStatus, int step, int total) throws ANoteException, IOException{
		return new CorpusCreation().createCorpusByLuceneSearch(configuration, newCorpus, dataprocessStatus, step, total);	
	}

	private void corpusCreationBatchExecution(ICorpusCreateConfiguration configuration) throws ANoteException, IOException{
		CorpusCreationInBatch corpusCreator = new CorpusCreationInBatch();
		ICorpus corpus = corpusCreator.startCorpusCreation(configuration);

		Set<File> files = new HashSet<>();
		Properties configurationProperties = configuration.getProperties();
		creationLogger.info("Starting to read directory");
		if(configurationProperties.containsKey(GlobalNames.serverXMLsDirectory)){
			File dir = new File(configurationProperties.getProperty(GlobalNames.serverXMLsDirectory));
			getFiles(dir, files);
		}

		creationLogger.info("Found " + files.size() + " in the given corpus directory!");

		if(!files.isEmpty()){
			creationLogger.info("Starting to add corpus in database");
			addPublicationsFromFiles(corpusCreator, corpus, new ArrayList<>(files), configuration.getCorpusSource());
		}
	}

	protected void addPublicationsFromFiles(CorpusCreationInBatch corpusCreator, ICorpus corpus, List<File> xmlFiles, CorpusCreateSourceEnum corpusSource)
			throws IOException, ANoteException {
		Set<IPublication> publications = new HashSet<>();
		for(int i=0; i<xmlFiles.size(); i++){
			if(corpusSource == null)
				throw new ANoteException("CorpusCreateSourceEnum can not be null");
			switch (corpusSource) {
				case Pubmed :
					publications.addAll(addUsingPubmedReader(xmlFiles.get(i)));
					break;
				case PMC :
					publications.addAll(addUsingPMCReader(xmlFiles.get(i)));
					break;
				case USPTO :
					publications.addAll(addUSPTOReader(xmlFiles.get(i)));
					break;
				default :
					break;
			}
			if(i%500==0 && i!=0){
				corpusCreator.addPublications(corpus, publications);
				publications.clear();
				creationLogger.info("Inserted the batch n� "+i + " ["+corpus.getDescription() + "]");
			}
		}
		if(!publications.isEmpty()){
			corpusCreator.addPublications(corpus, publications);
			publications.clear();
		}
	}
	
	private List<IPublication> addUSPTOReader(File usptoMetaFile)
			throws FileNotFoundException, ANoteException, IOException {
		if(usptoMetaFile.getName().endsWith(".meta"))
		{
			InputStream stream = new FileInputStream(usptoMetaFile);
			PatentMetaFilesReader reader = new PatentMetaFilesReader();
			try{
				List<IPublication> pubs = reader.getPatent(stream,usptoMetaFile);
				stream.close();
				return pubs;
			}catch(ANoteException e){
				creationLogger.error("Failed on file: " + usptoMetaFile.getAbsolutePath());
				stream.close();
				throw e;
			}
		}
		return new ArrayList<>();
	}

	private List<IPublication> addUsingPubmedReader(File pubmedFile)
			throws FileNotFoundException, ANoteException, IOException {
		InputStream stream = new FileInputStream(pubmedFile);
		PubmedReader reader = new PubmedReader();
		try{
			List<IPublication> pubs = reader.getPublications(stream);
			stream.close();
			return pubs;
		}catch(ANoteException e){
			creationLogger.error("Failed on file: " + pubmedFile.getAbsolutePath());
			stream.close();
			throw e;
		}
	}
	

	private List<IPublication> addUsingPMCReader(File pmcFile) throws ANoteException {
		PMCReader reader = new PMCReader();
		try{
			List<IPublication> pubs = reader.getPublications(pmcFile);
			return pubs;
		}catch(ANoteException e){
			creationLogger.error("Failed on file: " + pmcFile.getAbsolutePath());
			throw e;
		}
	}

	protected void getFiles(File file, Set<File> files){
		if(file.isDirectory()){
			for(File childFile : file.listFiles()){
				getFiles(childFile, files);
			}
		}else if(file.getAbsolutePath().endsWith(".xml") || file.getAbsolutePath().endsWith(".nxml") || file.getAbsolutePath().endsWith(".meta")){
			files.add(file);
		}
		
	}
}
