package com.silicolife.anote2daemon.wrapper.publications;

import pt.uminho.anote2.core.document.structure.IPublicationField;
import pt.uminho.anote2.datastructures.documents.structure.PublicationFieldImpl;

import com.silicolife.anote2daemon.model.core.entities.Publications;
import com.silicolife.anote2daemon.model.core.entities.PublicationsFields;
import com.silicolife.anote2daemon.model.core.entities.PublicationsFieldsId;

/**
 * Wrapper to handler with transformations of publications fields structures 
 * 
 * @author Joel Azevedo Costa
 * @year 2015
 *
 */
public class PublicationsFieldsWrapper {

	public static IPublicationField convertToAnoteStructure(PublicationsFields publicationsFields) {
		Long start = publicationsFields.getFieldStart();
		Long end = publicationsFields.getFieldEnd();
		String field = publicationsFields.getId().getField();
		IPublicationField publicationsFields_ = new PublicationFieldImpl(start, end, field);
		return publicationsFields_;
	}

	public static PublicationsFields convertToDaemonStructure(IPublicationField publicationsFields_, Publications publication) {
		Long start = publicationsFields_.getStart();
		Long end = publicationsFields_.getEnd();
		String name = publicationsFields_.getName();
		PublicationsFieldsId id = new PublicationsFieldsId(name, publication.getId());
		PublicationsFields publicationField = new PublicationsFields(id, publication, start, end);
		return publicationField;
	}
}
