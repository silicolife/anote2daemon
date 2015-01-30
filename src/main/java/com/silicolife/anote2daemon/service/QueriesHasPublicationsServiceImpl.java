package com.silicolife.anote2daemon.service;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.silicolife.anote2daemon.model.dao.core.PublicationsDao;
import com.silicolife.anote2daemon.model.dao.core.QueriesDao;
import com.silicolife.anote2daemon.model.dao.core.QueriesHasPublicationsDao;
import com.silicolife.anote2daemon.model.pojo.Publications;
import com.silicolife.anote2daemon.model.pojo.Queries;
import com.silicolife.anote2daemon.model.pojo.QueriesHasPublications;
import com.silicolife.anote2daemon.model.pojo.QueriesHasPublicationsId;
import com.silicolife.anote2daemon.service.core.QueriesHasPublicationsService;

@Transactional(readOnly = true)
@Service
public class QueriesHasPublicationsServiceImpl implements QueriesHasPublicationsService {

	@Autowired
	private QueriesHasPublicationsDao queriesHasPublicationsDao;
	@Autowired
	private PublicationsDao publicationDao;
	@Autowired
	private QueriesDao queriesDao;

	private final static Class<QueriesHasPublications> className = QueriesHasPublications.class;
	private final static Class<Publications> classNamePublications = Publications.class;
	private final static Class<Queries> classNameQueries = Queries.class;

	@Override
	public QueriesHasPublications getById(QueriesHasPublicationsId id) {
		QueriesHasPublications queriesHasPub = queriesHasPublicationsDao.findById(className, id);
		if (queriesHasPub != null) {
			Hibernate.initialize(queriesHasPub.getPublications());
			Hibernate.initialize(queriesHasPub.getQueries());
		}
		return queriesHasPub;
	}

	@Override
	public QueriesHasPublications getById(Long queriesId, Long publicationsId) {
		QueriesHasPublicationsId id = new QueriesHasPublicationsId(queriesId, publicationsId);
		QueriesHasPublications queriesHasPub = queriesHasPublicationsDao.findById(className, id);
		if (queriesHasPub != null) {
			Hibernate.initialize(queriesHasPub.getPublications());
			Hibernate.initialize(queriesHasPub.getQueries());
		}
		return queriesHasPub;
	}

	@Transactional(readOnly = false)
	@Override
	public QueriesHasPublications create(QueriesHasPublications queriesHasPub) {

		Publications publicationFromUser = queriesHasPub.getPublications();
		Queries queryFromUser = queriesHasPub.getQueries();

		if (publicationFromUser != null) {
			Publications publicationsResponse = publicationDao.findById(classNamePublications, publicationFromUser.getId());
			if (publicationsResponse == null)
				publicationDao.save(publicationFromUser);
		}

		if (queryFromUser != null) {
			Queries queriesResponse = queriesDao.findById(classNameQueries, queryFromUser.getId());
			if (queriesResponse == null)
				queriesDao.save(queryFromUser);
		}

		queriesHasPublicationsDao.save(queriesHasPub);
		return queriesHasPub;

	}

	@Override
	public QueriesHasPublications update(QueriesHasPublications queriesHasPub) {
		// TODO Auto-generated method stub
		return null;
	}

}
