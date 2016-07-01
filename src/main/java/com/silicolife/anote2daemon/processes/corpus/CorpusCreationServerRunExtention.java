package com.silicolife.anote2daemon.processes.corpus;

import java.util.GregorianCalendar;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import com.silicolife.textmining.core.datastructures.corpora.CorpusImpl;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.service.corpora.ICorpusService;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.service.publications.IPublicationsService;
import com.silicolife.textmining.core.datastructures.report.corpora.CorpusCreateReportImpl;
import com.silicolife.textmining.core.datastructures.utils.conf.GlobalNames;
import com.silicolife.textmining.core.datastructures.utils.conf.GlobalOptions;
import com.silicolife.textmining.core.interfaces.core.corpora.ICorpusCreateConfiguration;
import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;
import com.silicolife.textmining.core.interfaces.core.document.IPublication;
import com.silicolife.textmining.core.interfaces.core.document.corpus.CorpusTextType;
import com.silicolife.textmining.core.interfaces.core.document.corpus.ICorpus;
import com.silicolife.textmining.core.interfaces.core.report.corpora.ICorpusCreateReport;
import com.silicolife.textmining.processes.corpora.loaders.CorpusCreation;

public class CorpusCreationServerRunExtention extends CorpusCreation {
	
	private long starttime;

	private ICorpusService corpusService;
	private IPublicationsService publictionService;
	
	public CorpusCreationServerRunExtention(ICorpusService corpusService,IPublicationsService publictionService)
	{
		super();
		this.corpusService=corpusService;
		this.publictionService=publictionService;
	}
	
	@Override
	public ICorpusCreateReport createCorpus(ICorpusCreateConfiguration configuration) throws ANoteException
	{
		starttime = GregorianCalendar.getInstance().getTimeInMillis();
		Properties properties = configuration.getProperties();
		properties.put(GlobalNames.textType, CorpusTextType.convertCorpusTetTypeToString(configuration.getCorpusTextType()));
		ICorpus newCorpus = new CorpusImpl(configuration.getCorpusName(), configuration.getCorpusNotes(), configuration.getProperties());
		createCorpusOnDatabase(newCorpus);
		Set<Long> documentsIDs = configuration.getDocumentsIDs();
		int step = 0;
		int total = documentsIDs.size();
		for(Long publicationID:documentsIDs)
		{
			// Before connect ... insert document if not exists
			IPublication publication = getPublicationOnDatabaseByID(publicationID);
			if(publication!=null)
			{
				corpusService.addCorpusPublication(newCorpus.getId(), publicationID);
			}
			step++;
			if(step%10==0)
				memoryAndProgress(step,total);
		}

		ICorpusCreateReport report = new CorpusCreateReportImpl(newCorpus, configuration.getCorpusTextType(),configuration.getDocumentsIDs().size());
		return report;
	}
	
	@Override
	protected void createCorpusOnDatabase(ICorpus newCorpus)throws ANoteException {
		corpusService.createCorpus(newCorpus);
	}
	
	@Override
	protected IPublication getPublicationOnDatabaseByID(Long publictionID)throws ANoteException {
		return publictionService.getById(publictionID);
	}
	
	@Override
	protected void addPublicationToDatabase(List<IPublication> documentToadd)throws ANoteException {
		publictionService.create(documentToadd);
	}
	
	
	@Override
	protected void memoryAndProgress(int step, int total) {
		if(step%1000==0)
		{
			System.out.println((GlobalOptions.decimalformat.format((double)step/ (double) total * 100)) + " %...");
		}
	}

}
