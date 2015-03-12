package com.silicolife.anote2daemon.service.publications;

import java.util.List;
import java.util.Map;

import pt.uminho.anote2.core.document.IPublication;
import pt.uminho.anote2.datastructures.documents.Publication;

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
	public IPublication getById(Long id);

	/**
	 * Create publication
	 * 
	 * @param publication
	 * @return
	 */
	public Boolean create(List<Publication> publications_);

	/**
	 * Update publication
	 * 
	 * @param publication
	 * @return
	 */
	public Boolean update(Publication publication_);

	/**
	 * Get all SourceId from a publications
	 * 
	 * 
	 * @param source
	 * @return
	 */
	public Map<String, Long> getAllPublicationsFromSource(String source);

	/**
	 * Get full text from a publication
	 * 
	 * @param id
	 * @return
	 */
	public String getFullText(Long id);
}
