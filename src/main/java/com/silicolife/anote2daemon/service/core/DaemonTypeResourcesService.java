package com.silicolife.anote2daemon.service.core;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.silicolife.anote2daemon.model.pojo.DaemonTypeResources;

public interface DaemonTypeResourcesService {
	public DaemonTypeResources getById(Long id);

	public List<DaemonTypeResources> getByAttributes(Map<String, Serializable> eqRestrictions);

	public DaemonTypeResources create(DaemonTypeResources resourceType);

	public DaemonTypeResources update(DaemonTypeResources resourceType);
}
