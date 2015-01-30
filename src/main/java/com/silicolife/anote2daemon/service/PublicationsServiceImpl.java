package com.silicolife.anote2daemon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.silicolife.anote2daemon.model.dao.core.PublicationsDao;
import com.silicolife.anote2daemon.model.pojo.Publications;
import com.silicolife.anote2daemon.service.core.PublicationsService;

@Service
@Transactional(readOnly = true)
public class PublicationsServiceImpl implements PublicationsService {

	@Autowired
	private PublicationsDao publicationsDao;

	private final static Class<Publications> className = Publications.class;

	@Override
	public Publications getById(Long id) {
		Publications publication = publicationsDao.findById(className, id);
		return publication;
	}

	@Transactional(readOnly = false)
	@Override
	public Publications create(Publications publication) {
		publicationsDao.save(publication);
		return publication;
	}

	@Transactional(readOnly = false)
	@Override
	public Publications update(Publications publication) {
		publicationsDao.update(publication);
		return publication;
	}
}
