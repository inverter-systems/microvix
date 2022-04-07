package com.topinternacional.linx.bean;

import java.io.Serializable;
import java.util.List;

import com.topinternacional.linx.services.util.Util;

public class Produto implements Serializable {

	private static final long serialVersionUID = -6904915900735652714L;
	
	private Integer codigo;
	private Integer codigoNL;
	private String descricao;
	
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	public Integer getCodigoNL() {
		return codigoNL;
	}
	public void setCodigoNL(Integer codigoNL) {
		this.codigoNL = codigoNL;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public static Produto getProduto(Response xmlnotas) {
		List<String> notas = Util.getRegistrosXML(xmlnotas);
		
		String[] col = Util.getColunasXML(notas.get(0));
					
		Produto produto = new Produto();
		produto.setCodigo(Integer.valueOf(col[2]));
		produto.setCodigoNL(Integer.valueOf(col[8]));
		produto.setDescricao(col[4]);

		return produto;
	}

}
