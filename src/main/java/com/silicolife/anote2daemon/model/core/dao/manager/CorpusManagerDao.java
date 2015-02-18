package com.silicolife.anote2daemon.model.core.dao.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.silicolife.anote2daemon.model.core.dao.GenericDao;
import com.silicolife.anote2daemon.model.core.dao.specialdao.PublicationsAuxDao;
import com.silicolife.anote2daemon.model.core.entities.Corpus;
import com.silicolife.anote2daemon.model.core.entities.CorpusHasProcesses;
import com.silicolife.anote2daemon.model.core.entities.CorpusHasPublications;
import com.silicolife.anote2daemon.model.core.entities.CorpusProperties;
import com.silicolife.anote2daemon.model.core.entities.Publications;
import com.silicolife.anote2daemon.model.core.entities.PublicationsFields;
import com.silicolife.anote2daemon.model.core.entities.PublicationsHasPublicationLabels;
import com.silicolife.anote2daemon.model.core.entities.PublicationsHasPublicationsSource;
import com.silicolife.anote2daemon.model.core.entities.PublicationsLabels;
import com.silicolife.anote2daemon.model.core.entities.PublicationsSource;

@Repository
public class CorpusManagerDao extends PublicationsManagerDao {

	private GenericDao<Corpus> corpusDao;
	private GenericDao<CorpusProperties> corpusPropertiesDao;
	private GenericDao<CorpusHasProcesses> corpusHasProcessesDao;
	private GenericDao<CorpusHasPublications> corpusHasPublicationsDao;

	@Autowired
	public CorpusManagerDao(GenericDao<PublicationsSource> publicationsSourceDao, GenericDao<PublicationsHasPublicationsSource> publicationsHasPublicationsSourceDao,
			GenericDao<Publications> publicationsDao, GenericDao<PublicationsFields> publicationsFieldsDao, GenericDao<PublicationsLabels> publicationsLabelsDao,
			GenericDao<PublicationsHasPublicationLabels> publicationsHasPublicationLabelsDao, PublicationsAuxDao publicationsAuxDao, GenericDao<Corpus> corpusDao,
			GenericDao<CorpusProperties> corpusPropertiesDao, GenericDao<CorpusHasProcesses> corpusHasProcessesDao, GenericDao<CorpusHasPublications> corpusHasPublicationsDao) {
		super(publicationsSourceDao, publicationsHasPublicationsSourceDao, publicationsDao, publicationsFieldsDao, publicationsLabelsDao, publicationsHasPublicationLabelsDao,
				publicationsAuxDao);
		this.corpusDao = corpusDao;
		this.corpusPropertiesDao = corpusPropertiesDao;
		this.corpusHasProcessesDao = corpusHasProcessesDao;
		this.corpusHasPublicationsDao = corpusHasPublicationsDao;
	}

	public GenericDao<Corpus> getCorpusDao() {
		return corpusDao;
	}

	public GenericDao<CorpusProperties> getCorpusPropertiesDao() {
		return corpusPropertiesDao;
	}

	public GenericDao<CorpusHasProcesses> getCorpusHasProcessesDao() {
		return corpusHasProcessesDao;
	}

	public GenericDao<CorpusHasPublications> getCorpusHasPublicationsDao() {
		return corpusHasPublicationsDao;
	}
}
