package com.silicolife.anote2daemon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.silicolife.anote2daemon.model.dao.core.QueriesDao;
import com.silicolife.anote2daemon.model.pojo.Queries;
import com.silicolife.anote2daemon.model.pojo.QueriesType;
import com.silicolife.anote2daemon.service.core.QueriesService;
import com.silicolife.anote2daemon.service.core.QueriesTypeService;
import com.silicolife.anote2daemon.webservice.DaemonResponse;

@Service
@Transactional(readOnly = true)
public class QueriesServiceImpl implements QueriesService {

	@Autowired
	private QueriesDao queriesDao;
	@Autowired
	private QueriesTypeService queriesTypeService;

	private final static Class<Queries> className = Queries.class;

	@Override
	public DaemonResponse<Queries> getById(Long id) {
		Queries query = queriesDao.findById(className, id);
		return new DaemonResponse<Queries>(query);
	}

	@Transactional(readOnly = false)
	@Override
	public DaemonResponse<Queries> create(Queries query) {

		DaemonResponse<QueriesType> queryTypeResponse = queriesTypeService.getById(query.getQueriesType().getId());
		QueriesType queryType = queryTypeResponse.getContent();
		if (queryType == null)
			queriesTypeService.create(query.getQueriesType());

		queriesDao.save(query);
		return new DaemonResponse<Queries>(query);
	}

	@Transactional(readOnly = false)
	@Override
	public DaemonResponse<Queries> update(Queries query) {
		queriesDao.update(query);
		return new DaemonResponse<Queries>(query);
	}

}
