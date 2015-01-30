package com.silicolife.anote2daemon.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.silicolife.anote2daemon.model.dao.core.DaemonTypeResourcesDao;
import com.silicolife.anote2daemon.model.pojo.DaemonTypeResources;
import com.silicolife.anote2daemon.service.core.DaemonTypeResourcesService;
@Transactional(readOnly = true)
@Service
public class DaemonTypeResourcesServiceImpl implements DaemonTypeResourcesService {

	@Autowired
	private DaemonTypeResourcesDao daemonTypeResourcesDao;

	@Override
	public DaemonTypeResources getById(Long id) {
		DaemonTypeResources typeResource = daemonTypeResourcesDao.findById(DaemonTypeResourcesDao.className, id);
		return typeResource;
	}

	@Override
	public DaemonTypeResources create(DaemonTypeResources publication) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DaemonTypeResources update(DaemonTypeResources publication) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DaemonTypeResources> getByAttributes(Map<String, Serializable> eqRestrictions) {
		List<DaemonTypeResources> typeResource = daemonTypeResourcesDao.findByAttributes(DaemonTypeResourcesDao.className, eqRestrictions);
		if (typeResource.size() == 0)
			return null;

		return typeResource;
	}

}
