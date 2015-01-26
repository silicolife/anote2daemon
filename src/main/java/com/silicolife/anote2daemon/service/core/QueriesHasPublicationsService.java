package com.silicolife.anote2daemon.service.core;

import com.silicolife.anote2daemon.model.pojo.QueriesHasPublications;
import com.silicolife.anote2daemon.model.pojo.QueriesHasPublicationsId;
import com.silicolife.anote2daemon.webservice.DaemonResponse;

public interface QueriesHasPublicationsService {

	public DaemonResponse<QueriesHasPublications> getById(QueriesHasPublicationsId id);

	public DaemonResponse<QueriesHasPublications> getById(Long queriesId, Long publicationsId);

	public DaemonResponse<QueriesHasPublications> create(QueriesHasPublications queriesHasPub);

	public DaemonResponse<QueriesHasPublications> update(QueriesHasPublications queriesHasPub);
}
