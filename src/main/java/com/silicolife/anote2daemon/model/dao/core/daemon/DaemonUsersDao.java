package com.silicolife.anote2daemon.model.dao.core.daemon;

import com.silicolife.anote2daemon.model.dao.core.GenericDao;
import com.silicolife.anote2daemon.model.pojo.DaemonUsers;

public interface DaemonUsersDao extends GenericDao<DaemonUsers> {

	public static final Class<DaemonUsers> className = DaemonUsers.class;

}
