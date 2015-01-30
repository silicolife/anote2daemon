package com.silicolife.anote2daemon.service.core;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.silicolife.anote2daemon.model.pojo.DaemonUsersHasDataObject;
import com.silicolife.anote2daemon.model.pojo.DaemonUsersHasDataObjectId;

public interface DaemonUserHasDataObjectService {

	public DaemonUsersHasDataObject getById(DaemonUsersHasDataObjectId id);

	public List<DaemonUsersHasDataObject> getByAttributes(Map<String, Serializable> eqRestrictions);

	public DaemonUsersHasDataObject create(DaemonUsersHasDataObject daemonUserObject);

	public DaemonUsersHasDataObject update(DaemonUsersHasDataObject daemonUserObject);

}
