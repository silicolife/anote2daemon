package com.silicolife.anote2daemon.service;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.silicolife.anote2daemon.model.dao.core.QueriesHasPublicationsDao;
import com.silicolife.anote2daemon.model.pojo.Publications;
import com.silicolife.anote2daemon.model.pojo.Queries;
import com.silicolife.anote2daemon.model.pojo.QueriesHasPublications;
import com.silicolife.anote2daemon.model.pojo.QueriesHasPublicationsId;
import com.silicolife.anote2daemon.service.core.PublicationsService;
import com.silicolife.anote2daemon.service.core.QueriesHasPublicationsService;
import com.silicolife.anote2daemon.service.core.QueriesService;
import com.silicolife.anote2daemon.webservice.DaemonResponse;

@Transactional(readOnly = true)
@Service
public class QueriesHasPublicationsServiceImpl implements QueriesHasPublicationsService {

	@Autowired
	private QueriesHasPublicationsDao queriesHasPublicationsDao;
	@Autowired
	private PublicationsService publicationService;
	@Autowired
	private QueriesService queriesService;

	private final static Class<QueriesHasPublications> className = QueriesHasPublications.class;

	@Override
	public DaemonResponse<QueriesHasPublications> getById(QueriesHasPublicationsId id) {
		QueriesHasPublications queriesHasPub = queriesHasPublicationsDao.findById(className, id);
		if (queriesHasPub != null) {
			Hibernate.initialize(queriesHasPub.getPublications());
			Hibernate.initialize(queriesHasPub.getQueries());
		}
		return new DaemonResponse<QueriesHasPublications>(queriesHasPub);
	}

	@Override
	public DaemonResponse<QueriesHasPublications> create(QueriesHasPublications queriesHasPub) {

		DaemonResponse<Publications> publicationsResponse = publicationService.getById(queriesHasPub.getPublications().getId());
		DaemonResponse<Queries> queriesResponse = queriesService.getById(queriesHasPub.getQueries().getId());
		Publications publication = publicationsResponse.getContent();
		Queries query = queriesResponse.getContent();

		if (publication != null)
			publicationService.create(publication);
		if (query != null)
			queriesService.create(query);

		queriesHasPublicationsDao.save(queriesHasPub);
		return new DaemonResponse<QueriesHasPublications>(queriesHasPub);

	}

	@Override
	public DaemonResponse<QueriesHasPublications> update(QueriesHasPublications queriesHasPub) {
		// TODO Auto-generated method stub
		return null;
	}

}
