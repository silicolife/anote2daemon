package com.silicolife.anote2daemon.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.silicolife.anote2daemon.model.dao.core.DaemonUserHasDataObjectDao;
import com.silicolife.anote2daemon.model.pojo.DaemonUsersHasDataObject;
import com.silicolife.anote2daemon.model.pojo.DaemonUsersHasDataObjectId;
import com.silicolife.anote2daemon.service.core.DaemonUserHasDataObjectService;
import com.silicolife.anote2daemon.webservice.DaemonResponse;

public class DaemonUsersHasDataObjectServiceImpl implements DaemonUserHasDataObjectService {

	@Autowired
	private DaemonUserHasDataObjectDao daemonUserHasDataObjectDao;

	private final static Class<DaemonUsersHasDataObject> className = DaemonUsersHasDataObject.class;

	@Override
	public DaemonResponse<DaemonUsersHasDataObject> getById(DaemonUsersHasDataObjectId id) {
		DaemonUsersHasDataObject dataObject = daemonUserHasDataObjectDao.findById(className, id);
		DaemonResponse<DaemonUsersHasDataObject> response = new DaemonResponse<DaemonUsersHasDataObject>(dataObject);
		return response;
	}

	@Override
	public DaemonResponse<DaemonUsersHasDataObject> create(DaemonUsersHasDataObject daemonUserObject) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DaemonResponse<DaemonUsersHasDataObject> update(DaemonUsersHasDataObject daemonUserObject) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DaemonResponse<List<DaemonUsersHasDataObject>> getByAttributes(Map<String, Serializable> eqRestrictions) {
		List<DaemonUsersHasDataObject> dataObjects = daemonUserHasDataObjectDao.findByAttributes(className, eqRestrictions);
		if (dataObjects.size() == 0)
			return new DaemonResponse<List<DaemonUsersHasDataObject>>();

		DaemonResponse<List<DaemonUsersHasDataObject>> response = new DaemonResponse<List<DaemonUsersHasDataObject>>(dataObjects);
		return response;
	}

	@Override
	public DaemonResponse<List<Object>> getObjectsByAttributes(Long id, Long resourceType) {
		List<Object> dataObjects = daemonUserHasDataObjectDao.findObjectsByAttributes(id, resourceType);
		if (dataObjects.size() == 0)
			return new DaemonResponse<List<Object>>();

		DaemonResponse<List<Object>> response = new DaemonResponse<List<Object>>(dataObjects);
		return response;
	}

}
