package com.silicolife.anote2daemon.controller.publicationsAccess;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pt.uminho.anote2.core.cluster.IClusterProcess;

import com.silicolife.anote2daemon.service.clustering.ClusteringService;
import com.silicolife.anote2daemon.webservice.DaemonResponse;

@RequestMapping(value = "/clustering")
@ResponseBody
@Controller
public class ClusteringController {

	@Autowired
	private ClusteringService clusteringService;

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/getClustersFromQuery/{queryId}", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<List<IClusterProcess>>> getClustersFromQuery(@PathVariable Long queryId) {
		DaemonResponse<List<IClusterProcess>> response = new DaemonResponse<List<IClusterProcess>>(clusteringService.getClustersFromQuery(queryId));
		return new ResponseEntity<DaemonResponse<List<IClusterProcess>>>(response, HttpStatus.OK);
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/getClusteringById/{queryId}", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<IClusterProcess>> getClusteringById(@PathVariable Long queryId) {
		DaemonResponse<IClusterProcess> response = new DaemonResponse<IClusterProcess>(clusteringService.getClusteringById(queryId));
		return new ResponseEntity<DaemonResponse<IClusterProcess>>(response, HttpStatus.OK);
	}
}
