package com.silicolife.anote2daemon.wrapper.queries;

import java.util.Date;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import pt.uminho.anote2.datastructures.documents.query.Query;
import pt.uminho.anote2.datastructures.documents.query.QueryOriginType;
import pt.uminho.anote2.process.IR.IQuery;
import pt.uminho.anote2.process.IR.IQueryOriginType;

import com.silicolife.anote2daemon.model.core.entities.Queries;
import com.silicolife.anote2daemon.model.core.entities.QueriesProperties;
import com.silicolife.anote2daemon.model.core.entities.QueriesPropertiesId;
import com.silicolife.anote2daemon.model.core.entities.QueriesType;

/**
 * 
 * Class to transform anote2 Query structures to daemon Query structures and
 * vice-versa
 * 
 * 
 * @author Joel Azevedo Costa
 * @year 2015
 *
 */
public class QueriesWrapper {

	public IQuery convertToAnoteStructure(Queries daemonObject) {
		/*
		 * create query type
		 */
		Long queryTypeId = daemonObject.getQueriesType().getId();
		String queryTypeDesc = daemonObject.getQueriesType().getDescription();
		IQueryOriginType queryType_ = new QueryOriginType(queryTypeId, queryTypeDesc);
		/**
		 * create properties
		 */
		Properties properties = null;
		Set<QueriesProperties> queryProperties = daemonObject.getQueriesPropertieses();
		if (queryProperties.size() > 0) {
			properties = new Properties();
			for (QueriesProperties queryProperty : queryProperties) {
				String key = queryProperty.getId().getPropKey();
				String value = queryProperty.getPropValue();
				properties.put(key, value);
			}
		}
		/*
		 * create query
		 */
		Long id = daemonObject.getId();
		Date date = daemonObject.getQueryDate();
		String keywords = daemonObject.getKeywords();
		String organism = daemonObject.getOrganism();
		String completeQuery = daemonObject.getCompleteQuery();
		int publicationsSize = daemonObject.getMatchingPublications();
		int availableAbstract = daemonObject.getAvailableAbstracts();
		String name = daemonObject.getQueryName();
		String notes = daemonObject.getNotes();

		Query query = new Query(id, queryType_, date, keywords, organism, completeQuery, publicationsSize, availableAbstract, name, notes, properties);

		return query;
	}

	public Queries convertToDaemonStructure(IQuery parameter) {
		/*
		 * get parameters
		 */
		Long id = parameter.getID();
		Date date = parameter.getDate();
		String keywords = parameter.getKeyWords();
		String organism = parameter.getOrganism();
		String completeQuery = parameter.getCompleteQuery();
		int publicationsSize = parameter.getPublicationsSize();
		int availableAbstract = parameter.getAvailableAbstracts();
		String queryName = parameter.getName();
		String notes = parameter.getNotes();
		Boolean active = true;
		/*
		 * create query type
		 */
		IQueryOriginType queryType = parameter.getQueryOriginType();
		Long queryTypeId = queryType.getID();
		String querytypeDesc = queryType.getOrigin();
		QueriesType queryTypeDaemon = new QueriesType(queryTypeId);
		queryTypeDaemon.setDescription(querytypeDesc);
		/*
		 * create query
		 */
		Queries query = new Queries(id, queryTypeDaemon, date, keywords, active);

		/*
		 * create query properties
		 */
		Properties properties = parameter.getProperties();
		Set<QueriesProperties> queriesProperties = new HashSet<QueriesProperties>(0);
		for (String key : properties.stringPropertyNames()) {
			QueriesPropertiesId queriesPropertiesId = new QueriesPropertiesId(id, key);
			String value = properties.getProperty(key);
			QueriesProperties queriesPropertiesDaemon = new QueriesProperties(queriesPropertiesId, query, value);
			queriesProperties.add(queriesPropertiesDaemon);
		}
		/*
		 * set query data
		 */
		query.setOrganism(organism);
		query.setCompleteQuery(completeQuery);
		query.setMatchingPublications(publicationsSize);
		query.setAvailableAbstracts(availableAbstract);
		query.setQueryName(queryName);
		query.setNotes(notes);
		query.setQueriesPropertieses(queriesProperties);

		return query;
	}
}
