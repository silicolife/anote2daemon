package com.silicolife.anote2daemon.service.resources;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.silicolife.anote2daemon.model.core.dao.UsersLogged;
import com.silicolife.anote2daemon.model.core.dao.manager.ResourcesManagerDao;
import com.silicolife.anote2daemon.model.core.dao.manager.UsersManagerDao;
import com.silicolife.anote2daemon.model.core.entities.Classes;
import com.silicolife.anote2daemon.model.core.entities.Users;
import com.silicolife.anote2daemon.model.core.entities.UsersLog;

/**
 * Service layer which implements all operations about classes
 * 
 * 
 * @author Joel Azevedo costa
 * @year 2015
 *
 */
@Service
@Transactional(readOnly = true)
public class ClassesServiceImpl implements ClassesService {

	private ResourcesManagerDao resourcesManagerDao;
	private UsersManagerDao usersManagerDao;

	@Autowired
	private UsersLogged userLogged;

	@Autowired
	public ClassesServiceImpl(ResourcesManagerDao resourcesManagerDao, UsersManagerDao usersManagerDao) {
		this.resourcesManagerDao = resourcesManagerDao;
		this.usersManagerDao = usersManagerDao;
	}

	@Transactional(readOnly = false)
	@Override
	public Boolean insertNewClass(Classes classes) {
		resourcesManagerDao.getClassesDao().save(classes);
		/*
		 * Log
		 */
		Users user = userLogged.getCurrentUserLogged();
		UsersLog log = new UsersLog(user, new Date(), "create", "classes", null, "create new classes");
		usersManagerDao.getUsersLog().save(log);
		return true;
	}

	@Override
	public Map<Long, String> getClasses() {
		Map<Long, String> results = new HashMap<Long, String>();

		List<Classes> classes = resourcesManagerDao.getClassesDao().findAll();
		for (Classes class_ : classes) {
			results.put(class_.getId(), class_.getClass_());
		}

		if (results.size() == 0)
			return null;

		return results;
	}

	@Override
	public Classes getClassById(Long id) {
		Classes classes = resourcesManagerDao.getClassesDao().findById(id);
		return classes;
	}

}
