package com.silicolife.anote2daemon.service.core;

import com.silicolife.anote2daemon.utils.DaemonResponse;

public interface QueriesTypeService {

	public DaemonResponse getById(Long id);

	public DaemonResponse update(Long id, String description);

	public DaemonResponse create(String description);

	public DaemonResponse getByOrAttributes(Long id, String description);

	public DaemonResponse getByAttributes(Long id, String description);

}
