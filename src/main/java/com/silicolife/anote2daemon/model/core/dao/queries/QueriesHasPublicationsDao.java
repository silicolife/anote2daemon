package com.silicolife.anote2daemon.model.core.dao.queries;

import java.util.List;

import com.silicolife.anote2daemon.model.core.dao.GenericDao;
import com.silicolife.anote2daemon.model.core.entities.QueriesHasPublications;

public interface QueriesHasPublicationsDao extends GenericDao<QueriesHasPublications> {

	public static final Class<QueriesHasPublications> className = QueriesHasPublications.class;

	public List<Object[]> findPublicationsRelevanceFromQuery(Long queryId);

}
