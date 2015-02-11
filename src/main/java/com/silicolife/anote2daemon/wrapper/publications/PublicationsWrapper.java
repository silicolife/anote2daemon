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

	public static Publication convertToAnoteStructure(Publications daemonObject) {
		/*
		 * get publications parameters
		 */
		Long id = daemonObject.getId();
		String title = daemonObject.getTitle();
		String authors = daemonObject.getPubauthors();
		String type = daemonObject.getCategory();
		String yearDate = String.valueOf(daemonObject.getPubdate());
		String fullDate = daemonObject.getFulldate();
		String status = daemonObject.getPubstatus();
		String journal = daemonObject.getJournal();
		String volume = daemonObject.getVolume();
		String issue = daemonObject.getIssue();
		String pages = daemonObject.getPages();
		String abstractSection = daemonObject.getAbstract_();
		String externalLink = daemonObject.getExternalLinks();
		Boolean freefulltextAvailable = daemonObject.getFreeFullText();
		String notes = daemonObject.getNotes();
		String relativePath = daemonObject.getRelativePath();
		/*
		 * wrapper publications has sources
		 */
		Set<PublicationsHasPublicationsSource> publicationsHasSources = daemonObject.getPublicationsHasPublicationsSources();
		List<IPublicationExternalSourceLink> externalIDsSource_ = null;
		if (publicationsHasSources.size() > 0) {
			externalIDsSource_ = new ArrayList<IPublicationExternalSourceLink>();
			for (PublicationsHasPublicationsSource pubSource : publicationsHasSources) {
				IPublicationExternalSourceLink pubExternalAnote = PublicationsSourceWrapper.convertToAnoteStructure(pubSource);
				externalIDsSource_.add(pubExternalAnote);
			}
		}
		/*
		 * wrapper publications has fields
		 */
		Set<PublicationsFields> publicationsHasFields = daemonObject.getPublicationsFieldses();
		List<IPublicationField> fullTextfields_ = null;
		if (publicationsHasFields.size() > 0) {
			fullTextfields_ = new ArrayList<IPublicationField>();
			for (PublicationsFields pubField : publicationsHasFields) {
				IPublicationField pubFieldAnote = PublicationsFieldsWrapper.convertToAnoteStructure(pubField);
				fullTextfields_.add(pubFieldAnote);
			}
		}
		/*
		 * wrapper publications has labels
		 */
		Set<PublicationsHasPublicationLabels> publicationsHasLabels = daemonObject.getPublicationsHasPublicationLabelses();
		List<IPublicationLabel> labels_ = null;
		if (publicationsHasLabels.size() > 0) {
			labels_ = new ArrayList<IPublicationLabel>();
			for (PublicationsHasPublicationLabels pubLabel : publicationsHasLabels) {
				IPublicationLabel pubLabelAnote = PublicationsLabelsWrapper.convertToAnoteStructure(pubLabel.getPublicationsLabels());
				labels_.add(pubLabelAnote);
			}
		}
		/*
		 * create publication
		 */
		Publication publication = new Publication(id, title, authors, type, yearDate, fullDate, status, journal, volume, issue, pages, abstractSection, externalLink,
				freefulltextAvailable, notes, relativePath, externalIDsSource_, fullTextfields_, labels_);

		return publication;
	}

	public static Publications convertToDaemonStructure(IPublication anoteObject) {
		/*
		 * get publications parameters
		 */
		Long id = anoteObject.getID();
		String title = anoteObject.getTitle();
		String authors = anoteObject.getAuthors();
		String type = anoteObject.getType();
		String yearDate = anoteObject.getYearDate();
		String fullDate = anoteObject.getFullDate();
		String status = anoteObject.getStatus();
		String journal = anoteObject.getJournal();
		String volume = anoteObject.getVolume();
		String issue = anoteObject.getIssue();
		String pages = anoteObject.getPages();
		String abstractSection = anoteObject.getAbstractSection();
		String externalLink = anoteObject.getExternalLink();
		Boolean freefulltextAvailable = anoteObject.getAvailableFreeFullTExt();
		String notes = anoteObject.getNotes();
		String relativePath = anoteObject.getRelativePath();

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
