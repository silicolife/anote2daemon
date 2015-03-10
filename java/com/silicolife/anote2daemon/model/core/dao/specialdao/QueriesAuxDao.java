package com.silicolife.anote2daemon.model.core.dao.specialdao;

import java.util.List;

import com.silicolife.anote2daemon.model.core.entities.Queries;

public interface QueriesAuxDao {

	public List<Queries> findQueriesByAttributes(Long id, String resourceType);
	
}
