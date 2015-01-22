package com.silicolife.anote2daemon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.silicolife.anote2daemon.model.pojo.QueriesType;
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

	@RequestMapping(value = "/createQueryType", method = RequestMethod.PUT, consumes = { "application/json" })
	public ResponseEntity<DaemonResponse<QueriesType>> Type(@RequestBody DaemonRequest<QueriesType> request) {
		QueriesType queryType = request.getContent();
		DaemonResponse<QueriesType> response = queriesTypeService.create(queryType);
		return new ResponseEntity<DaemonResponse<QueriesType>>(response, HttpStatus.OK);
	}

//	@ExceptionHandler({ KeysHandlerException.class, DataIntegrityViolationException.class })
/*	public DaemonResponse<?> handleEmployeeNotFoundException(Exception ex) {

		Map<String, String> errorCodes = new HashMap<String, String>();
		errorCodes.put(ex.getMessage(), ExceptionsMessages.messages.get(ex.getMessage()));

		DaemonResponse<?> response = new DaemonResponse<>();
		response.setErrors(errorCodes);

		return response;
	}*/
}
