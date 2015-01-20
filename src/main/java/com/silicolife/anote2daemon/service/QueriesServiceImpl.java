package com.silicolife.anote2daemon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.silicolife.anote2daemon.model.dao.core.GenericDao;
import com.silicolife.anote2daemon.model.pojo.Queries;
import com.silicolife.anote2daemon.service.core.QueriesService;
import com.silicolife.anote2daemon.utils.DaemonResponse;

@EnableTransactionManagement
@Service
@Transactional(readOnly = true)
public class QueriesServiceImpl implements QueriesService {
	@Autowired
	private GenericDao<Queries> genericDao;

	private final static Class<Queries> className = Queries.class;

	@Override
	public DaemonResponse getById(Long id) {
		Queries query = genericDao.findById(className, id);
		DaemonResponse response = new DaemonResponse(query, null);
		return response;
	}

	@Override
	public DaemonResponse update() {
		return null;

	}

	@Override
	public DaemonResponse create() {
		return null;

	}
}
