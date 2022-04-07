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
import com.topinternacional.linx.controller.tabelas.LinhaController;
import com.topinternacional.linx.enun.Metodo;
import com.topinternacional.linx.enun.Sistemas;
import com.topinternacional.linx.enun.Status;
import com.topinternacional.linx.model.admin.Execucoes;
import com.topinternacional.linx.model.admin.repo.ExecucoesRepository;
import com.topinternacional.linx.model.nl.view.Linha;
import com.topinternacional.linx.model.nl.view.repo.LinhaRepository;
import com.topinternacional.linx.services.util.SOAPService;

@Service
public class LinhaService {
	
	@Autowired
	private LinhaRepository linhaRepository;
	
	@Autowired
	private ExecucoesRepository execucoesRepository;
 	
	@Autowired
	private SOAPService soap;		
	
	public Page<Linha> getLinhas(Integer pag) {
		return (Page<Linha>) linhaRepository.findAll(PageRequest.of(pag-1, 10, Sort.by("codigo")));
	}

	@Async("taskExecutor")
	public void exportMicrovix(Page<Linha> linhas) {
		Integer totalPages =  linhas.getTotalPages();
		Execucoes exec = execucoesRepository.save(new Execucoes("Processamento manual - envio das linhas comerciais", Sistemas.NL.getId(), Sistemas.MICROVIX.getId(), "Linha", totalPages)); 

		LinhaController.setStatus(Status.PROCESSANDO, 0);
		
		for (int pag=0; pag < totalPages; pag++) {
			Page<Linha> linha = (Page<Linha>) linhaRepository.findAll(PageRequest.of(pag, 10, Sort.by("codigo")));
			soap.importar(Metodo.LinxCadastraLinhas, getRegistros((List<Linha>) linha.getContent()), pag, totalPages, "Linha", exec.getId());
			
			LinhaController.setStatus(Status.PROCESSANDO, ((float)pag/(float)totalPages*100));
		}
		
		LinhaController.setStatus(Status.CONCLUIDO, 100);
	}

	private ArrayList<Registro> getRegistros(List<Linha> list) {
		ArrayList<Registro> registros = new ArrayList<Registro>();
		for (Linha linha: list) {
			Registro registro = new Registro();
			registro.getColunas().add(new Coluna("codigo", linha.getCodigo()));			
			registro.getColunas().add(new Coluna("nome_linha", linha.getNomeLinha()));		
			registros.add(registro);
		}
		return registros;
	}
}