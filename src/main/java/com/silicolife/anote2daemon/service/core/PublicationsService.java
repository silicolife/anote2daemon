package com.silicolife.anote2daemon.service.core;

import java.util.List;
import java.util.Map;

import com.silicolife.anote2daemon.model.pojo.Publications;

/**
 * Interface to define all methods of Service Layer publications
 * 
 * @author Joel Azevedo Costa
 * @year 2015
 *
 */
public interface PublicationsService {

	/**
	 * Get all fields publication by id
	 * 
	 * @param id
	 * @return
	 */
	public Publications getById(Long id);

	/**
	 * Get publication only with few columns (without full text)
	 * 
	 * @param id
	 * @return
	 */
	public Publications getFewColumnsById(Long id);

	/**
	 * Create publication
	 * 
	 * @param publication
	 * @return
	 */
	public Boolean create(Publications publication);

	/**
	 * Update publication
	 * 
	 * @param publication
	 * @return
	 */
	public Boolean update(Publications publication);

	/**
	 * Add list of publications to daemon
	 * 
	 * @param documents
	 */
	public Boolean addPublications(List<Publications> publications);
	
	/**
	 * Get all publications from a source
	 * 
	 * 
	 * @param source
	 * @return
	 */
	public Map<String,Publications> getAllPublicationsFromSource(String source);
}
