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

	public static PublicationExternalSourceLinkImpl convertToAnoteStructure(PublicationsHasPublicationsSource daemonObject) {
		Long sourceId = daemonObject.getId().getPublicationsSourceId();
		String sourceInternalId = daemonObject.getId().getPublicationsSourceInternalId();
		String sourceDesc = daemonObject.getPublicationsSource().getDescription();
		PublicationExternalSourceLinkImpl pubExternal = new PublicationExternalSourceLinkImpl(sourceInternalId, sourceId, sourceDesc);
		return pubExternal;
	}

	public static PublicationsHasPublicationsSource convertToDaemonStructure(IPublicationExternalSourceLink anoteObject, Publications publication) {
		Long sourceId = anoteObject.getSourceID();
		String sourceInternalId = anoteObject.getSourceInternalID();
		String sourceDesc = anoteObject.getSource();
		PublicationsSource pubSource = new PublicationsSource(sourceId, sourceDesc);
		PublicationsHasPublicationsSourceId id = new PublicationsHasPublicationsSourceId(sourceId, sourceInternalId);
		PublicationsHasPublicationsSource pubHasPubSource = new PublicationsHasPublicationsSource(id, pubSource, publication);

		return pubHasPubSource;
	}

}
