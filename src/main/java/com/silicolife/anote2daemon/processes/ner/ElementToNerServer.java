package com.silicolife.anote2daemon.processes.ner;

import java.util.ArrayList;
import java.util.Set;

import com.silicolife.anote2daemon.processes.resources.ResourceServerImpl;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.service.resources.IResourcesElementService;
import com.silicolife.textmining.core.datastructures.process.ner.ElementToNer;
import com.silicolife.textmining.core.datastructures.process.ner.ResourcesToNerAnote;
import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;
import com.silicolife.textmining.core.interfaces.resource.IResource;
import com.silicolife.textmining.core.interfaces.resource.IResourceElement;

@Deprecated
public class ElementToNerServer extends ElementToNer{

	private IResourcesElementService resourceService;
	
	public ElementToNerServer(IResourcesElementService resourceService, ResourcesToNerAnote resourceToNER, boolean termNormalization) throws ANoteException {
		super(resourceToNER, termNormalization);
		this.resourceService = resourceService;
	}
	
	public void processingINfo() throws ANoteException {

		for(int i=0;i<getResourceToNER().getList().size();i++)
		{
			Set<Long> selected = getResourceToNER().getList().get(i).getSelectedClassesID();
			Set<Long> all = getResourceToNER().getList().get(i).getAllClassesID();
			
			IResource<IResourceElement> resource = getResourceToNER().getList().get(i).getResource();
			resource = new ResourceServerImpl(resourceService, resource);
			if(selected.equals(all)){
				addResource(resource,isTermNormalization());
			}else{
				addResource(resource,new ArrayList<Long>(selected),isTermNormalization());
			}
		}
	}

}
