package com.topinternacional.linx.model.nl.ai;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the AI_CE_DIARIOS database table.
 * 
 */
@Embeddable
public class AiCeDiarioPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="COD_EMP")
	private long codEmp;

	@Column(name="COD_UNIDADE")
	private long codUnidade;

	@Column(name="NUM_NOTA")
	private long numNota;

	@Temporal(TemporalType.DATE)
	@Column(name="DTA_LANCAMENTO")
	private java.util.Date dtaLancamento;

	@Column(name="COD_ITEM")
	private long codItem;

	public AiCeDiarioPK() {
	}
	public long getCodEmp() {
		return this.codEmp;
	}
	public void setCodEmp(long codEmp) {
		this.codEmp = codEmp;
	}
	public long getCodUnidade() {
		return this.codUnidade;
	}
	public void setCodUnidade(long codUnidade) {
		this.codUnidade = codUnidade;
	}
	public long getNumNota() {
		return this.numNota;
	}
	public void setNumNota(long numNota) {
		this.numNota = numNota;
	}
	public java.util.Date getDtaLancamento() {
		return this.dtaLancamento;
	}
	public void setDtaLancamento(java.util.Date dtaLancamento) {
		this.dtaLancamento = dtaLancamento;
	}
	public long getCodItem() {
		return this.codItem;
	}
	public void setCodItem(long codItem) {
		this.codItem = codItem;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof AiCeDiarioPK)) {
			return false;
		}
		AiCeDiarioPK castOther = (AiCeDiarioPK)other;
		return 
			(this.codEmp == castOther.codEmp)
			&& (this.codUnidade == castOther.codUnidade)
			&& (this.numNota == castOther.numNota)
			&& this.dtaLancamento.equals(castOther.dtaLancamento)
			&& (this.codItem == castOther.codItem);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.codEmp ^ (this.codEmp >>> 32)));
		hash = hash * prime + ((int) (this.codUnidade ^ (this.codUnidade >>> 32)));
		hash = hash * prime + ((int) (this.numNota ^ (this.numNota >>> 32)));
		hash = hash * prime + this.dtaLancamento.hashCode();
		hash = hash * prime + ((int) (this.codItem ^ (this.codItem >>> 32)));
		
		return hash;
	}
}