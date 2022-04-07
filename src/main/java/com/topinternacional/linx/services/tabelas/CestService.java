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
import com.topinternacional.linx.controller.tabelas.CestController;
import com.topinternacional.linx.enun.Metodo;
import com.topinternacional.linx.enun.Sistemas;
import com.topinternacional.linx.enun.Status;
import com.topinternacional.linx.model.admin.Execucoes;
import com.topinternacional.linx.model.admin.repo.ExecucoesRepository;
import com.topinternacional.linx.model.nl.view.Cest;
import com.topinternacional.linx.model.nl.view.repo.CestRepository;
import com.topinternacional.linx.services.util.SOAPService;

@Service
public class CestService {
	
	@Autowired
	private CestRepository repo;
	
	@Autowired
	private ExecucoesRepository execRepo;
 	
	@Autowired
	private SOAPService soap;		
	
	public Page<Cest> getCests(Integer pag) {
		return (Page<Cest>) repo.findAll(PageRequest.of(pag-1, 10, Sort.by("codigo")));
	}

	@Async("taskExecutor")
	public void exportMicrovix(Page<Cest> reg) {
		Integer totalPages =  reg.getTotalPages();
		
		//Registra a execução
		Execucoes exec = execRepo.save(new Execucoes("Processamento manual - envio dos codigos cest", Sistemas.NL.getId(), Sistemas.MICROVIX.getId(), "Cest", totalPages)); 

		//Atualiza o status de execução
		CestController.setStatus(Status.PROCESSANDO, 0);
		
		for (int pag=0; pag < totalPages; pag++) {
			Page<Cest> cest = (Page<Cest>) repo.findAll(PageRequest.of(pag, 10, Sort.by("codigo")));
			soap.importar(Metodo.LinxCadastraCest, getRegistros((List<Cest>) cest.getContent()), pag, totalPages, "Cest", exec.getId());
			
			CestController.setStatus(Status.PROCESSANDO, ((float)pag/(float)totalPages*100));
		}
		
		CestController.setStatus(Status.CONCLUIDO, 100);
	}
	
	private ArrayList<Registro> getRegistros(List<Cest> cests) {
		ArrayList<Registro> registros = new ArrayList<Registro>();
		for (Cest cest: cests) {
			Registro registro = new Registro();
			registro.getColunas().add(new Coluna("codigo", cest.getCodigo()));			
			registro.getColunas().add(new Coluna("descricao", cest.getDescricao()));		
			registros.add(registro);
		}
		return registros;
	}
}