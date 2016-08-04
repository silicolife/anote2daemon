package com.silicolife.anote2daemon.processes.ir;

import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.service.publications.IPublicationsService;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.service.queries.IQueriesService;
import com.silicolife.textmining.core.datastructures.exceptions.process.InvalidConfigurationException;
import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;
import com.silicolife.textmining.core.interfaces.core.document.IPublication;
import com.silicolife.textmining.core.interfaces.core.report.processes.ir.IIRSearchProcessReport;
import com.silicolife.textmining.core.interfaces.process.IR.IIRSearchConfiguration;
import com.silicolife.textmining.core.interfaces.process.IR.IQuery;
import com.silicolife.textmining.core.interfaces.process.IR.exception.InternetConnectionProblemException;
import com.silicolife.textmining.core.interfaces.process.utils.ISimpleTimeLeft;
import com.silicolife.textmining.processes.ir.pubmed.PubMedSearch;

/**
 * Server Extension of PubMedSearch. This class serves to implements in
 * PubMedSearch to execute in 
 * 
 * @author Hugo Costa
 *
 */
public class PubMedSearchServerRunExtension extends PubMedSearch {
	
	private long starttime;
	
	private IQueriesService queriesService;
	
	private IPublicationsService publicationsService;

	public PubMedSearchServerRunExtension(IQueriesService queriesService, IPublicationsService publicationsService) {
		super();
		this.queriesService=queriesService;
		this.publicationsService=publicationsService;
	}

	public IIRSearchProcessReport search(IIRSearchConfiguration searchConfiguration) throws ANoteException, InternetConnectionProblemException, InvalidConfigurationException {
		starttime = GregorianCalendar.getInstance().getTimeInMillis();
		return super.search(searchConfiguration);
	}

	public Set<IPublication> searchPublicationMetaInfoUsingPMID(List<String> pmids, ISimpleTimeLeft progress) throws InternetConnectionProblemException {
		return super.searchPublicationMetaInfoUsingPMID(pmids);
	}
	
	@Override
	protected void registerQueryOnDatabase(IQuery query) throws ANoteException {
		queriesService.create(query);
	}
	
	@Override
	protected Map<String, Long> getAllPublicationExternalIdFromSource(String source )throws ANoteException {
		return publicationsService.getAllPublicationsIdFromSource(source);
	}
	
	@Override
	protected Set<String> getQueryPublicationIDWithGivenSource(IQuery query,String str) throws ANoteException {
		return queriesService.getQueryPublicationsExternalIDFromSource(query.getId(),str);
	}
	
	@Override
	protected  void insertPublications(Set<IPublication> documentsToInsert)throws ANoteException {
		publicationsService.create(documentsToInsert);
	}
	
	@Override
	protected void insertQueryPublications(IQuery query,Set<IPublication> publicationToAdd) throws ANoteException {
		Set<Long> publicationsIds = new HashSet<Long>();
		for (IPublication pub : publicationToAdd)
			publicationsIds.add(pub.getId());
		queriesService.addPublicationsToQuery(query.getId(), publicationsIds);
	}
	

	protected void updateQueryOnDatabase(IQuery query) throws ANoteException {
		queriesService.update(query);
	}

	@Override
	/**
	 * Save 
	 * 
	 */
	protected void memoryAndProgress(int step, int total) {
		// To Save in Database
		long nowTime = GregorianCalendar.getInstance().getTimeInMillis();
		super.memoryAndProgress(step, total);
	}



}
