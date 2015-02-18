package com.silicolife.anote2daemon.model.core.dao.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.silicolife.anote2daemon.model.core.dao.GenericDao;
import com.silicolife.anote2daemon.model.core.entities.Corpus;
import com.silicolife.anote2daemon.model.core.entities.CorpusHasProcesses;
import com.silicolife.anote2daemon.model.core.entities.CorpusHasPublications;
import com.silicolife.anote2daemon.model.core.entities.CorpusProperties;

@Repository
public class CorpusManagerDao {

	private GenericDao<Corpus> corpusDao;
	private GenericDao<CorpusProperties> corpusPropertiesDao;
	private GenericDao<CorpusHasProcesses> corpusHasProcessesDao;
	private GenericDao<CorpusHasPublications> corpusHasPublicationsDao;

	@Autowired
	public CorpusManagerDao(GenericDao<Corpus> corpusDao, GenericDao<CorpusProperties> corpusPropertiesDao, GenericDao<CorpusHasProcesses> corpusHasProcessesDao,
			GenericDao<CorpusHasPublications> corpusHasPublicationsDao) {
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
