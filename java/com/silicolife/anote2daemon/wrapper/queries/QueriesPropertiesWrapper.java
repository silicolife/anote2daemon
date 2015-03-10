package com.silicolife.anote2daemon.wrapper.queries;

import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import com.silicolife.anote2daemon.model.core.entities.Queries;
import com.silicolife.anote2daemon.model.core.entities.QueriesProperties;
import com.silicolife.anote2daemon.model.core.entities.QueriesPropertiesId;

/**
 * Class to transform anote2 Query properties structures to daemon Query properties structures and
 * vice-verse
 * 
 * @author Joel Azevedo Costa
 * @year 2015
 *
 */
public class QueriesPropertiesWrapper{

	public static Properties convertToAnoteStructure(Set<QueriesProperties> queryProperties) {
		Properties properties = null;
		if (queryProperties.size() > 0) {
			properties = new Properties();
			for (QueriesProperties queryProperty : queryProperties) {
				String key = queryProperty.getId().getPropKey();
				String value = queryProperty.getPropValue();
				properties.put(key, value);
			}
		}
		return properties;
	}

	public static Set<QueriesProperties> convertToDaemonStructure(Properties properties, Queries query) {
		
		Set<QueriesProperties> queriesProperties = new HashSet<QueriesProperties>(0);
		for (String key : properties.stringPropertyNames()) {
			QueriesPropertiesId queriesPropertiesId = new QueriesPropertiesId(query.getId(), key);
			String value = properties.getProperty(key);
			QueriesProperties queriesPropertiesDaemon = new QueriesProperties(queriesPropertiesId, query, value);
			queriesProperties.add(queriesPropertiesDaemon);
		}

		return queriesProperties;
	}

}
