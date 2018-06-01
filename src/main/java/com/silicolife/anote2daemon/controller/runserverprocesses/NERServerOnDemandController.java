package com.silicolife.anote2daemon.controller.runserverprocesses;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.silicolife.textmining.core.datastructures.init.InitConfiguration;
import com.silicolife.textmining.core.datastructures.process.ProcessRunStatusConfigurationEnum;
import com.silicolife.textmining.core.datastructures.process.ner.NERCaseSensativeEnum;
import com.silicolife.textmining.core.datastructures.process.ner.ResourcesToNerAnote;
import com.silicolife.textmining.core.datastructures.resources.dictionary.loaders.DictionaryImpl;
import com.silicolife.textmining.core.datastructures.resources.lexiacalwords.LexicalWordsImpl;
import com.silicolife.textmining.core.datastructures.resources.ontology.OntologyImpl;
import com.silicolife.textmining.core.interfaces.core.annotation.IEntityAnnotation;
import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;
import com.silicolife.textmining.core.interfaces.resource.IResource;
import com.silicolife.textmining.core.interfaces.resource.IResourceElement;
import com.silicolife.textmining.core.interfaces.resource.dictionary.IDictionary;
import com.silicolife.textmining.core.interfaces.resource.lexicalwords.ILexicalWords;
import com.silicolife.textmining.core.interfaces.resource.ontologies.IOntology;
import com.silicolife.textmining.processes.ie.ner.linnaeus.LinnauesTaggerByDocumentSingleton;
import com.silicolife.textmining.processes.ie.ner.linnaeus.adapt.uk.ac.man.entitytagger.matching.Matcher.Disambiguation;
import com.silicolife.textmining.processes.ie.ner.linnaeus.configuration.INERLinnaeusConfiguration;
import com.silicolife.textmining.processes.ie.ner.linnaeus.configuration.NERLinnaeusConfigurationImpl;
import com.silicolife.textmining.processes.ie.ner.linnaeus.configuration.NERLinnaeusPreProcessingEnum;
import com.silicolife.textmining.processes.ie.schemas.export.AnnotationExportA1Format;
import com.silicolife.textmining.processes.ie.schemas.export.EntityAnnotationToAnnoationExportA1Format;

@RequestMapping(value = "/run", produces = { MediaType.APPLICATION_JSON_VALUE})
@ResponseBody
@Controller
public class NERServerOnDemandController{
	
	final static Logger logger = LoggerFactory.getLogger(NERServerOnDemandController.class);

//	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/ner/{textStream}", method = RequestMethod.GET)
	public ResponseEntity<List<AnnotationExportA1Format>> runner(@PathVariable String textStream){
		logger.info("NER Text Stream GET :"+textStream);
		try {
			textStream = URLDecoder.decode(textStream,"UTF-8");
			if(!LinnauesTaggerByDocumentSingleton.getInstance().isInit())
				LinnauesTaggerByDocumentSingleton.getInstance().initConfiguration(defaultConfiguration());
			List<IEntityAnnotation> entities = LinnauesTaggerByDocumentSingleton.getInstance().execute(textStream);
			logger.info("To Convert Result"+entities.size());
			List<AnnotationExportA1Format> out = EntityAnnotationToAnnoationExportA1Format.convert(entities);
			logger.info("Result "+out.size());
			return new ResponseEntity<List<AnnotationExportA1Format>>(out,HttpStatus.OK);
		} catch (IOException | ANoteException e) {
			logger.error(e.getClass().toString() + " " + e.getMessage());
		}
		return new ResponseEntity<List<AnnotationExportA1Format>>(new ArrayList<>(),HttpStatus.OK);
	}
	
//	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/ner", method = RequestMethod.POST,consumes = { "application/json" })
	public ResponseEntity<List<AnnotationExportA1Format>> runnerpost(@RequestBody String textStream){
		logger.info("NER Text Stream POST :"+textStream);
		try {
			if(!LinnauesTaggerByDocumentSingleton.getInstance().isInit())
				LinnauesTaggerByDocumentSingleton.getInstance().initConfiguration(defaultConfiguration());
			List<IEntityAnnotation> entities = LinnauesTaggerByDocumentSingleton.getInstance().execute(textStream);
			logger.info("To Convert Result "+entities.size());
			List<AnnotationExportA1Format> out = EntityAnnotationToAnnoationExportA1Format.convert(entities);
			logger.info("Result "+out.size());
			return new ResponseEntity<List<AnnotationExportA1Format>>(out,HttpStatus.OK);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return new ResponseEntity<List<AnnotationExportA1Format>>(new ArrayList<>(),HttpStatus.OK);
	}


	private INERLinnaeusConfiguration defaultConfiguration() throws ANoteException {
		// Resources
		// Metabolites
		IResource<IResourceElement> metabolitesResource = InitConfiguration.getDataAccess().getResourceByID(8262616692503304223L);
		IOntology metabolitesChebi = new OntologyImpl(metabolitesResource);
		// Reactions
		IResource<IResourceElement> reationsResource = InitConfiguration.getDataAccess().getResourceByID(7584523555104757364L);
		IDictionary reactionsSTangingare = new DictionaryImpl(reationsResource);
		
		// Enzymes
		IResource<IResourceElement> enzymesResource = InitConfiguration.getDataAccess().getResourceByID(1042574798892124490L);
		IDictionary enzymesBrenda = new DictionaryImpl(enzymesResource);

		// Organisms
		IResource<IResourceElement> linnauesSpeciesResource = InitConfiguration.getDataAccess().getResourceByID(3131738670925400260L);
		IDictionary linnauesSpecies = new DictionaryImpl(linnauesSpeciesResource);
		
		// Biomass
		IResource<IResourceElement> biomassResource = InitConfiguration.getDataAccess().getResourceByID(4201036964741837900L);
		IDictionary biomass = new DictionaryImpl(biomassResource);
		
		// Kinetic Parameters
		IResource<IResourceElement> kineticParametersResource = InitConfiguration.getDataAccess().getResourceByID(3015756573485045628L);
		IDictionary kineticParameters = new DictionaryImpl(kineticParametersResource);
		
		// Pubchem Subset
		IResource<IResourceElement> pubchemSubsetResource = InitConfiguration.getDataAccess().getResourceByID(2456123763781987374L);
		IDictionary pubchemSubset = new DictionaryImpl(pubchemSubsetResource);
		
		// Kegg Compounds
		IResource<IResourceElement> keggCompoundsResource = InitConfiguration.getDataAccess().getResourceByID(6313536102811386987L);
		IDictionary keggCompounds = new DictionaryImpl(keggCompoundsResource);
		
		// Diseases ( Ontology Diseases)
		IResource<IResourceElement> diseasesResource = InitConfiguration.getDataAccess().getResourceByID(180173838987053627L);
		IOntology diseasesDiseasesOntology= new OntologyImpl(diseasesResource);
		
		// Uniprot
		IResource<IResourceElement> uniprotProteinsResource = InitConfiguration.getDataAccess().getResourceByID(2083670216605005104L);
		IDictionary uniprotProteins = new DictionaryImpl(uniprotProteinsResource);
		
		// Kegg Genes - Escherichia coli
		IResource<IResourceElement> keggGenesEcoliResource = InitConfiguration.getDataAccess().getResourceByID(5932361953485982085L);
		IDictionary keggGenesEcoli = new DictionaryImpl(keggGenesEcoliResource);
		
		// Kegg Genes - Saccharomyces cerevisiae
		IResource<IResourceElement> keggGenesScerevisiaeResource = InitConfiguration.getDataAccess().getResourceByID(8441950794640514315L);
		IDictionary keggGenesScerevisiae = new DictionaryImpl(keggGenesScerevisiaeResource);
		
		// Configuraton

		NERCaseSensativeEnum caseSensitive = NERCaseSensativeEnum.INALLWORDS;
		IResource<IResourceElement> stopWords = InitConfiguration.getDataAccess().getResourceByID(3102121930612798438L);
		ILexicalWords stopwords = new LexicalWordsImpl(stopWords);
		NERLinnaeusPreProcessingEnum preprocessing = NERLinnaeusPreProcessingEnum.StopWords;
		int sizeOfSmallWordsToBeNotAnnotated = 2;
		ResourcesToNerAnote resourceToNER = new ResourcesToNerAnote(caseSensitive,false,sizeOfSmallWordsToBeNotAnnotated);
		resourceToNER.addUsingAnoteClasses(biomass, biomass.getResourceClassContent(), biomass.getResourceClassContent());
		resourceToNER.addUsingAnoteClasses(reactionsSTangingare, reactionsSTangingare.getResourceClassContent(), reactionsSTangingare.getResourceClassContent());
		resourceToNER.addUsingAnoteClasses(kineticParameters, kineticParameters.getResourceClassContent(), kineticParameters.getResourceClassContent());
		resourceToNER.addUsingAnoteClasses(keggGenesScerevisiae, keggGenesScerevisiae.getResourceClassContent(), keggGenesScerevisiae.getResourceClassContent());		
		resourceToNER.addUsingAnoteClasses(keggGenesEcoli, keggGenesEcoli.getResourceClassContent(), keggGenesEcoli.getResourceClassContent());
		resourceToNER.addUsingAnoteClasses(uniprotProteins, uniprotProteins.getResourceClassContent(), uniprotProteins.getResourceClassContent());
		resourceToNER.addUsingAnoteClasses(pubchemSubset, pubchemSubset.getResourceClassContent(), pubchemSubset.getResourceClassContent());
		resourceToNER.addUsingAnoteClasses(keggCompounds, keggCompounds.getResourceClassContent(), keggCompounds.getResourceClassContent());
		resourceToNER.addUsingAnoteClasses(diseasesDiseasesOntology, diseasesDiseasesOntology.getResourceClassContent(), diseasesDiseasesOntology.getResourceClassContent());
		resourceToNER.addUsingAnoteClasses(metabolitesChebi, metabolitesChebi.getResourceClassContent(), metabolitesChebi.getResourceClassContent());
		resourceToNER.addUsingAnoteClasses(enzymesBrenda, enzymesBrenda.getResourceClassContent(), enzymesBrenda.getResourceClassContent());
		resourceToNER.addUsingAnoteClasses(linnauesSpecies, linnauesSpecies.getResourceClassContent(), linnauesSpecies.getResourceClassContent());
		
		boolean usingOtherResourceInfoToImproveRuleAnnotations = false;
		boolean normalized = true;
		boolean useabreviation = true;
		int numThreads = 24;
		Disambiguation disambiguation = Disambiguation.OFF;
		Map<String, Pattern> patterns = null;
		
		INERLinnaeusConfiguration linnauesConfiguration = new NERLinnaeusConfigurationImpl(null, ProcessRunStatusConfigurationEnum.createnew, patterns, resourceToNER, useabreviation, 
				disambiguation, caseSensitive, normalized, numThreads, stopwords, preprocessing,
				usingOtherResourceInfoToImproveRuleAnnotations, sizeOfSmallWordsToBeNotAnnotated);
		return linnauesConfiguration;
	}
	

}
