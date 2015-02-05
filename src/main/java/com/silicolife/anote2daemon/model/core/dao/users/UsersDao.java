package com.silicolife.anote2daemon.model.core.dao.users;

import com.silicolife.anote2daemon.model.core.dao.GenericDao;
import com.silicolife.anote2daemon.model.core.entities.Users;

public interface UsersDao extends GenericDao<Users> {

	public static final Class<Users> className = Users.class;

}
