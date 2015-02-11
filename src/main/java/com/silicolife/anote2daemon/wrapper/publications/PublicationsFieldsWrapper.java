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

	public static IPublicationField convertToAnoteStructure(PublicationsFields daemonObject) {
		Long start = daemonObject.getFieldStart();
		Long end = daemonObject.getFieldEnd();
		String field = daemonObject.getId().getField();
		IPublicationField publicationField = new PublicationFieldImpl(start, end, field);
		return publicationField;
	}

	public static PublicationsFields convertToDaemonStructure(IPublicationField anoteObject, Publications publication) {
		Long start = anoteObject.getStart();
		Long end = anoteObject.getEnd();
		String name = anoteObject.getName();
		PublicationsFieldsId id = new PublicationsFieldsId(name, publication.getId());
		PublicationsFields publicationField = new PublicationsFields(id, publication, start, end);
		return publicationField;
	}
}
