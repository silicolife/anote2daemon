package com.silicolife.anote2daemon.model.core;

/**
 * Enum which represents the relevance type
 * 
 * @author Hugo Costa
 *
 */
public enum RelevanceType {
	none, irrelevant, related, relevant;

	public static RelevanceType convertString(String string) {
		if (string == null) {
			return RelevanceType.none;
		}
		string = string.toLowerCase();
		if (string.equals("irrelevant")) {
			return RelevanceType.irrelevant;
		} else if (string.equals("related")) {
			return RelevanceType.related;
		} else if (string.equals("relevant")) {
			return RelevanceType.relevant;
		}
		return RelevanceType.none;
	}
}