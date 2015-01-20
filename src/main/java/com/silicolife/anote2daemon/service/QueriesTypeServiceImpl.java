package com.silicolife.anote2daemon.service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.silicolife.anote2daemon.model.dao.core.GenericDao;
import com.silicolife.anote2daemon.model.pojo.QueryType;
import com.silicolife.anote2daemon.service.core.QueriesTypeService;
import com.silicolife.anote2daemon.utils.DaemonResponse;
import com.silicolife.anote2daemon.utils.GenerateRandomId;

@EnableTransactionManagement
@Service
public class QueriesTypeServiceImpl implements QueriesTypeService {

	@Autowired
	private GenericDao<QueryType> genericDao;
	private final static Class<QueryType> className = QueryType.class;

	@Transactional(readOnly = true)
	@Override
	public DaemonResponse getById(Long id) {
		QueryType queryType = genericDao.findById(className, id);
		DaemonResponse response = new DaemonResponse(queryType, null);
		return response;
	}

	@Transactional(readOnly = true)
	@Override
	public DaemonResponse findByOrAttributes(Long id, String description) {
		/*
		 * Restrictions
		 */
		Map<String, Serializable> orRestrictions = new HashMap<String, Serializable>();
		if (id != null)
			orRestrictions.put("id", id);
		if (description != null)
			orRestrictions.put("description", description);

		List<QueryType> queryType = genericDao.findByOrAttributes(className, orRestrictions);
		if (queryType.size() == 0)
			return new DaemonResponse();

		return new DaemonResponse(queryType, null);
	}

	@Override
	public DaemonResponse update() {
		return null;
	}

	@Transactional
	@Override
	public DaemonResponse create(String description) {
		/**
		 * Generate unique ID
		 */
		Long id = GenerateRandomId.generateID();

		description = description.trim();
		DaemonResponse responseFind = findByOrAttributes(id, description);
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
					errors.put(1, "PK exist");
				if (description.toLowerCase().equals(descriptionFromDb))
					errors.put(2, "UK(description) exist");
			}

			return new DaemonResponse(null, errors);
		}

		/**
		 * If pass validations
		 */
		QueryType queryTypeToSave = new QueryType(id);
		queryTypeToSave.setDescription(description);
		genericDao.save(queryTypeToSave);

		return new DaemonResponse(queryTypeToSave, null);
	}
}
