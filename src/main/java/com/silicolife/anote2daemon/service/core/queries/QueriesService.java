package com.silicolife.anote2daemon.service.core.queries;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.silicolife.anote2daemon.model.pojo.Publications;
import com.silicolife.anote2daemon.model.pojo.Queries;

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
	public Map<String, Queries> getById(Long id);

	/**
	 * Get all queries associated from a user
	 * 
	 * @return
	 */
	public Map<String, Set<Queries>> getAll();

	/**
	 * Get all publications from a query
	 * 
	 * @param id
	 * @return
	 */
	public Map<String, Set<Publications>> getAllPublications(Long id);

	/**
	 * Create a query
	 * 
	 * @param query
	 * @return
	 */
	public Boolean create(Queries query);

	/**
	 * Update a Query
	 * 
	 * @param query
	 * @return
	 */
	public Boolean update(Queries query);

	/**
	 * Associate publications to a query
	 * 
	 * @param id
	 * @param publicationsIds
	 * @return
	 */
	public boolean addPublicationsToQuery(Long id, List<Long> publicationsIds);

}
