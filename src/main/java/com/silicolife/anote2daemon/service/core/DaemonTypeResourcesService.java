package com.silicolife.anote2daemon.service.core;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.silicolife.anote2daemon.model.pojo.DaemonTypeResources;
import com.silicolife.anote2daemon.webservice.DaemonResponse;

public interface DaemonTypeResourcesService {
	public DaemonResponse<DaemonTypeResources> getById(Long id);

	public DaemonResponse<List<DaemonTypeResources>> getByAttributes(Map<String, Serializable> eqRestrictions);

	public DaemonResponse<DaemonTypeResources> create(DaemonTypeResources resourceType);

	public DaemonResponse<DaemonTypeResources> update(DaemonTypeResources resourceType);
}
