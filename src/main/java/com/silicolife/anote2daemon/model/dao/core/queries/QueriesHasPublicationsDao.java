package com.silicolife.anote2daemon.model.dao.core.queries;

import java.util.List;

import com.silicolife.anote2daemon.model.dao.core.GenericDao;
import com.silicolife.anote2daemon.model.pojo.QueriesHasPublications;

public interface QueriesHasPublicationsDao extends GenericDao<QueriesHasPublications> {

	public static final Class<QueriesHasPublications> className = QueriesHasPublications.class;

	public List<Object[]> findPublicationsRelevanceFromQuery(Long queryId);

}
