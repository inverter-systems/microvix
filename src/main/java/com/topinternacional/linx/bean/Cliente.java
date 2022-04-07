package com.topinternacional.linx.bean;

import java.io.Serializable;
import java.util.List;

import com.topinternacional.linx.services.util.Util;

public class Cliente implements Serializable {
	
	private static final long serialVersionUID = 5522325760171038230L;
	
	private Integer portal;				
	private Integer codCliente;			
	private String razaoCliente;		
	private String nomeCliente;		
	private String docCliente;		
	private String tipoCliente;			
	private String enderecoCliente;		
	private String numeroRuaCliente;	
	private String complementEndCli;	
	private String bairroCliente;		
	private String cepCliente;			
	private String cidadeCliente;		
	private String ufCliente;			
	private String pais;					
	private String foneCliente;			
	private String emailCliente;		
	private String sexo;					
	private String dataCadastro;		
	private String dataNascimento;		
	private String celCliente;			
	private String ativo;				
	private String dtUpdate;			
	private String inscricaoEstadual;	
	private String incricaoMunicipal;
	private String identidadeCliente;	
	private String cartaoFidelidade;
	private Integer codIbgeMunicipio;	
	private String classeCliente;		
	private String matriculaConveniado;	
	private String tipoCadastro;		
	private Integer empresaCadastro;	
	private Integer idEstadoCivil;		
	private String faxCliente;			
	private String siteCliente;			
	private Long timestamp;				
	private Integer clienteAnonimo; // 0=FALSE 1=TRUE
	
	public Cliente() {}
	
	public Cliente(Integer portal, Integer codCliente, String razaoCliente, String nomeCliente, String docCliente,
			String tipoCliente, String enderecoCliente, String numeroRuaCliente, String complementEndCli,
			String bairroCliente, String cepCliente, String cidadeCliente, String ufCliente, String pais,
			String foneCliente, String emailCliente, String sexo, String dataCadastro, String dataNascimento,
			String celCliente, String ativo, String dtUpdate, String inscricaoEstadual, String incricaoMunicipal,
			String identidadeCliente, String cartaoFidelidade, Integer codIbgeMunicipio, String classeCliente,
			String matriculaConveniado, String tipoCadastro, Integer empresaCadastro, Integer idEstadoCivil,
			String faxCliente, String siteCliente, Long timestamp, Integer clienteAnonimo) {
		super();
		this.portal = portal;
		this.codCliente = codCliente;
		this.razaoCliente = razaoCliente;
		this.nomeCliente = nomeCliente;
		this.docCliente = docCliente;
		this.tipoCliente = tipoCliente;
		this.enderecoCliente = enderecoCliente;
		this.numeroRuaCliente = numeroRuaCliente;
		this.complementEndCli = complementEndCli;
		this.bairroCliente = bairroCliente;
		this.cepCliente = cepCliente;
		this.cidadeCliente = cidadeCliente;
		this.ufCliente = ufCliente;
		this.pais = pais;
		this.foneCliente = foneCliente;
		this.emailCliente = emailCliente;
		this.sexo = sexo;
		this.dataCadastro = dataCadastro;
		this.dataNascimento = dataNascimento;
		this.celCliente = celCliente;
		this.ativo = ativo;
		this.dtUpdate = dtUpdate;
		this.inscricaoEstadual = inscricaoEstadual;
		this.incricaoMunicipal = incricaoMunicipal;
		this.identidadeCliente = identidadeCliente;
		this.cartaoFidelidade = cartaoFidelidade;
		this.codIbgeMunicipio = codIbgeMunicipio;
		this.classeCliente = classeCliente;
		this.matriculaConveniado = matriculaConveniado;
		this.tipoCadastro = tipoCadastro;
		this.empresaCadastro = empresaCadastro;
		this.idEstadoCivil = idEstadoCivil;
		this.faxCliente = faxCliente;
		this.siteCliente = siteCliente;
		this.timestamp = timestamp;
		this.clienteAnonimo = clienteAnonimo;
	}
    
	public Integer getPortal() {
		return portal;
	}

	public void setPortal(Integer portal) {
		this.portal = portal;
	}

	public Integer getCodCliente() {
		return codCliente;
	}

	public void setCodCliente(Integer codCliente) {
		this.codCliente = codCliente;
	}

	public String getRazaoCliente() {
		return razaoCliente;
	}

	public void setRazaoCliente(String razaoCliente) {
		this.razaoCliente = razaoCliente;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getDocCliente() {
		return docCliente;
	}

	public void setDocCliente(String docCliente) {
		this.docCliente = docCliente;
	}

	public String getTipoCliente() {
		return tipoCliente;
	}

	public void setTipoCliente(String tipoCliente) {
		this.tipoCliente = tipoCliente;
	}

	public String getEnderecoCliente() {
		return enderecoCliente;
	}

	public void setEnderecoCliente(String enderecoCliente) {
		this.enderecoCliente = enderecoCliente;
	}

	public String getNumeroRuaCliente() {
		return numeroRuaCliente;
	}

	public void setNumeroRuaCliente(String numeroRuaCliente) {
		this.numeroRuaCliente = numeroRuaCliente;
	}

	public String getComplementEndCli() {
		return complementEndCli;
	}

	public void setComplementEndCli(String complementEndCli) {
		this.complementEndCli = complementEndCli;
	}

	public String getBairroCliente() {
		return bairroCliente;
	}

	public void setBairroCliente(String bairroCliente) {
		this.bairroCliente = bairroCliente;
	}

	public String getCepCliente() {
		return cepCliente;
	}

	public void setCepCliente(String cepCliente) {
		this.cepCliente = cepCliente;
	}

	public String getCidadeCliente() {
		return cidadeCliente;
	}

	public void setCidadeCliente(String cidadeCliente) {
		this.cidadeCliente = cidadeCliente;
	}

	public String getUfCliente() {
		return ufCliente;
	}

	public void setUfCliente(String ufCliente) {
		this.ufCliente = ufCliente;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getFoneCliente() {
		return foneCliente;
	}

	public void setFoneCliente(String foneCliente) {
		this.foneCliente = foneCliente;
	}

	public String getEmailCliente() {
		return emailCliente;
	}

	public void setEmailCliente(String emailCliente) {
		this.emailCliente = emailCliente;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(String dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getCelCliente() {
		return celCliente;
	}

	public void setCelCliente(String celCliente) {
		this.celCliente = celCliente;
	}

	public String getAtivo() {
		return ativo;
	}

	public void setAtivo(String ativo) {
		this.ativo = ativo;
	}

	public String getDtUpdate() {
		return dtUpdate;
	}

	public void setDtUpdate(String dtUpdate) {
		this.dtUpdate = dtUpdate;
	}

	public String getInscricaoEstadual() {
		return inscricaoEstadual;
	}

	public void setInscricaoEstadual(String inscricaoEstadual) {
		this.inscricaoEstadual = inscricaoEstadual;
	}

	public String getIncricaoMunicipal() {
		return incricaoMunicipal;
	}

	public void setIncricaoMunicipal(String incricaoMunicipal) {
		this.incricaoMunicipal = incricaoMunicipal;
	}

	public String getIdentidadeCliente() {
		return identidadeCliente;
	}

	public void setIdentidadeCliente(String identidadeCliente) {
		this.identidadeCliente = identidadeCliente;
	}

	public String getCartaoFidelidade() {
		return cartaoFidelidade;
	}

	public void setCartaoFidelidade(String cartaoFidelidade) {
		this.cartaoFidelidade = cartaoFidelidade;
	}

	public Integer getCodIbgeMunicipio() {
		return codIbgeMunicipio;
	}

	public void setCodIbgeMunicipio(Integer codIbgeMunicipio) {
		this.codIbgeMunicipio = codIbgeMunicipio;
	}

	public String getClasseCliente() {
		return classeCliente;
	}

	public void setClasseCliente(String classeCliente) {
		this.classeCliente = classeCliente;
	}

	public String getMatriculaConveniado() {
		return matriculaConveniado;
	}

	public void setMatriculaConveniado(String matriculaConveniado) {
		this.matriculaConveniado = matriculaConveniado;
	}

	public String getTipoCadastro() {
		return tipoCadastro;
	}

	public void setTipoCadastro(String tipoCadastro) {
		this.tipoCadastro = tipoCadastro;
	}

	public Integer getEmpresaCadastro() {
		return empresaCadastro;
	}

	public void setEmpresaCadastro(Integer empresaCadastro) {
		this.empresaCadastro = empresaCadastro;
	}

	public Integer getIdEstadoCivil() {
		return idEstadoCivil;
	}

	public void setIdEstadoCivil(Integer idEstadoCivil) {
		this.idEstadoCivil = idEstadoCivil;
	}

	public String getFaxCliente() {
		return faxCliente;
	}

	public void setFaxCliente(String faxCliente) {
		this.faxCliente = faxCliente;
	}

	public String getSiteCliente() {
		return siteCliente;
	}

	public void setSiteCliente(String siteCliente) {
		this.siteCliente = siteCliente;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public Integer getClienteAnonimo() {
		return clienteAnonimo;
	}

	public void setClienteAnonimo(Integer clienteAnonimo) {
		this.clienteAnonimo = clienteAnonimo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	// Setters personalizados
	public void setCodIbgeMunicipio(String codIbgeMunicipio) {
		this.codIbgeMunicipio = codIbgeMunicipio == null || codIbgeMunicipio.trim().length() == 0 ? null : Integer.valueOf(codIbgeMunicipio);
	}
	public void setEmpresaCadastro(String empresaCadastro) {
		this.empresaCadastro = empresaCadastro == null || empresaCadastro.trim().length() == 0 ? null : Integer.valueOf(empresaCadastro);
	}
	public void setIdEstadoCivil(String idEstadoCivil) {
		this.idEstadoCivil = idEstadoCivil == null || idEstadoCivil.trim().length() == 0 ? null : Integer.valueOf(idEstadoCivil);
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp == null || timestamp.trim().length() == 0 ? null : Long.valueOf(timestamp);
	}
	public void setClienteAnonimo(String clienteAnonimo) {		
		this.clienteAnonimo = clienteAnonimo.equalsIgnoreCase("TRUE") ? 1 : 0;
	}
	public void setCodCliente(String codCliente) {
		this.codCliente = Integer.valueOf(codCliente);
	}
	public void setPortal(String portal) {
		this.portal = Integer.valueOf(portal);
		
	}

	public static Cliente getCliente(Response consultaMovimento) {
		
		Cliente cli = new Cliente();
		
		List<String> clientes = Util.getRegistrosXML(consultaMovimento);
		
		String[] col =  Util.getColunasXML(clientes.get(0));
			
		cli.setPortal(col[1]);
    	cli.setCodCliente(col[2]);
    	cli.setRazaoCliente(col[3]);
    	cli.setNomeCliente(col[4]);
    	cli.setDocCliente(col[5]);
    	
    	cli.setTipoCliente(col[6]);
    	cli.setEnderecoCliente(col[7]);
    	cli.setNumeroRuaCliente(col[8]);
    	cli.setComplementEndCli(col[9]);
    	cli.setBairroCliente(col[10]);
    	
    	cli.setCepCliente(col[11]);
    	cli.setCidadeCliente(col[12]);
    	cli.setUfCliente(col[13]);
    	cli.setPais(col[14]);
    	cli.setFoneCliente(col[15]);
    	
    	cli.setEmailCliente(col[16]);
    	cli.setSexo(col[17]);
    	cli.setDataCadastro(col[18]);
    	cli.setDataNascimento(col[19]);
    	cli.setCelCliente(col[20]);
    	
    	cli.setAtivo(col[21]);
    	cli.setDtUpdate(col[22]);
    	cli.setInscricaoEstadual(col[23]);
    	cli.setIncricaoMunicipal(col[24]);
    	cli.setIdentidadeCliente(col[25]);
    	
    	cli.setCartaoFidelidade(col[26]);
    	cli.setCodIbgeMunicipio(col[27]);
    	cli.setClasseCliente(col[28]);
    	cli.setMatriculaConveniado(col[29]);
    	cli.setTipoCadastro(col[30]);
    	
    	cli.setEmpresaCadastro(col[31]);
    	cli.setIdEstadoCivil(col[32]);
    	cli.setFaxCliente(col[33]);
    	cli.setSiteCliente(col[34]); 
    	cli.setTimestamp(col[35]);
    	
    	cli.setClienteAnonimo(col[36]);
    	
		return cli;
	}
}
