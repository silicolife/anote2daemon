package com.silicolife.anote2daemon.model.core.pojo;

// Generated 5/Fev/2015 14:40:20 by Hibernate Tools 4.0.0

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * ProcessEntitiesClassStats generated by hbm2java
 */
@Entity
@Table(name = "process_entities_class_stats")
public class ProcessEntitiesClassStats implements java.io.Serializable {

	private ProcessEntitiesClassStatsId id;

	public ProcessEntitiesClassStats() {
	}

	public ProcessEntitiesClassStats(ProcessEntitiesClassStatsId id) {
		this.id = id;
	}

	@EmbeddedId
	@AttributeOverrides({ @AttributeOverride(name = "processesId", column = @Column(name = "processes_id", nullable = false)), @AttributeOverride(name = "class_", column = @Column(name = "class", nullable = false)),
			@AttributeOverride(name = "numberClasses", column = @Column(name = "numberClasses", nullable = false)) })
	public ProcessEntitiesClassStatsId getId() {
		return this.id;
	}

	public void setId(ProcessEntitiesClassStatsId id) {
		this.id = id;
	}

}
