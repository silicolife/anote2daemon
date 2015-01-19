package com.silicolife.anote2daemon.model.dao.core;

import java.io.Serializable;
import java.util.List;


public interface GenericDao {

	public <T> T find(Class<T> klass, Serializable id);

	public <T> List<T> findAll(Class<T> klass);
}
