package com.silicolife.anote2daemon.service.core;

import com.silicolife.anote2daemon.utils.DaemonResponse;

public interface PublicationsService {
	public DaemonResponse getById(Long id);
}
