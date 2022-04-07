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
import com.topinternacional.linx.controller.tabelas.NcmController;
import com.topinternacional.linx.enun.Metodo;
import com.topinternacional.linx.enun.Sistemas;
import com.topinternacional.linx.enun.Status;
import com.topinternacional.linx.model.admin.Execucoes;
import com.topinternacional.linx.model.admin.repo.ExecucoesRepository;
import com.topinternacional.linx.model.nl.view.Ncm;
import com.topinternacional.linx.model.nl.view.repo.NcmRepository;
import com.topinternacional.linx.services.util.SOAPService;

@Service
public class NcmService {
	
	@Autowired
	private NcmRepository repo;
	
	@Autowired
	private ExecucoesRepository execRepo;
 	
	@Autowired
	private SOAPService soap;		
	
	public Page<Ncm> getNcms(Integer pag) {
		return (Page<Ncm>) repo.findAll(PageRequest.of(pag-1, 10, Sort.by("codigo")));
	}

	@Async("taskExecutor")
	public void exportMicrovix(Page<Ncm> reg) {
		Integer totalPages =  reg.getTotalPages();
		
		//Registra a execução
		Execucoes exec = execRepo.save(new Execucoes("Processamento manual - envio dos código NCM", Sistemas.NL.getId(), Sistemas.MICROVIX.getId(), "NCM", totalPages)); 

		//Atualiza o status de execução
		NcmController.setStatus(Status.PROCESSANDO, 0);
		
		for (int pag=0; pag < totalPages; pag++) {
			Page<Ncm> ncm = (Page<Ncm>) repo.findAll(PageRequest.of(pag, 10, Sort.by("codigo")));
			soap.importar(Metodo.LinxCadastraNcm, getRegistros((List<Ncm>) ncm.getContent()), pag, totalPages, "NCM", exec.getId());
			
			NcmController.setStatus(Status.PROCESSANDO, ((float)pag/(float)totalPages*100));
		}
		
		NcmController.setStatus(Status.CONCLUIDO, 100);
	}

	private ArrayList<Registro> getRegistros(List<Ncm> list) {
		ArrayList<Registro> registros = new ArrayList<Registro>();
		for (Ncm ncm: list) {
			Registro registro = new Registro();
			registro.getColunas().add(new Coluna("codigo", ncm.getCodigo()));			
			registro.getColunas().add(new Coluna("descricao", ncm.getDescricao()));		
			registros.add(registro);
		}
		return registros;
	}
}