package com.topinternacional.linx.services.cadastros;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import com.topinternacional.linx.bean.Coluna;
import com.topinternacional.linx.bean.Registro;
import com.topinternacional.linx.controller.cadastros.ProdutoController;
import com.topinternacional.linx.enun.Metodo;
import com.topinternacional.linx.enun.Sistemas;
import com.topinternacional.linx.enun.Status;
import com.topinternacional.linx.model.admin.Execucoes;
import com.topinternacional.linx.model.admin.repo.ExecucoesRepository;
import com.topinternacional.linx.model.nl.view.Produto;
import com.topinternacional.linx.model.nl.view.repo.ProdutoRepository;
import com.topinternacional.linx.services.util.SOAPService;
import com.topinternacional.linx.services.util.Util;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository repo;
	
	@Autowired
	private ExecucoesRepository execRepo;
 	
	@Autowired
	private SOAPService soap;		
	
	public Page<Produto> getProdutos(String codigo, String marca, String setor, Sort sort, int pag) {
		PageRequest pr = PageRequest.of(pag-1, 10, sort);
		
		if (setor.equals("SN")) setor = "";
		if (codigo.equals("SN")) codigo = "";
		if (marca.equals("SN")) marca = "";

		if (!codigo.equals("")) {			
			return (Page<Produto>) repo.findByFilters(codigo, pr);	
		} else if (!marca.equals("") && !setor.equals("")) {
			return (Page<Produto>) repo.findByFilters(marca, setor, pr);
		} else if (marca.equals("") && setor.equals("") && codigo.equals("")) {
			return (Page<Produto>) repo.findAll(pr);
		}
		return (Page<Produto>) repo.findByFilters(codigo, marca, setor, pr);
	}
	
	@Async("taskExecutor")
	public void exportMicrovix(String codigo, String marca, String setor, Sort sort, String action) {
		Page<Produto> produto = getProdutos(codigo, marca, setor, sort, 1);
		Integer totalPages =  produto.getTotalPages();
		
		
		String filtro = "(setor="+setor+", marca="+marca+", codigo="+codigo+", sort="+sort+")"; 
				
		
		//Registra a execução
		Execucoes exec = execRepo.save(new Execucoes("Envio manuasl de produtos, "+Util.getSetor(setor)+" - "+filtro, Sistemas.NL.getId(), Sistemas.MICROVIX.getId(), "Produto", totalPages)); 

		//Atualiza o status de execução
		ProdutoController.setStatus(Status.PROCESSANDO, 0);
		
		//Importando a pagina/lote 1
		soap.importar(Metodo.LinxCadastraProdutos, getRegistros(action, (List<Produto>) produto.getContent()), 0, totalPages, "Produto", exec.getId());
		
		for (int pag=1; pag < totalPages; pag++) {
			produto = getProdutos(codigo, marca, setor, sort, pag);
			soap.importar(Metodo.LinxCadastraProdutos, getRegistros(action, (List<Produto>) produto.getContent()), pag, totalPages, "Produto", exec.getId());
			ProdutoController.setStatus(Status.PROCESSANDO, ((float)pag/(float)totalPages*100));
		}
		ProdutoController.setStatus(Status.CONCLUIDO, 100);
	}

	public ArrayList<Registro> getRegistros(String action, List<Produto> content) {
		switch (action) {
			case ProdutoController.ACTION_PRECO_CUSTO:
				return getRegistrosCusto(content);
			case ProdutoController.ACTION_PRECO_VENDA:
				return getRegistrosVenda(content);
			case ProdutoController.ACTION_TODOS_ITENS:
				return getRegistros(content);
			case ProdutoController.ACTION_TRIBUTARIOS:
				return getRegistrosTributario(content);		
			case ProdutoController.ACTION_CODAUXILIAR:
				return getRegistrosCodAuxiliar(content);	
		}
		return getRegistros(content);
	}

	public ArrayList<Registro> getRegistros(List<Produto> list) {
		ArrayList<Registro> registros = new ArrayList<Registro>();
		for (Produto produto: list) {
			Registro registro = new Registro();
			registro.getColunas().add(new Coluna("codigo", produto.getCodigo()));		
			registro.getColunas().add(new Coluna("nome_produto", produto.getNomeProduto()));
			registro.getColunas().add(new Coluna("cod_fornecedor", produto.getCodFornecedor()));
			registro.getColunas().add(new Coluna("referencia", produto.getReferencia()));
			registro.getColunas().add(new Coluna("cod_auxiliar", produto.getCodAuxiliar()));
			registro.getColunas().add(new Coluna("cod_setor", produto.getCodSetor()));
			registro.getColunas().add(new Coluna("cod_linha", produto.getCodSetor()+produto.getCodLinha()));
			registro.getColunas().add(new Coluna("cod_marca", produto.getCodSetor()+produto.getCodMarca()));
			registro.getColunas().add(new Coluna("cod_colecao", produto.getCodColecao()));
			registro.getColunas().add(new Coluna("cod_grade1", produto.getCodGrade1()));
			registro.getColunas().add(new Coluna("cod_grade2", produto.getCodGrade2()));
			registro.getColunas().add(new Coluna("unidade", produto.getUnidade().trim()));
			registro.getColunas().add(new Coluna("preco_custo", produto.getPrecoCusto()));
			registro.getColunas().add(new Coluna("preco_venda", produto.getPrecoVenda()));
			registro.getColunas().add(new Coluna("ncm", produto.getNcm()));
			
			if (Util.isValid(produto.getPesoBruto())) {
				registro.getColunas().add(new Coluna("peso_bruto", produto.getPesoBruto()));
			} else {
				registro.getColunas().add(new Coluna("peso_bruto", "0.0"));
			}
			
			if (Util.isValid(produto.getPesoLiquido())) {
				registro.getColunas().add(new Coluna("peso_liquido", produto.getPesoLiquido()));
			} else {
				registro.getColunas().add(new Coluna("peso_liquido", "0.0"));
			}

			registros.add(registro);
		}
		return registros;
	}

	private ArrayList<Registro> getRegistrosVenda(List<Produto> list) {
		ArrayList<Registro> registros = new ArrayList<Registro>();
		for (Produto produto: list) {
			Registro registro = new Registro();
			registro.getColunas().add(new Coluna("codigo", produto.getCodigo()));					
			registro.getColunas().add(new Coluna("preco_venda", produto.getPrecoVenda()));
			
			registros.add(registro);
		}
		return registros;
	}
	
	private ArrayList<Registro> getRegistrosCusto(List<Produto> list) {
		ArrayList<Registro> registros = new ArrayList<Registro>();
		for (Produto produto: list) {
			Registro registro = new Registro();
			registro.getColunas().add(new Coluna("codigo", produto.getCodigo()));					
			registro.getColunas().add(new Coluna("preco_custo", produto.getPrecoCusto()));						
			
			registros.add(registro);
		}
		return registros;
	}
	
	private ArrayList<Registro> getRegistrosCodAuxiliar(List<Produto> list) {
		ArrayList<Registro> registros = new ArrayList<Registro>();
		for (Produto produto: list) {
			Registro registro = new Registro();
			registro.getColunas().add(new Coluna("codigo", produto.getCodigo()));					
			registro.getColunas().add(new Coluna("cod_auxiliar", produto.getCodAuxiliar()));						
			
			registros.add(registro);
		}
		return registros;
	}
	
	private ArrayList<Registro> getRegistrosTributario(List<Produto> list) {
		ArrayList<Registro> registros = new ArrayList<Registro>();
		for (Produto produto: list) {
			Registro registro = new Registro();
			registro.getColunas().add(new Coluna("codigo", produto.getCodigo()));
			registro.getColunas().add(new Coluna("cest", produto.getCest())); 
			registros.add(registro);
		}
		return registros;
	}
}