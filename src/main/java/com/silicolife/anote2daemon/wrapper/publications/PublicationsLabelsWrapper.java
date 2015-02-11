package com.silicolife.anote2daemon.wrapper.publications;

import pt.uminho.anote2.core.document.labels.IPublicationLabel;
import pt.uminho.anote2.datastructures.documents.lables.PublicationLabelImpl;

import com.silicolife.anote2daemon.model.core.entities.Publications;
import com.silicolife.anote2daemon.model.core.entities.PublicationsHasPublicationLabels;
import com.silicolife.anote2daemon.model.core.entities.PublicationsHasPublicationLabelsId;
import com.silicolife.anote2daemon.model.core.entities.PublicationsLabels;

/**
 * Wrapper to handler with transformations of publications labels structures 
 * 
 * @author Joel Azevedo Costa
 * @year 2015
 *
 */
public class PublicationsLabelsWrapper {

	public static IPublicationLabel convertToAnoteStructure(PublicationsLabels parameter) {
		Long labelId = parameter.getId();
		String labelDesc = parameter.getDescription();
		IPublicationLabel pubLabel = new PublicationLabelImpl(labelId, labelDesc);
		return pubLabel;
	}

	public static PublicationsHasPublicationLabels convertToDaemonStructure(IPublicationLabel parameter, Publications publication) {
		Long labelId = parameter.getID();
		String labelDesc = parameter.getName();
		PublicationsLabels pubLabel = new PublicationsLabels(labelId, labelDesc);
		PublicationsHasPublicationLabelsId id = new PublicationsHasPublicationLabelsId(labelId, publication.getId());
		PublicationsHasPublicationLabels pubHasPubLabels = new PublicationsHasPublicationLabels(id, pubLabel, publication);
		return pubHasPubLabels;
	}
}
