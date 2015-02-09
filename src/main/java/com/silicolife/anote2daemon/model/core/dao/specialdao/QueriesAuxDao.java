package com.silicolife.anote2daemon.model.core.dao.specialdao;

import java.util.List;

public interface QueriesAuxDao {

	public List<Object[]> findQueriesByAttributes(Long id, String resourceType);
	
}
