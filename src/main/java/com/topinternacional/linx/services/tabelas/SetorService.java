package com.topinternacional.linx.services.tabelas;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.topinternacional.linx.bean.Coluna;
import com.topinternacional.linx.bean.Registro;
import com.topinternacional.linx.controller.tabelas.SetorController;
import com.topinternacional.linx.enun.Metodo;
import com.topinternacional.linx.enun.Sistemas;
import com.topinternacional.linx.enun.Status;
import com.topinternacional.linx.model.admin.Execucoes;
import com.topinternacional.linx.model.admin.repo.ExecucoesRepository;
import com.topinternacional.linx.model.nl.view.Setor;
import com.topinternacional.linx.model.nl.view.repo.SetorRepository;
import com.topinternacional.linx.services.util.SOAPService;

@Service
public class SetorService {
	
	@Autowired
	private SetorRepository setorRepository;
	
	@Autowired
	private ExecucoesRepository execucoesRepository;
 	
	@Autowired
	private SOAPService soap;		
	
	public Page<Setor> getSetores() {		
		return setorRepository.findAll(PageRequest.of(0, 10));
	}

	@Async("taskExecutor")
	public void exportMicrovix(Page<Setor> reg) {
		Integer totalPages =  reg.getTotalPages();
		Execucoes exec = execucoesRepository.save(new Execucoes("Processamento manual - envio dos setores comerciais", Sistemas.NL.getId(), Sistemas.MICROVIX.getId(), "Setor", totalPages)); 

		SetorController.setStatus(Status.PROCESSANDO, 0);
		
		for (int pag=0; pag < totalPages; pag++) {
			Page<Setor> setor = (Page<Setor>) setorRepository.findAll(PageRequest.of(pag, 10));
			soap.importar(Metodo.LinxCadastraSetores, getRegistros((List<Setor>) setor.getContent()), pag, totalPages, "Setor", exec.getId());
			SetorController.setStatus(Status.PROCESSANDO, ((float)pag/(float)totalPages*100));
		}
		
		SetorController.setStatus(Status.CONCLUIDO, 100);
	}

	private ArrayList<Registro> getRegistros(List<Setor> list) {
		ArrayList<Registro> registros = new ArrayList<Registro>();
		for (Setor setor: list) {
			Registro registro = new Registro();
			registro.getColunas().add(new Coluna("codigo", setor.getCodigo()));			
			registro.getColunas().add(new Coluna("nome_setor", setor.getNomeSetor()));		
			registros.add(registro);
		}
		return registros;
	}
}