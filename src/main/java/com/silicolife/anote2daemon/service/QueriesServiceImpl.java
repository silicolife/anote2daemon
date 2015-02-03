package com.silicolife.anote2daemon.service;

import java.util.ArrayList;
import java.util.Date;
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
import com.silicolife.anote2daemon.model.RelevanceType;
import com.silicolife.anote2daemon.model.dao.core.daemon.DaemonDataObjectDao;
import com.silicolife.anote2daemon.model.dao.core.daemon.DaemonLogDao;
import com.silicolife.anote2daemon.model.dao.core.daemon.DaemonTypeResourcesDao;
import com.silicolife.anote2daemon.model.dao.core.daemon.DaemonUserHasDataObjectDao;
import com.silicolife.anote2daemon.model.dao.core.publications.PublicationsDao;
import com.silicolife.anote2daemon.model.dao.core.queries.QueriesDao;
import com.silicolife.anote2daemon.model.dao.core.queries.QueriesHasPublicationsDao;
import com.silicolife.anote2daemon.model.pojo.DaemonDataObject;
import com.silicolife.anote2daemon.model.pojo.DaemonDataObjectId;
import com.silicolife.anote2daemon.model.pojo.DaemonLog;
import com.silicolife.anote2daemon.model.pojo.DaemonTypeResources;
import com.silicolife.anote2daemon.model.pojo.DaemonUsers;
import com.silicolife.anote2daemon.model.pojo.DaemonUsersHasDataObject;
import com.silicolife.anote2daemon.model.pojo.DaemonUsersHasDataObjectId;
import com.silicolife.anote2daemon.model.pojo.Publications;
import com.silicolife.anote2daemon.model.pojo.Queries;
import com.silicolife.anote2daemon.model.pojo.QueriesHasPublications;
import com.silicolife.anote2daemon.model.pojo.QueriesHasPublicationsId;
import com.silicolife.anote2daemon.service.auxiliar.DaemonUserLoggedAuxiliar;
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
	private QueriesHasPublicationsDao queriesHasPublicationsDao;
	@Autowired
	private DaemonUserHasDataObjectDao daemonUsersHasDataObjectDao;
	@Autowired
	private DaemonTypeResourcesDao daemonTypeResourcesDao;
	@Autowired
	private DaemonLogDao daemonLogDao;
	@Autowired
	private DaemonDataObjectDao daemonDataObjectDao;

	@Override
	public Queries getById(Long id) {
		DaemonUsers user = DaemonUserLoggedAuxiliar.getUserLogged();
		Queries query = queriesDao.findById(QueriesDao.className, id);
		if (query == null)
			throw new DaemonException(ExceptionsCodes.codeNoQuery, ExceptionsCodes.msgNoQuery);

		Hibernate.initialize(query.getQueriesType());
		DaemonTypeResources resource = getDaemonResourceType();
		DaemonUsersHasDataObjectId idDataObject = new DaemonUsersHasDataObjectId(user.getId(), id, resource.getId());
		DaemonUsersHasDataObject dataObject = daemonUsersHasDataObjectDao.findById(DaemonUserHasDataObjectDao.className, idDataObject);
		if (dataObject == null)
			throw new DaemonException(ExceptionsCodes.codeQueryAccessDenied, ExceptionsCodes.msgQueryAccessDenied);

		return query;
	}

	@Override
	public List<Publications> getAllPublications(Long id) {
		Queries query = queriesDao.findById(QueriesDao.className, id);
		if (query == null)
			throw new DaemonException(ExceptionsCodes.codeNoQuery, ExceptionsCodes.msgNoQuery);

		DaemonUsers user = DaemonUserLoggedAuxiliar.getUserLogged();
		DaemonTypeResources resource = getDaemonResourceType();
		DaemonUsersHasDataObjectId idDataObject = new DaemonUsersHasDataObjectId(user.getId(), query.getId(), resource.getId());
		DaemonUsersHasDataObject dataObject = daemonUsersHasDataObjectDao.findById(DaemonUserHasDataObjectDao.className, idDataObject);
		if (dataObject == null)
			throw new DaemonException(ExceptionsCodes.codeQueryAccessDenied, ExceptionsCodes.msgQueryAccessDenied);

		// -- initialize lazy objects
		Hibernate.initialize(query.getQueriesHasPublicationses());
		Set<QueriesHasPublications> publicationsQueries = query.getQueriesHasPublicationses();
		List<Publications> listPublications = new ArrayList<Publications>();

		for (QueriesHasPublications publicationQuery : publicationsQueries) {
			Publications publication = publicationsDao.findFewColumnsById(publicationQuery.getId().getPublicationsId());
			listPublications.add(publication);
		}

		return listPublications;
	}

	@Override
	public List<Queries> getAll() {

		DaemonUsers user = DaemonUserLoggedAuxiliar.getUserLogged();
		DaemonTypeResources resource = getDaemonResourceType();
		/**
		 * get all queries from a user
		 */
		List<Object[]> listObject = daemonUsersHasDataObjectDao.findQueriesByAttributes(user.getId(), resource.getId());
		if (listObject == null)
			return null;

		List<Queries> listQueries = new ArrayList<Queries>();
		for (Object[] object : listObject) {
			Queries query = (Queries) object[1];
			listQueries.add(query);
		}

		return listQueries;
	}

	@Transactional(readOnly = false)
	@Override
	public Boolean create(Queries query) {

		DaemonUsers user = DaemonUserLoggedAuxiliar.getUserLogged();
		DaemonTypeResources resource = getDaemonResourceType();

		queriesDao.save(query);
		DaemonDataObjectId dataObjectId = new DaemonDataObjectId(query.getId(), resource.getId());
		DaemonDataObject dataObject = new DaemonDataObject(dataObjectId, resource);
		daemonDataObjectDao.save(dataObject);
		DaemonUsersHasDataObjectId dataObjectUserId = new DaemonUsersHasDataObjectId(user.getId(), query.getId(), resource.getId());
		DaemonUsersHasDataObject dataObjectUser = new DaemonUsersHasDataObject(dataObjectUserId, dataObject, user, "owner");
		daemonUsersHasDataObjectDao.save(dataObjectUser);

		DaemonLog log = new DaemonLog(user, new Date(), "create", "queries/daemon_users_has_data_object/daemon_data_object", null, "create a new query");
		daemonLogDao.save(log);

		return true;
	}

	@Transactional(readOnly = false)
	@Override
	public Boolean update(Queries query) {

		Queries queryObj = queriesDao.findById(QueriesDao.className, query.getId());
		if (queryObj == null)
			throw new DaemonException(ExceptionsCodes.codeNoQuery, ExceptionsCodes.msgNoQuery);

		DaemonUsers user = DaemonUserLoggedAuxiliar.getUserLogged();
		DaemonTypeResources resource = getDaemonResourceType();
		DaemonUsersHasDataObjectId dataObjectUsersId = new DaemonUsersHasDataObjectId(user.getId(), query.getId(), resource.getId());
		DaemonUsersHasDataObject dataObjectUsers = daemonUsersHasDataObjectDao.findById(DaemonUserHasDataObjectDao.className, dataObjectUsersId);

		if (dataObjectUsers == null || dataObjectUsers.getAccesLevel().equals("read"))
			throw new DaemonException(ExceptionsCodes.codeQueryAccessDenied, ExceptionsCodes.msgQueryAccessDenied);

		queriesDao.save(query);

		DaemonLog log = new DaemonLog(user, new Date(), "update", "queries", null, "update query");
		daemonLogDao.save(log);

		return true;

	}

	@Transactional(readOnly = false)
	@Override
	public boolean addPublicationsToQuery(Long id, List<Long> publicationsIds) {

		DaemonUsers user = DaemonUserLoggedAuxiliar.getUserLogged();

		Queries query = queriesDao.findById(QueriesDao.className, id);
		if (query == null)
			throw new DaemonException(ExceptionsCodes.codeNoQuery, ExceptionsCodes.msgNoQuery);

		for (Long publicationId : publicationsIds) {
			Publications publication = publicationsDao.findFewColumnsById(publicationId);
			if (publication == null)
				throw new DaemonException(ExceptionsCodes.codeNoPublication, ExceptionsCodes.msgNoPublication);

			QueriesHasPublicationsId queryHasPubId = new QueriesHasPublicationsId(id, publicationId);
			QueriesHasPublications queryHasPub = new QueriesHasPublications(queryHasPubId, query, publication);
			queriesHasPublicationsDao.save(queryHasPub);
		}

		DaemonLog log = new DaemonLog(user, new Date(), "create", "queriesHasPublications", null, "associate publications to a query");
		daemonLogDao.save(log);

		return true;
	}

	@Transactional(readOnly = false)
	@Override
	public Boolean updateRelevance(Long queryId, Long publicationId, String relevance) {
		Queries query = queriesDao.findById(QueriesDao.className, queryId);
		if (query == null)
			throw new DaemonException(ExceptionsCodes.codeNoQuery, ExceptionsCodes.msgNoQuery);

		Publications publication = publicationsDao.findFewColumnsById(publicationId);
		if (publication == null)
			throw new DaemonException(ExceptionsCodes.codeNoPublication, ExceptionsCodes.msgNoPublication);
		/**
		 * if exists query and publication
		 */
		DaemonUsers user = DaemonUserLoggedAuxiliar.getUserLogged();
		DaemonTypeResources resource = getDaemonResourceType();
		DaemonUsersHasDataObjectId dataObjectUsersId = new DaemonUsersHasDataObjectId(user.getId(), queryId, resource.getId());
		DaemonUsersHasDataObject dataObjectUsers = daemonUsersHasDataObjectDao.findById(DaemonUserHasDataObjectDao.className, dataObjectUsersId);
		if (dataObjectUsers == null || dataObjectUsers.getAccesLevel().equals("read"))
			throw new DaemonException(ExceptionsCodes.codeQueryPublication, ExceptionsCodes.msgQueryPublication);
		/**
		 * if has access
		 */
		QueriesHasPublicationsId queriesPubId = new QueriesHasPublicationsId(queryId, publicationId);
		QueriesHasPublications queriesPublications = queriesHasPublicationsDao.findById(QueriesHasPublicationsDao.className, queriesPubId);
		if (queriesPublications == null)
			throw new DaemonException(ExceptionsCodes.codeQueryAccessDenied, ExceptionsCodes.msgQueryAccessDenied);

		queriesPublications.setRelevanceEnum(relevance);
		queriesHasPublicationsDao.update(queriesPublications);

		DaemonLog log = new DaemonLog(user, new Date(), "update", "queries_has_publications", null, "update relevance from a publication");
		daemonLogDao.save(log);

		return true;
	}

	@Override
	public Map<Long, RelevanceType> getQueryPublicationsRelevance(Long queryId) {

		Queries query = queriesDao.findById(QueriesDao.className, queryId);
		if (query == null)
			throw new DaemonException(ExceptionsCodes.codeNoQuery, ExceptionsCodes.msgNoQuery);

		DaemonUsers user = DaemonUserLoggedAuxiliar.getUserLogged();
		DaemonTypeResources resource = getDaemonResourceType();
		DaemonUsersHasDataObjectId dataObjectUsersId = new DaemonUsersHasDataObjectId(user.getId(), queryId, resource.getId());
		DaemonUsersHasDataObject dataObjectUsers = daemonUsersHasDataObjectDao.findById(DaemonUserHasDataObjectDao.className, dataObjectUsersId);
		if (dataObjectUsers == null)
			throw new DaemonException(ExceptionsCodes.codeQueryAccessDenied, ExceptionsCodes.msgQueryAccessDenied);
		/**
		 * if exists
		 */
		List<Object[]> listPubRelevance = queriesHasPublicationsDao.findPublicationsRelevanceFromQuery(queryId);
		Map<Long, RelevanceType> map = null;
		if (listPubRelevance != null) {
			map = new HashMap<Long, RelevanceType>();
			for (Object[] record : listPubRelevance) {
				Long pubId = (Long) record[0];
				map.put(pubId, RelevanceType.convertString((String) record[1]));
			}
		}

		return map;
	}

	/**
	 * Private method to return DaemonResourceType object
	 * 
	 * @return
	 */
	private DaemonTypeResources getDaemonResourceType() {
		DaemonTypeResources resource = daemonTypeResourcesDao.findUniqueByAttribute(DaemonTypeResourcesDao.className, "description", ResourcesType.queries);
		if (resource == null) {
			throw new DaemonException(ExceptionsCodes.codeNoResourceType, ExceptionsCodes.msgNoResourceType);
		}
		return resource;
	}
}
