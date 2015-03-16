package com.silicolife.anote2daemon.wrapper.corpora;

import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import pt.uminho.anote2.core.document.corpus.ICorpus;

import com.silicolife.anote2daemon.model.core.entities.Corpus;
import com.silicolife.anote2daemon.model.core.entities.CorpusProperties;

/**
 * Class to transform anote2 Corpus structures to daemon Corpus structures and
 * vice-verse
 * 
 * @author Joel Azevedo Costa
 * @year 2015
 *
 */
public class CorpusWrapper {

	public static ICorpus convertToAnoteStructure(Corpus corpus) {
		Long id = corpus.getId();
		String name = corpus.getCorpusName();
		String notes = corpus.getNotes();
		Set<CorpusProperties> corpusProperties = corpus.getCorpusPropertieses();
		/**
		 * create properties
		 */
		Properties properties = CorpusPropertiesWrapper.convertToAnoteStructure(corpusProperties);
		ICorpus corpus_ = new pt.uminho.anote2.datastructures.corpora.Corpus(id, name, notes, properties);

		return corpus_;
	}

	public static Corpus convertToDaemonStructure(ICorpus corpus_) {
		Long id = corpus_.getId();
		String name = corpus_.getDescription();
		String notes = corpus_.getNotes();
		Set<CorpusProperties> corpusProperties = new HashSet<CorpusProperties>(0);
		Properties properties = corpus_.getProperties();
		Boolean active = true;
		Corpus corpus = new Corpus(id, name, active);
		if (properties != null) {
			corpusProperties = CorpusPropertiesWrapper.convertToDaemonStructure(properties, corpus);
		}
		corpus.setNotes(notes);
		corpus.setCorpusPropertieses(corpusProperties);

		return corpus;
	}
}
