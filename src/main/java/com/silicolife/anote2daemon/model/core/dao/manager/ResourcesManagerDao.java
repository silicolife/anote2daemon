package com.silicolife.anote2daemon.model.core.dao.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.silicolife.anote2daemon.model.core.dao.GenericDao;
import com.silicolife.anote2daemon.model.core.entities.Classes;

@Repository
public class ResourcesManagerDao {

	private GenericDao<Classes> classesDao;

	@Autowired
	public ResourcesManagerDao(GenericDao<Classes> classesDao) {
		super();
		this.classesDao = classesDao;
	}

	public GenericDao<Classes> getClassesDao() {
		return classesDao;
	}
}
