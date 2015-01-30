package com.silicolife.anote2daemon.service.core;

import com.silicolife.anote2daemon.model.pojo.Publications;

public interface PublicationsService {
	public Publications getById(Long id);

	public Publications create(Publications publication);

	public Publications update(Publications publication);
}
