package com.silicolife.anote2daemon.controller.publicationsAccess;

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

import pt.uminho.anote2.carrot.linkage.datastructures.ClusterProcess;
import pt.uminho.anote2.core.cluster.IClusterProcess;
import pt.uminho.anote2.datastructures.dataaccess.database.dataaccess.implementation.service.clustering.ClusteringService;

import com.silicolife.anote2daemon.security.Permissions;
import com.silicolife.anote2daemon.webservice.DaemonResponse;

/**
 * The goal of this class is to expose for the web all Clustering
 * functionalities of anote2daemon. It is necessary a user logged to access
 * these methods
 * 
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
	private ClusteringService clusteringService;

	/**
	 * Get all clusters from a query
	 * 
	 * @param queryId
	 * @return
	 */
	@PreAuthorize("hasRole('ROLE_ADMIN') and hasPermission(#id, T(com.silicolife.anote2daemon.utils.ResourcesTypeUtils).queries.toString(), @permissions.getFullgrant())")
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
	@PreAuthorize("hasRole('ROLE_ADMIN')")
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
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/createClusterProcess", method = RequestMethod.POST, consumes = { "application/json" })
	public ResponseEntity<DaemonResponse<Boolean>> createClusterProcess(@RequestBody ClusterProcess clustering) {
		DaemonResponse<Boolean> response = new DaemonResponse<Boolean>(clusteringService.createClustering(clustering));
		return new ResponseEntity<DaemonResponse<Boolean>>(response, HttpStatus.OK);
	}

	/**
	 * Associate a Cluster process to a Query
	 * 
	 * @param queryId
	 * @param clusteringId
	 * @return
	 */
	@PreAuthorize("hasRole('ROLE_ADMIN') and hasPermission(#id, T(com.silicolife.anote2daemon.utils.ResourcesTypeUtils).queries.toString(), @permissions.getWritegrant())")
	@RequestMapping(value = "/clusterProcessQueryRegistry", method = RequestMethod.POST, consumes = { "application/json" })
	public ResponseEntity<DaemonResponse<Boolean>> clusterProcessQueryRegistry(@RequestParam Long queryId, @RequestParam Long clusteringId) {
		DaemonResponse<Boolean> response = new DaemonResponse<Boolean>(clusteringService.clusterProcessQueryRegistry(queryId, clusteringId));
		return new ResponseEntity<DaemonResponse<Boolean>>(response, HttpStatus.OK);
	}

	/**
	 * Inactive a Cluster Process
	 * 
	 * @param clusteringId
	 * @return
	 */
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/inactivateClustering", method = RequestMethod.PUT, consumes = { "application/json" })
	public ResponseEntity<DaemonResponse<Boolean>> inactivateClustering(@RequestParam Long clusteringId) {
		DaemonResponse<Boolean> response = new DaemonResponse<Boolean>(clusteringService.inactivateClustering(clusteringId));
		return new ResponseEntity<DaemonResponse<Boolean>>(response, HttpStatus.OK);
	}
}
