package com.silicolife.anote2daemon.service.core;

import com.silicolife.anote2daemon.model.pojo.Queries;
import com.silicolife.anote2daemon.webservice.DaemonResponse;

public interface QueriesService {

	public DaemonResponse<Queries> getById(Long id);

	public DaemonResponse<Queries> update();

	public DaemonResponse<Queries> create();

}
