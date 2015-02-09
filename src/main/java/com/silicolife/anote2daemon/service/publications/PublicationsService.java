package com.silicolife.anote2daemon.service.publications;

import java.util.List;
import java.util.Map;

import com.silicolife.anote2daemon.model.core.entities.Publications;

/**
 * Interface to define all methods of Service Layer publications
 * 
 * @author Joel Azevedo Costa
 * @year 2015
 *
 */
public interface PublicationsService {

	/**
	 * Get publication by id (without fulltext)
	 * 
	 * @param id
	 * @return
	 */
	public Publications getById(Long id);

	/**
	 * Create publication
	 * 
	 * @param publication
	 * @return
	 */
	public Boolean create(List<Publications> publications);

	/**
	 * Update publication
	 * 
	 * @param publication
	 * @return
	 */
	public Boolean update(Publications publication);

	/**
	 * Get all SourceId from a publications
	 * 
	 * 
	 * @param source
	 * @return
	 */
	public Map<String, Long> getAllPublicationsFromSource(String source);
}
