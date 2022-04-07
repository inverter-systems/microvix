package com.topinternacional.linx.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.topinternacional.linx.model.admin.FormasPgtoConfig;
import com.topinternacional.linx.services.util.Util;

public class FormaPagamento implements Serializable {

	private static final long serialVersionUID = 1734557822794990215L;

	private Integer codigo;
	private String descricao;
	private BigDecimal valor;
	private String formaPgto;
	private Integer numeroParcelas;
	
	private FormasPgtoConfig pgtoConfig;
	
	public FormaPagamento() {
		this.pgtoConfig = new FormasPgtoConfig();
	}
	
	public FormaPagamento(Integer codigo, String descricao, BigDecimal valor, String formaPgto, Integer numeroParcelas, FormasPgtoConfig pgtoConfig) {
		super();
		this.codigo = codigo;
		this.descricao = descricao;
		this.valor = valor;
		this.formaPgto = formaPgto;
		this.numeroParcelas = numeroParcelas;
		this.pgtoConfig = pgtoConfig;
	}

	public String getFormaPgto() {
		return formaPgto;
	}

	public void setFormaPgto(String formaPgto) {
		this.formaPgto = formaPgto;
	}

	public Integer getNumeroParcelas() {
		return numeroParcelas;
	}

	public void setNumeroParcelas(Integer numeroParcelas) {
		this.numeroParcelas = numeroParcelas;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public static List<FormaPagamento> getFormasPagamento(Response res) {
		List<FormaPagamento> formasPgto = new ArrayList<FormaPagamento>();
		List<String> itens = Util.getRegistrosXML(res);
		
		for (String item : itens) {
			String[] col = Util.getColunasXML(item);			
						
			FormaPagamento FormaPagamento = new FormaPagamento();
			
			FormaPagamento.setCodigo(Integer.valueOf(col[4]));
			FormaPagamento.setDescricao(col[5]);
			FormaPagamento.setValor(new BigDecimal(col[6]));
			FormaPagamento.setNumeroParcelas(Integer.valueOf(col[7]));
			FormaPagamento.setFormaPgto(col[10]);
			
			formasPgto.add(FormaPagamento);
		} 	
		return formasPgto;
	}

	public FormasPgtoConfig getPgtoConfig() {
		return pgtoConfig;
	}

	public void setPgtoConfig(FormasPgtoConfig pgtoConfig) {
		this.pgtoConfig = pgtoConfig;
	}
}
