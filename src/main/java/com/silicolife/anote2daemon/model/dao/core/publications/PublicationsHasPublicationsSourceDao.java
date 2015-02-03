package com.silicolife.anote2daemon.model.dao.core.publications;

import java.util.List;

import com.silicolife.anote2daemon.model.dao.core.GenericDao;
import com.silicolife.anote2daemon.model.pojo.PublicationsHasPublicationsSource;

public interface PublicationsHasPublicationsSourceDao extends GenericDao<PublicationsHasPublicationsSource> {

	public static final Class<PublicationsHasPublicationsSource> className = PublicationsHasPublicationsSource.class;
	
	public List<Object[]> findPublicationsFromSource(Long sourceId);
}
