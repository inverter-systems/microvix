package com.topinternacional.linx.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.topinternacional.linx.services.util.Util;

public class NotaFiscalItem implements Serializable {

	private static final long serialVersionUID = -5476992263404340712L;
	private String cnpjEmp;
	private Integer documento;
	private String serie;
	private Integer cfop;
	private String cst;
	private String modeloNF;
	private Date dataDocumento;
	private Date dataLancamento;
	private Integer codCliente;
	private Integer codVendedor;
	private Integer codProduto;
	private String descricao;
	private Integer codNL;
	private String barras;
	private Integer quantidade;
	private BigDecimal precoCusto;
	private BigDecimal precoUnitario;
	private BigDecimal valorLiquido;
	private BigDecimal desconto;
	private BigDecimal acrescimo;
	private String operacao;
	private String naturezaOperacao;
	private String observacoes;
	private Integer numItem;
	
	// Formas de Pagamento
	private Integer formaDinheiro; // 0 ou 1
	private Integer formaCartao;
	private Integer formaCheque;
	private BigDecimal totalDinheiro;
	private BigDecimal totalCartao;
	private BigDecimal totalCheque;
	
	// Imposto
	private BigDecimal icmsPercent;
	private BigDecimal ipiPercent;
	private BigDecimal valorIpi; 
	private BigDecimal valorSt;	
	
	public BigDecimal getIcmsPercent() {
		return icmsPercent;
	}
	
	public String getIcmsPercentFmt() {
		return String.format("%.2f", icmsPercent);
	}

	public void setIcmsPercent(BigDecimal icmsPercent) {
		this.icmsPercent = icmsPercent;
	}

	public BigDecimal getIpiPercent() {
		return ipiPercent;
	}
	
	public String getIpiPercentFmt() {
		return String.format("%.2f", ipiPercent);
	}

	public void setIpiPercent(BigDecimal ipiPercent) {
		this.ipiPercent = ipiPercent;
	}

	public BigDecimal getValorIpi() {
		return valorIpi;
	}
	
	public String getValorIpiFmt() {
		return String.format("%.2f", valorIpi);
	}

	public void setValorIpi(BigDecimal valorIpi) {
		this.valorIpi = valorIpi;
	}

	public BigDecimal getValorSt() {
		return valorSt;
	}
	
	public String getValorStFmt() {
		return String.format("%.2f", valorSt);
	}

	public void setValorSt(BigDecimal valorSt) {
		this.valorSt = valorSt;
	}

	public Integer getFormaDinheiro() {
		return formaDinheiro;
	}

	public void setFormaDinheiro(Integer formaDinheiro) {
		this.formaDinheiro = formaDinheiro;
	}

	public Integer getFormaCartao() {
		return formaCartao;
	}

	public void setFormaCartao(Integer formaCartao) {
		this.formaCartao = formaCartao;
	}

	public Integer getFormaCheque() {
		return formaCheque;
	}

	public void setFormaCheque(Integer formaCheque) {
		this.formaCheque = formaCheque;
	}

	public BigDecimal getTotalDinheiro() {
		return totalDinheiro;
	}

	public void setTotalDinheiro(BigDecimal totalDinheiro) {
		this.totalDinheiro = totalDinheiro;
	}

	public BigDecimal getTotalCartao() {
		return totalCartao;
	}

	public void setTotalCartao(BigDecimal totalCartão) {
		this.totalCartao = totalCartão;
	}

	public BigDecimal getTotalCheque() {
		return totalCheque;
	}

	public void setTotalCheque(BigDecimal totalCheque) {
		this.totalCheque = totalCheque;
	}

	public String getOperacao() {
		return operacao;
	}

	public void setOperacao(String operacao) {
		this.operacao = operacao;
	}

	public String getBarras() {
		return barras;
	}

	public void setBarras(String barras) {
		this.barras = barras;
	}

	public NotaFiscalItem() {}

	public String getCnpjEmp() {
		return cnpjEmp;
	}

	public void setCnpjEmp(String cnpjEmp) {
		this.cnpjEmp = cnpjEmp;
	}

	public Integer getDocumento() {
		return documento;
	}

	public void setDocumento(Integer documento) {
		this.documento = documento;
	}

	public String getSerie() {
		return serie;
	}

	public void setSerie(String serie) {
		this.serie = serie;
	}

	public Integer getCfop() {
		return cfop;
	}

	public void setCfop(Integer cfop) {
		this.cfop = cfop;
	}

	public String getModeloNF() {
		return modeloNF;
	}

	public void setModeloNF(String modeloNF) {
		this.modeloNF = modeloNF;
	}

	public Date getDataDocumento() {
		return dataDocumento;
	}

	public void setDataDocumento(Date dataDocumento) {
		this.dataDocumento = dataDocumento;
	}

	public Date getDataLancamento() {
		return dataLancamento;
	}

	public void setDataLancamento(Date dataLancamento) {
		this.dataLancamento = dataLancamento;
	}

	public Integer getCodCliente() {
		return codCliente;
	}

	public void setCodCliente(Integer codCliente) {
		this.codCliente = codCliente;
	}

	public Integer getCodVendedor() {
		return codVendedor;
	}

	public void setCodVendedor(Integer codVendedor) {
		this.codVendedor = codVendedor;
	}

	public Integer getCodProduto() {
		return codProduto;
	}

	public void setCodProduto(Integer codProduto) {
		this.codProduto = codProduto;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getPrecoCusto() {
		return precoCusto;
	}

	public void setPrecoCusto(BigDecimal precoCusto) {
		this.precoCusto = precoCusto;
	}

	public BigDecimal getPrecoUnitario() {
		return precoUnitario;
	}
	
	public String getPrecoUnitarioFmt() {
		return String.format("%.2f", precoUnitario) ;
	}
	
	public String getValorTotalFmt() {
		return String.format("%.2f", precoUnitario.multiply(new BigDecimal(quantidade)));
	}

	public void setPrecoUnitario(BigDecimal precoUnitario) {
		this.precoUnitario = precoUnitario;
	}

	public BigDecimal getValorLiquido() {
		return valorLiquido;
	}

	public void setValorLiquido(BigDecimal valorLiquido) {
		this.valorLiquido = valorLiquido;
	}

	public BigDecimal getDesconto() {
		return desconto;
	}

	public void setDesconto(BigDecimal bigDecimal) {
		this.desconto = bigDecimal;
	}

	public BigDecimal getAcrescimo() {
		return acrescimo;
	}

	public void setAcrescimo(BigDecimal acrescimo) {
		this.acrescimo = acrescimo;
	}
	
	public static List<NotaFiscalItem> getItensNotaMicrovix(Response xmlitens) {
		List<NotaFiscalItem> notasFiscaisItens = new ArrayList<NotaFiscalItem>();
		List<String> itens = Util.getRegistrosXML(xmlitens);	
		
		for (String item : itens) {
			String[] col = Util.getColunasXML(item);					
						
			NotaFiscalItem notaFiscalItem = new NotaFiscalItem();
			
			notaFiscalItem.setCnpjEmp(col[2]);
			notaFiscalItem.setCfop(Integer.valueOf(col[15]));
			notaFiscalItem.setCodCliente(Integer.valueOf(col[12]));
			notaFiscalItem.setCodProduto(Integer.valueOf(col[54]));
			notaFiscalItem.setNumItem(Integer.valueOf(col[83]));
			notaFiscalItem.setBarras(col[55]);
			notaFiscalItem.setQuantidade(Integer.valueOf(col[17]));
			notaFiscalItem.setCodVendedor(Integer.valueOf(col[16]));
			notaFiscalItem.setDataDocumento(Util.getDataTimeXML(col[10]));
			notaFiscalItem.setDataLancamento(Util.getDataTimeXML(col[11]));
			notaFiscalItem.setDesconto(new BigDecimal(col[20]));
			notaFiscalItem.setModeloNF(col[9]);
			notaFiscalItem.setDocumento(Integer.valueOf(col[5]));
			notaFiscalItem.setAcrescimo(new BigDecimal(col[79]));
			notaFiscalItem.setPrecoCusto(new BigDecimal(col[18]));
			notaFiscalItem.setPrecoUnitario(new BigDecimal(col[62]));
			notaFiscalItem.setValorLiquido(new BigDecimal(col[19]));
			notaFiscalItem.setOperacao(col[52]);
			notaFiscalItem.setNaturezaOperacao(col[64]);
			notaFiscalItem.setSerie(col[13]);
			notaFiscalItem.setCst(col[21]);
			notaFiscalItem.setObservacoes(col[61]);
			
			notaFiscalItem.setIcmsPercent(new BigDecimal(col[26]));
			notaFiscalItem.setIpiPercent(new BigDecimal(col[38]));
			notaFiscalItem.setValorIpi(new BigDecimal(col[37]));
			notaFiscalItem.setValorSt(new BigDecimal(col[34]));
						
			notaFiscalItem.setFormaDinheiro(Integer.valueOf(col[41]));
			notaFiscalItem.setTotalDinheiro(new BigDecimal(col[42]));
			notaFiscalItem.setFormaCheque(Integer.valueOf(col[43]));
			notaFiscalItem.setTotalCheque(new BigDecimal(col[44]));
			notaFiscalItem.setFormaCartao(Integer.valueOf(col[45]));
			notaFiscalItem.setTotalCartao(new BigDecimal(col[46]));
			
			notasFiscaisItens.add(notaFiscalItem);
		} 	
		return notasFiscaisItens;
	}
	
	public String getFormaDePagamento() {
		String fp = formaDinheiro == 1 ? "Dinheiro "+String.format("%.2f", totalDinheiro) : "";
		
		fp = fp + (fp.length() > 1 && formaCartao == 1? ", " : "");
		
		fp = fp + (formaCartao == 1 ? "Cartão "+String.format("%.2f", totalCartao) : "");
		
		fp = fp + (fp.length() > 10 && formaCheque == 1 ? ", " : "");
		
		fp = fp + (formaCheque == 1 ? "Cheque "+String.format("%.2f", totalCheque) : "");
		
		return fp;
	}

	public String getNaturezaOperacao() {
		return naturezaOperacao;
	}

	public void setNaturezaOperacao(String naturezaOperacao) {
		this.naturezaOperacao = naturezaOperacao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getCodNL() {
		return codNL;
	}

	public void setCodNL(Integer codNL) {
		this.codNL = codNL;
	}

	public String getCst() {
		return cst;
	}

	public void setCst(String cst) {
		this.cst = cst;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public Integer getNumItem() {
		return numItem;
	}

	public void setNumItem(Integer numItem) {
		this.numItem = numItem;
	}

}
