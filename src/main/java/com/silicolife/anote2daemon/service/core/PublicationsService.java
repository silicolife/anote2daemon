package com.silicolife.anote2daemon.service.core;

import com.silicolife.anote2daemon.model.pojo.Publications;
import com.silicolife.anote2daemon.webservice.DaemonResponse;

public interface PublicationsService {
	public DaemonResponse<Publications> getById(Long id);

	public DaemonResponse<Publications> create(Publications publication);

	public DaemonResponse<Publications> update(Publications publication);
}
