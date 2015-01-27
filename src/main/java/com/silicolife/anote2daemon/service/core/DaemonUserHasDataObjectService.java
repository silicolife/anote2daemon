package com.silicolife.anote2daemon.service.core;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.silicolife.anote2daemon.model.pojo.DaemonUsersHasDataObject;
import com.silicolife.anote2daemon.model.pojo.DaemonUsersHasDataObjectId;
import com.silicolife.anote2daemon.webservice.DaemonResponse;

public interface DaemonUserHasDataObjectService {

	public DaemonResponse<DaemonUsersHasDataObject> getById(DaemonUsersHasDataObjectId id);

	public DaemonResponse<List<DaemonUsersHasDataObject>> getByAttributes(Map<String, Serializable> eqRestrictions);

	public DaemonResponse<DaemonUsersHasDataObject> create(DaemonUsersHasDataObject daemonUserObject);

	public DaemonResponse<DaemonUsersHasDataObject> update(DaemonUsersHasDataObject daemonUserObject);

	public DaemonResponse<List<Object>> getObjectsByAttributes(Long id, Long resourceType);
}
