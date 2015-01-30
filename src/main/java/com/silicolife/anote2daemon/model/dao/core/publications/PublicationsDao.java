package com.silicolife.anote2daemon.model.dao.core.publications;

import com.silicolife.anote2daemon.model.dao.core.GenericDao;
import com.silicolife.anote2daemon.model.pojo.Publications;

public interface PublicationsDao extends GenericDao<Publications> {

	public static final Class<Publications> className = Publications.class;

	public Publications findFewColumnsById(Long id);

}
