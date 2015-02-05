package com.silicolife.anote2daemon.model.core.dao.queries;

import com.silicolife.anote2daemon.model.core.dao.GenericDao;
import com.silicolife.anote2daemon.model.core.entities.Publications;

public interface PublicationsDao extends GenericDao<Publications> {

	public static final Class<Publications> className = Publications.class;

	public Publications findFewColumnsById(Long id);
	
}
