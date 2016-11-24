package com.silicolife.anote2daemon.processes.ner;

import java.util.Iterator;
import java.util.NoSuchElementException;

import com.silicolife.anote2daemon.processes.corpus.AnnotationDocumentServerImpl;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.service.publications.IPublicationsService;
import com.silicolife.textmining.core.datastructures.documents.AnnotatedDocumentImpl;
import com.silicolife.textmining.core.datastructures.language.LanguageProperties;
import com.silicolife.textmining.core.datastructures.textprocessing.TermSeparator;
import com.silicolife.textmining.core.datastructures.utils.conf.GlobalNames;
import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;
import com.silicolife.textmining.core.interfaces.core.document.IAnnotatedDocument;
import com.silicolife.textmining.core.interfaces.core.document.IDocumentSet;
import com.silicolife.textmining.core.interfaces.core.document.IPublication;
import com.silicolife.textmining.core.interfaces.core.document.corpus.ICorpus;
import com.silicolife.textmining.core.interfaces.process.IE.IIEProcess;
import com.silicolife.textmining.processes.ie.ner.linnaeus.adapt.uk.ac.man.documentparser.dataholders.Document;
import com.silicolife.textmining.processes.ie.ner.linnaeus.adapt.uk.ac.man.documentparser.dataholders.Document.Text_raw_type;
import com.silicolife.textmining.processes.ie.ner.linnaeus.adapt.uk.ac.man.documentparser.input.DocumentIterator;

@Deprecated
public class PublicationServerIt implements DocumentIterator{
	
	protected Document nextDocument;
	protected Iterator<IPublication> documentIt;
	protected ICorpus corpus;
	protected IIEProcess process;

	private IPublicationsService publicationService;
	

	
	public PublicationServerIt(IPublicationsService publicationService,ICorpus corpus, IDocumentSet documentSet, IIEProcess process) throws ANoteException
	{
		this.publicationService=publicationService;
		this.corpus = corpus;
		documentIt = documentSet.iterator();
		this.process = process;
		fetchNext();
	}

	@Override
	public Document next() {
		if (nextDocument == null)
			throw new NoSuchElementException(LanguageProperties.getLanguageStream("pt.uminho.anote2.linnaeus.operation.err.nomoredocuments"));

		Document res = nextDocument;
		try {
			fetchNext();
		} catch (ANoteException e) {
			e.printStackTrace();
			return null;
		}
		return res;
	}

	@Override
	public void remove() {
		throw new IllegalStateException("not implemented");		
	}

	@Override
	public Iterator<Document> iterator() {
		return this;
	}

	
	public void skip() {
		if (!hasNext())
			throw new NoSuchElementException();

		try {
			fetchNext();
		} catch (ANoteException e) {
			e.printStackTrace();
		}
	}

	public boolean hasNext() {
		return nextDocument != null;
	}
	
	protected void fetchNext() throws ANoteException{
		nextDocument = null;
		if(documentIt.hasNext())
		{
			IPublication anoteDocument = documentIt.next();
			nextDocument = convertAnoteDocumentToLinnaeus(anoteDocument);
		}
		
	}

	
	protected Document convertAnoteDocumentToLinnaeus(IPublication anoteDocument) throws ANoteException {
		if(anoteDocument instanceof IAnnotatedDocument){
			AnnotationDocumentServerImpl annotationDocumentServerImpl = new AnnotationDocumentServerImpl(publicationService, (IAnnotatedDocument) anoteDocument);
			String rawText = annotationDocumentServerImpl.getDocumentAnnotationText();
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
			IAnnotatedDocument annotationDocument = new AnnotatedDocumentImpl(anoteDocument, process, corpus);
			AnnotationDocumentServerImpl annotationDocumentServerImpl = new AnnotationDocumentServerImpl(publicationService,annotationDocument);
			String rawText = annotationDocumentServerImpl.getDocumentAnnotationText();
			String documentText = rawText;
			if(process.getProperties().containsKey(GlobalNames.normalization))
			{
				if(Boolean.valueOf(process.getProperties().getProperty(GlobalNames.normalization)))
				{
					documentText = TermSeparator.termSeparator(rawText);
				}
			}
			return new Document(String.valueOf(annotationDocumentServerImpl.getId()), null, null, documentText,
					rawText, Text_raw_type.TEXT, annotationDocumentServerImpl.getYeardate(), null, null, null,
					annotationDocumentServerImpl.getVolume(), annotationDocumentServerImpl.getIssue(), annotationDocumentServerImpl.getPages(), null,null);
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
