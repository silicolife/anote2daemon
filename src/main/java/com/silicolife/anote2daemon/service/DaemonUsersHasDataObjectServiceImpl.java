package com.silicolife.anote2daemon.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.silicolife.anote2daemon.model.dao.core.DaemonUserHasDataObjectDao;
import com.silicolife.anote2daemon.model.pojo.DaemonUsersHasDataObject;
import com.silicolife.anote2daemon.model.pojo.DaemonUsersHasDataObjectId;
import com.silicolife.anote2daemon.service.core.DaemonUserHasDataObjectService;

@Transactional(readOnly = true)
@Service
public class DaemonUsersHasDataObjectServiceImpl implements DaemonUserHasDataObjectService {

	@Autowired
	private DaemonUserHasDataObjectDao daemonUserHasDataObjectDao;

	private final static Class<DaemonUsersHasDataObject> className = DaemonUsersHasDataObject.class;

	@Override
	public DaemonUsersHasDataObject getById(DaemonUsersHasDataObjectId id) {
		DaemonUsersHasDataObject dataObject = daemonUserHasDataObjectDao.findById(className, id);
		return dataObject;
	}

	@Override
	public DaemonUsersHasDataObject create(DaemonUsersHasDataObject daemonUserObject) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DaemonUsersHasDataObject update(DaemonUsersHasDataObject daemonUserObject) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DaemonUsersHasDataObject> getByAttributes(Map<String, Serializable> eqRestrictions) {
		List<DaemonUsersHasDataObject> dataObjects = daemonUserHasDataObjectDao.findByAttributes(className, eqRestrictions);
		if (dataObjects.size() == 0)
			return null;

		return dataObjects;
	}
}
