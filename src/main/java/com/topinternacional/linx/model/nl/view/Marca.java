package com.topinternacional.linx.model.nl.view;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.topinternacional.linx.services.util.Util;

@Entity
@Table(name="TOPV_LINX_MARCA")
public class Marca {
	
	@Id
	@Column(name = "CODIGO")
    private String codigo;	
	
	@Column(name = "NOME_MARCA")
	private String nomeMarca;
	
	@Transient
	private String setor;
		
	public Marca() {}
	
	public Marca(String codigo, String nomeMarca) {
		super();
		this.codigo = codigo;
		this.nomeMarca = nomeMarca;
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

	public String getNomeMarca() {
		return nomeMarca;
	}

	public void setNomeLinha(String nomeMarca) {
		this.nomeMarca = nomeMarca;
	}

	
}