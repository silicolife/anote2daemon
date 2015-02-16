package com.silicolife.anote2daemon.wrapper.clustering;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import pt.uminho.anote2.carrot.linkage.datastructures.ClusterProcess;
import pt.uminho.anote2.core.cluster.IClusterLabel;
import pt.uminho.anote2.core.cluster.IClusterProcess;

import com.silicolife.anote2daemon.model.core.entities.ClustersProcessHasClustersLabels;
import com.silicolife.anote2daemon.model.core.entities.ClustersProcesses;
import com.silicolife.anote2daemon.model.core.entities.ClustersProperties;

/**
 * Wrapper to convert anote2 structures to daemon structures and vice verse
 * 
 * @author Joel Azevedo Costa
 * @year 2015
 *
 */
public class ClustersProcessWrapper {

	/**
	 * Convert anote2 structure
	 * 
	 * @param clusterProcesses
	 * @return
	 */
	public static IClusterProcess convertToAnoteStructure(ClustersProcesses clusterProcesses) {

		Long id = clusterProcesses.getId();
		String name = clusterProcesses.getDescription();
		Set<ClustersProperties> clusterProperties = clusterProcesses.getClustersPropertieses();
		Set<ClustersProcessHasClustersLabels> clusterLabels = clusterProcesses.getClustersProcessHasClustersLabelses();
		Properties properties = new Properties();
		List<IClusterLabel> clusterLabels_ = new ArrayList<IClusterLabel>();
		/**
		 * create properties
		 */
		for (ClustersProperties clusterProp : clusterProperties) {
			String key = clusterProp.getId().getPropKey();
			String value = clusterProp.getPropValue();
			properties.put(key, value);
		}
		/**
		 * create cluster labels
		 */
		for (ClustersProcessHasClustersLabels clusterLabel : clusterLabels) {
			IClusterLabel clusterLabel_ = ClustersLabelsWrapper.convertToAnoteStructure(clusterLabel.getClustersLabels());
			clusterLabels_.add(clusterLabel_);
		}
		
		if (properties.size() == 0)
			properties = null;

		if (clusterLabels_.size() == 0)
			clusterLabels_ = null;

		IClusterProcess clusterProcess_ = new ClusterProcess(id, properties, name, clusterLabels_);
		clusterProcess_.setPropeties(properties);
		return clusterProcess_;
	}

	/**
	 * Convert to daemon structure
	 * 
	 * @param clusterProcess_
	 * @return
	 */
	public static ClustersProcesses convertToDaemonStructure(IClusterProcess clusterProcess_) {
		
		Long id = clusterProcess_.getID();
		String description = clusterProcess_.getDescription();
		ClustersProcesses clusterProcesses = new ClustersProcesses(id,true);
		clusterProcesses.setDescription(description);
		return clusterProcesses;
	}
}
