package com.silicolife.anote2daemon.model.core.dao.specialdao;

import java.util.List;

import com.silicolife.anote2daemon.model.core.entities.Publications;

public interface PublicationsAuxDao {

	public List<Publications> findPublicationsByQueryId(Long queryId);

	public List<Publications> findPublicationsByCorpusId(Long corpusId);

	public Publications getPublicationFullText(Long publicationId);
}
