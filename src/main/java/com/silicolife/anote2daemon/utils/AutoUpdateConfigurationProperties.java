package com.silicolife.anote2daemon.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;


public class AutoUpdateConfigurationProperties {

	static Logger logger = Logger.getLogger(AutoUpdateConfigurationProperties.class);
	private static Properties prop = null;

	private static synchronized Properties getPropConfigurations(){
		if(prop==null)
		{
			prop = new Properties();
			InputStream inputStream = null;
			try {
				String propFileName = "autoupdate.properties";
				inputStream = AutoUpdateConfigurationProperties.class.getClassLoader().getResourceAsStream(propFileName);
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

}
