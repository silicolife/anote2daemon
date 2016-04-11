package com.silicolife.anote2daemon.processes.ir;

import java.util.GregorianCalendar;
import java.util.List;

import com.silicolife.textmining.core.datastructures.exceptions.process.InvalidConfigurationException;
import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;
import com.silicolife.textmining.core.interfaces.core.document.IPublication;
import com.silicolife.textmining.core.interfaces.core.report.processes.ir.IIRSearchProcessReport;
import com.silicolife.textmining.core.interfaces.process.IR.IIRSearchConfiguration;
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

	public PubMedSearchServerRunExtension() {
		super();
	}

	public IIRSearchProcessReport search(IIRSearchConfiguration searchConfiguration) throws ANoteException, InternetConnectionProblemException, InvalidConfigurationException {
		starttime = GregorianCalendar.getInstance().getTimeInMillis();
		return super.search(searchConfiguration);
	}

	public List<IPublication> searchPublicationMetaInfoUsingPMID(List<String> pmids, ISimpleTimeLeft progress) throws InternetConnectionProblemException {
		return super.searchPublicationMetaInfoUsingPMID(pmids);
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
