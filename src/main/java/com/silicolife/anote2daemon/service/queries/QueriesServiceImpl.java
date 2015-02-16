package com.silicolife.anote2daemon.service.queries;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pt.uminho.anote2.core.document.IPublication;
import pt.uminho.anote2.process.IR.IQuery;

import com.silicolife.anote2daemon.exceptions.DaemonException;
import com.silicolife.anote2daemon.exceptions.ExceptionsCodes;
import com.silicolife.anote2daemon.model.core.RelevanceType;
import com.silicolife.anote2daemon.model.core.dao.UsersLogged;
import com.silicolife.anote2daemon.model.core.dao.manager.QueriesManagerDao;
import com.silicolife.anote2daemon.model.core.dao.manager.UsersManagerDao;
import com.silicolife.anote2daemon.model.core.entities.Publications;
import com.silicolife.anote2daemon.model.core.entities.Queries;
import com.silicolife.anote2daemon.model.core.entities.QueriesHasPublications;
import com.silicolife.anote2daemon.model.core.entities.QueriesHasPublicationsId;
import com.silicolife.anote2daemon.model.core.entities.QueriesType;
import com.silicolife.anote2daemon.model.core.entities.Users;
import com.silicolife.anote2daemon.model.core.entities.UsersHasDataObject;
import com.silicolife.anote2daemon.model.core.entities.UsersHasDataObjectId;
import com.silicolife.anote2daemon.model.core.entities.UsersLog;
import com.silicolife.anote2daemon.utils.ResourcesTypeUtils;
import com.silicolife.anote2daemon.wrapper.publications.PublicationsWrapper;
import com.silicolife.anote2daemon.wrapper.queries.QueriesWrapper;

/**
 * Service layer which implement all operations about Queries
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
	private final static String queries = ResourcesTypeUtils.queries.toString();

	@Autowired
	public QueriesServiceImpl(QueriesManagerDao queriesManagerDao, UsersManagerDao usersManagerDao) {
		this.queriesManagerDao = queriesManagerDao;
		this.usersManagerDao = usersManagerDao;
	}

	@Override
	public IQuery getById(Long id) {
		Users user = userLogged.getCurrentUserLogged();
		Queries query = queriesManagerDao.getQueriesDao().findById(id);
		UsersHasDataObjectId idDataObject = new UsersHasDataObjectId(user.getId(), id, queries);
		UsersHasDataObject dataObject = usersManagerDao.getUsersHasdataObjectDao().findById(idDataObject);
		if (dataObject == null)
			throw new DaemonException(ExceptionsCodes.codeQueryAccessDenied, ExceptionsCodes.msgQueryAccessDenied);

		if (query == null)
			return null;

		IQuery query_ = QueriesWrapper.convertToAnoteStructure(query);
		return query_;
	}

	@Override
	public List<IPublication> getQueryPublications(Long id) {
		Queries query = queriesManagerDao.getQueriesDao().findById(id);
		if (query == null)
			throw new DaemonException(ExceptionsCodes.codeNoQuery, ExceptionsCodes.msgNoQuery);

		Users user = userLogged.getCurrentUserLogged();
		UsersHasDataObjectId idDataObject = new UsersHasDataObjectId(user.getId(), query.getId(), queries);
		UsersHasDataObject dataObject = usersManagerDao.getUsersHasdataObjectDao().findById(idDataObject);
		if (dataObject == null)
			throw new DaemonException(ExceptionsCodes.codeQueryAccessDenied, ExceptionsCodes.msgQueryAccessDenied);

		List<Publications> listPublications = queriesManagerDao.getPublicationsAuxDao().findPublicationsByQueryId(id);
		if (listPublications.size() == 0)
			return null;

		List<IPublication> listPublications_ = new ArrayList<IPublication>();
		for (Publications publication : listPublications) {
			IPublication publication_ = PublicationsWrapper.convertToAnoteStructure(publication);
			listPublications_.add(publication_);
		}

		return listPublications_;
	}

	@Override
	public List<IQuery> getAll() {

		Users user = userLogged.getCurrentUserLogged();
		/**
		 * get all queries from a user
		 */
		List<Queries> listQueries = queriesManagerDao.getQueriesAuxDao().findQueriesByAttributes(user.getId(), queries);
		if (listQueries.size() == 0)
			return null;

		List<IQuery> listQueries_ = new ArrayList<IQuery>();
		for (Queries query : listQueries) {
			IQuery query_ = QueriesWrapper.convertToAnoteStructure(query);
			listQueries_.add(query_);
		}

		return listQueries_;
	}

	@Transactional(readOnly = false)
	@Override
	public Boolean create(IQuery query_) {

		Users user = userLogged.getCurrentUserLogged();
		Queries query = QueriesWrapper.convertToDaemonStructure(query_);
		/*
		 * save query type if not exist
		 */
		QueriesType queryType = query.getQueriesType();
		QueriesType queryTypeDb = queriesManagerDao.getQueriesType().findById(queryType.getId());
		if (queryTypeDb == null)
			queriesManagerDao.getQueriesType().save(queryType);

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
	public Boolean update(IQuery query_) {
		Users user = userLogged.getCurrentUserLogged();
		Queries query = QueriesWrapper.convertToDaemonStructure(query_);
		Queries queryDb = queriesManagerDao.getQueriesDao().findById(query.getId());
		if (queryDb == null)
			throw new DaemonException(ExceptionsCodes.codeNoQuery, ExceptionsCodes.msgNoQuery);
		/*
		 * verify if has permissions
		 */
		UsersHasDataObjectId dataObjectUserId = new UsersHasDataObjectId(user.getId(), query.getId(), queries);
		UsersHasDataObject dataObjectUser = usersManagerDao.getUsersHasdataObjectDao().findById(dataObjectUserId);
		if (dataObjectUser == null || dataObjectUser.getAccesLevel().equals("read"))
			throw new DaemonException(ExceptionsCodes.codeQueryAccessDenied, ExceptionsCodes.msgQueryAccessDenied);

		queriesManagerDao.getQueriesDao().update(query);

		UsersLog log = new UsersLog(user, new Date(), "update", "queries", null, "update query");
		usersManagerDao.getUsersLog().save(log);

		return true;

	}

	@Transactional(readOnly = false)
	@Override
	public boolean addPublicationsToQuery(Long id, List<Long> publicationsIds) {

		Users user = userLogged.getCurrentUserLogged();
		Queries query = queriesManagerDao.getQueriesDao().findById(id);
		if (query == null)
			throw new DaemonException(ExceptionsCodes.codeNoQuery, ExceptionsCodes.msgNoQuery);

		for (Long publicationId : publicationsIds) {
			QueriesHasPublicationsId queryHasPubId = new QueriesHasPublicationsId(id, publicationId);
			QueriesHasPublications queryHasPub = new QueriesHasPublications(queryHasPubId, null, null);
			queriesManagerDao.getQueriesHasPublicationsDao().save(queryHasPub);
		}

		UsersLog log = new UsersLog(user, new Date(), "create", "queries_has_publications", null, "associate publications to a query");
		usersManagerDao.getUsersLog().save(log);

		return true;
	}

	@Transactional(readOnly = false)
	@Override
	public Boolean updateRelevance(Long queryId, Long publicationId, String relevance) {

		Users user = userLogged.getCurrentUserLogged();
		QueriesHasPublicationsId queriesPubId = new QueriesHasPublicationsId(queryId, publicationId);
		QueriesHasPublications queriesPub = queriesManagerDao.getQueriesHasPublicationsDao().findById(queriesPubId);
		if (queriesPub == null)
			throw new DaemonException(ExceptionsCodes.codeQueryPublication, ExceptionsCodes.msgQueryPublication);

		queriesPub.setRelevance(relevance);
		/*
		 * verify if has permissions
		 */
		UsersHasDataObjectId dataObjectUserId = new UsersHasDataObjectId(user.getId(), queryId, queries);
		UsersHasDataObject dataObjectUser = usersManagerDao.getUsersHasdataObjectDao().findById(dataObjectUserId);
		if (dataObjectUser == null || dataObjectUser.getAccesLevel().equals("read"))
			throw new DaemonException(ExceptionsCodes.codeQueryAccessDenied, ExceptionsCodes.msgQueryAccessDenied);

		queriesManagerDao.getQueriesHasPublicationsDao().update(queriesPub);

		UsersLog log = new UsersLog(user, new Date(), "update", "queries_has_publications", null, "update relevance");
		usersManagerDao.getUsersLog().save(log);

		return true;
	}

	@Override
	public Map<Long, RelevanceType> getQueryPublicationsRelevance(Long queryId) {

		Queries query = queriesManagerDao.getQueriesDao().findById(queryId);
		if (query == null)
			throw new DaemonException(ExceptionsCodes.codeNoQuery, ExceptionsCodes.msgNoQuery);

		Users user = userLogged.getCurrentUserLogged();
		/*
		 * verify if has permissions
		 */
		UsersHasDataObjectId dataObjectUserId = new UsersHasDataObjectId(user.getId(), queryId, queries);
		UsersHasDataObject dataObjectUser = usersManagerDao.getUsersHasdataObjectDao().findById(dataObjectUserId);
		if (dataObjectUser == null)
			throw new DaemonException(ExceptionsCodes.codeQueryAccessDenied, ExceptionsCodes.msgQueryAccessDenied);

		Set<QueriesHasPublications> queriesHasPub = query.getQueriesHasPublicationses();
		Map<Long, RelevanceType> map = new HashMap<Long, RelevanceType>();
		for (QueriesHasPublications queryHasPub : queriesHasPub) {
			Long pubId = queryHasPub.getId().getPublicationsId();
			map.put(pubId, RelevanceType.convertString(queryHasPub.getRelevance()));
		}

		if (map.size() == 0)
			return null;

		return map;
	}
}
