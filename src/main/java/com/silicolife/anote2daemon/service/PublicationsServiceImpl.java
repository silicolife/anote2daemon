package com.silicolife.anote2daemon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.silicolife.anote2daemon.model.dao.core.GenericDao;
import com.silicolife.anote2daemon.model.pojo.Publications;
import com.silicolife.anote2daemon.service.core.PublicationsService;
import com.silicolife.anote2daemon.utils.DaemonResponse;

@EnableTransactionManagement
@Service
@Transactional(readOnly = true)
public class PublicationsServiceImpl implements PublicationsService {

	@Autowired
	private GenericDao<Publications> genericDao;

	private final static Class<Publications> className = Publications.class;

	@Override
	public DaemonResponse getById(Long id) {
		Publications publication = genericDao.findById(className, id);
		DaemonResponse response = new DaemonResponse(publication, null);
		return response;
	}
}
