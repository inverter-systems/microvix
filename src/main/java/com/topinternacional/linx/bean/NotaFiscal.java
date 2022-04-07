package com.topinternacional.linx.bean;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.JAXBException;

import com.topinternacional.linx.services.util.Util;

import br.com.swconsultoria.nfe.schema_4.enviNFe.TNFe;
import br.com.swconsultoria.nfe.util.XmlNfeUtil;


public class NotaFiscal implements Serializable {

	private static final long serialVersionUID = -735496234490662165L;
	
	private Integer numero;	
	private String serie;
	private Date dataEmissao;
	private Date dataAlteracao;
	private Date dataInclusao;
	private String cnpjCpf;
	private Integer codPci;
	private Integer codMicrovix;	
	private Integer codSefaz;
	private String chaveNf;
	private Integer codClienteMicrovix;
	private String identificador;
	private Integer modeloNf;
	private List<NotaFiscalItem> itens = new ArrayList<NotaFiscalItem>();
	private Cliente cliente;
	private String operacao;
	private List<FormaPagamento> formaPagamento = new ArrayList<FormaPagamento>();
	private Vendedor vendedor;
	private String naturezaOperacao;
	private String observacoes;
	
	private TNFe nfe = new TNFe();
	
	// *** Customizados
	public String getDataEmissaoFmt() {
		return dataEmissao == null ? null : new SimpleDateFormat("dd/MM/yyyy").format(dataEmissao);
	}
	public String getDataAlteracaoFmt() {
		return new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(dataAlteracao);
	}
	public String getDataInclusaoFmt() {
		return dataInclusao == null ? null : new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(dataInclusao);
	}
	public void setDataInclusao(Date dataInclusao) {
		this.dataInclusao = dataInclusao;
	}
	public void setDataEmissao(String dataEmissao) {
		try {
			this.dataEmissao = dataEmissao == null ? null : new SimpleDateFormat("yyyy-mm-dd HH:mm:ss").parse(dataEmissao);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	public void setCodPci(String codPci) {
		this.codPci = codPci == null ? null : Integer.valueOf(codPci);
	}
	public void setCodMicrovix(String codMicrovix) {
		this.codMicrovix = codMicrovix == null ? null : Integer.valueOf(codMicrovix);
	}
	public void setCodSefaz(String codSefaz) {
		this.codSefaz = codSefaz == null ? null : Integer.valueOf(codSefaz);
	}
	public static List<NotaFiscal> getNotasMicrovix(Response xmlnotas) {
		List<NotaFiscal> notasFiscais = new ArrayList<NotaFiscal>();
		
		List<String> notas = Util.getRegistrosXML(xmlnotas);
		
		if (notas == null) return null; 
		
		for (String nota : notas) {
			String[] col = Util.getColunasXML(nota);
			
			if (!col[17].equals("100")) continue;
						
			//NotaFiscal notaFiscal = new NotaFiscal(col);
			NotaFiscal notaFiscal = getNovaNota(col);
			
			notasFiscais.add(notaFiscal);
		} 	
		return notasFiscais;
	}
	private static NotaFiscal getNovaNota(String[] col) {
		NotaFiscal nf = new NotaFiscal();
		
		nf.numero = Integer.valueOf(col[3]);		
		nf.serie = col[4];
		nf.dataEmissao = Util.getDataXMLMicrovix(col[5]);
		nf.dataInclusao = Util.getDataTimeXML(col[11]);
		nf.cnpjCpf = col[2];
		nf.codSefaz = Integer.valueOf(col[17]);		
		nf.chaveNf = col[6];
		nf.identificador = col[10];	
		nf.observacoes = col[10];
		
		return nf;
	}
	private static NotaFiscal getNovaNotaComXml(String[] col) {
		NotaFiscal nf = getNovaNota(col);
		
		try {
			nf.setNfe(XmlNfeUtil.xmlToObject(Util.getXmlDaTag(col[8], "NFe"), TNFe.class));
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		
		return nf;
	}
	public static NotaFiscal getNotaMicrovix(Response xmlnotas) {
		List<String> notas = Util.getRegistrosXML(xmlnotas);
		
		String[] col = Util.getColunasXML(notas.get(0));
					
		return getNovaNotaComXml(col);
		
	}
	public String getOperacaoFmt() {	
		switch (this.operacao.trim()) {
		  case "E":
			  return "Entrada";
		  case "S":
			  return "Saída";
		case "DE":
		    return "Devolução";
		  case "DS":
			  return "Devolução de Saída";
		  case "N":
		      return "Neutro";		  
		}
		return null;
	}	
	
	// *** Gerados	
	public Integer getModeloNf() {
		return modeloNf;
	}
	public void setModeloNf(Integer modeloNf) {
		this.modeloNf = modeloNf;
	}
	public Integer getNumero() {
		return numero;
	}
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	public String getSerie() {
		return serie;
	}
	public void setSerie(String serie) {
		this.serie = serie;
	}
	public Date getDataEmissao() {
		return dataEmissao;
	}
	public void setDataEmissao(Date dataEmissao) {
		this.dataEmissao = dataEmissao;
	}
	public Date getDataAlteracao() {
		return dataAlteracao;
	}
	public void setDataAlteracao(Date dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}
	public Date getDataInclusao() {
		return dataInclusao;
	}
	public String getCnpjCpf() {
		return cnpjCpf;
	}
	public void setCnpjCpf(String cnpjCpf) {
		this.cnpjCpf = cnpjCpf;
	}
	public Integer getCodPci() {
		return codPci;
	}
	public void setCodPci(Integer cod_pci) {
		this.codPci = cod_pci;
	}
	public Integer getCodMicrovix() {
		return codMicrovix;
	}
	public void setCodMicrovix(Integer cod_microvix) {
		this.codMicrovix = cod_microvix;
	}
	public Integer getCodSefaz() {
		return codSefaz;
	}
	public void setCodSefaz(Integer codSefaz) {
		this.codSefaz = codSefaz;
	}
	public String getChaveNf() {
		return chaveNf;
	}
	public void setChaveNf(String chaveNf) {
		this.chaveNf = chaveNf;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Integer getCodClienteMicrovix() {
		return codClienteMicrovix;
	}
	public void setCodClienteMicrovix(Integer codClienteMicrovix) {
		this.codClienteMicrovix = codClienteMicrovix;
	}
	public void setNumero(String num) {
		this.numero = num == null ? null : Integer.valueOf(num);		
	}
	public String getIdentificador() {
		return identificador;
	}
	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public void setModeloNf(String modeloNF) {
		this.modeloNf = modeloNF == null ? null : Integer.valueOf(modeloNF);
	}
	public String getOperacao() {
		return operacao;
	}		
	public void setOperacao(String operacao) {
		this.operacao = operacao;
	}
	public List<FormaPagamento> getFormaPagamento() {
		return formaPagamento;
	}
	public void setFormaPagamento(List<FormaPagamento> formaPagamento) {
		this.formaPagamento = formaPagamento;
	}
	public Vendedor getVendedor() {
		return vendedor;
	}
	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}
	public String getNaturezaOperacao() {
		return naturezaOperacao;
	}
	public void setNaturezaOperacao(String naturezaOperacao) {
		this.naturezaOperacao = naturezaOperacao;
	}
	public List<NotaFiscalItem> getItens() {
		return itens;
	}
	public void setItens(List<NotaFiscalItem> itens) {
		this.itens = itens;
	}
	public String getObservacoes() {
		return observacoes;
	}
	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}
	public TNFe getNfe() {
		return nfe;
	}
	public void setNfe(TNFe nfe) {
		this.nfe = nfe;
	}
}
