package com.topinternacional.linx.enun;

public enum Status {
	PROCESSANDO("Processando"),
	AGUARDADO("Aguardando"),
	ERRO("Erro"),	
	CONCLUIDO("Concluido");

	private String descricao;

	private Status(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
