package com.topinternacional.linx.services.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.topinternacional.linx.api.BasicService;
import com.topinternacional.linx.controller.admin.LogController;
import com.topinternacional.linx.enun.Metodo;
import com.topinternacional.linx.enun.Status;
import com.topinternacional.linx.model.admin.Execucoes;
import com.topinternacional.linx.model.admin.Log;
import com.topinternacional.linx.model.admin.repo.LogRepository;
import com.topinternacional.linx.model.nl.view.Produto;
import com.topinternacional.linx.services.cadastros.ProdutoService;

@Service
public class LogService extends BasicService{
	
	@Autowired
	private LogRepository repo;
	
	@Autowired
	private ProdutoService produtoServ;
	
	
	public Page<Log> getLog(Long id, Sort sort, int pag) {
		PageRequest pr = PageRequest.of(pag-1, 10, sort);
		return (Page<Log>) repo.findByFilters(id, pr);
	}

	public Double getLogSucess(Long id, long tot) {
        Double sucess_percent = repo.findSucessPercent(id);
        if (sucess_percent > 0) {
        	return (sucess_percent/tot)*100;
        } else {
        	return 0D;
        }	
	}

	public void excluirExecucao(Long id) {
		execRepo.deleteById(id);
	}

	@Async("taskExecutor")
	public void reprocesarErros(Execucoes exec) {
		if (exec.getCadastro().equals("Produto")) {
			String[] params = exec.getNome().substring((exec.getNome().indexOf("(")+1), exec.getNome().indexOf(")")).split(",");
			String setor = params[0].split(":")[0].split("=")[1];
			String marca = params[1].split(":")[0].split("=")[1];
			String codigo = params[2].split(":")[0].split("=")[1];
			String sort = params[3].split(":")[0].split("=")[1];
			
			Page<Produto> produto = produtoServ.getProdutos(codigo, marca, setor, Sort.by(sort), 1);
			
			Integer totalPages =  produto.getTotalPages();		 				
	
			List<Log> logs = repo.findAllErros(exec.getId());
			
			LogController.setStatus(Status.PROCESSANDO, 0);
			
			for (Log log : logs) {
				Integer pag = Integer.valueOf(log.getLog().substring((log.getLog().indexOf("lote/página")+12), (log.getLog().indexOf(" de "))));
				
				produto = produtoServ.getProdutos(codigo, marca, setor, Sort.by(sort), pag);
				
				soap.reprocessar(Metodo.LinxCadastraProdutos, produtoServ.getRegistros((List<Produto>) produto.getContent()), pag, logs.size(), "Produto", exec.getId(), log);
				LogController.setStatus(Status.PROCESSANDO, ((float)pag/(float)totalPages*100));
				
			}
			
			LogController.setStatus(Status.CONCLUIDO, 100);
		} else if (exec.getCadastro().equals("Notas Fiscais")) {
			try {
				
				LogController.setStatus(Status.PROCESSANDO, 0);		
				Thread.sleep(1000);
				LogController.setStatus(Status.PROCESSANDO, 25);
				Thread.sleep(1000);
				LogController.setStatus(Status.PROCESSANDO, 50);
				Thread.sleep(1000);
				LogController.setStatus(Status.PROCESSANDO, 75);	
				Thread.sleep(1000);
				LogController.setStatus(Status.CONCLUIDO, 100);
			
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}