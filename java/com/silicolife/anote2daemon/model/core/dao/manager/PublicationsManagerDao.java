package com.silicolife.anote2daemon.model.core.dao.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.silicolife.anote2daemon.model.core.dao.GenericDao;
import com.silicolife.anote2daemon.model.core.dao.specialdao.PublicationsAuxDao;
import com.silicolife.anote2daemon.model.core.entities.Publications;
import com.silicolife.anote2daemon.model.core.entities.PublicationsFields;
import com.silicolife.anote2daemon.model.core.entities.PublicationsHasPublicationLabels;
import com.silicolife.anote2daemon.model.core.entities.PublicationsHasPublicationsSource;
import com.silicolife.anote2daemon.model.core.entities.PublicationsLabels;
import com.silicolife.anote2daemon.model.core.entities.PublicationsSource;

/**
 * Class to handler with Publications object DAO
 * 
 * @author Joel Azevedo Costa
 * @year 2015
 *
 */
@Repository
public class PublicationsManagerDao {

	private GenericDao<PublicationsSource> publicationsSourceDao;
	private GenericDao<PublicationsHasPublicationsSource> publicationsHasPublicationsSourceDao;
	private GenericDao<Publications> publicationsDao;
	private GenericDao<PublicationsFields> publicationsFieldsDao;
	private GenericDao<PublicationsLabels> publicationsLabelsDao;
	private GenericDao<PublicationsHasPublicationLabels> publicationsHasPublicationLabelsDao;
	private PublicationsAuxDao publicationsAuxDao;

	@Autowired
	public PublicationsManagerDao(GenericDao<PublicationsSource> publicationsSourceDao, GenericDao<PublicationsHasPublicationsSource> publicationsHasPublicationsSourceDao,
			GenericDao<Publications> publicationsDao, GenericDao<PublicationsFields> publicationsFieldsDao, GenericDao<PublicationsLabels> publicationsLabelsDao,
			GenericDao<PublicationsHasPublicationLabels> publicationsHasPublicationLabelsDao, PublicationsAuxDao publicationsAuxDao) {
		this.publicationsSourceDao = publicationsSourceDao;
		this.publicationsHasPublicationsSourceDao = publicationsHasPublicationsSourceDao;
		this.publicationsDao = publicationsDao;
		this.publicationsFieldsDao = publicationsFieldsDao;
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

	public GenericDao<PublicationsFields> getPublicationsFieldsDao() {
		return publicationsFieldsDao;
	}

	public GenericDao<PublicationsLabels> getPublicationsLabelsDao() {
		return publicationsLabelsDao;
	}

	public GenericDao<PublicationsHasPublicationLabels> getPublicationsHasPublicationLabelsDao() {
		return publicationsHasPublicationLabelsDao;
	}

	public PublicationsAuxDao getPublicationsAuxDao() {
		return publicationsAuxDao;
	}

}
