package com.silicolife.anote2daemon.processes.resources;

import java.util.List;
import java.util.Set;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.service.resources.IResourcesElementService;
import com.silicolife.textmining.core.datastructures.resources.ResourceElementSetImpl;
import com.silicolife.textmining.core.datastructures.resources.ResourceImpl;
import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;
import com.silicolife.textmining.core.interfaces.core.dataaccess.layer.resources.IResourceManagerReport;
import com.silicolife.textmining.core.interfaces.core.general.IExternalID;
import com.silicolife.textmining.core.interfaces.core.general.classe.IAnoteClass;
import com.silicolife.textmining.core.interfaces.resource.IResource;
import com.silicolife.textmining.core.interfaces.resource.IResourceElement;
import com.silicolife.textmining.core.interfaces.resource.IResourceElementSet;
import com.silicolife.textmining.core.interfaces.resource.content.IResourceContent;

public class ResourceServerImpl extends ResourceImpl{

	private IResourcesElementService resourceService;

	public ResourceServerImpl(IResourcesElementService resourceService, IResource<IResourceElement> resource){
		super(resource.getId(), resource.getName(),resource.getInfo(), resource.getType(), resource.isActive());
		this.resourceService = resourceService;
	}
	
	public IResourceElementSet<IResourceElement> getResourceElementsByName(String name) throws ANoteException{
		return resourceService.getResourceElementsByName(this.getId(), name);
	}
	
	public IResourceElementSet<IResourceElement> getResourceElementsByClass(IAnoteClass klass) throws ANoteException {
		return resourceService.getResourceElementsByClass(this.getId(), klass.getName());
	}
	
	public IResourceElementSet<IResourceElement> getResourceElements() throws ANoteException {
		int index = 0;
		int size = 100000;
		IResourceElementSet<IResourceElement> elements = new ResourceElementSetImpl<>();
		IResourceElementSet<IResourceElement> tempelements = resourceService.getResourceElementsInBatchWithLimit(this.getId(), index, size);
		while(tempelements.size() != 0){
			elements.addAllElementResource(tempelements.getResourceElements());
			index = index + size;
			tempelements = resourceService.getResourceElementsInBatchWithLimit(this.getId(), index, size);
		}
		return elements;
		//		return InitConfiguration.getDataAccess().getResourceElements(this);
//		return resourceService.getResourceElements(this.getId());
	}
	
	public Set<IAnoteClass> getResourceClassContent() throws ANoteException{
		return resourceService.getResourceClassContent(this.getId());
	}
	
	public IResourceElement getResourceElementByID(long termID) throws ANoteException{
		return resourceService.getResourceElemenByID(termID);
	}
	
	public synchronized IResourceContent getResourceContent() throws ANoteException{
		return resourceService.getResourceContent(this.getId());
	}
	
	public IResourceManagerReport addResourceElements(List<IResourceElement> elements) throws ANoteException{
		return resourceService.addResourceElements(this.getId(), elements);
	}
	
	public IResourceManagerReport updateResourceElement(IResourceElement elem) throws ANoteException {
		return resourceService.updateResourceElement(elem);
	}
	
	public void inactivateElement(IResourceElement elem) throws ANoteException{
		resourceService.inactivateResourceElement(elem.getId());
	}

	public void inactiveResourceElementElementsByClassID(long classID) throws ANoteException {
		resourceService.removeResourceClass(this.getId(), classID);
	}
	public void inactivateResourceElement(IResourceElement elem) throws ANoteException {
		resourceService.inactivateResourceElement(elem.getId());
	}
	
	public void removeResourceElementSynonyms(IResourceElement elem) throws ANoteException{
		resourceService.removeResourceElementSynonyms(elem.getId());
	}
	
	public void removeResourceElementSynonym(IResourceElement elem,String synonym) throws ANoteException{
		resourceService.removeResourceElementSynonym(elem.getId(), synonym);
	}
	public void inactiveElementsByClassID(long classID) throws ANoteException {
		resourceService.removeResourceClass(this.getId(), classID);		
	}
	
	public void updateElement(IResourceElement element) throws ANoteException{
		resourceService.updateResourceElement(element);
	}
	
	public boolean checkiftermalreadyexist(String term) throws ANoteException {
		return resourceService.checkResourceElementExistsInResource(this.getId(), term);
	}

	public IResourceManagerReport addExternalID(IResourceElement elem, List<IExternalID> externalIDs) throws ANoteException {
		return resourceService.addResourceElementExternalIDs(this.getId(),elem.getId(), externalIDs);
	}
	
	public boolean isFill() throws ANoteException {
		return resourceService.getResourceContent(this.getId()).getTermNumber()!=0;
	}
	
}
