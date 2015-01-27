package com.silicolife.anote2daemon.model.dao.core;

import java.util.List;

import com.silicolife.anote2daemon.model.pojo.DaemonUsersHasDataObject;

public interface DaemonUserHasDataObjectDao extends GenericDao<DaemonUsersHasDataObject> {

	public List<Object> findObjectsByAttributes(Long id, Long resourceType);
}
