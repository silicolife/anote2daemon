package com.silicolife.anote2daemon.service.core;

import com.silicolife.anote2daemon.utils.DaemonResponse;

public interface QueriesService {

	public DaemonResponse getById(Long id);

	public DaemonResponse update();

	public DaemonResponse create();

}