package com.silicolife.anote2daemon.service.resources;

import java.util.Map;

import com.silicolife.anote2daemon.model.core.entities.Classes;

/**
 * Interface to define all methods of Service Layer classes
 * 
 * @author Joel Azevedo Costa
 * @year 2015
 *
 */
public interface ClassesService {

	/**
	 * Create new class
	 * 
	 * @param classes
	 * @return
	 */
	public Boolean insertNewClass(Classes classes);

	/**
	 * get map of all classes
	 * 
	 * @return
	 */
	public Map<Long, String> getClasses();

	/**
	 * get classe by id
	 * 
	 * @param id
	 * @return
	 */
	public Classes getClassById(Long id);

}
