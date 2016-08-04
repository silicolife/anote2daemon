package com.silicolife.anote2daemon.processes.resources;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.silicolife.textmining.core.datastructures.resources.lexiacalwords.LexicalWordsImpl;
import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;
import com.silicolife.textmining.core.interfaces.resource.IResource;
import com.silicolife.textmining.core.interfaces.resource.IResourceElement;

public class LexicalWordsServerImpl extends LexicalWordsImpl{
	
	private IResource<IResourceElement> serverResource;

	public LexicalWordsServerImpl(IResource<IResourceElement> serverResource){
		super(serverResource);
		this.serverResource = serverResource;
	}
	
	@JsonIgnore
	public synchronized  Map<String, IResourceElement> getLexicalWords() throws ANoteException {
		if(getLexicalWordElement()==null)
		{
			Map<String,IResourceElement> lexicalWordDatabaseIDMap = new HashMap<String, IResourceElement>();
			List<IResourceElement> elements = serverResource.getResourceElements().getAlphabeticOrder();
			for(IResourceElement elem:elements)
			{
				lexicalWordDatabaseIDMap.put(elem.getTerm(), elem);
			}
			setLexicalWordElement(lexicalWordDatabaseIDMap);
		}
		return getLexicalWordElement();
	}

}
