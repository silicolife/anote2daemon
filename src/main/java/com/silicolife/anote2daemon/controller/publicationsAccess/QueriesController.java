package com.silicolife.anote2daemon.controller.publicationsAccess;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.silicolife.anote2daemon.model.pojo.Queries;
import com.silicolife.anote2daemon.model.pojo.QueriesHasPublications;
import com.silicolife.anote2daemon.model.pojo.QueriesType;
import com.silicolife.anote2daemon.service.core.QueriesHasPublicationsService;
import com.silicolife.anote2daemon.service.core.QueriesService;
import com.silicolife.anote2daemon.service.core.QueriesTypeService;
import com.silicolife.anote2daemon.webservice.DaemonRequest;
import com.silicolife.anote2daemon.webservice.DaemonResponse;

@RequestMapping(value = "/queries")
@ResponseBody
@Controller
public class QueriesController {

	@Autowired
	private QueriesService queriesService;
	@Autowired
	private QueriesTypeService queriesTypeService;
	@Autowired
	private QueriesHasPublicationsService queriesHasPublicationsService;

	@RequestMapping(value = "/createQueryType", method = RequestMethod.PUT, consumes = { "application/json" })
	public ResponseEntity<DaemonResponse<QueriesType>> createQueryType(@RequestBody DaemonRequest<QueriesType> request) {
		QueriesType queryType = request.getContent();
		DaemonResponse<QueriesType> response = queriesTypeService.create(queryType);
		return new ResponseEntity<DaemonResponse<QueriesType>>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/getQueryById/{id}", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<Queries>> getQueryById(@PathVariable Long id) {
		DaemonResponse<Queries> response = queriesService.getById(id);
		return new ResponseEntity<DaemonResponse<Queries>>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/getQueryTypeById/{id}", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<QueriesType>> getQueryTypeById(@PathVariable Long id) {
		DaemonResponse<QueriesType> response = queriesTypeService.getById(id);
		return new ResponseEntity<DaemonResponse<QueriesType>>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/createQuery", method = RequestMethod.PUT, consumes = { "application/json" })
	public ResponseEntity<DaemonResponse<Queries>> createQuery(@RequestBody DaemonRequest<Queries> request) {
		Queries query = request.getContent();
		DaemonResponse<Queries> response = queriesService.create(query);
		return new ResponseEntity<DaemonResponse<Queries>>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/getQueriesHasPublicationsById/{queryId}&{publicationId}", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<QueriesHasPublications>> getQueriesHasPublicationsById(@PathVariable Long queryId, @PathVariable Long publicationId) {
		DaemonResponse<QueriesHasPublications> response = queriesHasPublicationsService.getById(queryId, publicationId);
		return new ResponseEntity<DaemonResponse<QueriesHasPublications>>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/createQueriesHasPublications", method = RequestMethod.PUT, consumes = { "application/json" })
	public ResponseEntity<DaemonResponse<QueriesHasPublications>> createQueriesHasPublications(@RequestBody DaemonRequest<QueriesHasPublications> request) {
		QueriesHasPublications queriesHasPublications = request.getContent();
		DaemonResponse<QueriesHasPublications> response = queriesHasPublicationsService.create(queriesHasPublications);
		return new ResponseEntity<DaemonResponse<QueriesHasPublications>>(response, HttpStatus.OK);
	}

}
