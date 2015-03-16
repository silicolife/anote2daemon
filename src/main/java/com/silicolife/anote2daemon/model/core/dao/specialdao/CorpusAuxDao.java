package com.silicolife.anote2daemon.model.core.dao.specialdao;

import java.util.List;

import com.silicolife.anote2daemon.model.core.entities.Processes;

public interface CorpusAuxDao {

	public List<Processes> findProcessesByCorpusId(Long corpusId);
}
