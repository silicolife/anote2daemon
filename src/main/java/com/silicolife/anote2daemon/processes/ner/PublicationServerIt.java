package com.silicolife.anote2daemon.processes.ner;

import com.silicolife.textmining.core.datastructures.documents.AnnotatedDocumentImpl;
import com.silicolife.textmining.core.datastructures.textprocessing.TermSeparator;
import com.silicolife.textmining.core.datastructures.utils.conf.GlobalNames;
import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;
import com.silicolife.textmining.core.interfaces.core.document.IAnnotatedDocument;
import com.silicolife.textmining.core.interfaces.core.document.IDocumentSet;
import com.silicolife.textmining.core.interfaces.core.document.IPublication;
import com.silicolife.textmining.core.interfaces.core.document.corpus.ICorpus;
import com.silicolife.textmining.core.interfaces.process.IE.IIEProcess;
import com.silicolife.textmining.processes.ie.ner.linnaeus.PublicationIt;
import com.silicolife.textmining.processes.ie.ner.linnaeus.adapt.uk.ac.man.documentparser.dataholders.Document;
import com.silicolife.textmining.processes.ie.ner.linnaeus.adapt.uk.ac.man.documentparser.dataholders.Document.Text_raw_type;

public class PublicationServerIt extends PublicationIt{

	public PublicationServerIt(ICorpus corpus, IDocumentSet documentSet, IIEProcess process) throws ANoteException {
		super(corpus, documentSet, process);
	}
	
	@Override
	protected Document convertAnoteDocumentToLinnaeus(IPublication anoteDocument) throws ANoteException {
		if(anoteDocument instanceof IAnnotatedDocument){
			String rawText = ((IAnnotatedDocument) anoteDocument).getDocumentAnnotationText();
			String documentText = rawText;
			if(process.getProperties().containsKey(GlobalNames.normalization))
			{
				if(Boolean.valueOf(process.getProperties().getProperty(GlobalNames.normalization)))
				{
					documentText = TermSeparator.termSeparator(rawText);
				}
			}
			return new Document(String.valueOf(anoteDocument.getId()),null , null, documentText, rawText,
					Text_raw_type.TEXT, ((IAnnotatedDocument) anoteDocument).getYeardate(), null, null, null, ((IAnnotatedDocument) anoteDocument).getVolume(),
					((IAnnotatedDocument) anoteDocument).getIssue(), ((IAnnotatedDocument) anoteDocument).getPages(), null, null);
		}

		if(anoteDocument instanceof IPublication)
		{
			IAnnotatedDocument annotDOc = new AnnotatedDocumentImpl(anoteDocument,process, corpus);
			String rawText = annotDOc.getDocumentAnnotationText();
			String documentText = rawText;
			if(process.getProperties().containsKey(GlobalNames.normalization))
			{
				if(Boolean.valueOf(process.getProperties().getProperty(GlobalNames.normalization)))
				{
					documentText = TermSeparator.termSeparator(rawText);
				}
			}
			return new Document(String.valueOf(annotDOc.getId()), null, null, documentText,
					rawText, Text_raw_type.TEXT, annotDOc.getYeardate(), null, null, null,
					annotDOc.getVolume(), annotDOc.getIssue(), annotDOc.getPages(), null,null);
		}
		else{
			String rawText = anoteDocument.getFullTextContent();
			String documentText = rawText;
			if(process.getProperties().containsKey(GlobalNames.normalization))
			{
				if(Boolean.valueOf(process.getProperties().getProperty(GlobalNames.normalization)))
				{
					documentText = TermSeparator.termSeparator(rawText);
				}
			}
			return new Document(String.valueOf(anoteDocument.getId()), null, null, documentText, rawText, Text_raw_type.TEXT,
					null, null, null, null, null, null, null, null, null);
		}
	}

}
