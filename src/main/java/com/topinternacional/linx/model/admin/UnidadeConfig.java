package com.topinternacional.linx.model.admin;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TOPT_LINX_REL_UNID")
public class UnidadeConfig {

	@Id
	@Column(name = "COD_UNIDADE")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer codUnidade;
	
	@Column(name = "COD_PCI")
	private Integer codPci;
	
	@Column(name = "COD_MICROVIX")
	private Integer codMicrovix;
	
	@Column(name = "CNPJ")
	private String cnpj;
	
	@Column(name = "DES_MODELO")
	private String desModelo;
	
	@Column(name = "COD_UF")
	private String codUf;
	
	@Column(name = "COD_LOCAL")
	private String codLocal;
	
	@Column(name = "COD_PESSOA")
	private Integer codPessoa;

	public UnidadeConfig() {}
	
	public UnidadeConfig(Integer codUnidade, Integer codPci, Integer codMicrovix, String cnpj, String desModelo,
			String codUf, String codLocal, Integer codPessoa) {
		super();
		this.codUnidade = codUnidade;
		this.codPci = codPci;
		this.codMicrovix = codMicrovix;
		this.cnpj = cnpj;
		this.desModelo = desModelo;
		this.codUf = codUf;
		this.codLocal = codLocal;
		this.codPessoa = codPessoa;
	}

	public Integer getCodUnidade() {
		return codUnidade;
	}

	public void setCodUnidade(Integer codUnidade) {
		this.codUnidade = codUnidade;
	}

	public Integer getCodPci() {
		return codPci;
	}

	public void setCodPci(Integer codPci) {
		this.codPci = codPci;
	}

	public Integer getCodMicrovix() {
		return codMicrovix;
	}

	public void setCodMicrovix(Integer codMicrovix) {
		this.codMicrovix = codMicrovix;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getDesModelo() {
		return desModelo;
	}

	public void setDesModelo(String desModelo) {
		this.desModelo = desModelo;
	}

	public String getCodUf() {
		return codUf;
	}

	public void setCodUf(String codUf) {
		this.codUf = codUf;
	}

	public String getCodLocal() {
		return codLocal;
	}

	public void setCodLocal(String codLocal) {
		this.codLocal = codLocal;
	}

	public Integer getCodPessoa() {
		return codPessoa;
	}

	public void setCodPessoa(Integer codPessoa) {
		this.codPessoa = codPessoa;
	}
	
}