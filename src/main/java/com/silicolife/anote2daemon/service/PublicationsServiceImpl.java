package com.silicolife.anote2daemon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.silicolife.anote2daemon.model.dao.core.PublicationsDao;
import com.silicolife.anote2daemon.model.pojo.Publications;
import com.silicolife.anote2daemon.service.core.PublicationsService;
import com.silicolife.anote2daemon.webservice.DaemonResponse;

@EnableTransactionManagement
@Service
@Transactional(readOnly = true)
public class PublicationsServiceImpl implements PublicationsService {

	@Autowired
	private PublicationsDao publicationsDao;

	private final static Class<Publications> className = Publications.class;

	@Override
	public DaemonResponse<Publications> getById(Long id) {
		Publications publication = publicationsDao.findById(className, id);
		DaemonResponse<Publications> response = new DaemonResponse<Publications>(publication, null);
		return response;
	}
}
