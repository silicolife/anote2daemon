package com.silicolife.anote2daemon.service.core;

import com.silicolife.anote2daemon.model.pojo.QueriesType;

public interface QueriesTypeService {

	public QueriesType getById(Long id);

	public QueriesType create(QueriesType queryType);
	
	public QueriesType update(QueriesType queryType);
}
