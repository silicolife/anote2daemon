package com.silicolife.anote2daemon.controller.runserverprocesses;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;
import com.silicolife.textmining.processes.ie.schemas.export.AnnotationExportA1Format;

@RequestMapping(value = "/run", produces = { MediaType.APPLICATION_JSON_VALUE})
@ResponseBody
@Controller
@Deprecated
public class NERServerOnDemandController{
	
	final static Logger logger = LoggerFactory.getLogger(NERServerOnDemandController.class);

//	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/ner/{textStream}", method = RequestMethod.GET)
	public ResponseEntity<List<AnnotationExportA1Format>> runner(@PathVariable String textStream) throws ANoteException{
		throw new ANoteException("Method moved to annotator/rest/ner/anotedaemonresources");
	}
	
}
