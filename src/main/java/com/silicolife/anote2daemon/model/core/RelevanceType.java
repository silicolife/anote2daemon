package com.silicolife.anote2daemon.model.core;

/**
 * Enum which represents the relevance type
 * 
 * @author Hugo Costa
 *
 */
public enum RelevanceType {
	none, Irrelevant, Related, Relevant;
	
	public static RelevanceType convertString(String string) {
		if(string== null)
		{
			return RelevanceType.none;
		}
		if(string.equals("Irrelevant"))
		{
			return RelevanceType.Irrelevant;
		}
		else if(string.equals("Related"))
		{
			return RelevanceType.Related;
		}
		else if(string.equals("Relevant"))
		{
			return RelevanceType.Relevant;
		}
		return RelevanceType.none;
	}
}