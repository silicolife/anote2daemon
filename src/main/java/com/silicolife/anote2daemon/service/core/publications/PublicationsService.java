package com.silicolife.anote2daemon.service.core.publications;

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
	 * Get publication by id
	 * 
	 * @param id
	 * @return
	 */
	public Publications getById(Long id);

	/**
	 * Get publication only with few columns, without full text
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
	public Publications create(Publications publication);

	/**
	 * Update publication
	 * 
	 * @param publication
	 * @return
	 */
	public Publications update(Publications publication);
}
