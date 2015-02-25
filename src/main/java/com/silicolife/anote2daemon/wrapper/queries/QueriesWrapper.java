package com.silicolife.anote2daemon.wrapper.queries;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import pt.uminho.anote2.core.document.relevance.RelevanceTypeEnum;
import pt.uminho.anote2.datastructures.documents.query.Query;
import pt.uminho.anote2.datastructures.documents.query.QueryOriginType;
import pt.uminho.anote2.process.IR.IQuery;
import pt.uminho.anote2.process.IR.IQueryOriginType;

import com.silicolife.anote2daemon.model.core.entities.PublicationsQueryRelevance;
import com.silicolife.anote2daemon.model.core.entities.Queries;
import com.silicolife.anote2daemon.model.core.entities.QueriesProperties;
import com.silicolife.anote2daemon.model.core.entities.QueriesType;

/**
 * 
 * Class to transform anote2 Query structures to daemon Query structures and
 * vice-verse
 * 
 * 
 * @author Joel Azevedo Costa
 * @year 2015
 *
 */
public class QueriesWrapper {

	public static IQuery convertToAnoteStructure(Queries queries) {
		/*
		 * create query type
		 */
		Long queryTypeId = queries.getQueriesType().getId();
		String queryTypeDesc = queries.getQueriesType().getDescription();
		QueryOriginType queryType_ = new QueryOriginType(queryTypeId, queryTypeDesc);
		/**
		 * create properties
		 */
		Properties properties = QueriesPropertiesWrapper.convertToAnoteStructure(queries.getQueriesPropertieses());
		/*
		 * create publications query relevance
		 */
		Set<PublicationsQueryRelevance> pubQueriesRelevance = queries.getPublicationsQueryRelevances();
		Map<Long, RelevanceTypeEnum> documentRelevance = null;
		if (pubQueriesRelevance.size() > 0) {
			documentRelevance = new HashMap<Long, RelevanceTypeEnum>();
			for (PublicationsQueryRelevance pubRelevance : pubQueriesRelevance) {
				Long pubId = pubRelevance.getId().getPublicationsId();
				RelevanceTypeEnum relevance = RelevanceTypeEnum.convertString(pubRelevance.getRelevance());
				documentRelevance.put(pubId, relevance);
			}
		}
		/*
		 * create query
		 */
		Long id = queries.getId();
		Date date = queries.getQueryDate();
		String keywords = queries.getKeywords();
		String organism = queries.getOrganism();
		String completeQuery = queries.getCompleteQuery();
		int publicationsSize = 0;
		if (queries.getMatchingPublications() != null)
			publicationsSize = queries.getMatchingPublications();
		int availableAbstract = 0;
		if (queries.getAvailableAbstracts() != null)
			availableAbstract = queries.getAvailableAbstracts();
		String name = queries.getQueryName();
		String notes = queries.getNotes();

		IQuery query_ = new Query(id, queryType_, date, keywords, organism, completeQuery, publicationsSize, availableAbstract, name, notes, documentRelevance, properties);

		return query_;
	}

	public static Queries convertToDaemonStructure(IQuery query_) {
		/*
		 * get parameters
		 */
		Long id = query_.getID();
		Date date = query_.getDate();
		String keywords = query_.getKeyWords();
		String organism = query_.getOrganism();
		String completeQuery = query_.getCompleteQuery();
		Integer publicationsSize = query_.getPublicationsSize();
		if (publicationsSize == 0)
			publicationsSize = null;
		Integer availableAbstract = query_.getAvailableAbstracts();
		if (availableAbstract == 0)
			availableAbstract = null;
		String queryName = query_.getName();
		String notes = query_.getNotes();
		Boolean active = true;
		/*
		 * create query type
		 */
		IQueryOriginType queryType = query_.getQueryOriginType();
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
		Set<QueriesProperties> queriesProperties = QueriesPropertiesWrapper.convertToDaemonStructure(query_.getProperties(), query);
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
