package com.silicolife.anote2daemon.model.core.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Define all generic methos to access the data.
 * 
 * 
 * @author Joel Azevedo Costa
 * @year 2015
 *
 * @param <T>
 */
public interface GenericDao<T> {

	public T findById(Serializable id);

	public List<T> findAll();

	public List<T> findByAttributes(Map<String, Serializable> eqRestrictions);

	public T findUniqueByAttribute(String attribute, Serializable value);

	public List<T> findByOrAttributes(Map<String, Serializable> orRestrictions);

	public void save(Object object);

	public void update(Object object);

	public void delete(Object object);
}
