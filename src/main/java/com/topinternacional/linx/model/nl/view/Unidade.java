package com.topinternacional.linx.model.nl.view;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TOPV_LINX_UNIDADES")
public class Unidade {
	
	@Id
	@Column(name = "COD_UNIDADE")
    private String codUnidade;	
	
	@Column(name = "DES_NOME")
	private String desNome;
		
	public Unidade() {}

	public Unidade(String codUnidade, String desNome) {
		super();
		this.codUnidade = codUnidade;
		this.desNome = desNome;
	}

	public String getCodUnidade() {
		return codUnidade;
	}

	public void setCodUnidade(String codUnidade) {
		this.codUnidade = codUnidade;
	}

	public String getDesNome() {
		return desNome;
	}

	public void setDesNome(String desNome) {
		this.desNome = desNome;
	}
	
	public String getNome() {
		return codUnidade+" - "+desNome;
	}
}