package com.silicolife.anote2daemon.processes.re;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import com.silicolife.textmining.core.datastructures.general.ClassPropertiesManagement;
import com.silicolife.textmining.core.datastructures.init.InitConfiguration;
import com.silicolife.textmining.core.datastructures.process.ProcessRunStatusConfigurationEnum;
import com.silicolife.textmining.core.datastructures.process.ner.NERCaseSensativeEnum;
import com.silicolife.textmining.core.datastructures.process.ner.ResourcesToNerAnote;
import com.silicolife.textmining.core.datastructures.resources.dictionary.loaders.DictionaryImpl;
import com.silicolife.textmining.core.datastructures.resources.lexiacalwords.LexicalWordsImpl;
import com.silicolife.textmining.core.datastructures.resources.ontology.OntologyImpl;
import com.silicolife.textmining.core.datastructures.resources.rule.RuleSetImpl;
import com.silicolife.textmining.core.datastructures.utils.Utils;
import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;
import com.silicolife.textmining.core.interfaces.core.general.classe.IAnoteClass;
import com.silicolife.textmining.core.interfaces.process.IE.IIEProcess;
import com.silicolife.textmining.core.interfaces.resource.IResource;
import com.silicolife.textmining.core.interfaces.resource.IResourceElement;
import com.silicolife.textmining.core.interfaces.resource.dictionary.IDictionary;
import com.silicolife.textmining.core.interfaces.resource.lexicalwords.ILexicalWords;
import com.silicolife.textmining.core.interfaces.resource.ontologies.IOntology;
import com.silicolife.textmining.core.interfaces.resource.rules.IRuleSet;
import com.silicolife.textmining.processes.ie.ner.linnaeus.adapt.uk.ac.man.entitytagger.matching.Matcher.Disambiguation;
import com.silicolife.textmining.processes.ie.ner.linnaeus.configuration.INERLinnaeusConfiguration;
import com.silicolife.textmining.processes.ie.ner.linnaeus.configuration.NERLinnaeusConfigurationImpl;
import com.silicolife.textmining.processes.ie.ner.linnaeus.configuration.NERLinnaeusPreProcessingEnum;
import com.silicolife.textmining.processes.ie.pipelines.kineticparameters.OrganismKineticInformationExportToFile;
import com.silicolife.textmining.processes.ie.pipelines.kineticparameters.configuration.IKineticREPipelineConfiguration;
import com.silicolife.textmining.processes.ie.re.kineticre.configuration.IREKineticAdvancedConfiguration;
import com.silicolife.textmining.processes.ie.re.kineticre.configuration.IREKineticREConfiguration;
import com.silicolife.textmining.processes.ie.re.kineticre.configuration.REKineticAdvancedConfigurationImpl;
import com.silicolife.textmining.processes.ie.re.kineticre.configuration.REKineticConfigurationImpl;
import com.silicolife.textmining.processes.ie.re.kineticre.core.REKineticConfigurationClasses;
import com.silicolife.textmining.processes.ie.re.kineticre.io.IREKineticREResultsExportConfiguration;
import com.silicolife.textmining.processes.ie.re.kineticre.io.REKineticREResultsExportConfigurationImpl;

public class OrganismKineticInformationExportToFileExtension extends OrganismKineticInformationExportToFile{
	
	private final static String exportDir = "home/hcosta/kineticrepositories/";
	
	public OrganismKineticInformationExportToFileExtension(IKineticREPipelineConfiguration configuration) throws ANoteException {
		super(getProcesses2(), getLinnaeusConfiguration(), getKineticREConfiguration(), getExportConfiguration(configuration));
	}
	
	
	private static Set<IIEProcess> getProcesses2() throws ANoteException
	{
		Set<IIEProcess> processes = new HashSet<>();
		InitConfiguration.getDataAccess().getProcessByID(4773474653848955523L);
		InitConfiguration.getDataAccess().getProcessByID(7110377322269597560L);
		return processes;
	}
	
	private static INERLinnaeusConfiguration getLinnaeusConfiguration() throws ANoteException
	{
		// Resources
		// Metabolites
		IResource<IResourceElement> metabolitesResource = InitConfiguration.getDataAccess().getResourceByID(8262616692503304223L);
		IOntology metabolitesChebi = new OntologyImpl(metabolitesResource);

		// Enzymes
		IResource<IResourceElement> enzymesResource = InitConfiguration.getDataAccess().getResourceByID(1042574798892124490L);
		IDictionary enzymesBrenda = new DictionaryImpl(enzymesResource);

		// Organisms
		IResource<IResourceElement> linnauesSpeciesResource = InitConfiguration.getDataAccess().getResourceByID(3131738670925400260L);
		IDictionary linnauesSpecies = new DictionaryImpl(linnauesSpeciesResource);
		
		// Kinetic Parameters
		IResource<IResourceElement> kineticParametersResource = InitConfiguration.getDataAccess().getResourceByID(3447837329161962922L);
		IDictionary kineticParameters = new DictionaryImpl(kineticParametersResource);
		
		// Kinetic Parameters
		IResource<IResourceElement> unitsResource = InitConfiguration.getDataAccess().getResourceByID(8581920124520136135L);
		IDictionary units = new DictionaryImpl(unitsResource);
		
		IResource<IResourceElement> numericUnitsRuleResource = InitConfiguration.getDataAccess().getResourceByID(7857542676539092817L);
		IRuleSet numericUnitsRule = new RuleSetImpl(numericUnitsRuleResource);

		// Configuraton

		NERCaseSensativeEnum caseSensitive = NERCaseSensativeEnum.ONLYINSMALLWORDS;
		IResource<IResourceElement> stopWords = InitConfiguration.getDataAccess().getResourceByID(3102121930612798438L);
		ILexicalWords stopwords = new LexicalWordsImpl(stopWords);
		NERLinnaeusPreProcessingEnum preprocessing = NERLinnaeusPreProcessingEnum.StopWords;
		int ignoretermswithlowerequalsthan = 2;
		ResourcesToNerAnote resourceToNER = new ResourcesToNerAnote(caseSensitive,false,ignoretermswithlowerequalsthan);
		resourceToNER.addUsingAnoteClasses(metabolitesChebi, metabolitesChebi.getResourceClassContent(), metabolitesChebi.getResourceClassContent());
		resourceToNER.addUsingAnoteClasses(enzymesBrenda, enzymesBrenda.getResourceClassContent(), enzymesBrenda.getResourceClassContent());
		resourceToNER.addUsingAnoteClasses(linnauesSpecies, linnauesSpecies.getResourceClassContent(), linnauesSpecies.getResourceClassContent());
		resourceToNER.addUsingAnoteClasses(kineticParameters, kineticParameters.getResourceClassContent(), kineticParameters.getResourceClassContent());
		resourceToNER.addUsingAnoteClasses(units, units.getResourceClassContent(), units.getResourceClassContent());
		resourceToNER.addUsingAnoteClasses(numericUnitsRule, numericUnitsRule.getResourceClassContent(), numericUnitsRule.getResourceClassContent());

		boolean usingOtherResourceInfoToImproveRuleAnnotations = false;
		boolean normalized = true;
		boolean useabreviation = true;
		int numThreads = 2;
		Disambiguation disambiguation = Disambiguation.OFF;
		Map<String, Pattern> patterns = null;
		INERLinnaeusConfiguration conf = new NERLinnaeusConfigurationImpl(null,ProcessRunStatusConfigurationEnum.createnew, patterns , resourceToNER, useabreviation , disambiguation , caseSensitive , normalized, numThreads , stopwords, preprocessing , usingOtherResourceInfoToImproveRuleAnnotations,ignoretermswithlowerequalsthan);
		return conf;
	}
	
	private static IREKineticREConfiguration getKineticREConfiguration() throws ANoteException
	{
		// Mapeamento feito pelo utilizador: entre as classes usadas no NER e as que precisa pra o RE
		Set<IAnoteClass> units = new HashSet<IAnoteClass>();
		units.add(ClassPropertiesManagement.getClassIDClassName("units"));
		units.add(ClassPropertiesManagement.getClassIDClassName("unit_rule"));
		Set<IAnoteClass> values = new HashSet<IAnoteClass>();
		values.add(ClassPropertiesManagement.getClassIDClassName("value_rule"));
		Set<IAnoteClass> kineticParameters = new HashSet<IAnoteClass>();
		kineticParameters.add(ClassPropertiesManagement.getClassIDClassName("Kparameters"));
		Set<IAnoteClass> metabolites = new HashSet<IAnoteClass>();
		metabolites.add(ClassPropertiesManagement.getClassIDClassName("metabolite"));
		Set<IAnoteClass> enzymes = new HashSet<IAnoteClass>();
		enzymes.add(ClassPropertiesManagement.getClassIDClassName("Enzyme"));
		enzymes.add(ClassPropertiesManagement.getClassIDClassName("enzyme_rule"));

		Set<IAnoteClass> organism = new HashSet<IAnoteClass>();
		organism.add(ClassPropertiesManagement.getClassIDClassName("Organism"));
		
		REKineticConfigurationClasses classes = new REKineticConfigurationClasses(units, values, kineticParameters, metabolites, enzymes, organism);
		IREKineticAdvancedConfiguration advanced = new REKineticAdvancedConfigurationImpl();
		IREKineticREConfiguration configuration = new REKineticConfigurationImpl(null,null, ProcessRunStatusConfigurationEnum.createnew, classes,advanced );
		return configuration;
	}

	private static IREKineticREResultsExportConfiguration getExportConfiguration(IKineticREPipelineConfiguration configuration2) throws ANoteException {
		// Mapeamento feito pelo utilizador: entre as classes usadas no NER e as que precisa pra o RE
		Set<IAnoteClass> units = new HashSet<IAnoteClass>();
		units.add(ClassPropertiesManagement.getClassIDClassName("units"));
		units.add(ClassPropertiesManagement.getClassIDClassName("unit_rule"));
		Set<IAnoteClass> values = new HashSet<IAnoteClass>();
		values.add(ClassPropertiesManagement.getClassIDClassName("value_rule"));
		Set<IAnoteClass> kineticParameters = new HashSet<IAnoteClass>();
		kineticParameters.add(ClassPropertiesManagement.getClassIDClassName("Kparameters"));
		Set<IAnoteClass> metabolites = new HashSet<IAnoteClass>();
		metabolites.add(ClassPropertiesManagement.getClassIDClassName("metabolite"));
		Set<IAnoteClass> enzymes = new HashSet<IAnoteClass>();
		enzymes.add(ClassPropertiesManagement.getClassIDClassName("Enzyme"));
		enzymes.add(ClassPropertiesManagement.getClassIDClassName("enzyme_rule"));
		Set<IAnoteClass> organism = new HashSet<IAnoteClass>();
		organism.add(ClassPropertiesManagement.getClassIDClassName("Organism"));
		REKineticConfigurationClasses classConfiguration = new REKineticConfigurationClasses(units, values, kineticParameters, metabolites, enzymes, organism);
		boolean sentencesToExport = true;;
		String exportFile = exportDir + String.valueOf(configuration2.getNCBITaxonomy()) + "_" +getStringDate() + ".tsv";
		IREKineticREResultsExportConfiguration configutaion =  new REKineticREResultsExportConfigurationImpl(exportFile , null, classConfiguration, sentencesToExport );
		return 	configutaion;
	}
	
	private static String getStringDate()
	{
		int year= Utils.currentYear();
		int month= Utils.currentMonth()+1;
		int day= Utils.currentDay();
		return day+"_"+month+"_"+year;
	}
	
}
