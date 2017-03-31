package com.silicolife.anote2daemon.processes.corpus;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.silicolife.anote2daemon.utils.AutoUpdateConfigurationProperties;
import com.silicolife.textmining.core.datastructures.corpora.CorpusUpdateConfigurationImpl;
import com.silicolife.textmining.core.datastructures.exceptions.process.InvalidConfigurationException;
import com.silicolife.textmining.core.datastructures.init.InitConfiguration;
import com.silicolife.textmining.core.datastructures.process.ProcessRunStatusConfigurationEnum;
import com.silicolife.textmining.core.interfaces.core.corpora.ICorpusUpdateConfiguration;
import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;
import com.silicolife.textmining.core.interfaces.core.document.corpus.ICorpus;
import com.silicolife.textmining.core.interfaces.process.IE.IIEProcess;
import com.silicolife.textmining.core.interfaces.process.IE.ner.INERConfiguration;
import com.silicolife.textmining.processes.ie.ner.datatstructures.ANERLexicalResources;
import com.silicolife.textmining.processes.ie.ner.linnaeus.LinnaeusTagger;
import com.silicolife.textmining.processes.ie.ner.linnaeus.configuration.NERLinnaeusConfigurationImpl;

public class PubmedUpdateExecutionManager {
	
	static Logger logger = Logger.getLogger(PubmedUpdateExecutionManager.class.getName());
	
	private boolean execute;
	
	private static PubmedUpdateExecutionManager _instance;

	private PubmedUpdateExecutionManager(){
		this.execute = false;
	}
	
	public static PubmedUpdateExecutionManager getInstance()
	{
		if(_instance==null)
			_instance = new PubmedUpdateExecutionManager();
		return _instance;
	}
	
	public void startExecution() throws IOException, ANoteException, InvalidConfigurationException{
		execute = true;
		execution();
	}
	
	public void stopExecution(){
		execute = false;
	}

	private void execution() throws IOException, ANoteException, InvalidConfigurationException {
		logger.info("Executing Pubmed Corpus and Processes Update");
		CorpusUpdaterExecutorServer corpusUpdaterExecutorServer = new CorpusUpdaterExecutorServer();
		int updatedFiles = corpusUpdaterExecutorServer.executeCorpusUpdate(getCorpusUpdateConfiguration());
		if(updatedFiles>0)
		{
			List<IIEProcess> processsesToUpdate = getProcessesToUpdate();
			movePubmedFilesToFinalDirectory();
			for(IIEProcess process:processsesToUpdate)
			{
				logger.info("Resume Process start " +process.getName());
				ANERLexicalResources tagger = new LinnaeusTagger();
				INERConfiguration linaneusConfiguration = new NERLinnaeusConfigurationImpl(process, ProcessRunStatusConfigurationEnum.resume);
				tagger.execute(linaneusConfiguration);
			}
		}
		else
			logger.info("No files to update");
	}
	
	private List<IIEProcess> getProcessesToUpdate() throws ANoteException
	{
		List<IIEProcess> out = new ArrayList<>();
		String idsSeparatedByComma = AutoUpdateConfigurationProperties.getPubmedCorpusProcessesToResumeIds();
		String[] list = idsSeparatedByComma.split(",");
		for(String item:list)
		{
			String itemTrim = item.trim();
			if(!itemTrim.isEmpty())
			{
				Long processID = Long.valueOf(itemTrim);
				IIEProcess process = InitConfiguration.getDataAccess().getProcessByID(processID);
				if(process==null)
					logger.info("Process Id "+processID+ " not found");
				else
					if(!process.getProcessOrigin().equals(LinnaeusTagger.linnausOrigin))
					{
						logger.info("Process Id "+processID+ " is not a Linnaeus Process");
					}
					else
						out.add(process);
			}
		}
		return out;
	}

	public 	ICorpusUpdateConfiguration getCorpusUpdateConfiguration() throws ANoteException
	{
		String publicationsDirectory = AutoUpdateConfigurationProperties.getPubmedCorpusUpdateDir();
		Properties properties = new Properties();
		long corpusID = Long.valueOf(AutoUpdateConfigurationProperties.getPubmedCorpusId());
		ICorpus corpusToUpdate = InitConfiguration.getDataAccess().getCorpusByID(corpusID );
		CorpusUpdateConfigurationImpl corpusupdateConfiguration = new CorpusUpdateConfigurationImpl(corpusToUpdate, publicationsDirectory, properties);
		return corpusupdateConfiguration;
	}
	
	private static boolean movePubmedFilesToFinalDirectory() throws IOException{
		String tmpDir = AutoUpdateConfigurationProperties.getPubmedCorpusUpdateDir();
		String finalDir = AutoUpdateConfigurationProperties.getPubmedCorpusToArchiveDir();
		File finalDirFile = new File(finalDir);
		if(!finalDirFile.exists())
			finalDirFile.mkdir();
		File tmpDirFile = new File(tmpDir);
		File[] fileToMove = tmpDirFile.listFiles();
		logger.info("Start Moving file "+fileToMove.length + " to file destination");
		for(File file:fileToMove)
		{
			Files.move(Paths.get(file.getAbsolutePath()), Paths.get(finalDir+"/"+file.getName()),StandardCopyOption.REPLACE_EXISTING);
		}
		logger.info("Finsihed moving files");
		return true;		
	}

}
