package com.topinternacional.linx.model.nl.ai;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the AI_NS_NOTAS_OPERACOES database table.
 * 
 */
@Entity
@Table(name="AI_NS_NOTAS_OPERACOES")
@NamedQuery(name="AiNsNotasOperacoes.findAll", query="SELECT a FROM AiNsNotasOperacoes a")
public class AiNsNotasOperacoes implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private AiNsNotasOperacoePK id;

	@Column(name="COD_GR_FISCAL")
	private BigDecimal codGrFiscal;

	@Column(name="COD_MAQ_NOTA_ANT")
	private BigDecimal codMaqNotaAnt;

	@Column(name="COD_OPER")
	private Integer codOper;

	@Column(name="DES_OPERACAO")
	private String desOperacao;

	@Column(name="DES_SIT_IPI")
	private String desSitIpi;

	@Temporal(TemporalType.DATE)
	@Column(name="DTA_TRANSACAO")
	private Date dtaTransacao;

	@Column(name="IND_CONSUMIDOR")
	private int indConsumidor;

	@Column(name="IND_REJEITADA")
	private Integer indRejeitada;

	@Column(name="NUM_CFOP")
	private Integer numCfop;

	@Column(name="NUM_NOTA_ANT")
	private BigDecimal numNotaAnt;

	@Column(name="PER_COMISSAO")
	private BigDecimal perComissao;

	@Column(name="PER_COMISSAO_AT")
	private BigDecimal perComissaoAt;

	@Column(name="TIP_STATUS_TRANSACAO")
	private Integer tipStatusTransacao;

	@Column(name="TIP_TRANSACAO")
	private Integer tipTransacao;

	@Column(name="TXT_ERRO")
	private String txtErro;

	@Column(name="VLR_ACRESCIMO")
	private BigDecimal vlrAcrescimo;

	@Column(name="VLR_ACRESCIMO_COB")
	private BigDecimal vlrAcrescimoCob;

	@Column(name="VLR_BC_COMISSAO")
	private BigDecimal vlrBcComissao;

	@Column(name="VLR_BC_COMISSAO_AT")
	private BigDecimal vlrBcComissaoAt;

	@Column(name="VLR_BC_IPI")
	private BigDecimal vlrBcIpi;

	@Column(name="VLR_COFINS")
	private BigDecimal vlrCofins;

	@Column(name="VLR_COFINS_RET")
	private BigDecimal vlrCofinsRet;

	@Column(name="VLR_COFINS_ST")
	private BigDecimal vlrCofinsSt;

	@Column(name="VLR_COMISSAO")
	private BigDecimal vlrComissao;

	@Column(name="VLR_COMISSAO_AT")
	private BigDecimal vlrComissaoAt;

	@Column(name="VLR_CSLL_RET")
	private BigDecimal vlrCsllRet;

	@Column(name="VLR_DESCONTO_COB")
	private BigDecimal vlrDescontoCob;

	@Column(name="VLR_DESCONTO1")
	private BigDecimal vlrDesconto1;

	@Column(name="VLR_DESCONTO2")
	private BigDecimal vlrDesconto2;

	@Column(name="VLR_ENTRADA")
	private BigDecimal vlrEntrada;

	@Column(name="VLR_FRETE")
	private BigDecimal vlrFrete;

	@Column(name="VLR_FRETE_PAGO")
	private BigDecimal vlrFretePago;

	@Column(name="VLR_FUNRURAL")
	private BigDecimal vlrFunrural;

	@Column(name="VLR_INSS")
	private BigDecimal vlrInss;

	@Column(name="VLR_IOF_FINAN")
	private BigDecimal vlrIofFinan;

	@Column(name="VLR_IPI")
	private BigDecimal vlrIpi;

	@Column(name="VLR_IRRF")
	private BigDecimal vlrIrrf;

	@Column(name="VLR_IS_IPI")
	private BigDecimal vlrIsIpi;

	@Column(name="VLR_OPERACAO")
	private BigDecimal vlrOperacao;

	@Column(name="VLR_OU_IPI")
	private BigDecimal vlrOuIpi;

	@Column(name="VLR_OUTROS_ACRE")
	private BigDecimal vlrOutrosAcre;

	@Column(name="VLR_OUTROS_DCTO")
	private BigDecimal vlrOutrosDcto;

	@Column(name="VLR_PIS")
	private BigDecimal vlrPis;

	@Column(name="VLR_PIS_RET")
	private BigDecimal vlrPisRet;

	@Column(name="VLR_PIS_ST")
	private BigDecimal vlrPisSt;

	@Column(name="VLR_PREMIO")
	private BigDecimal vlrPremio;

	@Column(name="VLR_PREMIO_AT")
	private BigDecimal vlrPremioAt;

	@Column(name="VLR_PRODUTOS")
	private BigDecimal vlrProdutos;

	@Column(name="VLR_SEGURO")
	private BigDecimal vlrSeguro;

	@Column(name="VLR_TAC")
	private BigDecimal vlrTac;

	public AiNsNotasOperacoes() {
		this.id = new AiNsNotasOperacoePK();
	}

	public AiNsNotasOperacoePK getId() {
		return this.id;
	}

	public void setId(AiNsNotasOperacoePK id) {
		this.id = id;
	}

	public BigDecimal getCodGrFiscal() {
		return this.codGrFiscal;
	}

	public void setCodGrFiscal(BigDecimal codGrFiscal) {
		this.codGrFiscal = codGrFiscal;
	}

	public BigDecimal getCodMaqNotaAnt() {
		return this.codMaqNotaAnt;
	}

	public void setCodMaqNotaAnt(BigDecimal codMaqNotaAnt) {
		this.codMaqNotaAnt = codMaqNotaAnt;
	}

	public Integer getCodOper() {
		return this.codOper;
	}

	public void setCodOper(Integer integer) {
		this.codOper = integer;
	}

	public String getDesOperacao() {
		return this.desOperacao;
	}

	public void setDesOperacao(String desOperacao) {
		this.desOperacao = desOperacao;
	}

	public String getDesSitIpi() {
		return this.desSitIpi;
	}

	public void setDesSitIpi(String desSitIpi) {
		this.desSitIpi = desSitIpi;
	}

	public Date getDtaTransacao() {
		return this.dtaTransacao;
	}

	public void setDtaTransacao(Date dtaTransacao) {
		this.dtaTransacao = dtaTransacao;
	}

	public int getIndConsumidor() {
		return this.indConsumidor;
	}

	public void setIndConsumidor(int i) {
		this.indConsumidor = i;
	}

	public Integer getIndRejeitada() {
		return this.indRejeitada;
	}

	public void setIndRejeitada(Integer i) {
		this.indRejeitada = i;
	}

	public Integer getNumCfop() {
		return this.numCfop;
	}

	public void setNumCfop(Integer integer) {
		this.numCfop = integer;
	}

	public BigDecimal getNumNotaAnt() {
		return this.numNotaAnt;
	}

	public void setNumNotaAnt(BigDecimal numNotaAnt) {
		this.numNotaAnt = numNotaAnt;
	}

	public BigDecimal getPerComissao() {
		return this.perComissao;
	}

	public void setPerComissao(BigDecimal perComissao) {
		this.perComissao = perComissao;
	}

	public BigDecimal getPerComissaoAt() {
		return this.perComissaoAt;
	}

	public void setPerComissaoAt(BigDecimal perComissaoAt) {
		this.perComissaoAt = perComissaoAt;
	}

	public Integer getTipStatusTransacao() {
		return this.tipStatusTransacao;
	}

	public void setTipStatusTransacao(Integer i) {
		this.tipStatusTransacao = i;
	}

	public Integer getTipTransacao() {
		return this.tipTransacao;
	}

	public void setTipTransacao(Integer tipTransacao) {
		this.tipTransacao = tipTransacao;
	}

	public String getTxtErro() {
		return this.txtErro;
	}

	public void setTxtErro(String txtErro) {
		this.txtErro = txtErro;
	}

	public BigDecimal getVlrAcrescimo() {
		return this.vlrAcrescimo;
	}

	public void setVlrAcrescimo(BigDecimal vlrAcrescimo) {
		this.vlrAcrescimo = vlrAcrescimo;
	}

	public BigDecimal getVlrAcrescimoCob() {
		return this.vlrAcrescimoCob;
	}

	public void setVlrAcrescimoCob(BigDecimal vlrAcrescimoCob) {
		this.vlrAcrescimoCob = vlrAcrescimoCob;
	}

	public BigDecimal getVlrBcComissao() {
		return this.vlrBcComissao;
	}

	public void setVlrBcComissao(BigDecimal vlrBcComissao) {
		this.vlrBcComissao = vlrBcComissao;
	}

	public BigDecimal getVlrBcComissaoAt() {
		return this.vlrBcComissaoAt;
	}

	public void setVlrBcComissaoAt(BigDecimal vlrBcComissaoAt) {
		this.vlrBcComissaoAt = vlrBcComissaoAt;
	}

	public BigDecimal getVlrBcIpi() {
		return this.vlrBcIpi;
	}

	public void setVlrBcIpi(BigDecimal vlrBcIpi) {
		this.vlrBcIpi = vlrBcIpi;
	}

	public BigDecimal getVlrCofins() {
		return this.vlrCofins;
	}

	public void setVlrCofins(BigDecimal vlrCofins) {
		this.vlrCofins = vlrCofins;
	}

	public BigDecimal getVlrCofinsRet() {
		return this.vlrCofinsRet;
	}

	public void setVlrCofinsRet(BigDecimal vlrCofinsRet) {
		this.vlrCofinsRet = vlrCofinsRet;
	}

	public BigDecimal getVlrCofinsSt() {
		return this.vlrCofinsSt;
	}

	public void setVlrCofinsSt(BigDecimal vlrCofinsSt) {
		this.vlrCofinsSt = vlrCofinsSt;
	}

	public BigDecimal getVlrComissao() {
		return this.vlrComissao;
	}

	public void setVlrComissao(BigDecimal vlrComissao) {
		this.vlrComissao = vlrComissao;
	}

	public BigDecimal getVlrComissaoAt() {
		return this.vlrComissaoAt;
	}

	public void setVlrComissaoAt(BigDecimal vlrComissaoAt) {
		this.vlrComissaoAt = vlrComissaoAt;
	}

	public BigDecimal getVlrCsllRet() {
		return this.vlrCsllRet;
	}

	public void setVlrCsllRet(BigDecimal vlrCsllRet) {
		this.vlrCsllRet = vlrCsllRet;
	}

	public BigDecimal getVlrDescontoCob() {
		return this.vlrDescontoCob;
	}

	public void setVlrDescontoCob(BigDecimal vlrDescontoCob) {
		this.vlrDescontoCob = vlrDescontoCob;
	}

	public BigDecimal getVlrDesconto1() {
		return this.vlrDesconto1;
	}

	public void setVlrDesconto1(BigDecimal vlrDesconto1) {
		this.vlrDesconto1 = vlrDesconto1;
	}

	public BigDecimal getVlrDesconto2() {
		return this.vlrDesconto2;
	}

	public void setVlrDesconto2(BigDecimal vlrDesconto2) {
		this.vlrDesconto2 = vlrDesconto2;
	}

	public BigDecimal getVlrEntrada() {
		return this.vlrEntrada;
	}

	public void setVlrEntrada(BigDecimal vlrEntrada) {
		this.vlrEntrada = vlrEntrada;
	}

	public BigDecimal getVlrFrete() {
		return this.vlrFrete;
	}

	public void setVlrFrete(BigDecimal vlrFrete) {
		this.vlrFrete = vlrFrete;
	}

	public BigDecimal getVlrFretePago() {
		return this.vlrFretePago;
	}

	public void setVlrFretePago(BigDecimal vlrFretePago) {
		this.vlrFretePago = vlrFretePago;
	}

	public BigDecimal getVlrFunrural() {
		return this.vlrFunrural;
	}

	public void setVlrFunrural(BigDecimal vlrFunrural) {
		this.vlrFunrural = vlrFunrural;
	}

	public BigDecimal getVlrInss() {
		return this.vlrInss;
	}

	public void setVlrInss(BigDecimal vlrInss) {
		this.vlrInss = vlrInss;
	}

	public BigDecimal getVlrIofFinan() {
		return this.vlrIofFinan;
	}

	public void setVlrIofFinan(BigDecimal vlrIofFinan) {
		this.vlrIofFinan = vlrIofFinan;
	}

	public BigDecimal getVlrIpi() {
		return this.vlrIpi;
	}

	public void setVlrIpi(BigDecimal vlrIpi) {
		this.vlrIpi = vlrIpi;
	}

	public BigDecimal getVlrIrrf() {
		return this.vlrIrrf;
	}

	public void setVlrIrrf(BigDecimal vlrIrrf) {
		this.vlrIrrf = vlrIrrf;
	}

	public BigDecimal getVlrIsIpi() {
		return this.vlrIsIpi;
	}

	public void setVlrIsIpi(BigDecimal vlrIsIpi) {
		this.vlrIsIpi = vlrIsIpi;
	}

	public BigDecimal getVlrOperacao() {
		return this.vlrOperacao;
	}

	public void setVlrOperacao(BigDecimal vlrOperacao) {
		this.vlrOperacao = vlrOperacao;
	}

	public BigDecimal getVlrOuIpi() {
		return this.vlrOuIpi;
	}

	public void setVlrOuIpi(BigDecimal vlrOuIpi) {
		this.vlrOuIpi = vlrOuIpi;
	}

	public BigDecimal getVlrOutrosAcre() {
		return this.vlrOutrosAcre;
	}

	public void setVlrOutrosAcre(BigDecimal vlrOutrosAcre) {
		this.vlrOutrosAcre = vlrOutrosAcre;
	}

	public BigDecimal getVlrOutrosDcto() {
		return this.vlrOutrosDcto;
	}

	public void setVlrOutrosDcto(BigDecimal vlrOutrosDcto) {
		this.vlrOutrosDcto = vlrOutrosDcto;
	}

	public BigDecimal getVlrPis() {
		return this.vlrPis;
	}

	public void setVlrPis(BigDecimal vlrPis) {
		this.vlrPis = vlrPis;
	}

	public BigDecimal getVlrPisRet() {
		return this.vlrPisRet;
	}

	public void setVlrPisRet(BigDecimal vlrPisRet) {
		this.vlrPisRet = vlrPisRet;
	}

	public BigDecimal getVlrPisSt() {
		return this.vlrPisSt;
	}

	public void setVlrPisSt(BigDecimal vlrPisSt) {
		this.vlrPisSt = vlrPisSt;
	}

	public BigDecimal getVlrPremio() {
		return this.vlrPremio;
	}

	public void setVlrPremio(BigDecimal vlrPremio) {
		this.vlrPremio = vlrPremio;
	}

	public BigDecimal getVlrPremioAt() {
		return this.vlrPremioAt;
	}

	public void setVlrPremioAt(BigDecimal vlrPremioAt) {
		this.vlrPremioAt = vlrPremioAt;
	}

	public BigDecimal getVlrProdutos() {
		return this.vlrProdutos;
	}

	public void setVlrProdutos(BigDecimal vlrProdutos) {
		this.vlrProdutos = vlrProdutos;
	}

	public BigDecimal getVlrSeguro() {
		return this.vlrSeguro;
	}

	public void setVlrSeguro(BigDecimal vlrSeguro) {
		this.vlrSeguro = vlrSeguro;
	}

	public BigDecimal getVlrTac() {
		return this.vlrTac;
	}

	public void setVlrTac(BigDecimal vlrTac) {
		this.vlrTac = vlrTac;
	}

}