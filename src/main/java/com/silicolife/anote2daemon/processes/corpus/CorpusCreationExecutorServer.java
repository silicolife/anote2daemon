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
import com.silicolife.textmining.core.interfaces.core.corpora.ICorpusCreateConfiguration;
import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;
import com.silicolife.textmining.core.interfaces.core.document.IPublication;
import com.silicolife.textmining.core.interfaces.core.document.corpus.ICorpus;
import com.silicolife.textmining.processes.corpora.loaders.CorpusCreation;
import com.silicolife.textmining.processes.corpora.loaders.CorpusCreationInBatch;
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

	private void corpusCreationBatchExecution(ICorpusCreateConfiguration configuration) throws ANoteException, IOException{
		CorpusCreationInBatch corpusCreator = new CorpusCreationInBatch();
		ICorpus corpus = corpusCreator.startCorpusCreation(configuration);

		Set<File> files = new HashSet<>();
		Properties configurationProperties = configuration.getProperties();
		creationLogger.info("Starting to read directory");
		if(configurationProperties.containsKey(GlobalNames.serverXMLsDirectory)){
			File dir = new File(configurationProperties.getProperty(GlobalNames.serverXMLsDirectory));
			getXMLFiles(dir, files);
		}

		creationLogger.info("Found " + files.size() + " in the given corpus directory!");

		if(!files.isEmpty()){
			creationLogger.info("Starting to add corpus in database");
			addPublicationsFromXMLFiles(corpusCreator, corpus, new ArrayList<>(files), configurationProperties);
		}
	}

	protected void addPublicationsFromXMLFiles(CorpusCreationInBatch corpusCreator, ICorpus corpus, List<File> xmlFiles, Properties configurationProperties)
			throws IOException, ANoteException {
		Set<IPublication> publications = new HashSet<>();
		for(int i=0; i<xmlFiles.size(); i++){
			if(configurationProperties.containsKey(GlobalNames.readPMCFiles) && configurationProperties.getProperty(GlobalNames.readPMCFiles).equals("true")){
				addUsingPMCReader(xmlFiles, publications, i);
			}else{
				addUsingPubmedReader(xmlFiles, publications, i);
			}
			
			if(i%1000==0 && i!=0){
				corpusCreator.addPublications(corpus, publications);
				publications.clear();
				System.out.println("Inserted the batch nº "+i);
				creationLogger.info("Inserted the batch nº "+i);
			}
		}
		if(!publications.isEmpty()){
			corpusCreator.addPublications(corpus, publications);
			publications.clear();
		}
	}

	private void addUsingPubmedReader(List<File> xmlFiles, Set<IPublication> publications, int i)
			throws FileNotFoundException, ANoteException, IOException {
		InputStream stream = new FileInputStream(xmlFiles.get(i));
		PubmedReader reader = new PubmedReader();
		try{
			List<IPublication> pubs = reader.getPublications(stream);
			publications.addAll(pubs);
		}catch(ANoteException e){
			creationLogger.error("Failed on file: " + xmlFiles.get(i).getAbsolutePath());
			throw e;
		}
		stream.close();
	}
	

	private void addUsingPMCReader(List<File> xmlFiles, Set<IPublication> publications, int i) throws ANoteException {
		File pmcFile = xmlFiles.get(i);
		PMCReader reader = new PMCReader();
		try{
			List<IPublication> pubs = reader.getPublications(pmcFile);
			publications.addAll(pubs);
		}catch(ANoteException e){
			creationLogger.error("Failed on file: " + xmlFiles.get(i).getAbsolutePath());
			throw e;
		}
	}

	protected void getXMLFiles(File file, Set<File> files){
		if(file.isDirectory()){
			for(File childFile : file.listFiles()){
				getXMLFiles(childFile, files);
			}
		}else if(file.getAbsolutePath().endsWith(".xml") || file.getAbsolutePath().endsWith(".nxml")){
			files.add(file);
		}
		
	}
}
