package com.silicolife.anote2daemon.controller.clustering;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.silicolife.anote2daemon.security.RestPermissionsEvaluatorEnum;
import com.silicolife.anote2daemon.utils.GenericPairSpringSpel;
import com.silicolife.anote2daemon.webservice.DaemonResponse;
import com.silicolife.textmining.core.datastructures.clustering.ClusterLabelImpl;
import com.silicolife.textmining.core.datastructures.clustering.ClusterProcessImpl;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.exceptions.ClusteringException;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.security.Permissions;
import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.service.clustering.IClusteringService;
import com.silicolife.textmining.core.interfaces.core.cluster.IClusterLabel;
import com.silicolife.textmining.core.interfaces.core.cluster.IClusterProcess;

/**
 * The goal of this class is to expose for the web all Clustering
 * functionalities of anote2daemon. It is necessary a user logged to access
 * these methods
 * 
 * 
 * @author Joel Azevedo Costa
 * @year 2015
 *
 */
@RequestMapping(value = "/clustering", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
@ResponseBody
@Controller
public class ClusteringController {

	@Autowired
	private Permissions permissions;
	@Autowired
	private GenericPairSpringSpel<RestPermissionsEvaluatorEnum, List<String>> genericPairSpringSpel;
	@Autowired
	private IClusteringService clusteringService;

	/**
	 * Get all clusters from a query
	 * 
	 * @param queryId
	 * @return
	 */
	@PreAuthorize("isAuthenticated() and hasPermission(#id, "
			+ "T(pt.uminho.anote2.datastructures.dataaccess.database.dataaccess.implementation.utils.ResourcesTypeUtils).queries.getName(),"
			+ "@genericPairSpringSpel.getGenericPairSpringSpel(T(com.silicolife.anote2daemon.security.RestPermissionsEvaluatorEnum).default_,@permissions.getFullgrant()))")
	@RequestMapping(value = "/getClustersFromQuery/{queryId}", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<List<IClusterProcess>>> getClustersFromQuery(@PathVariable Long queryId) {
		DaemonResponse<List<IClusterProcess>> response = new DaemonResponse<List<IClusterProcess>>(clusteringService.getClustersFromQuery(queryId));
		return new ResponseEntity<DaemonResponse<List<IClusterProcess>>>(response, HttpStatus.OK);
	}

	/**
	 * Get cluster by id
	 * 
	 * @param queryId
	 * @return
	 */
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/getClusteringById/{id}", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<IClusterProcess>> getClusteringById(@PathVariable Long id) {
		DaemonResponse<IClusterProcess> response = new DaemonResponse<IClusterProcess>(clusteringService.getClusteringById(id));
		return new ResponseEntity<DaemonResponse<IClusterProcess>>(response, HttpStatus.OK);
	}

	/**
	 * create a cluster process
	 * 
	 * @param clustering
	 * @return
	 */
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/createClusterProcess", method = RequestMethod.POST, consumes = { "application/json" })
	public ResponseEntity<DaemonResponse<Boolean>> createClusterProcess(@RequestBody ClusterProcessImpl clustering) {
		DaemonResponse<Boolean> response = new DaemonResponse<Boolean>(clusteringService.createClustering(clustering));
		return new ResponseEntity<DaemonResponse<Boolean>>(response, HttpStatus.OK);
	}

	/**
	 * Associate a Cluster process to a Query
	 * 
	 * @param queryId
	 * @param clusteringId
	 * @return
	 * @throws ClusteringException 
	 */
	@PreAuthorize("isAuthenticated() and hasPermission(#id,"
			+ "T(pt.uminho.anote2.datastructures.dataaccess.database.dataaccess.implementation.utils.ResourcesTypeUtils).queries.getName(),"
			+ "@genericPairSpringSpel.getGenericPairSpringSpel(T(com.silicolife.anote2daemon.security.RestPermissionsEvaluatorEnum).default_,@permissions.getWritegrant()))")
	@RequestMapping(value = "/clusterProcessQueryRegistry", method = RequestMethod.POST, consumes = { "application/json" })
	public ResponseEntity<DaemonResponse<Boolean>> clusterProcessQueryRegistry(@RequestParam Long queryId, @RequestParam Long clusteringId) throws ClusteringException {
		DaemonResponse<Boolean> response = new DaemonResponse<Boolean>(clusteringService.registerQueryClustering(queryId, clusteringId));
		return new ResponseEntity<DaemonResponse<Boolean>>(response, HttpStatus.OK);
	}

	/**
	 * Inactive a Cluster Process
	 * 
	 * @param clusteringId
	 * @return
	 * @throws ClusteringException 
	 */
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/inactivateClustering", method = RequestMethod.PUT, consumes = { "application/json" })
	public ResponseEntity<DaemonResponse<Boolean>> inactivateClustering(@RequestParam Long clusteringId) throws ClusteringException {
		DaemonResponse<Boolean> response = new DaemonResponse<Boolean>(clusteringService.inactivateClustering(clusteringId));
		return new ResponseEntity<DaemonResponse<Boolean>>(response, HttpStatus.OK);
	}
	
	/**
	 * 
	 * 
	 * @param clusteringId
	 * @param clusterLabels
	 * @return
	 * @throws ClusteringException
	 */
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/addClusteringLabels/{clusteringId}", method = RequestMethod.POST, consumes = { "application/json" })
	public ResponseEntity<DaemonResponse<Boolean>> addClusteringLabels(@PathVariable Long clusteringId, @RequestBody ArrayList<ClusterLabelImpl> clusterLabels) throws ClusteringException {
		List<IClusterLabel> labelsCluster = new ArrayList<IClusterLabel>();
		for (ClusterLabelImpl label : clusterLabels)
			labelsCluster.add(label);

		DaemonResponse<Boolean> response = new DaemonResponse<Boolean>(clusteringService.addClusteringLabels(clusteringId, labelsCluster));
		return new ResponseEntity<DaemonResponse<Boolean>>(response, HttpStatus.OK);
	}
	
	/**
	 * 
	 * 
	 * @param clusterProcess
	 * @return
	 * @throws ClusteringException
	 */
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/updateQueryClusteringProcess", method = RequestMethod.POST, consumes = { "application/json" })
	public ResponseEntity<DaemonResponse<Boolean>> updateQueryClusteringProcess(@RequestBody ClusterProcessImpl clusterProcess) throws ClusteringException {
		DaemonResponse<Boolean> response = new DaemonResponse<Boolean>(clusteringService.updateQueryClusteringProcess(clusterProcess));
		return new ResponseEntity<DaemonResponse<Boolean>>(response, HttpStatus.OK);
	}
}
