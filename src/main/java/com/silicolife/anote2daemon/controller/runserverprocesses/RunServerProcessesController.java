package com.silicolife.anote2daemon.controller.runserverprocesses;

import java.io.IOException;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.silicolife.anote2daemon.processes.corpus.CorpusAutoUpdate;
import com.silicolife.anote2daemon.processes.corpus.CorpusCreationExecutorServer;
import com.silicolife.anote2daemon.processes.corpus.CorpusUpdaterExecutorServer;
import com.silicolife.anote2daemon.processes.re.OrganismKineticInformationExportToFileExtension;
import com.silicolife.anote2daemon.utils.ApplicationConfigurationProperties;
import com.silicolife.anote2daemon.utils.SpringRunnable;
import com.silicolife.anote2daemon.webservice.DaemonResponse;
import com.silicolife.textmining.core.datastructures.corpora.CorpusCreateConfigurationGlobalImpl;
import com.silicolife.textmining.core.datastructures.corpora.CorpusCreateConfigurationImpl;
import com.silicolife.textmining.core.datastructures.corpora.CorpusCreateConfigurationLuceneSearchImpl;
import com.silicolife.textmining.core.datastructures.corpora.CorpusUpdateConfigurationImpl;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.exceptions.RunServerProcessesException;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.lucene.service.ILuceneService;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.service.annotation.IAnnotationService;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.service.publications.IPublicationsService;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.service.resources.IResourcesService;
import com.silicolife.textmining.core.datastructures.resources.export.ResourceExportConfigurationImpl;
import com.silicolife.textmining.core.datastructures.utils.Utils;
import com.silicolife.textmining.core.interfaces.resource.IResource;
import com.silicolife.textmining.core.interfaces.resource.IResourceElement;
import com.silicolife.textmining.core.interfaces.resource.export.tsv.IResourceExportConfiguration;
import com.silicolife.textmining.core.interfaces.resource.export.tsv.IResourceExportToCSV;
import com.silicolife.textmining.processes.ie.ner.datatstructures.ANERLexicalResources;
import com.silicolife.textmining.processes.ie.ner.linnaeus.LinnaeusTagger;
import com.silicolife.textmining.processes.ie.ner.linnaeus.configuration.NERLinnaeusConfigurationImpl;
import com.silicolife.textmining.processes.ie.pipelines.kineticparameters.configuration.KineticREPipelineConfigurationImpl;
import com.silicolife.textmining.processes.ir.pubmed.PubMedSearch;
import com.silicolife.textmining.processes.ir.pubmed.configuration.IRPubmedSearchConfigurationImpl;
import com.silicolife.textmining.processes.resources.export.ResourceExportToCSVImpl;

/**
 * The goal of this class is to expose for the web all Publications
 * functionalities of anote2daemon. It is necessary a user logged to access
 * these methods
 * 
 * @author Joel Azevedo Costa
 * @year 2015
 *
 */
@RequestMapping(value = "/runserverprocesses", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
@ResponseBody
@Controller
public class RunServerProcessesController {

	final static Logger logger = LoggerFactory.getLogger(RunServerProcessesController.class);

	@Autowired 
	private TaskExecutor taskExecutor;

	@Autowired
	private ILuceneService luceneService;

	@Autowired
	private IResourcesService resourcesService;
	
	@Autowired
	private IAnnotationService annotationService;
	
	@Autowired
	private IPublicationsService publicationService;

	/**
	 * 
	 * @return
	 */
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/rebuildLuceneIndex", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<Boolean>> rebuildLuceneIndex(){
		taskExecutor.execute(new SpringRunnable(false){

			@Override
			protected void onRun() {
				try {
					luceneService.rebuildLuceneIndex();
				} catch (Exception e) {
					logger.error("Exception",e);
				}
			}
		});

		DaemonResponse<Boolean> response = new DaemonResponse<Boolean>(true);
		return new ResponseEntity<DaemonResponse<Boolean>>(response, HttpStatus.OK);
	}

	/**
	 * 
	 * @return
	 */
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/autoupdate", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<Boolean>> autoupdate(){

		taskExecutor.execute(new SpringRunnable(true){

			@Override
			protected void onRun() {
				CorpusAutoUpdate.run();		
			}
		});

		DaemonResponse<Boolean> response = new DaemonResponse<Boolean>(true);
		return new ResponseEntity<DaemonResponse<Boolean>>(response, HttpStatus.OK);
	}



	/**
	 * 
	 * 
	 * @param request
	 * @return
	 */
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/configuration", method = RequestMethod.POST, consumes = { "application/json" })
	public ResponseEntity<DaemonResponse<Boolean>> executeConfiguration(@RequestBody String[] parameters) throws RunServerProcessesException{
		logger.info(parameters[0] + " " + parameters[1]);
		ObjectMapper bla = new ObjectMapper();
		try {
			switch (parameters[0]) {
				case IRPubmedSearchConfigurationImpl.pubmedsearchUID :
					executeBackgroundThreadForPubMedSearch(parameters, bla);
					break;
				case CorpusCreateConfigurationImpl.configurationUID :
					executeBackgroundThreadForCorpusCreation(parameters, bla);
					break;
				case CorpusUpdateConfigurationImpl.configurationUID :
					executeBackgroundThreadForCorpusUpdate(parameters, bla);
					break;
				case CorpusCreateConfigurationGlobalImpl.configurationUID:
					executeBackgroundThreadForGlobalCorpusCreation(parameters, bla);
					break;
				case CorpusCreateConfigurationLuceneSearchImpl.configurationUID:
					executeBackgroundThreadForLuceneSearchCorpusCreation(parameters, bla);
					break;
				case NERLinnaeusConfigurationImpl.nerLinnaeusUID :
					executeBackgroundThreadForLinneausTagger(parameters, bla);
					break;
				case KineticREPipelineConfigurationImpl.processUID :
					executeBackgroundThreadForKineticREPipeline(parameters, bla);
					break;
				case ResourceExportToCSVImpl.processUID:
					executeExportResource(parameters, bla);
					break;
				default :
					break;
			}
		} catch (JsonParseException e) {
			throw new RunServerProcessesException(e);
		} catch (JsonMappingException e) {
			throw new RunServerProcessesException(e);
		} catch (IOException e) {
			throw new RunServerProcessesException(e);
		}
		DaemonResponse<Boolean> response = new DaemonResponse<Boolean>(true);
		return new ResponseEntity<DaemonResponse<Boolean>>(response, HttpStatus.OK);
	}

	private void executeExportResource(String[] parameters, ObjectMapper bla) throws JsonParseException, JsonMappingException, IOException {
		final Long resourceId = bla.readValue(parameters[1],Long.class);
		taskExecutor.execute(new SpringRunnable(false){
			@Override
			protected void onRun() {
				try {
					IResource<IResourceElement> resource = resourcesService.getResourcesById(resourceId);
					if(resource!=null)
					{
						logger.info("Resource Export Start " + resourceId);
						IResourceExportToCSV exporter = new ResourceExportToCSVImpl();
						String filePath = ApplicationConfigurationProperties.getExportResourceDir() + "/resource_" + resourceId + "_" + Utils.currentTimeSimple().replace("-", "_") + ".tsv";
						IResourceExportConfiguration configuration = new ResourceExportConfigurationImpl(filePath );
						exporter.exportCSVFile(resource, configuration);
						logger.info("Resource Export End " + resourceId);
					}
				} catch (Exception e) {
					logger.error("Exception",e);;
				}
			}
		});
	}

	private void executeBackgroundThreadForKineticREPipeline(String[] parameters, ObjectMapper bla) throws IOException, JsonParseException, JsonMappingException {
		final KineticREPipelineConfigurationImpl kineticrepipelineConfiguration = bla.readValue(parameters[1],KineticREPipelineConfigurationImpl.class);
		taskExecutor.execute(new SpringRunnable(false){

			@Override
			protected void onRun() {
				try {	
					OrganismKineticInformationExportToFileExtension kparamPipeline = new OrganismKineticInformationExportToFileExtension(kineticrepipelineConfiguration);
					if(kineticrepipelineConfiguration.getNCBITaxonomy()!=null)						
						kparamPipeline.retrievedByOrganism(kineticrepipelineConfiguration.getNCBITaxonomy());
					else if(kineticrepipelineConfiguration.getECNumber()!=null)
						kparamPipeline.retrievedByEnzyme(kineticrepipelineConfiguration.getECNumber());
					else if(kineticrepipelineConfiguration.getChEBI()!=null)
						kparamPipeline.retrievedByCompound(kineticrepipelineConfiguration.getChEBI());
				} catch (Exception e) {
					logger.error("Exception",e);;
				}
			}
		});
	}

	private void executeBackgroundThreadForPubMedSearch(String[] parameters, ObjectMapper bla) throws IOException, JsonParseException, JsonMappingException {
		final IRPubmedSearchConfigurationImpl searchConfiguration = bla.readValue(parameters[1],IRPubmedSearchConfigurationImpl.class);
		taskExecutor.execute(new SpringRunnable(true){

			@Override
			protected void onRun() {
				try {
					PubMedSearch pubmedSearch = new PubMedSearch();
					pubmedSearch.search(searchConfiguration);
				} catch (Exception e) {
					logger.error("Exception",e);;
				}
			}
		});
	}

	private void executeBackgroundThreadForCorpusCreation(String[] parameters, ObjectMapper bla)
			throws IOException, JsonParseException, JsonMappingException {
		final CorpusCreateConfigurationImpl corpuscreationConfiguration = bla.readValue(parameters[1],CorpusCreateConfigurationImpl.class);

		taskExecutor.execute(new SpringRunnable(true) {

			@Override
			protected void onRun() {
				try {
					CorpusCreationExecutorServer corpusCreation = new CorpusCreationExecutorServer();
					corpusCreation.executeCorpusCreation(corpuscreationConfiguration);
				} catch (Exception e) {
					logger.error("Exception",e);
				}
			}

		});
	}
	
	private void executeBackgroundThreadForGlobalCorpusCreation(String[] parameters, ObjectMapper bla)
			throws IOException, JsonParseException, JsonMappingException {
		final CorpusCreateConfigurationGlobalImpl corpuscreationConfigurationGlobal = bla.readValue(parameters[1],CorpusCreateConfigurationGlobalImpl.class);

		taskExecutor.execute(new SpringRunnable(true) {

			@Override
			protected void onRun() {
				try {
					Set<Long> documentsIDs = publicationService.getPublicationsIdsFromResourcesQuery(corpuscreationConfigurationGlobal.getSearchString(), annotationService);
					corpuscreationConfigurationGlobal.setDocumentsIDs(documentsIDs);
					CorpusCreationExecutorServer corpusCreation = new CorpusCreationExecutorServer();
					corpusCreation.executeCorpusCreationByIds(corpuscreationConfigurationGlobal);
				} catch (Exception e) {
					logger.error("Exception",e);
				}
			}

		});
	}
	
	private void executeBackgroundThreadForLuceneSearchCorpusCreation(String[] parameters, ObjectMapper bla)
			throws IOException, JsonParseException, JsonMappingException {
		final CorpusCreateConfigurationLuceneSearchImpl corpuscreationConfigurationLuceneSearch = bla.readValue(parameters[1],CorpusCreateConfigurationLuceneSearchImpl.class);

		taskExecutor.execute(new SpringRunnable(true) {

			@Override
			protected void onRun() {
				try {
					
					CorpusCreationExecutorServer corpusCreation = new CorpusCreationExecutorServer();
					corpusCreation.executeCorpusCreationByLuceneSearch(corpuscreationConfigurationLuceneSearch);
				} catch (Exception e) {
					logger.error("Exception",e);
				}
			}

		});
	} 


	private void executeBackgroundThreadForCorpusUpdate(String[] parameters, ObjectMapper bla)
			throws IOException, JsonParseException, JsonMappingException {
		final CorpusUpdateConfigurationImpl corpusupdateConfiguration = bla.readValue(parameters[1],CorpusUpdateConfigurationImpl.class);

		taskExecutor.execute(new SpringRunnable(false) {

			@Override
			protected void onRun() {
				try {
					CorpusUpdaterExecutorServer corpusUpdate = new CorpusUpdaterExecutorServer();
					corpusUpdate.executeCorpusUpdate(corpusupdateConfiguration);
				} catch (Exception e) {
					logger.error("Exception",e);
				}
			}

		});
	}

	private void executeBackgroundThreadForLinneausTagger(String[] parameters, ObjectMapper bla) throws IOException, JsonParseException, JsonMappingException {
		final NERLinnaeusConfigurationImpl linaneusConfiguration = bla.readValue(parameters[1],NERLinnaeusConfigurationImpl.class);
		taskExecutor.execute(new SpringRunnable(true) {

			@Override
			protected void onRun() {
				try {
					ANERLexicalResources tagger = new LinnaeusTagger();
					tagger.execute(linaneusConfiguration);
				} catch (Exception e) {
					logger.error("Exception",e);
				}
			}
		});
	}

}
