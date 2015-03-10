package com.silicolife.anote2daemon.wrapper.process;

import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import com.silicolife.anote2daemon.model.core.entities.Processes;
import com.silicolife.anote2daemon.model.core.entities.ProcessesProperties;
import com.silicolife.anote2daemon.model.core.entities.ProcessesPropertiesId;

public class ProcessPropertiesWrapper {

	public static Properties convertToAnoteStructure(Set<ProcessesProperties> processProperties) {

		Properties properties = new Properties();
		for (ProcessesProperties processProperty : processProperties) {
			String key = processProperty.getId().getPropKey();
			String value = processProperty.getPropValue();
			properties.put(key, value);
		}
		if (properties.size() == 0)
			return null;

		return properties;
			
	}

	public static Set<ProcessesProperties> convertToDaemonStructure(Properties properties, Processes process) {
			
		Set<ProcessesProperties> processsesProperties = new HashSet<ProcessesProperties>(0);
		for (String key : properties.stringPropertyNames()) {
			ProcessesPropertiesId id = new ProcessesPropertiesId(process.getId(), key);
			String value = properties.getProperty(key);
			ProcessesProperties processProperties = new ProcessesProperties(id, process, value);
			processsesProperties.add(processProperties);
		}

		return processsesProperties;

	}
	
}
