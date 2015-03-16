package com.silicolife.anote2daemon.model.core.dao.specialdao;

import java.util.List;

import com.silicolife.anote2daemon.model.core.entities.ClustersProcesses;

public interface ClustersAuxDao {

	public List<ClustersProcesses> findClustersByQueryId(Long queryId);
}
