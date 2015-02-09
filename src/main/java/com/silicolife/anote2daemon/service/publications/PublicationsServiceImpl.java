package com.silicolife.anote2daemon.service.publications;

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
import com.silicolife.anote2daemon.model.core.dao.manager.PublicationsManagerDao;
import com.silicolife.anote2daemon.model.core.entities.Publications;
import com.silicolife.anote2daemon.model.core.entities.PublicationsHasPublicationsSource;
import com.silicolife.anote2daemon.model.core.entities.PublicationsSource;

@Service
@Transactional(readOnly = true)
public class PublicationsServiceImpl implements PublicationsService {

	private PublicationsManagerDao publicationsManagerDao;

	@Autowired
	public PublicationsServiceImpl(PublicationsManagerDao publicationsManagerDao) {
		super();
		this.publicationsManagerDao = publicationsManagerDao;
	}

	@Override
	public Publications getById(Long id) {
		Publications publication = publicationsManagerDao.getPublicationsAuxDao().findPublicationsById(id);
		return publication;
	}

	@Transactional(readOnly = false)
	@Override
	public Boolean update(Publications publication) {
		Publications pub = publicationsManagerDao.getPublicationsAuxDao().findPublicationsById(publication.getId());
		if (pub == null)
			throw new DaemonException(ExceptionsCodes.codeNoPublication, ExceptionsCodes.msgNoPublication);

		publicationsManagerDao.getPublicationsDao().update(publication);
		return true;
	}

	@Override
	public Boolean create(List<Publications> publications) {
		for (Publications publication : publications) {
			publicationsManagerDao.getPublicationsDao().save(publication);
		}
		return true;
	}

	@Override
	public Map<String, Long> getAllPublicationsFromSource(String source) {
		PublicationsSource publicationSource = publicationsManagerDao.getPublicationsSourceDao().findUniqueByAttribute("description", source);
		if (publicationSource == null)
			throw new DaemonException(ExceptionsCodes.codePublicationSource, ExceptionsCodes.msgPublicationSource);

		Map<String, Long> response = new HashMap<String, Long>();
		Hibernate.initialize(publicationSource.getPublicationsHasPublicationsSources());
		Set<PublicationsHasPublicationsSource> pubsSources = publicationSource.getPublicationsHasPublicationsSources();
		for (PublicationsHasPublicationsSource pubSource : pubsSources) {
			String pubSourceId = pubSource.getId().getPublicationsSourceInternalId();
			Long pubId = pubSource.getPublications().getId();
			response.put(pubSourceId, pubId);
		}

		if (response.size() == 0)
			return null;

		return response;
	}
}
