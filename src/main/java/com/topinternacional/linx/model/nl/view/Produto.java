package com.topinternacional.linx.model.nl.view;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.topinternacional.linx.services.util.Util;

@Entity
@Table(name = "TOPV_LINX_PRODUTOS")
public class Produto {

	@Id
	@Column(name = "CODIGO")
	private String codigo;

	@Column(name = "NOME_PRODUTO")
	private String nomeProduto;

	@Column(name = "COD_FORNECEDOR")
	private String codFornecedor;

	@Column(name = "REFERENCIA")
	private String referencia;

	@Column(name = "COD_AUXILIAR")
	private String codAuxiliar;

	@Column(name = "COD_SETOR")
	private String codSetor;
	
	@Column(name = "SETOR")
	private String setor;

	@Column(name = "COD_LINHA")
	private String codLinha;
	
	@Column(name = "LINHA")
	private String linha;

	@Column(name = "COD_MARCA")
	private String codMarca;
	
	@Column(name = "MARCA")
	private String marca;
	
	@Column(name = "COD_COLECAO")
	private String codColecao;

	@Column(name = "COD_GRADE1")
	private String codGrade1;

	@Column(name = "COD_GRADE2")
	private String codGrade2;

	@Column(name = "UNIDADE")
	private String unidade;

	@Column(name = "PRECO_CUSTO")
	private String precoCusto;

	@Column(name = "PRECO_VENDA")
	private String precoVenda;

	@Column(name = "COD_CLASSIFICACAO")
	private String codClassificacao;

	@Column(name = "COD_ESPESSURA")
	private String codEspessura;

	@Column(name = "ORIGEM_MERCADORIA")
	private String origemMercadoria;

	@Column(name = "CEST")
	private String cest;

	@Column(name = "NCM")
	private String ncm;
	
	@Column(name = "ID_CONFIG_TRIBUTARIA")
	private String idConfigTributaria;

	@Column(name = "DESATIVADO")
	private String desativado;

	@Column(name = "ENTREGA_FUTURA")
	private String entregaFutura;

	@Column(name = "PESO_BRUTO")
	private String pesoBruto;

	@Column(name = "PESO_LIQUIDO")
	private String pesoLiquido;

	@Column(name = "VOLUME")
	private String volume;

	@Column(name = "CUSTO_MEDIO")
	private String custoMedio;

	@Column(name = "DESCRICAO_BASICA")
	private String descricaoBasica;

	@Column(name = "EXIGE_CONTROLE_SERIAL")
	private String exigeControleSerial;

	@Column(name = "SERIAL_TIPO")
	private String serialTipo;

	@Column(name = "SERIAL_TAMANHO")
	private String serialTamanho;

	@Column(name = "TRIBUTA_FCP")
	private String tributaFcp;

	@Column(name = "ALTURA_PARA_FRETE")
	private String alturaParaFrete;

	@Column(name = "COD_COMPRADOR")
	private String codComprador;

	@Column(name = "COD_TIPO_ITEM")
	private String codTipoItem;

	@Column(name = "COMPRIMENTO_PARA_FRETE")
	private String comprimentoParaFrete;

	@Column(name = "IPI")
	private String ipi;

	@Column(name = "LARGURA_PARA_FRETE")
	private String largurtaParaFrete;

	@Column(name = "UNIDADE_COMPRA")
	private String unidadeCompra;
	
	public Produto() {}

	public Produto(String codigo, String nomeProduto, String codFornecedor, String referencia, String codAuxiliar,
			String codSetor, String setor, String codLinha, String linha, String codMarca, String marca,
			String codColecao, String codGrade1, String codGrade2, String unidade, String precoCusto, String precoVenda,
			String codClassificacao, String codEspessura, String origemMercadoria, String cest, String ncm,
			String idConfigTributaria, String desativado, String entregaFutura, String pesoBruto, String pesoLiquido,
			String volume, String custoMedio, String descricaoBasica, String exigeControleSerial, String serialTipo,
			String serialTamanho, String tributaFcp, String alturaParaFrete, String codComprador, String codTipoItem,
			String comprimentoParaFrete, String ipi, String largurtaParaFrete, String unidadeCompra) {
		super();
		this.codigo = codigo;
		this.nomeProduto = nomeProduto;
		this.codFornecedor = codFornecedor;
		this.referencia = referencia;
		this.codAuxiliar = codAuxiliar;
		this.codSetor = codSetor;
		this.setor = setor;
		this.codLinha = codLinha;
		this.linha = linha;
		this.codMarca = codMarca;
		this.marca = marca;
		this.codColecao = codColecao;
		this.codGrade1 = codGrade1;
		this.codGrade2 = codGrade2;
		this.unidade = unidade;
		this.precoCusto = precoCusto;
		this.precoVenda = precoVenda;
		this.codClassificacao = codClassificacao;
		this.codEspessura = codEspessura;
		this.origemMercadoria = origemMercadoria;
		this.cest = cest;
		this.ncm = ncm;
		this.idConfigTributaria = idConfigTributaria;
		this.desativado = desativado;
		this.entregaFutura = entregaFutura;
		this.pesoBruto = pesoBruto;
		this.pesoLiquido = pesoLiquido;
		this.volume = volume;
		this.custoMedio = custoMedio;
		this.descricaoBasica = descricaoBasica;
		this.exigeControleSerial = exigeControleSerial;
		this.serialTipo = serialTipo;
		this.serialTamanho = serialTamanho;
		this.tributaFcp = tributaFcp;
		this.alturaParaFrete = alturaParaFrete;
		this.codComprador = codComprador;
		this.codTipoItem = codTipoItem;
		this.comprimentoParaFrete = comprimentoParaFrete;
		this.ipi = ipi;
		this.largurtaParaFrete = largurtaParaFrete;
		this.unidadeCompra = unidadeCompra;
	}




	public String getCodLinha() {
		return codLinha;
	}


	public void setCodLinha(String codLinha) {
		this.codLinha = codLinha;
	}


	public String getLinha() {
		return linha;
	}


	public void setLinha(String linha) {
		this.linha = linha;
	}


	public void setSetor(String setor) {
		this.setor = setor;
	}


	public String getMarca() {
		return marca;
	}	
	
	public String getMarcaFmt() {
		return codMarca+" - "+marca;
	}	
	
	public void setMarca(String marca) {
		this.marca = marca;
	}
	
	public String getSetor() {
		return Util.getSetor(codSetor);
	}	
	
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	public String getCodFornecedor() {
		return codFornecedor;
	}

	public void setCodFornecedor(String codFornecedor) {
		this.codFornecedor = codFornecedor;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public String getCodAuxiliar() {
		return codAuxiliar;
	}

	public void setCodAuxiliar(String codAuxiliar) {
		this.codAuxiliar = codAuxiliar;
	}

	public String getCodSetor() {
		return codSetor;
	}

	public void setCodSetor(String codSetor) {
		this.codSetor = codSetor;
	}
	
	public String getCodMarca() {
		return codMarca;
	}

	public void setCodMarca(String codMarca) {
		this.codMarca = codMarca;
	}

	public String getCodColecao() {
		return codColecao;
	}

	public void setCodColecao(String codColecao) {
		this.codColecao = codColecao;
	}

	public String getCodGrade1() {
		return codGrade1;
	}

	public void setCodGrade1(String codGrade1) {
		this.codGrade1 = codGrade1;
	}

	public String getCodGrade2() {
		return codGrade2;
	}

	public void setCodGrade2(String codGrade2) {
		this.codGrade2 = codGrade2;
	}

	public String getUnidade() {
		return unidade;
	}

	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}

	public String getPrecoCustoFmt() {
		return Util.getValorBR(precoCusto);
	}
	
	public String getPrecoCusto() {
		return precoCusto;
	}

	public void setPrecoCusto(String precoCusto) {
		this.precoCusto = precoCusto;
	}

	public String getPrecoVenda() {
		return precoVenda;
	}
	
	public String getPrecoVendaFmt() {
		return Util.getValorBR(precoVenda);
	}


	public void setPrecoVenda(String precoVenda) {
		this.precoVenda = precoVenda;
	}

	public String getCodClassificacao() {
		return codClassificacao;
	}

	public void setCodClassificacao(String codClassificacao) {
		this.codClassificacao = codClassificacao;
	}

	public String getCodEspessura() {
		return codEspessura;
	}

	public void setCodEspessura(String codEspessura) {
		this.codEspessura = codEspessura;
	}

	public String getOrigemMercadoria() {
		return origemMercadoria;
	}

	public void setOrigemMercadoria(String origemMercadoria) {
		this.origemMercadoria = origemMercadoria;
	}

	public String getCest() {
		return cest;
	}

	public void setCest(String cest) {
		this.cest = cest;
	}

	public String getNcm() {
		return ncm;
	}

	public void setNcm(String ncm) {
		this.ncm = ncm;
	}

	public String getDesativado() {
		return desativado;
	}

	public void setDesativado(String desativado) {
		this.desativado = desativado;
	}

	public String getEntregaFutura() {
		return entregaFutura;
	}

	public void setEntregaFutura(String entregaFutura) {
		this.entregaFutura = entregaFutura;
	}

	public String getPesoBruto() {
		return pesoBruto;
	}

	public void setPesoBruto(String pesoBruto) {
		this.pesoBruto = pesoBruto;
	}

	public String getPesoLiquido() {
		return pesoLiquido;
	}

	public void setPesoLiquido(String pesoLiquido) {
		this.pesoLiquido = pesoLiquido;
	}

	public String getVolume() {
		return volume;
	}

	public void setVolume(String volume) {
		this.volume = volume;
	}

	public String getCustoMedio() {
		return custoMedio;
	}

	public void setCustoMedio(String custoMedio) {
		this.custoMedio = custoMedio;
	}

	public String getDescricaoBasica() {
		return descricaoBasica;
	}

	public void setDescricaoBasica(String descricaoBasica) {
		this.descricaoBasica = descricaoBasica;
	}

	public String getExigeControleSerial() {
		return exigeControleSerial;
	}

	public void setExigeControleSerial(String exigeControleSerial) {
		this.exigeControleSerial = exigeControleSerial;
	}

	public String getSerialTipo() {
		return serialTipo;
	}

	public void setSerialTipo(String serialTipo) {
		this.serialTipo = serialTipo;
	}

	public String getSerialTamanho() {
		return serialTamanho;
	}

	public void setSerialTamanho(String serialTamanho) {
		this.serialTamanho = serialTamanho;
	}

	public String getTributaFcp() {
		return tributaFcp;
	}

	public void setTributaFcp(String tributaFcp) {
		this.tributaFcp = tributaFcp;
	}

	public String getAlturaParaFrete() {
		return alturaParaFrete;
	}

	public void setAlturaParaFrete(String alturaParaFrete) {
		this.alturaParaFrete = alturaParaFrete;
	}

	public String getCodComprador() {
		return codComprador;
	}

	public void setCodComprador(String codComprador) {
		this.codComprador = codComprador;
	}

	public String getCodTipoItem() {
		return codTipoItem;
	}

	public void setCodTipoItem(String codTipoItem) {
		this.codTipoItem = codTipoItem;
	}

	public String getComprimentoParaFrete() {
		return comprimentoParaFrete;
	}

	public void setComprimentoParaFrete(String comprimentoParaFrete) {
		this.comprimentoParaFrete = comprimentoParaFrete;
	}

	public String getIpi() {
		return ipi;
	}

	public void setIpi(String ipi) {
		this.ipi = ipi;
	}

	public String getLargurtaParaFrete() {
		return largurtaParaFrete;
	}

	public void setLargurtaParaFrete(String largurtaParaFrete) {
		this.largurtaParaFrete = largurtaParaFrete;
	}

	public String getUnidadeCompra() {
		return unidadeCompra;
	}

	public void setUnidadeCompra(String unidadeCompra) {
		this.unidadeCompra = unidadeCompra;
	}

	public String getIdConfigTributaria() {
		return idConfigTributaria;
	}

	public void setIdConfigTributaria(String idConfigTributaria) {
		this.idConfigTributaria = idConfigTributaria;
	}
	
}
