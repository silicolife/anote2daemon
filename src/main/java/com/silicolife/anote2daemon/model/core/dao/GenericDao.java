package com.silicolife.anote2daemon.model.core.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface GenericDao<T> {

	public T findById(Class<T> klass, Serializable id);

	public List<T> findAll(Class<T> klass);

	public List<T> findByAttributes(Class<T> klass, Map<String, Serializable> eqRestrictions);

	public T findUniqueByAttribute(Class<T> klass, String attribute, Serializable value);

	public List<T> findByOrAttributes(Class<T> klass, Map<String, Serializable> orRestrictions);

	public void save(Object object);

	public void update(Object object);

	public void delete(Object object);
}
