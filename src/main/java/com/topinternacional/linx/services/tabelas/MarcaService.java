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
import com.topinternacional.linx.controller.tabelas.MarcaController;
import com.topinternacional.linx.enun.Metodo;
import com.topinternacional.linx.enun.Sistemas;
import com.topinternacional.linx.enun.Status;
import com.topinternacional.linx.model.admin.Execucoes;
import com.topinternacional.linx.model.admin.repo.ExecucoesRepository;
import com.topinternacional.linx.model.nl.view.Marca;
import com.topinternacional.linx.model.nl.view.repo.MarcaRepository;
import com.topinternacional.linx.services.util.SOAPService;

@Service
public class MarcaService {
	
	@Autowired
	private MarcaRepository repo;
	
	@Autowired
	private ExecucoesRepository execRepo;
 	
	@Autowired
	private SOAPService soap;		
	
	public Page<Marca> getMarcas(Integer pag) {
		return (Page<Marca>) repo.findAll(PageRequest.of(pag-1, 10, Sort.by("codigo")));
	}

	@Async("taskExecutor")
	public void exportMicrovix(Page<Marca> reg) {
		Integer totalPages =  reg.getTotalPages();
		
		//Registra a execução
		Execucoes exec = execRepo.save(new Execucoes("Processamento manual - envio das marcas comerciais", Sistemas.NL.getId(), Sistemas.MICROVIX.getId(), "Marca", totalPages)); 

		//Atualiza o status de execução
		MarcaController.setStatus(Status.PROCESSANDO, 0);
		
		for (int pag=0; pag < totalPages; pag++) {
			Page<Marca> marca = (Page<Marca>) repo.findAll(PageRequest.of(pag, 10, Sort.by("codigo")));
			soap.importar(Metodo.LinxCadastraMarcas, getRegistros((List<Marca>) marca.getContent()), pag, totalPages, "Marca", exec.getId());
			
			MarcaController.setStatus(Status.PROCESSANDO, ((float)pag/(float)totalPages*100));
		}
		
		MarcaController.setStatus(Status.CONCLUIDO, 100);
	}

	private ArrayList<Registro> getRegistros(List<Marca> list) {
		ArrayList<Registro> registros = new ArrayList<Registro>();
		for (Marca marca: list) {
			Registro registro = new Registro();
			registro.getColunas().add(new Coluna("codigo", marca.getCodigo()));			
			registro.getColunas().add(new Coluna("nome_marca", marca.getNomeMarca()));		
			registros.add(registro);
		}
		return registros;
	}
}