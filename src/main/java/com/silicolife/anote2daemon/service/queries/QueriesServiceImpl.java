package com.silicolife.anote2daemon.service.queries;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.silicolife.anote2daemon.exceptions.DaemonException;
import com.silicolife.anote2daemon.exceptions.ExceptionsCodes;
import com.silicolife.anote2daemon.model.core.RelevanceType;
import com.silicolife.anote2daemon.model.core.dao.UsersLogged;
import com.silicolife.anote2daemon.model.core.dao.manager.QueriesManagerDao;
import com.silicolife.anote2daemon.model.core.dao.manager.UsersManagerDao;
import com.silicolife.anote2daemon.model.core.entities.Publications;
import com.silicolife.anote2daemon.model.core.entities.Queries;
import com.silicolife.anote2daemon.model.core.entities.QueriesHasPublications;
import com.silicolife.anote2daemon.model.core.entities.Users;
import com.silicolife.anote2daemon.model.core.entities.UsersHasDataObject;
import com.silicolife.anote2daemon.model.core.entities.UsersHasDataObjectId;
import com.silicolife.anote2daemon.model.core.entities.UsersLog;

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

	private QueriesManagerDao queriesManagerDao;
	private UsersManagerDao usersManagerDao;
	@Autowired
	private UsersLogged userLogged;

	private final static String queries = "queries";

	@Autowired
	public QueriesServiceImpl(QueriesManagerDao queriesManagerDao, UsersManagerDao usersManagerDao) {
		this.queriesManagerDao = queriesManagerDao;
		this.usersManagerDao = usersManagerDao;
	}

	
	@Override
	public Queries getById(Long id) {
		Users user = userLogged.getCurrentUserLogged();
		Queries query = queriesManagerDao.getQueriesDao().findById(id);
		if (query == null)
			throw new DaemonException(ExceptionsCodes.codeNoQuery, ExceptionsCodes.msgNoQuery);

		Hibernate.initialize(query.getQueriesType());
		UsersHasDataObjectId idDataObject = new UsersHasDataObjectId(user.getId(), id, queries);
		UsersHasDataObject dataObject = usersManagerDao.getUsersHasdataObjectDao().findById(idDataObject);
		if (dataObject == null)
			throw new DaemonException(ExceptionsCodes.codeQueryAccessDenied, ExceptionsCodes.msgQueryAccessDenied);

		return query;
	}

	@Override
	public List<Publications> getQueryPublications(Long id) {
		Queries query = queriesManagerDao.getQueriesDao().findById(id);
		if (query == null)
			throw new DaemonException(ExceptionsCodes.codeNoQuery, ExceptionsCodes.msgNoQuery);

		Users user = userLogged.getCurrentUserLogged();
		UsersHasDataObjectId idDataObject = new UsersHasDataObjectId(user.getId(), query.getId(), queries);
		UsersHasDataObject dataObject = usersManagerDao.getUsersHasdataObjectDao().findById(idDataObject);
		if (dataObject == null)
			throw new DaemonException(ExceptionsCodes.codeQueryAccessDenied, ExceptionsCodes.msgQueryAccessDenied);

		// -- initialize lazy objects
		Hibernate.initialize(query.getQueriesHasPublicationses());
		Set<QueriesHasPublications> publicationsQueries = query.getQueriesHasPublicationses();
		List<Publications> listPublications = new ArrayList<Publications>();
		for (QueriesHasPublications publicationQuery : publicationsQueries) {
			Publications publication = queriesManagerDao.getPublicationsAuxDao().findPublicationsById(publicationQuery.getId().getPublicationsId());
			listPublications.add(publication);
		}

		return listPublications;
	}

	@Override
	public List<Queries> getAll() {

		Users user = userLogged.getCurrentUserLogged();
		/**
		 * get all queries from a user
		 */
		List<Object[]> listObject = queriesManagerDao.getQueriesAuxDao().findQueriesByAttributes(user.getId(), queries);
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

		Users user = userLogged.getCurrentUserLogged();
		queriesManagerDao.getQueriesDao().save(query);
		UsersHasDataObjectId dataObjectUserId = new UsersHasDataObjectId(user.getId(), query.getId(), queries);
		UsersHasDataObject dataObjectUser = new UsersHasDataObject(dataObjectUserId, user, "owner");
		usersManagerDao.getUsersHasdataObjectDao().save(dataObjectUser);

		UsersLog log = new UsersLog(user, new Date(), "create", "queries/daemon_users_has_data_object", null, "create a new query");
		usersManagerDao.getUsersLog().save(log);

		return true;
	}

	@Transactional(readOnly = false)
	@Override
	public Boolean update(Queries query) {

		/*
		 * Queries queryObj = queriesDao.findById(QueriesDao.className,
		 * query.getId()); if (queryObj == null) throw new
		 * DaemonException(ExceptionsCodes.codeNoQuery,
		 * ExceptionsCodes.msgNoQuery);
		 * 
		 * DaemonUsers user = DaemonUserLoggedAuxiliar.getUserLogged();
		 * DaemonTypeResources resource = getDaemonResourceType();
		 * DaemonUsersHasDataObjectId dataObjectUsersId = new
		 * DaemonUsersHasDataObjectId(user.getId(), query.getId(),
		 * resource.getId()); DaemonUsersHasDataObject dataObjectUsers =
		 * daemonUsersHasDataObjectDao.findById(UsersHasDataObjectDao.className,
		 * dataObjectUsersId);
		 * 
		 * if (dataObjectUsers == null ||
		 * dataObjectUsers.getAccesLevel().equals("read")) throw new
		 * DaemonException(ExceptionsCodes.codeQueryAccessDenied,
		 * ExceptionsCodes.msgQueryAccessDenied);
		 * 
		 * queriesDao.save(query);
		 * 
		 * DaemonLog log = new DaemonLog(user, new Date(), "update", "queries",
		 * null, "update query"); daemonLogDao.save(log);
		 */

		return true;

	}

	@Transactional(readOnly = false)
	@Override
	public boolean addPublicationsToQuery(Long id, List<Long> publicationsIds) {

		/*
		 * DaemonUsers user = DaemonUserLoggedAuxiliar.getUserLogged();
		 * 
		 * Queries query = queriesDao.findById(QueriesDao.className, id); if
		 * (query == null) throw new
		 * DaemonException(ExceptionsCodes.codeNoQuery,
		 * ExceptionsCodes.msgNoQuery);
		 * 
		 * for (Long publicationId : publicationsIds) { Publications publication
		 * = publicationsDao.findFewColumnsById(publicationId); if (publication
		 * == null) throw new DaemonException(ExceptionsCodes.codeNoPublication,
		 * ExceptionsCodes.msgNoPublication);
		 * 
		 * QueriesHasPublicationsId queryHasPubId = new
		 * QueriesHasPublicationsId(id, publicationId); QueriesHasPublications
		 * queryHasPub = new QueriesHasPublications(queryHasPubId, query,
		 * publication); queriesHasPublicationsDao.save(queryHasPub); }
		 * 
		 * DaemonLog log = new DaemonLog(user, new Date(), "create",
		 * "queriesHasPublications", null, "associate publications to a query");
		 * daemonLogDao.save(log);
		 */

		return true;
	}

	@Transactional(readOnly = false)
	@Override
	public Boolean updateRelevance(Long queryId, Long publicationId, String relevance) {
		/*
		 * Queries query = queriesDao.findById(QueriesDao.className, queryId);
		 * if (query == null) throw new
		 * DaemonException(ExceptionsCodes.codeNoQuery,
		 * ExceptionsCodes.msgNoQuery);
		 * 
		 * Publications publication =
		 * publicationsDao.findFewColumnsById(publicationId); if (publication ==
		 * null) throw new DaemonException(ExceptionsCodes.codeNoPublication,
		 * ExceptionsCodes.msgNoPublication);
		 * 
		 * DaemonUsers user = DaemonUserLoggedAuxiliar.getUserLogged();
		 * DaemonTypeResources resource = getDaemonResourceType();
		 * DaemonUsersHasDataObjectId dataObjectUsersId = new
		 * DaemonUsersHasDataObjectId(user.getId(), queryId, resource.getId());
		 * DaemonUsersHasDataObject dataObjectUsers =
		 * daemonUsersHasDataObjectDao.findById(UsersHasDataObjectDao.className,
		 * dataObjectUsersId); if (dataObjectUsers == null ||
		 * dataObjectUsers.getAccesLevel().equals("read")) throw new
		 * DaemonException(ExceptionsCodes.codeQueryPublication,
		 * ExceptionsCodes.msgQueryPublication);
		 * 
		 * QueriesHasPublicationsId queriesPubId = new
		 * QueriesHasPublicationsId(queryId, publicationId);
		 * QueriesHasPublications queriesPublications =
		 * queriesHasPublicationsDao
		 * .findById(QueriesHasPublicationsDao.className, queriesPubId); if
		 * (queriesPublications == null) throw new
		 * DaemonException(ExceptionsCodes.codeQueryAccessDenied,
		 * ExceptionsCodes.msgQueryAccessDenied);
		 * 
		 * queriesPublications.setRelevanceEnum(relevance);
		 * queriesHasPublicationsDao.update(queriesPublications);
		 * 
		 * DaemonLog log = new DaemonLog(user, new Date(), "update",
		 * "queries_has_publications", null,
		 * "update relevance from a publication"); daemonLogDao.save(log);
		 */

		return true;
	}

	@Override
	public Map<Long, RelevanceType> getQueryPublicationsRelevance(Long queryId) {

		/*
		 * Queries query = queriesDao.findById(QueriesDao.className, queryId);
		 * if (query == null) throw new
		 * DaemonException(ExceptionsCodes.codeNoQuery,
		 * ExceptionsCodes.msgNoQuery);
		 * 
		 * DaemonUsers user = DaemonUserLoggedAuxiliar.getUserLogged();
		 * DaemonTypeResources resource = getDaemonResourceType();
		 * DaemonUsersHasDataObjectId dataObjectUsersId = new
		 * DaemonUsersHasDataObjectId(user.getId(), queryId, resource.getId());
		 * DaemonUsersHasDataObject dataObjectUsers =
		 * daemonUsersHasDataObjectDao.findById(UsersHasDataObjectDao.className,
		 * dataObjectUsersId); if (dataObjectUsers == null) throw new
		 * DaemonException(ExceptionsCodes.codeQueryAccessDenied,
		 * ExceptionsCodes.msgQueryAccessDenied);
		 * 
		 * List<Object[]> listPubRelevance =
		 * queriesHasPublicationsDao.findPublicationsRelevanceFromQuery
		 * (queryId); Map<Long, RelevanceType> map = null; if (listPubRelevance
		 * != null) { map = new HashMap<Long, RelevanceType>(); for (Object[]
		 * record : listPubRelevance) { Long pubId = (Long) record[0];
		 * map.put(pubId, RelevanceType.convertString((String) record[1])); } }
		 */

		return null;
	}

}
