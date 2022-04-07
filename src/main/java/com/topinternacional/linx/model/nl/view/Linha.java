package com.topinternacional.linx.model.nl.view;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.topinternacional.linx.services.util.Util;

@Entity
@Table(name="TOPV_LINX_LINHA")
public class Linha {
	
	@Id
	@Column(name = "CODIGO")
    private String codigo;	
	
	@Column(name = "NOME_LINHA")
	private String nomeLinha;
	
	@Transient
	private String setor;
		
	public Linha() {}
	
	public Linha(String codigo, String nomeLinha) {
		super();
		this.codigo = codigo;
		this.nomeLinha = nomeLinha;
	}
	
	public String getSetor() {
		return Util.getSetor(codigo);
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNomeLinha() {
		return nomeLinha;
	}

	public void setNomeLinha(String nomeLinha) {
		this.nomeLinha = nomeLinha;
	}

	
}