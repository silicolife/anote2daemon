package com.silicolife.anote2daemon.service.queries;

import java.util.List;
import java.util.Map;

import pt.uminho.anote2.core.document.IPublication;
import pt.uminho.anote2.datastructures.documents.query.Query;
import pt.uminho.anote2.process.IR.IQuery;

import com.silicolife.anote2daemon.model.core.RelevanceType;

/**
 * Interface to define all methods of Service Layer Queries
 * 
 * @author Joel Azevedo Costa
 * @year 2015
 * 
 */
public interface QueriesService {

	/**
	 * Get query by id
	 * 
	 * @param id
	 * @return
	 */
	public IQuery getById(Long id);

	/**
	 * Get all queries associated from a user
	 * 
	 * @return
	 */
	public List<IQuery> getAll();

	/**
	 * Get all publications from a query
	 * 
	 * @param id
	 * @return
	 */
	public List<IPublication> getQueryPublications(Long id);

	/**
	 * Create a query
	 * 
	 * @param query
	 * @return
	 */
	public Boolean create(Query query);

	/**
	 * Update a Query
	 * 
	 * @param query
	 * @return
	 */
	public Boolean update(Query query);

	/**
	 * Associate publications to a query
	 * 
	 * @param id
	 * @param publicationsIds
	 * @return
	 */
	public Boolean addPublicationsToQuery(Long id, List<Long> publicationsIds);

	/**
	 * Update the relevance from a publication in a query
	 * 
	 * @param queryId
	 * @param publicationId
	 * @param relevance
	 * @return
	 */
	public Boolean updateRelevance(Long queryId, Long publicationId, String relevance);

	/**
	 * Get relevance from a publications from a query
	 * 
	 * 
	 * @param queryId
	 * @return
	 */
	public Map<Long, RelevanceType> getQueryPublicationsRelevance(Long queryId);

}
