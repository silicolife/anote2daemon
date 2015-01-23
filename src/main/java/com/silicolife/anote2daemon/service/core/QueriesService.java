package com.silicolife.anote2daemon.service.core;

import com.silicolife.anote2daemon.model.pojo.Queries;
import com.silicolife.anote2daemon.webservice.DaemonResponse;

public interface QueriesService {

	public DaemonResponse<Queries> getById(Long id);

	public DaemonResponse<Queries> create(Queries query);

	public DaemonResponse<Queries> update(Queries query);

}
