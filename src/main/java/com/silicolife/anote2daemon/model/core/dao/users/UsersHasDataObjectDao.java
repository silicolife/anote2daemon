package com.silicolife.anote2daemon.model.core.dao.users;

import java.util.List;

import com.silicolife.anote2daemon.model.core.dao.GenericDao;
import com.silicolife.anote2daemon.model.core.entities.UsersHasDataObject;

public interface UsersHasDataObjectDao extends GenericDao<UsersHasDataObject> {

	public static final Class<UsersHasDataObject> className = UsersHasDataObject.class;

	public List<Object[]> findQueriesByAttributes(Long id, Long resourceType);
}
