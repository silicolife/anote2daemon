package com.silicolife.anote2daemon.service.core;

import java.util.List;
import java.util.Map;

import com.silicolife.anote2daemon.model.pojo.DaemonUsers;
import com.silicolife.anote2daemon.model.pojo.Queries;
import com.silicolife.anote2daemon.webservice.DaemonResponse;


public interface QueriesService {

	public DaemonResponse<Queries> getById(Long id);

	public DaemonResponse<Map<String, List<Queries>>> getAll(DaemonUsers users);

	public DaemonResponse<Queries> create(Queries query);

	public DaemonResponse<Queries> update(Queries query);

}
