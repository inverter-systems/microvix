package com.topinternacional.linx.model.admin;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the TOPT_LINX_COND_PGTO database table.
 * 
 */
@Embeddable
public class FormasPgtoConfigPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private String sistema;

	private long codigo;

	public FormasPgtoConfigPK() {
	}
	public String getSistema() {
		return this.sistema;
	}
	public void setSistema(String sistema) {
		this.sistema = sistema;
	}
	public long getCodigo() {
		return this.codigo;
	}
	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof FormasPgtoConfigPK)) {
			return false;
		}
		FormasPgtoConfigPK castOther = (FormasPgtoConfigPK)other;
		return 
			this.sistema.equals(castOther.sistema)
			&& (this.codigo == castOther.codigo);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.sistema.hashCode();
		hash = hash * prime + ((int) (this.codigo ^ (this.codigo >>> 32)));
		
		return hash;
	}
}