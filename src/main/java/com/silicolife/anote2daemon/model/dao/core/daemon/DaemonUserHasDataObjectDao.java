package com.silicolife.anote2daemon.model.dao.core.daemon;

import java.util.List;

import com.silicolife.anote2daemon.model.dao.core.GenericDao;
import com.silicolife.anote2daemon.model.pojo.DaemonUsersHasDataObject;

public interface DaemonUserHasDataObjectDao extends GenericDao<DaemonUsersHasDataObject> {

	public static final Class<DaemonUsersHasDataObject> className = DaemonUsersHasDataObject.class;

	public List<Object[]> findQueriesByAttributes(Long id, Long resourceType);
}
