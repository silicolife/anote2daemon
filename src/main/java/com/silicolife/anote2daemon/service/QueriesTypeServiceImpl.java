package com.silicolife.anote2daemon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.silicolife.anote2daemon.model.dao.core.QueriesTypeDao;
import com.silicolife.anote2daemon.model.pojo.QueriesType;
import com.silicolife.anote2daemon.service.core.QueriesTypeService;

@Transactional(readOnly = true)
@Service
public class QueriesTypeServiceImpl implements QueriesTypeService {

	@Autowired
	private QueriesTypeDao queriesTypeDao;
	
	private final static Class<QueriesType> className = QueriesType.class;

	@Transactional(readOnly = false)
	@Override
	public QueriesType create(QueriesType queryType) {
		queriesTypeDao.save(queryType);
		return queryType;
	}

	@Override
	public QueriesType getById(Long id) {
		QueriesType queryType = queriesTypeDao.findById(className, id);
		return queryType;
	}

	@Transactional(readOnly = false)
	@Override
	public QueriesType update(QueriesType queryType) {
		queriesTypeDao.update(queryType);
		return queryType;
	}

}
