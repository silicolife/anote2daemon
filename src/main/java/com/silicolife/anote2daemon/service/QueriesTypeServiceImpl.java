package com.silicolife.anote2daemon.service;

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
		return new DaemonResponse<QueriesType>(queryType);
	}

	@Override
	public DaemonResponse<QueriesType> getById(Long id) {
		QueriesType queryType = queriesTypeDao.findById(className, id);
		return new DaemonResponse<QueriesType>(queryType);
	}

	@Transactional(readOnly = false)
	@Override
	public DaemonResponse<QueriesType> update(QueriesType queryType) {
		queriesTypeDao.update(queryType);
		return new DaemonResponse<QueriesType>(queryType);
	}

}
