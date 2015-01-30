package com.silicolife.anote2daemon.model.dao.core;

import java.util.List;

import com.silicolife.anote2daemon.model.pojo.DaemonUsersHasDataObject;

public interface DaemonUserHasDataObjectDao extends GenericDao<DaemonUsersHasDataObject> {

	public static final Class<DaemonUsersHasDataObject> className = DaemonUsersHasDataObject.class;

	public List<Object[]> findQueriesByAttributes(Long id, Long resourceType);
}
