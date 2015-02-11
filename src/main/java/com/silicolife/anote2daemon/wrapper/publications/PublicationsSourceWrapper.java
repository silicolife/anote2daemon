package com.silicolife.anote2daemon.wrapper.publications;

import pt.uminho.anote2.core.document.IPublicationExternalSourceLink;
import pt.uminho.anote2.datastructures.documents.PublicationExternalSourceLinkImpl;

import com.silicolife.anote2daemon.model.core.entities.Publications;
import com.silicolife.anote2daemon.model.core.entities.PublicationsHasPublicationsSource;
import com.silicolife.anote2daemon.model.core.entities.PublicationsHasPublicationsSourceId;
import com.silicolife.anote2daemon.model.core.entities.PublicationsSource;

/**
 * Wrapper to handler with transformations of publications source structures 
 * 
 * @author Joel Azevedo Costa
 * @year 2015
 *
 */
public class PublicationsSourceWrapper {

	public static PublicationExternalSourceLinkImpl convertToAnoteStructure(PublicationsHasPublicationsSource pubHasPubSource) {
		Long sourceId = pubHasPubSource.getId().getPublicationsSourceId();
		String sourceInternalId = pubHasPubSource.getId().getPublicationsSourceInternalId();
		String sourceDesc = pubHasPubSource.getPublicationsSource().getDescription();
		PublicationExternalSourceLinkImpl pubExternal_ = new PublicationExternalSourceLinkImpl(sourceInternalId, sourceId, sourceDesc);
		return pubExternal_;
	}

	public static PublicationsHasPublicationsSource convertToDaemonStructure(IPublicationExternalSourceLink pubExternal_, Publications publication) {
		Long sourceId = pubExternal_.getSourceID();
		String sourceInternalId = pubExternal_.getSourceInternalID();
		String sourceDesc = pubExternal_.getSource();
		PublicationsSource pubSource = new PublicationsSource(sourceId, sourceDesc);
		PublicationsHasPublicationsSourceId id = new PublicationsHasPublicationsSourceId(sourceId, sourceInternalId);
		PublicationsHasPublicationsSource pubHasPubSource = new PublicationsHasPublicationsSource(id, pubSource, publication);

		return pubHasPubSource;
	}

}
