package com.topinternacional.linx.enun;

/* Metodos Web Service de Entrada de Produtos */
public enum Metodo {
	/* Web Service de Entrada */
	LinxCadastraSetores("LinxCadastraSetores"), LinxCadastraLinhas("LinxCadastraLinhas"),
	LinxCadastraMarcas("LinxCadastraMarcas"), LinxCadastraColecoes("LinxCadastraColecoes"),
	LinxCadastraGrade1("LinxCadastraGrade1"), LinxCadastraGrade2("LinxCadastraGrade2"),
	LinxCadastraNcm("LinxCadastraNcm"), LinxCadastraCest("LinxCadastraCest"),
	LinxCadastraProdutos("LinxCadastraProdutos"), LinxCadastraProdutosCodebar("LinxCadastraProdutosCodebar"),
	LinxCadastraClientesFornecedores("LinxCadastraClientesFornecedores"),
	LinxAtualizaProdutosDetalhes("LinxAtualizaProdutosDetalhes"),
	/* Web Service de Saida */
	LinxMovimento("LinxMovimento"),
	LinxXMLDocumentos("LinxXMLDocumentos"),
	LinxVendedores("LinxVendedores"),
	LinxProdutos("LinxProdutos"),
	LinxClientesFornec("LinxClientesFornec"), 
	LinxMovimentoPlanos("LinxMovimentoPlanos");

	private final String valor;

	Metodo(String valorOpcao) {
		valor = valorOpcao;
	}

	public String getValor() {
		return valor;
	}
}