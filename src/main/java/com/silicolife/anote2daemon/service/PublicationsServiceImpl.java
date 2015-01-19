package com.silicolife.anote2daemon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.silicolife.anote2daemon.model.dao.core.GenericDao;
import com.silicolife.anote2daemon.model.pojo.Publications;
import com.silicolife.anote2daemon.service.core.PublicationsService;

@EnableTransactionManagement
@Service
@Transactional(readOnly = true)
public class PublicationsServiceImpl implements PublicationsService {

	@Autowired
	GenericDao genericDao;

	private final static Class<Publications> className = Publications.class;

	@Override
	public Publications getById(Long id) {
		return (Publications) genericDao.find(className, id);
	}
}
