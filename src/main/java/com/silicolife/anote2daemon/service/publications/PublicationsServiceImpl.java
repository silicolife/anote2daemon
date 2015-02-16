package com.silicolife.anote2daemon.service.publications;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pt.uminho.anote2.core.document.IPublication;
import pt.uminho.anote2.core.document.IPublicationExternalSourceLink;
import pt.uminho.anote2.core.document.labels.IPublicationLabel;
import pt.uminho.anote2.core.document.structure.IPublicationField;

import com.silicolife.anote2daemon.exceptions.DaemonException;
import com.silicolife.anote2daemon.exceptions.ExceptionsCodes;
import com.silicolife.anote2daemon.model.core.dao.manager.PublicationsManagerDao;
import com.silicolife.anote2daemon.model.core.entities.Publications;
import com.silicolife.anote2daemon.model.core.entities.PublicationsFields;
import com.silicolife.anote2daemon.model.core.entities.PublicationsHasPublicationLabels;
import com.silicolife.anote2daemon.model.core.entities.PublicationsHasPublicationsSource;
import com.silicolife.anote2daemon.model.core.entities.PublicationsLabels;
import com.silicolife.anote2daemon.model.core.entities.PublicationsSource;
import com.silicolife.anote2daemon.wrapper.publications.PublicationsFieldsWrapper;
import com.silicolife.anote2daemon.wrapper.publications.PublicationsLabelsWrapper;
import com.silicolife.anote2daemon.wrapper.publications.PublicationsSourceWrapper;
import com.silicolife.anote2daemon.wrapper.publications.PublicationsWrapper;

/**
 * Service layer which implements all operations about publications
 * 
 * 
 * @author Joel Azevedo Costa
 * @year 2015
 *
 */
@Service
@Transactional(readOnly = true)
public class PublicationsServiceImpl implements PublicationsService {

	private PublicationsManagerDao publicationsManagerDao;

	@Autowired
	public PublicationsServiceImpl(PublicationsManagerDao publicationsManagerDao) {
		super();
		this.publicationsManagerDao = publicationsManagerDao;
	}

	@Override
	public IPublication getById(Long id) {
		Publications publication = publicationsManagerDao.getPublicationsDao().findById(id);
		IPublication publication_ = null;
		if (publication != null) {
			publication_ = PublicationsWrapper.convertToAnoteStructure(publication);
		}
		return publication_;
	}

	@Transactional(readOnly = false)
	@Override
	public Boolean update(IPublication publication_) {
		Publications pub = publicationsManagerDao.getPublicationsDao().findById(publication_.getID());
		if (pub == null)
			throw new DaemonException(ExceptionsCodes.codeNoPublication, ExceptionsCodes.msgNoPublication);

		Publications publications = PublicationsWrapper.convertToDaemonStructure(publication_);
		publicationsManagerDao.getPublicationsDao().update(publications);
		return true;
	}

	@Transactional(readOnly = false)
	@Override
	public Boolean create(List<IPublication> publications_) {
		for (IPublication publication_ : publications_) {
			/*
			 * save publication
			 */
			Publications publication = PublicationsWrapper.convertToDaemonStructure(publication_);
			publicationsManagerDao.getPublicationsDao().save(publication);
			/*
			 * save publications associations
			 */
			List<IPublicationExternalSourceLink> pubSources_ = publication_.getPublicationExternalIDSource();
			List<IPublicationField> pubFiels_ = publication_.getPublicationFields();
			List<IPublicationLabel> pubLabels_ = publication_.getPublicationLabels();

			if (pubSources_ != null) {
				for (IPublicationExternalSourceLink source_ : pubSources_) {
					PublicationsHasPublicationsSource pubHasPubSource = PublicationsSourceWrapper.convertToDaemonStructure(source_, publication);
					createSource(pubHasPubSource);
				}
			}
			if (pubFiels_ != null) {
				for (IPublicationField field_ : pubFiels_) {
					PublicationsFields pubFields = PublicationsFieldsWrapper.convertToDaemonStructure(field_, publication);
					createFields(pubFields);
				}
			}
			if (pubLabels_ != null) {
				for (IPublicationLabel label_ : pubLabels_) {
					PublicationsHasPublicationLabels pubHasPubLabel = PublicationsLabelsWrapper.convertToDaemonStructure(label_, publication);
					createLabels(pubHasPubLabel);
				}
			}
		}
		return true;
	}

	@Override
	public Map<String, Long> getAllPublicationsFromSource(String source) {
		PublicationsSource publicationSource = publicationsManagerDao.getPublicationsSourceDao().findUniqueByAttribute("description", source);
		if (publicationSource == null)
			throw new DaemonException(ExceptionsCodes.codePublicationSource, ExceptionsCodes.msgPublicationSource);

		Map<String, Long> response = new HashMap<String, Long>();
		Hibernate.initialize(publicationSource.getPublicationsHasPublicationsSources());
		Set<PublicationsHasPublicationsSource> pubsSources = publicationSource.getPublicationsHasPublicationsSources();
		for (PublicationsHasPublicationsSource pubSource : pubsSources) {
			String pubSourceId = pubSource.getId().getPublicationsSourceInternalId();
			Long pubId = pubSource.getPublications().getId();
			response.put(pubSourceId, pubId);
		}
		if (response.size() == 0)
			return null;

		return response;
	}

	/*
	 * private methods
	 */
	private void createSource(PublicationsHasPublicationsSource pubHasPubSource) {
		PublicationsSource pubSource = pubHasPubSource.getPublicationsSource();
		String sourceDesc = pubSource.getDescription();
		PublicationsSource publicationSource = publicationsManagerDao.getPublicationsSourceDao().findUniqueByAttribute("description", sourceDesc);
		if (publicationSource == null) {
			publicationsManagerDao.getPublicationsSourceDao().save(pubSource);
			publicationSource = pubSource;
		}
		PublicationsHasPublicationsSource pubHasSource = publicationsManagerDao.getPublicationsHasPublicationsSourceDao().findById(pubHasPubSource.getId());
		if (pubHasSource == null) {
			pubHasPubSource.getId().setPublicationsSourceId(publicationSource.getId());
			publicationsManagerDao.getPublicationsHasPublicationsSourceDao().save(pubHasPubSource);
		}
	}

	private void createFields(PublicationsFields pubField) {
		publicationsManagerDao.getPublicationsFieldsDao().save(pubField);
	}

	private void createLabels(PublicationsHasPublicationLabels pubHasPubLabels) {
		PublicationsLabels pubLabels = pubHasPubLabels.getPublicationsLabels();
		String labelDesc = pubLabels.getDescription();
		PublicationsLabels pubLabel = publicationsManagerDao.getPublicationsLabelsDao().findUniqueByAttribute("description", labelDesc);
		if (pubLabel == null) {
			publicationsManagerDao.getPublicationsLabelsDao().save(pubLabels);
			pubLabel = pubLabels;
		}
		PublicationsHasPublicationLabels pubHasLabels = publicationsManagerDao.getPublicationsHasPublicationLabelsDao().findById(pubHasPubLabels.getId());
		if (pubHasLabels == null) {
			pubHasPubLabels.getId().setPublicationLabelsId(pubLabel.getId());
			publicationsManagerDao.getPublicationsHasPublicationLabelsDao().save(pubHasPubLabels);

		}
	}
}
