package com.silicolife.anote2daemon.service.core;

import java.util.List;

import com.silicolife.anote2daemon.model.pojo.QueriesType;
import com.silicolife.anote2daemon.webservice.DaemonResponse;

public interface QueriesTypeService {

	public DaemonResponse<QueriesType> getById(Long id);

	public DaemonResponse<QueriesType> create(QueriesType queryType);

	public DaemonResponse<List<QueriesType>> getByDescription(String name);
}
