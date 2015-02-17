package com.silicolife.anote2daemon.wrapper.publications;

import pt.uminho.anote2.core.document.labels.IPublicationLabel;
import pt.uminho.anote2.datastructures.documents.lables.PublicationLabelImpl;

import com.silicolife.anote2daemon.model.core.entities.Publications;
import com.silicolife.anote2daemon.model.core.entities.PublicationsHasPublicationLabels;
import com.silicolife.anote2daemon.model.core.entities.PublicationsHasPublicationLabelsId;
import com.silicolife.anote2daemon.model.core.entities.PublicationsLabels;

/**
 * Class to transform anote2 Publications Labels structures to daemon
 * Publications Labels structures and vice-verse
 * 
 * @author Joel Azevedo Costa
 * @year 2015
 *
 */
public class PublicationsLabelsWrapper {

	public static IPublicationLabel convertToAnoteStructure(PublicationsLabels publicationsLabels) {
		Long labelId = publicationsLabels.getId();
		String labelDesc = publicationsLabels.getDescription();
		IPublicationLabel publicationsLabels_ = new PublicationLabelImpl(labelId, labelDesc);
		return publicationsLabels_;
	}

	public static PublicationsHasPublicationLabels convertToDaemonStructure(IPublicationLabel publicationsLabels_, Publications publication) {
		Long labelId = publicationsLabels_.getID();
		String labelDesc = publicationsLabels_.getName();
		PublicationsLabels pubLabel = new PublicationsLabels(labelId, labelDesc);
		PublicationsHasPublicationLabelsId id = new PublicationsHasPublicationLabelsId(labelId, publication.getId());
		PublicationsHasPublicationLabels pubHasPubLabels = new PublicationsHasPublicationLabels(id, pubLabel, publication);
		return pubHasPubLabels;
	}
}
