package com.silicolife.anote2daemon.model.core.pojo;

// Generated 5/Fev/2015 14:40:20 by Hibernate Tools 4.0.0

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * ProcessCluesStats generated by hbm2java
 */
@Entity
@Table(name = "process_clues_stats")
public class ProcessCluesStats implements java.io.Serializable {

	private ProcessCluesStatsId id;

	public ProcessCluesStats() {
	}

	public ProcessCluesStats(ProcessCluesStatsId id) {
		this.id = id;
	}

	@EmbeddedId
	@AttributeOverrides({ @AttributeOverride(name = "processesId", column = @Column(name = "processes_id", nullable = false)), @AttributeOverride(name = "clue", column = @Column(name = "clue", length = 250)),
			@AttributeOverride(name = "numberClues", column = @Column(name = "numberClues", nullable = false)), @AttributeOverride(name = "classificationRe", column = @Column(name = "classification_re", length = 250)) })
	public ProcessCluesStatsId getId() {
		return this.id;
	}

	public void setId(ProcessCluesStatsId id) {
		this.id = id;
	}

}
