package com.silicolife.anote2daemon.wrapper.clustering;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import pt.uminho.anote2.core.cluster.IClusterLabel;
import pt.uminho.anote2.datastructures.clustering.ClusterLabel;

import com.silicolife.anote2daemon.model.core.entities.ClustersLabels;
import com.silicolife.anote2daemon.model.core.entities.ClustersLabelsPublications;

/**
 * Class to transform anote2 Cluster Labels structures to daemon Cluster Labels
 * structures and vice-verse
 * 
 * @author Joel Azevedo Costa
 * @year 2015
 *
 */
public class ClustersLabelsWrapper {

	public static IClusterLabel convertToAnoteStructure(ClustersLabels clusterLabel) {
		Long id = clusterLabel.getClustersLabelId();
		String name = clusterLabel.getClusterLabelName();
		Double score = clusterLabel.getScore();
		List<Long> publicationsId = new ArrayList<Long>();
		/**
		 * get publications
		 */
		Set<ClustersLabelsPublications> clustersPub = clusterLabel.getClustersLabelsPublicationses();
		for (ClustersLabelsPublications clusterPub : clustersPub) {
			publicationsId.add(clusterPub.getId().getPublicationsId());
		}
		if (publicationsId.size() == 0)
			publicationsId = null;
		
		IClusterLabel clusterLabel_ = new ClusterLabel(id, name, score, publicationsId);
		return clusterLabel_;
	}

	public static ClustersLabels convertToDaemonStructure(IClusterLabel clusterLabel_) {
		Long id = clusterLabel_.getID();
		String name = clusterLabel_.getName();
		Double score = clusterLabel_.getScore();
		ClustersLabels clusterLabels = new ClustersLabels(id, name);
		clusterLabels.setScore(score);
		return clusterLabels;
	}
}
