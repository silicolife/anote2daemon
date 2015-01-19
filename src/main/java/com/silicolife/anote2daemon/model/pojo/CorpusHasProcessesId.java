package com.silicolife.anote2daemon.model.pojo;

// Generated 19/Jan/2015 15:19:18 by Hibernate Tools 4.0.0

/**
 * CorpusHasProcessesId generated by hbm2java
 */
public class CorpusHasProcessesId implements java.io.Serializable {

	private long corpusIdcorpus;
	private long processesIdprocesses;

	public CorpusHasProcessesId() {
	}

	public CorpusHasProcessesId(long corpusIdcorpus, long processesIdprocesses) {
		this.corpusIdcorpus = corpusIdcorpus;
		this.processesIdprocesses = processesIdprocesses;
	}

	public long getCorpusIdcorpus() {
		return this.corpusIdcorpus;
	}

	public void setCorpusIdcorpus(long corpusIdcorpus) {
		this.corpusIdcorpus = corpusIdcorpus;
	}

	public long getProcessesIdprocesses() {
		return this.processesIdprocesses;
	}

	public void setProcessesIdprocesses(long processesIdprocesses) {
		this.processesIdprocesses = processesIdprocesses;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof CorpusHasProcessesId))
			return false;
		CorpusHasProcessesId castOther = (CorpusHasProcessesId) other;

		return (this.getCorpusIdcorpus() == castOther.getCorpusIdcorpus()) && (this.getProcessesIdprocesses() == castOther.getProcessesIdprocesses());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (int) this.getCorpusIdcorpus();
		result = 37 * result + (int) this.getProcessesIdprocesses();
		return result;
	}

}