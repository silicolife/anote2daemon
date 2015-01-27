package com.silicolife.anote2daemon.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.silicolife.anote2daemon.model.dao.core.DaemonTypeResourcesDao;
import com.silicolife.anote2daemon.model.pojo.DaemonTypeResources;
import com.silicolife.anote2daemon.service.core.DaemonTypeResourcesService;
import com.silicolife.anote2daemon.webservice.DaemonResponse;

public class DaemonTypeResourcesServiceImpl implements DaemonTypeResourcesService {

	@Autowired
	private DaemonTypeResourcesDao daemonTypeResourcesDao;

	private final static Class<DaemonTypeResources> className = DaemonTypeResources.class;

	@Override
	public DaemonResponse<DaemonTypeResources> getById(Long id) {
		DaemonTypeResources typeResource = daemonTypeResourcesDao.findById(className, id);
		DaemonResponse<DaemonTypeResources> response = new DaemonResponse<DaemonTypeResources>(typeResource);
		return response;
	}

	@Override
	public DaemonResponse<DaemonTypeResources> create(DaemonTypeResources publication) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DaemonResponse<DaemonTypeResources> update(DaemonTypeResources publication) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DaemonResponse<List<DaemonTypeResources>> getByAttributes(Map<String, Serializable> eqRestrictions) {
		List<DaemonTypeResources> typeResource = daemonTypeResourcesDao.findByAttributes(className, eqRestrictions);
		if (typeResource.size() == 0)
			return new DaemonResponse<List<DaemonTypeResources>>();

		DaemonResponse<List<DaemonTypeResources>> response = new DaemonResponse<List<DaemonTypeResources>>(typeResource);
		return response;
	}

}
