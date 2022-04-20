package com.topinternacional.linx.service.tabelas;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.topinternacional.linx.controller.tabelas.BarraController;
import com.topinternacional.linx.dto.Coluna;
import com.topinternacional.linx.dto.Registro;
import com.topinternacional.linx.enun.Metodo;
import com.topinternacional.linx.enun.Sistemas;
import com.topinternacional.linx.enun.Status;
import com.topinternacional.linx.model.admin.Execucoes;
import com.topinternacional.linx.model.admin.repo.ExecucoesRepository;
import com.topinternacional.linx.model.nl.view.Barra;
import com.topinternacional.linx.model.nl.view.repo.BarraRepository;
import com.topinternacional.linx.service.SOAPService;

@Service
public class BarraService {
	
	@Autowired
	private BarraRepository repo;
	
	@Autowired
	private ExecucoesRepository execRepo;
 	
	@Autowired
	private SOAPService soap;		

	private ArrayList<Registro> getRegistros(List<Barra> list) {
		ArrayList<Registro> registros = new ArrayList<Registro>();
		for (Barra barra: list) {
			Registro registro = new Registro();
			registro.getColunas().add(new Coluna("cod_barras", barra.getCodBarras()));		
			registro.getColunas().add(new Coluna("codigo_produto", barra.getCodigoProduto()));			
			registros.add(registro);
		}
		return registros;
	}

	public Page<Barra> getBarras(Integer codigo, String setor, Sort sort, int pag) {
		if (codigo == null) {
			return (Page<Barra>) repo.findByFilters(PageRequest.of(pag-1, 10, sort));
		}
		return (Page<Barra>) repo.findByFilters(codigo, PageRequest.of(pag-1, 10, sort));
	}

	@Async("taskExecutor")
	public void exportMicrovix(Integer codigo, String setor, Sort sort) {
		Page<Barra> barra = getBarras(codigo, setor, sort, 1);
		Integer totalPages =  barra.getTotalPages();
		
		String filtro = "(setor="+setor+", codigo="+codigo+", sort="+sort+")"; 
		
		//Registra a execução
		Execucoes exec = execRepo.save(new Execucoes("Processamento manual - envio código de barras - "+filtro, Sistemas.NL.getId(), Sistemas.MICROVIX.getId(), "Barras", totalPages)); 

		//Atualiza o status de execução
		BarraController.setStatus(Status.PROCESSANDO, 0);
		
		//Importando a pagina/lote 1
		soap.importar(Metodo.LinxCadastraProdutosCodebar, getRegistros((List<Barra>) barra.getContent()), 0, totalPages, "Barras", exec.getId());
		
		for (int pag=1; pag < totalPages; pag++) {
			barra = getBarras(codigo, setor, sort, pag);
			soap.importar(Metodo.LinxCadastraProdutosCodebar, getRegistros((List<Barra>) barra.getContent()), pag, totalPages, "Barras", exec.getId());
			
			BarraController.setStatus(Status.PROCESSANDO, ((float)pag/(float)totalPages*100));
		}
		
		BarraController.setStatus(Status.CONCLUIDO, 100);
	}
}