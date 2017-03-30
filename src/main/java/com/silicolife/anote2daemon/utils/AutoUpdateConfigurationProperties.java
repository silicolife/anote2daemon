package com.silicolife.anote2daemon.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;


public class AutoUpdateConfigurationProperties {
	
	static Logger logger = Logger.getLogger(AutoUpdateConfigurationProperties.class.getName());
	private static Properties prop = null;
	
	private static Properties getPropConfigurations(){
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
		return prop;
	}
	
	public static String getPubmedCorpusId(){
		if(prop == null){
			prop = getPropConfigurations();
		}
		return prop.get("pubmedcorpusid").toString();
	}
	
	public static String getPubmedCorpusProcessesToResumeIds(){
		if(prop == null){
			prop = getPropConfigurations();
		}
		return prop.get("pubmedcorpusprocessestoresumeids").toString();
	}
	
	public static String getPubmedCorpusUpdateDir(){
		if(prop == null){
			prop = getPropConfigurations();
		}
		return prop.get("pubmedcorpusupdatedir").toString();
	}
	
	public static String getPubmedCorpusToArchiveDir(){
		if(prop == null){
			prop = getPropConfigurations();
		}
		return prop.get("pubmedcorpusafterupdatedir").toString();
	}
	
	public static String getPMCCorpusId(){
		if(prop == null){
			prop = getPropConfigurations();
		}
		return prop.get("pmccorpusid").toString();
	}
	
	public static String getPMCCorpusProcessesToResumeIds(){
		if(prop == null){
			prop = getPropConfigurations();
		}
		return prop.get("pmccorpusprocessestoresumeids").toString();
	}
	
	public static String getPMCCorpusUpdateDir(){
		if(prop == null){
			prop = getPropConfigurations();
		}
		return prop.get("pmccorpusupdatedir").toString();
	}
	
	public static String getPMCCorpusToArchiveDir(){
		if(prop == null){
			prop = getPropConfigurations();
		}
		return prop.get("pmccorpusafterupdatedir").toString();
	}

}
