package com.topinternacional.linx.services.cadastros;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.topinternacional.linx.api.BasicService;
import com.topinternacional.linx.bean.Coluna;
import com.topinternacional.linx.bean.Registro;
import com.topinternacional.linx.controller.cadastros.PessoaController;
import com.topinternacional.linx.enun.Metodo;
import com.topinternacional.linx.enun.Sistemas;
import com.topinternacional.linx.enun.Status;
import com.topinternacional.linx.model.admin.Execucoes;
import com.topinternacional.linx.model.nl.view.Pessoa;
import com.topinternacional.linx.model.nl.view.repo.PessoaRepository;
import com.topinternacional.linx.services.util.Util;

@Service
public class PessoaService extends BasicService {
	
	@Autowired
	private PessoaRepository repo;
	
	public Page<Pessoa> getPessoas(String codigo, String nome, String tipo, String tipopessoa, Sort sort, int pag) {
		PageRequest pr = PageRequest.of(pag-1, 10, sort);
		Page<Pessoa> pessoas = null;

		if (codigo.equals("SN") && nome.equals("SN") && tipo.equals("SN")) {
			pessoas = (Page<Pessoa>) repo.findAllPessoas(tipopessoa, pr);
		} else if (codigo.equals("SN") && nome.equals("SN") && !tipo.equals("SN")) {
			pessoas = (Page<Pessoa>) repo.findAllByFilter(tipo, tipopessoa,  pr);
		}  else if (codigo.equals("SN") && !nome.equals("SN") && tipo.equals("SN")) {
			pessoas = (Page<Pessoa>) repo.findAllByName(nome, tipopessoa, pr); 
		}  else if (!codigo.equals("SN") && nome.equals("SN") && tipo.equals("SN")) {
			pessoas = (Page<Pessoa>) repo.findAllByCodigo(codigo, tipopessoa, pr); 	
		} else {	
			pessoas = (Page<Pessoa>) repo.findByCodigoOrNomeRazaoSocialOrPfPj(codigo, nome, tipo, pr);
		}
		return pessoas;
	}  
	
	@Async("taskExecutor") 
	public void exportMicrovix(String codigo, String nome, String tipo, String tipopessoa, Sort sort, String action) {
		Page<Pessoa> pessoas = getPessoas(codigo, nome, tipo, tipopessoa, sort, 1);
		Integer totalPages =  pessoas.getTotalPages();
		
		//Registra a execução
		Execucoes exec = execRepo.save(new Execucoes("Processamento manual - envio dos "+tipopessoa, Sistemas.NL.getId(), Sistemas.MICROVIX.getId(), "Pessoa", totalPages)); 

		//Atualiza o status de execução
		PessoaController.setStatus(Status.PROCESSANDO, 0);
		
		//Importando a pagina/lote 1
		soap.importar(Metodo.LinxCadastraClientesFornecedores, getRegistros(action, (List<Pessoa>) pessoas.getContent()), 0, totalPages, "Pessoa", exec.getId());
		
		for (int pag=1; pag < totalPages; pag++) {
			pessoas = getPessoas(codigo, nome, tipo, tipopessoa, sort, pag);
			soap.importar(Metodo.LinxCadastraClientesFornecedores, getRegistros(action, (List<Pessoa>) pessoas.getContent()), pag, totalPages, "Pessoa", exec.getId());
			PessoaController.setStatus(Status.PROCESSANDO, ((float)pag/(float)totalPages*100));
		}
		PessoaController.setStatus(Status.CONCLUIDO, 100);
	}
	
	private ArrayList<Registro> getRegistros(String action, List<Pessoa> content) {
		switch (action) {
			case PessoaController.ACTION_CPFPJ:
				return getRegistrosCPFCNPJ(content);
			case PessoaController.ACTION_TODOS:
				return getRegistros(content);						
		}
		return getRegistros(content);
	}

	private ArrayList<Registro> getRegistros(List<Pessoa> list) {
		ArrayList<Registro> registros = new ArrayList<Registro>();
		for (Pessoa pessoa: list) {
			Registro registro = new Registro();
			
			registro.getColunas().add(new Coluna("codigo", pessoa.getCodigo()));			
			registro.getColunas().add(new Coluna("nome_razao_social", pessoa.getNomeRazaoSocial()));
			
			if (pessoa.getDocCliente().equals("00000000000000") || pessoa.getPfPj().equals("F")  ) {
				if (pessoa.getCodigo().substring(pessoa.getCodigo().length()-1).equalsIgnoreCase("0"))  {
					registro.getColunas().add(new Coluna("doc_cliente", (pessoa.getCodigo()+"99999999999999").substring(0, 11)));
				} else {
					registro.getColunas().add(new Coluna("doc_cliente", (pessoa.getCodigo()+"00000000000000").substring(0, 11)));
				}
				
			} else {
				registro.getColunas().add(new Coluna("doc_cliente", pessoa.getDocCliente()));
			}
			
			registro.getColunas().add(new Coluna("pf_pj", pessoa.getPfPj()));		
			registro.getColunas().add(new Coluna("endereco", pessoa.getEndereco()));		
			registro.getColunas().add(new Coluna("cidade", pessoa.getCidade()));		
			registro.getColunas().add(new Coluna("uf", pessoa.getUf()));	
			registro.getColunas().add(new Coluna("estado_civil", pessoa.getEstadoCivil()));	
			registro.getColunas().add(new Coluna("tipo", pessoa.getTipo()));		
			
			if (Util.isValid(pessoa.getNomeFantasia())) registro.getColunas().add(new Coluna("nome_fantasia", pessoa.getNomeFantasia()));
			if (Util.isValid(pessoa.getIdentidadeCliente())) registro.getColunas().add(new Coluna("identidade_cliente", pessoa.getIdentidadeCliente()));		
			if (Util.isValid(pessoa.getInscricaoCliente())) registro.getColunas().add(new Coluna("inscricao_cliente", pessoa.getInscricaoCliente()));					
			if (Util.isValid(pessoa.getComplemento())) registro.getColunas().add(new Coluna("complemento", pessoa.getComplemento()));		
			if (Util.isValid(pessoa.getBairro())) registro.getColunas().add(new Coluna("bairro", pessoa.getBairro()));		
			if (Util.isValid(pessoa.getCep())) registro.getColunas().add(new Coluna("cep", pessoa.getCep()));		
			if (Util.isValid(pessoa.getTelefone())) registro.getColunas().add(new Coluna("telefone", pessoa.getTelefone()));		
			if (Util.isValid(pessoa.getEmail())) registro.getColunas().add(new Coluna("email", pessoa.getEmail()));			
			if (Util.isValid(pessoa.getDataNascimento())) registro.getColunas().add(new Coluna("data_nascimento", pessoa.getDataNascimento()));
			if (Util.isValid(pessoa.getSexo())) registro.getColunas().add(new Coluna("sexo", pessoa.getSexo()));
			if (Util.isValid(pessoa.getPais())) registro.getColunas().add(new Coluna("pais", pessoa.getPais()));
			if (Util.isValid(pessoa.getAtualizaPorCpfCnpj())) registro.getColunas().add(new Coluna("atualizar_por_cpf_cnpj", pessoa.getAtualizaPorCpfCnpj()));
			
			registros.add(registro);
		}
		return registros;
	}
	
	private ArrayList<Registro> getRegistrosCPFCNPJ(List<Pessoa> list) {
		ArrayList<Registro> registros = new ArrayList<Registro>();
		for (Pessoa pessoa: list) {
			Registro registro = new Registro();
			
			registro.getColunas().add(new Coluna("codigo", pessoa.getCodigo()));
			registro.getColunas().add(new Coluna("nome_razao_social", pessoa.getNomeRazaoSocial()));
			
			if (pessoa.getDocCliente().equals("00000000000000") || pessoa.getPfPj().equals("F")  ) {
				if (pessoa.getCodigo().substring(pessoa.getCodigo().length()-1).equalsIgnoreCase("0"))  {
					registro.getColunas().add(new Coluna("doc_cliente", (pessoa.getCodigo()+"99999999999999").substring(0, 11)));
				} else {
					registro.getColunas().add(new Coluna("doc_cliente", (pessoa.getCodigo()+"00000000000000").substring(0, 11)));
				}
				
			} else {
				registro.getColunas().add(new Coluna("doc_cliente", pessoa.getDocCliente()));
			}
			
			registro.getColunas().add(new Coluna("pf_pj", pessoa.getPfPj()));		
			registro.getColunas().add(new Coluna("endereco", pessoa.getEndereco()));		
			registro.getColunas().add(new Coluna("cidade", pessoa.getCidade()));		
			registro.getColunas().add(new Coluna("uf", pessoa.getUf()));	
			registro.getColunas().add(new Coluna("estado_civil", pessoa.getEstadoCivil()));	
			registro.getColunas().add(new Coluna("tipo", pessoa.getTipo()));
			
			if (Util.isValid(pessoa.getAtualizaPorCpfCnpj())) registro.getColunas().add(new Coluna("atualizar_por_cpf_cnpj", "1"));
			
			registros.add(registro);
		}
		return registros;
	}
}