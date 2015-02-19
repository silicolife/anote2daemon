package com.silicolife.anote2daemon.service.corpora;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pt.uminho.anote2.core.document.IPublication;
import pt.uminho.anote2.core.document.corpus.ICorpus;
import pt.uminho.anote2.process.IE.IIEProcess;

import com.silicolife.anote2daemon.exceptions.DaemonException;
import com.silicolife.anote2daemon.exceptions.ExceptionsCodes;
import com.silicolife.anote2daemon.model.core.dao.UsersLogged;
import com.silicolife.anote2daemon.model.core.dao.manager.CorpusManagerDao;
import com.silicolife.anote2daemon.model.core.dao.manager.UsersManagerDao;
import com.silicolife.anote2daemon.model.core.entities.Corpus;
import com.silicolife.anote2daemon.model.core.entities.CorpusProperties;
import com.silicolife.anote2daemon.model.core.entities.Processes;
import com.silicolife.anote2daemon.model.core.entities.Publications;
import com.silicolife.anote2daemon.model.core.entities.Users;
import com.silicolife.anote2daemon.model.core.entities.UsersHasDataObject;
import com.silicolife.anote2daemon.model.core.entities.UsersHasDataObjectId;
import com.silicolife.anote2daemon.model.core.entities.UsersLog;
import com.silicolife.anote2daemon.utils.ResourcesTypeUtils;
import com.silicolife.anote2daemon.wrapper.corpora.CorpusPropertiesWrapper;
import com.silicolife.anote2daemon.wrapper.corpora.CorpusWrapper;
import com.silicolife.anote2daemon.wrapper.process.ProcessWrapper;
import com.silicolife.anote2daemon.wrapper.publications.PublicationsWrapper;

/**
 * Service layer which implements all operations about corpus.
 * 
 * @author Joel Azevedo Costa
 * @year 2015
 */
@Service
@Transactional(readOnly = true)
public class CorpusServiceImpl implements CorpusService {

	private CorpusManagerDao corpusManagerDao;
	private UsersManagerDao usersManagerDao;
	@Autowired
	private UsersLogged userLogged;
	private final static String corpusStr = ResourcesTypeUtils.corpus.toString();

	@Autowired
	public CorpusServiceImpl(CorpusManagerDao corpusManagerDao, UsersManagerDao usersManagerDao) {
		this.corpusManagerDao = corpusManagerDao;
		this.usersManagerDao = usersManagerDao;
	}

	@Override
	public List<ICorpus> getAllCorpus() {
		List<Corpus> corpus = corpusManagerDao.getCorpusDao().findAll();
		List<ICorpus> corpus_ = new ArrayList<ICorpus>();
		for (Corpus corpusObj : corpus) {
			ICorpus corpusObj_ = CorpusWrapper.convertToAnoteStructure(corpusObj);
			corpus_.add(corpusObj_);
		}
		if (corpus_.size() == 0)
			return null;

		return corpus_;
	}

	@Override
	public ICorpus getCorpusByID(Long id) {
		Corpus corpus = corpusManagerDao.getCorpusDao().findById(id);
		if (corpus == null)
			return null;

		ICorpus corpus_ = CorpusWrapper.convertToAnoteStructure(corpus);

		return corpus_;
	}

	@Transactional(readOnly = false)
	@Override
	public Boolean createCorpus(ICorpus corpus_) {
		/*
		 * save corpus
		 */
		Users user = userLogged.getCurrentUserLogged();
		Corpus corpus = CorpusWrapper.convertToDaemonStructure(corpus_);
		corpusManagerDao.getCorpusDao().save(corpus);
		/*
		 * save corpus associations
		 */
		Properties properties = corpus_.getProperties();
		if (properties != null) {
			Set<CorpusProperties> corpusProperties = CorpusPropertiesWrapper.convertToDaemonStructure(properties, corpus);
			for (CorpusProperties corpusProperty : corpusProperties) {
				createCorpusProperties(corpusProperty);
			}
		}

		UsersHasDataObjectId dataObjectUserId = new UsersHasDataObjectId(user.getId(), corpus.getId(), corpusStr);
		UsersHasDataObject dataObjectUser = new UsersHasDataObject(dataObjectUserId, user, "owner");
		usersManagerDao.getUsersHasdataObjectDao().save(dataObjectUser);
		/*
		 * log
		 */
		UsersLog log = new UsersLog(user, new Date(), "create", "corpus/corpus_properties/daemon_users_has_data_object", null, "create corpus");
		usersManagerDao.getUsersLog().save(log);

		return true;

	}

	@Transactional(readOnly = false)
	@Override
	public boolean updateCorpus(ICorpus corpus_) {
		/*
		 * update corpus
		 */
		Users user = userLogged.getCurrentUserLogged();
		Corpus corpus = CorpusWrapper.convertToDaemonStructure(corpus_);
		corpusManagerDao.getCorpusDao().update(corpus);
		/*
		 * log
		 */
		UsersLog log = new UsersLog(user, new Date(), "update", "corpus", null, "update corpus");
		usersManagerDao.getUsersLog().save(log);

		return true;
	}

	@Override
	public List<IPublication> getCorpusPublications(Long corpusId) {
		Corpus corpus = corpusManagerDao.getCorpusDao().findById(corpusId);
		if (corpus == null)
			throw new DaemonException(ExceptionsCodes.codeNoCorpus, ExceptionsCodes.msgNoCorpus);

		List<IPublication> publications_ = new ArrayList<IPublication>();
		List<Publications> publications = corpusManagerDao.getPublicationsAuxDao().findPublicationsByCorpusId(corpusId);
		for (Publications publication : publications) {
			IPublication publication_ = PublicationsWrapper.convertToAnoteStructure(publication);
			publications_.add(publication_);
		}

		if (publications_.size() == 0)
			return null;

		return publications_;
	}

	@Override
	public List<IIEProcess> getCorpusProcesses(Long corpusId) {

		List<IIEProcess> processes_ = new ArrayList<IIEProcess>();
		List<Processes> processes = corpusManagerDao.getCorpusAuxDao().findProcessesByCorpusId(corpusId);
		for (Processes process : processes) {
			IIEProcess process_ = ProcessWrapper.convertToAnoteStructure(process);
			processes_.add(process_);
		}
		if (processes_.size() == 0)
			return null;

		return processes_;
	}

	/*
	 * private auxiliary methods to save corpus properties
	 */
	private void createCorpusProperties(CorpusProperties properties) {
		corpusManagerDao.getCorpusPropertiesDao().save(properties);
	}
}
