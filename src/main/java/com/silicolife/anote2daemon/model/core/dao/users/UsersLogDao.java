package com.silicolife.anote2daemon.model.core.dao.users;

import com.silicolife.anote2daemon.model.core.dao.GenericDao;
import com.silicolife.anote2daemon.model.core.entities.UsersLog;

public interface UsersLogDao extends GenericDao<UsersLog>{

	public static final Class<UsersLog> className = UsersLog.class;
}
