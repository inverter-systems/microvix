package com.topinternacional.linx.model.nl.view;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TOPV_LINX_CEST")
public class Cest {
	
	@Id
	@Column(name = "CODIGO")
    private String codigo;	
	
	@Column(name = "DESCRICAO")
	private String descricao;
		
	public Cest() {}
	
	public Cest(String codigo, String descricao) {
		super();
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	
}