package com.topinternacional.linx.model.nl.view;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TOPV_LINX_PESSOAS")
public class Pessoa {
	
	@Id
	@Column(name = "CODIGO")
    private String codigo;	
	
	@Column(name = "NOME_RAZAO_SOCIAL")
    private String nomeRazaoSocial;
	
	@Column(name = "DOC_CLIENTE")
    private String docCliente;
	
	@Column(name = "PF_PJ")
    private String pfPj;
	
	@Column(name = "ENDERECO")
    private String endereco;
	
	@Column(name = "CIDADE")
    private String cidade;
	
	@Column(name = "UF")
    private String uf;
	
	@Column(name = "ESTADO_CIVIL")
    private String estadoCivil;
	
	@Column(name = "TIPO")
    private String tipo;
	
	@Column(name = "NOME_FANTASIA")
    private String nomeFantasia;
	
	@Column(name = "IDENTIDADE_CLIENTE")
    private String identidadeCliente;
	
	@Column(name = "INSCRICAO_CLIENTE")
    private String inscricaoCliente;
	
	@Column(name = "COMPLEMENTO")
    private String complemento;
	
	@Column(name = "BAIRRO")
    private String bairro;
	
	@Column(name = "CEP")
    private String cep;
	
	@Column(name = "TELEFONE")
    private String telefone;
	
	@Column(name = "EMAIL")
    private String email;
	
	@Column(name = "DATA_NASCIMENTO")
    private String dataNascimento;
	
	@Column(name = "SEXO")
    private String sexo;
	
	@Column(name = "PAIS")
    private String pais;
	
	@Column(name = "ATUALIZAR_POR_CPF_CNPJ")
    private String atualizaPorCpfCnpj;
		
	public Pessoa() {}

	public Pessoa(String codigo, String nomeRazaoSocial, String docCliente, String pfPj, String endereco, String cidade,
			String uf, String estadoCivil, String tipo, String nomeFantasia, String identidadeCliente,
			String inscricaoCliente, String complemento, String bairro, String cep, String telefone, String email,
			String dataNascimento, String sexo, String pais, String atualizaPorCpfCnpj) {
		super();
		this.codigo = codigo;
		this.nomeRazaoSocial = nomeRazaoSocial;
		this.docCliente = docCliente;
		this.pfPj = pfPj;
		this.endereco = endereco;
		this.cidade = cidade;
		this.uf = uf;
		this.estadoCivil = estadoCivil;
		this.tipo = tipo;
		this.nomeFantasia = nomeFantasia;
		this.identidadeCliente = identidadeCliente;
		this.inscricaoCliente = inscricaoCliente;
		this.complemento = complemento;
		this.bairro = bairro;
		this.cep = cep;
		this.telefone = telefone;
		this.email = email;
		this.dataNascimento = dataNascimento;
		this.sexo = sexo;
		this.pais = pais;
		this.atualizaPorCpfCnpj = atualizaPorCpfCnpj;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNomeRazaoSocial() {
		return nomeRazaoSocial;
	}

	public void setNomeRazaoSocial(String nomeRazaoSocial) {
		this.nomeRazaoSocial = nomeRazaoSocial;
	}

	public String getDocCliente() {
		return docCliente;
	}

	public void setDocCliente(String docCliente) {
		this.docCliente = docCliente;
	}

	public String getPfPj() {
		return pfPj;
	}

	public void setPfPj(String pfPj) {
		this.pfPj = pfPj;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	public String getIdentidadeCliente() {
		return identidadeCliente;
	}

	public void setIdentidadeCliente(String identidadeCliente) {
		this.identidadeCliente = identidadeCliente;
	}

	public String getInscricaoCliente() {
		return inscricaoCliente;
	}

	public void setInscricaoCliente(String inscricaoCliente) {
		this.inscricaoCliente = inscricaoCliente;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getAtualizaPorCpfCnpj() {
		return atualizaPorCpfCnpj;
	}

	public void setAtualizaPorCpfCnpj(String atualizaPorCpfCnpj) {
		this.atualizaPorCpfCnpj = atualizaPorCpfCnpj;
	}
}
