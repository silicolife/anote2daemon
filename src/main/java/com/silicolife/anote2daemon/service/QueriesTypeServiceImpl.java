package com.silicolife.anote2daemon.service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.silicolife.anote2daemon.model.dao.core.QueriesTypeDao;
import com.silicolife.anote2daemon.model.pojo.QueriesType;
import com.silicolife.anote2daemon.service.core.QueriesTypeService;
import com.silicolife.anote2daemon.webservice.DaemonResponse;

@Transactional(readOnly = true)
@Service
public class QueriesTypeServiceImpl implements QueriesTypeService {

	@Autowired
	private QueriesTypeDao queriesTypeDao;
	private final static Class<QueriesType> className = QueriesType.class;

	@Transactional(readOnly = false)
	@Override
	public DaemonResponse<QueriesType> create(QueriesType queryType) {
		queriesTypeDao.save(queryType);
		return new DaemonResponse<QueriesType>(queryType, null);
	}

	@Override
	public DaemonResponse<QueriesType> getById(Long id) {
		QueriesType queryType = queriesTypeDao.findById(className, id);
		return new DaemonResponse<QueriesType>(queryType);
	}

	@Override
	public DaemonResponse<List<QueriesType>> getByDescription(String name) {
		Map<String, Serializable> map = new HashMap<String, Serializable>();
		map.put("description", name);
		List<QueriesType> queryTypeList = queriesTypeDao.findByAttributes(className, map);
		if (queryTypeList.size() > 0)
			queryTypeList = null;
		return new DaemonResponse<List<QueriesType>>(queryTypeList);
	}
}
