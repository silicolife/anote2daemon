package com.silicolife.anote2daemon.processes.corpus;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.slf4j.LoggerFactory;

import com.silicolife.anote2daemon.utils.ApplicationConfigurationProperties;
import com.silicolife.textmining.core.datastructures.corpora.CorpusUpdateConfigurationImpl;
import com.silicolife.textmining.core.datastructures.exceptions.process.InvalidConfigurationException;
import com.silicolife.textmining.core.datastructures.init.InitConfiguration;
import com.silicolife.textmining.core.datastructures.process.ProcessRunStatusConfigurationEnum;
import com.silicolife.textmining.core.interfaces.core.corpora.CorpusCreateSourceEnum;
import com.silicolife.textmining.core.interfaces.core.corpora.ICorpusUpdateConfiguration;
import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;
import com.silicolife.textmining.core.interfaces.core.document.corpus.ICorpus;
import com.silicolife.textmining.core.interfaces.process.IE.IIEProcess;
import com.silicolife.textmining.core.interfaces.process.IE.ner.INERConfiguration;
import com.silicolife.textmining.processes.ie.ner.datatstructures.ANERLexicalResources;
import com.silicolife.textmining.processes.ie.ner.linnaeus.LinnaeusTagger;
import com.silicolife.textmining.processes.ie.ner.linnaeus.configuration.NERLinnaeusConfigurationImpl;

public class USPTOPatentUpdateExecutionManager {
	
	final static org.slf4j.Logger logger = LoggerFactory.getLogger(USPTOPatentUpdateExecutionManager.class);
	
	private boolean execute;
	
	private static USPTOPatentUpdateExecutionManager _instance;

	private USPTOPatentUpdateExecutionManager(){
		this.execute = false;
	}
	
	public static USPTOPatentUpdateExecutionManager getInstance()
	{
		if(_instance==null)
			_instance = new USPTOPatentUpdateExecutionManager();
		return _instance;
	}
	
	public void startExecution() throws IOException, ANoteException, InvalidConfigurationException{
		execute = true;
		executionGrant();
		executionApplication();
	}
	
	public void stopExecution(){
		execute = false;
	}
	
	private void executionGrant() throws IOException, ANoteException, InvalidConfigurationException {
		logger.info("Executing USPTO [Grant] Patent Corpus and Processes Update");
		CorpusUpdaterExecutorServer corpusUpdaterExecutorServer = new CorpusUpdaterExecutorServer();
		int updatedFiles = corpusUpdaterExecutorServer.executeCorpusUpdate(getUSPTOGrantCorpusUpdateConfiguration());
		if(updatedFiles>0)
		{
			List<IIEProcess> processsesToUpdate = getProcessesToUpdate();
			moveUSPTOGrantFilesToFinalDirectory();
			logger.info("Resume Process " +processsesToUpdate.size());
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
	
	private void executionApplication() throws IOException, ANoteException, InvalidConfigurationException {
		logger.info("Executing USPTO [Application] PatentCorpus and Processes Update");
		CorpusUpdaterExecutorServer corpusUpdaterExecutorServer = new CorpusUpdaterExecutorServer();
		int updatedFiles = corpusUpdaterExecutorServer.executeCorpusUpdate(getUSPTOApplicationCorpusUpdateConfiguration());
		if(updatedFiles>0)
		{
			List<IIEProcess> processsesToUpdate = getProcessesToUpdate();
			moveUSPTOApplicationFilesToFinalDirectory();
			logger.info("Resume Process " +processsesToUpdate.size());
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
		String idsSeparatedByComma = ApplicationConfigurationProperties.getPatentCorpusProcessesToResumeIds();
		String[] list = idsSeparatedByComma.split(",");
		for(String item:list)
		{
			String itemTrim = item.trim();
			if(!itemTrim.isEmpty())
			{
				Long processID = Long.valueOf(itemTrim);
				IIEProcess process = InitConfiguration.getDataAccess().getProcessByID(processID);
				if(process==null)
				{
					logger.info("Process Id "+processID+ " not found");
				}
				else
				{
					if(!process.getProcessOrigin().getOrigin().equals(LinnaeusTagger.linnausOrigin.getOrigin()))
					{
						logger.info("Process Id "+processID+ " is not a Linnaeus Process");
					}
					else
					{
						out.add(process);
					}
				}
			}
			else
			{
				logger.info("No Processes to update");
			}
		}
		return out;
	}

	/**
	 * Get ICorpusUpdateConfiguration for USPTO Grant Configuration
	 * 
	 * @return
	 * @throws ANoteException
	 */
	public 	ICorpusUpdateConfiguration getUSPTOGrantCorpusUpdateConfiguration() throws ANoteException
	{
		String usptoGrantPatentDirectory = ApplicationConfigurationProperties.getUSPTOGrantPatentCorpusUpdateDir();
		return getConfiguration(usptoGrantPatentDirectory);
	}
	
	/**
	 * Get ICorpusUpdateConfiguration for USPTO Application Configuration
	 * 
	 * @return
	 * @throws ANoteException
	 */
	public 	ICorpusUpdateConfiguration getUSPTOApplicationCorpusUpdateConfiguration() throws ANoteException
	{
		String usptoApplicationPatentDirectory = ApplicationConfigurationProperties.getUSPTOApplicationPatentCorpusUpdateDir();
		return getConfiguration(usptoApplicationPatentDirectory);
	}

	/**
	 * Get ICorpusUpdateConfiguration given base files dir
	 * 
	 * @param usptoPatentDirectory
	 * @return
	 * @throws ANoteException
	 */
	private ICorpusUpdateConfiguration getConfiguration(String basefilesdir) throws ANoteException {
		Properties properties = new Properties();
		long corpusID = Long.valueOf(ApplicationConfigurationProperties.getPatentCorpusID());
		ICorpus corpusToUpdate = InitConfiguration.getDataAccess().getCorpusByID(corpusID );
		CorpusUpdateConfigurationImpl corpusupdateConfiguration = new CorpusUpdateConfigurationImpl(corpusToUpdate, basefilesdir, properties,CorpusCreateSourceEnum.USPTO);
		return corpusupdateConfiguration;
	}
	
	/**
	 * In the end of process move files to archive dir - USPTOGrant
	 * 
	 * @return
	 * @throws IOException
	 */
	public static boolean moveUSPTOGrantFilesToFinalDirectory() throws IOException{
		String tmpDir = ApplicationConfigurationProperties.getUSPTOGrantPatentCorpusUpdateDir();
		String finalDir = ApplicationConfigurationProperties.getUSPTOGrantPatentCorpusToArchiveDir();
		moveUSPTOFiles(tmpDir,finalDir);
		return true;		
	}
	
	/**
	 * In the end of process move files to archive dir - USPTOApplication
	 * 
	 * @return
	 * @throws IOException
	 */
	public static boolean moveUSPTOApplicationFilesToFinalDirectory() throws IOException{
		String tmpDir = ApplicationConfigurationProperties.getUSPTOApplicationPatentCorpusUpdateDir();
		String finalDir = ApplicationConfigurationProperties.getUSPTOApplicationPatentCorpusToArchiveDir();
		moveUSPTOFiles(tmpDir,finalDir);
		return true;		
	}
	
	private static void moveUSPTOFiles(String tmpDir, String finalDir) throws IOException
	{
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
	}

}
