package com.silicolife.anote2daemon.wrapper.publications;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import pt.uminho.anote2.core.document.IPublication;
import pt.uminho.anote2.core.document.IPublicationExternalSourceLink;
import pt.uminho.anote2.core.document.labels.IPublicationLabel;
import pt.uminho.anote2.core.document.structure.IPublicationField;
import pt.uminho.anote2.datastructures.documents.Publication;

import com.silicolife.anote2daemon.model.core.entities.Publications;
import com.silicolife.anote2daemon.model.core.entities.PublicationsFields;
import com.silicolife.anote2daemon.model.core.entities.PublicationsHasPublicationLabels;
import com.silicolife.anote2daemon.model.core.entities.PublicationsHasPublicationsSource;

/**
 * Wrapper to handler with transformations of publications structures
 * 
 * 
 * @author Joel Azevedo Costa
 * @year 2015
 *
 */
public class PublicationsWrapper {

	public static Publication convertToAnoteStructure(Publications publications) {
		/*
		 * get publications parameters
		 */
		Long id = publications.getId();
		String title = publications.getTitle();
		String authors = publications.getPubauthors();
		String type = publications.getCategory();
		String yearDate = String.valueOf(publications.getPubdate());
		String fullDate = publications.getFulldate();
		String status = publications.getPubstatus();
		String journal = publications.getJournal();
		String volume = publications.getVolume();
		String issue = publications.getIssue();
		String pages = publications.getPages();
		String abstractSection = publications.getAbstract_();
		String externalLink = publications.getExternalLinks();
		Boolean freefulltextAvailable = publications.getFreeFullText();
		String notes = publications.getNotes();
		String relativePath = publications.getRelativePath();
		/*
		 * wrapper publications has sources
		 */
		Set<PublicationsHasPublicationsSource> publicationsHasSources = publications.getPublicationsHasPublicationsSources();
		List<IPublicationExternalSourceLink> externalIDsSource_ = null;
		if (publicationsHasSources.size() > 0) {
			externalIDsSource_ = new ArrayList<IPublicationExternalSourceLink>();
			for (PublicationsHasPublicationsSource pubSource : publicationsHasSources) {
				IPublicationExternalSourceLink pubExternal_ = PublicationsSourceWrapper.convertToAnoteStructure(pubSource);
				externalIDsSource_.add(pubExternal_);
			}
		}
		/*
		 * wrapper publications has fields
		 */
		Set<PublicationsFields> publicationsHasFields = publications.getPublicationsFieldses();
		List<IPublicationField> fullTextfields_ = null;
		if (publicationsHasFields.size() > 0) {
			fullTextfields_ = new ArrayList<IPublicationField>();
			for (PublicationsFields pubField : publicationsHasFields) {
				IPublicationField pubField_ = PublicationsFieldsWrapper.convertToAnoteStructure(pubField);
				fullTextfields_.add(pubField_);
			}
		}
		/*
		 * wrapper publications has labels
		 */
		Set<PublicationsHasPublicationLabels> publicationsHasLabels = publications.getPublicationsHasPublicationLabelses();
		List<IPublicationLabel> labels_ = null;
		if (publicationsHasLabels.size() > 0) {
			labels_ = new ArrayList<IPublicationLabel>();
			for (PublicationsHasPublicationLabels pubLabel : publicationsHasLabels) {
				IPublicationLabel pubLabel_ = PublicationsLabelsWrapper.convertToAnoteStructure(pubLabel.getPublicationsLabels());
				labels_.add(pubLabel_);
			}
		}
		/*
		 * create publication
		 */
		Publication publication = new Publication(id, title, authors, type, yearDate, fullDate, status, journal, volume, issue, pages, abstractSection, externalLink,
				freefulltextAvailable, notes, relativePath, externalIDsSource_, fullTextfields_, labels_);

		return publication;
	}

	public static Publications convertToDaemonStructure(IPublication publications_) {
		/*
		 * get publications parameters
		 */
		Long id = publications_.getID();
		String title = publications_.getTitle();
		String authors = publications_.getAuthors();
		String type = publications_.getType();
		String yearDate = publications_.getYearDate();
		String fullDate = publications_.getFullDate();
		String status = publications_.getStatus();
		String journal = publications_.getJournal();
		String volume = publications_.getVolume();
		String issue = publications_.getIssue();
		String pages = publications_.getPages();
		String abstractSection = publications_.getAbstractSection();
		String externalLink = publications_.getExternalLink();
		Boolean freefulltextAvailable = publications_.getAvailableFreeFullTExt();
		String notes = publications_.getNotes();
		String relativePath = publications_.getRelativePath();

		Publications publication = new Publications();
		publication.setId(id);
		publication.setTitle(title);
		publication.setPubauthors(authors);
		publication.setCategory(type);
		publication.setPubdate(Integer.parseInt(yearDate));
		publication.setFulldate(fullDate);
		publication.setPubstatus(status);
		publication.setJournal(journal);
		publication.setVolume(volume);
		publication.setIssue(issue);
		publication.setPages(pages);
		publication.setAbstract_(abstractSection);
		publication.setExternalLinks(externalLink);
		publication.setFreeFullText(freefulltextAvailable);
		publication.setNotes(notes);
		publication.setRelativePath(relativePath);

		return publication;

	}
}
