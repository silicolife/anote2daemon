package com.silicolife.anote2daemon.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.silicolife.anote2daemon.exceptions.DaemonException;
import com.silicolife.anote2daemon.exceptions.ExceptionsCodes;
import com.silicolife.anote2daemon.model.dao.core.publications.PublicationsDao;
import com.silicolife.anote2daemon.model.dao.core.publications.PublicationsHasPublicationsSourceDao;
import com.silicolife.anote2daemon.model.dao.core.publications.PublicationsSourceDao;
import com.silicolife.anote2daemon.model.pojo.Publications;
import com.silicolife.anote2daemon.model.pojo.PublicationsHasPublicationsSource;
import com.silicolife.anote2daemon.model.pojo.PublicationsSource;
import com.silicolife.anote2daemon.service.core.PublicationsService;

@Service
@Transactional(readOnly = true)
public class PublicationsServiceImpl implements PublicationsService {

	@Autowired
	private PublicationsDao publicationsDao;
	@Autowired
	private PublicationsSourceDao publicationsSourceDao;
	@Autowired
	private PublicationsHasPublicationsSourceDao publicationsHasPublicationsSourceDao;

	@Override
	public Publications getById(Long id) {
		Publications publication = publicationsDao.findById(PublicationsDao.className, id);
		return publication;
	}

	@Transactional(readOnly = false)
	@Override
	public Boolean create(Publications publication) {
		return null;
	}

	@Transactional(readOnly = false)
	@Override
	public Boolean update(Publications publication) {
		Publications pub = publicationsDao.findFewColumnsById(publication.getId());
		if (pub == null)
			throw new DaemonException(ExceptionsCodes.codeNoPublication, ExceptionsCodes.msgNoPublication);

		publicationsDao.update(publication);
		return true;
	}

	@Override
	public Publications getFewColumnsById(Long id) {
		Publications publication = publicationsDao.findFewColumnsById(id);
		return publication;
	}

	@Override
	public Boolean addPublications(List<Publications> publications) {
		for (Publications publication : publications) {
			publicationsDao.save(publication);
		}
		return true;
	}

	@Override
	public Map<String, Long> getAllPublicationsFromSource(String source) {
		PublicationsSource publicationSource = publicationsSourceDao.findUniqueByAttribute(PublicationsSourceDao.className, "description", source);
		if (publicationSource == null)
			throw new DaemonException(ExceptionsCodes.codePublicationSource, ExceptionsCodes.msgPublicationSource);

		Map<String, Long> response = new HashMap<String, Long>();

		Hibernate.initialize(publicationSource.getPublicationsHasPublicationsSources());
		Set<PublicationsHasPublicationsSource> pubsSources = publicationSource.getPublicationsHasPublicationsSources();
		for (PublicationsHasPublicationsSource pubSource : pubsSources) {
			String pubSourceId = pubSource.getId().getPublicationsSourceInternalId();
			Long pubId = pubSource.getId().getPublicationsId();
			response.put(pubSourceId, pubId);
		}

		if (response.size() == 0)
			return null;

		return response;
	}
}
