package com.silicolife.anote2daemon.service.publications;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.silicolife.anote2daemon.model.dao.core.publications.PublicationsDao;
import com.silicolife.anote2daemon.model.pojo.Publications;
import com.silicolife.anote2daemon.service.core.publications.PublicationsService;

@Service
@Transactional(readOnly = true)
public class PublicationsServiceImpl implements PublicationsService {

	@Autowired
	private PublicationsDao publicationsDao;

	@Override
	public Publications getById(Long id) {
		Publications publication = publicationsDao.findById(PublicationsDao.className, id);
		return publication;
	}

	@Transactional(readOnly = false)
	@Override
	public Publications create(Publications publication) {
		return null;
	}

	@Transactional(readOnly = false)
	@Override
	public Publications update(Publications publication) {
		return null;
	}

	@Override
	public Publications getFewColumnsById(Long id) {
		Publications publication = publicationsDao.findFewColumnsById(id);
		return publication;
	}
}
