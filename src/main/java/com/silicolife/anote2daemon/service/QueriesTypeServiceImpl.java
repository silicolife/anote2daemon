package com.silicolife.anote2daemon.service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.silicolife.anote2daemon.model.dao.core.QueriesTypeDao;
import com.silicolife.anote2daemon.model.pojo.QueryType;
import com.silicolife.anote2daemon.service.core.QueriesTypeService;
import com.silicolife.anote2daemon.utils.DaemonResponse;
import com.silicolife.anote2daemon.utils.GenerateRandomId;

@EnableTransactionManagement
@Service
public class QueriesTypeServiceImpl implements QueriesTypeService {

	@Autowired
	private QueriesTypeDao queriesTypeDao;
	
	private final static Class<QueryType> className = QueryType.class;

	@Transactional(readOnly = true)
	@Override
	public DaemonResponse getById(Long id) {
		QueryType queryType = queriesTypeDao.findById(className, id);
		DaemonResponse response = new DaemonResponse(queryType, null);
		return response;
	}

	@Transactional(readOnly = true)
	@Override
	public DaemonResponse getByOrAttributes(Long id, String description) {
		/*
		 * Restrictions
		 */
		Map<String, Serializable> orRestrictions = new HashMap<String, Serializable>();
		if (id != null)
			orRestrictions.put("id", id);
		if (description != null)
			orRestrictions.put("description", description);

		List<QueryType> queryTypeList = queriesTypeDao.findByOrAttributes(className, orRestrictions);
		if (queryTypeList.size() == 0)
			return new DaemonResponse();

		return new DaemonResponse(queryTypeList, null);
	}

	@Transactional(readOnly = true)
	@Override
	public DaemonResponse getByAttributes(Long id, String description) {
		/*
		 * Restrictions
		 */
		Map<String, Serializable> eqRestrictions = new HashMap<String, Serializable>();
		if (id != null)
			eqRestrictions.put("id", id);
		if (description != null)
			eqRestrictions.put("description", description);

		List<QueryType> queryTypeList = queriesTypeDao.findByAttributes(className, eqRestrictions);
		if (queryTypeList.size() == 0)
			return new DaemonResponse();

		return new DaemonResponse(queryTypeList, null);
	}

	@Transactional
	@Override
	public DaemonResponse update(Long id, String description) {

		Map<Integer, String> errors = null;
		DaemonResponse responseFind = getById(id);
		QueryType queryTypeObj = (QueryType) responseFind.getContent();
		description = description.trim();

		/**
		 * Validations
		 */
		if (queryTypeObj == null) {
			errors = new HashMap<Integer, String>();
			errors.put(1, "PK does not exist");
		}

		responseFind = getByAttributes(null, description);
		if (responseFind.getContent() != null) {
			if (errors == null)
				errors = new HashMap<Integer, String>();
			errors.put(2, "UK(description)already exist");
		}

		if (errors != null)
			return new DaemonResponse(null, errors);

		/**
		 * If pass validations
		 */
		queryTypeObj.setDescription(description);
		queriesTypeDao.update(queryTypeObj);

		return new DaemonResponse(queryTypeObj, null);
	}

	@Transactional
	@Override
	public DaemonResponse create(String description) {
		/**
		 * Generate unique ID
		 */
		Long id = GenerateRandomId.generateID();

		description = description.trim();
		DaemonResponse responseFind = getByOrAttributes(id, description);
		@SuppressWarnings("unchecked")
		List<QueryType> queryTypeList = (List<QueryType>) responseFind.getContent();

		/**
		 * Validations
		 */
		if (queryTypeList != null) {
			Map<Integer, String> errors = new HashMap<Integer, String>();

			for (QueryType queryType : queryTypeList) {
				Long idFromDb = queryType.getId();
				String descriptionFromDb = queryType.getDescription().trim().toLowerCase();
				if (id == idFromDb)
					errors.put(1, "PK already exist");
				if (description.toLowerCase().equals(descriptionFromDb))
					errors.put(2, "UK(description)already exist");
			}
			return new DaemonResponse(null, errors);
		}

		/**
		 * If pass validations
		 */
		QueryType queryTypeToSave = new QueryType(id);
		queryTypeToSave.setDescription(description);
		queriesTypeDao.save(queryTypeToSave);

		return new DaemonResponse(queryTypeToSave, null);
	}
}
