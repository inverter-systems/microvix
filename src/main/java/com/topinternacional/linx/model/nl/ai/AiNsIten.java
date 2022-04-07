package com.topinternacional.linx.model.nl.ai;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the AI_NS_ITENS database table.
 * 
 */
@Entity
@Table(name="AI_NS_ITENS")
@NamedQuery(name="AiNsIten.findAll", query="SELECT a FROM AiNsIten a")
public class AiNsIten implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private AiNsItenPK id;

	@Column(name="COD_AJUSTE")
	private String codAjuste;

	@Column(name="COD_AUTORIZADOR")
	private BigDecimal codAutorizador;

	@Column(name="COD_CAMPANHA")
	private BigDecimal codCampanha;

	@Column(name="COD_CANAL")
	private BigDecimal codCanal;

	@Column(name="COD_CEST")
	private String codCest;

	@Column(name="COD_CFOP")
	private BigDecimal codCfop;

	@Column(name="COD_COMPL_PED")
	private BigDecimal codComplPed;

	@Column(name="COD_CONTRIBUICAO_PISCOF")
	private String codContribuicaoPiscof;

	@Column(name="COD_CONVENIO")
	private BigDecimal codConvenio;

	@Column(name="COD_CONVENIO_AUT")
	private BigDecimal codConvenioAut;

	@Column(name="COD_DCTO_MOTIVO")
	private BigDecimal codDctoMotivo;

	@Column(name="COD_EMP_PED")
	private BigDecimal codEmpPed;

	@Column(name="COD_ENQ_IPI")
	private String codEnqIpi;

	@Column(name="COD_GIA")
	private BigDecimal codGia;

	@Column(name="COD_GR_FISCAL")
	private BigDecimal codGrFiscal;

	@Column(name="COD_ICMS_PDV")
	private String codIcmsPdv;

	@Column(name="COD_ITEM")
	private BigDecimal codItem;

	@Column(name="COD_LISTA_QTD")
	private BigDecimal codListaQtd;

	@Column(name="COD_MAQ_ANT")
	private BigDecimal codMaqAnt;

	@Column(name="COD_MAQ_CE")
	private BigDecimal codMaqCe;

	@Column(name="COD_MAQ_ENT")
	private BigDecimal codMaqEnt;

	@Column(name="COD_MAQ_SEG")
	private BigDecimal codMaqSeg;

	@Column(name="COD_MOT_DES_ICMS")
	private String codMotDesIcms;

	@Column(name="COD_NAT_BC_PISCOF")
	private String codNatBcPiscof;

	@Column(name="COD_NAT_REC_PISCOF")
	private String codNatRecPiscof;

	@Column(name="COD_PESSOA_PLANO")
	private BigDecimal codPessoaPlano;

	@Column(name="COD_PLANO")
	private BigDecimal codPlano;

	@Column(name="COD_SIT_COFINS")
	private String codSitCofins;

	@Column(name="COD_SIT_ICMS")
	private String codSitIcms;

	@Column(name="COD_SIT_ICMS1")
	private String codSitIcms1;

	@Column(name="COD_SIT_IPI")
	private String codSitIpi;

	@Column(name="COD_SIT_PIS")
	private String codSitPis;

	@Column(name="COD_SIT_SIMPLES_NAC")
	private String codSitSimplesNac;

	@Column(name="COD_TIPO_CRED_PISCOF")
	private String codTipoCredPiscof;

	@Column(name="COD_TRIB_ISSQN")
	private String codTribIssqn;

	@Column(name="COD_UNIDADE_PED")
	private BigDecimal codUnidadePed;

	@Column(name="DES_CERTIFICADO_ENSAIO")
	private String desCertificadoEnsaio;

	@Column(name="DES_CHAVE_TRIB")
	private String desChaveTrib;

	@Column(name="DES_COR_LACRE")
	private String desCorLacre;

	@Column(name="DES_INDICADORES_TRIB")
	private String desIndicadoresTrib;

	@Temporal(TemporalType.DATE)
	@Column(name="DTA_TRANSACAO")
	private Date dtaTransacao;

	@Column(name="IDX_BASE_ICMS")
	private BigDecimal idxBaseIcms;

	@Column(name="IDX_SUBS")
	private BigDecimal idxSubs;

	@Column(name="IDX_SUBS_PREV")
	private BigDecimal idxSubsPrev;

	@Column(name="IND_AVULSO")
	private BigDecimal indAvulso;

	@Column(name="IND_CRED_GERADO")
	private BigDecimal indCredGerado;

	@Column(name="IND_CREDPRES")
	private BigDecimal indCredpres;

	@Column(name="IND_ICMS")
	private BigDecimal indIcms;

	@Column(name="IND_IPI")
	private BigDecimal indIpi;

	@Column(name="IND_RED_IPI")
	private BigDecimal indRedIpi;

	@Column(name="IND_REDUCAO")
	private BigDecimal indReducao;

	@Column(name="IND_REDUCAO_BASE_ST")
	private BigDecimal indReducaoBaseSt;

	@Column(name="NUM_AUTORIZADO_TROCA")
	private String numAutorizadoTroca;

	@Column(name="NUM_CARTEIRA")
	private String numCarteira;

	@Column(name="NUM_DENSIDADE")
	private BigDecimal numDensidade;

	@Column(name="NUM_ICCID_CHIP")
	private String numIccidChip;

	@Column(name="NUM_IMEI_CELULAR")
	private String numImeiCelular;

	@Column(name="NUM_ITEM_PED")
	private BigDecimal numItemPed;

	@Column(name="NUM_NOTA_ANT")
	private BigDecimal numNotaAnt;

	@Column(name="NUM_NTC_CHIP")
	private String numNtcChip;

	@Column(name="NUM_PEDIDO_PED")
	private BigDecimal numPedidoPed;

	@Column(name="NUM_SEQ_CE")
	private BigDecimal numSeqCe;

	@Column(name="NUM_SEQ_CONJUNTO")
	private BigDecimal numSeqConjunto;

	@Column(name="NUM_SEQ_DEPENDENTE")
	private BigDecimal numSeqDependente;

	@Column(name="NUM_SEQ_ENT")
	private BigDecimal numSeqEnt;

	@Column(name="NUM_SEQ_SEG")
	private BigDecimal numSeqSeg;

	@Column(name="NUM_SEQ_TITULAR")
	private BigDecimal numSeqTitular;

	@Column(name="NUM_SERIE_ITEM")
	private String numSerieItem;

	@Column(name="NUM_TEMPERATURA")
	private BigDecimal numTemperatura;

	@Column(name="PER_ACRESCIMO")
	private BigDecimal perAcrescimo;

	@Column(name="PER_COFINS")
	private BigDecimal perCofins;

	@Column(name="PER_COFINS_RET")
	private BigDecimal perCofinsRet;

	@Column(name="PER_COFINS_ST")
	private BigDecimal perCofinsSt;

	@Column(name="PER_CSLL_RET")
	private BigDecimal perCsllRet;

	@Column(name="PER_DESCONTO")
	private BigDecimal perDesconto;

	@Column(name="PER_FCP")
	private BigDecimal perFcp;

	@Column(name="PER_FCP_DEST")
	private BigDecimal perFcpDest;

	@Column(name="PER_FCP_ST")
	private BigDecimal perFcpSt;

	@Column(name="PER_FCP_ST_PREV")
	private BigDecimal perFcpStPrev;

	@Column(name="PER_FUNRURAL")
	private BigDecimal perFunrural;

	@Column(name="PER_ICMS")
	private BigDecimal perIcms;

	@Column(name="PER_ICMS_DEST")
	private BigDecimal perIcmsDest;

	@Column(name="PER_ICMS_DIFERIDO")
	private BigDecimal perIcmsDiferido;

	@Column(name="PER_ICMS_INTER")
	private BigDecimal perIcmsInter;

	@Column(name="PER_ICMS_PART")
	private BigDecimal perIcmsPart;

	@Column(name="PER_ICMS_PREV")
	private BigDecimal perIcmsPrev;

	@Column(name="PER_INSS")
	private BigDecimal perInss;

	@Column(name="PER_IRRF")
	private BigDecimal perIrrf;

	@Column(name="PER_PIS")
	private BigDecimal perPis;

	@Column(name="PER_PIS_RET")
	private BigDecimal perPisRet;

	@Column(name="PER_PIS_ST")
	private BigDecimal perPisSt;

	@Column(name="PER_RED_BC_ICMS")
	private BigDecimal perRedBcIcms;

	@Column(name="PER_RED_BC_ICMS_PREV")
	private BigDecimal perRedBcIcmsPrev;

	@Column(name="PER_RED_BC_IPI")
	private BigDecimal perRedBcIpi;

	@Column(name="PER_RED_BC_SUBS")
	private BigDecimal perRedBcSubs;

	@Column(name="PER_RED_BC_SUBST_PREV")
	private BigDecimal perRedBcSubstPrev;

	@Column(name="PER_RED_ICMS")
	private BigDecimal perRedIcms;

	@Column(name="PER_SUBST_TRIB")
	private BigDecimal perSubstTrib;

	@Column(name="PER_SUBST_TRIB_PREV")
	private BigDecimal perSubstTribPrev;

	@Column(name="TIP_STATUS_TRANSACAO")
	private BigDecimal tipStatusTransacao;

	@Column(name="TIP_TRANSACAO")
	private BigDecimal tipTransacao;

	@Column(name="TXT_ERRO")
	private String txtErro;

	@Column(name="VLR_ACRESCIMO")
	private BigDecimal vlrAcrescimo;

	@Column(name="VLR_ACRESCIMO_COB")
	private BigDecimal vlrAcrescimoCob;

	@Column(name="VLR_ACRESCIMO_EMB")
	private BigDecimal vlrAcrescimoEmb;

	@Column(name="VLR_AUTORIZADO_TROCA")
	private BigDecimal vlrAutorizadoTroca;

	@Column(name="VLR_BASE_FUNRURAL")
	private BigDecimal vlrBaseFunrural;

	@Column(name="VLR_BASE_ICMS")
	private BigDecimal vlrBaseIcms;

	@Column(name="VLR_BASE_ICMS_PREV")
	private BigDecimal vlrBaseIcmsPrev;

	@Column(name="VLR_BASE_ICMS_ST_DEST")
	private BigDecimal vlrBaseIcmsStDest;

	@Column(name="VLR_BASE_ICMS_ST_PREV")
	private BigDecimal vlrBaseIcmsStPrev;

	@Column(name="VLR_BASE_INSS")
	private BigDecimal vlrBaseInss;

	@Column(name="VLR_BASE_IPI")
	private BigDecimal vlrBaseIpi;

	@Column(name="VLR_BASE_IRRF")
	private BigDecimal vlrBaseIrrf;

	@Column(name="VLR_BASE_PC")
	private BigDecimal vlrBasePc;

	@Column(name="VLR_BASE_PIS_COF")
	private BigDecimal vlrBasePisCof;

	@Column(name="VLR_BC_FCP")
	private BigDecimal vlrBcFcp;

	@Column(name="VLR_BC_FCP_ST")
	private BigDecimal vlrBcFcpSt;

	@Column(name="VLR_BC_FCP_ST_PREV")
	private BigDecimal vlrBcFcpStPrev;

	@Column(name="VLR_COFINS")
	private BigDecimal vlrCofins;

	@Column(name="VLR_COFINS_CC")
	private BigDecimal vlrCofinsCc;

	@Column(name="VLR_COFINS_RET")
	private BigDecimal vlrCofinsRet;

	@Column(name="VLR_COFINS_RET_TDC")
	private BigDecimal vlrCofinsRetTdc;

	@Column(name="VLR_COFINS_ST")
	private BigDecimal vlrCofinsSt;

	@Column(name="VLR_CONTR_COBERTURA")
	private BigDecimal vlrContrCobertura;

	@Column(name="VLR_CSLL_RET")
	private BigDecimal vlrCsllRet;

	@Column(name="VLR_CSLL_RET_TDC")
	private BigDecimal vlrCsllRetTdc;

	@Column(name="VLR_CUSTO_BONIFICACAO")
	private BigDecimal vlrCustoBonificacao;

	@Column(name="VLR_CUSTO_COMPRA")
	private BigDecimal vlrCustoCompra;

	@Column(name="VLR_CUSTO_ESTOCAGEM")
	private BigDecimal vlrCustoEstocagem;

	@Column(name="VLR_DESCONTO")
	private BigDecimal vlrDesconto;

	@Column(name="VLR_DESCONTO_COB")
	private BigDecimal vlrDescontoCob;

	@Column(name="VLR_DESCONTO_EMB")
	private BigDecimal vlrDescontoEmb;

	@Column(name="VLR_DIF_ICMS")
	private BigDecimal vlrDifIcms;

	@Column(name="VLR_DIF_ICMS_BC")
	private BigDecimal vlrDifIcmsBc;

	@Column(name="VLR_FCP")
	private BigDecimal vlrFcp;

	@Column(name="VLR_FCP_DEST")
	private BigDecimal vlrFcpDest;

	@Column(name="VLR_FCP_ST")
	private BigDecimal vlrFcpSt;

	@Column(name="VLR_FCP_ST_PREV")
	private BigDecimal vlrFcpStPrev;

	@Column(name="VLR_FRETE")
	private BigDecimal vlrFrete;

	@Column(name="VLR_GARANTIA")
	private BigDecimal vlrGarantia;

	@Column(name="VLR_ICMS_CC")
	private BigDecimal vlrIcmsCc;

	@Column(name="VLR_ICMS_DEST")
	private BigDecimal vlrIcmsDest;

	@Column(name="VLR_ICMS_DIFERIDO")
	private BigDecimal vlrIcmsDiferido;

	@Column(name="VLR_ICMS_REMET")
	private BigDecimal vlrIcmsRemet;

	@Column(name="VLR_ICMS_ST_DEST")
	private BigDecimal vlrIcmsStDest;

	@Column(name="VLR_ICMS_ST_PREV")
	private BigDecimal vlrIcmsStPrev;

	@Column(name="VLR_INSS_TDC")
	private BigDecimal vlrInssTdc;

	@Column(name="VLR_IOF_FINAN")
	private BigDecimal vlrIofFinan;

	@Column(name="VLR_IRRF_TDC")
	private BigDecimal vlrIrrfTdc;

	@Column(name="VLR_ISENTAS_ICMS")
	private BigDecimal vlrIsentasIcms;

	@Column(name="VLR_ISENTAS_IPI")
	private BigDecimal vlrIsentasIpi;

	@Column(name="VLR_NAO_INCIDENTE")
	private BigDecimal vlrNaoIncidente;

	@Column(name="VLR_OUTRAS_ICMS")
	private BigDecimal vlrOutrasIcms;

	@Column(name="VLR_OUTRAS_IPI")
	private BigDecimal vlrOutrasIpi;

	@Column(name="VLR_PIS")
	private BigDecimal vlrPis;

	@Column(name="VLR_PIS_CC")
	private BigDecimal vlrPisCc;

	@Column(name="VLR_PIS_RET")
	private BigDecimal vlrPisRet;

	@Column(name="VLR_PIS_RET_TDC")
	private BigDecimal vlrPisRetTdc;

	@Column(name="VLR_PIS_ST")
	private BigDecimal vlrPisSt;

	@Column(name="VLR_RED_BC_ICMS")
	private BigDecimal vlrRedBcIcms;

	@Column(name="VLR_RED_BC_IPI")
	private BigDecimal vlrRedBcIpi;

	@Column(name="VLR_RED_BC_SUBS")
	private BigDecimal vlrRedBcSubs;

	@Column(name="VLR_SEGURO")
	private BigDecimal vlrSeguro;

	@Column(name="VLR_TOTAL")
	private BigDecimal vlrTotal;

	@Column(name="VLR_TOTAL_NF")
	private BigDecimal vlrTotalNf;

	@Column(name="VLR_UNI_BRUTO")
	private BigDecimal vlrUniBruto;

	@Column(name="VLR_UNI_NF")
	private BigDecimal vlrUniNf;

	@Column(name="VLR_UNITARIO")
	private BigDecimal vlrUnitario;

	public AiNsIten() {
	}

	public AiNsItenPK getId() {
		return this.id;
	}

	public void setId(AiNsItenPK id) {
		this.id = id;
	}

	public String getCodAjuste() {
		return this.codAjuste;
	}

	public void setCodAjuste(String codAjuste) {
		this.codAjuste = codAjuste;
	}

	public BigDecimal getCodAutorizador() {
		return this.codAutorizador;
	}

	public void setCodAutorizador(BigDecimal codAutorizador) {
		this.codAutorizador = codAutorizador;
	}

	public BigDecimal getCodCampanha() {
		return this.codCampanha;
	}

	public void setCodCampanha(BigDecimal codCampanha) {
		this.codCampanha = codCampanha;
	}

	public BigDecimal getCodCanal() {
		return this.codCanal;
	}

	public void setCodCanal(BigDecimal codCanal) {
		this.codCanal = codCanal;
	}

	public String getCodCest() {
		return this.codCest;
	}

	public void setCodCest(String codCest) {
		this.codCest = codCest;
	}

	public BigDecimal getCodCfop() {
		return this.codCfop;
	}

	public void setCodCfop(BigDecimal codCfop) {
		this.codCfop = codCfop;
	}

	public BigDecimal getCodComplPed() {
		return this.codComplPed;
	}

	public void setCodComplPed(BigDecimal codComplPed) {
		this.codComplPed = codComplPed;
	}

	public String getCodContribuicaoPiscof() {
		return this.codContribuicaoPiscof;
	}

	public void setCodContribuicaoPiscof(String codContribuicaoPiscof) {
		this.codContribuicaoPiscof = codContribuicaoPiscof;
	}

	public BigDecimal getCodConvenio() {
		return this.codConvenio;
	}

	public void setCodConvenio(BigDecimal codConvenio) {
		this.codConvenio = codConvenio;
	}

	public BigDecimal getCodConvenioAut() {
		return this.codConvenioAut;
	}

	public void setCodConvenioAut(BigDecimal codConvenioAut) {
		this.codConvenioAut = codConvenioAut;
	}

	public BigDecimal getCodDctoMotivo() {
		return this.codDctoMotivo;
	}

	public void setCodDctoMotivo(BigDecimal codDctoMotivo) {
		this.codDctoMotivo = codDctoMotivo;
	}

	public BigDecimal getCodEmpPed() {
		return this.codEmpPed;
	}

	public void setCodEmpPed(BigDecimal codEmpPed) {
		this.codEmpPed = codEmpPed;
	}

	public String getCodEnqIpi() {
		return this.codEnqIpi;
	}

	public void setCodEnqIpi(String codEnqIpi) {
		this.codEnqIpi = codEnqIpi;
	}

	public BigDecimal getCodGia() {
		return this.codGia;
	}

	public void setCodGia(BigDecimal codGia) {
		this.codGia = codGia;
	}

	public BigDecimal getCodGrFiscal() {
		return this.codGrFiscal;
	}

	public void setCodGrFiscal(BigDecimal codGrFiscal) {
		this.codGrFiscal = codGrFiscal;
	}

	public String getCodIcmsPdv() {
		return this.codIcmsPdv;
	}

	public void setCodIcmsPdv(String codIcmsPdv) {
		this.codIcmsPdv = codIcmsPdv;
	}

	public BigDecimal getCodItem() {
		return this.codItem;
	}

	public void setCodItem(BigDecimal codItem) {
		this.codItem = codItem;
	}

	public BigDecimal getCodListaQtd() {
		return this.codListaQtd;
	}

	public void setCodListaQtd(BigDecimal codListaQtd) {
		this.codListaQtd = codListaQtd;
	}

	public BigDecimal getCodMaqAnt() {
		return this.codMaqAnt;
	}

	public void setCodMaqAnt(BigDecimal codMaqAnt) {
		this.codMaqAnt = codMaqAnt;
	}

	public BigDecimal getCodMaqCe() {
		return this.codMaqCe;
	}

	public void setCodMaqCe(BigDecimal codMaqCe) {
		this.codMaqCe = codMaqCe;
	}

	public BigDecimal getCodMaqEnt() {
		return this.codMaqEnt;
	}

	public void setCodMaqEnt(BigDecimal codMaqEnt) {
		this.codMaqEnt = codMaqEnt;
	}

	public BigDecimal getCodMaqSeg() {
		return this.codMaqSeg;
	}

	public void setCodMaqSeg(BigDecimal codMaqSeg) {
		this.codMaqSeg = codMaqSeg;
	}

	public String getCodMotDesIcms() {
		return this.codMotDesIcms;
	}

	public void setCodMotDesIcms(String codMotDesIcms) {
		this.codMotDesIcms = codMotDesIcms;
	}

	public String getCodNatBcPiscof() {
		return this.codNatBcPiscof;
	}

	public void setCodNatBcPiscof(String codNatBcPiscof) {
		this.codNatBcPiscof = codNatBcPiscof;
	}

	public String getCodNatRecPiscof() {
		return this.codNatRecPiscof;
	}

	public void setCodNatRecPiscof(String codNatRecPiscof) {
		this.codNatRecPiscof = codNatRecPiscof;
	}

	public BigDecimal getCodPessoaPlano() {
		return this.codPessoaPlano;
	}

	public void setCodPessoaPlano(BigDecimal codPessoaPlano) {
		this.codPessoaPlano = codPessoaPlano;
	}

	public BigDecimal getCodPlano() {
		return this.codPlano;
	}

	public void setCodPlano(BigDecimal codPlano) {
		this.codPlano = codPlano;
	}

	public String getCodSitCofins() {
		return this.codSitCofins;
	}

	public void setCodSitCofins(String codSitCofins) {
		this.codSitCofins = codSitCofins;
	}

	public String getCodSitIcms() {
		return this.codSitIcms;
	}

	public void setCodSitIcms(String codSitIcms) {
		this.codSitIcms = codSitIcms;
	}

	public String getCodSitIcms1() {
		return this.codSitIcms1;
	}

	public void setCodSitIcms1(String codSitIcms1) {
		this.codSitIcms1 = codSitIcms1;
	}

	public String getCodSitIpi() {
		return this.codSitIpi;
	}

	public void setCodSitIpi(String codSitIpi) {
		this.codSitIpi = codSitIpi;
	}

	public String getCodSitPis() {
		return this.codSitPis;
	}

	public void setCodSitPis(String codSitPis) {
		this.codSitPis = codSitPis;
	}

	public String getCodSitSimplesNac() {
		return this.codSitSimplesNac;
	}

	public void setCodSitSimplesNac(String codSitSimplesNac) {
		this.codSitSimplesNac = codSitSimplesNac;
	}

	public String getCodTipoCredPiscof() {
		return this.codTipoCredPiscof;
	}

	public void setCodTipoCredPiscof(String codTipoCredPiscof) {
		this.codTipoCredPiscof = codTipoCredPiscof;
	}

	public String getCodTribIssqn() {
		return this.codTribIssqn;
	}

	public void setCodTribIssqn(String codTribIssqn) {
		this.codTribIssqn = codTribIssqn;
	}

	public BigDecimal getCodUnidadePed() {
		return this.codUnidadePed;
	}

	public void setCodUnidadePed(BigDecimal codUnidadePed) {
		this.codUnidadePed = codUnidadePed;
	}

	public String getDesCertificadoEnsaio() {
		return this.desCertificadoEnsaio;
	}

	public void setDesCertificadoEnsaio(String desCertificadoEnsaio) {
		this.desCertificadoEnsaio = desCertificadoEnsaio;
	}

	public String getDesChaveTrib() {
		return this.desChaveTrib;
	}

	public void setDesChaveTrib(String desChaveTrib) {
		this.desChaveTrib = desChaveTrib;
	}

	public String getDesCorLacre() {
		return this.desCorLacre;
	}

	public void setDesCorLacre(String desCorLacre) {
		this.desCorLacre = desCorLacre;
	}

	public String getDesIndicadoresTrib() {
		return this.desIndicadoresTrib;
	}

	public void setDesIndicadoresTrib(String desIndicadoresTrib) {
		this.desIndicadoresTrib = desIndicadoresTrib;
	}

	public Date getDtaTransacao() {
		return this.dtaTransacao;
	}

	public void setDtaTransacao(Date dtaTransacao) {
		this.dtaTransacao = dtaTransacao;
	}

	public BigDecimal getIdxBaseIcms() {
		return this.idxBaseIcms;
	}

	public void setIdxBaseIcms(BigDecimal idxBaseIcms) {
		this.idxBaseIcms = idxBaseIcms;
	}

	public BigDecimal getIdxSubs() {
		return this.idxSubs;
	}

	public void setIdxSubs(BigDecimal idxSubs) {
		this.idxSubs = idxSubs;
	}

	public BigDecimal getIdxSubsPrev() {
		return this.idxSubsPrev;
	}

	public void setIdxSubsPrev(BigDecimal idxSubsPrev) {
		this.idxSubsPrev = idxSubsPrev;
	}

	public BigDecimal getIndAvulso() {
		return this.indAvulso;
	}

	public void setIndAvulso(BigDecimal indAvulso) {
		this.indAvulso = indAvulso;
	}

	public BigDecimal getIndCredGerado() {
		return this.indCredGerado;
	}

	public void setIndCredGerado(BigDecimal indCredGerado) {
		this.indCredGerado = indCredGerado;
	}

	public BigDecimal getIndCredpres() {
		return this.indCredpres;
	}

	public void setIndCredpres(BigDecimal indCredpres) {
		this.indCredpres = indCredpres;
	}

	public BigDecimal getIndIcms() {
		return this.indIcms;
	}

	public void setIndIcms(BigDecimal indIcms) {
		this.indIcms = indIcms;
	}

	public BigDecimal getIndIpi() {
		return this.indIpi;
	}

	public void setIndIpi(BigDecimal indIpi) {
		this.indIpi = indIpi;
	}

	public BigDecimal getIndRedIpi() {
		return this.indRedIpi;
	}

	public void setIndRedIpi(BigDecimal indRedIpi) {
		this.indRedIpi = indRedIpi;
	}

	public BigDecimal getIndReducao() {
		return this.indReducao;
	}

	public void setIndReducao(BigDecimal indReducao) {
		this.indReducao = indReducao;
	}

	public BigDecimal getIndReducaoBaseSt() {
		return this.indReducaoBaseSt;
	}

	public void setIndReducaoBaseSt(BigDecimal indReducaoBaseSt) {
		this.indReducaoBaseSt = indReducaoBaseSt;
	}

	public String getNumAutorizadoTroca() {
		return this.numAutorizadoTroca;
	}

	public void setNumAutorizadoTroca(String numAutorizadoTroca) {
		this.numAutorizadoTroca = numAutorizadoTroca;
	}

	public String getNumCarteira() {
		return this.numCarteira;
	}

	public void setNumCarteira(String numCarteira) {
		this.numCarteira = numCarteira;
	}

	public BigDecimal getNumDensidade() {
		return this.numDensidade;
	}

	public void setNumDensidade(BigDecimal numDensidade) {
		this.numDensidade = numDensidade;
	}

	public String getNumIccidChip() {
		return this.numIccidChip;
	}

	public void setNumIccidChip(String numIccidChip) {
		this.numIccidChip = numIccidChip;
	}

	public String getNumImeiCelular() {
		return this.numImeiCelular;
	}

	public void setNumImeiCelular(String numImeiCelular) {
		this.numImeiCelular = numImeiCelular;
	}

	public BigDecimal getNumItemPed() {
		return this.numItemPed;
	}

	public void setNumItemPed(BigDecimal numItemPed) {
		this.numItemPed = numItemPed;
	}

	public BigDecimal getNumNotaAnt() {
		return this.numNotaAnt;
	}

	public void setNumNotaAnt(BigDecimal numNotaAnt) {
		this.numNotaAnt = numNotaAnt;
	}

	public String getNumNtcChip() {
		return this.numNtcChip;
	}

	public void setNumNtcChip(String numNtcChip) {
		this.numNtcChip = numNtcChip;
	}

	public BigDecimal getNumPedidoPed() {
		return this.numPedidoPed;
	}

	public void setNumPedidoPed(BigDecimal numPedidoPed) {
		this.numPedidoPed = numPedidoPed;
	}

	public BigDecimal getNumSeqCe() {
		return this.numSeqCe;
	}

	public void setNumSeqCe(BigDecimal numSeqCe) {
		this.numSeqCe = numSeqCe;
	}

	public BigDecimal getNumSeqConjunto() {
		return this.numSeqConjunto;
	}

	public void setNumSeqConjunto(BigDecimal numSeqConjunto) {
		this.numSeqConjunto = numSeqConjunto;
	}

	public BigDecimal getNumSeqDependente() {
		return this.numSeqDependente;
	}

	public void setNumSeqDependente(BigDecimal numSeqDependente) {
		this.numSeqDependente = numSeqDependente;
	}

	public BigDecimal getNumSeqEnt() {
		return this.numSeqEnt;
	}

	public void setNumSeqEnt(BigDecimal numSeqEnt) {
		this.numSeqEnt = numSeqEnt;
	}

	public BigDecimal getNumSeqSeg() {
		return this.numSeqSeg;
	}

	public void setNumSeqSeg(BigDecimal numSeqSeg) {
		this.numSeqSeg = numSeqSeg;
	}

	public BigDecimal getNumSeqTitular() {
		return this.numSeqTitular;
	}

	public void setNumSeqTitular(BigDecimal numSeqTitular) {
		this.numSeqTitular = numSeqTitular;
	}

	public String getNumSerieItem() {
		return this.numSerieItem;
	}

	public void setNumSerieItem(String numSerieItem) {
		this.numSerieItem = numSerieItem;
	}

	public BigDecimal getNumTemperatura() {
		return this.numTemperatura;
	}

	public void setNumTemperatura(BigDecimal numTemperatura) {
		this.numTemperatura = numTemperatura;
	}

	public BigDecimal getPerAcrescimo() {
		return this.perAcrescimo;
	}

	public void setPerAcrescimo(BigDecimal perAcrescimo) {
		this.perAcrescimo = perAcrescimo;
	}

	public BigDecimal getPerCofins() {
		return this.perCofins;
	}

	public void setPerCofins(BigDecimal perCofins) {
		this.perCofins = perCofins;
	}

	public BigDecimal getPerCofinsRet() {
		return this.perCofinsRet;
	}

	public void setPerCofinsRet(BigDecimal perCofinsRet) {
		this.perCofinsRet = perCofinsRet;
	}

	public BigDecimal getPerCofinsSt() {
		return this.perCofinsSt;
	}

	public void setPerCofinsSt(BigDecimal perCofinsSt) {
		this.perCofinsSt = perCofinsSt;
	}

	public BigDecimal getPerCsllRet() {
		return this.perCsllRet;
	}

	public void setPerCsllRet(BigDecimal perCsllRet) {
		this.perCsllRet = perCsllRet;
	}

	public BigDecimal getPerDesconto() {
		return this.perDesconto;
	}

	public void setPerDesconto(BigDecimal perDesconto) {
		this.perDesconto = perDesconto;
	}

	public BigDecimal getPerFcp() {
		return this.perFcp;
	}

	public void setPerFcp(BigDecimal perFcp) {
		this.perFcp = perFcp;
	}

	public BigDecimal getPerFcpDest() {
		return this.perFcpDest;
	}

	public void setPerFcpDest(BigDecimal perFcpDest) {
		this.perFcpDest = perFcpDest;
	}

	public BigDecimal getPerFcpSt() {
		return this.perFcpSt;
	}

	public void setPerFcpSt(BigDecimal perFcpSt) {
		this.perFcpSt = perFcpSt;
	}

	public BigDecimal getPerFcpStPrev() {
		return this.perFcpStPrev;
	}

	public void setPerFcpStPrev(BigDecimal perFcpStPrev) {
		this.perFcpStPrev = perFcpStPrev;
	}

	public BigDecimal getPerFunrural() {
		return this.perFunrural;
	}

	public void setPerFunrural(BigDecimal perFunrural) {
		this.perFunrural = perFunrural;
	}

	public BigDecimal getPerIcms() {
		return this.perIcms;
	}

	public void setPerIcms(BigDecimal perIcms) {
		this.perIcms = perIcms;
	}

	public BigDecimal getPerIcmsDest() {
		return this.perIcmsDest;
	}

	public void setPerIcmsDest(BigDecimal perIcmsDest) {
		this.perIcmsDest = perIcmsDest;
	}

	public BigDecimal getPerIcmsDiferido() {
		return this.perIcmsDiferido;
	}

	public void setPerIcmsDiferido(BigDecimal perIcmsDiferido) {
		this.perIcmsDiferido = perIcmsDiferido;
	}

	public BigDecimal getPerIcmsInter() {
		return this.perIcmsInter;
	}

	public void setPerIcmsInter(BigDecimal perIcmsInter) {
		this.perIcmsInter = perIcmsInter;
	}

	public BigDecimal getPerIcmsPart() {
		return this.perIcmsPart;
	}

	public void setPerIcmsPart(BigDecimal perIcmsPart) {
		this.perIcmsPart = perIcmsPart;
	}

	public BigDecimal getPerIcmsPrev() {
		return this.perIcmsPrev;
	}

	public void setPerIcmsPrev(BigDecimal perIcmsPrev) {
		this.perIcmsPrev = perIcmsPrev;
	}

	public BigDecimal getPerInss() {
		return this.perInss;
	}

	public void setPerInss(BigDecimal perInss) {
		this.perInss = perInss;
	}

	public BigDecimal getPerIrrf() {
		return this.perIrrf;
	}

	public void setPerIrrf(BigDecimal perIrrf) {
		this.perIrrf = perIrrf;
	}

	public BigDecimal getPerPis() {
		return this.perPis;
	}

	public void setPerPis(BigDecimal perPis) {
		this.perPis = perPis;
	}

	public BigDecimal getPerPisRet() {
		return this.perPisRet;
	}

	public void setPerPisRet(BigDecimal perPisRet) {
		this.perPisRet = perPisRet;
	}

	public BigDecimal getPerPisSt() {
		return this.perPisSt;
	}

	public void setPerPisSt(BigDecimal perPisSt) {
		this.perPisSt = perPisSt;
	}

	public BigDecimal getPerRedBcIcms() {
		return this.perRedBcIcms;
	}

	public void setPerRedBcIcms(BigDecimal perRedBcIcms) {
		this.perRedBcIcms = perRedBcIcms;
	}

	public BigDecimal getPerRedBcIcmsPrev() {
		return this.perRedBcIcmsPrev;
	}

	public void setPerRedBcIcmsPrev(BigDecimal perRedBcIcmsPrev) {
		this.perRedBcIcmsPrev = perRedBcIcmsPrev;
	}

	public BigDecimal getPerRedBcIpi() {
		return this.perRedBcIpi;
	}

	public void setPerRedBcIpi(BigDecimal perRedBcIpi) {
		this.perRedBcIpi = perRedBcIpi;
	}

	public BigDecimal getPerRedBcSubs() {
		return this.perRedBcSubs;
	}

	public void setPerRedBcSubs(BigDecimal perRedBcSubs) {
		this.perRedBcSubs = perRedBcSubs;
	}

	public BigDecimal getPerRedBcSubstPrev() {
		return this.perRedBcSubstPrev;
	}

	public void setPerRedBcSubstPrev(BigDecimal perRedBcSubstPrev) {
		this.perRedBcSubstPrev = perRedBcSubstPrev;
	}

	public BigDecimal getPerRedIcms() {
		return this.perRedIcms;
	}

	public void setPerRedIcms(BigDecimal perRedIcms) {
		this.perRedIcms = perRedIcms;
	}

	public BigDecimal getPerSubstTrib() {
		return this.perSubstTrib;
	}

	public void setPerSubstTrib(BigDecimal perSubstTrib) {
		this.perSubstTrib = perSubstTrib;
	}

	public BigDecimal getPerSubstTribPrev() {
		return this.perSubstTribPrev;
	}

	public void setPerSubstTribPrev(BigDecimal perSubstTribPrev) {
		this.perSubstTribPrev = perSubstTribPrev;
	}

	public BigDecimal getTipStatusTransacao() {
		return this.tipStatusTransacao;
	}

	public void setTipStatusTransacao(BigDecimal tipStatusTransacao) {
		this.tipStatusTransacao = tipStatusTransacao;
	}

	public BigDecimal getTipTransacao() {
		return this.tipTransacao;
	}

	public void setTipTransacao(BigDecimal tipTransacao) {
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

	public BigDecimal getVlrAcrescimoEmb() {
		return this.vlrAcrescimoEmb;
	}

	public void setVlrAcrescimoEmb(BigDecimal vlrAcrescimoEmb) {
		this.vlrAcrescimoEmb = vlrAcrescimoEmb;
	}

	public BigDecimal getVlrAutorizadoTroca() {
		return this.vlrAutorizadoTroca;
	}

	public void setVlrAutorizadoTroca(BigDecimal vlrAutorizadoTroca) {
		this.vlrAutorizadoTroca = vlrAutorizadoTroca;
	}

	public BigDecimal getVlrBaseFunrural() {
		return this.vlrBaseFunrural;
	}

	public void setVlrBaseFunrural(BigDecimal vlrBaseFunrural) {
		this.vlrBaseFunrural = vlrBaseFunrural;
	}

	public BigDecimal getVlrBaseIcms() {
		return this.vlrBaseIcms;
	}

	public void setVlrBaseIcms(BigDecimal vlrBaseIcms) {
		this.vlrBaseIcms = vlrBaseIcms;
	}

	public BigDecimal getVlrBaseIcmsPrev() {
		return this.vlrBaseIcmsPrev;
	}

	public void setVlrBaseIcmsPrev(BigDecimal vlrBaseIcmsPrev) {
		this.vlrBaseIcmsPrev = vlrBaseIcmsPrev;
	}

	public BigDecimal getVlrBaseIcmsStDest() {
		return this.vlrBaseIcmsStDest;
	}

	public void setVlrBaseIcmsStDest(BigDecimal vlrBaseIcmsStDest) {
		this.vlrBaseIcmsStDest = vlrBaseIcmsStDest;
	}

	public BigDecimal getVlrBaseIcmsStPrev() {
		return this.vlrBaseIcmsStPrev;
	}

	public void setVlrBaseIcmsStPrev(BigDecimal vlrBaseIcmsStPrev) {
		this.vlrBaseIcmsStPrev = vlrBaseIcmsStPrev;
	}

	public BigDecimal getVlrBaseInss() {
		return this.vlrBaseInss;
	}

	public void setVlrBaseInss(BigDecimal vlrBaseInss) {
		this.vlrBaseInss = vlrBaseInss;
	}

	public BigDecimal getVlrBaseIpi() {
		return this.vlrBaseIpi;
	}

	public void setVlrBaseIpi(BigDecimal vlrBaseIpi) {
		this.vlrBaseIpi = vlrBaseIpi;
	}

	public BigDecimal getVlrBaseIrrf() {
		return this.vlrBaseIrrf;
	}

	public void setVlrBaseIrrf(BigDecimal vlrBaseIrrf) {
		this.vlrBaseIrrf = vlrBaseIrrf;
	}

	public BigDecimal getVlrBasePc() {
		return this.vlrBasePc;
	}

	public void setVlrBasePc(BigDecimal vlrBasePc) {
		this.vlrBasePc = vlrBasePc;
	}

	public BigDecimal getVlrBasePisCof() {
		return this.vlrBasePisCof;
	}

	public void setVlrBasePisCof(BigDecimal vlrBasePisCof) {
		this.vlrBasePisCof = vlrBasePisCof;
	}

	public BigDecimal getVlrBcFcp() {
		return this.vlrBcFcp;
	}

	public void setVlrBcFcp(BigDecimal vlrBcFcp) {
		this.vlrBcFcp = vlrBcFcp;
	}

	public BigDecimal getVlrBcFcpSt() {
		return this.vlrBcFcpSt;
	}

	public void setVlrBcFcpSt(BigDecimal vlrBcFcpSt) {
		this.vlrBcFcpSt = vlrBcFcpSt;
	}

	public BigDecimal getVlrBcFcpStPrev() {
		return this.vlrBcFcpStPrev;
	}

	public void setVlrBcFcpStPrev(BigDecimal vlrBcFcpStPrev) {
		this.vlrBcFcpStPrev = vlrBcFcpStPrev;
	}

	public BigDecimal getVlrCofins() {
		return this.vlrCofins;
	}

	public void setVlrCofins(BigDecimal vlrCofins) {
		this.vlrCofins = vlrCofins;
	}

	public BigDecimal getVlrCofinsCc() {
		return this.vlrCofinsCc;
	}

	public void setVlrCofinsCc(BigDecimal vlrCofinsCc) {
		this.vlrCofinsCc = vlrCofinsCc;
	}

	public BigDecimal getVlrCofinsRet() {
		return this.vlrCofinsRet;
	}

	public void setVlrCofinsRet(BigDecimal vlrCofinsRet) {
		this.vlrCofinsRet = vlrCofinsRet;
	}

	public BigDecimal getVlrCofinsRetTdc() {
		return this.vlrCofinsRetTdc;
	}

	public void setVlrCofinsRetTdc(BigDecimal vlrCofinsRetTdc) {
		this.vlrCofinsRetTdc = vlrCofinsRetTdc;
	}

	public BigDecimal getVlrCofinsSt() {
		return this.vlrCofinsSt;
	}

	public void setVlrCofinsSt(BigDecimal vlrCofinsSt) {
		this.vlrCofinsSt = vlrCofinsSt;
	}

	public BigDecimal getVlrContrCobertura() {
		return this.vlrContrCobertura;
	}

	public void setVlrContrCobertura(BigDecimal vlrContrCobertura) {
		this.vlrContrCobertura = vlrContrCobertura;
	}

	public BigDecimal getVlrCsllRet() {
		return this.vlrCsllRet;
	}

	public void setVlrCsllRet(BigDecimal vlrCsllRet) {
		this.vlrCsllRet = vlrCsllRet;
	}

	public BigDecimal getVlrCsllRetTdc() {
		return this.vlrCsllRetTdc;
	}

	public void setVlrCsllRetTdc(BigDecimal vlrCsllRetTdc) {
		this.vlrCsllRetTdc = vlrCsllRetTdc;
	}

	public BigDecimal getVlrCustoBonificacao() {
		return this.vlrCustoBonificacao;
	}

	public void setVlrCustoBonificacao(BigDecimal vlrCustoBonificacao) {
		this.vlrCustoBonificacao = vlrCustoBonificacao;
	}

	public BigDecimal getVlrCustoCompra() {
		return this.vlrCustoCompra;
	}

	public void setVlrCustoCompra(BigDecimal vlrCustoCompra) {
		this.vlrCustoCompra = vlrCustoCompra;
	}

	public BigDecimal getVlrCustoEstocagem() {
		return this.vlrCustoEstocagem;
	}

	public void setVlrCustoEstocagem(BigDecimal vlrCustoEstocagem) {
		this.vlrCustoEstocagem = vlrCustoEstocagem;
	}

	public BigDecimal getVlrDesconto() {
		return this.vlrDesconto;
	}

	public void setVlrDesconto(BigDecimal vlrDesconto) {
		this.vlrDesconto = vlrDesconto;
	}

	public BigDecimal getVlrDescontoCob() {
		return this.vlrDescontoCob;
	}

	public void setVlrDescontoCob(BigDecimal vlrDescontoCob) {
		this.vlrDescontoCob = vlrDescontoCob;
	}

	public BigDecimal getVlrDescontoEmb() {
		return this.vlrDescontoEmb;
	}

	public void setVlrDescontoEmb(BigDecimal vlrDescontoEmb) {
		this.vlrDescontoEmb = vlrDescontoEmb;
	}

	public BigDecimal getVlrDifIcms() {
		return this.vlrDifIcms;
	}

	public void setVlrDifIcms(BigDecimal vlrDifIcms) {
		this.vlrDifIcms = vlrDifIcms;
	}

	public BigDecimal getVlrDifIcmsBc() {
		return this.vlrDifIcmsBc;
	}

	public void setVlrDifIcmsBc(BigDecimal vlrDifIcmsBc) {
		this.vlrDifIcmsBc = vlrDifIcmsBc;
	}

	public BigDecimal getVlrFcp() {
		return this.vlrFcp;
	}

	public void setVlrFcp(BigDecimal vlrFcp) {
		this.vlrFcp = vlrFcp;
	}

	public BigDecimal getVlrFcpDest() {
		return this.vlrFcpDest;
	}

	public void setVlrFcpDest(BigDecimal vlrFcpDest) {
		this.vlrFcpDest = vlrFcpDest;
	}

	public BigDecimal getVlrFcpSt() {
		return this.vlrFcpSt;
	}

	public void setVlrFcpSt(BigDecimal vlrFcpSt) {
		this.vlrFcpSt = vlrFcpSt;
	}

	public BigDecimal getVlrFcpStPrev() {
		return this.vlrFcpStPrev;
	}

	public void setVlrFcpStPrev(BigDecimal vlrFcpStPrev) {
		this.vlrFcpStPrev = vlrFcpStPrev;
	}

	public BigDecimal getVlrFrete() {
		return this.vlrFrete;
	}

	public void setVlrFrete(BigDecimal vlrFrete) {
		this.vlrFrete = vlrFrete;
	}

	public BigDecimal getVlrGarantia() {
		return this.vlrGarantia;
	}

	public void setVlrGarantia(BigDecimal vlrGarantia) {
		this.vlrGarantia = vlrGarantia;
	}

	public BigDecimal getVlrIcmsCc() {
		return this.vlrIcmsCc;
	}

	public void setVlrIcmsCc(BigDecimal vlrIcmsCc) {
		this.vlrIcmsCc = vlrIcmsCc;
	}

	public BigDecimal getVlrIcmsDest() {
		return this.vlrIcmsDest;
	}

	public void setVlrIcmsDest(BigDecimal vlrIcmsDest) {
		this.vlrIcmsDest = vlrIcmsDest;
	}

	public BigDecimal getVlrIcmsDiferido() {
		return this.vlrIcmsDiferido;
	}

	public void setVlrIcmsDiferido(BigDecimal vlrIcmsDiferido) {
		this.vlrIcmsDiferido = vlrIcmsDiferido;
	}

	public BigDecimal getVlrIcmsRemet() {
		return this.vlrIcmsRemet;
	}

	public void setVlrIcmsRemet(BigDecimal vlrIcmsRemet) {
		this.vlrIcmsRemet = vlrIcmsRemet;
	}

	public BigDecimal getVlrIcmsStDest() {
		return this.vlrIcmsStDest;
	}

	public void setVlrIcmsStDest(BigDecimal vlrIcmsStDest) {
		this.vlrIcmsStDest = vlrIcmsStDest;
	}

	public BigDecimal getVlrIcmsStPrev() {
		return this.vlrIcmsStPrev;
	}

	public void setVlrIcmsStPrev(BigDecimal vlrIcmsStPrev) {
		this.vlrIcmsStPrev = vlrIcmsStPrev;
	}

	public BigDecimal getVlrInssTdc() {
		return this.vlrInssTdc;
	}

	public void setVlrInssTdc(BigDecimal vlrInssTdc) {
		this.vlrInssTdc = vlrInssTdc;
	}

	public BigDecimal getVlrIofFinan() {
		return this.vlrIofFinan;
	}

	public void setVlrIofFinan(BigDecimal vlrIofFinan) {
		this.vlrIofFinan = vlrIofFinan;
	}

	public BigDecimal getVlrIrrfTdc() {
		return this.vlrIrrfTdc;
	}

	public void setVlrIrrfTdc(BigDecimal vlrIrrfTdc) {
		this.vlrIrrfTdc = vlrIrrfTdc;
	}

	public BigDecimal getVlrIsentasIcms() {
		return this.vlrIsentasIcms;
	}

	public void setVlrIsentasIcms(BigDecimal vlrIsentasIcms) {
		this.vlrIsentasIcms = vlrIsentasIcms;
	}

	public BigDecimal getVlrIsentasIpi() {
		return this.vlrIsentasIpi;
	}

	public void setVlrIsentasIpi(BigDecimal vlrIsentasIpi) {
		this.vlrIsentasIpi = vlrIsentasIpi;
	}

	public BigDecimal getVlrNaoIncidente() {
		return this.vlrNaoIncidente;
	}

	public void setVlrNaoIncidente(BigDecimal vlrNaoIncidente) {
		this.vlrNaoIncidente = vlrNaoIncidente;
	}

	public BigDecimal getVlrOutrasIcms() {
		return this.vlrOutrasIcms;
	}

	public void setVlrOutrasIcms(BigDecimal vlrOutrasIcms) {
		this.vlrOutrasIcms = vlrOutrasIcms;
	}

	public BigDecimal getVlrOutrasIpi() {
		return this.vlrOutrasIpi;
	}

	public void setVlrOutrasIpi(BigDecimal vlrOutrasIpi) {
		this.vlrOutrasIpi = vlrOutrasIpi;
	}

	public BigDecimal getVlrPis() {
		return this.vlrPis;
	}

	public void setVlrPis(BigDecimal vlrPis) {
		this.vlrPis = vlrPis;
	}

	public BigDecimal getVlrPisCc() {
		return this.vlrPisCc;
	}

	public void setVlrPisCc(BigDecimal vlrPisCc) {
		this.vlrPisCc = vlrPisCc;
	}

	public BigDecimal getVlrPisRet() {
		return this.vlrPisRet;
	}

	public void setVlrPisRet(BigDecimal vlrPisRet) {
		this.vlrPisRet = vlrPisRet;
	}

	public BigDecimal getVlrPisRetTdc() {
		return this.vlrPisRetTdc;
	}

	public void setVlrPisRetTdc(BigDecimal vlrPisRetTdc) {
		this.vlrPisRetTdc = vlrPisRetTdc;
	}

	public BigDecimal getVlrPisSt() {
		return this.vlrPisSt;
	}

	public void setVlrPisSt(BigDecimal vlrPisSt) {
		this.vlrPisSt = vlrPisSt;
	}

	public BigDecimal getVlrRedBcIcms() {
		return this.vlrRedBcIcms;
	}

	public void setVlrRedBcIcms(BigDecimal vlrRedBcIcms) {
		this.vlrRedBcIcms = vlrRedBcIcms;
	}

	public BigDecimal getVlrRedBcIpi() {
		return this.vlrRedBcIpi;
	}

	public void setVlrRedBcIpi(BigDecimal vlrRedBcIpi) {
		this.vlrRedBcIpi = vlrRedBcIpi;
	}

	public BigDecimal getVlrRedBcSubs() {
		return this.vlrRedBcSubs;
	}

	public void setVlrRedBcSubs(BigDecimal vlrRedBcSubs) {
		this.vlrRedBcSubs = vlrRedBcSubs;
	}

	public BigDecimal getVlrSeguro() {
		return this.vlrSeguro;
	}

	public void setVlrSeguro(BigDecimal vlrSeguro) {
		this.vlrSeguro = vlrSeguro;
	}

	public BigDecimal getVlrTotal() {
		return this.vlrTotal;
	}

	public void setVlrTotal(BigDecimal vlrTotal) {
		this.vlrTotal = vlrTotal;
	}

	public BigDecimal getVlrTotalNf() {
		return this.vlrTotalNf;
	}

	public void setVlrTotalNf(BigDecimal vlrTotalNf) {
		this.vlrTotalNf = vlrTotalNf;
	}

	public BigDecimal getVlrUniBruto() {
		return this.vlrUniBruto;
	}

	public void setVlrUniBruto(BigDecimal vlrUniBruto) {
		this.vlrUniBruto = vlrUniBruto;
	}

	public BigDecimal getVlrUniNf() {
		return this.vlrUniNf;
	}

	public void setVlrUniNf(BigDecimal vlrUniNf) {
		this.vlrUniNf = vlrUniNf;
	}

	public BigDecimal getVlrUnitario() {
		return this.vlrUnitario;
	}

	public void setVlrUnitario(BigDecimal vlrUnitario) {
		this.vlrUnitario = vlrUnitario;
	}

}