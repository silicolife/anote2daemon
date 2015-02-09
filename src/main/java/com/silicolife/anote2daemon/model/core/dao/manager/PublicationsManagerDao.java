package com.silicolife.anote2daemon.model.core.dao.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.silicolife.anote2daemon.model.core.dao.GenericDao;
import com.silicolife.anote2daemon.model.core.dao.specialdao.PublicationsAuxDao;
import com.silicolife.anote2daemon.model.core.entities.Publications;
import com.silicolife.anote2daemon.model.core.entities.PublicationsHasPublicationsSource;
import com.silicolife.anote2daemon.model.core.entities.PublicationsSource;

@Component
public class PublicationsManagerDao {

	private GenericDao<PublicationsSource> publicationsSourceDao;
	private GenericDao<PublicationsHasPublicationsSource> publicationsHasPublicationsSourceDao;
	private GenericDao<Publications> publicationsDao;
	private PublicationsAuxDao publicationsAuxDao;

	@Autowired
	public PublicationsManagerDao(GenericDao<PublicationsSource> publicationsSourceDao, GenericDao<PublicationsHasPublicationsSource> publicationsHasPublicationsSourceDao,
			GenericDao<Publications> publicationsDao, PublicationsAuxDao publicationsAuxDao) {
		super();
		this.publicationsSourceDao = publicationsSourceDao;
		this.publicationsHasPublicationsSourceDao = publicationsHasPublicationsSourceDao;
		this.publicationsDao = publicationsDao;
		this.publicationsAuxDao = publicationsAuxDao;
	}

	public GenericDao<PublicationsSource> getPublicationsSourceDao() {
		return publicationsSourceDao;
	}

	public GenericDao<PublicationsHasPublicationsSource> getPublicationsHasPublicationsSourceDao() {
		return publicationsHasPublicationsSourceDao;
	}

	public GenericDao<Publications> getPublicationsDao() {
		return publicationsDao;
	}

	public PublicationsAuxDao getPublicationsAuxDao() {
		return publicationsAuxDao;
	}

}
