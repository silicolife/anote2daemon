package com.silicolife.anote2daemon.processes.corpus;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.service.publications.IPublicationsService;
import com.silicolife.textmining.core.datastructures.documents.PublicationImpl;
import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;
import com.silicolife.textmining.core.interfaces.core.document.IPublication;

@Deprecated
public class PublicationServerImpl extends PublicationImpl{
	
	private IPublicationsService publicationService;

	
	public PublicationServerImpl(IPublicationsService publicationService,IPublication pub) {
		super(pub.getId(), pub.getTitle(), pub.getAuthors(), pub.getType(), pub.getYeardate(), pub.getFulldate(), pub.getStatus(), pub.getJournal(), pub.getVolume(),
				pub.getIssue(), pub.getPages(), pub.getAbstractSection(), pub.getExternalLink(), pub.isFreeFullText(), pub.getNotes(), pub.getRelativePath(), pub
						.getPublicationExternalIDSource(), pub.getPublicationFields(), pub.getPublicationLabels());
		setSourceURL(pub.getSourceURL());
		this.publicationService=publicationService;
	}
	
	
	
	@JsonIgnore
	@Override
	public String getFullTextContent() {
		if(fullTextContent==null)
		{
			try {
				fullTextContent = publicationService.getFullText(getId());
			} catch (ANoteException e) {
				fullTextContent = null;
			}
			if(fullTextContent==null)
			{
				return new String();
			}
		}
		return fullTextContent;
	}

}
