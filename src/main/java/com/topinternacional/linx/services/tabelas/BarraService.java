package com.topinternacional.linx.services.tabelas;

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
import com.topinternacional.linx.controller.tabelas.BarraController;
import com.topinternacional.linx.enun.Metodo;
import com.topinternacional.linx.enun.Sistemas;
import com.topinternacional.linx.enun.Status;
import com.topinternacional.linx.model.admin.Execucoes;
import com.topinternacional.linx.model.admin.repo.ExecucoesRepository;
import com.topinternacional.linx.model.nl.view.Barra;
import com.topinternacional.linx.model.nl.view.repo.BarraRepository;
import com.topinternacional.linx.services.util.SOAPService;

@Service
public class BarraService {
	
	@Autowired
	private BarraRepository repo;
	
	@Autowired
	private ExecucoesRepository execRepo;
 	
	@Autowired
	private SOAPService soap;		
	
	public Page<Barra> getBarras(Integer pag) {
		return (Page<Barra>) repo.findAll(PageRequest.of(pag-1, 10, Sort.by("codigoProduto")));
	}

	@Async("taskExecutor")
	public void exportMicrovix(Page<Barra> reg) {
		Integer totalPages =  reg.getTotalPages();
		
		//Registra a execução
		Execucoes exec = execRepo.save(new Execucoes("Processamento manual - envio código de barras", Sistemas.NL.getId(), Sistemas.MICROVIX.getId(), "Barras", totalPages)); 

		//Atualiza o status de execução
		BarraController.setStatus(Status.PROCESSANDO, 0);
		
		for (int pag=0; pag < totalPages; pag++) {
			Page<Barra> barra = getBarras(pag+1);
			soap.importar(Metodo.LinxCadastraProdutosCodebar, getRegistros((List<Barra>) barra.getContent()), pag, totalPages, "Barras", exec.getId());
			
			BarraController.setStatus(Status.PROCESSANDO, ((float)pag/(float)totalPages*100));
		}
		
		BarraController.setStatus(Status.CONCLUIDO, 100);
	}

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
}