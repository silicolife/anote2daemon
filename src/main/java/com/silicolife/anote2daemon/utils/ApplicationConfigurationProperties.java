package com.silicolife.anote2daemon.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;


public class ApplicationConfigurationProperties {

	static Logger logger = Logger.getLogger(ApplicationConfigurationProperties.class);
	private static Properties prop = null;

	private static synchronized Properties getPropConfigurations(){
		if(prop==null)
		{
			prop = new Properties();
			InputStream inputStream = null;
			try {
				String propFileName = "application.properties";
				inputStream = ApplicationConfigurationProperties.class.getClassLoader().getResourceAsStream(propFileName);
				if (inputStream != null) {
					prop.load(inputStream);
				} else {
					throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
				}
			} catch (Exception e) {
				logger.error("Exception: " + e);
			} finally {
				if(inputStream != null)
					try {
						inputStream.close();
					} catch (IOException e) {
					}
			}
		}
		return prop;
	}
	
	public static String getPubmedCorpusId(){
		return getPropConfigurations().getProperty("pubmedcorpusid");
	}
	
	public static String getPubmedCorpusProcessesToResumeIds(){
		return getPropConfigurations().getProperty("pubmedcorpusprocessestoresumeids");
	}
	
	public static String getPubmedCorpusUpdateDir(){
		return getPropConfigurations().getProperty("pubmedcorpusupdatedir");
	}
	
	public static String getPubmedCorpusToArchiveDir(){
		return getPropConfigurations().getProperty("pubmedcorpusafterupdatedir");
	}
	
	public static String getPMCCorpusId(){
		return getPropConfigurations().getProperty("pmccorpusid");
	}
	
	public static String getPMCCorpusProcessesToResumeIds(){
		return getPropConfigurations().getProperty("pmccorpusprocessestoresumeids");
	}
	
	public static String getPMCCorpusUpdateDir(){
		return getPropConfigurations().getProperty("pmccorpusupdatedir");
	}
	
	public static String getPMCCorpusToArchiveDir(){
		return getPropConfigurations().getProperty("pmccorpusafterupdatedir");
	}
	
	public static String getPatentCorpusID(){
		return getPropConfigurations().getProperty("patentcorpusid");
	}


	public static String getPatentCorpusProcessesToResumeIds() {
		return getPropConfigurations().getProperty("patentcorpusprocessestoresumeids");
	}
	
	public static String getUSPTOGrantPatentCorpusUpdateDir() {
		return getPropConfigurations().getProperty("patentcorpusupdatedir");
	}

	public static String getUSPTOGrantPatentCorpusToArchiveDir() {
		return getPropConfigurations().getProperty("patentcorpusafterupdatedir");
	}
	
	public static String getUSPTOApplicationPatentCorpusUpdateDir() {
		return getPropConfigurations().getProperty("usptoapplicaitoncorpusupdatedir");
	}

	public static String getUSPTOApplicationPatentCorpusToArchiveDir() {
		return getPropConfigurations().getProperty("usptoapplicaitonpatentcorpusafterupdatedir");
	}
	
	public static String getExportKineticREDir() {
		return getPropConfigurations().getProperty("exportkineticreresult");
	}
	
	public static String getExportResourceDir() {
		return getPropConfigurations().getProperty("exportresourceresult");
	}

}
