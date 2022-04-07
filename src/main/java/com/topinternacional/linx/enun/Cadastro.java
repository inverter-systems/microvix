package com.topinternacional.linx.enun;

/* Cadastros disponiveis no intergador */
public enum Cadastro {
	SN("SN"), 
	Produtos("Produtos"), 
	Barras("Barras"),
	Cest("Cests"),
	Ncm("Ncm"),
	Linha("Linha"),
	Marca("Marca"),
	Setor("Setor"),
	NotaFiscasl("Nota Fiscal"),
	Pessoas("Pessoas"); 

	private final String valor;

	Cadastro(String valorOpcao) {
		valor = valorOpcao;
	}

	public String getValor() {
		return valor;
	}
}