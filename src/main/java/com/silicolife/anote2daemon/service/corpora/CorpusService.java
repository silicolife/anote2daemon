package com.silicolife.anote2daemon.service.corpora;

import java.util.List;

import pt.uminho.anote2.core.document.IPublication;
import pt.uminho.anote2.core.document.corpus.ICorpus;
import pt.uminho.anote2.process.IE.IIEProcess;

/**
 * 
 * Interface to define all methods of Service layer about corpus
 * 
 * @author Joel Azevedo Costa
 * @year 2015
 *
 */
public interface CorpusService {
	/**
	 * Get all corpus
	 * 
	 * @return
	 */
	public List<ICorpus> getAllCorpus();
	
	/**
	 * Get Corpus by id
	 * 
	 * @param corpusId
	 * @return
	 */
	public ICorpus getCorpusByID(Long id);
	
	/**
	 * Create a new Corpus
	 * 
	 * @param corpus-
	 */
	public Boolean createCorpus(ICorpus corpus_);
	
	/**
	 * Update corpus
	 * 
	 * @param corpus_
	 * @return
	 */
	public boolean updateCorpus(ICorpus corpus_);
	
	/**
	 * Get all publications from a corpus
	 * 
	 * @param corpusId
	 * @return
	 */
	public List<IPublication> getCorpusPublications(Long corpusId);
	
	/**
	 * Get processes from a corpus
	 * 
	 * @param corpusId
	 * @return
	 */
	public List<IIEProcess> getCorpusProcesses(Long corpusId);
	
	/**
	 * Register a corpus to a process
	 * 
	 * @param corpusId
	 * @param processId
	 * @return
	 */
	public Boolean registerCoprusProcess(Long corpusId, Long processId);
	
	
}
