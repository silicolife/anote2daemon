package com.silicolife.anote2daemon.model.dao.core;

import java.io.Serializable;
import java.util.List;

public interface GenericDao<T> {

	public T find(Class<T> klass, Serializable id);

	public List<T> findAll(Class<T> klass);

	public void save(Object object);

	public void update(Object object);

	public void delete(Object object);
}
