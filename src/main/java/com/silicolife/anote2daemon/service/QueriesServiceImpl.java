package com.silicolife.anote2daemon.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.silicolife.anote2daemon.exceptions.DaemonQueriesException;
import com.silicolife.anote2daemon.exceptions.ExceptionsCodes;
import com.silicolife.anote2daemon.model.dao.core.DaemonTypeResourcesDao;
import com.silicolife.anote2daemon.model.dao.core.DaemonUserHasDataObjectDao;
import com.silicolife.anote2daemon.model.dao.core.PublicationsDao;
import com.silicolife.anote2daemon.model.dao.core.QueriesDao;
import com.silicolife.anote2daemon.model.pojo.DaemonTypeResources;
import com.silicolife.anote2daemon.model.pojo.DaemonUsers;
import com.silicolife.anote2daemon.model.pojo.DaemonUsersHasDataObject;
import com.silicolife.anote2daemon.model.pojo.DaemonUsersHasDataObjectId;
import com.silicolife.anote2daemon.model.pojo.Publications;
import com.silicolife.anote2daemon.model.pojo.Queries;
import com.silicolife.anote2daemon.model.pojo.QueriesHasPublications;
import com.silicolife.anote2daemon.service.auxiliar.UserLogged;
import com.silicolife.anote2daemon.service.core.QueriesService;
import com.silicolife.anote2daemon.webservice.ResourcesType;

/**
 * Service layer which implements all operations about Queries
 * 
 * @author Joel Azevedo Costa
 * @year 2015
 *
 */
@Service
@Transactional(readOnly = true)
public class QueriesServiceImpl implements QueriesService {

	@Autowired
	private QueriesDao queriesDao;
	@Autowired
	private PublicationsDao publicationsDao;
	@Autowired
	private DaemonUserHasDataObjectDao daemonUsersHasDataObjectDao;
	@Autowired
	private DaemonTypeResourcesDao daemonTypeResourcesDao;

	@Override
	public Map<String, Queries> getById(Long id) {
		DaemonUsers user = UserLogged.getUserLogged();

		Queries query = queriesDao.findById(QueriesDao.className, id);
		if (query == null)
			throw new DaemonQueriesException(ExceptionsCodes.codeNoQuery, ExceptionsCodes.msgNoQuery);

		DaemonTypeResources resource = getDaemonResourceType();
		DaemonUsersHasDataObjectId idDataObject = new DaemonUsersHasDataObjectId(user.getId(), query.getId(), resource.getId());
		DaemonUsersHasDataObject dataObject = daemonUsersHasDataObjectDao.findById(DaemonUserHasDataObjectDao.className, idDataObject);
		if (dataObject == null)
			throw new DaemonQueriesException(ExceptionsCodes.codeQueryAccessDenied, ExceptionsCodes.msgQueryAccessDenied);

		Map<String, Queries> response = new HashMap<String, Queries>();
		response.put(dataObject.getAccesLevel(), query);

		return response;
	}

	@Override
	public Map<String, Set<Publications>> getAllPublications(Long id) {
		DaemonUsers user = UserLogged.getUserLogged();

		Queries query = queriesDao.findById(QueriesDao.className, id);
		if (query == null)
			throw new DaemonQueriesException(ExceptionsCodes.codeNoQuery, ExceptionsCodes.msgNoQuery);

		DaemonTypeResources resource = getDaemonResourceType();
		DaemonUsersHasDataObjectId idDataObject = new DaemonUsersHasDataObjectId(user.getId(), query.getId(), resource.getId());
		DaemonUsersHasDataObject dataObject = daemonUsersHasDataObjectDao.findById(DaemonUserHasDataObjectDao.className, idDataObject);
		if (dataObject == null)
			throw new DaemonQueriesException(ExceptionsCodes.codeQueryAccessDenied, ExceptionsCodes.msgQueryAccessDenied);

		// -- initialize lazy objects
		Hibernate.initialize(query.getQueriesHasPublicationses());
		Set<QueriesHasPublications> publicationsQueries = query.getQueriesHasPublicationses();
		Set<Publications> listPublications = new HashSet<Publications>();

		for (QueriesHasPublications publicationQuery : publicationsQueries) {
			Publications publication = publicationsDao.findFewColumnsById(publicationQuery.getId().getPublicationsId());
			listPublications.add(publication);
		}

		Map<String, Set<Publications>> response = new HashMap<String, Set<Publications>>();
		response.put(dataObject.getAccesLevel(), listPublications);

		return response;
	}

	@Override
	public Map<String, Set<Queries>> getAll() {

		DaemonUsers user = UserLogged.getUserLogged();
		DaemonTypeResources resource = getDaemonResourceType();
		/**
		 * get all queries from a user
		 */
		List<Object[]> listObject = daemonUsersHasDataObjectDao.findQueriesByAttributes(user.getId(), resource.getId());
		if (listObject == null)
			return null;

		Map<String, Set<Queries>> map = new HashMap<String, Set<Queries>>();
		for (Object[] object : listObject) {
			DaemonUsersHasDataObject dataObject = (DaemonUsersHasDataObject) object[0];
			Queries query = (Queries) object[1];
			String previlege = dataObject.getAccesLevel();
			Set<Queries> listQueries = map.get(previlege);
			if (listQueries == null)
				listQueries = new HashSet<Queries>();
			listQueries.add(query);
			map.put(previlege, listQueries);
		}

		return map;
	}

	@Override
	public Queries create(Queries query) {
		return null;
	}

	@Override
	public Queries update(Queries query) {
		return null;
	}

	/**
	 * Private method to return DaemonResourceType object
	 * 
	 * @return
	 */
	private DaemonTypeResources getDaemonResourceType() {
		DaemonTypeResources resource = daemonTypeResourcesDao.findUniqueByAttribute(DaemonTypeResourcesDao.className, "description", ResourcesType.queries);
		if (resource == null) {
			throw new DaemonQueriesException(ExceptionsCodes.codeNoResourceType, ExceptionsCodes.msgNoResourceType);
		}
		return resource;
	}
}
