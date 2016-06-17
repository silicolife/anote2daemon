package com.silicolife.anote2daemon.processes.corpus;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.xml.sax.SAXException;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.service.corpora.ICorpusService;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.service.publications.IPublicationsService;
import com.silicolife.textmining.core.datastructures.utils.conf.GlobalNames;
import com.silicolife.textmining.core.interfaces.core.corpora.ICorpusCreateConfiguration;
import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;
import com.silicolife.textmining.core.interfaces.core.document.IPublication;
import com.silicolife.textmining.core.interfaces.core.document.corpus.ICorpus;
import com.silicolife.textmining.processes.ir.pubmed.utils.PMSearch;

public class CorpusCreationServerExecutor {


	private ICorpusService corpusService;
	private IPublicationsService publictionService;

	public CorpusCreationServerExecutor(ICorpusService corpusService, IPublicationsService publictionService){
		super();
		this.corpusService=corpusService;
		this.publictionService=publictionService;
	}

	public void executeCorpusCreation(ICorpusCreateConfiguration configuration) throws ANoteException, IOException{
		Set<Long> documents = configuration.getDocumentsIDs();
		if(documents != null && !documents.isEmpty())
			new CorpusCreationServerRunExtention(corpusService, publictionService).createCorpus(configuration);
		else
			corpusCreationBatchExecution(configuration);
	}

	private void corpusCreationBatchExecution(ICorpusCreateConfiguration configuration) throws ANoteException, IOException{
		CorpusCreationInBatchServerRunExtension corpusCreator = new CorpusCreationInBatchServerRunExtension(corpusService, publictionService);
		ICorpus corpus = corpusCreator.startCorpusCreation(configuration);

		Set<File> files = new HashSet<>();
		Properties configurationProperties = configuration.getProperties();
		if(configurationProperties.containsKey(GlobalNames.serverXMLsDirectory)){
			File dir = new File(configurationProperties.getProperty(GlobalNames.serverXMLsDirectory));
			getXMLFiles(dir, files);
		}

		if(!files.isEmpty())
			addPublicationsFromXMLFiles(corpusCreator, corpus, new ArrayList<>(files));
	}

	private void addPublicationsFromXMLFiles(CorpusCreationInBatchServerRunExtension corpusCreator, ICorpus corpus, List<File> xmlFiles)
			throws IOException, ANoteException {
		try{
			Set<IPublication> publications = new HashSet<>();
			for(int i=0; i<xmlFiles.size(); i++){
				InputStream stream = new FileInputStream(xmlFiles.get(i));
				List<IPublication> pubs = PMSearch.getPublicationsFromXMLStream(stream);
				publications.addAll(pubs);
				stream.close();
				if(i%1000==0 && i!=0){
					corpusCreator.addPublications(corpus, publications);
					publications.clear();
					System.out.println("Inserted the batch nº "+i);
				}
			}
			if(!publications.isEmpty()){
				corpusCreator.addPublications(corpus, publications);
				publications.clear();
			}
		}catch(XPathExpressionException | SAXException | ParserConfigurationException e){
			new ANoteException(e);
		}
	}

	private void getXMLFiles(File file, Set<File> files){
		if(file.isDirectory()){
			for(File childFile : file.listFiles()){
				getXMLFiles(childFile, files);
			}
		}else if(file.getAbsolutePath().endsWith(".xml")){
			files.add(file);
		}
	}
}
