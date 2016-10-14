package com.silicolife.anote2daemon.controller.runserverprocesses;

import java.io.IOException;

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
import com.silicolife.anote2daemon.processes.corpus.CorpusCreationServerExecutor;
import com.silicolife.anote2daemon.processes.corpus.CorpusServerImpl;
import com.silicolife.anote2daemon.processes.corpus.CorpusUpdaterServerExecutor;
import com.silicolife.anote2daemon.processes.ir.PubMedSearchServerRunExtension;
import com.silicolife.anote2daemon.processes.ner.LinnaeusTaggerServerRunExtention;
import com.silicolife.anote2daemon.utils.SpringRunnable;
import com.silicolife.anote2daemon.webservice.DaemonResponse;
import com.silicolife.textmining.core.datastructures.corpora.CorpusCreateConfigurationImpl;
import com.silicolife.textmining.core.datastructures.corpora.CorpusUpdateConfigurationImpl;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.exceptions.RunServerProcessesException;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.security.Permissions;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.service.annotation.IAnnotationService;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.service.corpora.ICorpusService;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.service.processes.IProcessesService;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.service.publications.IPublicationsService;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.service.queries.IQueriesService;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.service.resources.IClassesService;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.service.resources.IResourcesElementService;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.service.resources.IResourcesService;
import com.silicolife.textmining.core.datastructures.process.ner.NERResumeConfigurationImpl;
import com.silicolife.textmining.core.interfaces.core.document.corpus.ICorpus;
import com.silicolife.textmining.processes.ie.ner.linnaeus.configuration.NERLinnaeusConfigurationImpl;
import com.silicolife.textmining.processes.ir.pubmed.configuration.IRPubmedSearchConfigurationImpl;

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
	private Permissions permissions;

	@Autowired
	private IQueriesService queriesService;

	@Autowired
	private IPublicationsService publicationsService;

	@Autowired
	private ICorpusService corpusService;

	@Autowired
	private IProcessesService processService;

	@Autowired
	private IAnnotationService annotationService;
	
	@Autowired
	private IResourcesService resourcesService;

	@Autowired
	private IResourcesElementService resourcesElementService;

	@Autowired
	private IClassesService classesService;

	@Autowired 
	private TaskExecutor taskExecutor;

	/**
	 * 
	 * 
	 * @param request
	 * @return
	 */
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/configuration", method = RequestMethod.POST, consumes = { "application/json" })
	public ResponseEntity<DaemonResponse<Boolean>> executeConfiguration(@RequestBody String[] parameters) throws RunServerProcessesException{
		System.out.println(parameters[0] + " " + parameters[1]);
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
			case NERLinnaeusConfigurationImpl.nerLinnaeusUID :
				executeBackgroundThreadForLinneausTagger(parameters, bla);
				break;
			case NERResumeConfigurationImpl.uid :
				executeBackgroundThreadForResumeLinneausTagger(parameters, bla);
				break;
//			case RERelationConfigurationImpl.reRelationUID :
//				RERelationConfigurationImpl reRelationConfiguration = bla.readValue(parameters[1],RERelationConfigurationImpl.class);

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

	private void executeBackgroundThreadForPubMedSearch(String[] parameters, ObjectMapper bla) throws IOException, JsonParseException, JsonMappingException {
		final IRPubmedSearchConfigurationImpl searchConfiguration = bla.readValue(parameters[1],IRPubmedSearchConfigurationImpl.class);
		taskExecutor.execute(new SpringRunnable(){

			@Override
			protected void onRun() {
				try {
					queriesService.setUserLogged(getUserLogged());
					publicationsService.setUserLogged(getUserLogged());
					PubMedSearchServerRunExtension pubmedSearch = new PubMedSearchServerRunExtension(queriesService,publicationsService);
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

		taskExecutor.execute(new SpringRunnable() {

			@Override
			protected void onRun() {
				try {
					corpusService.setUserLogged(getUserLogged());
					publicationsService.setUserLogged(getUserLogged());
					CorpusCreationServerExecutor corpusCreation = new CorpusCreationServerExecutor(corpusService, publicationsService);
					corpusCreation.executeCorpusCreation(corpuscreationConfiguration);
				} catch (Exception e) {
					logger.error("Exception",e);
				}
			}

		});
	}
	
	private void executeBackgroundThreadForCorpusUpdate(String[] parameters, ObjectMapper bla)
			throws IOException, JsonParseException, JsonMappingException {
		final CorpusUpdateConfigurationImpl corpusupdateConfiguration = bla.readValue(parameters[1],CorpusUpdateConfigurationImpl.class);

		taskExecutor.execute(new SpringRunnable() {

			@Override
			protected void onRun() {
				try {
					corpusService.setUserLogged(getUserLogged());
					publicationsService.setUserLogged(getUserLogged());
					CorpusUpdaterServerExecutor corpusUpdate = new CorpusUpdaterServerExecutor(corpusService, publicationsService);
					corpusUpdate.executeCorpusUpdate(corpusupdateConfiguration);
				} catch (Exception e) {
					logger.error("Exception",e);
				}
			}

		});
	}

	private void executeBackgroundThreadForLinneausTagger(String[] parameters, ObjectMapper bla) throws IOException, JsonParseException, JsonMappingException {
		final NERLinnaeusConfigurationImpl linaneusConfiguration = bla.readValue(parameters[1],NERLinnaeusConfigurationImpl.class);
		ICorpus corpus = linaneusConfiguration.getCorpus();
		ICorpus corpusServer = new CorpusServerImpl(publicationsService,corpusService, corpus);
		linaneusConfiguration.setCorpus(corpusServer);
		taskExecutor.execute(new SpringRunnable() {

			@Override
			protected void onRun() {
				try {
					corpusService.setUserLogged(getUserLogged());
					resourcesService.setUserLogged(getUserLogged());
					resourcesElementService.setUserLogged(getUserLogged());
					classesService.setUserLogged(getUserLogged());
					processService.setUserLogged(getUserLogged());
					annotationService.setUserLogged(getUserLogged());
					LinnaeusTaggerServerRunExtention tagger = new LinnaeusTaggerServerRunExtention(publicationsService,corpusService, resourcesService, resourcesElementService, classesService, processService, annotationService);
					tagger.executeCorpusNER(linaneusConfiguration);
				} catch (Exception e) {
					logger.error("Exception",e);
				}
			}
		});
	}
	
	private void executeBackgroundThreadForResumeLinneausTagger(String[] parameters, ObjectMapper bla) throws IOException, JsonParseException, JsonMappingException {
		final NERResumeConfigurationImpl configuration = bla.readValue(parameters[1],NERResumeConfigurationImpl.class);
		ICorpus corpus = configuration.getCorpus();
		ICorpus corpusServer = new CorpusServerImpl(publicationsService,corpusService, corpus);
		configuration.setCorpus(corpusServer);
		taskExecutor.execute(new SpringRunnable() {

			@Override
			protected void onRun() {
				try {
					corpusService.setUserLogged(getUserLogged());
					resourcesService.setUserLogged(getUserLogged());
					resourcesElementService.setUserLogged(getUserLogged());
					classesService.setUserLogged(getUserLogged());
					processService.setUserLogged(getUserLogged());
					annotationService.setUserLogged(getUserLogged());
					LinnaeusTaggerServerRunExtention tagger = new LinnaeusTaggerServerRunExtention(publicationsService,corpusService, resourcesService, resourcesElementService, classesService, processService, annotationService);
					tagger.resumeNER(configuration);
				} catch (Exception e) {
					logger.error("Exception",e);
					System.out.println(e);
					e.printStackTrace();
				}
			}
		});
	}
}
