package com.topinternacional.linx.model.nl.view;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TOPV_LINX_BARRAS")
public class Barra {
	
	@Id
	@Column(name = "COD_PRODUTO")
    private String codigoProduto;	
	
	@Column(name = "COD_BARRAS")
	private String codBarras;
	
	@Column(name = "SETOR")
	private String setor;

	@Column(name = "DESCRICAO")
	private String descricao;
	
	public Barra() {}
	
	public Barra(String codigoProduto, String codBarras, String setor, String descricao) {
		super();
		this.codigoProduto = codigoProduto;
		this.codBarras = codBarras;
		this.setor = setor;
		this.descricao = descricao;
	}

	public String getSetor() {
		return setor;
	}

	public void setSetor(String setor) {
		this.setor = setor;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getCodigoProduto() {
		return codigoProduto;
	}

	public void setCodigoProduto(String codigoProduto) {
		this.codigoProduto = codigoProduto;
	}

	public String getCodBarras() {
		return codBarras;
	}

	public void setCodBarras(String codBarras) {
		this.codBarras = codBarras;
	}
}