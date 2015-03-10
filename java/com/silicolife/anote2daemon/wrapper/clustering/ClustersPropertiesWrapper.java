package com.silicolife.anote2daemon.wrapper.clustering;

import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import com.silicolife.anote2daemon.model.core.entities.ClustersProcesses;
import com.silicolife.anote2daemon.model.core.entities.ClustersProperties;
import com.silicolife.anote2daemon.model.core.entities.ClustersPropertiesId;

/**
 * Class to transform anote2 Cluster Properties structures to daemon
 * Cluster Properties structures and vice-verse
 * 
 * @author Joel Azevedo Costa
 * @year 2015
 *
 */
public class ClustersPropertiesWrapper {

	public static Properties convertToAnoteStructure(Set<ClustersProperties> clusterProperties) {
	
		Properties properties = new Properties();
		for (ClustersProperties clusterProperty : clusterProperties) {
			String key = clusterProperty.getId().getPropKey();
			String value = clusterProperty.getPropValue();
			properties.put(key, value);
		}

		if (properties.size() == 0)
			return null;

		return properties;
	}

	public static Set<ClustersProperties> convertToDaemonStructure(Properties properties, ClustersProcesses clusterProcess) {

		Set<ClustersProperties> clusterProperties = new HashSet<ClustersProperties>(0);
		for (String key : properties.stringPropertyNames()) {
			ClustersPropertiesId clusterPropertiesId = new ClustersPropertiesId(clusterProcess.getId(), key);
			String value = properties.getProperty(key);
			ClustersProperties clusterPropertiesDaemon = new ClustersProperties(clusterPropertiesId, clusterProcess, value);
			clusterProperties.add(clusterPropertiesDaemon);
		}

		return clusterProperties;
	}
}
