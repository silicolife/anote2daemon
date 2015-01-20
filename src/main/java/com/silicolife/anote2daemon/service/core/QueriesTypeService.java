package com.silicolife.anote2daemon.service.core;

import com.silicolife.anote2daemon.utils.DaemonResponse;

public interface QueriesTypeService {

	public DaemonResponse getById(Long id);

	public DaemonResponse update();

	public DaemonResponse create(String description);

	public DaemonResponse findByOrAttributes(Long id, String description);

}
