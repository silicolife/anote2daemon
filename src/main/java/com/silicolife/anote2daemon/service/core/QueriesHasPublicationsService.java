package com.silicolife.anote2daemon.service.core;

import com.silicolife.anote2daemon.model.pojo.QueriesHasPublications;
import com.silicolife.anote2daemon.model.pojo.QueriesHasPublicationsId;

public interface QueriesHasPublicationsService {

	public QueriesHasPublications getById(QueriesHasPublicationsId id);

	public QueriesHasPublications getById(Long queriesId, Long publicationsId);

	public QueriesHasPublications create(QueriesHasPublications queriesHasPub);

	public QueriesHasPublications update(QueriesHasPublications queriesHasPub);
}
