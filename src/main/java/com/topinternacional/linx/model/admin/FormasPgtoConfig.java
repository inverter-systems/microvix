package com.topinternacional.linx.model.admin;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


@Entity
@Table(name="TOPT_LINX_COND_PGTO")
public class FormasPgtoConfig implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private FormasPgtoConfigPK id;

	@Column(name="COD_COND_PGTO")
	private BigDecimal codCondPgto;

	@Column(name="COD_PESSOA")
	private BigDecimal codPessoa;

	@Column(name="DES_COND_PGTO")
	private String desCondPgto;

	@Column(name="DES_LANCAMENTO")
	private String desLancamento;

	@Column(name="IND_AVISTA")
	private BigDecimal indAvista;

	@Column(name="TIP_TITULO")
	private BigDecimal tipTitulo;

	public FormasPgtoConfig() {
	}

	public FormasPgtoConfigPK getId() {
		return this.id;
	}

	public void setId(FormasPgtoConfigPK id) {
		this.id = id;
	}

	public BigDecimal getCodCondPgto() {
		return this.codCondPgto;
	}

	public void setCodCondPgto(BigDecimal codCondPgto) {
		this.codCondPgto = codCondPgto;
	}

	public BigDecimal getCodPessoa() {
		return this.codPessoa;
	}

	public void setCodPessoa(BigDecimal codPessoa) {
		this.codPessoa = codPessoa;
	}

	public String getDesCondPgto() {
		return this.desCondPgto;
	}

	public void setDesCondPgto(String desCondPgto) {
		this.desCondPgto = desCondPgto;
	}

	public String getDesLancamento() {
		return this.desLancamento;
	}

	public void setDesLancamento(String desLancamento) {
		this.desLancamento = desLancamento;
	}

	public BigDecimal getIndAvista() {
		return this.indAvista;
	}

	public void setIndAvista(BigDecimal indAvista) {
		this.indAvista = indAvista;
	}

	public BigDecimal getTipTitulo() {
		return this.tipTitulo;
	}

	public void setTipTitulo(BigDecimal tipTitulo) {
		this.tipTitulo = tipTitulo;
	}

}