package com.silicolife.anote2daemon.service;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.silicolife.anote2daemon.model.dao.core.QueriesDao;
import com.silicolife.anote2daemon.model.pojo.DaemonTypeResources;
import com.silicolife.anote2daemon.model.pojo.DaemonUsers;
import com.silicolife.anote2daemon.model.pojo.Queries;
import com.silicolife.anote2daemon.service.core.DaemonTypeResourcesService;
import com.silicolife.anote2daemon.service.core.DaemonUserHasDataObjectService;
import com.silicolife.anote2daemon.service.core.DaemonUsersService;
import com.silicolife.anote2daemon.service.core.QueriesService;
import com.silicolife.anote2daemon.webservice.DaemonResponse;
import com.silicolife.anote2daemon.webservice.ResourcesType;

@Service
@Transactional(readOnly = true)
public class QueriesServiceImpl implements QueriesService {

	@Autowired
	private QueriesDao queriesDao;
	@Autowired
	private DaemonUsersService daemonUsersService;
	@Autowired
	private DaemonUserHasDataObjectService daemonUsersHasDataObjectService;
	@Autowired
	private DaemonTypeResourcesService daemonTypeResourcesService;

	private final static Class<Queries> className = Queries.class;

	@Override
	public DaemonResponse<Queries> getById(Long id) {
		Queries query = queriesDao.findById(className, id);
		return new DaemonResponse<Queries>(query);
	}

	@Override
	public DaemonResponse<Map<String, List<Queries>>> getAll(DaemonUsers user) {

		/**
		 * verify if user exist
		 */
		DaemonResponse<DaemonUsers> usersFromDb = daemonUsersService.getById(user.getId());
		if (usersFromDb.getContent() == null) {
			System.out.println("No user");
			return null;
		}
		
		/**
		 * verify if resource type queries exist
		 */
		Map<String, Serializable> eqRestrictions = new HashMap<String, Serializable>();
		eqRestrictions.put("description", ResourcesType.queries);
		DaemonResponse<List<DaemonTypeResources>> typesResourcesFromDb = daemonTypeResourcesService.getByAttributes(eqRestrictions);
		List<DaemonTypeResources> listResources = typesResourcesFromDb.getContent();
		if (listResources == null) {
			System.out.print("no resource");
			return null;
		}

		/**
		 * get all queries from a user
		 */
		DaemonResponse<List<Object>> listObjectFromDb = daemonUsersHasDataObjectService.getObjectsByAttributes(usersFromDb.getContent().getId(), listResources.get(0).getId());
		List<Object> listObject = listObjectFromDb.getContent();
		if (listObject == null)
			return new DaemonResponse<Map<String, List<Queries>>>();

		Map<String, List<Queries>> map = new HashMap<String, List<Queries>>();
		for (Object object : listObject) {
			@SuppressWarnings("unchecked")
			Map<String, Serializable> row = (Map<String, Serializable>) object;
			Long idResource = ((BigInteger) row.get("uid_resource")).longValue();
			
			Queries query = queriesDao.findById(className, idResource);
			String previlege = (String) row.get("acces_level");
			
			List<Queries> listQueries = map.get(previlege);
			if (listQueries == null)
				listQueries = new ArrayList<Queries>();

			listQueries.add(query);
			map.put(previlege, listQueries);
		}

		return new DaemonResponse<Map<String, List<Queries>>>(map);

	}

	@Override
	public DaemonResponse<Queries> create(Queries query) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DaemonResponse<Queries> update(Queries query) {
		// TODO Auto-generated method stub
		return null;
	}
}
